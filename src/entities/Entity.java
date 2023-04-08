package entities;

import java.util.ArrayList;

public abstract class Entity {
    protected int x, y, hp, atk, def;
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

}
