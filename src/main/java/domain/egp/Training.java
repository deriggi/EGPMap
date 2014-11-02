/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.egp;

import java.util.ArrayList;

/**
 *
 * @author jDeRiggi
 */
public class Training {

    private String eventTitle;
    private String eventType;
    private String location;
    private String venue;
    private String eventId;
    private String status;
    private String startDate;
    private String endDate;
    private String activityHeading;
    private boolean isHimii;
    private String track;
    private ArrayList<String> images = null;
    private ArrayList<String> targetMinistries = null;
    private float latitude;
    private float longitude;
    private int male;
    private int female;
    private float percentSatisfied;
    public static final String himiiKey = "usaidmissionpriorityactivity";
    public static final String activityHeadingKey = "activityheading";
    public static final String trackKey = "track";
    public static final String eventTitleKey = "eventtitle";
    public static final String eventTypeKey = "eventtype";
    public static final String locationKey = "location";
    public static final String latitudeKey = "latitude";
    public static final String longitudeKey = "longitude";
    public static final String venueKey = "venue";
    public static final String eventIdKey = "eventid";
    public static final String statusKey = "status";
    public static final String femaleKey = "female";
    public static final String maleKey = "male";
    public static final String percentSatisfiedKey = "p_ofoverallsatisfaction";
    public static final String startDateKey = "startdate";
    public static final String endDateKey = "enddate";
    public static final String targetMinistriesKey = "targetministries";

    public void addImage(String image) {
        if (image == null) {
            return;
        }
        if (images == null) {
            images = new ArrayList<String>();
        }
        images.add(image);
    }

    public boolean isIsHimii() {
        return isHimii;
    }

    public void setIsHimii(boolean isHimii) {
        this.isHimii = isHimii;
    }

    public String getActivityHeading() {
        return activityHeading;
    }

    public void setActivityHeading(String activityHeading) {
        this.activityHeading = activityHeading;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public ArrayList<String> getTargetMinistries() {
        return targetMinistries;
    }

    public void setTargetMinistries(ArrayList<String> targetMinistries) {
        this.targetMinistries = targetMinistries;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getPercentSatisfied() {
        return percentSatisfied;
    }

    public void setPercentSatisfied(float percentSatisfied) {
        this.percentSatisfied = percentSatisfied;
    }
}
