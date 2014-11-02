/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.egp;

/**
 *
 * @author jDeRiggi
 */
public class EGPTechnicalActivityPhotoLibraryIndex {
    
    private String location;
    private String timeframe;
    private String specificLocation;
    private String renovationId;
    private String siteRenovationTitle;
    private String libraryLink;

    public static String locationKey = "location";
    public static String timeframeKey = "timeframe";
    public static String reovationidKey = "renovationid";
    public static String siteRenovationTitleKey = "siterenovationtitle";
    public static String libraryLinkKey = "librarylink";
   
    public String getLibraryLink() {
        return libraryLink;
    }

    public void setLibraryLink(String libraryLink) {
        this.libraryLink = libraryLink;
    }
    

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    public String getSpecificLocation() {
        return specificLocation;
    }

    public void setSpecificLocation(String specificLocation) {
        this.specificLocation = specificLocation;
    }

    public String getRenovationId() {
        return renovationId;
    }

    public void setRenovationId(String renovationId) {
        this.renovationId = renovationId;
    }

    public String getSiteRenovationTitle() {
        return siteRenovationTitle;
    }

    public void setSiteRenovationTitle(String siteRenovationTitle) {
        this.siteRenovationTitle = siteRenovationTitle;
    }
    
}
