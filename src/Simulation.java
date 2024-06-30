package youtube;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.ImageIcon;
import youtube.classes.Channel;
import youtube.classes.Stream;
import youtube.classes.User;
import youtube.classes.Video;
import youtube.classes.Watchable;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The Simulation class represents a YouTube-like simulation with users, channels, videos, and streams.
 * It includes multithreading to simulate user and channel activities.
 * This class is also serializable for saving and loading the simulation state.
 */

public class Simulation implements Serializable{
    Random random = new Random();
    private ArrayList<User> userList;
    private ArrayList<Channel> channelList;
    private ArrayList<Video> videoList;
    private ArrayList<Stream> streamList;
    
    private ArrayList<Stream> streamsToStart;

    private transient ArrayList<Thread> threads;
    private volatile boolean stopThreads = false;
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Constructs a new Simulation object, initializes user,
     * channel, video, stream lists and starts the simulation threads
     * ( User and Channel have thair own tasks to do).
     */
    
    public Simulation() {
        userList = new ArrayList<>();
        channelList = new ArrayList <>();
        videoList = new ArrayList<>();
        streamList = new ArrayList<>();

        streamsToStart = new ArrayList<>();
        threads = new ArrayList<>();
       
        //initialization
        addRandomChannels();
        addRandomUsers();
        addRandomVideos();
        addRandomStreams();
        addUsersToChannels();
        
        // start wokring
        startThreads(); 
        
    }
    
    // functions for initialization of data for users, channels, videos and streams
    private void addRandomUsers(){
        String[] firstNames = {"Andrzej","Urszula", "Maja", "Weronika", "Wiktoria", "Marianna", "Anna", "Patrycja",
        "Tymoteusz", "Jakub", "Matuesz", "Piotr", "Igor", "Jan", "Pawel", "Filip", "Stanislaw", "Tomasz", "Maciej", "Antoni"};
        String[] lastNames = {"Cyganiak", "Posiadala", "Komorowska", "Dombka", "Krol", "Myszkowska", "Bieganska", "Kazmierczak",
        "Pawlowski", "Parchliniak", "Marciniak", "Machinski", "Krauzlis", "Taciak", "Paluszkiewicz", "Kelar", "Urbanski", "Wilusz", "Brodniewicz", "Kicinski", "Guss"};
        String [] isPremium = {"true", "false"};
        
        for( int i= 0;  i< 18; i++){
            int age = random.nextInt(18,70);
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            LocalDate date = randomDate();
            User newUser = new User(i, new ImageIcon("images/default.png"), firstName, lastName, age, date, isPremium[random.nextInt(isPremium.length)]);
            userList.add(newUser);
            
            int numOfChannels = random.nextInt(1,channelList.size());
            ArrayList <Channel> addChannels = new ArrayList<>();
            
            for( int j=0 ; j < numOfChannels; j++){
                int ChannelIndex = random.nextInt(channelList.size());
                Channel currChannel = channelList.get(ChannelIndex);
                if (!addChannels.contains(currChannel)){
                    currChannel = channelList.get(ChannelIndex);        
                    newUser.addChannel(currChannel);
                    addChannels.add(currChannel);
                }
           }
        }
    }
    
    private void addRandomChannels(){
        for( int i = 0; i< 7; i++){
            String name = String.format("channel_%d", i);
            LocalDate date = randomDate();
            channelList.add(new Channel(name, new ImageIcon("images/channel.png"), date));
        }
    }
    
