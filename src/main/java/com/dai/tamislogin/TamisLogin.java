/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dai.tamislogin;

import converter.EGPTechnicalSitesConverter;
import converter.EGPTrainingConverter;
import converter.PhotoLibraryIndexConverter;
import domain.egp.EGPTechnicalActivityPhotoLibraryIndex;
import domain.egp.EGPTrainingPhotoLibraryIndex;
import domain.egp.TechnicalActivity;
import domain.egp.Training;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import pageparser.EGPDashboardPageParser;
import pageparser.EGPPhotoPageParser;
import pageparser.EGPRenovationSitesParser;
import pageparser.EGPRenovationsPhotosLibraryParser;
import pageparser.EGPTrainingPhotosLibraryParser;
import pageparser.EGPTrainingsPageParser;
import pageparser.FRPMandEParser;
import pageparser.PageParser;
import util.ImageProcessor;

/**
 *
 * @author jderiggi
 */
public class TamisLogin {

    private static final Logger logger = Logger.getLogger(TamisLogin.class.getName());
    public static final String TAMIS_AUTHENTICATION_URL = "https://tamis.dai.com/names.nsf?Login";
//    public static final String APPS1_ROOT = "https://apps1.daiglobal.net/";
    public static final String APPS1_ROOT = "https://apps1.dai.com/";
//    public static final String APPS1_AUTHENTICATION_URL = "https://apps1.daiglobal.net/names.nsf?Login";
    private static final String DASHBOARD_URL = "https://tamis.dai.com/EffectiveGovernanceTAMIS.nsf/Project%20Ref?OpenPage";
    private static final String INDICATOR_PROGRESS_SUMMARY = "https://tamis.dai.com/Projects/Jordan/JordanFRP_TAMIS.nsf/IndProgSummary?OpenView&Start=1&Count=30000";
    private static final String TRAINING_URL = "https://apps1.dai.com/Projects/Palestine/EffectiveGovernanceTAMIS.nsf/40821DEBCD167B8985257CD000756569?OpenView&count=3000";
    private static final String TRAINING_PHOTO_LIBRARY_URL = "https://apps1.dai.com/Projects/Palestine/WestBankEGP_PhotoLib.nsf/5E657085442C2DD1C2257B21003595BD";
    private static final String RENOVATION_SITES_URL = "https://apps1.dai.com/Projects/Palestine/EffectiveGovernanceTAMIS.nsf/158809a22008158a85257cdd005c901d?OpenView&count=3000";
    private static final String RENOVATION_SITE_PHOTO_LIBRARY = "https://apps1.dai.com/Projects/Palestine/WestBankEGP_PhotoLib.nsf/1BF7D89550CE4F4EC2257B210074C94D";
    private static final String FRP_TRAINING_URL = "https://tamis.dai.com/Projects/Jordan/JordanFRP_TAMIS.nsf/QTREvent_WebExport?OpenView&count=30000";
    
