/*
 * 
 */
package client;

import clientui.DoorUI;

/**
 * DoorClient
 *
 * @author x14104571
 */
public class DoorClient extends Client {

    private final String OPEN = "Open";
    private final String CLOSE = "Close";
    private boolean isOpening = false;
    private boolean isClosed = false;

    
//DoorClient Constructor.
    public DoorClient() {
        super();
        serviceType = "_door._udp.local.";
        ui = new DoorUI(this);
        name = "SmartDoor";
    }

    
//Test code NOT IN USE, didn't delete it for future reference    
    /* 
    public void open() {
        if (isOpening == true) {
        if (!isClosed) {
            String a = sendMessage(OPEN);
            if (a.equals(OK)) {
                isClosed = true;
                ui.updateArea("MeetingRoom finished, The door is now Closed.");
            }
        } else {
            ui.updateArea("MeetingRoom ended ");
        }
        } 
        else{
            
            ui.updateArea("MeetingRoom starting."); // User is told to turn on the power before the kettle boils
        }
    }
     
      public void close() {
        if (isClosed == true) {
        if (!isOpening) {
            String a = sendMessage(CLOSE);
             if (a.equals(OK)) {
                isOpening = true;
                ui.updateArea("MeetingRoom ready, The door is now Open.");
            }
        } else {
            ui.updateArea("MeetingRoom already started ");
        }
        } 
        else{
            
            ui.updateArea("MeetingRoom finished, closing door."); // User is told to turn on the power before the kettle boils
        }
    }
    */
            
            
           
      
      
//sends a message to open door.
    public void open() {
        if (!isOpening) {
            String a = sendMessage(OPEN);
            if (a.equals(OK)) {
                isOpening= true;
                ui.updateArea("MeetingRoom ready, The door is now Open. ");
            }
        } else {
            ui.updateArea("MeetingRoom already started ");
        }
    }
//sends a message to close door.
    public void close() {
        if (!isClosed) {
            String a = sendMessage(CLOSE);
            if (a.equals(OK)) {
                isClosed= true;
                ui.updateArea("MeetingRoom finished, closing door. ");
            }
        } else {
            ui.updateArea("The Door is now closed. ");
        }
    }

    
//sends messages to console
    @Override
    public void updatePoll(String msg) {
        if (msg.equals("\"Status function: Meeting room complete\"")){
            isOpening = false;
            ui.updateArea("MeetingRoom Complete");
        } else if(msg.equals("\"Status function: Meeting room empty\"")){
           isClosed = false;
           ui.updateArea("MeetingRoom empty");
    }  
    }

    @Override
    public void disable() {
        super.disable();
        ui = new DoorUI(this);
        isOpening = false;
        isClosed = false;
    }
}
