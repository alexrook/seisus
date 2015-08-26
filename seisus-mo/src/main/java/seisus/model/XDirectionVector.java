package seisus.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author moroz
 */
@Entity
public class XDirectionVector implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "frequencyVal", referencedColumnName = "val"),
        @JoinColumn(name = "nodeLabel", referencedColumnName = "nodeLabel"),
        @JoinColumn(name = "projectId", referencedColumnName = "projectId")
    })
    Frequency pk;

    double accel05, accel02, accel01;

    public double getAccel05() {
        return accel05;
    }

    public void setAccel05(double accel05) {
        this.accel05 = accel05;
    }

    public double getAccel02() {
        return accel02;
    }

    public void setAccel02(double accel02) {
        this.accel02 = accel02;
    }

    public double getAccel01() {
        return accel01;
    }

    public void setAccel01(double accel01) {
        this.accel01 = accel01;
    }

    @Override
    public String toString() {
        return "seisus.model.XDirectionVector[ frequency=" +pk.getPk().getVal()  +
                                                ", node=" + pk.getLabel().getPk().getLabel() + "  ]";
    }

}
