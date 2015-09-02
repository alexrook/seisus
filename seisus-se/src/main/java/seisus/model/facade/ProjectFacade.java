package seisus.model.facade;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import seisus.model.Project;

/**
 * @author moroz
 */
public class ProjectFacade extends AbstractFacade<Project> {

    @PersistenceContext
    EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectFacade() {
        super(Project.class);
    }

    Project getProjectByName(String name) {
        return em.createNamedQuery(name, Project.class)
                    .setParameter("name", name).getSingleResult();
    }

}
