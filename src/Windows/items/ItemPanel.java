package Windows.items;

import javax.swing.*;
import java.awt.*;

public class ItemPanel extends JPanel {
    JLabel title;
    JButton button;
    ItemPanel(){
        title = new JLabel("Choose Item");
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
