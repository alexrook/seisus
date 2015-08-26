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
    Long projectId;
    

    @Override
    public String toString() {
        return "seisus.model.NodeLabel[ projectId=" +projectId + ", label="+id.label+" ]";
    }

}
