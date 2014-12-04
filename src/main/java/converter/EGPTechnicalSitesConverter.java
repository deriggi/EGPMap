/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import domain.egp.TechnicalActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.TAMISUtil;

/**
 *
 * @author jDeRiggi
 */
public class EGPTechnicalSitesConverter {

    private Pattern patty = Pattern.compile("\\[a-zA-Z]{1,}");

    public TechnicalActivity convertMapToActivity(HashMap<String, String> map) {
        if (map == null) {
            return null;
        }

        TechnicalActivity ta = new TechnicalActivity();

        if(map.containsKey(TechnicalActivity.himiiKey)){
            String himiival = map.get(TechnicalActivity.himiiKey);
            if(himiival != null){
                himiival = himiival.trim();
            }
            
            if(himiival != null && himiival.length() > 0){
                ta.setIsHimii(true);
            }else{
                ta.setIsHimii(false);
            }
            
        }
        
        if(map.containsKey(TechnicalActivity.finalCostKey)){
            ta.setFinalCost(map.get(TechnicalActivity.finalCostKey));
        }
        if(map.containsKey(TechnicalActivity.trackKey)){
            ta.setTrack(map.get(TechnicalActivity.trackKey));
        }
        
        if(map.containsKey(TechnicalActivity.activityHeadingKey)){
            
            ta.setActivityHeading(map.get(TechnicalActivity.activityHeadingKey));
        }
        if (TAMISUtil.hasIt(map, TechnicalActivity.siteIdKey)) {
            ta.setTamisId(map.get(TechnicalActivity.siteIdKey));
        }

        if (TAMISUtil.hasIt(map, TechnicalActivity.nameKey)) {
            ta.setName(map.get(TechnicalActivity.nameKey));
        }

        if (TAMISUtil.hasIt(map, TechnicalActivity.ministryKey)) {
            String[] minisList = map.get(TechnicalActivity.ministryKey).split(",");
            if(ta.getTargetMinistries() == null){
                ta.setTargetMinistries(new ArrayList<String>());
            }
            for(String m : minisList){
                ta.getTargetMinistries().add(m.trim());
            }
            
        }

        if (TAMISUtil.hasIt(map, TechnicalActivity.goalKey)) {
            ta.setGoal(map.get(TechnicalActivity.goalKey));
        }

        if (TAMISUtil.hasIt(map, TechnicalActivity.statusKey)) {
            ta.setStatus(map.get(TechnicalActivity.statusKey));
//            String stat = ta.getStatus();
//            if (stat != null) {
//                Matcher matcher = patty.matcher(stat);
//                if (matcher.find()) {
//                    ta.setStatus(matcher.group());
//
//                }
//            }

        }


        if (TAMISUtil.hasIt(map, TechnicalActivity.latitudeKey)) {
            float lat = TAMISUtil.cleanAndParseFloat(map.get(TechnicalActivity.latitudeKey));
            ta.setLatitude(lat);
        }

        if (TAMISUtil.hasIt(map, TechnicalActivity.longitudeKey)) {
            float lon = TAMISUtil.cleanAndParseFloat(map.get(TechnicalActivity.longitudeKey));
            ta.setLongitude(lon);
        }

        if (TAMISUtil.hasIt(map, TechnicalActivity.percentCompleteKey)) {
            float pc = TAMISUtil.cleanAndParseFloat((map.get(TechnicalActivity.percentCompleteKey)));
            ta.setPercentComplete(pc);
        }

        if (TAMISUtil.hasIt(map, TechnicalActivity.startDateKey)) {
            String startDate = ((map.get(TechnicalActivity.startDateKey)));
            ta.setStartDate(startDate);
        }

        if (TAMISUtil.hasIt(map, TechnicalActivity.endDateKey)) {
            String endDate = ((map.get(TechnicalActivity.endDateKey)));
            ta.setEndDate(endDate);
        }



        if (TAMISUtil.hasIt(map, TechnicalActivity.specificLocationKey)) {
            ta.setLocation(map.get(TechnicalActivity.specificLocationKey));

        }
        
        if (TAMISUtil.hasIt(map, TechnicalActivity.impactKey)) {
            ta.setImpact(map.get(TechnicalActivity.impactKey));
        }
        
        if (TAMISUtil.hasIt(map, TechnicalActivity.committeeTitleKey)) {
            ta.setCommitteeTitle(map.get(TechnicalActivity.committeeTitleKey));
        }
        
        if (TAMISUtil.hasIt(map, TechnicalActivity.servicesExpandedKey)) {
            ta.setServicesExpanded(map.get(TechnicalActivity.servicesExpandedKey));
        }
        
        
        return ta;
    }
}
