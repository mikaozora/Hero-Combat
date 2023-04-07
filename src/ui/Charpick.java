package ui;

import entities.Wizard;
import gamestates.GameStates;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.Charpick.*;

public class Charpick {
    private int x,y, rowIndex, index;
    private GameStates state;
    private BufferedImage[] img;
    private boolean mouseOver, mousePressed, mouseReleased;
    private Rectangle bounds;


    public Charpick(int x, int y, int rowIndex) {
        this.x = x;
        this.y = y;
        this.rowIndex = rowIndex;
        initBounds();
        loadImg();
    }

    public Charpick() {
    }

    private void initBounds() {
        bounds = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    private void loadImg() {
        img = new BufferedImage[2];
        BufferedImage temp = LoadSave.getSprite(LoadSave.PICKCHAR_CHAR);
        for (int i = 0; i < img.length; i++) {
            img[i] = temp.getSubimage(i * WIDTH, rowIndex * HEIGHT, WIDTH, HEIGHT);
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(img[index], x, y, WIDTH, HEIGHT, null);
    }

    public void drawDetails(Graphics2D g2, Wizard wizard){
        drawWizardDetails(g2, wizard);
        drawSamuraiDetails(g2);
        drawDwarfDetails(g2);
    }

    public void drawWizardDetails(Graphics2D g2, Wizard wizard){
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 16));
        g2.setColor(Color.white);
        g2.drawString("Wizard", 178, 425);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 14));
        g2.drawString("ATK:"+wizard.getAtk(), 178, 445);
        g2.drawString("DEF:"+wizard.getDef(), 250, 445);
        g2.drawString("HP:"+wizard.getHp(), 322, 445);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 10));
        g2.drawString("Skill 1:Prick", 178, 470);
        g2.drawString("CD:1Turn", 178, 490);
        g2.drawString("Damage:+50ATK", 178, 510);
        g2.drawString("Skill 2:Fireball", 268, 470);
        g2.drawString("CD:2Turn", 268, 490);
        g2.drawString("Damage:+50ATK", 268, 510);
        g2.drawString("Skill 2:Fireball", 360, 470);
        g2.drawString("CD:2Turn", 360, 490);
        g2.drawString("Damage:+50ATK", 360, 510);
    }
    public void drawSamuraiDetails(Graphics2D g2){
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 16));
        g2.setColor(Color.white);
        g2.drawString("Samurai", 178+MARGIN, 425);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 14));
        g2.drawString("ATK:"+500, 178+MARGIN, 445);
        g2.drawString("DEF:"+500, 250+MARGIN, 445);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 10));
        g2.drawString("Skill 1:Prick", 178+MARGIN, 470);
        g2.drawString("CD:1Turn", 178+MARGIN, 490);
        g2.drawString("Damage:+50ATK", 178+MARGIN, 510);
        g2.drawString("Skill 2:Fireball", 268+MARGIN, 470);
        g2.drawString("CD:2Turn", 268+MARGIN, 490);
        g2.drawString("Damage:+50ATK", 268+MARGIN, 510);
        g2.drawString("Skill 2:Fireball", 360+MARGIN, 470);
        g2.drawString("CD:2Turn", 360+MARGIN, 490);
        g2.drawString("Damage:+50ATK", 360+MARGIN, 510);
    }
    public void drawDwarfDetails(Graphics2D g2){
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 16));
        g2.setColor(Color.white);
        g2.drawString("Dwarf", 178+MARGIN*2, 425);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 14));
        g2.drawString("ATK:"+500, 178+MARGIN*2, 445);
        g2.drawString("DEF:"+500, 250+MARGIN*2, 445);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 10));
        g2.drawString("Skill 1:Prick", 178+MARGIN*2, 470);
        g2.drawString("CD:1Turn", 178+MARGIN*2, 490);
        g2.drawString("Damage:+50ATK", 178+MARGIN*2, 510);
        g2.drawString("Skill 2:Fireball", 268+MARGIN*2, 470);
        g2.drawString("CD:2Turn", 268+MARGIN*2, 490);
        g2.drawString("Damage:+50ATK", 268+MARGIN*2, 510);
        g2.drawString("Skill 2:Fireball", 360+MARGIN*2, 470);
        g2.drawString("CD:2Turn", 360+MARGIN*2, 490);
        g2.drawString("Damage:+50ATK", 360+MARGIN*2, 510);
    }

    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        }
        if(mousePressed){
            index = 1;
        }
        if(mouseReleased){
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

    public void resetBool(){
        mouseOver = false;
        mousePressed = false;
    }
}
