package gamestates;

import audio.AudioPlayer;
import entities.AlmoGarden;
import entities.Dwarf;
import entities.Entity;
import entities.KingGarden;
import entities.Samurai;
import entities.Skill;
import entities.Wizard;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import main.Game;
import ui.GameoverOverlay;
import ui.PauseOverlay;
import ui.SkillButton;
import utils.Constant;

public class Playing extends States implements Statemethods {
    private Entity player1;
    private Entity player2;
    private Entity tempP1;
    private Entity tempP2;
    private AlmoGarden almo;
    private KingGarden kingGarden;
    private final int scale = 3;
    private PlayerStates p1;
    private PlayerStates p2;
    private MapStates map;
    BufferedImage[] skills = new BufferedImage[4];
    private SkillButton[] skillButtonsP1 = new SkillButton[4];
    private SkillButton[] skillButtonsP2 = new SkillButton[4];
    ItemStates p1_item;
    ItemStates p2_item;
    private int turn = 1, k = 0;
    private int tempTurn = 1;
    private Skill[] cdP1 = new Skill[4];
    private Skill[] cdP2 = new Skill[4];
    private boolean[] skillPressedP1 = new boolean[4];
    private boolean[] skillPressedP2 = new boolean[4];
    private boolean[] delayP1 = new boolean[4];
    private boolean[] delayP2 = new boolean[4];
    private boolean p1Turn = true;
    private boolean p2Turn = false;
    private PauseOverlay pauseOverlay;
    private boolean pause = false;
    private GameoverOverlay gameoverOverlay;
    private int gameOver = -1;

    public Playing(Game game, PlayerStates p1, PlayerStates p2, ItemStates p1_item, ItemStates p2_item, MapStates map) {
        super(game);
        this.p1 = p1;
        this.p2 = p2;
        this.map = map;
        this.p1_item = p1_item;
        this.p2_item = p2_item;
        this.initClasses();
        this.loadSkill();
        this.loadCd();
        this.loadSkillPressed();
    }

