package Windows.main;

import Windows.characters.CharacterFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    private MainPanel panel;

    MainFrame(){

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        panel = new MainPanel();
        panel.play.addActionListener(this);
        panel.exit.addActionListener(this);
        this.add(panel);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == panel.play){
            this.dispose();
            new CharacterFrame();
        }
        if(e.getSource() == panel.exit){
            this.dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }

}
