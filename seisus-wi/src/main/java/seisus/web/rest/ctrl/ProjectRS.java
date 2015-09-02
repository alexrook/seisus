package seisus.web.rest.ctrl;

import java.io.InputStream;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import seisus.model.Project;
import seisus.model.facade.ProjectFacade;
import seisus.web.rest.AbstractRS;

/**
 * @author moroz
 */
@Path("/project")
public class ProjectRS extends AbstractRS {

    @EJB
    ProjectFacade facade;

    @GET
    @Path("{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProjectByID(@PathParam("id") int id) {
        return facade.get(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectList(@HeaderParam("X-Range") String headerRange) {
        return getItemsList(facade, headerRange);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response putDirVector(@HeaderParam("projectName") String projectName,
            @HeaderParam("label") String label, @FormParam("file") InputStream in) {
        
      //  request.
        return Response.ok().build();
    }
}
