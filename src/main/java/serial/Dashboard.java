package serial;

public class Dashboard {

    private final int buttons;
    private final boolean analogDisplay;

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
