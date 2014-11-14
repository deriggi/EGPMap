/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import domain.egp.IndicatorProgress;
import domain.egp.TechnicalActivity;
import domain.egp.Training;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jderiggi
 */
public class ProjectCache {
    private static ProjectCache cache = new ProjectCache();
    
    // projkey, datasetkey, --> hashtable of properties...could probably replace the hash with an object
    // projkey, datasetkey, --> list of hashtables of properties...could probably replace the hash with an object
    private HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> projectCache = new HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>>();
    
    private HashMap<String, TechnicalActivity> technicalActivities = new HashMap<String,TechnicalActivity>();
    private HashMap<String, Training> training = new HashMap<String, Training>();
    private ArrayList<IndicatorProgress> ips = new ArrayList<IndicatorProgress>();
    
    
    public static ProjectCache get(){
        return cache;
    }

    public HashMap<String, Training> getTraining() {
        return training;
    }

    public void setIndicatorProgress(ArrayList<IndicatorProgress> ip){
        this.ips  =ip;
    }
    public ArrayList<IndicatorProgress> getIps(){
        return ips;
    }
    
    public void setTraining(HashMap<String, Training> training) {
        this.training = training;
    }
    
    
    public void setTechnicalActivities(HashMap<String, TechnicalActivity> map){
        this.technicalActivities = map;
    }
    
    public HashMap<String,TechnicalActivity> getTechnicalActivities(){
        return technicalActivities;
    }
    
    
    public void addDataset(String project, String datasetkey, ArrayList<HashMap<String,String>> data){
        if( !projectCache.containsKey(project)){
            projectCache.put(project, new HashMap<String, ArrayList<HashMap<String, String>>>() );
        }
        
        
        projectCache.get(project).put(datasetkey,data);
        
    }
    
    public ArrayList<HashMap<String, String>> getData(String project, String setkey){
        if( !projectCache.containsKey(project)){
            return new ArrayList<HashMap<String,String>>(0);
        }
        else if(!projectCache.get(project).containsKey(setkey)){
            return new ArrayList<HashMap<String,String>>(0);
        }
        
        return projectCache.get(project).get(setkey);
        
    }
    
    
    
    
    
}
