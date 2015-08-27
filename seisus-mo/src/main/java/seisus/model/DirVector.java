package seisus.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * @author moroz
 */
@MappedSuperclass
public  class DirVector implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    protected Frequency frequency;

    protected Double accel05, accel02, accel01;
    
    public DirVector() {
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Double getAccel05() {
        return accel05;
    }

    public void setAccel05(Double accel05) {
        this.accel05 = accel05;
    }

    public Double getAccel02() {
        return accel02;
    }

    public void setAccel02(Double accel02) {
        this.accel02 = accel02;
    }

    public Double getAccel01() {
        return accel01;
    }

    public void setAccel01(Double accel01) {
        this.accel01 = accel01;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.frequency);
        hash = 89 * hash + Objects.hashCode(this.accel05);
        hash = 89 * hash + Objects.hashCode(this.accel02);
        hash = 89 * hash + Objects.hashCode(this.accel01);
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
        final DirVector other = (DirVector) obj;
        if (!Objects.equals(this.frequency, other.frequency)) {
            return false;
        }
        if (!Objects.equals(this.accel05, other.accel05)) {
            return false;
        }
        if (!Objects.equals(this.accel02, other.accel02)) {
            return false;
        }
        return Objects.equals(this.accel01, other.accel01);
    }

   

}
