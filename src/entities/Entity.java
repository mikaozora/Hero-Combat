package entities;

import gamestates.ItemStates;

import main.Game;
import utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static utils.Constant.*;
import static utils.Constant.WizardConstant.*;
//import utils.Constant.*;

public abstract class Entity {
    protected int x, y, hp, atk, def;
    protected boolean attack3, attack2, attack1, basic;
    protected ItemStates item;
    protected ArrayList<Skill> skills;
    protected int action, getIdxAction;
    protected int alreadyAtk = 0, sumX = 4, z = 0, needStop = 0;
    protected boolean doneInc = false;

    int player;
    public Entity enemy;
    public boolean attacked =  false;
    protected Game game;
    public Entity(int x, int y, int hp, int atk, int def, int player, Game game) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        skills = new ArrayList<>();
        this.player = player;
        this.game = game;
    }

    protected void checkEnemy(){
        if (enemy.getDef() > 0) {
            if ((enemy.getDef() - this.getSkills().get(getIdxAction).getDamage()) < 0){
                enemy.setDef(enemy.getDef() - this.getSkills().get(getIdxAction).getDamage());
                enemy.setHp(enemy.getHp() + enemy.getDef());
                enemy.setDef(0);
                if (this.getHp() <= 0){
                    if (enemy instanceof Wizard || enemy instanceof Dwarf){
                        enemy.setAction(Constant.WizardConstant.DEAD);
                    }else if (enemy instanceof Samurai){
                        enemy.setAction(Constant.SamuraiConstant.DEAD);
                    }

                    game.getPlaying().setGameOver(1);
                }
            }else {
                enemy.setDef(enemy.getDef() - this.getSkills().get(getIdxAction).getDamage());
                if (this.getHp() <= 0){
                    if (enemy instanceof Wizard || enemy instanceof Dwarf){
                        enemy.setAction(Constant.WizardConstant.DEAD);
                    }else if (enemy instanceof Samurai){
                        enemy.setAction(Constant.SamuraiConstant.DEAD);
                    }
                    game.getPlaying().setGameOver(1);
                }
            }
        } else if (enemy.getHp() > 0) {
            if ((enemy.getHp() - this.getSkills().get(getIdxAction).getDamage()) < 0){
                enemy.setHp(0);
                System.out.println(this.getSkills().get(getIdxAction).getDamage());
                if (enemy instanceof Wizard || enemy instanceof Dwarf){
                    enemy.setAction(Constant.WizardConstant.DEAD);
                }else if (enemy instanceof Samurai){
                    enemy.setAction(Constant.SamuraiConstant.DEAD);
                }
                game.getPlaying().setGameOver(1);

            }else {
                enemy.setHp(enemy.getHp() - this.getSkills().get(getIdxAction).getDamage());
                if (this.getHp() <= 0){
                    if (enemy instanceof Wizard || enemy instanceof Dwarf){
                        enemy.setAction(Constant.WizardConstant.DEAD);
                    }else if (enemy instanceof Samurai){
                        enemy.setAction(Constant.SamuraiConstant.DEAD);
                    }
                    game.getPlaying().setGameOver(1);
                }
            }
        } else if(enemy.getHp() <= 0) {
            if (enemy instanceof Wizard || enemy instanceof Dwarf){
                enemy.setAction(Constant.WizardConstant.DEAD);
            }else if (enemy instanceof Samurai){
                enemy.setAction(Constant.SamuraiConstant.DEAD);
            }
            game.getPlaying().setGameOver(1);
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    protected abstract void doSkill(int atk, int run);

    public abstract void loadAnimations();
    public abstract void render(Graphics2D g2);
    public abstract void update();

    public abstract void setAttack3(boolean attack3);

    public abstract void setAttack2(boolean attack2);

    public abstract void setAttack1(boolean attack1);

    public abstract void setBasic(boolean basic);

    public abstract boolean isAttack3();
    public void setAction(int action) {
        this.action = action;
    }
    public int getAction() {
        return action;
    }

}
