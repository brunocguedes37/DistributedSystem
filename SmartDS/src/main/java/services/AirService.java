package services;

import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;

/**
 * The Class AirService.
 */
public class AirService extends Service {

    private final Timer timer;
    private int percentHot;

    public AirService(String name) {
        super(name, "_air._udp.local.");
        timer = new Timer();
        percentHot = 0;
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("Hot")) {
            timer.schedule(new RemindTask(), 0, 1000);
            sendBack("OK");
            ui.updateArea("Warming Air");
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            if (percentHot < 100) {
                percentHot += 15;
            }
        }
    }

    @Override
    public String getStatus() {
        return "Air is " + percentHot + "% Hot.";
    }

    public static void main(String[] args) {
        new AirService("Nuth's");
    }
}
