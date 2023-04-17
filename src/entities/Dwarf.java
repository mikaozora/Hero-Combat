package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.DwarfConstant.*;

public class Dwarf extends Entity{
    private BufferedImage[][] dwarf;
    private int aniTick, aniIndex, aniSpeed = 20;
    private int action = IDLE;
    private int width, height;

    public Dwarf(int x, int y, int width, int height) {
        super(x, y, 600, 40, 50);
        initSkills();
        this.width = width;
        this.height = height;
        loadAnimations();
    }
    public void loadAnimations() {
        BufferedImage img = LoadSave.getSprite(LoadSave.DWARF);
        dwarf = new BufferedImage[7][6];
        for (int j = 0; j < dwarf.length; j++) {
            for (int i = 0; i < dwarf[j].length; i++) {
                dwarf[j][i] = img.getSubimage(96 * i, 96 * j, 96, 96);
            }
        }
    }
    public void update() {
        setAnimation();
        updateAniTick();
    }

    public void render(Graphics2D g2) {
        g2.drawImage(dwarf[action][aniIndex], x, y, width, height, null);
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
        skills.add(new Skill("Execute", 1, atk+50));
        skills.add(new Skill("Fury", 2, atk+70));
        skills.add(new Skill("Rage", 3, def+90));
    }


    public void setAttack3(boolean attack3) {
        this.attack3 = attack3;
    }

    @Override
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

    @Override
    public boolean isAttack3() {
        return this.attack3;
    }
}