    private void initClasses() {
        if (p1 == PlayerStates.WIZARD) {
            player1 = new Wizard(Constant.PlayerPosition.xPosP1, Constant.PlayerPosition.yPosP1, Constant.WizardConstant.WIDTH * scale, Constant.WizardConstant.HEIGHT * scale, 1, game);
            tempP1 = new Wizard(Constant.PlayerPosition.xPosP1, Constant.PlayerPosition.yPosP1, Constant.WizardConstant.WIDTH * scale, Constant.WizardConstant.HEIGHT * scale, 1, game);
        } else if (p1 == PlayerStates.SAMURAI) {
            player1 = new Samurai(Constant.PlayerPosition.xPosP1, Constant.PlayerPosition.yPosP1, Constant.SamuraiConstant.WIDTH * scale, Constant.SamuraiConstant.HEIGHT * scale, 1, game);
            tempP1 = new Samurai(Constant.PlayerPosition.xPosP1, Constant.PlayerPosition.yPosP1, Constant.SamuraiConstant.WIDTH * scale, Constant.SamuraiConstant.HEIGHT * scale, 1, game);
        }else if(p1 == PlayerStates.DWARF){
            player1 = new Dwarf(Constant.PlayerPosition.xPosP1, Constant.PlayerPosition.yPosP1, Constant.DwarfConstant.WIDTH * scale, Constant.DwarfConstant.HEIGHT * scale, 1, game);
            tempP1 = new Dwarf(Constant.PlayerPosition.xPosP1, Constant.PlayerPosition.yPosP1, Constant.DwarfConstant.WIDTH * scale, Constant.DwarfConstant.HEIGHT * scale, 1, game);
        }
        if (p2 == PlayerStates.WIZARD) {
            player2 = new Wizard(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.WizardConstant.WIDTH * scale, Constant.WizardConstant.HEIGHT * scale, 2, game);
            tempP2 = new Wizard(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.WizardConstant.WIDTH * scale, Constant.WizardConstant.HEIGHT * scale, 2, game);
        } else if (p2 == PlayerStates.SAMURAI) {
            player2 = new Samurai(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.SamuraiConstant.WIDTH * scale, Constant.SamuraiConstant.HEIGHT * scale, 2, game);
            tempP2 = new Samurai(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.SamuraiConstant.WIDTH * scale, Constant.SamuraiConstant.HEIGHT * scale, 2, game);
        }else if(p2 == PlayerStates.DWARF){
            player2 = new Dwarf(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.DwarfConstant.WIDTH * scale, Constant.DwarfConstant.HEIGHT * scale, 2, game);
            tempP2 = new Dwarf(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.DwarfConstant.WIDTH * scale, Constant.DwarfConstant.HEIGHT * scale, 2, game);
        }

        player2.enemy = player1;
        player1.enemy = player2;

        if (this.p1_item == ItemStates.SHIELD) {
            this.player1.setDef(this.player1.getDef() + 500);
            this.tempP1.setDef(this.tempP1.getDef() + 500);
        } else if (this.p1_item == ItemStates.SWORD) {
            if (p1 == PlayerStates.DWARF){
                player1.getSkills().get(0).setDamage(player1.getSkills().get(0).getDamage() + 500);
                player1.getSkills().get(1).setDamage(player1.getSkills().get(1).getDamage() + 500);
                player1.getSkills().get(2).setDamage(player1.getSkills().get(2).getDamage() + 500);
            } else if (p1 == PlayerStates.SAMURAI) {
                player1.getSkills().get(0).setDamage(player1.getSkills().get(0).getDamage() + 500);
                player1.getSkills().get(1).setDamage(player1.getSkills().get(1).getDamage() + 500);
                player1.getSkills().get(2).setDamage(player1.getSkills().get(2).getDamage() + 500);
            } else if (p1 == PlayerStates.WIZARD) {
                player1.getSkills().get(0).setDamage(player1.getSkills().get(0).getDamage() + 500);
                player1.getSkills().get(1).setDamage(player1.getSkills().get(1).getDamage() + 500);
                player1.getSkills().get(2).setDamage(player1.getSkills().get(2).getDamage() + 500);
                player1.getSkills().get(3).setDamage(player1.getSkills().get(3).getDamage() + 500);
            }

            this.player1.setAtk(this.player1.getAtk() + 500);
            this.tempP1.setDef(this.tempP1.getDef() + 500);
        }

        if (this.p2_item == ItemStates.SHIELD) {
            this.player2.setDef(this.player2.getDef() + 500);
            this.tempP2.setDef(this.tempP2.getDef() + 500);
        } else if (this.p2_item == ItemStates.SWORD) {
            if (p2 == PlayerStates.DWARF){
                player2.getSkills().get(0).setDamage(player2.getSkills().get(0).getDamage() + 500);
                player2.getSkills().get(1).setDamage(player2.getSkills().get(1).getDamage() + 500);
                player2.getSkills().get(2).setDamage(player2.getSkills().get(2).getDamage() + 500);
            } else if (p2 == PlayerStates.SAMURAI) {
                player2.getSkills().get(0).setDamage(player1.getSkills().get(0).getDamage() + 500);
                player2.getSkills().get(1).setDamage(player1.getSkills().get(1).getDamage() + 500);
                player2.getSkills().get(2).setDamage(player1.getSkills().get(2).getDamage() + 500);
            } else if (p2 == PlayerStates.WIZARD) {
                player2.getSkills().get(0).setDamage(player2.getSkills().get(0).getDamage() + 500);
                player2.getSkills().get(1).setDamage(player2.getSkills().get(1).getDamage() + 500);
                player2.getSkills().get(2).setDamage(player2.getSkills().get(2).getDamage() + 500);
                player2.getSkills().get(3).setDamage(player2.getSkills().get(3).getDamage() + 500);
            }
            this.player2.setAtk(this.player2.getAtk() + 500);
            this.tempP2.setAtk(this.tempP2.getAtk() + 500);
        }

        this.almo = new AlmoGarden(0, 0);
        this.kingGarden = new KingGarden(0, 0);
        this.pauseOverlay = new PauseOverlay(this);
        this.gameoverOverlay = new GameoverOverlay(this);
    }

    private void loadSkill() {
        this.skillButtonsP1[0] = new SkillButton(57, 205, 0, this.player1, 3);
        this.skillButtonsP1[1] = new SkillButton(57, 329, 1, this.player1, 2);
        this.skillButtonsP1[2] = new SkillButton(57, 454, 2, this.player1, 1);
        this.skillButtonsP1[3] = new SkillButton(57, 579, 3, this.player1, 0);
        this.skillButtonsP2[0] = new SkillButton(1251, 205, 0, this.player2, 3);
        this.skillButtonsP2[1] = new SkillButton(1251, 329, 1, this.player2, 2);
        this.skillButtonsP2[2] = new SkillButton(1251, 454, 2, this.player2, 1);
        this.skillButtonsP2[3] = new SkillButton(1251, 579, 3, this.player2, 0);
    }

