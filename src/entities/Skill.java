package entities;

public class Skill {
    private String name;
    private int effect;
    private int damage;
    private int cd;

    public Skill(String name, int cd, int damage) {
        this.name = name;
        this.cd = cd;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getEffect() {
        return effect;
    }

    public int getCd() {
        return cd;
    }

    public int getDamage() {
        return damage;
    }
}
