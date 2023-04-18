package gamestates;

import entities.*;
import main.Game;
import ui.GameoverOverlay;
import ui.PauseOverlay;
import ui.SkillButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static utils.Constant.*;

public class Playing extends States implements Statemethods {
    private Entity player1, player2, tempP1, tempP2;
    private AlmoGarden almo;
    private KingGarden kingGarden;
    private final int scale = 3;
    private PlayerStates p1, p2;
    private MapStates map;
    BufferedImage skills[] = new BufferedImage[4];
    private SkillButton[] skillButtonsP1 = new SkillButton[4];
    private SkillButton[] skillButtonsP2 = new SkillButton[4];
    ItemStates p1_item, p2_item;
    private int turn = 1, tempTurn = 1;
    private Skill[] cdP1 = new Skill[4];
    private Skill[] cdP2 = new Skill[4];
    private boolean[] skillPressedP1 = new boolean[4];
    private boolean[] skillPressedP2 = new boolean[4];
    private boolean[] delayP1 = new boolean[4];
    private boolean[] delayP2 = new boolean[4];
    private boolean p1Turn = true, p2Turn = false;
    private PauseOverlay pauseOverlay;
    private boolean pause = false;
    private GameoverOverlay gameoverOverlay;
    private int gameOver = -1; // -1 = not end, 0 = p1 win, 1 = p2 win

    public Playing(Game game, PlayerStates p1, PlayerStates p2, ItemStates p1_item, ItemStates p2_item, MapStates map) {
        super(game);
        this.p1 = p1;
        this.p2 = p2;
        this.map = map;
        this.p1_item = p1_item;
        this.p2_item = p2_item;
        initClasses();
        loadSkill();
        loadCd();
        loadSkillPressed();
    }

    private void initClasses() {
        if (p1 == PlayerStates.WIZARD) {
            player1 = new Wizard(PlayerPosition.xPosP1, PlayerPosition.yPosP1, WizardConstant.WIDTH * scale, WizardConstant.HEIGHT * scale);
            tempP1 = new Wizard(PlayerPosition.xPosP1, PlayerPosition.yPosP1, WizardConstant.WIDTH * scale, WizardConstant.HEIGHT * scale);
        } else if (p1 == PlayerStates.SAMURAI) {
            player1 = new Samurai(PlayerPosition.xPosP1, PlayerPosition.yPosP1, SamuraiConstant.WIDTH * scale, SamuraiConstant.HEIGHT * scale);
            tempP1 = new Samurai(PlayerPosition.xPosP1, PlayerPosition.yPosP1, SamuraiConstant.WIDTH * scale, SamuraiConstant.HEIGHT * scale);
        } else if (p1 == PlayerStates.DWARF) {
            player1 = new Dwarf(PlayerPosition.xPosP1, PlayerPosition.yPosP1, DwarfConstant.WIDTH * scale, DwarfConstant.HEIGHT * scale);
            tempP1 = new Dwarf(PlayerPosition.xPosP1, PlayerPosition.yPosP1, DwarfConstant.WIDTH * scale, DwarfConstant.HEIGHT * scale);
        }
        if (p2 == PlayerStates.WIZARD) {
            player2 = new Wizard(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -WizardConstant.WIDTH * scale, WizardConstant.HEIGHT * scale);
            tempP2 = new Wizard(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -WizardConstant.WIDTH * scale, WizardConstant.HEIGHT * scale);
        } else if (p2 == PlayerStates.SAMURAI) {
            player2 = new Samurai(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -SamuraiConstant.WIDTH * scale, SamuraiConstant.HEIGHT * scale);
            tempP2 = new Samurai(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -SamuraiConstant.WIDTH * scale, SamuraiConstant.HEIGHT * scale);
        } else if (p2 == PlayerStates.DWARF) {
            player2 = new Dwarf(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -DwarfConstant.WIDTH * scale, DwarfConstant.HEIGHT * scale);
            tempP2 = new Dwarf(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -DwarfConstant.WIDTH * scale, DwarfConstant.HEIGHT * scale);
        }
        if (p1_item == ItemStates.SHIELD) {
            player1.setDef(player1.getDef() + 500);
            tempP1.setDef(tempP1.getDef() + 500);
        } else if (p1_item == ItemStates.SWORD) {
            player1.setAtk(player1.getAtk() + 500);
            tempP1.setDef(tempP1.getDef() + 500);
        }
        if (p2_item == ItemStates.SHIELD) {
            player2.setDef(player2.getDef() + 500);
            tempP2.setDef(tempP2.getDef() + 500);
        } else if (p2_item == ItemStates.SWORD) {
//            player2.getSkills().get(0).setDamage(player2.getSkills().get(0).getDamage() + 500);
            player2.setAtk(player2.getAtk() + 500);
            tempP2.setAtk(tempP2.getAtk() + 500);
        }

        almo = new AlmoGarden(0, 0);
        kingGarden = new KingGarden(0, 0);
        pauseOverlay = new PauseOverlay(this);
        gameoverOverlay = new GameoverOverlay(this);
    }

