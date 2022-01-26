package serial;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "carmodel")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarModel {

    @XmlAttribute
    private boolean isLeftSteeringWheel;

    @XmlAttribute
    private int wheels;
    private String name;
    private Dashboard dashboard;
    private String[] colors;

    public CarModel() {
    }

    public CarModel(boolean isLeftSteeringWheel, int wheels, String name, Dashboard dashboard, String[] colors) {
        this.isLeftSteeringWheel = isLeftSteeringWheel;
        this.wheels = wheels;
        this.name = name;
        this.dashboard = dashboard;
        this.colors = colors;
    }

    public boolean isLeftSteeringWheel() {
        return isLeftSteeringWheel;
    }

    public int getWheels() {
        return wheels;
    }

    public String getName() {
        return name;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public String[] getColors() {
        return colors;
    }

    @Override
    public String toString() {
        return "CarModel{"
                + "isLeftSteeringWheel=" + isLeftSteeringWheel
                + ", wheels=" + wheels
                + ", name=" + name
                + ", dashboard=" + dashboard
                + ", colors=" + Arrays.toString(colors)
                + '}';
    }
}
