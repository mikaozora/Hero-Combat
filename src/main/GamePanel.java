package main;

import inputs.KeyboardHandler;
import inputs.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    final int panelWidth = 1400;
    final int panelHeight = 750;
    private Game game;
    private MouseHandler mouseHandler;
    GamePanel(Game game) {
        this.addKeyListener(new KeyboardHandler(this));
        mouseHandler = new MouseHandler(this);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.game = game;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        game.render(g2);
    }

    public Game getGame(){
        return game;
    }

}