    private void loadSkill() {
        skillButtonsP1[0] = new SkillButton(57, 205, 0, player1, 3);
        skillButtonsP1[1] = new SkillButton(57, 329, 1, player1, 2);
        skillButtonsP1[2] = new SkillButton(57, 454, 2, player1, 1);
        skillButtonsP1[3] = new SkillButton(57, 579, 3, player1, 0);
        skillButtonsP2[0] = new SkillButton(1251, 205, 0, player2, 3);
        skillButtonsP2[1] = new SkillButton(1251, 329, 1, player2, 2);
        skillButtonsP2[2] = new SkillButton(1251, 454, 2, player2, 1);
        skillButtonsP2[3] = new SkillButton(1251, 579, 3, player2, 0);
    }

    public void loadCd() {
        for (int i = 0; i < cdP1.length; i++) {
            cdP1[i] = player1.getSkills().get(i);
        }
        for (int i = 0; i < cdP2.length; i++) {
            cdP2[i] = player2.getSkills().get(i);
        }
    }

    public void loadSkillPressed() {
        Arrays.fill(skillPressedP1, false);
        Arrays.fill(skillPressedP2, false);
        Arrays.fill(delayP1, true);
        Arrays.fill(delayP2, true);
    }

    @Override
    public void update() {
        if (gameOver == -1) {
            if (!pause) {
                player1.update();
                player2.update();
                for (SkillButton sb : skillButtonsP1) {
                    sb.update();
                }
                for (SkillButton sb : skillButtonsP2) {
                    sb.update();
                }
            } else {
                pauseOverlay.update();
            }
        }else{
            gameoverOverlay.update();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (map == MapStates.ALMO_GARDEN) {
            almo.render(g2);
        } else if (map == MapStates.KING_GARDEN) {
            kingGarden.render(g2);
        }
        player1.render(g2);
        player2.render(g2);
        drawDetails(g2);
        for (SkillButton sb : skillButtonsP1) {
            sb.draw(g2);
        }
        for (SkillButton sb : skillButtonsP2) {
            sb.draw(g2);
        }
        drawTurn(g2);
        if (pause) {
            pauseOverlay.draw(g2);
        }
        if(gameOver != -1){
            gameoverOverlay.draw(g2);
        }
    }

    public void drawTurn(Graphics2D g2) {
        g2.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 20));
        g2.drawString("" + turn, 650, 40);
        g2.drawString((p1Turn ? "P1 Turn" : "P2 Turn"), 700, 100);
    }

    public void drawDetails(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.drawString("Hp:" + player1.getHp(), 10, 50);
        g2.drawString("ATK:" + player1.getAtk(), 60, 50);
        g2.drawString("DEF:" + player1.getDef(), 110, 50);
        g2.drawString("ITEM:" + p1_item, 200, 50);
        g2.drawString("Hp:" + player2.getHp(), 1000, 100);
        g2.drawString("ATK:" + player2.getAtk(), 1050, 100);
        g2.drawString("DEF:" + player2.getDef(), 1100, 100);
        g2.drawString("ITEM:" + p2_item, 1150, 100);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (pause) {
            pauseOverlay.mouseMoved(e);
        }
        if(gameOver != -1){
            gameoverOverlay.mouseMoved(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (pause) {
            pauseOverlay.mousePressed(e);
        }
        if(gameOver != -1){
            gameoverOverlay.mousePressed(e);
        }
        if (p1Turn) {
            skillPressP1(e);
        } else if (p2Turn) {
            skillPressP2(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (pause) {
            pauseOverlay.mouseReleased(e);
        }
        if(gameOver != -1){
            gameoverOverlay.mouseReleased(e);
        }
        if (p1Turn) {
            skillReleaseP1(e);
        } else if (p2Turn) {
            skillReleaseP2(e);
        }
    }

    public void skillPressP1(MouseEvent e) {
        for (SkillButton sb : skillButtonsP1) {
            if (isInSb(sb, e)) {
                sb.setMousePressed(true);
                break;
            }
        }
    }

    public void skillPressP2(MouseEvent e) {
        for (SkillButton sb : skillButtonsP2) {
            if (isInSb(sb, e)) {
                sb.setMousePressed(true);
                break;
            }
        }
    }

    public void skillReleaseP1(MouseEvent e) {
        for (SkillButton sb : skillButtonsP1) {
            if (!skillPressedP1[sb.getAction()]) {
                if (isInSb(sb, e)) {
                    if (sb.isMousePressed()) {
                        sb.applyAction();
                        sb.setMouseReleased(true);
                        if (sb.getAction() != 0) {
                            skillPressedP1[sb.getAction()] = true;
                        }
                        if (player2.getDef() > 0) {
                            player2.setDef(player2.getDef() - player1.getSkills().get(sb.getAction()).getDamage());
                        } else if (player2.getHp() > 0) {
                            player2.setHp(player2.getHp() + player2.getDef());
                            player2.setHp(player2.getHp() - player1.getSkills().get(sb.getAction()).getDamage());
                        } else {
                            System.out.println("Player 1 win");
                        }
                        p1Turn = false;
                        p2Turn = true;
                    }
                    break;
                }
            }
            sb.resetAni();
        }
        resetButton();
    }

    public void skillReleaseP2(MouseEvent e) {
        for (SkillButton sb : skillButtonsP2) {
            if (!skillPressedP2[sb.getAction()]) {
                if (isInSb(sb, e)) {
                    if (sb.isMousePressed()) {
                        sb.applyAction();
                        sb.setMouseReleased(true);
                        if (sb.getAction() != 0) {
                            skillPressedP2[sb.getAction()] = true;
                        }
                        if (player1.getDef() > 0) {
                            player1.setDef(player1.getDef() - player2.getSkills().get(sb.getAction()).getDamage());
                        } else if (player1.getHp() > 0) {
                            player1.setHp(player1.getHp() + player1.getDef());
                            player1.setHp(player1.getHp() - player2.getSkills().get(sb.getAction()).getDamage());
                        } else {
                            System.out.println("Player 2 win");
                        }
                        p1Turn = true;
                        p2Turn = false;
                        turn++;
                        cdProcess();
                    }
                    break;
                }
            }
            sb.resetAni();
        }
        resetButton();
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
        cdProcessP1();
        cdProcessP2();
    }

    public void cdProcessP1() {
        if (skillPressedP1[3]) {
            if (!delayP1[3]) {
                if (cdP1[3].getCd() > 0) {
                    cdP1[3].setCd(cdP1[3].getCd() - 1);
                    if (cdP1[3].getCd() == 0) {
                        cdP1[3].setCd(3);
                        skillPressedP1[3] = false;
                        skillButtonsP1[0].setMouseReleased(false);
                        delayP1[3] = true;
                    }
                }
            } else {
                delayP1[3] = false;
            }
        }
        if (skillPressedP1[1]) {
            if (!delayP1[1]) {
                if (cdP1[1].getCd() > 0) {
                    cdP1[1].setCd(cdP1[1].getCd() - 1);
                    if (cdP1[1].getCd() == 0) {
                        cdP1[1].setCd(1);
                        skillPressedP1[1] = false;
                        skillButtonsP1[2].setMouseReleased(false);
                        delayP1[1] = true;
                    }
                }
            } else {
                delayP1[1] = false;
            }
        }
        if (skillPressedP1[2]) {
            if (!delayP1[2]) {
                if (cdP1[2].getCd() > 0) {
                    cdP1[2].setCd(cdP1[2].getCd() - 1);
                    if (cdP1[2].getCd() == 0) {
                        cdP1[2].setCd(2);
                        skillPressedP1[2] = false;
                        skillButtonsP1[1].setMouseReleased(false);
                        delayP1[2] = true;
                    }
                }
            } else {
                delayP1[2] = false;
            }
        }
    }

    public void cdProcessP2() {
        if (skillPressedP2[3]) {
            if (!delayP2[3]) {
                if (cdP2[3].getCd() > 0) {
                    cdP2[3].setCd(cdP2[3].getCd() - 1);
                    if (cdP2[3].getCd() == 0) {
                        cdP2[3].setCd(3);
                        skillPressedP2[3] = false;
                        skillButtonsP2[0].setMouseReleased(false);
                        delayP2[3] = true;
                    }
                }
            } else {
                delayP2[3] = false;
            }
        }
        if (skillPressedP2[1]) {
            if (!delayP2[1]) {
                if (cdP2[1].getCd() > 0) {
                    cdP2[1].setCd(cdP2[1].getCd() - 1);
                    if (cdP2[1].getCd() == 0) {
                        cdP2[1].setCd(1);
                        skillPressedP2[1] = false;
                        skillButtonsP2[2].setMouseReleased(false);
                        delayP2[1] = true;
                    }
                }
            } else {
                delayP2[1] = false;
            }
        }
        if (skillPressedP2[2]) {
            if (!delayP2[2]) {
                if (cdP2[2].getCd() > 0) {
                    cdP2[2].setCd(cdP2[2].getCd() - 1);
                    if (cdP2[2].getCd() == 0) {
                        cdP2[2].setCd(2);
                        skillPressedP2[2] = false;
                        skillButtonsP2[1].setMouseReleased(false);
                        delayP2[2] = true;
                    }
                }
            } else {
                delayP2[2] = false;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver == -1) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE -> pause = !pause;
                case KeyEvent.VK_0 -> gameOver = 0; // test
                case KeyEvent.VK_1 -> gameOver = 1; // test
            }
        }
    }

    public void unPauseGame() {
        this.pause = false;
    }

    public void rematch() {
        turn = 1;
        p1Turn = true;
        p2Turn = false;
        gameOver = -1;
        loadSkillPressed();
        resetSkillBtn();
        resetCd();
        resetAttribut();
    }

    public void resetCd() {
        for (int i = 0; i < cdP1.length; i++) {
            cdP1[i].setCd(i);
        }
        for (int i = 0; i < cdP2.length; i++) {
            cdP2[i].setCd(i);
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
        player1.setDef(tempP1.getDef());
        player1.setHp(tempP1.getHp());
        player1.setAtk(tempP1.getAtk());
        player2.setDef(tempP2.getDef());
        player2.setHp(tempP2.getHp());
        player2.setAtk(tempP2.getAtk());
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isPause() {
        return pause;
    }

    public int getGameOver() {
        return gameOver;
    }
}