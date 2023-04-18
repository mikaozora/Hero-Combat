package ui;

import gamestates.GameStates;
import gamestates.Playing;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static utils.Constant.UI.PauseBtn.*;

public class PauseOverlay {
    private Playing playing;
    private BufferedImage bg;
    private UrmButton resumeBtn, restartBtn, menuBtn;
    public PauseOverlay(Playing playing){
        this.playing = playing;
        loadBg();
        loadBtn();
    }

    private void loadBg(){
        bg = LoadSave.getSprite(LoadSave.BG_PAUSE);
    }
    private void loadBtn(){
        resumeBtn = new UrmButton(540 - MARGIN_LR, 278 - MARGIN_TB, 0);
        restartBtn = new UrmButton(540 - MARGIN_LR, 408 - MARGIN_TB, 1);
        menuBtn = new UrmButton(540 - MARGIN_LR, 538 - MARGIN_TB, 2);
    }

    public void draw(Graphics2D g2){
        g2.drawImage(bg, 426, 141, 548, 519, null);
        resumeBtn.draw(g2);
        restartBtn.draw(g2);
        menuBtn.draw(g2);
    }
    public void update(){
        resumeBtn.update();
        restartBtn.update();
        menuBtn.update();
    }
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        resumeBtn.setMouseOver(false);
        restartBtn.setMouseOver(false);
        menuBtn.setMouseOver(false);
        if(playing.isInPb(resumeBtn, e)){
            resumeBtn.setMouseOver(true);
        }else if(playing.isInPb(restartBtn, e)){
            restartBtn.setMouseOver(true);
        }else if(playing.isInPb(menuBtn, e)){
            menuBtn.setMouseOver(true);
        }
    }

    public void mousePressed(MouseEvent e) {
        if(playing.isInPb(resumeBtn, e)){
            resumeBtn.setMousePressed(true);
        }else if(playing.isInPb(restartBtn, e)){
            restartBtn.setMousePressed(true);
        }else if(playing.isInPb(menuBtn, e)){
            menuBtn.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if(playing.isInPb(resumeBtn, e)){
            if(resumeBtn.isMousePressed()){
                playing.unPauseGame();
            }
        }else if(playing.isInPb(restartBtn, e)){
            if(restartBtn.isMousePressed()){
                playing.rematch();
                playing.setPause(!playing.isPause());
            }
        }else if(playing.isInPb(menuBtn, e)){
            if(menuBtn.isMousePressed()){
                GameStates.state = GameStates.MENU;
                playing.unPauseGame();
            }
        }
        resumeBtn.resetBool();
        restartBtn.resetBool();
        menuBtn.resetBool();
    }

}
