package ui;

import gamestates.GameStates;
import gamestates.Playing;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.PauseBtn.MARGIN_LR;
import static utils.Constant.UI.PauseBtn.MARGIN_TB;

public class GameoverOverlay {
    private Playing playing;
    private BufferedImage bg;
    private UrmButton restartBtn, menuBtn;

    public GameoverOverlay(Playing playing) {
        this.playing = playing;
        loadBg();
        loadBtn();
    }

    private void loadBg() {
        bg = LoadSave.getSprite(LoadSave.BG_P1WIN);
//        if(playing.getGameOver() == 0){
//            bg = LoadSave.getSprite(LoadSave.BG_P1WIN);
//        }else if(playing.getGameOver() == 1){
//            bg = LoadSave.getSprite(LoadSave.BG_P2WIN);
//        }
    }

    private void loadBtn() {
        restartBtn = new UrmButton(549 - MARGIN_LR, 309 - MARGIN_TB, 1);
        menuBtn = new UrmButton(549 - MARGIN_LR, 439 - MARGIN_TB, 2);
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(bg, 426, 158, 548, 431, null);
        restartBtn.draw(g2);
        menuBtn.draw(g2);
    }

    public void update() {
        if(playing.getGameOver() == 0){
            bg = LoadSave.getSprite(LoadSave.BG_P1WIN);
        }else if(playing.getGameOver() == 1){
            bg = LoadSave.getSprite(LoadSave.BG_P2WIN);
        }
        restartBtn.update();
        menuBtn.update();
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        restartBtn.setMouseOver(false);
        menuBtn.setMouseOver(false);
        if (playing.isInPb(restartBtn, e)) {
            restartBtn.setMouseOver(true);
        } else if (playing.isInPb(menuBtn, e)) {
            menuBtn.setMouseOver(true);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (playing.isInPb(restartBtn, e)) {
            restartBtn.setMousePressed(true);
        } else if (playing.isInPb(menuBtn, e)) {
            menuBtn.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (playing.isInPb(restartBtn, e)) {
            if (restartBtn.isMousePressed()) {
                playing.rematch();
            }
        } else if (playing.isInPb(menuBtn, e)) {
            if (menuBtn.isMousePressed()) {
                GameStates.state = GameStates.MENU;
                playing.unPauseGame();
            }
        }
        restartBtn.resetBool();
        menuBtn.resetBool();
    }
}
