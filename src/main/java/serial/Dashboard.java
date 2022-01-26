package serial;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dashboard")
public class Dashboard {

    @XmlAttribute
    private int buttons;

    @XmlAttribute
    private boolean analogDisplay;

    public Dashboard() {
    }

    public Dashboard(int buttons, boolean analogDisplay) {
        this.buttons = buttons;
        this.analogDisplay = analogDisplay;
    }

    @Override
    public String toString() {
        return "Dashboard{"
                + "buttons=" + buttons
                + ", analogDisplay=" + analogDisplay
                + '}';
    }
}
