package serial;

import java.util.Arrays;

public class CarModel {

    private final boolean isLeftSteeringWheel;
    private final int wheels;
    private final String name;
    private final Dashboard dashboard;
    private final String[] colors;

    public CarModel(boolean isLeftSteeringWheel, int wheels, String name, Dashboard dashboard, String[] colors) {
        this.isLeftSteeringWheel = isLeftSteeringWheel;
        this.wheels = wheels;
        this.name = name;
        this.dashboard = dashboard;
        this.colors = colors;
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
