package ui;

import utils.Constant.*;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayingSkillButton {
    int xBasicAtk, yBasicAtk, xSkill1, ySkill1, xSkill2, ySkill2, xUlt, yUlt, index;
    BufferedImage basicAtk, skill1, skill2, ultimate;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds1, bounds2, boundsUlt, boundBasic;
    public PlayingSkillButton(int xBasicAtk, int yBasicAtk, int xSkill1, int ySkill1, int xSkill2, int ySkill2, int xUlt, int yUlt){
        this.xBasicAtk = xBasicAtk;
        this.yBasicAtk = yBasicAtk;

        this.xSkill1 = xSkill1;
        this.ySkill1 = ySkill1;

        this.xSkill2 = xSkill2;
        this.ySkill2 = ySkill2;

        this.xUlt = xUlt;
        this.yUlt = yUlt;
//        initBounds();
    }

    public void wizardSkill(){
        basicAtk = LoadSave.getSprite(LoadSave.BASIC_ATTACK);
        skill1 = LoadSave.getSprite(LoadSave.WIZARD_SKILL1);
        skill2 = LoadSave.getSprite(LoadSave.WIZARD_SKILL2);
        ultimate = LoadSave.getSprite(LoadSave.WIZARD_ULT);
    }

    public void warriorSkill(){
        basicAtk = LoadSave.getSprite(LoadSave.BASIC_ATTACK);
        skill1 = LoadSave.getSprite(LoadSave.WARRIOR_SKILL1);
        skill2 = LoadSave.getSprite(LoadSave.WARRIOR_SKILL2);
        ultimate = LoadSave.getSprite(LoadSave.WARRIOR_ULT);
    }

    public void samuraiSKill(){
        basicAtk = LoadSave.getSprite(LoadSave.BASIC_ATTACK);
        skill1 = LoadSave.getSprite(LoadSave.SAMURAI_SKILL1);
        skill2 = LoadSave.getSprite(LoadSave.SAMURAI_SKILL2);
        ultimate = LoadSave.getSprite(LoadSave.SAMURAI_ULT);
    }

    public void draw(Graphics2D g2){
        g2.drawImage(basicAtk, xBasicAtk, yBasicAtk, null);
        g2.drawImage(skill1, xSkill1, ySkill1, null);
        g2.drawImage(skill2, xSkill2, ySkill2, null);
        g2.drawImage(ultimate, xUlt, yUlt, null);
    }

    private void initBounds(){
        bounds1 = new Rectangle(xSkill1, ySkill1,89, 89);
        bounds2 = new Rectangle(xSkill2, ySkill2,89, 89);
        boundsUlt = new Rectangle(xUlt, yUlt,89, 89);
        boundBasic = new Rectangle(xBasicAtk, yBasicAtk,89, 89);
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

//    public Rectangle getBounds() {
//        return bounds;
//    }


    public Rectangle getBounds1() {
        return bounds1;
    }

    public Rectangle getBounds2() {
        return bounds2;
    }

    public Rectangle getBoundsUlt() {
        return boundsUlt;
    }

    public Rectangle getBoundBasic() {
        return boundBasic;
    }

    public void resetBool(){
        mouseOver = false;
        mousePressed = false;
    }
}
