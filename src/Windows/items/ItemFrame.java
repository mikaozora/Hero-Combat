package Windows.items;

import Windows.characters.CharacterPanel;
import Windows.maps.MapsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemFrame extends JFrame implements ActionListener {

    ItemPanel panel;
    public ItemFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        panel = new ItemPanel();
        panel.button.addActionListener(this);
        this.add(panel);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == panel.button){
            this.dispose();
            new MapsFrame();
        }
    }
}