    public void loadCd() {
        int i;
        for(i = 0; i < this.cdP1.length; ++i) {
            this.cdP1[i] = (Skill)this.player1.getSkills().get(i);
        }

        for(i = 0; i < this.cdP2.length; ++i) {
            this.cdP2[i] = (Skill)this.player2.getSkills().get(i);
        }

    }

    public void loadSkillPressed() {
        Arrays.fill(this.skillPressedP1, false);
        Arrays.fill(this.skillPressedP2, false);
        Arrays.fill(this.delayP1, true);
        Arrays.fill(this.delayP2, true);
    }

    @Override
    public void update() {
        if (this.gameOver == -1) {
            if (!this.pause) {
                this.player1.update();
                this.player2.update();
                SkillButton[] var1 = this.skillButtonsP1;
                int var2 = var1.length;

                int var3;
                SkillButton sb;
                for(var3 = 0; var3 < var2; ++var3) {
                    sb = var1[var3];
                    sb.update();
                }

                var1 = this.skillButtonsP2;
                var2 = var1.length;

                for(var3 = 0; var3 < var2; ++var3) {
                    sb = var1[var3];
                    sb.update();
                }
            } else {
                this.pauseOverlay.update();
            }
        } else {
            this.gameoverOverlay.update();
        }

    }

    @Override
    public void draw(Graphics2D g2) {
        if (this.map == MapStates.ALMO_GARDEN) {
            this.almo.render(g2);
        } else if (this.map == MapStates.KING_GARDEN) {
            this.kingGarden.render(g2);
        }

        this.player1.render(g2);
        this.player2.render(g2);
        this.drawDetails(g2);
        SkillButton[] var2 = this.skillButtonsP1;
        int var3 = var2.length;

        int var4;
        SkillButton sb;
        for(var4 = 0; var4 < var3; ++var4) {
            sb = var2[var4];
            sb.draw(g2);
        }

        var2 = this.skillButtonsP2;
        var3 = var2.length;

        for(var4 = 0; var4 < var3; ++var4) {
            sb = var2[var4];
            sb.draw(g2);
        }

        this.drawTurn(g2);
        if (this.pause) {
            this.pauseOverlay.draw(g2);
        }

        if (this.gameOver != -1) {
            this.gameoverOverlay.draw(g2);
        }

    }

    public void drawTurn(Graphics2D g2) {
        g2.setFont(new Font("Plus Jakarta Sans", 1, 20));
        int var10001 = this.turn;
        g2.drawString("" + var10001, 650, 40);
        g2.drawString(this.p1Turn ? "P1 Turn" : "P2 Turn", 700, 100);
    }

    public void drawDetails(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.drawString("Hp:" + this.player1.getHp(), 10, 50);
        g2.drawString("ATK:" + this.player1.getAtk(), 60, 50);
        g2.drawString("DEF:" + this.player1.getDef(), 110, 50);
        g2.drawString("ITEM:" + String.valueOf(this.p1_item), 200, 50);
        g2.drawString("Hp:" + this.player2.getHp(), 1000, 100);
        g2.drawString("ATK:" + this.player2.getAtk(), 1050, 100);
        g2.drawString("DEF:" + this.player2.getDef(), 1100, 100);
        g2.drawString("ITEM:" + String.valueOf(this.p2_item), 1150, 100);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.pause) {
            this.pauseOverlay.mouseMoved(e);
        }

        if (this.gameOver != -1) {
            this.gameoverOverlay.mouseMoved(e);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.pause) {
            this.pauseOverlay.mousePressed(e);
        }

        if (this.gameOver != -1) {
            this.gameoverOverlay.mousePressed(e);
        }

        if (this.p1Turn) {
            this.skillPressP1(e);
        } else if (this.p2Turn) {
            this.skillPressP2(e);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.pause) {
            this.pauseOverlay.mouseReleased(e);
        }

        if (this.gameOver != -1) {
            this.gameoverOverlay.mouseReleased(e);
        }

