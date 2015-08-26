package seisus.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author moroz
 */
@Entity
public class NodeLabel implements Serializable {

    private static final long serialVersionUID = 1L;

     @Embeddable
    public class NodeLabelPK implements Serializable {
         Long projectId;
         String label;
     }

    @Id
    private NodeLabelPK id;
    
    @ManyToOne
    @JoinColumn(name = "projectId")
    Project project;

    public NodeLabelPK getId() {
        return id;
    }

    public void setId(NodeLabelPK id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    
    

    @Override
    public String toString() {
        return "seisus.model.NodeLabel[ projectId=" +project.getId() + ", label="+id.label+" ]";
    }

}
