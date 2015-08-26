package seisus.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author moroz
 */
@Entity(name = "x_vector")
public class XDirVector implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "node", referencedColumnName = "nodeLabel", insertable = false, updatable = false),
        @JoinColumn(name = "projectId", referencedColumnName = "projectId", insertable = false, updatable = false),
        @JoinColumn(name = "frequency", referencedColumnName = "val", insertable = false, updatable = false)
    })
    private Frequency frequency;

    private Double accel05, accel02, accel01;

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
    public String toString() {
        return "XDirVector{" + "frequency=" + frequency 
                + ", accel05=" + accel05
                + ", accel02=" + accel02
                + ", accel01=" + accel01 + '}';
    }

}
