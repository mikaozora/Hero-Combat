package ui;

import gamestates.GameStates;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.MapsImg.*;

public class MapsImg {
    private int x, y, rowIndex, index;
    private GameStates state;
    private BufferedImage[] img;
    private boolean mouseOver, mousePressed, mouseReleased;
    private Rectangle bounds;

    public MapsImg(int x, int y, int rowIndex) {
        this.x = x;
        this.y = y;
        this.rowIndex = rowIndex;
        loadImg();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(x, y, 282, 343);
    }

    private void loadImg() {
        img = new BufferedImage[2];
        BufferedImage temp = LoadSave.getSprite(LoadSave.MAPS_IMG);
        for (int i = 0; i < img.length; i++) {
            img[i] = temp.getSubimage(i * WIDTH_IMAGE, rowIndex * HEIGHT_IMAGE, WIDTH_IMAGE, HEIGHT_IMAGE);
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(img[index], x, y, WIDTH_IMAGE, HEIGHT_IMAGE, null);
    }


    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        }
        if(mousePressed){
            index = 1;
        }
        if (mouseReleased){
            index = 1;
        }
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public boolean isMouseReleased() {
        return mouseReleased;
    }

    public void setMouseReleased(boolean mouseReleased) {
        this.mouseReleased = mouseReleased;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public void applyMapState(){
        GameStates.state = state;
    }

    public void resetBool(){
        mouseOver = false;
        mousePressed = false;
    }
}
