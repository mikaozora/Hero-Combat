package ui;

import gamestates.GameStates;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utils.Constant.UI.MenuBtn.*;

public class MenuButton {
    private int x, y, rowIndex, index;
    private GameStates state;
    private BufferedImage[] img;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public MenuButton(int x, int y, int rowIndex, GameStates state) {
        this.x = x;
        this.y = y;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImg();
        initBounds();
    }


    private void initBounds() {
        bounds = new Rectangle(x, y, 221, 78);
    }

    private void loadImg() {
        img = new BufferedImage[2];
        BufferedImage temp = LoadSave.getSprite(LoadSave.MENU_BUTTON);
        for (int i = 0; i < img.length; i++) {
            img[i] = temp.getSubimage(i * WIDTH_BUTTON, rowIndex * HEIGHT_BUTTON, WIDTH_BUTTON, HEIGHT_BUTTON);
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(img[index], x, y, WIDTH_BUTTON, HEIGHT_BUTTON, null);
    }

    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        }
        if(mousePressed){
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

    public Rectangle getBounds() {
        return bounds;
    }
    public void applyGameState(){
        GameStates.state = state;
    }

    public void resetBool(){
        mouseOver = false;
        mousePressed = false;
    }

    public GameStates getState(){
        return state;
    }
}
