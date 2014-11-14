/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import domain.egp.Training;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.TAMISUtil;

/**
 *
 * @author jDeRiggi
 */
public class EGPTrainingConverter {

    private static Logger log = Logger.getLogger(EGPTrainingConverter.class.getName());

    public HashMap<String, Training> makeTrainingMap(String key, ArrayList<HashMap<String, String>> all) {
        HashMap<String, Training> map = new HashMap<String, Training>();
        ArrayList<Float> longitude = new ArrayList<Float>();

        for (HashMap<String, String> trainingData : all) {
            Training t = convert(trainingData);
            if (t != null && t.getEventType() != null && !t.getEventType().equals("Training")) {
                System.out.println("not training so skipping " + t.getEventType());
                continue;
            }
            System.out.println("keeping this one " + t.getEventType());

            float lon = t.getLongitude();
            if (longitude.contains(lon)) {
                t.setLongitude(lon + 0.0001f * (float) (Math.random() * 2));
                t.setLatitude(t.getLatitude() + 0.0001f * (float) (Math.random() * 2));
            }
            longitude.add(lon);

            map.put(t.getEventId(), t);
        }
        log.log(Level.INFO, "created training set size of {0}", map.size());
        return map;
    }

    public Training convert(HashMap<String, String> map) {
        if (map == null) {
            return null;
        }
        Training t = new Training();

        if (map.containsKey(Training.eventTitleKey)) {
            t.setEventTitle(map.get(Training.eventTitleKey));
        }

        if (map.containsKey(Training.himiiKey)) {
            String himiival = map.get(Training.himiiKey);
            if(himiival != null){
                himiival = himiival.trim();
            }
            
            if (himiival != null && himiival.length() > 0) {
                t.setIsHimii(true);
            } else {
                t.setIsHimii(false);
            }
        }
        
         if(map.containsKey(Training.finalCostKey)){
            t.setFinalCost(map.get(Training.finalCostKey));
        }
        if (map.containsKey(Training.eventIdKey)) {
            t.setEventId(map.get(Training.eventIdKey));
        }

        if (map.containsKey(Training.eventTypeKey)) {
            t.setEventType(map.get(Training.eventTypeKey));
        }

        if (map.containsKey(Training.startDateKey)) {
            t.setStartDate(map.get(Training.startDateKey));
        }

        if (map.containsKey(Training.endDateKey)) {
            t.setEndDate(map.get(Training.endDateKey));
        }
        if (map.containsKey(Training.trackKey)) {
            t.setTrack(map.get(Training.trackKey));
        }


        if (map.containsKey(Training.activityHeadingKey)) {
            t.setActivityHeading(map.get(Training.activityHeadingKey));
        }


        if (map.containsKey(Training.targetMinistriesKey)) {
            String ministryString = map.get(Training.targetMinistriesKey);
            String[] minis = ministryString.split(",");
            if (t.getTargetMinistries() == null) {
                t.setTargetMinistries(new ArrayList<String>());
            }
            for (String m : minis) {
                t.getTargetMinistries().add(m.trim());
            }

        }

        if (map.containsKey(Training.latitudeKey)) {
            float lat = TAMISUtil.cleanAndParseFloat(map.get(Training.latitudeKey));
            t.setLatitude(lat);
        }

        if (map.containsKey(Training.longitudeKey)) {
            float lon = TAMISUtil.cleanAndParseFloat(map.get(Training.longitudeKey));
            t.setLongitude(lon);
        }

        if (map.containsKey(Training.percentSatisfiedKey)) {
            float ps = TAMISUtil.cleanAndParseFloat(map.get(Training.percentSatisfiedKey));
            t.setPercentSatisfied(ps);
        }

        if (map.containsKey(Training.statusKey)) {
            t.setStatus(map.get(Training.statusKey));
        }

        if (map.containsKey(Training.maleKey)) {
            int male = TAMISUtil.cleanAndParseInt(map.get(Training.maleKey));
            t.setMale(male);
        }

        if (map.containsKey(Training.femaleKey)) {
            int female = TAMISUtil.cleanAndParseInt(map.get(Training.femaleKey));
            t.setFemale(female);
        }

        if (map.containsKey(Training.locationKey)) {
            String location = map.get(Training.locationKey);
            t.setLocation(location);
        }

        return t;





    }
}
