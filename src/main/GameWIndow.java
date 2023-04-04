package main;

import javax.swing.*;

public class GameWIndow extends JFrame {

    public GameWIndow(GamePanel gp){
        this.add(gp);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
