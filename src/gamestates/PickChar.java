package gamestates;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PickChar extends States implements Statemethods{
    private BufferedImage bg, title;
    public PickChar(Game game) {
        super(game);
        loadBg();
        loadTitle();
    }

    @Override
    public void update() {

    }
    private void loadBg(){
        bg = LoadSave.getSprite(LoadSave.BACKGROUND_DEFAULT);
    }
    private void loadTitle(){
        title = LoadSave.getSprite(LoadSave.PICKCHAR_TITLE);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(bg, 0, 0, 1400, 750, null);
        g2.drawImage(title, 246, 44, 916, 101, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            GameStates.state = GameStates.PICKITEM;
        }
    }
}
