package seisus.model;

import java.io.Serializable;
import java.util.Objects;
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

        public Long getProjectId() {
            return projectId;
        }

        public void setProjectId(Long projectId) {
            this.projectId = projectId;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 79 * hash + Objects.hashCode(this.projectId);
            hash = 79 * hash + Objects.hashCode(this.label);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final NodeLabelPK other = (NodeLabelPK) obj;
            if (!Objects.equals(this.projectId, other.projectId)) {
                return false;
            }
            return Objects.equals(this.label, other.label);
        }
        
    }

    @Id
    private NodeLabelPK pk;

    @ManyToOne
    @JoinColumn(name = "projectId")
    Project project;

    public NodeLabelPK getPk() {
        return pk;
    }

    public void setPk(NodeLabelPK pk) {
        this.pk = pk;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "seisus.model.NodeLabel[ projectId=" + project.getId() + ", label=" + pk.label + " ]";
    }

}
