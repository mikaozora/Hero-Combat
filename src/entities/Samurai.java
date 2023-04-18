package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.SamuraiConstant.*;

public class Samurai extends Entity{
    private BufferedImage[][] samurai;
    private int aniTick, aniIndex, aniSpeed = 20;
    private int action = DEAD;
    private int width, height;
    private boolean attack3 = false;
    public Samurai(int x, int y, int width, int height) {
        super(x, y, HP, ATK, DEF);
        initSkills();
        this.width = width;
        this.height = height;
        loadAnimations();
    }
    public void loadAnimations(){
        BufferedImage img = LoadSave.getSprite(LoadSave.SAMURAI);
        samurai = new BufferedImage[8][8];
        for (int j = 0; j < samurai.length; j++) {
            for (int i = 0; i < samurai[j].length; i++) {
                samurai[j][i] = img.getSubimage(128 * i, 128 * j, 128, 128);
            }
        }
    }
    public void update(){
        setAnimation();
        updateAniTick();
    }
    public void render(Graphics2D g2){
        g2.drawImage(samurai[action][aniIndex], x, y, width, height, null);
    }
    void updateAniTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(action)) {
                aniIndex = 0;
                resetAction();
            }
        }
    }
    void setAnimation() {
        int startAni = action;

        if (basic){
            action = BASIC;
        }else if(attack1){
            action = ATT1;
        }else if(attack2){
            action = ATT2;
        } else if (attack3) {
            action = ATT3;
        } else {
            action = IDLE;
        }

        if (startAni != action) {
            resetTick();
        }
    }
    private void resetTick() {
        aniTick = 0;
        aniIndex = 0;
    }
    private void resetAction(){
        attack3 = false;
        attack1 = false;
        attack2 = false;
        basic = false;
    }
    void initSkills(){
        skills.add(new Skill("Basic", 0, atk));
        skills.add(new Skill("Slash", 1, atk+50));
        skills.add(new Skill("Slice", 2, atk+70));
        skills.add(new Skill("Heal", 3, hp+90));
    }
    public void setAttack3(boolean attack3) {
        this.attack3 = attack3;
    }
    public void setAttack2(boolean attack2) {
        this.attack2 = attack2;
    }

    @Override
    public void setAttack1(boolean attack1) {
        this.attack1 = attack1;
    }

    @Override
    public void setBasic(boolean basic) {
        this.basic = basic;
    }
    public boolean isAttack3() {
        return this.attack3;
    }
}
