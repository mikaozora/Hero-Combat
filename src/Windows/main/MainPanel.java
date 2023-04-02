package Windows.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel{

    JButton play;
    JButton exit;

    JFrame frame;
    MainPanel(){
        play = new JButton();
        play.setBounds(500, 100, 100, 50);
        play.setText("Play");

        exit = new JButton();
        exit.setText("Exit");
        exit.setBounds(500, 200, 100, 50);

        this.add(play);
        this.add(exit);
        this.setPreferredSize(new Dimension(1200, 800));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.setDoubleBuffered(true);
    }

}
