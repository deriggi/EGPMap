/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pageparser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jderiggi
 */
public abstract class PageParser {
    private String content;
    
    public PageParser(String pageContent){
        this.content = pageContent;
    }
    public PageParser(){
        
    }
    public void setup(String pageContent){
        this.content = pageContent;
    }
    
    public String getContent(){
        return this.content;
    }
    
    /**
     * Return a nice object or hastable of the elements
     * @param url 
     */
    public abstract ArrayList<HashMap<String, String>>  parsePage();
    
    
    
}
