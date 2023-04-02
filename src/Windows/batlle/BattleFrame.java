package Windows.batlle;

import Windows.maps.MapsPanel;

import javax.swing.*;

public class BattleFrame extends JFrame {
    BattlePanel panel;
    public BattleFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        panel = new BattlePanel();
        this.add(panel);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
