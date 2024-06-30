package youtube.classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
 * The Channel class represents a channel
 * It stores information about the channel, such as channel ID, followers, uploaded videos
 * and uploaded streams
 * Implements Serializable for object serialization and Subject for observers.
 */

public class Channel implements Serializable, Subject{

    //observer pattern
    private LinkedList<Watchable> content = new LinkedList<>();
    private LinkedList<Observer> observers = new LinkedList<>();
    
    private String channelId;
    private ImageIcon thumbnail;
    private LocalDate dateOfJoining;
    private ArrayList<User> listOfFollowers;
    private ArrayList<Video> listOfUploadedVideos;
    
    private ArrayList<Stream> listOfUploadedStreams;
    private ArrayList<Stream> listOfEndedStreams;

    /**
     * Constructs a Channel object with the specified parameters.
     *
     * @param channelId      The unique identifier for the channel.
     * @param thumbnail      The channel's thumbnail image.
     * @param dateOfJoining  The date when the channel was created.
     */
    
    public Channel(String channelId, ImageIcon thumbnail, LocalDate dateOfJoining) {
        this.thumbnail = thumbnail;
        this.channelId = channelId;
        this.dateOfJoining = dateOfJoining;
        this.listOfFollowers = new ArrayList<>();
        this.listOfUploadedVideos = new ArrayList<>();
        this.listOfUploadedStreams = new ArrayList<>();
        this.listOfEndedStreams = new ArrayList<>();
    }
    
    // (Getters and Setters)

    // id
    public String getChannelId(){
        return channelId;
    }
    public void setChannelId(String newchannelId){
        channelId = newchannelId;
    }
    
    public void setThumbnail(ImageIcon newIcon){
        thumbnail = newIcon;
    }
    public ImageIcon getThumbnail(){
       return thumbnail;
    }

    // list of followers
    public ArrayList<User> getListOfFollowers(){
        return (ArrayList<User>) listOfFollowers;
    }
    public void setListOfFollowers(ArrayList<User> newlistOfFollowers){
        listOfFollowers = newlistOfFollowers;
    }

    // list of uploaded videos
    public ArrayList<Video> getListOfUploadedVideos(){
        return listOfUploadedVideos;
    }
    public void setListOfUploadedVideos(ArrayList<Video> newlistOfUploadedVideos){
        listOfUploadedVideos = newlistOfUploadedVideos;
    }
    
    public ArrayList<Stream> getListOfEndedStreams(){
        return listOfEndedStreams;
    }
    public void setListOEndedStreams(ArrayList<Stream> newlistOfEndedStreams){
        listOfEndedStreams = newlistOfEndedStreams;
    }
    
    public ArrayList<Stream> getListOfUploadedStreams(){
        return listOfUploadedStreams;
    }
    public void setListOfUploadedStreams(ArrayList<Stream> newlistOfUploadedStreams){
        listOfUploadedStreams = newlistOfUploadedStreams;
    }
    
    
    /**
     * Adds a user to the list of followers for this channel.
     *
     * @param newUser The user to be added.
     */
    public void addUser( User newUser){
        listOfFollowers.add(newUser);
    }
    
    /**
     * Adds a video to the list of uploaded videos for this channel.
     *
     * @param newVideo The video to be added.
     */
    public void addVideo(Video newVideo){
        listOfUploadedVideos.add(newVideo);
        addWatchable(newVideo);
    }
    
    /**
     * Adds a stream to the list of uploaded streams for this channel.
     *
     * @param newStream The stream to be added.
     */
    public void addStream(Stream newStream){
        listOfUploadedStreams.add(newStream);
        addWatchable(newStream);
    }
    
    // ... (Printing functions)
    
    /**
     * Returns a string representation of the channel's followers.
     *
     * @return A string containing followers.
     */
    public String printFollowers(){
        if (listOfFollowers.isEmpty()){
            return "";
        }
        
        String list = "List of followers: \n";
        for (User user:  listOfFollowers){
            list = list + user.getUserName()+ '\n';
        }
        return list;
    }
    
    /**
     * Returns a string representation of the channel's videos.
     *
     * @return A string containing videos.
     */
    public String printVideos(){
        if (listOfUploadedVideos.isEmpty()){
            return "";
        }
        String list = "List of uploaded videos: \n";
        for (Video video:  listOfUploadedVideos){
            list = list + video.getName() + '\n';
        }
        return list;
    }
    
    /**
     * Returns a string representation of the channel's streams.
     *
     * @return A string containing streams.
     */
    public String printStreams(){
        if (listOfUploadedStreams.isEmpty()){
            return "";
        }
        String list = "Started streams: \n";
        for (Stream stream:  listOfUploadedStreams){
            list = list + stream.getName() + '\n';
        }
        
        String list2 = "Ended streams: \n";
        for (Stream stream:  listOfEndedStreams){
            list2 = list2 + stream.getName() + '\n';
        }
        
        return list + '\n' + list2;
    }
    
    /**
     * Returns a string representation of the channel's information.
     *
     * @return A string containing channel information.
     */
    public String InformationToString(){
        return "ID: " + channelId + '\n' + '\n'+ printFollowers() + '\n' + printVideos() + '\n' + printStreams();
    }
    
    
    /**
     * Adds a watchable item to the content of the channel and notifies observers.
     *
     * @param watchable The watchable item to be added.
     */
    public void addWatchable(Watchable watchable){
        content.add(watchable);
        notifyObservers(watchable);
    }

    // ... (Observer pattern methods)
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Watchable watchable) {
        for( Observer observer: observers){
            observer.update(watchable);
        }
    }

    
}