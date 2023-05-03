package gamestates;

import audio.AudioPlayer;
import entities.AlmoGarden;
import entities.Dwarf;
import entities.Entity;
import entities.KingGarden;
import entities.Samurai;
import entities.Skill;
import entities.Wizard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import main.Game;
import ui.GameoverOverlay;
import ui.PauseOverlay;
import ui.SkillButton;
import utils.Constant;
import utils.LoadSave;

import java.awt.font.GlyphVector;

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

    // status bar
    private BufferedImage statusBarP1;
    private BufferedImage statusBarP2;

    private int statusBarWidth = 496;
    private int statusBarHeight = 53;
    private int statusBarX = 40;
    private int statusBarY = 40;

    private int healthBarWidth = 452;
    private int healthBarHeight = 33;
    private int healthBarXstart = 44;
    private int healthBarYstart = 0;

    private int armorBarWidth = 354;
    private int armorBarHeight = 20;
    private int armorBarXstart = 44;
    private int armorBarYstart = 33;


    private int maxHealth_p1;
    private int maxHealth_p2;
    private int healthWidth_p1 = healthBarWidth;
    private int healthWidth_p2 = healthBarWidth;

    private int maxArmor_p1;
    private int maxArmor_p2;
    private int armorWidth_p1 = armorBarWidth;
    private int armorWidth_p2 = armorBarWidth;


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
        } else if (p1 == PlayerStates.DWARF) {
            player1 = new Dwarf(Constant.PlayerPosition.xPosP1, Constant.PlayerPosition.yPosP1, Constant.DwarfConstant.WIDTH * scale, Constant.DwarfConstant.HEIGHT * scale, 1, game);
            tempP1 = new Dwarf(Constant.PlayerPosition.xPosP1, Constant.PlayerPosition.yPosP1, Constant.DwarfConstant.WIDTH * scale, Constant.DwarfConstant.HEIGHT * scale, 1, game);
        }
        if (p2 == PlayerStates.WIZARD) {
            player2 = new Wizard(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.WizardConstant.WIDTH * scale, Constant.WizardConstant.HEIGHT * scale, 2, game);
            tempP2 = new Wizard(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.WizardConstant.WIDTH * scale, Constant.WizardConstant.HEIGHT * scale, 2, game);
        } else if (p2 == PlayerStates.SAMURAI) {
            player2 = new Samurai(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.SamuraiConstant.WIDTH * scale, Constant.SamuraiConstant.HEIGHT * scale, 2, game);
            tempP2 = new Samurai(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.SamuraiConstant.WIDTH * scale, Constant.SamuraiConstant.HEIGHT * scale, 2, game);
        } else if (p2 == PlayerStates.DWARF) {
            player2 = new Dwarf(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.DwarfConstant.WIDTH * scale, Constant.DwarfConstant.HEIGHT * scale, 2, game);
            tempP2 = new Dwarf(Constant.PlayerPosition.xPosP2, Constant.PlayerPosition.yPosP2, -Constant.DwarfConstant.WIDTH * scale, Constant.DwarfConstant.HEIGHT * scale, 2, game);
        }

        player2.enemy = player1;
        player1.enemy = player2;

        if (this.p1_item == ItemStates.SHIELD) {
            this.player1.setDef(this.player1.getDef() + 500);
            this.tempP1.setDef(this.tempP1.getDef() + 500);
        } else if (this.p1_item == ItemStates.SWORD) {
            if (p1 == PlayerStates.DWARF) {
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
            if (p2 == PlayerStates.DWARF) {
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
        this.maxHealth_p1 = player1.getHp();
        this.maxHealth_p2 = player2.getHp();
        this.maxArmor_p1 = player1.getDef();
        this.maxArmor_p2 = player2.getDef();
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
        this.statusBarP1 = LoadSave.getSprite(LoadSave.STATUS_BAR);
        this.statusBarP2 = LoadSave.getSprite(LoadSave.STATUS_BAR);
    }

    public void loadCd() {
        int i;
        for (i = 0; i < this.cdP1.length; ++i) {
            this.cdP1[i] = (Skill) this.player1.getSkills().get(i);
        }

        for (i = 0; i < this.cdP2.length; ++i) {
            this.cdP2[i] = (Skill) this.player2.getSkills().get(i);
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
//        System.out.println(gameOver);
        updateHealthBar();
        updateArmorBar();
        if (this.gameOver == -1) {
            if (!this.pause) {
                this.player1.update();
                this.player2.update();
                for (SkillButton sb : skillButtonsP1) {
                    sb.update();
                }
                for (SkillButton sb : skillButtonsP2) {
                    sb.update();
                }
            } else {
                this.pauseOverlay.update();
            }
        } else {
            this.gameoverOverlay.update();
        }

    }

    private void updateHealthBar() {
        healthWidth_p2 = (int) ((player2.getHp() / (float) maxHealth_p2) * healthBarWidth);
        healthWidth_p1 = (int) ((player1.getHp() / (float) maxHealth_p1) * healthBarWidth);
    }

    private void updateArmorBar() {
        armorWidth_p2 = (int) ((player2.getDef() / (float) maxArmor_p2) * armorBarWidth);
        armorWidth_p1 = (int) ((player1.getDef() / (float) maxArmor_p1) * armorBarWidth);
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

        for (SkillButton sb : skillButtonsP1) {
            sb.draw(g2);
        }
        for (SkillButton sb : skillButtonsP2) {
            sb.draw(g2);
        }

        this.drawTurn(g2);
        if (this.pause) {
            this.pauseOverlay.draw(g2);
        }

        if (this.gameOver != -1) {
            this.gameoverOverlay.draw(g2);
        }
        g2.drawImage(statusBarP1, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g2.drawImage(statusBarP2, statusBarX + 1320, statusBarY, statusBarWidth * -1, statusBarHeight, null);

        g2.setColor(Color.RED);
        g2.fillRect(healthBarXstart + statusBarX, healthBarYstart + statusBarY, healthWidth_p1, healthBarHeight);
        g2.create();
        g2.setColor(Color.RED);
        g2.translate(1360, 0);
        g2.scale(-1, 1);
        g2.fillRect(healthBarXstart, healthBarYstart + statusBarY, healthWidth_p2, healthBarHeight);

        g2.setColor(Color.blue);
        g2.fillRect(armorBarXstart + statusBarX - 40, armorBarYstart + statusBarY, armorWidth_p2, armorBarHeight);
        g2.create();
        g2.setColor(Color.blue);
        g2.translate(1320, 0);
        g2.scale(-1, 1);
        g2.fillRect(armorBarXstart, armorBarYstart + statusBarY, armorWidth_p1, armorBarHeight);


    }

    public void drawStringOutline(Graphics g, String s, int x, int y, Color c, Color cOutLine) {

        if (c == null)
            c = g.getColor();/* w ww  .j a v a  2 s .co m*/
        if (cOutLine == null)
            cOutLine = Color.black;
        if (!(g instanceof Graphics2D)) {
            g.drawString(s, x, y);
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        Color cb = g2.getColor();
        Object aliasing = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform t = g2.getTransform();
        try {
            Font f = g2.getFont();
            FontMetrics fm = g2.getFontMetrics(f);
            GlyphVector v = f.createGlyphVector(fm.getFontRenderContext(), s);
            Shape s1 = v.getOutline();
            g2.translate(x, y);
            Stroke st = g2.getStroke();
            g2.setStroke(new BasicStroke(1.6f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
            g.setColor(cOutLine);
            g2.draw(s1);
            g2.setStroke(st);
            g2.setColor(c);
            //          g2.translate(-0.3,-0.3);
            g2.fill(s1);
        } finally {
            g2.setTransform(t);
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, aliasing);
        g2.setColor(cb);
    }

    public void drawTurn(Graphics2D g2) {
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 40));
        drawStringOutline(g2, String.format("%02d", turn), 678, 50, Color.WHITE, Color.BLACK);
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 26));
        drawStringOutline(g2, (p1Turn ? "Player1 Turn" : "Player2 Turn"), 620, 100, Color.WHITE, Color.BLACK);
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
        for (SkillButton sb : skillButtonsP1) {
            if (isInSb(sb, e)) {
                sb.setMousePressed(true);
            }
        }
    }

    public void skillPressP2(MouseEvent e) {
        for (SkillButton sb : skillButtonsP2) {
            if (isInSb(sb, e)) {
                sb.setMousePressed(true);
            }
        }
    }

    public void skillReleaseP1(MouseEvent e) {
        for (SkillButton sb : skillButtonsP1) {
            if (!this.skillPressedP1[sb.getAction()] && this.isInSb(sb, e)) {
                if (sb.isMousePressed()) {
                    sb.applyAction();
                    sb.setMouseReleased(true);
                    if (sb.getAction() != 0) {
                        this.skillPressedP1[sb.getAction()] = true;
                    }
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
        for (SkillButton sb : skillButtonsP2) {
            if (!this.skillPressedP2[sb.getAction()] && this.isInSb(sb, e)) {
                if (sb.isMousePressed()) {
                    sb.applyAction();
                    sb.setMouseReleased(true);
                    if (sb.getAction() != 0) {
                        this.skillPressedP2[sb.getAction()] = true;
                    }
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
        for (SkillButton sb : skillButtonsP1) {
            sb.resetBool();
        }
        for (SkillButton sb : skillButtonsP2) {
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
        for (i = 0; i < this.cdP1.length; ++i) {
            this.cdP1[i].setCd(i);
        }

        for (i = 0; i < this.cdP2.length; ++i) {
            this.cdP2[i].setCd(i);
        }

    }

    public void resetSkillBtn() {
        for (SkillButton skillButton : skillButtonsP1) {
            skillButton.setMouseReleased(false);
            skillButton.setMousePressed(false);
        }
        for (SkillButton skillButton : skillButtonsP2) {
            skillButton.setMouseReleased(false);
            skillButton.setMousePressed(false);
        }

    }

    public void resetAttribut() {
        this.player1.setDef(this.tempP1.getDef());
        this.player1.setHp(this.tempP1.getHp());
        this.player1.setAtk(this.tempP1.getAtk());
        this.player1.setX(this.tempP1.getX());
        this.player2.setDef(this.tempP2.getDef());
        this.player2.setHp(this.tempP2.getHp());
        this.player2.setAtk(this.tempP2.getAtk());
        this.player2.setX(this.tempP2.getX());
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

    public void setGameOver(int gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isP1Turn() {
        return p1Turn;
    }

    public void setP1Turn(boolean p1Turn) {
        this.p1Turn = p1Turn;
    }

    public boolean isP2Turn() {
        return p2Turn;
    }

    public void setP2Turn(boolean p2Turn) {
        this.p2Turn = p2Turn;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
