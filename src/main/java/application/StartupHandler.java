/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import cache.ProjectCache;
import com.dai.tamislogin.TamisLogin;
import static com.dai.tamislogin.TamisLogin.getEGPIndicators;
import domain.egp.IndicatorProgress;
import domain.egp.TechnicalActivity;
import domain.egp.Training;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.http.impl.client.CloseableHttpClient;
import util.ImageProcessor;

/**
 * Web application lifecycle listener.
 *
 * @author jderiggi
 */
@WebListener()
public class StartupHandler implements ServletContextListener {

    private static Logger log = Logger.getLogger(StartupHandler.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ImageProcessor.setShrunkenImageFolder(sce.getServletContext().getRealPath("\\shrunkenimages\\"));

//        CloseableHttpClient apps1Client = TamisLogin.authenticate(TamisLogin.APPS1_AUTHENTICATION_URL);
        CloseableHttpClient tamisClient = TamisLogin.authenticate(TamisLogin.TAMIS_AUTHENTICATION_URL);
        try {

            loadFRP(null, tamisClient);


        } catch (Exception ex) {
            Logger.getLogger(StartupHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            loadEGP(tamisClient);
        } catch (Exception ex) {
            Logger.getLogger(StartupHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<HashMap<String, String>> cleanTrainingData(ArrayList<HashMap<String, String>> trainingData) {
        String[] baddys = {"goals", "workplanobjective", "targetgroup", "additionalcomments", "trainer", "venue", "eventtitle", "topic", "trainingevalarea7average", "workshopevalarea8average"};
        for (HashMap<String, String> map : trainingData) {
            for (String baddy : baddys) {
                if (map.containsKey(baddy)) {
                    map.remove(baddy);
                }
            }
        }
        return trainingData;
    }

    private void loadFRP(CloseableHttpClient apps1Client, CloseableHttpClient tamisClient) {
        ArrayList<HashMap<String, String>> mande = TamisLogin.getFRPIndicators(tamisClient);
        ArrayList<HashMap<String, String>> training = TamisLogin.getFRPTraining(tamisClient);
        training = cleanTrainingData(training);

        ProjectCache.get().addDataset("frp", "mande", mande);
        ProjectCache.get().addDataset("frp", "training", training);

    }

    private void loadEGP(CloseableHttpClient apps1Client) {
        // I guess change this so it is all training into one thang, yah feel me?
        ArrayList<HashMap<String, String>> training = TamisLogin.getEGPTrainingPage(apps1Client);
        if (training.size() > 0) {
            ProjectCache.get().addDataset("egp", "training", training);
        }

        // TODO load egp indicators, then convert all to IndicatorProgress objects, send to front end
        ArrayList<HashMap<String, String>> data = getEGPIndicators(apps1Client);
        ArrayList<IndicatorProgress> ips = new ArrayList<IndicatorProgress>();
        for(HashMap row : data){
            IndicatorProgress ip = new IndicatorProgress();
            ip.populate(row);
            ips.add(ip);
        }
        ProjectCache.get().setIndicatorProgress(ips);
        
        
        HashMap<String, TechnicalActivity> taLookup = TamisLogin.getEGPRenovationSites(apps1Client);
        TamisLogin.populateTechnicalActivityObjects(taLookup, apps1Client);
        ProjectCache.get().setTechnicalActivities(taLookup);

        HashMap<String, Training> trainingFull = TamisLogin.getEGPTrainingSites(apps1Client, training);
        ProjectCache.get().setTraining(trainingFull);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
