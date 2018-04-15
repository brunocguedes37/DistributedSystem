package services;

import java.util.Timer;
import java.util.TimerTask;
import com.google.gson.Gson;

import serviceui.ServiceUI;

/**
 * The DoorService.
 */
public class DoorService extends Service {

    private final Timer timer;
    private int peopleIn;
    /* added string function*/
    private String function;
    /* added Gson*/
    Gson gson = new Gson(); 

    public DoorService(String name) {
        super(name, "_door._udp.local.");
        timer = new Timer();
        peopleIn = 0;
       /* Added function*/
        function = "";
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("Open")) {
            timer.schedule(new RemindTaskDoor(), 0, 2000);
            sendBack("OK");
            ui.updateArea("People coming in.");
        } else if (a.equals("Closed")) {
            timer.schedule(new RemindTaskDoor(), 0, 2000);
            sendBack("OK");
            ui.updateArea("people left the room.");
        }else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    class RemindTaskDoor extends TimerTask {

        @Override
        public void run() {
            if (peopleIn < 25){
                function = "Door Opening";
                ui.updateArea("Added function:" + function);
                peopleIn += 25;
            } else if(peopleIn < 50){
                function = "People entering the meeting room";
                ui.updateArea("Added function:" + function);
                peopleIn += 25;
            } else if(peopleIn < 75){
                function = "People still entering";
                ui.updateArea("Added function:" + function);
                peopleIn += 25;
            } else if(peopleIn < 100){
                function = "Meeting room complete";
                ui.updateArea("Added function:" + function);
                peopleIn += 30;
            }
        }
    }

  /*  @Override
    public String getStatus() {
        return "In the MeetingRoom there are " + peopleIn + " people.";
    }*/

    @Override
    public String getStatus() { 
        return gson.toJson("Current Task: " + function);
    }
    
    public static void main(String[] args) {
        new DoorService("Smart Door");
    }
}
