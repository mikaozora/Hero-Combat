package gamestates;

import entities.Dwarf;
import entities.Samurai;
import entities.Wizard;
import main.Game;
import ui.Charpick;
import ui.CharpickButton;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.CharpickBtn.*;
import static utils.Constant.*;

public class PickChar extends States implements Statemethods {
    private BufferedImage bg, title;
    private CharpickButton button;
    private Charpick[] chars = new Charpick[3];
    private int player = 1;
    private PlayerStates p1, p2, temp;
    private Wizard wizard;
    private Samurai samurai;
    private Dwarf dwarf;

    public PickChar(Game game) {
        super(game);
        initClasses();
        loadBg();
        loadTitle();
        loadChar();
        loadButton();
        p1 = PlayerStates.NULL;
        p2 = PlayerStates.NULL;
    }

    private void loadBg() {
        bg = LoadSave.getSprite(LoadSave.BACKGROUND_DEFAULT);
    }

    private void loadTitle() {
        title = LoadSave.getSprite(LoadSave.PICKCHAR_TITLE);
    }

    private void loadButton() {
        button = new CharpickButton(451 - MARGIN_LR, 613 - MARGIN_TB, 0, GameStates.PICKITEM);
    }


    private void loadChar() {
        chars[0] = new Charpick(169, 192, 0);
        chars[1] = new Charpick(549, 192, 1);
        chars[2] = new Charpick(929, 192, 2);
    }

    private void initClasses(){
        wizard = new Wizard(0, 0, WizardConstant.WIDTH, WizardConstant.HEIGHT, -1, game);
        samurai = new Samurai(0, 0, SamuraiConstant.WIDTH, SamuraiConstant.HEIGHT, -1, game);
        dwarf = new Dwarf(0, 0, DwarfConstant.WIDTH, DwarfConstant.HEIGHT, -1, game);
    }
    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(bg, 0, 0, 1400, 750, null);
        g2.drawImage(title, 246, 44, 916, 101, null);
        for (Charpick cp : chars) {
            cp.draw(g2);
        }
        new Charpick().drawDetails(g2, wizard, samurai, dwarf);
        g2.drawString("Player: " + player, 100, 100);
        button.draw(g2);
    }

    @Override
    public void update() {
        for (Charpick cp : chars) {
            cp.update();
        }
        button.update();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        button.setMouseOver(false);
        if (isInCb(button, e)) {
            button.setMouseOver(true);
        }
        for (Charpick cp : chars) {
            cp.setMouseOver(false);
        }
        for (Charpick cp : chars) {
            if (isInCp(cp, e)) {
                cp.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isInCb(button, e)) {
            button.setMousePressed(true);
        }
        for (Charpick cp : chars) {
            if (isInCp(cp, e)) {
                cp.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        temp = PlayerStates.NULL;
        for (Charpick cp : chars) {
            if (isInCp(cp, e)) {
                if (cp.isMousePressed()) {
                    if (cp.getBounds().getY() == 192) {
                        if (cp.getBounds().getX() == 169) {
                            chars[0].setMouseReleased(true);
                            chars[1].setMouseReleased(false);
                            chars[2].setMouseReleased(false);
                            temp = PlayerStates.WIZARD;
                        } else if (cp.getBounds().getX() == 549) {
                            chars[0].setMouseReleased(false);
                            chars[1].setMouseReleased(true);
                            chars[2].setMouseReleased(false);
                            temp = PlayerStates.SAMURAI;
                        } else if (cp.getBounds().getX() == 929) {
                            chars[0].setMouseReleased(false);
                            chars[1].setMouseReleased(false);
                            chars[2].setMouseReleased(true);
                            temp = PlayerStates.DWARF;
                        }
                    }
                    if(player == 1){
                        p1 = temp;
                    }else{
                        p2 = temp;
                    }
                }
                break;
            }
        }
        if (isInCb(button, e)) {
            if (button.isMousePressed()) {
                if (player == 2) {
                    if(p1 != PlayerStates.NULL && p2 != PlayerStates.NULL){
                        game.setPickItem(new PickItem(game, p1, p2));
                        button.applyGameState();
                    }
                }
                if(p1 != PlayerStates.NULL || p2 != PlayerStates.NULL){
                    chars[0].setMouseReleased(false);
                    chars[1].setMouseReleased(false);
                    chars[2].setMouseReleased(false);
                    player = 2;
                }
            }
        }
        resetButton();
    }

    private void resetButton() {
        button.resetBool();
        for (Charpick cp : chars) {
            cp.resetBool();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public PlayerStates getP1() {
        return p1;
    }

    public PlayerStates getP2() {
        return p2;
    }
}