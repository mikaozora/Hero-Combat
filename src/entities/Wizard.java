package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utils.Constant.WizardConstant.*;
import static utils.Constant.WizardConstant.getSpriteAmount;

public class Wizard extends Entity {
    private BufferedImage[][] hero2;
    private int aniTick, aniIndex, aniSpeed = 20;
    private int action = IDLE;
    private boolean attack3 = false, attack2 = false, attack1 = false, basicAtk = false;
    private int width, height;

    public Wizard(int x, int y, int width, int height) {
        super(x, y, 500, 50, 50);
        initSkills();
        this.width = width;
        this.height = height;
        loadAnimations();
    }


    public void update() {
        setAnimation();
        updateAniTick();
    }

    public void render(Graphics2D g2) {
        g2.drawImage(hero2[action][aniIndex], x, y, width, height, null);
    }

    public void loadAnimations() {
        BufferedImage img = LoadSave.getSprite(LoadSave.WIZARD);
        hero2 = new BufferedImage[7][14];
        for (int j = 0; j < hero2.length; j++) {
            for (int i = 0; i < hero2[j].length; i++) {
                hero2[j][i] = img.getSubimage(128 * i, 128 * j, 128, 128);
            }
        }
    }
    void initSkills(){
        skills.add(new Skill("Basic", 0, atk));
        skills.add(new Skill("Prick", 1, atk+50));
        skills.add(new Skill("Fireball", 2, atk+70));
        skills.add(new Skill("Fire jet", 3, atk+90));
    }

    public void runP1(){
        while(x < 1265){
            for (int i = 0; i < 200; i++) {
//                System.out.println(".");
            }
            x++;
        }
    }

    void updateAniTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(action)) {
                aniIndex = 0;
                attack3 = false;
            }
        }
    }

    void setAnimation() {
        int startAni = action;

        if (basicAtk){
//            action = RUN;

            action = BASIC;
//            runBackP1();
        }else if(attack1){
//            action = RUN;
//            runP1();
//            x+=1;
            action = ATT1;
//            runBackP1();
        }else if(attack2){
//            action = RUN;
//            runP1();
//            x+=1;
            action = ATT2;
//            runBackP1();
        } else if (attack3) {

            action = RUN;
            runP1();
            action = ATT3;
//            runBackP1();
        } else {
            action = IDLE;
//            runP1();
        }


        if (startAni != action) {
            resetTick();
        }
    }

    private void resetTick() {
        aniTick = 0;
        aniIndex = 0;
    }


    public void setAttack3(boolean attack3) {
        this.attack3 = attack3;
    }

    public void setAttack2(boolean attack2) {
        this.attack2 = attack2;
    }

    public void setAttack1(boolean attack1) {
        this.attack1 = attack1;
    }

    public void setBasicAtk(boolean basicAtk) {
        this.basicAtk = basicAtk;
    }
}
