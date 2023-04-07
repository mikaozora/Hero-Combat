package gamestates;

import main.Game;
import ui.Charpick;
import ui.CharpickButton;
import ui.MenuButton;

import java.awt.event.MouseEvent;
import static utils.Constant.UI.*;


public class States {
    protected Game game;
    public States(Game game){
        this.game = game;
    }


    public boolean isInMb(MenuButton mb, MouseEvent e){
        return mb.getBounds().contains(e.getX() - MenuBtn.MARGIN_LR, e.getY() - MenuBtn.MARGIN_TB);
    }

    public boolean isInCb(CharpickButton cb, MouseEvent e){
        return cb.getBounds().contains(e.getX() - CharpickBtn.MARGIN_LR, e.getY() - CharpickBtn.MARGIN_TB);
    }
    public boolean isInCp(Charpick cp, MouseEvent e){
        return cp.getBounds().contains(e.getX(), e.getY());
    }
    public Game getGame() {
        return game;
    }
}
