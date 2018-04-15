package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.DoorClient;

/**
 * DoorUI 
 *
 * @author x14104571
 */

public class DoorUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
// adding Jbutton
    private JButton open;
    private JButton close;
    private final DoorClient parent;

    public DoorUI(DoorClient doorClient) {
        super(doorClient);
        parent = doorClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        open = new JButton("Open");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{open});
        close = new JButton("Close");
        scroll.setBounds(10, 30, UIConstants.COMPONENTWIDTH, 200);
        add(new JButton[]{close});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open) {
            parent.open();
        } else if (e.getSource() == close) {
            parent.close();
        }
    }
}
