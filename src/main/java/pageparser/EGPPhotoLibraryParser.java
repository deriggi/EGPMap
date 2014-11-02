/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pageparser;

import domain.egp.Training;
import java.util.HashMap;

/**
 *
 * @author jderiggi
 */
public class EGPPhotoLibraryParser {

    public Training convertMapToPhotoLibraryIndex(HashMap<String, String> map) {
    
        if(map == null){
            return null;
        }
        
        Training t = new Training();
        if(map.containsKey(Training.eventTitleKey)){
            t.setEventTitle(map.get(Training.eventTitleKey));
        }
        
        return t;
    }
}
