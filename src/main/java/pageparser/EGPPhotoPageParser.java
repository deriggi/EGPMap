/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pageparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author jDeRiggi
 */
public class EGPPhotoPageParser extends PageParser {
    
    private final String HREF= "href";
    private final String imageRoot;
    
    public EGPPhotoPageParser(String imageRoot){
        this.imageRoot = imageRoot;
    }
    
    @Override
    public ArrayList<HashMap<String, String>> parsePage() {
        
        Document doc = Jsoup.parse(getContent());
        Elements jpg = doc.select("a");
        Iterator<Element> images = jpg.iterator();
        
        ArrayList<HashMap<String, String>> demImages = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> allImages = new HashMap<String,String>();
        
        int counter = 0;
        StringBuilder sb = null;
        StringBuilder pathBuilder = null;
        while(images.hasNext()){
            sb = new StringBuilder();
            pathBuilder = new StringBuilder();
            String key = sb.append("img_").append(counter++).toString();
            
            Element image = images.next();
            
            allImages.put(key, pathBuilder.append(imageRoot).append(image.attr(HREF)).toString() );
            
//            System.out.println(image.text());
//            System.out.println(image.attr("href"));

        }
        demImages.add(allImages);
        return demImages;
        
    }

    
}
