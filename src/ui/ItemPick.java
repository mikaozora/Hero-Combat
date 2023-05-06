package ui;

import gamestates.GameStates;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.Charpick.MARGIN;
import static utils.Constant.UI.SkillButton.ItemImg.HEIGHT_IMAGE;
import static utils.Constant.UI.SkillButton.ItemImg.WIDTH_IMAGE;

public class ItemPick {
    private int x,y,rowIndex,index;
    private GameStates state;
    private BufferedImage[] img;
    private boolean mouseOver, mousePressed,mouseReleased;
    private Rectangle bounds;

    public ItemPick(int x, int y,int rowIndex){
        this.x = x;
        this.y = y;
        this.rowIndex = rowIndex;
        loadImg();
        initBounds();
    }
    public ItemPick(){

    }

    private void initBounds() {
        bounds = new Rectangle(x,y,282,343);
    }

    private void loadImg() {
        img = new BufferedImage[2];
        BufferedImage temp  = LoadSave.getSprite(LoadSave.PICKITEM_ITEM);
        for (int i = 0; i < img.length; i++){
            img[i] = temp.getSubimage(i*WIDTH_IMAGE , rowIndex*HEIGHT_IMAGE,WIDTH_IMAGE,HEIGHT_IMAGE);
        }
    }
    public void draw(Graphics2D g2){
        g2.drawImage(img[index],x,y,WIDTH_IMAGE,HEIGHT_IMAGE,null);
    }
    public void drawDetails(Graphics2D g2){
        drawShield(g2);
        drawSword(g2);
    }

    private void drawShield(Graphics2D g2) {
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 16));
        g2.setColor(Color.white);
        g2.drawString("Shield of Justice", 20+MARGIN, 515);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 14));
        g2.drawString("DEF:"+80, 20+MARGIN, 540);
    }

    private void drawSword(Graphics2D g2) {
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 16));
        g2.setColor(Color.white);
        g2.drawString("King Edward Sword", 20+MARGIN*2, 515);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 14));
        g2.drawString("ATK:"+80, 20+MARGIN*2, 540);
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
    public void resetBool(){
        mouseOver = false;
        mousePressed = false;
    }
}