    private void addRandomVideos(){
        LocalDate date = randomDate();
        videoList.add(new Video("0", new ImageIcon("images/frozen.png"),"Frozen", "\"Frozen\" is an animated musical film released in 2013 that revolves around the challenges faced by two royal sisters, Elsa and Anna, in the kingdom of Arendelle.\n Elsa's magical ice powers inadvertently plunge the land into eternal winter, setting the stage for a journey of self-discovery and sisterly bonds.",
                102, date, "false"));
        
        date = randomDate();
        videoList.add(new Video("1", new ImageIcon("images/frozen2.png"),"Frozen2", "\"Frozen 2\" is a sequel to the 2013 animated musical film.\n Released in 2019, the story continues with Elsa, Anna, Kristoff, Olaf, and Sven embarking on a journey to discover the origin of Elsa's magical powers and save their kingdom.\n The film, with a runtime of approximately 103 minutes, explores themes of self-discovery, bravery, and the importance of unity.",
                103, date, "true"));
        
        date = randomDate();
        videoList.add(new Video("2", new ImageIcon("images/titanic.png"),"Titanic", "James Cameron's 1997 film \"Titanic\" is a cinematic masterpiece that unfolds a tragic love story between Jack Dawson, a penniless artist, and Rose DeWitt Bukater, a young aristocrat, against the backdrop of the ill-fated maiden voyage of the luxurious ship.\n The movie skillfully blends romance, drama, and historical events, earning critical acclaim for its breathtaking visuals, compelling performances by Leonardo DiCaprio and Kate Winslet, and the iconic line,\n \"I'm the king of the world!\" as Jack and Rose navigate the ship's doomed fate. \"Titanic\" became a cultural phenomenon, winning multiple Academy Awards, including Best Picture, \n and remains one of the highest-grossing films of all time.",
                150, date, "false"));
        
        date = randomDate();
        videoList.add(new Video("3", new ImageIcon("images/Mission.png"),"Mission: Impossible - Fallout", "In \"Mission: Impossible - Fallout\" (2018), the sixth installment of the series, Ethan Hunt (Tom Cruise) and his team are tasked with preventing a global catastrophe after a mission goes awry. The film is a relentless rollercoaster of action, featuring breathtaking stunts, including a high-altitude skydiving sequence and an intense helicopter chase. With a complex plot, unexpected twists, and Henry Cavill's memorable performance, \"Fallout\" stands out as a high-energy and critically acclaimed addition to the \"Mission: Impossible\" franchise.",
                200, date, "false"));
        
        date = randomDate();
        videoList.add(new Video("4", new ImageIcon("images/barbie.png"),"Barbie in the 12 Dancing Princesses", "It is a classic fairy tale, twelve princesses are discovered to be mysteriously wearing out their shoes every night. Despite being locked in their room, they continue to escape and dance until dawn. The story takes a magical turn as a determined soldier endeavors to unravel the secret behind their nightly escapades, leading to the discovery of an enchanted world beneath the palace, full of silver trees and enchanted boat rides, adding a touch of enchantment to this timeless tale.",
                106, date, "true"));
        
        date = randomDate();
        videoList.add(new Video("5", new ImageIcon("images/ff1.png"),"Fast and furious 1", "\"In The Fast and the Furious\" (2001), the film introduces audiences to the world of illegal street racing and undercover police work. Dom Toretto, played by Vin Diesel, is the charismatic leader of a tight-knit racing community, while Brian O'Conner, portrayed by Paul Walker, infiltrates the group as an undercover cop. The film unfolds with high-stakes races, intense action sequences, and a growing camaraderie between Dom and Brian. \"The Fast and the Furious\" laid the foundation for a blockbuster franchise, captivating audiences with its adrenaline-pumping car chases, memorable characters, and a blend of action and camaraderie that has become synonymous with the series.",
                240, date, "false"));
        
        date = randomDate();
        videoList.add(new Video("6", new ImageIcon("images/ff7.png"),"Fast and furious 7", "\"Fast & Furious 7\" (2015), also known as \"Furious 7,\" is a high-octane action film that continues the iconic franchise. The movie grapples with the emotional aftermath of Paul Walker's tragic death, incorporating it into the storyline as his character, Brian O'Conner, retires from the team. The narrative revolves around the crew's pursuit of a sinister mercenary, Deckard Shaw (Jason Statham), while grappling with personal challenges and saying farewell to Walker's character in a poignant tribute. Packed with spectacular car chases, gravity-defying stunts, and a mix of emotion and adrenaline, \"Furious 7\" became a massive box office success, providing a fitting and heartfelt send-off to one of its beloved cast members.",
                260, date, "true"));
        
        date = randomDate();
        videoList.add(new Video("7", new ImageIcon("images/ff4.png"),"Fast and furious 4", "\"Fast & Furious\" (2009), the fourth installment in the franchise, reunites key members of the original cast as Vin Diesel's Dom Toretto and Paul Walker's Brian O'Conner team up again for a high-stakes mission. Set between the events of the first and third films, the story centers on an undercover operation to bring down a drug lord, intertwining themes of family, loyalty, and fast-paced action. With intense car chases, thrilling heists, and the return of the core characters, \"Fast & Furious\" marks a pivotal moment in the series' evolution, setting the stage for the subsequent films to come.",
                210, date, "false"));
        
        date = randomDate();
        videoList.add(new Video("8", new ImageIcon("images/gladiator.png"),"Gladiator", "\"Gladiator\" (2000), directed by Ridley Scott, is an epic historical drama set in ancient Rome. The film follows Maximus Decimus Meridius, a loyal general betrayed by the corrupt Commodus (Joaquin Phoenix) who murders Maximus's family and sends him into slavery. Maximus rises as a gladiator, seeking revenge against Commodus in the brutal arenas of the Colosseum. Filled with gripping action, powerful performances, and a memorable score by Hans Zimmer, \"Gladiator\" won multiple Academy Awards, including Best Picture and Best Actor for Russell Crowe, and stands as a cinematic triumph in the historical epic genre.",
                280, date, "true"));
        
        date = randomDate();
        videoList.add(new Video("9", new ImageIcon("images/dd.png"),"Dirty dancing", "\"Dirty Dancing\" (1987) is a classic romantic drama set in the summer of 1963. The film tells the story of Frances \"Baby\" Houseman, played by Jennifer Grey, who falls in love with Johnny Castle, a dance instructor portrayed by Patrick Swayze, while vacationing with her family at a resort. Against the backdrop of dance competitions and social class differences, \"Dirty Dancing\" explores themes of love, self-discovery, and breaking societal norms. The film is celebrated for its iconic dance sequences, particularly the finale featuring the famous lift, and its enduring soundtrack, making it a beloved and timeless piece of cinema.",
                240, date, "true"));
        
        date = randomDate();
        videoList.add(new Video("10", new ImageIcon("images/pw.png"),"Pretty woman", "\"Pretty Woman\" (1990) is a romantic comedy film directed by Garry Marshall. The story follows Vivian Ward, a Hollywood sex worker played by Julia Roberts, who is hired by wealthy businessman Edward Lewis, portrayed by Richard Gere, to be his escort for social events. As the two characters from different worlds spend time together, they develop a deep connection and transform each other's lives. Filled with charm, humor, and a fairy-tale romance, \"Pretty Woman\" became a cultural phenomenon and remains a beloved classic, known for its memorable moments and the irresistible chemistry between Roberts and Gere.",
                146, date, "true"));
        
        date = randomDate();
        videoList.add(new Video("11", new ImageIcon("images/av.png"),"Avengers: Endgame", "\"Endgame\"(2019) is a culmination of over a decade of interconnected storytelling in the Marvel Cinematic Universe (MCU), featuring a star-studded ensemble cast. The film delivers emotional and action-packed moments, resolving character arcs and bringing closure to the overarching narrative. With its record-breaking box office success and widespread acclaim, \"Avengers: Endgame\" stands as a monumental achievement in cinematic storytelling and a landmark in superhero filmmaking.",
                192, date, "false"));
        
        date = randomDate();
        videoList.add(new Video("12", new ImageIcon("images/spideraway.png"),"Spiderman: away from home", "In Ixtenco, Mexico, Nick Fury and Maria Hill investigate a mysterious storm and face the Earth Elemental, only to be assisted by Quentin Beck, a super-powered individual. Recruited by Fury, Beck joins forces to combat Elemental threats. Meanwhile, in New York, Peter Parker, still mourning Tony Stark, plans to confess his feelings to MJ during a school trip to Europe. However, his plans are disrupted when Fury seeks his assistance against new Elemental threats orchestrated by Beck. Unbeknownst to Parker, Beck is deceiving the world with illusions, and after a series of battles, he frames Spider-Man for a drone attack in London and reveals Parker's secret identity in a shocking twist. The mid-credits scene exposes Spider-Man's predicament, while the post-credits scene reveals that Fury and Hill were Skrulls, setting up future developments in the Marvel Cinematic Universe.",
                182, date, "true"));
        
        date = randomDate();
        videoList.add(new Video("13", new ImageIcon("images/spidernoway.png"),"Spiderman: no way home", "\"Spider-Man: No Way Home\" (2021). Directed by Jon Watts, the film continues the story of Peter Parker, played by Tom Holland, after the events of \"Spider-Man: Far From Home\" (2019). In \"No Way Home,\" Peter's life takes a chaotic turn when his identity as Spider-Man is exposed, leading him to seek help from Doctor Strange (Benedict Cumberbatch) to erase the knowledge from the world.",
                180, date, "false"));
        
    }
    