    public static void main(String[] args) {
//        try {
        // need to test client to make sure authentication happened correctly first
        // one option is to return the client with some type of status code, perhaps the http code

        long t0 = new Date().getTime();
        CloseableHttpClient TAMISClient = TamisLogin.authenticate(TAMIS_AUTHENTICATION_URL);
        long t1 = new Date().getTime();
        logger.log(Level.INFO, "loggin tin tamis.dai took {0} seconds", (t1 - t0) / 1000.0f);

//        CloseableHttpClient APPS1Client = TamisLogin.authenticate(APPS1_AUTHENTICATION_URL);
        long t2 = new Date().getTime();
        logger.log(Level.INFO, "loggin tin apps1.dai took {0} seconds", (t2 - t1) / 1000.0f);

//        HashMap<String, TechnicalActivity> taLookup = getEGPRenovationSites(APPS1Client);
//        populateTechnicalActivityObjects(taLookup, APPS1Client);
        getEGPTrainingSites(TAMISClient, TamisLogin.getEGPTrainingPage(TAMISClient));

        // =============================
//            ArrayList<HashMap<String, String>> sitesData = TamisLogin.getRenovationsSite(APPS1Client);
//            EGPTechnicalSitesConverter converter = new EGPTechnicalSitesConverter();
//
//            // create a lookup for the TAs so we can put the 
//            HashMap<String, TechnicalActivity> taLookup = new HashMap<String, TechnicalActivity>();
//            for (HashMap<String, String> row : sitesData) {
//                TechnicalActivity ta = converter.convertMapToActivity(row);
//                if (ta.getTamisId() != null && ta.getTamisId().length() > 1) {
//                    taLookup.put(ta.getTamisId(), ta);
//                }
//            }
        // ==============================


        // get the library index
//            ArrayList<HashMap<String, String>> libraryIndex = TamisLogin.getRenovationsPhotoLibrary(APPS1Client);
//            PhotoLibraryIndexConverter libraryIndexConverter = new PhotoLibraryIndexConverter();
//
//            for (HashMap<String, String> row : libraryIndex) {
//
//                // for each library index
//                EGPTechnicalActivityPhotoLibraryIndex pli = libraryIndexConverter.convertMapToPhotoLibraryIndex(row);
//                String renoID = pli.getRenovationId();
//                String timeframe = pli.getTimeframe();
//                System.out.println(pli.getRenovationId() + "  " + timeframe);
//
//                // get the photo library
//                ArrayList<HashMap<String, String>> photoMap = getPageData(APPS1Client, APPS1_ROOT + pli.getLibraryLink(), new EGPPhotoPageParser(APPS1_ROOT));
//                Collection<String> images = photoMap.get(0).values();
//
//                // shrink images
//                if (images.size() > 0) {
//                    for (Object image : images.toArray()) {
//                        String oneImage = (String) image;
//                        System.out.println("processing " + oneImage);
//                        String outpath = ImageProcessor.getImage(APPS1Client, oneImage, ImageProcessor.getShrunkenImageFolder());
//                        taLookup.get(renoID).getImagesFromStatus(timeframe).add(outpath);
//                    }
//                }
        // ================================================


//            }
        // update each TA with the set of image paths




//            getPageData(APPS1Client, "https://apps1.daiglobal.net/Projects/Palestine/WestBankEGP_PhotoLib.nsf/1bf7d89550ce4f4ec2257b210074c94d/ae708afca6e5b37fc2257d15002e59b4?OpenDocument", new EGPPhotoPageParser());

//            ArrayList<HashMap<String,String>> egpDash = TamisLogin.getDashboard(TAMISClient);
//            ArrayList<HashMap<String,String>> egpTraining = TamisLogin.getEGPTrainingPage(APPS1Client);
//            ArrayList<HashMap<String,String>> reno = TamisLogin.getRenovationsSite(APPS1Client);
//            logger.log(Level.INFO, "renovations size: {0}", reno.get(0).size());


        // most recent=================
//            ArrayList<HashMap<String,String>> indicators = TamisLogin.getFRPIndicators(TAMISClient);
//            
//            
//            ArrayList<HashMap<String,String>> training = TamisLogin.getFRPTraining(TAMISClient);
//            logger.log(Level.INFO, "frp indicators size: {0}", indicators.size());
//            logger.log(Level.INFO, "frp training size: {0}", training.size());
        // end most recent ==================


//            String sizeDash = Integer.toString(egpDash.get(0).size());
//            logger.log(Level.INFO, sizeDash);
//            
//            String sizeTraining = Integer.toString(egpTraining.get(0).size());
//            logger.log(Level.INFO, sizeTraining);





        //        Set<String> keys = egpDash.keySet();
        //        
        //        StringBuilder sb = new StringBuilder();
        //        String lineSep = System.getProperty("line.separator");
        //        sb.append(lineSep);
        //        for (String k : keys){
        //            sb.append(k);
        //            sb.append(", ");
        //            sb.append(egpDash.get(k));
        //            sb.append(lineSep);
        //        }

        //        logger.log(Level.INFO, "{0}", sb.toString());
//        } catch (Exception ex) {
//            Logger.getLogger(TamisLogin.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    /*
     *  convenience methods to specific pages
     */
    // FRP
    public static ArrayList<HashMap<String, String>> getFRPIndicators(CloseableHttpClient client) {
        return getPageData(client, INDICATOR_PROGRESS_SUMMARY, new FRPMandEParser());

    }

    public static ArrayList<HashMap<String, String>> getFRPTraining(CloseableHttpClient client) {
        return getPageData(client, FRP_TRAINING_URL, new FRPMandEParser());
    }

    /**
     * EGP
     *
     * *
     */
    public static ArrayList<HashMap<String, String>> getDashboard(CloseableHttpClient client) {
        return getPageData(client, DASHBOARD_URL, new EGPDashboardPageParser());
    }

    public static ArrayList<HashMap<String, String>> getEGPTrainingPage(CloseableHttpClient client) {
        return getPageData(client, TRAINING_URL, new EGPTrainingsPageParser());
    }

    public static ArrayList<HashMap<String, String>> getRenovationsSite(CloseableHttpClient client) {
        return getPageData(client, RENOVATION_SITES_URL, new EGPRenovationSitesParser());
    }

    public static ArrayList<HashMap<String, String>> getRenovationsPhotoLibrary(CloseableHttpClient client) {
        return getPageData(client, RENOVATION_SITE_PHOTO_LIBRARY, new EGPRenovationsPhotosLibraryParser());
    }

    public static ArrayList<HashMap<String, String>> getTrainingPhotoLibrary(CloseableHttpClient client) {
        return getPageData(client, TRAINING_PHOTO_LIBRARY_URL, new EGPTrainingPhotosLibraryParser());
    }

    public static HashMap<String, Training> getEGPTrainingSites(CloseableHttpClient httpClient, ArrayList<HashMap<String, String>> trainingData) {

//        ArrayList<HashMap<String, String>> trainingData = TamisLogin.getEGPTrainingPage(httpClient);
        // convert trainings to to objects
        // convert to id-object lookup
        HashMap<String, Training> trainingLookup = new EGPTrainingConverter().makeTrainingMap(Training.eventIdKey, trainingData);

        // get library index of training photos
        ArrayList<HashMap<String, String>> trainingPhotoLibrary = TamisLogin.getTrainingPhotoLibrary(httpClient);

        // populate trainings
        // == for each libraryindex, get training and add photos
        PhotoLibraryIndexConverter indexConverter = new PhotoLibraryIndexConverter();
        for (HashMap<String, String> row : trainingPhotoLibrary) {
            EGPTrainingPhotoLibraryIndex photoIndex = indexConverter.convertMapToTrainingLibraryIndex(row);
            System.out.println(photoIndex.getEventId() + " " + photoIndex.getPhotoLibraryLink());
            ArrayList<HashMap<String, String>> photoMap = getPageData(httpClient, APPS1_ROOT + photoIndex.getPhotoLibraryLink(), new EGPPhotoPageParser(APPS1_ROOT));
            Collection<String> images = photoMap.get(0).values();

            Training t = null;
            if (trainingLookup.containsKey(photoIndex.getEventId())) {
                t = trainingLookup.get(photoIndex.getEventId());
            }

            // shrink images
            if (images.size() > 0) {
                for (Object image : images.toArray()) {
                    String oneImage = (String) image;

                    // get the corresponding training and populate it with this image
                    if (t != null) {
                        String outpath = ImageProcessor.getImage(httpClient, oneImage, ImageProcessor.getShrunkenImageFolder());
                        
                        t.addImage(outpath);

                    }
                }
            }

        }
        return trainingLookup;
    }

    public static HashMap<String, TechnicalActivity> getEGPRenovationSites(CloseableHttpClient httpClient) {
        ArrayList<HashMap<String, String>> sitesData = TamisLogin.getRenovationsSite(httpClient);
        EGPTechnicalSitesConverter converter = new EGPTechnicalSitesConverter();

        // create a lookup for the TAs so we can put the 
        HashMap<String, TechnicalActivity> taLookup = new HashMap<String, TechnicalActivity>();
        ArrayList<Float> longitude = new ArrayList<Float>();
        for (HashMap<String, String> row : sitesData) {
            TechnicalActivity ta = converter.convertMapToActivity(row);
            float lon = ta.getLongitude();
            if (longitude.contains(lon)) {
                ta.setLongitude(lon + 0.0001f * (float) (Math.random() * 2));
                ta.setLatitude(ta.getLatitude() + 0.0001f * (float) (Math.random() * 2));
            }
            longitude.add(lon);
            if (ta.getTamisId() != null && ta.getTamisId().length() > 1) {
                taLookup.put(ta.getTamisId(), ta);
            }
        }
        return taLookup;
    }

//    public static void popuplateEGPTrainingObjects(HashMap<String, TechnicalActivity> taLookup) {
//    }

    public static void populateTechnicalActivityObjects(HashMap<String, TechnicalActivity> taLookup, CloseableHttpClient httpClient) {
        ArrayList<HashMap<String, String>> libraryIndex = TamisLogin.getRenovationsPhotoLibrary(httpClient);
        PhotoLibraryIndexConverter libraryIndexConverter = new PhotoLibraryIndexConverter();

        for (HashMap<String, String> row : libraryIndex) {

            // for each library index
            EGPTechnicalActivityPhotoLibraryIndex pli = libraryIndexConverter.convertMapToPhotoLibraryIndex(row);
            String renoID = pli.getRenovationId();
            String timeframe = pli.getTimeframe();
            System.out.println(pli.getRenovationId() + ", " + ", " + timeframe);

            if (taLookup.containsKey(pli.getRenovationId())) {
                System.out.println(taLookup.get(pli.getRenovationId()).getName());
            }

            // get the photo library
            ArrayList<HashMap<String, String>> photoMap = getPageData(httpClient, APPS1_ROOT + pli.getLibraryLink(), new EGPPhotoPageParser(APPS1_ROOT));
            Collection<String> images = photoMap.get(0).values();

            // shrink images
            if (images.size() > 0) {
                for (Object image : images.toArray()) {
                    String oneImage = (String) image;
                    System.out.println("processing " + oneImage);
                    String outpath = ImageProcessor.getImage(httpClient, oneImage, ImageProcessor.getShrunkenImageFolder());
                    if (taLookup.containsKey(renoID)) {
                        taLookup.get(renoID).getImagesFromStatus(timeframe).add(outpath);
                    } else {
                        System.out.println("ta lookup does not have " + renoID + " is of size  " + taLookup.size());
                    }
                }
            }

        }
    }

    public static CloseableHttpClient authenticate(String authURL) {
        HttpPost httppost;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("Password", "Moshi83#"));
        formparams.add(new BasicNameValuePair("Username", "jderiggi"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httppost = new HttpPost(authURL);
        httppost.setEntity(entity);
        CloseableHttpResponse response2 = null;

        try {

            response2 = httpclient.execute(httppost);

            StatusLine statusLine = response2.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            logger.log(Level.INFO, "status code: {0}", statusCode);
            HttpEntity entity2 = response2.getEntity();
            String location = response2.getFirstHeader("Location").getValue();
            logger.log(Level.INFO, "redirect location : {0}", location);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (response2 != null) {
                    response2.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(TamisLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return httpclient;
    }

    /**
     * The generalized page parser method, used to scrape any page with a custom
     * parser
     */
    private static ArrayList<HashMap<String, String>> getPageData(CloseableHttpClient client, String url, PageParser pageParser) {
        CloseableHttpResponse dashboardResponse = null;
        try {

            HttpGet egpGet = new HttpGet(url);
            dashboardResponse = client.execute(egpGet);
            String statusLine = dashboardResponse.getStatusLine().toString();
            String response = EntityUtils.toString(dashboardResponse.getEntity());
            pageParser.setup(response);
//            logger.log(Level.INFO,response);


        } catch (IOException ioe) {

            ioe.printStackTrace();

        } finally {
            try {

                if (dashboardResponse != null) {
                    dashboardResponse.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(TamisLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (pageParser != null) {

            return pageParser.parsePage();
        } else {
            logger.warning("returning empty shell because page parser is null");
            return new ArrayList<HashMap<String, String>>(0);
        }


    }

    @Deprecated
    public static HashMap<String, String> getDashboardDeprecated() throws Exception {
        HttpPost httppost;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("Password", "Moshi83#"));
            formparams.add(new BasicNameValuePair("Username", "jderiggi"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            httppost = new HttpPost("https://tamis.dai.com/names.nsf?Login");
            httppost.setEntity(entity);
            CloseableHttpResponse response2 = httpclient.execute(httppost);
            CloseableHttpResponse response3 = null;
            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                String location = response2.getFirstHeader("Location").getValue();
                System.out.println("redirect: " + location);
                HttpGet egpGet = new HttpGet(DASHBOARD_URL);
                System.out.println(EntityUtils.toString(entity2));

                response3 = httpclient.execute(egpGet);

                String statusLine = response3.getStatusLine().toString();
                String response = EntityUtils.toString(response3.getEntity());
                System.out.println(response);
//                EGPDashboardPageParser pageParser = new EGPDashboardPageParser(response);

                return new HashMap<String, String>(0);

            } finally {

                if (response2 != null) {
                    response2.close();
                }

                if (response3 != null) {
                    response3.close();
                }

            }
        } finally {
        }
    }
}
