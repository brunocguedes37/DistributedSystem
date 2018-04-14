/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clientui.ProjectorUI;
/**
 *
 * @author x14101629
 */
public class ProjectorClient extends Client {

    private final String projectorOn = "ON";
    private final String projectorOff = "OFF";
    private boolean isProjectorOn = false;
    private boolean isProjectorOff = false;
    private final String projectorHDMI = "HDMI";
    private final String projectorVGA = "VGA";
    private boolean isProjectorHDMI = false;
    private boolean isProjectorVGA = false;

    /**
     * ProjectorClient Constructor.
     */
    public ProjectorClient() {
        super();
        serviceType = "_projector._udp.local.";
        ui = new ProjectorUI(this);
        name = "Projector";
    }

    /**
     * sends a message to projector.
     */
    public void projectorOn() {
        if (!isProjectorOn) {
            String a = sendMessage(projectorOn);
            if (a.equals(OK)) {
                isProjectorOn = true;
                ui.updateArea("Projector is ON");
            }
        } else {
            ui.updateArea("Projector already ON");
        }
    }
    
    public void projectorOff() {
        if (!isProjectorOff) {
            String a = sendMessage(projectorOff);
            if (a.equals(OK)) {
                isProjectorOff = true;
                ui.updateArea("Projector is OFF");
            }
        } else {
            ui.updateArea("Projector already OFF");
        }
    }
    
    public void projectorHDMI() {
        if (!isProjectorHDMI) {
            String a = sendMessage(projectorOff);
            if (a.equals(OK)) {
                isProjectorHDMI = true;
                ui.updateArea("Projector is now connected with HDMI");
            }
        } else {
            ui.updateArea("Projector already CONNECTED");
        }
    }
    
    public void projectorVGA() {
        if (!isProjectorVGA) {
            String a = sendMessage(projectorOff);
            if (a.equals(OK)) {
                isProjectorVGA = true;
                ui.updateArea("Projector is now connected with VGA");
            }
        } else {
            ui.updateArea("Projector already CONNECTED");
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Projector is ON")) {
            isProjectorOn = false;
        } else if (msg.equals("Projector is OFF")) {
            isProjectorOff = false;
        } else if (msg.equals("Projector is now connected with HDMI")) {
            isProjectorHDMI = false;
        } else if (msg.equals("Projector is now connected with VGA")) {
            isProjectorVGA = false;
        } 
    }

    @Override
    public void disable() {
        super.disable();
        ui = new ProjectorUI(this);
        isProjectorOn = false;
    }
}

