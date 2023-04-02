package Windows.characters;

import javax.swing.*;
import java.awt.*;

public class CharacterPanel extends JPanel {
    JLabel title;
    JButton button;
    CharacterPanel(){
        title = new JLabel("Choose Character");
        title.setForeground(Color.white);
        title.setBounds(500, 0, 300, 100);

        button = new JButton("Next");
        button.setBounds(900, 700, 100, 50);

        this.add(title);
        this.add(button);
        this.setPreferredSize(new Dimension(1200, 800));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.setDoubleBuffered(true);
    }
}
