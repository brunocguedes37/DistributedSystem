/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.ChairClient;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author x14101629
 */
public class ChairUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton chairUp;
    private JButton chairDown;
    private JButton set;
    private final ChairClient parent;
    private JTextField temset;
    private JLabel labeltext;
    private JLabel labeltext2;
    private String tempeture;

    public ChairUI(ChairClient chairClient) {
        super(chairClient);
        parent = chairClient;
        init();
    }

    @Override
    public void init() {
        super.init();  
        chairUp = new JButton("PushUP");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{chairUp});
        chairDown = new JButton("PushDOWN");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{chairDown});
       
        JLabel labeltext = new JLabel("Please choose tempeture");
        add(new JLabel[]{labeltext});
        JTextField temset = new JTextField(3);
        add(new JTextField[]{temset});
        JLabel labeltext2 = new JLabel("'C");
        add(new JLabel[]{labeltext2});
        
        
        set = new JButton("SET");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{set});
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

           
           if (e.getSource() == chairUp) {
            parent.chairUp();
        } else if (e.getSource() == chairDown) {
            parent.chairDown();
        } else if (e.getSource() == set) {
            parent.set(tempeture);
        } 
           
    }
}

