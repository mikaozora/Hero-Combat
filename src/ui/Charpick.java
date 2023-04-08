package ui;

import entities.Dwarf;
import entities.Samurai;
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

    public void drawDetails(Graphics2D g2, Wizard wizard, Samurai samurai, Dwarf dwarf){
        drawWizardDetails(g2, wizard);
        drawSamuraiDetails(g2, samurai);
        drawDwarfDetails(g2, dwarf);
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
        g2.drawString("Skill 1:"+wizard.getSkills().get(1).getName(), 178, 470);
        g2.drawString("CD:"+wizard.getSkills().get(1).getCd()+"Turn", 178, 490);
        g2.drawString("Damage:+"+(wizard.getSkills().get(1).getDamage() - wizard.getAtk())+"ATK", 178, 510);
        g2.drawString("Skill 2:"+wizard.getSkills().get(2).getName(), 268, 470);
        g2.drawString("CD:"+wizard.getSkills().get(2).getCd()+"Turn", 268, 490);
        g2.drawString("Damage:+"+(wizard.getSkills().get(2).getDamage() - wizard.getAtk())+"ATK", 268, 510);
        g2.drawString("Skill 3:"+wizard.getSkills().get(3).getName(), 360, 470);
        g2.drawString("CD:"+wizard.getSkills().get(3).getCd()+"Turn", 360, 490);
        g2.drawString("Damage:+"+(wizard.getSkills().get(3).getDamage() - wizard.getAtk())+"ATK", 360, 510);
    }
    public void drawSamuraiDetails(Graphics2D g2, Samurai samurai){
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 16));
        g2.setColor(Color.white);
        g2.drawString("Samurai", 178+MARGIN, 425);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 14));
        g2.drawString("ATK:"+samurai.getAtk(), 178+MARGIN, 445);
        g2.drawString("DEF:"+samurai.getDef(), 250+MARGIN, 445);
        g2.drawString("HP:"+samurai.getHp(), 322+MARGIN, 445);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 10));
        g2.drawString("Skill 1:"+samurai.getSkills().get(1).getName(), 178+MARGIN, 470);
        g2.drawString("CD:"+samurai.getSkills().get(1).getCd()+"Turn", 178+MARGIN, 490);
        g2.drawString("Damage:+"+(samurai.getSkills().get(1).getDamage() - samurai.getAtk())+"ATK", 178+MARGIN, 510);
        g2.drawString("Skill 2:"+samurai.getSkills().get(2).getName(), 268+MARGIN, 470);
        g2.drawString("CD:"+samurai.getSkills().get(2).getCd()+"Turn", 268+MARGIN, 490);
        g2.drawString("Damage:+"+(samurai.getSkills().get(2).getDamage() - samurai.getAtk())+"ATK", 268+MARGIN, 510);
        g2.drawString("Skill 3:"+samurai.getSkills().get(3).getName(), 360+MARGIN, 470);
        g2.drawString("CD:"+samurai.getSkills().get(3).getCd()+"Turn", 360+MARGIN, 490);
        g2.drawString("Damage:+"+(samurai.getSkills().get(3).getDamage() - samurai.getHp())+"HP", 360+MARGIN, 510);
    }
    public void drawDwarfDetails(Graphics2D g2, Dwarf dwarf){
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 16));
        g2.setColor(Color.white);
        g2.drawString("Dwarf", 178+MARGIN*2, 425);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 14));
        g2.drawString("ATK:"+dwarf.getAtk(), 178+MARGIN*2, 445);
        g2.drawString("DEF:"+dwarf.getDef(), 250+MARGIN*2, 445);
        g2.drawString("HP:"+dwarf.getHp(), 322+MARGIN*2, 445);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 10));
        g2.drawString("Skill 1:"+dwarf.getSkills().get(1).getName(), 178+MARGIN*2, 470);
        g2.drawString("CD:"+dwarf.getSkills().get(1).getCd()+"Turn", 178+MARGIN*2, 490);
        g2.drawString("Damage:+"+(dwarf.getSkills().get(1).getDamage() - dwarf.getAtk())+"ATK", 178+MARGIN*2, 510);
        g2.drawString("Skill 2:"+dwarf.getSkills().get(2).getName(), 268+MARGIN*2, 470);
        g2.drawString("CD:"+dwarf.getSkills().get(2).getCd()+"Turn", 268+MARGIN*2, 490);
        g2.drawString("Damage:+"+(dwarf.getSkills().get(2).getDamage() - dwarf.getAtk())+"ATK", 268+MARGIN*2, 510);
        g2.drawString("Skill 3:"+dwarf.getSkills().get(3).getName(), 360+MARGIN*2, 470);
        g2.drawString("CD:"+dwarf.getSkills().get(3).getCd()+"Turn", 360+MARGIN*2, 490);
        g2.drawString("Damage:+"+(dwarf.getSkills().get(3).getDamage() - dwarf.getDef())+"DEF", 360+MARGIN*2, 510);
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
