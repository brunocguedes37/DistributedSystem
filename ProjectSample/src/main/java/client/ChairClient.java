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

    private final String SET = "Set";
    private boolean isSetting = false;

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
    public void set(String set) {
        if (!isSetting) {
            String a = sendMessage(SET);
            if (a.equals(OK)) {
                isSetting = true;
                ui.updateArea("Chair is processing");
                ui.updateArea(set + "Tempeture");
            }
        } else {
            ui.updateArea("SORRY! your chairs has been set");
        }
    }


    
    
        
    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Chair is good to use.")) {
            isSetting = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new ChairUI(this);
        isSetting = false;
    }
    
}