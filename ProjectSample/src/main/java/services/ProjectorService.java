/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;
/**
 *
 * @author x14101629
 */
public class ProjectorService extends Service {

    private final Timer timer;
    private boolean projectorOn;
    private boolean projectorOff;
    private boolean projectorHDMI;
    private boolean projectorVGA;
    

    public ProjectorService(String name) {
        super(name, "_projector._udp.local.");
        timer = new Timer();
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("ON")) {
            timer.schedule(new RemindTaskProjector(), 0, 2000);
            sendBack("OK");
            ui.updateArea("Projector is now ON");
        } else if (a.equals("OFF")) {
            sendBack("OK");
            ui.updateArea("Projector is now OFF");
        } else if (a.equals("HDMI")) {
            sendBack("OK");
            ui.updateArea("Projector is now connected with HDMI");
        } else if (a.equals("VGA")) {
            sendBack("OK");
            ui.updateArea("Projector is now connected with VGA ");
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    class RemindTaskProjector extends TimerTask {

        @Override
        public void run() {
            if (projectorHDMI = true) {
                projectorVGA = false;
            } else if (projectorVGA = true) {
                       projectorHDMI = false;
            }           
        }
    }

    @Override
    public String getStatus() {
        return "Welcome to smart meeting room please choose your perform.";
    }

    public static void main(String[] args) {
        new ProjectorService("Smart Meeting1");
    }
}