    private void addRandomStreams(){
        String[] images = {"images/s1.png", "images/s2.png", "images/s3.png", "images/s4.png"};
        String[] descriptions= { "Stream about gaming", "Stream about playing fifa", "Stream about doing shopping", "Chill stream", "Stream about the music"};
        for(int i = 0; i < 20; i++){
            String image = images[random.nextInt(images.length)];
            String name = String.format("stream_%d", i);
            String desc = descriptions[random.nextInt(descriptions.length)];
            LocalDateTime date = LocalDateTime.now();
            int addSec = random.nextInt(5, 150);
            date = date.plusSeconds(addSec);
            
            streamList.add(new Stream(String.format("%d", i), new ImageIcon( image),name, desc, date));
            streamsToStart.add(new Stream(String.format("%d", i), new ImageIcon( image),name, desc, date));
        }
    }
    
    
    /**
     * Returns a random date, which is used durring initialization
     *
     * @return a LocalDate which is random
     */
    public LocalDate randomDate(){
        int year = random.nextInt(2013,2023);
        int month = random.nextInt(1,12);
        int day = random.nextInt(1,28);
        LocalDate date = LocalDate.of(year, month, day);
        
        return date;
    }
    
    private void addUsersToChannels(){
        for(Channel channel: channelList){
            
            for( User user: userList){
                ArrayList listOfChannels = user.getFollowingChannels();
                if (listOfChannels.contains(channel)){
                    channel.registerObserver(user);
                    channel.addUser(user);
                }
            }
        }
    }
    
    
    // functions used after starting the simulation
    private void addVideoToChannel( Channel channel){
        lock.lock();
        try {
            int videoIndex = random.nextInt(videoList.size());
            Video video = videoList.get(videoIndex);
            ArrayList videosInChannel = channel.getListOfUploadedVideos();
            if(!videosInChannel.contains(video)){
                channel.addVideo(video);
            }
        } finally {
        lock.unlock();
        }
    }
    
