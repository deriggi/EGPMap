/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import cache.GsonHelper;
import cache.ProjectCache;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jderiggi
 */
@Path("project")
public class ProjectDataResource {
    
    @GET
    @Path("/{project}/{dataset}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sendData(@PathParam("project") String project, @PathParam("dataset") String dataset){
        String json = GsonHelper.get().toJson(ProjectCache.get().getData(project, dataset));
        
        return json;
    }
    
    @GET
    @Path("/egp/technicalactivities")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTechnicalActivities(){
        String json = GsonHelper.get().toJson(ProjectCache.get().getTechnicalActivities().values());
        
        return json;
    }
    
     
    @GET
    @Path("/egp/training")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTrainingActivities(){
        String json = GsonHelper.get().toJson(ProjectCache.get().getTraining().values());
        
        return json;
    }
    
     @GET
    @Path("/egp/indicators")
    @Produces(MediaType.APPLICATION_JSON)
    public String getIndicators(){
        String json = GsonHelper.get().toJson(ProjectCache.get().getIps());
        
        return json;
    }
    
    @GET
    @Path("/txt")
    @Produces("text/plain")
    public Response getTextFile() { 
        
        Response.ResponseBuilder response = Response.ok((Object) "sometextforyoutodownload");
        response.header("Content-Disposition", "attachment; filename=\"test_text_file.txt\"");
        return response.build();
    }

    
     
    
}
