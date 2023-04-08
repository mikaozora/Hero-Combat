package gamestates;

import entities.*;
import main.Game;
import ui.MapsImg;
import ui.PlayingSkillButton;
import ui.SkillButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constant.*;

public class Playing extends States implements Statemethods {
    private Entity player1, player2;
    private AlmoGarden almo;
    private KingGarden kingGarden;
    private final int scale = 3;
    private PlayerStates p1, p2;
    private MapStates map;
    BufferedImage skills[] = new BufferedImage[4];
    private SkillButton[] skillButtons = new SkillButton[8];

    ItemStates p1_item, p2_item;

    public Playing(Game game, PlayerStates p1, PlayerStates p2, ItemStates p1_item, ItemStates p2_item, MapStates map) {
        super(game);
        this.p1 = p1;
        this.p2 = p2;
        this.map = map;
        this.p1_item = p1_item;
        this.p2_item = p2_item;
        initClasses();
        loadSkill();
    }

    private void initClasses() {
        if (p1 == PlayerStates.WIZARD) {
            player1 = new Wizard(PlayerPosition.xPosP1, PlayerPosition.yPosP1, WizardConstant.WIDTH * scale, WizardConstant.HEIGHT * scale);
        } else if (p1 == PlayerStates.SAMURAI) {
            player1 = new Samurai(PlayerPosition.xPosP1, PlayerPosition.yPosP1, SamuraiConstant.WIDTH * scale, SamuraiConstant.HEIGHT * scale);
        }else if(p1 == PlayerStates.DWARF){
            player1 = new Dwarf(PlayerPosition.xPosP1, PlayerPosition.yPosP1, DwarfConstant.WIDTH * scale, DwarfConstant.HEIGHT * scale);
        }
        if (p2 == PlayerStates.WIZARD) {
            player2 = new Wizard(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -WizardConstant.WIDTH * scale, WizardConstant.HEIGHT * scale);
        } else if (p2 == PlayerStates.SAMURAI) {
            player2 = new Samurai(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -SamuraiConstant.WIDTH * scale, SamuraiConstant.HEIGHT * scale);
        }else if(p2 == PlayerStates.DWARF){
            player2 = new Dwarf(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -DwarfConstant.WIDTH * scale, DwarfConstant.HEIGHT * scale);
        }
        almo = new AlmoGarden(0, 0);
        kingGarden = new KingGarden(0, 0);

    }

    private void loadSkill(){
        skillButtons[0] = new SkillButton(57, 205, 0, player1, 0);
        skillButtons[1] = new SkillButton(57, 329, 1, player1, 0);
        skillButtons[2] = new SkillButton(57, 454, 2, player1, 0);
        skillButtons[3] = new SkillButton(57, 579, 3, player1, 0);
        skillButtons[4] = new SkillButton(1251, 205, 0, player2, 0);
        skillButtons[5] = new SkillButton(1251, 329, 1, player2, 0);
        skillButtons[6] = new SkillButton(1251, 454, 2, player2, 0);
        skillButtons[7] = new SkillButton(1251, 579, 3, player2, 0);
    }

    @Override
    public void update() {
        player1.update();
        player2.update();
        for(SkillButton sb : skillButtons){
            sb.update();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if(map == MapStates.ALMO_GARDEN){
            almo.render(g2);
        }else if(map == MapStates.KING_GARDEN){
            kingGarden.render(g2);
        }
        player1.render(g2);
        player2.render(g2);
        drawDetails(g2);
        for(SkillButton sb : skillButtons){
            sb.draw(g2);
        }
    }

    public void drawDetails(Graphics2D g2){
        g2.setColor(Color.white);
        g2.drawString("Hp:"+player1.getHp(), 10, 50);
        g2.drawString("ATK:"+player1.getAtk(), 60, 50);
        g2.drawString("DEF:"+player1.getDef(), 110, 50);
        g2.drawString("Hp:"+player2.getHp(), 1000, 50);
        g2.drawString("ATK:"+player2.getAtk(), 1050, 50);
        g2.drawString("DEF:"+player2.getDef(), 1100, 50);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            player1.setAttack3(true);
        }


    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        skillP1.setMousePressed(false);
//        skillP2.setMousePressed(false);
//        if (isInBasic(skillP1, e) || isInSkill1(skillP1, e) || isInSkill2(skillP1, e) || isInUlt(skillP1, e)) {
//            skillP1.setMousePressed(true);
//        }else if(isInBasic(skillP2, e) || isInSkill1(skillP2, e) || isInSkill2(skillP2, e) || isInUlt(skillP2, e)){
//            skillP2.setMousePressed(true);
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        if (skillP1.isMousePressed()){
//            if (skillP1.getBoundBasic().getX() == SkillPosition.xPosBasicAtkP1){
//
//            }
//        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            GameStates.state = GameStates.MENU;
        }
        if(e.getKeyCode() == KeyEvent.VK_L){
            player2.setAttack3(true);
        }
    }

    public void setP1(PlayerStates p1) {
        this.p1 = p1;
    }

    public void setP2(PlayerStates p2) {
        this.p2 = p2;
    }
}