/*
 * 
 */
package services;

import java.util.Timer;
import java.util.TimerTask;
import com.google.gson.Gson;

import serviceui.ServiceUI;

/**
 * DoorService.
 *
 * @author x14104571
 */
public class DoorService extends Service {

    private final Timer timer;
    private int peopleIn;
    //added string function
    private String function;
    //added Gson
    Gson gson = new Gson(); 

    public DoorService(String name) {
        super(name, "_door._udp.local.");
        timer = new Timer();
        peopleIn = 0;
//Added function
        function = "";
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("Open")) {
            timer.schedule(new RemindTaskOpen(), 0, 14000);
            sendBack("OK");
            ui.updateArea("The meeting Room is now Open.");
        } else if (a.equals("Closed")) {
            timer.schedule(new RemindTaskClose(), 0, 14000);
            sendBack("OK");
            ui.updateArea("The meeting Room is now Closed.");
        }else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    class RemindTaskOpen extends TimerTask {
        @Override
        public void run() {
            if (peopleIn < 25){
                function = "Door Opening";
                ui.updateArea("Current function:" + function);
                peopleIn += 25;
            } else if(peopleIn < 50){
                function = "People entering the meeting room";
                ui.updateArea("Current function:" + function);
                peopleIn += 25;
            } else if(peopleIn < 75){
                function = "People still entering";
                ui.updateArea("Current function:" + function);
                peopleIn += 25;
            } else if(peopleIn < 100){
                function = "Meeting room complete";
                ui.updateArea("Current function:" + function);
                peopleIn += 30;
            }
        }
    }

    
    class RemindTaskClose extends TimerTask {
        @Override
        public void run() {
            if (peopleIn < 25){
                function = "Door Closing";
                ui.updateArea("Current function:" + function);
                peopleIn += 25;
            } else if(peopleIn < 50){
                function = "People leaving the meeting room";
                ui.updateArea("Current function:" + function);
                peopleIn += 25;
            } else if(peopleIn < 75){
                function = "People still Leaving";
                ui.updateArea("Current function:" + function);
                peopleIn += 25;
            } else if(peopleIn < 100){
                function = "Meeting room Empty";
                ui.updateArea("Current function:" + function);
                peopleIn += 30;
            }
        }
    }
  
   
//Adding Gson messaging 
    @Override
    public String getStatus() { 
        return gson.toJson("Status function: " + function);
    }
    
    public static void main(String[] args) {
        new DoorService("SmartDoor");
    }
}
