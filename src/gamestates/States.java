package gamestates;

import main.Game;
import ui.MenuButton;

import java.awt.event.MouseEvent;
import static utils.Constant.UI.MenuBtn.*;

public class States {
    protected Game game;
    public States(Game game){
        this.game = game;
    }


    public boolean isIn(MenuButton mb, MouseEvent e){
        return mb.getBounds().contains(e.getX() - MARGIN_LR, e.getY() - MARGIN_TB);
    }
    public Game getGame() {
        return game;
    }
}
