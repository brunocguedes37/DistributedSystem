package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import client.AssistentClient;

/**
 * AssistentUI
 * 
 * @author x14104571
 */
public class AssistentUI extends ClientUI {

    private static final long serialVersionUID = 1L;
    private JButton preform;
    private JComboBox container;
    private final AssistentClient parent;

    public AssistentUI(AssistentClient assistentClient) {
        super(assistentClient);
        parent = assistentClient;
        init();
    }

    @Override
    public void init() {
        super.init();
//list of options (START , FINISH  and SEND
        String[] record = {"Start", "Finish", "Send"};   
        container = new JComboBox(record);
        preform = new JButton("PreformAction");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JComboBox[]{container});
        add(new JButton[]{preform});
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
//combobox
        String type = container.getSelectedItem().toString();
        
//run the function for the corresponding preform action
        if (type.equals("Start")) {
            parent.start();
        }
            
        if (type.equals("Finish")) {
            parent.finish();
        }
        
        if (type.equals("Send")) {
            parent.send();
        }
        
    }
}

