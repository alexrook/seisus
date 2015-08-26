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

    @Embeddable
    public class FrequencyPK implements Serializable {

        Double val;
        String nodeLabel;

        public Double getVal() {
            return val;
        }

        public void setVal(Double val) {
            this.val = val;
        }

        public String getNodeLabel() {
            return nodeLabel;
        }

        public void setNodeLabel(String nodeLabel) {
            this.nodeLabel = nodeLabel;
        }
        

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.val);
            hash = 97 * hash + Objects.hashCode(this.nodeLabel);
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
            final FrequencyPK other = (FrequencyPK) obj;
            if (!Objects.equals(this.val, other.val)) {
                return false;
            }
            return Objects.equals(this.nodeLabel, other.nodeLabel);
        }

       
    }

    @Id
    FrequencyPK id;
    
    @ManyToOne
    @JoinColumn(name = "nodeLabel")
    String label;

    public FrequencyPK getId() {
        return id;
    }

    public void setId(FrequencyPK id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

  

    @Override
    public String toString() {
        return "seisus.model.Frequency[ node=" + label + ", value=" + id.val + " ]";
    }

}
