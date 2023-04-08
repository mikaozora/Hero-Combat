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
    private boolean mousePressed;
    private Rectangle bounds;

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
    }
    public void update(){
        index = 0;
        if(mousePressed){
            index = 1;
        }
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
}
