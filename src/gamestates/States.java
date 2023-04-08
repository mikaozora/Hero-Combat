package gamestates;

import main.Game;
import ui.*;
import ui.Charpick;
import ui.MapsImg;
import utils.Constant;

import java.awt.event.MouseEvent;
import static utils.Constant.UI.*;

import static utils.Constant.UI.MenuBtn.*;
import static utils.Constant.UI.MapsBtn.*;

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

    public boolean isIn(MapsButton mb, MouseEvent e){
        return mb.getBounds().contains(e.getX() - MARGIN_LR_MAP, e.getY() - MARGIN_TB_MAP);
    }

    public boolean isIn(MapsImg mb, MouseEvent e){
        return mb.getBounds().contains(e.getX(), e.getY());
    }

//    public boolean isInSkill1(PlayingSkillButton mb,MouseEvent e){
//        return mb.getBounds1().contains(e.getX(), e.getY());
//    }
//
//    public boolean isInSkill2(PlayingSkillButton mb,MouseEvent e){
//        return mb.getBounds2().contains(e.getX(), e.getY());
//    }
//
//    public boolean isInUlt(PlayingSkillButton mb,MouseEvent e){
//        return mb.getBoundsUlt().contains(e.getX(), e.getY());
//    }
//
//    public boolean isInBasic(PlayingSkillButton mb,MouseEvent e){
//        return mb.getBoundBasic().contains(e.getX(), e.getY());
//    }

    public Game getGame() {
        return game;
    }
}
