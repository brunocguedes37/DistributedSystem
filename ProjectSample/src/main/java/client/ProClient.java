/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clientui.ProUI;
/**
 *
 * @author x14101629
 */
public class ProClient extends Client {

    private final String WARM = "Warm";
    private boolean isWarming = false;

    /**
     * Bed Client Constructor.
     */
    public ProClient() {
        super();
        serviceType = "_pro._udp.local.";
        ui = new ProUI(this);
        name = "Projector";
    }

    /**
     * sends a message to warm the bed.
     */
    public void warm() {
        if (!isWarming) {
            String a = sendMessage(WARM);
            if (a.equals(OK)) {
                isWarming = true;
                ui.updateArea("Bed is Warming");
            }
        } else {
            ui.updateArea("Bed already Warming");
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Bed is 100% warmed.")) {
            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new ProUI(this);
        isWarming = false;
    }
}

