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
    private boolean chairUp;
    private boolean chairDown;


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
        
        } else if (a.equals("UP")) {
            timer.schedule(new RemindTaskChair(), 0, 2000);
            sendBack("OK");
            ui.updateArea("Chair is now push UP");
        } else if (a.equals("DOWN")) {
            sendBack("OK");
            ui.updateArea("Chair is push DOWN");
      
            
        } else if (a.equals("Set")) {
            timer.schedule(new RemindTaskSet(), 0, 1000);
            sendBack("OK");
            ui.updateArea("Chair is setting to your require");
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }

    }

    //try 
    class RemindTaskChair extends TimerTask {

        @Override
        public void run() {
            if (chairUp = true) {
                chairDown = false;
            } else if (chairDown = true) {
                       chairUp = false;
            }           
        }
    }
    
    
    class RemindTaskSet extends TimerTask {

        @Override
        public void run() {
            if (percentHot < 100) {
                percentHot += 10;
            }
        }
    }




        @Override
        public String getStatus
        
            () {
        return "Chair is now processing.. It is " + percentHot + "% ready for you";
        }
            
    
    

    

    public static void main(String[] args) {
        new ChairService("Smart Meeting1");
    }
}
