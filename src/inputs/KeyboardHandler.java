package inputs;

import gamestates.GameStates;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {
    GamePanel gp;
    public KeyboardHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameStates.state){
            case MENU:
                gp.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gp.getGame().getPlaying().keyPressed(e);
                break;
            case PICKCHAR:
                gp.getGame().getPickChar().keyPressed(e);
            case PICKITEM:
                gp.getGame().getPickItem().keyPressed(e);
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
