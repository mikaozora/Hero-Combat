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
    private boolean attack3 = false;
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

    void loadAnimations() {
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
        if (attack3) {
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



    public void setattack3(boolean attack3) {
        this.attack3 = attack3;
    }


}
