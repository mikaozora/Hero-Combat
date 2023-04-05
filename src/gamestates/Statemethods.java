package gamestates;

import inputs.MouseHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Statemethods {
    public void update();
    public void draw(Graphics2D g2);
    public void mouseClicked(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void keyPressed(KeyEvent e);
}
