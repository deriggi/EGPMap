/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import domain.egp.EGPTechnicalActivityPhotoLibraryIndex;
import domain.egp.EGPTrainingPhotoLibraryIndex;
import java.util.HashMap;
import util.TAMISUtil;

/**
 * returns a list of image URLS given an http thing
 * @author jDeRiggi
 */

public class PhotoLibraryIndexConverter {
    
    public EGPTrainingPhotoLibraryIndex convertMapToTrainingLibraryIndex(HashMap<String,String> libraryIndexData){
        if(libraryIndexData== null){
            return null;
        }
        
        EGPTrainingPhotoLibraryIndex trainingPhotoIndex = new EGPTrainingPhotoLibraryIndex();
        
        if(libraryIndexData.containsKey(EGPTrainingPhotoLibraryIndex.titleKey)){
            trainingPhotoIndex.setTitle(libraryIndexData.get(EGPTrainingPhotoLibraryIndex.titleKey));
        }
        
        if(libraryIndexData.containsKey(EGPTrainingPhotoLibraryIndex.eventIdKey)){
            trainingPhotoIndex.setEventId(libraryIndexData.get(EGPTrainingPhotoLibraryIndex.eventIdKey));
        }
        
        if(libraryIndexData.containsKey(EGPTrainingPhotoLibraryIndex.eventTypeKey)){
            trainingPhotoIndex.setEventType(libraryIndexData.get(EGPTrainingPhotoLibraryIndex.eventTypeKey));
        }
        
        if(libraryIndexData.containsKey(EGPTrainingPhotoLibraryIndex.locationKey)){
            trainingPhotoIndex.setLocation(libraryIndexData.get(EGPTrainingPhotoLibraryIndex.locationKey));
        }
        
        if(libraryIndexData.containsKey(EGPTrainingPhotoLibraryIndex.libraryLinkKey)){
            trainingPhotoIndex.setPhotoLibraryLink(libraryIndexData.get(EGPTrainingPhotoLibraryIndex.libraryLinkKey));
        }
        
        
        return trainingPhotoIndex;
        
        
        
    }
    
    public EGPTechnicalActivityPhotoLibraryIndex convertMapToPhotoLibraryIndex(HashMap<String,String> libraryIndexData){
        if(libraryIndexData == null){
            return null;
        }
        
        EGPTechnicalActivityPhotoLibraryIndex pli = new EGPTechnicalActivityPhotoLibraryIndex();
        
        if(TAMISUtil.hasIt(libraryIndexData, EGPTechnicalActivityPhotoLibraryIndex.reovationidKey)){
            pli.setRenovationId(libraryIndexData.get(EGPTechnicalActivityPhotoLibraryIndex.reovationidKey));
        }
        
        if(TAMISUtil.hasIt(libraryIndexData, EGPTechnicalActivityPhotoLibraryIndex.siteRenovationTitleKey)){
            pli.setSiteRenovationTitle(libraryIndexData.get(EGPTechnicalActivityPhotoLibraryIndex.siteRenovationTitleKey));
        }
        
        if(TAMISUtil.hasIt(libraryIndexData, EGPTechnicalActivityPhotoLibraryIndex.timeframeKey)){
            pli.setTimeframe(libraryIndexData.get(EGPTechnicalActivityPhotoLibraryIndex.timeframeKey));
        }
        
        if(TAMISUtil.hasIt(libraryIndexData, EGPTechnicalActivityPhotoLibraryIndex.libraryLinkKey)){
            pli.setLibraryLink(libraryIndexData.get(EGPTechnicalActivityPhotoLibraryIndex.libraryLinkKey));
        }
        
        
        
        return pli;
    }
    
    
    
}
