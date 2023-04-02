package Windows.characters;

import Windows.items.ItemFrame;
import Windows.main.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterFrame extends JFrame implements ActionListener {
    CharacterPanel panel;
    public CharacterFrame(){

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        panel = new CharacterPanel();
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
            new ItemFrame();
        }
    }
}
