package seisus.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * @author moroz
 */
@Entity(name = "node")
public class NodeLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(length = 25)
    String label;

    @ManyToOne(cascade = CascadeType.REMOVE)
    Project project;

    public NodeLabel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.label);
        hash = 53 * hash + Objects.hashCode(this.project);
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
        final NodeLabel other = (NodeLabel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return Objects.equals(this.project, other.project);
    }

}
