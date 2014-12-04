/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.egp;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jDeRiggi
 */
public class TechnicalActivity {

    private static final Logger log = Logger.getLogger(TechnicalActivity.class.getName());
    private String name;
    private String tamisId;
    private String goal;
    private ArrayList<String> targetMinistries;
    private String location;
    private String startDate;
    private String endDate;
    private String status;
    private String servicesExpanded;
    private String impact;
    private String committeeTitle;

    
    
    private String track;
    private String activityHeading;
    private boolean isHimii;
    private String finalCost;
    private ArrayList<String> beforeImages = null;
    private ArrayList<String> afterImages = null;
    private ArrayList<String> duringImages = null;
    private float percentComplete;
    private float latitude;
    private float longitude;
    
    public static final String committeeTitleKey = "committeetitletopic";
    public static final String servicesExpandedKey = "servicesexpandedorimproved";
    public static final String impactKey = "impact";
    public static final String finalCostKey = "finalcost";
    public static final String himiiKey = "usaidmissionpriorityactivity";
    public static final String nameKey = "nameofsitetitle";
    public static final String longitudeKey = "longitude";
    public static final String latitudeKey = "latitude";
    public static final String percentCompleteKey = "percentofoverallactivitycomplete";
    public static final String goalKey = "goal";
    public static final String cityKey = "city";
    public static final String ministryKey = "ministry";
    public static final String siteIdKey = "siterenovationidn_";
    public static final String specificLocationKey = "specificlocation";
    public static final String startDateKey = "estimatedperiodofperformancestartdate";
    public static final String endDateKey = "estimatedperiodofperformanceenddate";
    public static final String statusKey = "status";
    public static final String trackKey = "track";
    public static final String activityHeadingKey = "activityheading";

    public float getLatitude() {
        return latitude;
    }

    public ArrayList<String> getImagesFromStatus(String status) {
        if (status == null) {
            return null;
        }
        status = status.trim().toLowerCase();
        if (status.equals("before")) {
            return getBeforeImages();
        }
        if (status.equals("after")) {
            return getAfterImages();
        }
        if (status.equals("during")) {
            return getDuringImages();
        }
        log.log(Level.WARNING, "did not find a image library for timeframe {0} ", status);
        return null;
    }

    public String getCommitteeTitle() {
        return committeeTitle;
    }

    public void setCommitteeTitle(String committeeTitle) {
        this.committeeTitle = committeeTitle;
    }
    
     public String getServicesExpanded() {
        return servicesExpanded;
    }

    public void setServicesExpanded(String servicesExpanded) {
        this.servicesExpanded = servicesExpanded;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }
    
    
    public void setTargetMinistries(ArrayList<String> targetMinistries) {
        this.targetMinistries = targetMinistries;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getActivityHeading() {
        return activityHeading;
    }

    public void setActivityHeading(String activityHeading) {
        this.activityHeading = activityHeading;
    }

    public String getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(String finalCost) {
        this.finalCost = finalCost;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public ArrayList<String> getTargetMinistries() {
        return targetMinistries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTamisId() {
        return tamisId;
    }

    public void setTamisId(String tamisId) {
        this.tamisId = tamisId;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> makeIfNull(ArrayList<String> images) {
        if (images == null) {
            return new ArrayList<String>();
        }
        return images;
    }

    public ArrayList<String> getBeforeImages() {
        beforeImages = makeIfNull(beforeImages);

        return beforeImages;
    }

    public void setBeforeImages(ArrayList<String> beforeImages) {
        this.beforeImages = beforeImages;
    }

    public ArrayList<String> getAfterImages() {
        afterImages = makeIfNull(afterImages);
        return afterImages;
    }

    public void setAfterImages(ArrayList<String> afterImages) {

        this.afterImages = afterImages;
    }

    public ArrayList<String> getDuringImages() {
        duringImages = makeIfNull(duringImages);

        return duringImages;
    }

    public void setDuringImages(ArrayList<String> duringImages) {
        this.duringImages = duringImages;
    }

    public float getPercentComplete() {
        return percentComplete;
    }

    public void setPercentComplete(float percentComplete) {
        this.percentComplete = percentComplete;
    }
    
    public boolean isIsHimii() {
        return isHimii;
    }

    public void setIsHimii(boolean isHimii) {
        this.isHimii = isHimii;
    }
}
