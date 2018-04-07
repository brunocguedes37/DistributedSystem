/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clientui.ChairUI;

/**
 *
 * @author x14101629
 */
public class ChairClient extends Client {

    private final String WARM = "Warm";
    private boolean isWarming = false;

    /**
     * Air Client Constructor.
     */
    public ChairClient() {
        super();
        serviceType = "_chair._udp.local.";
        ui = new ChairUI(this);
        name = "Smart Chair";
    }

    /**
     * sends a message to warm the chair.
     */
    public void warm() {
        if (!isWarming) {
            String a = sendMessage(WARM);
            if (a.equals(OK)) {
                isWarming = true;
                ui.updateArea("Chair is Warm");
            }
        } else {
            ui.updateArea("Chair already Warmed");
        }
    }


    
    
        
    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Chair is good to use.")) {
            boolean isCooling = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new ChairUI(this);
        isWarming = false;
    }
    
}
