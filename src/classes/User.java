package youtube.classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
* The User class represents a user
* It stores basic information about user ( e.g  name, thumbnail, 
* list of streams and videos to watch and informations about current watching content
* Implements Serializable to support object serialization and Observer for updates.
*/

public class User implements Serializable ,Observer{

    private Integer userId;
    private ImageIcon thumnail;
    private final String name;
    private String userName;
    private String email;
    private LocalDate dateOfJoining;
    private ArrayList <Channel> followingChannels; 
    private String isPremium;
    private Watchable currentView; 
    private LinkedList<Watchable> queue;
    
    private LocalDateTime startWatching;
    private LocalDateTime lastTimeWatching;
    
    /**
     * Constructs a User object with the specified parameters.
     *
     * @param userId        The unique identifier for the user.
     * @param thumnail     The user's profile picture.
     * @param name          The user's first name.
     * @param surname       The user's last name.
     * @param age           The user's age.
     * @param dateOfJoining The date when the user joined the platform.
     * @param isPremium     The premium status of the user.
     */
    public User(Integer userId, ImageIcon thumnail, String name, String surname, int age,  LocalDate dateOfJoining,String isPremium ){
        this.userId  = userId;
        this.thumnail = thumnail;
        this.name = name;
        this.userName = String.format("%s_%d", name, userId);
        this.email = String.format("%s%s%d@gmail.com", name, surname, 2023-age);
        this.dateOfJoining = dateOfJoining;
        this.isPremium = isPremium;
        this.followingChannels = new ArrayList<>();
        this.currentView = null;
        this.queue = new LinkedList<>();
        this.startWatching = null;
        this.lastTimeWatching = null;
    }
 

    //// getters and setters
    // id
    public Integer getUserId(){ 
        return userId;
    }
    public void setUserId(Integer newuserId){ 
        userId = newuserId; 
    }

    // thumbnail
    public ImageIcon getThumnail(){ 
        return thumnail;
    }
    public void setThumnail(ImageIcon newthumnail){ 
        thumnail = newthumnail; 
    }
    
    // userName
    public String getUserName(){
        return userName;
    }
    public void setUserName(String newName){
        userName = newName;
    }
    
    // email
    public String getEmail(){
        return email;
    }
    public void setEmail( String email){
        this.email = email;
    }

    // date of joining
    public LocalDate getDateOfJoining(){ 
        return dateOfJoining;
    }
    public void setDateOfJoining(LocalDate newdateOfJoining){ 
        dateOfJoining = newdateOfJoining; 
    }

    // following channels
    public ArrayList<Channel> getFollowingChannels(){ 
        return followingChannels;
    }
    public void setFollowingChannels(ArrayList<Channel> newfollowingChannels){ 
        followingChannels = newfollowingChannels;
    }

    // is premium
    public String getIsPremium(){
        return isPremium;
    }
    public void setIsPremium(String newisPremium){ 
        isPremium = newisPremium; 
    }
    
    // cuurent view
    public Watchable getCurrentView(){ 
        return currentView;
    }
    public void setCurrentView(Watchable newcurrentView){ 
        currentView = newcurrentView; 
    }

    // queue
    public LinkedList<Watchable> getQueue(){ 
        return queue;
    }
    public void setQueue(LinkedList<Watchable> newqueue){ 
        queue = newqueue;
    }
    
    public LocalDateTime getStartWatching(){
        return startWatching;
    }
    
    public void setStartWatching(LocalDateTime newStartWatching){
        startWatching = newStartWatching ;
    }
    
    public LocalDateTime getLastTimeWatching(){
        return lastTimeWatching;
    }
    
    public void setLastTimeWatching(LocalDateTime NewLastTimeWatching){
        lastTimeWatching = NewLastTimeWatching;
    }
    
    // other functions
    
    
    /**
     * Adds a new channel to the list of channels that the user is following.
     *
     * @param newChannel The channel to be added.
     */
    public void addChannel(Channel newChannel){
        followingChannels.add(newChannel);
    }
  
    
    /**
    * Return a string representation of all
    * following channels
    *
    * @param A string containing information
    */
    private String printFollowingChannels(){
        String following = "";
        for( Channel channel: followingChannels){
            following = following + channel.getChannelId() + '\n';
        }
        return following;
    }
    
    /**
    * Returns a string representation of information about
    * each content from queue
    *
    * @return A string containing user information.
    */
    private String printInTheQueue(){
        String queueInfo = "";
        for( Watchable watchable: queue){
            queueInfo = queueInfo + watchable.printInfo();
        }
        return queueInfo;
    }
    
    /**
    * Returns a string representation of information of
    * the user's current watching content.
    *
    * @return A string containing information.
    */
    public String printCurrView(){
        if (currentView != null){
            return "Current view: " + '\n' + currentView.printInfoCurrView();
        }
        return null;
    }

    /**
    * Returns a string representation of the user's information.
    *
    * @return A string containing user information.
    */
    public String informationToString(){
        return  "Index: " + this.userId + '\n' +
                "Name: " + this.name + '\n' +
                "Email: " + this.email + '\n' + '\n'+
                "Date of joining: " + this.dateOfJoining+ '\n' +
                "Statuts premium: " + this.isPremium + '\n' +
                "Following channels: " + '\n' + printFollowingChannels() + '\n' +
                "In the queue are: " + '\n'+ printInTheQueue();
    }
    
    
    // (Observer pattern)
    /**
    * Updates the user's queue if the content is not already in the user's queue
    * This method is called by the Observer pattern when a new watchable item is available.
    *
    * @param newWatchable The new watchable item.
    */
    @Override
    public void update(Watchable newWatchable) {
        String version = newWatchable.version();
        if(version.equals(isPremium) || version.contains("false")){
            String newName = newWatchable.printInfoCurrView();
            for( Watchable watchable: queue){
                if (newName.equals(watchable.printInfoCurrView())){
                    newName = "do_not_add";
                    break;
                }
            }
            if(!newName.equals("do_not_add"))
                queue.add(newWatchable);
        }
    }


}