        if (this.p1Turn) {
            this.skillReleaseP1(e);
        } else if (this.p2Turn) {
            this.skillReleaseP2(e);
        }

    }

    public void skillPressP1(MouseEvent e) {
        SkillButton[] var2 = this.skillButtonsP1;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            SkillButton sb = var2[var4];
            if (this.isInSb(sb, e)) {
                sb.setMousePressed(true);
                break;
            }
        }

    }

    public void skillPressP2(MouseEvent e) {
        SkillButton[] var2 = this.skillButtonsP2;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            SkillButton sb = var2[var4];
            if (this.isInSb(sb, e)) {
                sb.setMousePressed(true);
                break;
            }
        }

    }

    public void skillReleaseP1(MouseEvent e) {
        SkillButton[] var2 = this.skillButtonsP1;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            SkillButton sb = var2[var4];
            if (!this.skillPressedP1[sb.getAction()] && this.isInSb(sb, e)) {
                if (sb.isMousePressed()) {
                    sb.applyAction();
                    sb.setMouseReleased(true);
                    if (sb.getAction() != 0) {
                        this.skillPressedP1[sb.getAction()] = true;
                    }

//                    if (p1 == PlayerStates.SAMURAI && sb.getAction() == 3){
//                        player1.setHp(player1.getHp() + 90);
//                    } else if (p1 == PlayerStates.DWARF && sb.getAction() == 3) {
//                        player1.setDef(player1.getDef() + 90);
//                    }else {
//                        if (player2.getDef() > 0) {
//                            if ((player2.getDef() - player1.getSkills().get(sb.getAction()).getDamage()) < 0){
//                                player2.setDef(player2.getDef() - player1.getSkills().get(sb.getAction()).getDamage());
//                                player2.setHp(player2.getHp() + player2.getDef());
//                                player2.setDef(0);
//                                if (player2.getHp() <= 0){
//                                    gameOver = 0;
//                                }
//                            }else {
//                                player2.setDef(player2.getDef() - player1.getSkills().get(sb.getAction()).getDamage());
//                                if (player2.getHp() <= 0){
//                                    gameOver = 0;
//                                }
//                            }
//                        } else if (player2.getHp() > 0) {
//                            if ((player2.getHp() - player1.getSkills().get(sb.getAction()).getDamage()) < 0){
//                                player2.setHp(0);
//                                gameOver = 0;
//                            }else {
//                                player2.setHp(player2.getHp() - player1.getSkills().get(sb.getAction()).getDamage());
//                                if (player2.getHp() <= 0){
//                                    gameOver = 0;
//                                }
//                            }
//                        } else if(player2.getHp() <= 0) {
//                            gameOver = 0;
//                        }
//                    }

                    this.p1Turn = false;
                    this.p2Turn = true;
                }
                break;
            }

            sb.resetAni();
        }

        this.resetButton();
    }

    public void skillReleaseP2(MouseEvent e) {
        SkillButton[] var2 = this.skillButtonsP2;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            SkillButton sb = var2[var4];
            if (!this.skillPressedP2[sb.getAction()] && this.isInSb(sb, e)) {
                if (sb.isMousePressed()) {
                    sb.applyAction();
                    sb.setMouseReleased(true);
                    if (sb.getAction() != 0) {
                        this.skillPressedP2[sb.getAction()] = true;
                    }

//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException ex) {
//                        throw new RuntimeException(ex);
//                    }

//                    if (p2 == PlayerStates.SAMURAI && sb.getAction() == 3){
//                        player2.setHp(player2.getHp() + 90);
//                    } else if (p2 == PlayerStates.DWARF && sb.getAction() == 3) {
//                        player2.setDef(player2.getDef() + 90);
//                    }else {
//                        if (player1.getDef() > 0) {
//                            if ((player1.getDef() - player2.getSkills().get(sb.getAction()).getDamage()) < 0){
//                                player1.setDef(player1.getDef() - player2.getSkills().get(sb.getAction()).getDamage());
//                                player1.setHp(player1.getHp() + player1.getDef());
//                                player1.setDef(0);
//                                if (player2.getHp() <= 0){
//                                    if (player1 instanceof Wizard || player1 instanceof Dwarf){
//                                        player1.setAction(Constant.WizardConstant.DEAD);
//                                    }else if (player1 instanceof Samurai){
//                                        player1.setAction(Constant.SamuraiConstant.DEAD);
//                                    }
//
//                                    gameOver = 1;
//                                }
//                            }else {
//                                player1.setDef(player1.getDef() - player2.getSkills().get(sb.getAction()).getDamage());
//                                if (player2.getHp() <= 0){
//                                    if (player1 instanceof Wizard || player1 instanceof Dwarf){
//                                        player1.setAction(Constant.WizardConstant.DEAD);
//                                    }else if (player1 instanceof Samurai){
//                                        player1.setAction(Constant.SamuraiConstant.DEAD);
//                                    }
//
////                                    while (k != 2000){
////                                        if (k == 1999) {
//                                            gameOver = 1;
////                                            break;
////                                        }
////                                        k++;
////                                    }
//
//                                }
//                            }
//                        } else if (player1.getHp() > 0) {
//                            if ((player1.getHp() - player2.getSkills().get(sb.getAction()).getDamage()) < 0){
//                                player1.setHp(0);
//                                if (player1 instanceof Wizard || player1 instanceof Dwarf){
//                                    player1.setAction(Constant.WizardConstant.DEAD);
//                                }else if (player1 instanceof Samurai){
//                                    player1.setAction(Constant.SamuraiConstant.DEAD);
//                                }
//
////                                while (k != 2000){
////                                    if (k == 1999) {
//                                        gameOver = 1;
////                                        break;
////                                    }
////                                    k++;
////                                }
//
//                            }else {
//                                player1.setHp(player1.getHp() - player2.getSkills().get(sb.getAction()).getDamage());
//                                if (player2.getHp() <= 0){
//                                    if (player1 instanceof Wizard || player1 instanceof Dwarf){
//                                        player1.setAction(Constant.WizardConstant.DEAD);
//                                    }else if (player1 instanceof Samurai){
//                                        player1.setAction(Constant.SamuraiConstant.DEAD);
//                                    }
//
////                                    while (k != 2000){
////                                        if (k == 1999) {
//                                            gameOver = 1;
////                                            break;
////                                        }
////                                        k++;
////                                    }
//
//                                }
//                            }
//                        } else if(player1.getHp() <= 0) {
//
//                            if (player1 instanceof Wizard || player1 instanceof Dwarf){
//                                player1.setAction(Constant.WizardConstant.DEAD);
//                            }else if (player1 instanceof Samurai){
//                                player1.setAction(Constant.SamuraiConstant.DEAD);
//                            }
//
////                            while (k != 2000){
////                                if (k == 1999) {
//                                    gameOver = 1;
////                                    break;
////                                }
////                                k++;
////                            }
//                        }
//                    }
                    p1Turn = true;
                    p2Turn = false;
                    turn++;
                    cdProcess();
                }
                break;
            }

            sb.resetAni();
        }

        this.resetButton();
    }

    public void resetButton() {
        SkillButton[] var1 = this.skillButtonsP1;
        int var2 = var1.length;

        int var3;
        SkillButton sb;
        for(var3 = 0; var3 < var2; ++var3) {
            sb = var1[var3];
            sb.resetBool();
        }

        var1 = this.skillButtonsP2;
        var2 = var1.length;

        for(var3 = 0; var3 < var2; ++var3) {
            sb = var1[var3];
            sb.resetBool();
        }

    }

    public void cdProcess() {
        this.cdProcessP1();
        this.cdProcessP2();
    }

    public void cdProcessP1() {
        if (this.skillPressedP1[3]) {
            if (!this.delayP1[3]) {
                if (this.cdP1[3].getCd() > 0) {
                    this.cdP1[3].setCd(this.cdP1[3].getCd() - 1);
                    if (this.cdP1[3].getCd() == 0) {
                        this.cdP1[3].setCd(3);
                        this.skillPressedP1[3] = false;
                        this.skillButtonsP1[0].setMouseReleased(false);
                        this.delayP1[3] = true;
                    }
                }
            } else {
                this.delayP1[3] = false;
            }
        }

        if (this.skillPressedP1[1]) {
            if (!this.delayP1[1]) {
                if (this.cdP1[1].getCd() > 0) {
                    this.cdP1[1].setCd(this.cdP1[1].getCd() - 1);
                    if (this.cdP1[1].getCd() == 0) {
                        this.cdP1[1].setCd(1);
                        this.skillPressedP1[1] = false;
                        this.skillButtonsP1[2].setMouseReleased(false);
                        this.delayP1[1] = true;
                    }
                }
            } else {
                this.delayP1[1] = false;
            }
        }

        if (this.skillPressedP1[2]) {
            if (!this.delayP1[2]) {
                if (this.cdP1[2].getCd() > 0) {
                    this.cdP1[2].setCd(this.cdP1[2].getCd() - 1);
                    if (this.cdP1[2].getCd() == 0) {
                        this.cdP1[2].setCd(2);
                        this.skillPressedP1[2] = false;
                        this.skillButtonsP1[1].setMouseReleased(false);
                        this.delayP1[2] = true;
                    }
                }
            } else {
                this.delayP1[2] = false;
            }
        }

    }

    public void cdProcessP2() {
        if (this.skillPressedP2[3]) {
            if (!this.delayP2[3]) {
                if (this.cdP2[3].getCd() > 0) {
                    this.cdP2[3].setCd(this.cdP2[3].getCd() - 1);
                    if (this.cdP2[3].getCd() == 0) {
                        this.cdP2[3].setCd(3);
                        this.skillPressedP2[3] = false;
                        this.skillButtonsP2[0].setMouseReleased(false);
                        this.delayP2[3] = true;
                    }
                }
            } else {
                this.delayP2[3] = false;
            }
        }

        if (this.skillPressedP2[1]) {
            if (!this.delayP2[1]) {
                if (this.cdP2[1].getCd() > 0) {
                    this.cdP2[1].setCd(this.cdP2[1].getCd() - 1);
                    if (this.cdP2[1].getCd() == 0) {
                        this.cdP2[1].setCd(1);
                        this.skillPressedP2[1] = false;
                        this.skillButtonsP2[2].setMouseReleased(false);
                        this.delayP2[1] = true;
                    }
                }
            } else {
                this.delayP2[1] = false;
            }
        }

        if (this.skillPressedP2[2]) {
            if (!this.delayP2[2]) {
                if (this.cdP2[2].getCd() > 0) {
                    this.cdP2[2].setCd(this.cdP2[2].getCd() - 1);
                    if (this.cdP2[2].getCd() == 0) {
                        this.cdP2[2].setCd(2);
                        this.skillPressedP2[2] = false;
                        this.skillButtonsP2[1].setMouseReleased(false);
                        this.delayP2[2] = true;
                    }
                }
            } else {
                this.delayP2[2] = false;
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.gameOver == -1) {
            switch (e.getKeyCode()) {
                case 27:
                    this.pause = !this.pause;
            }
        }

    }

    public void unPauseGame() {
        this.pause = false;
    }

    public void rematch() {
        this.turn = 1;
        this.p1Turn = true;
        this.p2Turn = false;
        this.gameOver = -1;
        this.loadSkillPressed();
        this.resetSkillBtn();
        this.resetCd();
        this.resetAttribut();
    }

    public void resetCd() {
        int i;
        for(i = 0; i < this.cdP1.length; ++i) {
            this.cdP1[i].setCd(i);
        }

        for(i = 0; i < this.cdP2.length; ++i) {
            this.cdP2[i].setCd(i);
        }

    }

    public void resetSkillBtn() {
        SkillButton[] var1 = this.skillButtonsP1;
        int var2 = var1.length;

        int var3;
        SkillButton skillButton;
        for(var3 = 0; var3 < var2; ++var3) {
            skillButton = var1[var3];
            skillButton.setMouseReleased(false);
            skillButton.setMousePressed(false);
        }

        var1 = this.skillButtonsP2;
        var2 = var1.length;

        for(var3 = 0; var3 < var2; ++var3) {
            skillButton = var1[var3];
            skillButton.setMouseReleased(false);
            skillButton.setMousePressed(false);
        }

    }

    public void resetAttribut() {
        this.player1.setDef(this.tempP1.getDef());
        this.player1.setHp(this.tempP1.getHp());
        this.player1.setAtk(this.tempP1.getAtk());
        this.player2.setDef(this.tempP2.getDef());
        this.player2.setHp(this.tempP2.getHp());
        this.player2.setAtk(this.tempP2.getAtk());
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isPause() {
        return this.pause;
    }

    public int getGameOver() {
        return this.gameOver;
    }
    public void  setGameOver(int gameOver){
        this.gameOver = gameOver;
    }
}
