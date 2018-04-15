/*
 * 
 */
package client;

import clientui.DoorUI;

/**
 * Bed Client.
 *
 * 
 */
public class DoorClient extends Client {

    private final String OPEN = "Open";
    private final String CLOSE = "Close";
    private boolean isOpening = false;
    private boolean isClosed = false;

    /**
     *Door Client Constructor.
     */
    public DoorClient() {
        super();
        serviceType = "_door._udp.local.";
        ui = new DoorUI(this);
        name = "SmartDoor";
    }

    /**
     * sends a message to open door.
     */
    public void open() {
        if (!isOpening) {
            String a = sendMessage(OPEN);
            if (a.equals(OK)) {
                isOpening= true;
                ui.updateArea("MeetingRoom ready to open Door. ");
            }
        } else {
            ui.updateArea("The Door is now open. ");
        }
    }
    
    /**
     * sends a message to close door.
     */
    public void close() {
        if (!isClosed) {
            String a = sendMessage(CLOSE);
            if (a.equals(OK)) {
                isClosed= false;
                ui.updateArea("MeetingRoom ready to close Door. ");
            }
        } else {
            ui.updateArea("The Door is now closed. ");
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Meeting room complete")){
            isOpening = false;
            ui.updateArea("MeetingRoom Complete");
        }
      else if(msg.equals("Door Opening")){
           isClosed = false;
           ui.updateArea("MeetingRoom empty");
    }  
    }

    @Override
    public void disable() {
        super.disable();
        ui = new DoorUI(this);
        isOpening = false;
    }
}
