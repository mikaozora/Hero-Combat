package entities;

import audio.AudioPlayer;
import gamestates.ItemStates;
import gamestates.PlayerStates;
import main.Game;
import utils.Constant;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;

import static utils.Constant.SamuraiConstant.*;
import static utils.Constant.WizardConstant.HITTED;

public class Samurai extends Entity{
    private BufferedImage[][] samurai;
    private int aniTick, aniIndex, aniSpeed = 20;
//    private int action = DEAD;
    private int width, height;
    private boolean attack3 = false;
    public Samurai(int x, int y, int width, int height, int player, Game game, ItemStates item) {
        super(x, y, HP, ATK, DEF, player, game, item);
        initSkills();
        this.width = width;
        this.height = height;
        loadAnimations();
    }

    @Override
    protected void doSkill(int atk, int run) {
        if (player == 1){
            if (atk == ATT3){
                action = atk;
                for (int i = 0; i < 50; i++){
                    action = HITTED;
                    if (i >= 45){
                        game.getAudioPlayer().playEffect(AudioPlayer.HEALING);
                    }
                }
                if (!doneInc){

                    if (this.item == ItemStates.SHIELD){
                        if((this.getHp() + 90) > HP && (this.getDef() + 90) > (DEF+500)){
                            this.setDef(DEF+500);
                        }
                        else if ((this.getHp() + 90) > HP && (this.getDef() + 90) <= (DEF+500)){
                            this.setDef(this.getDef() + (this.getHp() + 90 - HP));
                            this.setHp(HP);
                        }else if(this.getHp() + 90 <= HP){
                            this.setHp(this.getHp() + 90);
                        }
                    }else {
                        if((this.getHp() + 90) > HP && (this.getDef() + 90) > (DEF)){
                            this.setDef(DEF);
                        }
                        else if ((this.getHp() + 90) > HP && (this.getDef() + 90) <= DEF){
                            this.setDef(this.getDef() + (this.getHp() + 90 - HP));
                            this.setHp(HP);
                        }else if(this.getHp() + 90 <= HP){
                            this.setHp(this.getHp() + 90);
                        }
                    }
                    doneInc = true;
                }
            }
            else if (x >= 770 && alreadyAtk == 0){
                action = atk;
                sumX*=-1;
                alreadyAtk = 1;
                needStop = 0;
            }
            else{
                if(action == atk && z != 50 && (atk ==  ATT2 || atk == BASIC)){
                    if (z >= 40){
                        enemy.attacked = true;
                        if (z == 43){
                            game.getAudioPlayer().playEffect(AudioPlayer.SLASH_2);
                        }
                        if(z == 49){
                            checkEnemy();
                        }
                    }
                    z++;
                    return;
                }else if(action == atk && z != 98 && atk ==  ATT1){
                    if (z >= 70){
                        enemy.attacked = true;
                        if (z == 70){
                            game.getAudioPlayer().playEffect(AudioPlayer.SLASH_4);
                        }
                        if(z == 80){
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

        }else if (player == 2){
            if (atk == ATT3){
                action = atk;
                for (int i = 0; i < 50; i++){
                    action = HITTED;
                    if (i >= 45){
                        game.getAudioPlayer().playEffect(AudioPlayer.HEALING);
                    }
                }
                if (!doneInc){
                    if (this.item == ItemStates.SHIELD){
                        if((this.getHp() + 90) > HP && (this.getDef() + 90) > (DEF+500)){
                            this.setDef(DEF+500);
                        }
                        else if ((this.getHp() + 90) > HP && (this.getDef() + 90) <= (DEF+500)){
                            this.setDef(this.getDef() + (this.getHp() + 90 - HP));
                            this.setHp(HP);
                        }
                        else if(this.getHp() + 900 <= HP){
                            this.setHp(this.getHp() + 900);
                        }
                    }else {
                        if((this.getHp() + 90) > HP && (this.getDef() + 90) > (DEF)){
                            this.setDef(DEF);
                        }
                        else if ((this.getHp() + 90) > HP && (this.getDef() + 90) <= DEF){
                            this.setDef(this.getDef() + (this.getHp() + 90 - HP));
                            this.setHp(HP);
                        }else if(this.getHp() + 90 <= HP){
                            this.setHp(this.getHp() + 90);
                        }
                    }
                    doneInc = true;
                }
            }
            else if (x <= 650 && alreadyAtk == 0){
                action = atk;
                sumX*=-1;
                alreadyAtk = 1;
                needStop = 0;
            }
            else{
                if(action == atk && z != 50 && (atk ==  ATT2 || atk == BASIC)){
                    if (z >= 40){
                        enemy.attacked = true;
                        if (z == 43){
                            game.getAudioPlayer().playEffect(AudioPlayer.SLASH_2);
                        }
                        if(z == 49){
                            checkEnemy();
                        }
                    }
                    z++;
                    return;
                }else if(action == atk && z != 98 && atk ==  ATT1){
                    if (z >= 70){
                        enemy.attacked = true;
                        if (z == 70){
                            game.getAudioPlayer().playEffect(AudioPlayer.SLASH_4);
                        }
                        if(z == 80){
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
                if(action == DEAD){
                    if(this.player == 1){
                        game.getPlaying().setGameOver(1);
                    }else if(this.player == 2){
                        game.getPlaying().setGameOver(0);
                    }
                    dead = false;
                }
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
        }else if(dead){
            action = DEAD;
        } else {
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
//        dead = false;
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
