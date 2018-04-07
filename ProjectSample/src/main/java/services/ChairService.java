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
public class ChairService extends Service {

    private final Timer timer;
    private int percentHot;

    public ChairService(String name) {
        super(name, "_chair._udp.local.");
        timer = new Timer();
        percentHot = 0;
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("Warm")) {
            timer.schedule(new RemindTask(), 0, 1000);
            sendBack("OK");
            ui.updateArea("Warming Chair");
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            if (percentHot < 35) {
                percentHot += 1;
            }
        }
    }

    @Override
    public String getStatus() {
        return "Chair is " + percentHot + "'C Warm.";
    }
    

    public static void main(String[] args) {
        new ChairService("Nuth's");
    }
}
