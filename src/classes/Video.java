package youtube.classes;

import java.io.Serializable;
import java.time.LocalDate;
import javax.swing.ImageIcon;

/**
 * The Video class represents a video
 * It stores basic information about the video (e.g such as video ID, name, description,
 * duration, upload date, number of views, number of likes, and premium status)
 * Implements Serializable for object serialization and Watchable for watchable items.
 */

public class Video implements Serializable, Watchable{

    
    private String videoId;
    private ImageIcon thumbnail;
    private String name;
    private String description;
    private Integer duration;
    private LocalDate dateOfUpload;
    private Integer numberOfViews;
    private Integer numberOfLikes;
    private String isPremiumVersion;

    /**
     * Constructs a Video object with the specified parameters.
     *
     * @param videoId          The unique identifier for the video.
     * @param thumbnail        The video's thumbnail image.
     * @param name             The name of the video.
     * @param description      The description of the video.
     * @param duration         The duration of the video in seconds.
     * @param dateOfUpload     The date when the video was uploaded.
     * @param isPremiumVersion The premium status of the video.
     */
    
    public Video(String videoId, ImageIcon thumbnail, String name, String description, Integer duration, LocalDate dateOfUpload, String isPremiumVersion){
        this.videoId = videoId;
        this.thumbnail = thumbnail;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.dateOfUpload = dateOfUpload;
        this.numberOfLikes = 0;
        this.numberOfViews = 0;
        this.isPremiumVersion = isPremiumVersion;
    }    

    //(Getters and Setters)
    //videoid
    public String getVideoId(){ 
        return videoId;
    }
    public void setVideoId(String newVideoId){
        videoId = newVideoId;
    }

    //thumbnail
    public ImageIcon getThumbnail(){
        return thumbnail;
    }
    public void setThumnail(ImageIcon newThumbnail){
        thumbnail = newThumbnail;
    }

    //name
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }
    
    //description
    public String getDescription(){
        return description;
    }
    public void setDescription(String newDescription){
        description = newDescription;
    }

    //duration
    public Integer getDuration(){
        return duration;
    }
    public void setDuration(Integer newDuration){
        duration = newDuration; }

    //upload date
    public LocalDate getDateOfUpload(){
        return dateOfUpload;
    }
    public void setDateOfUpload(LocalDate newDateOfUpload){
        dateOfUpload = newDateOfUpload;
    }

    //number of likes
    public Integer getNumberOfLikes(){
        return numberOfLikes;
    }
    public void setNumberOfLikes(Integer newNumberOfLikes){
        numberOfLikes = newNumberOfLikes;
    }

    //number of views
    public Integer getNumberOfViews(){
        return numberOfViews;
    }
    public void setNumberOfViews(Integer newNumberOfViews){
        numberOfViews = newNumberOfViews;
    }

    //is premium  
    public String getIsPremiumVersion(){
        return isPremiumVersion;
    }
    public void setIsPremiumVersion(String newIsPremiumVersion){
        isPremiumVersion = newIsPremiumVersion;
    }

    /**
     * Returns a string representation of the video's information.
     *
     * @return A string containing video information.
     */
    public String informationToString() {
        return  "Infromation about video: " + '\n' + '\n'+
                "Id:  " + this.videoId + '\n' +
                "Name:  " + this.name + '\n' +
                "Description: " + this.description + '\n' + '\n' +
                "Duration in seconds: " +  this.duration + '\n' +
                "Date of upload " + this.dateOfUpload + '\n' +
                "Number of likes: " + this.numberOfLikes + '\n' +
                "Number of views: " + this.numberOfViews + '\n' +
                "Status premium: " + this.isPremiumVersion + '\n';
    }
    
    /**
     * Returns a string representation of the video's information for the current view.
     *
     * @return A string containing video information for the current view.
     */
    @Override
    public String printInfoCurrView(){
        return "Infromation about video: " + '\n' + '\n'+
                "Name:  " + this.name + '\n' +
                "Status premium: " + this.isPremiumVersion + '\n';
    }

    /**
     * Returns a string representation of the video's information.
     *
     * @return A string containing video information.
     */
    @Override
    public String printInfo() {
        return "Video: "+ this.name + '\n';
    }

    /**
     * Returns the premium version status of the video.
     *
     * @return The premium version status.
     */
    @Override
    public String version() {
        return isPremiumVersion;
    }
    
    /**
     * Returns the duration of the video in seconds.
     *
     * @return The duration of the video.
     */
    @Override
    public int duration() {
        return duration;
    }

    /**
     * Returns the index or ID of the video.
     *
     * @return The video index or ID.
     */
    @Override
    public String index(){
        return videoId;
        }
    
}
 