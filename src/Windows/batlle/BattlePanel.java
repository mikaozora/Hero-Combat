package Windows.batlle;

import javax.swing.*;
import java.awt.*;

public class BattlePanel extends JPanel {
    JLabel title;
    JButton button;
    BattlePanel(){
        title = new JLabel("Battle");
        title.setForeground(Color.white);
        title.setBounds(500, 0, 300, 100);

        button = new JButton("Skill 1");
        button.setBounds(900, 700, 100, 50);

        this.add(title);
        this.add(button);
        this.setPreferredSize(new Dimension(1200, 800));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.setDoubleBuffered(true);
    }
}
