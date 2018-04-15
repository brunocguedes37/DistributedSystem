package services;

import java.util.Timer;
import java.util.TimerTask;
import com.google.gson.Gson;

import serviceui.ServiceUI;

/**
 * AssistentService
 * 
 * @author x14104571
 */
public class AssistentService extends Service {

    private final Timer timer;
    private int record;
    private String action;
//Adding Gson messaging     
    Gson gson = new Gson(); 
    
    public AssistentService(String name) {
        super(name, "_assistent._udp.local.");
        timer = new Timer();
        record = 0;
        action = "";    
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());    
        } else if (a.equals("Start")) {
            ui.updateArea("Started recording meeting");
            timer.schedule(new RemindTaskStart(), 0, 14000);
            sendBack("OK");
        } else if (a.equals("Finish")){ 
            ui.updateArea("Finished recording meeting");
            timer.schedule(new RemindTaskFinish(), 0, 14000);
            sendBack("OK");
        } else if (a.equals("Send")){
            ui.updateArea("Sending meeting summary");
            timer.schedule(new RemindTaskSend(), 0, 14000);
            sendBack("OK");
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    class RemindTaskStart extends TimerTask {

        @Override
        public void run() {
            //updating progress  
            if (record < 20){
                action = "Turning recorder ON";
                ui.updateArea("Added Task:" + action);
                record += 20;
            } else if(record < 40){
                action = "Recording of the Meeting will START shortly ";
                ui.updateArea("Added Task:" + action);
                record += 20;
            } else if(record < 60){
                action = "Recording Started";
                ui.updateArea("Added Task:" + action);
                record += 20;
            } else if(record < 80){
                action = "Meeting is being recorded";
                ui.updateArea("Added Task:" + action);
                record += 20;
            } else if(record < 100){
                action = "Meeting recorded";
                ui.updateArea("Added Task:" + action);
                record += 25;
            }
        }
    }
    
    class RemindTaskFinish extends TimerTask {

        @Override
        public void run() {
            if (record < 25){
                action = "Turning recorder OFF";
                ui.updateArea("Added Task:" + action);
                record += 25;
            } else if(record < 50){
                action = "Recording of the Meeting will FINISH shortly";
                ui.updateArea("Added Task:" + action);
                record += 25;
            } else if(record < 75){
                action = "Recording is about to FINISH";
                ui.updateArea("Added Task:" + action);
                record += 25;
            } else if(record < 100){
                action = "Recording FINISHED";
                ui.updateArea("Added Task:" + action);
                record += 25;
            }     
        }
    }
    
    class RemindTaskSend extends TimerTask {

        @Override
        public void run() {
            if (record < 20){
                action = "Turning sender ON";
                ui.updateArea("Added Task:" + action);
                record += 20;
            } else if(record < 40){
                action = "Summarizing the meeting";
                ui.updateArea("Added Task:" + action);
                record += 20;
            } else if(record < 60){
                action = "Uploading summary to SENDER";
                ui.updateArea("Added Task:" + action);
                record += 20;
            } else if(record < 80){
                action = "Summary of the meeting ready to SEND";
                ui.updateArea("Added Task:" + action);
                record += 20;
            } else if(record < 100){
                action = "Sending summary";
                ui.updateArea("Added Task:" + action);
                record += 25;
            }
        }
    }

//Adding Gson messaging     
    @Override
    public String getStatus() { 
        return gson.toJson("Status: " + action);
    }

    public static void main(String[] args) {
        new AssistentService("MeetingAssistent");
    }
}



