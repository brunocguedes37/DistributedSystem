package client;

import clientui.AssistentUI;

/**
 * AssistentClient
 * 
 * @author x14104571
 */
public class AssistentClient extends Client {
    
    private final String START = "Start";
    private final String FINISH = "Finish";
    private final String SEND = "Send";
    
    private boolean isWorking = false;

//AssistentClient constructor
    public AssistentClient() {
        super();
        serviceType = "_assistent._udp.local.";
        ui = new AssistentUI(this);
        name = "MeetingAssistent";
    }

//methods for start , finish and send recordings
    public void start() {
        if (!isWorking) {
            String a = sendMessage(START);
            if (a.equals(OK)) {
                isWorking = true;
                ui.updateArea("Assistent ready to START recording the meeting");
            }
        } else{
            ui.updateArea("Assistent already STARTED recording");
        }
    }
    
    public void finish() {
        if (!isWorking) {
            String a = sendMessage(FINISH);
            if (a.equals(OK)) {
                isWorking = true;
                ui.updateArea("Assistent will FINISH recording the meeting");
               
            }
        } else{
            ui.updateArea("Assistent already FINISHED recording");
        }
    }
    
    public void send() {
        if (!isWorking) {
            String a = sendMessage(SEND);
            if (a.equals(OK)) {
                isWorking = true;
                ui.updateArea("Assistent will SEND a summary of the meeting to all present");
               
            }
        } else{
            ui.updateArea("Assistent SENDING summary of the meeting");
        }
    }

    @Override
    public void updatePoll(String msg) {
//stop once final methods is printed
        if (msg.equals("\"Status: Meeting recorded\"")||(msg.equals("\"Status: Recording FINISH\""))||(msg.equals("\"Current Task: Sending summary\"")) ){
           isWorking = false;
            ui.updateArea("Meeting done");
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new AssistentUI(this);
        isWorking = false;
    }
}


