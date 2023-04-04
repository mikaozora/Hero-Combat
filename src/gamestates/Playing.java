package gamestates;

import entities.Wizard;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends States implements Statemethods{
    private Wizard wizard;

    public Playing(Game game) {
        super(game);
        initClasses();
    }


    private void initClasses(){
        wizard = new Wizard(10, 10);
    }

    public Wizard getWizard(){
        return wizard;
    }

    @Override
    public void update() {
        wizard.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        wizard.render(g2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            wizard.setattack3(true);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_P){
            GameStates.state = GameStates.MENU;
        }
    }
}
