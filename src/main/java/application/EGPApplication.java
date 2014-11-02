/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import resource.ProjectDataResource;

/**
 *
 * @author jderiggi
 */
    
@ApplicationPath("v1")
public class EGPApplication extends Application{
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(ProjectDataResource.class);
        return s;
    }  
    
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
         resources.add(resource.ProjectDataResource.class);
     }
    
    
}
