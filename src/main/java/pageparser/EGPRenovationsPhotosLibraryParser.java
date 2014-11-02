/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pageparser;

import domain.egp.EGPTechnicalActivityPhotoLibraryIndex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.TAMISUtil;

/**
 *
 * @author jDeRiggi
 */
public class EGPRenovationsPhotosLibraryParser extends PageParser{
    private static final Logger logger = Logger.getLogger(EGPRenovationsPhotosLibraryParser.class.getName());
    
    @Override
    public ArrayList<HashMap<String, String>> parsePage() {
                Document doc = Jsoup.parse(getContent());
                 Elements tables = doc.select("table");
        
        Element dataTable = tables.get(2);
        Elements header = dataTable.select("th");
        Elements data = dataTable.select("tr");
        
        Iterator<Element> headerIterator = header.iterator();
        Iterator<Element> dataIterator = data.iterator();
        ArrayList<ArrayList<String>> allRows = new ArrayList<ArrayList<String>>();
        ArrayList<String> headerRow = new ArrayList<String>();
        
        // get all header data names and put in ordered array
        
        while (headerIterator.hasNext()){
            Element tdElement = headerIterator.next();
            headerRow.add(tdElement.text());
        }
        logger.log(Level.INFO, "size of the header {0} " , headerRow.size());
        
        
        int trCounter = 0;
        while (dataIterator.hasNext()){
            Element row = dataIterator.next();
            Elements tds = row.select("td");
            Iterator<Element> tdIterator = tds.iterator();
            
            ArrayList<String> rowData = new ArrayList<String>();
            
            int tdCounter = 0;
            String libraryLink= null;
            
            while (tdIterator.hasNext()){
                
                Element td = tdIterator.next();
                rowData.add(td.text());
                Elements links = td.select("a");
                if (links.size() > 0){
                    String link = links.get(0).attr("href");
                    if(link != null && link.length()> 0){
                        libraryLink = link;
                    }
                    
                }
                tdCounter++;
                
            }
            headerRow.add(EGPTechnicalActivityPhotoLibraryIndex.libraryLinkKey);
            rowData.add(libraryLink);
            
            if(rowData.size() > 0){
                allRows.add(new ArrayList(rowData));
            }
            

            trCounter++;
        } // done collecting all data
        
        logger.log(Level.INFO, "number of records in the top level photo library {0} " , allRows.size());
        return convertRows(allRows, headerRow);
    }
    
       
    private ArrayList<HashMap<String, String>> convertRows(ArrayList<ArrayList<String>> allRows, ArrayList<String> header) {
        ArrayList<HashMap<String, String>> properties = new ArrayList<HashMap<String, String>>();
        
        for (int i = 0; i < allRows.size(); i++) {
            
            HashMap<String, String> row = new HashMap<String,String>();
            
            for (int j = 0; j < allRows.get(i).size(); j++) {
                
                // value from this row
                String text = allRows.get(i).get(j);
                
                // get header of same index
                row.put(TAMISUtil.cleanProperty(header.get(j)), text);

            }
//            TAMISUtil.printMap(row);
            
            properties.add(row);
            
        }
        return properties;

    }

    
    
}
