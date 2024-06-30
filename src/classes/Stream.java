package youtube.classes;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.swing.ImageIcon;

/**
 * The Stream class represents a live stream
 * It stores information about the stream, such as stream ID, name, description,
 * start time, number of likes, and number of users
 * Implements Serializable for object serialization and Watchable for watchable items.
 */

public class Stream implements Serializable, Watchable {

    private String streamId;
    private ImageIcon thumbnail;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private int numberOfLikes = 0;
    private int numberOfUsers = 0;
    
    /**
     * Constructs a Stream object with the specified parameters.
     *
     * @param streamId      The unique identifier for the stream.
     * @param thumbnail     The stream's thumbnail image.
     * @param name          The name of the stream.
     * @param description   The description of the stream.
     * @param startTime     The start time of the stream.
     */

    public Stream( String streamId, ImageIcon thumbnail, String name, String description, LocalDateTime startTime){
        this.streamId = streamId;
        this.thumbnail = thumbnail;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
    }

    //(Getters and Setters)
    // id
    public String getStreamId(){ 
        return streamId;
    }
    public void setStreamId(String newStreamId){
        streamId = newStreamId;
    }

    // thumbnail
    public ImageIcon getThumbnail(){
        return thumbnail;
    }
    public void setThumnail(ImageIcon newThumbnail){
        thumbnail = newThumbnail;
    }

    // name
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }

    // description
    public String getDescription(){
        return description;
    }
    public void setDescription(String newDescription){
        description = newDescription;
    }

    // start time
    public LocalDateTime getStartTime(){
        return startTime;
    }
    public void setStartTime(LocalDateTime newStartTime){
        startTime = newStartTime;
    }

    // number of likes
    public int getNumberOfLikes(){
        return numberOfLikes;
    }
    public void setNumberOfLikes(int newNumberOfLikes){
        numberOfLikes = newNumberOfLikes;
    }
  
    // number of users
    public int getNumberOfUsers(){
        return numberOfUsers;
    }
    public void setNumberOfUsers(int newNumberOfUsers){
        numberOfUsers = newNumberOfUsers;
    }
    
    /**
     * Increases the number of users watching the stream by one.
     */
    public void incremeant(){
        numberOfUsers += 1;
    }

    
    /**
     * Returns a string representation of the stream's information.
     *
     * @return A string containing stream information.
     */
    public String InformationToString() {
        return "Id: " + this.streamId + '\n' +
                "Name: " + this.name + '\n' +
                "Description: " + this.description + '\n' +'\n' +
                "Start time: " + this.startTime + '\n' +
                "Number of likes: " + numberOfLikes + '\n' +
                "Number of Users: " + this.numberOfUsers;
    }
    
    /**
     * Returns a string representation of the stream's information for the current view.
     *
     * @return A string containing stream information for the current view.
     */
    @Override
    public String printInfoCurrView() {
        return "Infromation about stream: " + '\n' + '\n'+
                "Name:  " + this.name + '\n' +
                "Status: LIVE " + '\n' +
                "Description: " + this.description + '\n';
    }

    /**
     * Returns a string representation of the stream's general information.
     *
     * @return A string containing general stream information.
     */
    @Override
    public String printInfo() {
        return "Stream: " + this.name + '\n' ;
    }
    
    /**
     * Returns the premium version status of the stream
     * It has to be true, becasue all user should be able 
     * to watch it
     * 
     * @return The premium version status.
     */
    @Override
    public String version() {
        return "true";
    }
    
    
    /**
     * Returns the duration of the stream in seconds.
     *
     * @return The duration of the stream.
     */
    @Override
    public int duration() {
        return 0;
    }

    /**
     * Returns the name of the stream.
     *
     * @return The stream name.
     */
    @Override
    public String index() {
        return name;
    }
    
}
  