    private void checkStreams(Channel channel){
        LocalDateTime now = LocalDateTime.now();
        lock.lock();
        try {
            for (Stream stream : streamList) {
                if (now.isAfter(stream.getStartTime())) {
                    
                    ArrayList<Stream> channelStartedStreams = channel.getListOfUploadedStreams();
                    ArrayList<Stream> channelDeletedStreams = channel.getListOfEndedStreams();

                    if (!channelStartedStreams.contains(stream) && !channelDeletedStreams.contains(stream)){
                        channel.addStream(stream);
                    }
                    
                }
            }
        } finally {
        lock.unlock();
        }
    }

    private void endOfStream(Channel channel){
        lock.lock();
        try {
            float prob = random.nextFloat(0,1);
            ArrayList<Stream> channelStartedStreams = channel.getListOfUploadedStreams();

            if (prob <= 0.2 && !channelStartedStreams.isEmpty()){
                int index = random.nextInt(channelStartedStreams.size());
                Stream delStream = channelStartedStreams.get(index);
                channelStartedStreams.remove(index);
                
                ArrayList<Stream> channelDeletedStreams = channel.getListOfEndedStreams();
                channelDeletedStreams.add(delStream);
                
                String streamInfo = String.format("Stream: %s\n", delStream.getName());
                boolean change = false;

                // if any user has this stream in the queue
                for (User user : channel.getListOfFollowers()) {
                    change = false;
                    Watchable currentView = user.getCurrentView();
                    if (currentView != null) {
                        String currView = currentView.printInfoCurrView();
                        if (streamInfo.equals(currView)) {
                            user.setCurrentView(null);
                            int users = delStream.getNumberOfUsers();
                            users --;
                            delStream.setNumberOfUsers(users);
                            change = true;
                        }
                        if(!change){
                            LinkedList<Watchable> currQueue =  user.getQueue();
                            for( Watchable watchable: currQueue){
                                String inqueue = watchable.printInfoCurrView();
                                if (streamInfo.equals(inqueue)){
                                    currQueue.remove(watchable);
                                    user.setQueue(currQueue);
                                    int users = delStream.getNumberOfUsers();
                                    users --;
                                    delStream.setNumberOfUsers(users);
                                    break;
                                }

                            }
                        }
                    } 
                }
                
            }
        } finally {
        lock.unlock();
        }
    }
    
