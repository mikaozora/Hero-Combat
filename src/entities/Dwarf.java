package entities;

import audio.AudioPlayer;
import main.Game;
import utils.Constant;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.DwarfConstant.*;
import static utils.Constant.WizardConstant.HITTED;

public class Dwarf extends Entity{
    private BufferedImage[][] dwarf;
    private int aniTick, aniIndex, aniSpeed = 20;
    private int sumX = 5;
    private int width, height;

    public Dwarf(int x, int y, int width, int height, int player, Game game) {
        super(x, y, HP, ATK, DEF, player, game);
        initSkills();
        this.width = width;
        this.height = height;
        loadAnimations();
    }

    @Override
    protected void doSkill(int atk, int run) {

        if (atk == BASIC) getIdxAction = 0;
        else if (atk == ATT1) getIdxAction = 1;
        else if (atk == ATT2) getIdxAction = 2;
        else if (atk == ATT3) getIdxAction = 3;

        if (player == 1){
            if (atk == ATT3){
                action = atk;
                for (int i = 0; i < 10; i++){
                    action = HITTED;
                    if (i >= 5){
                        game.getAudioPlayer().playEffect(AudioPlayer.RAGE_DWARF);
                    }
                }
                if (!doneInc){
                    this.setDef(this.getDef() + 90);
                    doneInc = true;
                }
            }
            else if (x >= 750 && alreadyAtk == 0){
                action = atk;
                sumX*=-1;
                alreadyAtk = 1;
                needStop = 0;
            }
            else{
                if(action == atk && z != 50){
                    if (z >= 40){
                        enemy.attacked = true;
                        if (z == 43){
                            game.getAudioPlayer().playEffect(AudioPlayer.SLASH_3);
                        }
                        if (z == 49){
                            checkEnemy();
                        }
                    }
                    z++;
                    return;
                }
                if (x <= Constant.PlayerPosition.xPosP1 && needStop == 1){
                    x = Constant.PlayerPosition.xPosP1;
                    x-=sumX;
                    if (action == IDLE){
                        alreadyAtk = 0;
                        sumX *= -1;
                        z = 0;
                    }
                }
                action = run;
                x+=sumX;
                needStop = 1;
            }
        }else if(player == 2){
            if (atk == ATT3){
                action = atk;

                for (int i = 0; i < 10; i++){
                    action = HITTED;
                    if (i >= 5){
                        game.getAudioPlayer().playEffect(AudioPlayer.RAGE_DWARF);
                    }
                }

                if (!doneInc){
                    this.setDef(this.getDef() + 90);
                    doneInc = true;
                }
            }
            else if (x <= 680 && alreadyAtk == 0){
                action = atk;
                sumX*=-1;
                alreadyAtk = 1;
                needStop = 0;
            }
            else{
                if(action == atk && z != 50){
                    if (z >= 40){
                        enemy.attacked = true;
                        if (z == 43){
                            game.getAudioPlayer().playEffect(AudioPlayer.SLASH_3);
                        }
                        if (z == 49){
                            checkEnemy();
                        }
                    }
                    z++;
                    return;
                }
                if (x >= Constant.PlayerPosition.xPosP2 && needStop == 1){
                    x = Constant.PlayerPosition.xPosP2;
                    x-=sumX;
                    if (action == IDLE){
                        alreadyAtk = 0;
                        sumX *= -1;
                        z = 0;
                    }
                }
                action = run;
                x-=sumX;
                needStop = 1;
            }
        }
    }

    public void loadAnimations() {
        BufferedImage img = LoadSave.getSprite(LoadSave.DWARF);
        dwarf = new BufferedImage[8][6];
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
            doSkill(BASIC, RUN);
        }else if(attack1){
            doSkill(ATT1, RUN);
        }else if(attack2){
            doSkill(ATT2, RUN);
        } else if (attack3) {
            doSkill(ATT3, RUN);
        }else if(attacked){
            action = HITTED;
        }
        else {
            action = IDLE;
            doneInc = false;
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
        attacked = false;
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
