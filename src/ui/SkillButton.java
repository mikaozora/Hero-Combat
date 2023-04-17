package ui;

import entities.Dwarf;
import entities.Entity;
import entities.Samurai;
import entities.Wizard;
import gamestates.GameStates;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.SkillButton.*;

public class SkillButton {
    private int x, y, rowIndex, index;
    private Entity entity;
    private int action;
    private BufferedImage[] img;
    private boolean mousePressed, mouseReleased;
    private Rectangle bounds;
    private int skillIndex;

    public SkillButton(int x, int y, int rowIndex, Entity entity, int action) {
        this.x = x;
        this.y = y;
        this.rowIndex = rowIndex;
        this.entity = entity;
        this.action = action;
        initBounds();
        loadImg();
    }

    private void initBounds(){
        bounds = new Rectangle(x, y, SIZE, SIZE);
    }
    private void loadImg(){
        img = new BufferedImage[2];
        BufferedImage temp = null;
        if(entity instanceof Dwarf){
            temp = LoadSave.getSprite(LoadSave.DWARF_SKILL);
        }else if(entity instanceof Samurai){
            temp = LoadSave.getSprite(LoadSave.SAMURAI_SKILL);
        }else if(entity instanceof Wizard){
            temp = LoadSave.getSprite(LoadSave.WIZARD_SKILL);
        }
        for (int i = 0; i < img.length; i++) {
            img[i] = temp.getSubimage(i * SIZE, rowIndex * SIZE, SIZE, SIZE);
        }
    }
    public void draw(Graphics2D g2){
        g2.drawImage(img[index], x, y, SIZE, SIZE, null);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 26));
        if(mouseReleased){
            if(action != 0){
                if(entity.getSkills().get(action).getCd() > 0){
                    g2.drawString(""+entity.getSkills().get(action).getCd(), x+38, y+55);
                }
            }
        }
    }
    public void update(){
        index = 0;
        if(mousePressed){
            index = 1;
        }
        if(mouseReleased){
            index = 1;
        }
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void setMouseReleased(boolean mouseReleased) {
        this.mouseReleased = mouseReleased;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public void applyAction(){
        switch (action){
            case 0 -> entity.setBasic(true);
            case 1 -> entity.setAttack1(true);
            case 2 -> entity.setAttack2(true);
            case 3 -> entity.setAttack3(true);
        }
    }
    public void resetAni(){
        entity.setBasic(false);
        entity.setAttack1(false);
        entity.setAttack2(false);
        entity.setAttack3(false);
    }

    public void resetBool(){
        mousePressed = false;
    }

    public int getAction() {
        return action;
    }

    public boolean isMouseReleased() {
        return mouseReleased;
    }
}
