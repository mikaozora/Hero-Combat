package inputs;

import gamestates.GameStates;
import main.Game;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
    GamePanel gp;
    public MouseHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameStates.state){
            case MENU:
                gp.getGame().getMenu().mouseClicked(e);
                break;
            case PLAYING:
                gp.getGame().getPlaying().mouseClicked(e);
                break;
            case PICKCHAR:
                gp.getGame().getPickChar().mouseClicked(e);
                break;
            case PICKITEM:
                gp.getGame().getPickItem().mouseClicked(e);
                break;
            case PICKMAP:
                gp.getGame().getPickMap().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
