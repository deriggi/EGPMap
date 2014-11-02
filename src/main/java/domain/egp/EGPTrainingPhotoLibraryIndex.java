/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.egp;

/**
 *
 * @author jDeRiggi
 */
public class EGPTrainingPhotoLibraryIndex {
    
    private String title;
    private String location;
    private String eventType;
    private String eventId;
    private String comments;
    private String photoLibraryLink;
    
    public static final String titleKey = "title";
    public static final String locationKey = "location";
    public static final String eventTypeKey = "eventtype";
    public static final String eventIdKey = "eventid";
    public static final String commentsKey = "comments";
    public static final String libraryLinkKey = "librarylink";

    public String getPhotoLibraryLink() {
        return photoLibraryLink;
    }

    public void setPhotoLibraryLink(String photoLibraryLink) {
        this.photoLibraryLink = photoLibraryLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
    
}
