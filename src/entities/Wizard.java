package entities;

import audio.AudioPlayer;
import gamestates.ItemStates;
import gamestates.PlayerStates;
import gamestates.Playing;
import main.Game;
import utils.Constant;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.WizardConstant.*;
import static utils.Constant.WizardConstant.getSpriteAmount;


public class Wizard extends Entity {
    private BufferedImage[][] hero2;
    private int aniTick, aniIndex, aniSpeed = 20;
    //    private int action = IDLE;
    private int width, height;

    public Wizard(int x, int y, int width, int height, int player, Game game, ItemStates item) {
        super(x, y, HP, ATK, DEF, player, game, item);
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

    @Override
    protected void doSkill(int atk, int run) {
        if (player == 1) {
            if (x >= 800 && alreadyAtk == 0) {
                action = atk;
                sumX *= -1;
                alreadyAtk = 1;
                needStop = 0;
            } else {
                if (action == atk && z != 277 && atk == ATT3) {
                    if (z >= 70) {
                        enemy.attacked = true;
                        if (z == 72) {
                            game.getAudioPlayer().playEffect(AudioPlayer.FIREJET);
                        }
                        if (z == 150) {
                            checkEnemy();
                        }
                    }
                    z++;
                    return;
                } else if (action == atk && z != 155 && atk == ATT2) {
                    if (z >= 100) {
                        enemy.attacked = true;
                        if (z == 103) {
                            game.getAudioPlayer().playEffect(AudioPlayer.FIREBALL);
                        }
                        if (z == 110) {
                            checkEnemy();
                        }
                    }
                    z++;
                    return;
                } else if (action == atk && z != 70 && (atk == ATT1 || atk == BASIC)) {
                    if (z >= 40) {
                        enemy.attacked = true;
                        if (z == 43) {
                            game.getAudioPlayer().playEffect(AudioPlayer.STAB_2);
                        }
                        if (z == 49) {
                            checkEnemy();
                        }
                    }
                    z++;
                    return;
                }

                if (x <= Constant.PlayerPosition.xPosP1 && needStop == 1) {
                    x = Constant.PlayerPosition.xPosP1;
                    x -= sumX;
                    if (action == IDLE) {
                        alreadyAtk = 0;
                        sumX *= -1;
                        z = 0;
                    }

                }


                action = run;
                x += sumX;
                needStop = 1;
            }
        } else if (player == 2) {
            if (x <= 650 && alreadyAtk == 0) {
                action = atk;
                sumX *= -1;
                alreadyAtk = 1;
                needStop = 0;
            } else {
                if (action == atk && z != 277 && atk == ATT3) {
                    if (z >= 70) {
                        enemy.attacked = true;
                        if (z == 73) {
                            game.getAudioPlayer().playEffect(AudioPlayer.FIREJET);
                        }
                        if (z == 150) {
                            checkEnemy();
                        }
                    }
                    z++;
                    return;
                } else if (action == atk && z != 155 && atk == ATT2) {
                    if (z >= 100) {
                        enemy.attacked = true;
                        if (z == 103) {
                            game.getAudioPlayer().playEffect(AudioPlayer.FIREBALL);
                        }
                        if (z == 120) {
                            checkEnemy();
                        }
                    }
                    z++;
                    return;
                } else if (action == atk && z != 70 && (atk == ATT1 || atk == BASIC)) {

                    if (z >= 40) {
                        enemy.attacked = true;
                        if (z == 43) {
                            game.getAudioPlayer().playEffect(AudioPlayer.STAB_2);
                        }
                        if (z == 49) {
                            checkEnemy();
                        }
                    }
                    z++;
                    return;
                }
                if (x >= Constant.PlayerPosition.xPosP2 && needStop == 1) {
                    x = Constant.PlayerPosition.xPosP2;
                    x -= sumX;
                    if (action == IDLE) {
                        alreadyAtk = 0;
                        sumX *= -1;
                        z = 0;
                    }
                }
                action = run;
                x -= sumX;
                needStop = 1;
            }
        }
    }

    public void loadAnimations() {
        BufferedImage img = LoadSave.getSprite(LoadSave.WIZARD);
        hero2 = new BufferedImage[8][14];
        for (int j = 0; j < hero2.length; j++) {
            for (int i = 0; i < hero2[j].length; i++) {
                hero2[j][i] = img.getSubimage(128 * i, 128 * j, 128, 128);
            }
        }
    }

    void initSkills() {
        skills.add(new Skill("Basic", 0, atk));
        skills.add(new Skill("Prick", 1, atk + 50));
        skills.add(new Skill("Fireball", 2, atk + 70));
        skills.add(new Skill("Fire jet", 3, atk + 150));
    }

    void updateAniTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(action)) {
                aniIndex = 0;
                if (action == DEAD) {
                    if (this.player == 1) {
                        game.getPlaying().setGameOver(1);
                    } else if (this.player == 2) {
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

        if (basic) {
            doSkill(BASIC, RUN);
        } else if (attack1) {
            doSkill(ATT1, RUN);
        } else if (attack2) {
            doSkill(ATT2, RUN);
        } else if (attack3) {
            doSkill(ATT3, RUN);
        } else if (attacked) {
            action = HITTED;
        } else if (dead) {
            action = DEAD;
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

    private void resetAction() {
        attack3 = false;
        attack1 = false;
        attack2 = false;
        basic = false;
        attacked = false;
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
