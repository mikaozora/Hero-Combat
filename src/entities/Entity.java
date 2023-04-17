package entities;

import java.awt.*;
import java.util.ArrayList;

public abstract class Entity {
    protected int x, y, hp, atk, def;
    protected boolean attack3, attack2, attack1, basic;
    protected ArrayList<Skill> skills;
    public Entity(int x, int y, int hp, int atk, int def) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        skills = new ArrayList<>();
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

    public abstract void loadAnimations();
    public abstract void render(Graphics2D g2);
    public abstract void update();

    public abstract void setAttack3(boolean attack3);

    public abstract void setAttack2(boolean attack2);

    public abstract void setAttack1(boolean attack1);

    public abstract void setBasic(boolean basic);

    public abstract boolean isAttack3();
}
