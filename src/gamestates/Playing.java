package gamestates;

import entities.Dwarf;
import entities.Samurai;
import entities.Wizard;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utils.Constant.*;

public class Playing extends States implements Statemethods {
    private Wizard wizardP1, wizardP2;
    private Samurai samuraiP1, samuraiP2;
    private Dwarf dwarfP1, dwarfP2;
    private final int scale = 3;
    private PlayerStates p1, p2;

    public Playing(Game game, PlayerStates p1, PlayerStates p2) {
        super(game);
        this.p1 = p1;
        this.p2 = p2;
        initClasses();
    }


    private void initClasses() {
        wizardP1 = new Wizard(PlayerPosition.xPosP1, PlayerPosition.yPosP1, WizardConstant.WIDTH * scale, WizardConstant.HEIGHT * scale);
        wizardP2 = new Wizard(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -WizardConstant.WIDTH * scale, WizardConstant.HEIGHT * scale);
        samuraiP1 = new Samurai(PlayerPosition.xPosP1, PlayerPosition.yPosP1, SamuraiConstant.WIDTH * scale, SamuraiConstant.HEIGHT * scale);
        samuraiP2 = new Samurai(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -SamuraiConstant.WIDTH * scale, SamuraiConstant.HEIGHT * scale);
        dwarfP1 = new Dwarf(PlayerPosition.xPosP1, PlayerPosition.yPosP1, DwarfConstant.WIDTH * scale, DwarfConstant.HEIGHT * scale);
        dwarfP2 = new Dwarf(PlayerPosition.xPosP2, PlayerPosition.yPosP2, -DwarfConstant.WIDTH * scale, DwarfConstant.HEIGHT * scale);
    }


    @Override
    public void update() {
        wizardP1.update();
        wizardP2.update();
        samuraiP1.update();
        samuraiP2.update();
        dwarfP1.update();
        dwarfP2.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        if (p1 == PlayerStates.WIZARD) {
            wizardP1.render(g2);
        } else if (p1 == PlayerStates.SAMURAI) {
            samuraiP1.render(g2);
        }else if(p1 == PlayerStates.DWARF){
            dwarfP1.render(g2);
        }
        if (p2 == PlayerStates.WIZARD) {
            wizardP2.render(g2);
        } else if (p2 == PlayerStates.SAMURAI) {
            samuraiP2.render(g2);
        }else if(p2 == PlayerStates.DWARF){
            dwarfP2.render(g2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (p1 == PlayerStates.WIZARD) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                wizardP1.setattack3(true);
            }
        }else if(p1 == PlayerStates.SAMURAI){
            if (e.getButton() == MouseEvent.BUTTON1) {
                samuraiP1.setattack3(true);
            }
        }else if(p1 == PlayerStates.DWARF){
            if (e.getButton() == MouseEvent.BUTTON1) {
                dwarfP1.setattack3(true);
            }
        }


    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            GameStates.state = GameStates.MENU;
        }
        if (p2 == PlayerStates.WIZARD) {
            if (e.getKeyCode() == KeyEvent.VK_L) {
                wizardP2.setattack3(true);
            }
        }else if(p2 == PlayerStates.SAMURAI){
            if (e.getKeyCode() == KeyEvent.VK_L) {
                samuraiP2.setattack3(true);
            }
        }else if(p2 == PlayerStates.DWARF){
            if (e.getKeyCode() == KeyEvent.VK_L) {
                dwarfP2.setattack3(true);
            }
        }
    }

    public void setP1(PlayerStates p1) {
        this.p1 = p1;
    }

    public void setP2(PlayerStates p2) {
        this.p2 = p2;
    }
}
