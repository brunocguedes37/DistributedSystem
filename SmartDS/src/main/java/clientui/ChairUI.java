/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.ChairClient;

/**
 *
 * @author x14101629
 */
public class ChairUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton warm;
    private final ChairClient parent;

    public ChairUI(ChairClient chairClient) {
        super(chairClient);
        parent = chairClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        warm = new JButton("Warm the Chair");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{warm});
        
       //another botton
        warm = new JButton("Cooling Down the Chair");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{warm});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == warm) {
            parent.warm();
        }
    }
}

