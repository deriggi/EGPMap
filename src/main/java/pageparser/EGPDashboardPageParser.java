/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pageparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.TAMISUtil;

/**
 *
 * @author jderiggi
 */
public class EGPDashboardPageParser extends PageParser {

    public EGPDashboardPageParser(String content) {
        super(content);
    }
    public EGPDashboardPageParser(){
        
    }

    @Override
    public ArrayList<HashMap<String, String>> parsePage() {
        Document doc = Jsoup.parse(getContent());
        Elements rows = doc.select("table tr");
        
        Iterator<Element> rowiterator = rows.iterator();

        ArrayList<ArrayList<String>> allRows = new ArrayList<ArrayList<String>>();
        while (rowiterator.hasNext()) {

            ArrayList<String> rowData = new ArrayList<String>();

            Element row = rowiterator.next();
            Elements tds = row.select("td");
            Iterator<Element> tditerator = tds.iterator();

            while (tditerator.hasNext()) {

                Element td = tditerator.next();
                String text = td.text();
                rowData.add(text);

            } // done with this row of tds

            if (rowHasStuff(rowData)) {
                allRows.add(new ArrayList(rowData));
               
            }


        } //  done with all rows

        
        return convertRows(allRows);

        // push allRows into a hash on even odds
    }

    private boolean rowHasStuff(ArrayList<String> row) {
        if (row != null && row.size() > 0) {
            for (String s : row) {
                if (s.trim().length() != 0) {
                    return true;
                }
            }
        }

        return false;
    }

//    private HashMap<String, String> convertRows(ArrayList<ArrayList<String>> allRows){
//        
//        HashMap<String, String> properties = new HashMap<String, String>();     
//        
//        for (int i = 0; i < allRows.size(); i++){
//            
//            for(int j = 0; j < allRows.get(i).size(); j++ ){
//                String text = allRows.get(i).get(j);
//                
//                if(i % 2 == 0){
//                    // props
//                    properties.put(cleanProperty(text), null);
//                }else {
//                    // values
//                   
//                    properties.put(allRows.get(i-1).get(j),text);
//                }
//            }
//        }
//        return properties;
//        
//    }
    
    
    private ArrayList<HashMap<String, String>> convertRows(ArrayList<ArrayList<String>> allRows) {
        // TODO check if all rows have same size, because they should. Also should be even count
        ArrayList<HashMap<String, String>> properties = new ArrayList<HashMap<String, String>>();
        
        HashMap<String, String> row = new HashMap<String, String>();

        // skipping every other row and pulling values and props at same time
        for (int i = 1; i < allRows.size(); i += 2) {

            for (int j = 0; j < allRows.get(i).size(); j++) {
                
                // value from this row
                String text = allRows.get(i).get(j);
                // prop from previous row
                String prop = TAMISUtil.cleanProperty(allRows.get(i - 1).get(j));
                
                row.put(prop , text);

            }
        }
        
        properties.add(row);
        TAMISUtil.printMap(row);
        return properties;

    }


    

   
}
