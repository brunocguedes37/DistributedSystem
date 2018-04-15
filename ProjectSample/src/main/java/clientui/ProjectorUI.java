/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientui;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.ProjectorClient;
/**
 *
 * @author x14101629
 */
public class ProjectorUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton projectorOn;
    private JButton projectorOff;
    private JButton projectorHDMI;
    private JButton projectorVGA;
    
    private final ProjectorClient parent;

    public ProjectorUI(ProjectorClient projectorClient) {
        super(projectorClient);
        parent = projectorClient;
        init();
    }
    /**
     * this is to create button in each function in GUI.
     */
    @Override
    public void init() {
        super.init();
        projectorOn = new JButton("ON");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{projectorOn});
        projectorOff = new JButton("OFF");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{projectorOff});
        projectorHDMI = new JButton("HDMI");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{projectorHDMI});
        projectorVGA = new JButton("VGA");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{projectorVGA});
    }
    /**
     * this is to code where the button perform.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == projectorOn) {
            parent.projectorOn();
        } else if (e.getSource() == projectorOff) {
            parent.projectorOff();
        } else if (e.getSource() == projectorHDMI) {
            parent.projectorHDMI();
        } else if (e.getSource() == projectorVGA) {
            parent.projectorVGA();
        }
    }
}

