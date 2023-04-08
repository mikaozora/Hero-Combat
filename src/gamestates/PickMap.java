package gamestates;

import main.Game;
import ui.MapsButton;
import ui.MapsImg;
import utils.Constant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.IOException;
import java.io.InputStream;
import static utils.Constant.UI.MapsBtn.*;

public class PickMap extends States implements Statemethods{

    BufferedImage bgImg, h1Img;
    MapsButton chooseMapBtn;
    MapsImg MapsImg[] = new MapsImg[2];
    MapStates temp, mapGame = MapStates.NULL;
    PlayerStates p1, p2;

    public PickMap(Game game, PlayerStates p1, PlayerStates p2) {
        super(game);
        this.p1 = p1;
        this.p2 = p2;
        importImg();
        loadBtn();
        loadImg();
    }

    private void importImg() {
        InputStream bg = getClass().getResourceAsStream("/bg1.png");

        try {
            bgImg = ImageIO.read(bg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InputStream h1 = getClass().getResourceAsStream("/chooseMapsTxt.png");
        try {
            h1Img = ImageIO.read(h1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadBtn(){
        chooseMapBtn = new MapsButton(510 - MARGIN_LR_MAP, 620 - MARGIN_TB_MAP, 1, GameStates.PLAYING);
    }

    private void loadImg(){
        MapsImg[0] = new MapsImg(380, 220, 0);
        MapsImg[1] = new MapsImg(750, 220, 1);
    }

    @Override
    public void update() {
        chooseMapBtn.update();
        for (MapsImg map: MapsImg) {
            map.update();
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        g2.drawImage(bgImg, 0, 0, null);
//        g2.setColor(Color.black);
        g2.drawImage(h1Img, 410, 60, null);
//        g2.drawImage(almoMaps, 380, 220, null);
//        g2.drawImage(kingsMaps, 750, 220, null);
        for (MapsImg map: MapsImg) {
            map.draw(g2);
        }
//        g2.setColor(Color.red);
        chooseMapBtn.draw(g2);
    }



    @Override
    public void mouseClicked(MouseEvent e) {
//        if(e.getButton() == MouseEvent.BUTTON1){
//            GameStates.state = GameStates.PLAYING;
//        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        chooseMapBtn.setMouseOver(false);

        if (isIn(chooseMapBtn, e)){
            chooseMapBtn.setMouseOver(true);
        }

        for (MapsImg mb :
                MapsImg) {
            mb.setMouseOver(false);
        }

        for (MapsImg mb :
                MapsImg) {
            if (isIn(mb, e)){
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isIn(chooseMapBtn, e)){
            chooseMapBtn.setMousePressed(true);
        }

        for (MapsImg mb :
                MapsImg) {
            if(isIn(mb, e)){
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        temp = MapStates.NULL;
        for (MapsImg mb :
                MapsImg) {
            if(isIn(mb, e)){
                if (mb.isMousePressed()){
                    if(mb.getBounds().getY() == 220){
                        if(mb.getBounds().getX() == 380){
                            MapsImg[0].setMouseReleased(true);
                            MapsImg[1].setMouseReleased(false);
                            System.out.println(temp);
                            temp = MapStates.ALMO_GARDEN;
                        }else if(mb.getBounds().getX() == 750){
                            MapsImg[0].setMouseReleased(false);
                            MapsImg[1].setMouseReleased(true);
                            System.out.println(temp);
                            temp = MapStates.KING_GARDEN;
                        }
                    }
                    mapGame = temp;
                }
                break;
            }
        }

        if(isIn(chooseMapBtn, e)){
            if (chooseMapBtn.isMousePressed()){
                if (mapGame != MapStates.NULL){
                    game.setPlaying(new Playing(game, p1, p2, mapGame));
                    chooseMapBtn.applyGameState();
                }
            }
        }

        resetButton();
    }

    private void resetButton() {
        chooseMapBtn.resetBool();
        for (MapsImg mb :
                MapsImg) {
            mb.resetBool();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}