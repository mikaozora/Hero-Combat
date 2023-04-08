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
        super(x, y, 400, 60, 50);
        initSkills();
        this.width = width;
        this.height = height;
        loadAnimations();
    }
    public void loadAnimations(){
        BufferedImage img = LoadSave.getSprite(LoadSave.SAMURAI);
        samurai = new BufferedImage[7][8];
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
    void initSkills(){
        skills.add(new Skill("Basic", 0, atk));
        skills.add(new Skill("Slash", 1, atk+50));
        skills.add(new Skill("Slice", 2, atk+70));
        skills.add(new Skill("Heal", 3, hp+90));
    }
    public void setAttack3(boolean attack3) {
        this.attack3 = attack3;
    }
}
