package seisus.web.rest.ctrl;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public Project getColorByID(@PathParam("id") int id) {
        return facade.get(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectList(@HeaderParam("X-Range") String headerRange) {
        return getItemsList(facade, headerRange);
    }
}
