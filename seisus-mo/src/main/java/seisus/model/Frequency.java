package seisus.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * @author moroz
 */
@Entity
public class Frequency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Double val;
    
    @ManyToOne
    NodeLabel label;

    public Frequency() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }

    public NodeLabel getLabel() {
        return label;
    }

    public void setLabel(NodeLabel label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.val);
        hash = 37 * hash + Objects.hashCode(this.label);
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
        final Frequency other = (Frequency) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.val, other.val)) {
            return false;
        }
        return Objects.equals(this.label, other.label);
    }
    
    

}
