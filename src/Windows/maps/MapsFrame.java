package Windows.maps;

import Windows.batlle.BattleFrame;
import Windows.items.ItemPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapsFrame extends JFrame implements ActionListener {
    MapsPanel panel;
    public  MapsFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        panel = new MapsPanel();
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
            new BattleFrame();
        }
    }
}