    // function that will be used by multithreading
    private void channelTasks(Channel channel){
        while(!stopThreads){
            float prob = random.nextFloat(0,1);
            if (prob >= 0.95){
                addVideoToChannel(channel);
            }
            else if( prob >= 0.2){
                checkStreams(channel);
            }
            else{
                endOfStream(channel);
            }
            
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

        }
    }
    
    private void userTasks(User user){
        while(!stopThreads){
            Watchable currView = user.getCurrentView();
            // there is now watching
            if ( currView != null){
                int duration = currView.duration();

                if (duration != 0){  // it is video
                    user.setLastTimeWatching(LocalDateTime.now());
                    int differenceInSeconds = (int) java.time.Duration.between(user.getStartWatching(), LocalDateTime.now()).getSeconds();
                    if (differenceInSeconds > duration){
                        user.setCurrentView(null);
                        currView = null;
                    }
                }
            }
            
            // there is  now now watching
            LinkedList<Watchable> queue = user.getQueue();
            if (!queue.isEmpty() && currView == null ){

                int choosen = random.nextInt(queue.size());
                Watchable newCurrentView = queue.get(choosen);
                
                queue.remove(choosen);
                user.setStartWatching( LocalDateTime.now());
                user.setLastTimeWatching(LocalDateTime.now());
                user.setQueue(queue);
                user.setCurrentView(newCurrentView);
                
                String name = newCurrentView.index();
                if(name.contains("stream")){
                    lock.lock();
                    try {
                        for( Stream stream: streamList){
                            if(stream.getName().equals(name)){
                                stream.incremeant();
                                float prob = random.nextFloat(0,1);
                                if( prob <= 0.5){
                                    stream.setNumberOfLikes(stream.getNumberOfLikes()+1);
                                }
                            }
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();            
            }
        }
    }
    
    private void startThreads(){
        for(Channel channel: channelList){
            Thread thread = new Thread(() -> channelTasks(channel));
            threads.add(thread);
            thread.start();
        }
        
        for(User user: userList){
            Thread thread = new Thread(() -> userTasks(user));
            threads.add(thread);
            thread.start();
        }
    }
    
    
    /**
    * Stops all simulation threads by setting the stopThreads flag and interrupting each thread.
    * Waits for all threads to finish.
    */
    public void stopThreads() {
        stopThreads = true;

        for (Thread thread : threads) {
            thread.interrupt();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    
    /**
     * Restarts simulation threads after stopping. Creates new threads and starts them.
    */
    public void restartThreads() {
        stopThreads = false;

        threads.clear(); 

        for (Channel channel : channelList) {
            Thread thread = new Thread(() -> channelTasks(channel));
            threads.add(thread);
        }

        for (User user : userList) {
            Thread thread = new Thread(() -> userTasks(user));
            threads.add(thread);
        }

        startThreads();
    }

    // Other methods using serialization

    /**
     * Saves the current state of the simulation to a file.
     *
     * @param filePath The path to the file where the simulation state will be saved.
     */
    public void saveSimulation(String filePath) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outputStream.writeObject(this);
            System.out.println("Simulation saved successfully.");
        } catch (IOException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Loads a saved simulation state from a file.
     *
     * @param filePath The path to the file containing the saved simulation state.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    public void loadSimulation(String filePath) throws ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = inputStream.readObject();
            if (obj instanceof Simulation) {
                Simulation loadedSimulation = (Simulation) obj;
                // Update the current instance with the loaded data
                this.userList = loadedSimulation.userList;
                this.channelList = loadedSimulation.channelList;
                this.videoList = loadedSimulation.videoList;
                this.streamList = loadedSimulation.streamList;
                this.streamsToStart = loadedSimulation.streamsToStart;
                System.out.println("Simulation loaded successfully.");
            }
        } catch (IOException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // (Getters)
    public ArrayList<User> getUserList() {
        return userList;
    }
    
    public ArrayList<Channel> getChannelList(){
        return channelList;
    }
    
    public ArrayList<Video> getVideoList(){
        return videoList;
    }
    
    public ArrayList<Stream> getStreamList(){
        return streamList;
    }
    
    public ArrayList<Stream> getStreamsToStart(){
        return streamsToStart;
    }
    
    public boolean getStopValue(){
        return stopThreads;

    }
    
}


