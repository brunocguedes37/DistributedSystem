/*
 * 
 */
package client;

import clientui.AirUI;

/**
 * Bed Client.
 *
 * @author dominic
 */
public class AirClient extends Client {

    private final String WARM = "Hot";
    private boolean isWarming = false;

    /**
     * Air Client Constructor.
     */
    public AirClient() {
        super();
        serviceType = "_air._udp.local.";
        ui = new AirUI(this);
        name = "Living Room";
    }

    /**
     * sends a message to warm the bed.
     */
    public void warm() {
        if (!isWarming) {
            String a = sendMessage(WARM);
            if (a.equals(OK)) {
                isWarming = true;
                ui.updateArea("Air is Hot");
            }
        } else {
            ui.updateArea("Air already Hot");
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Air is 100% Hot.")) {
            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new AirUI(this);
        isWarming = false;
    }
}
