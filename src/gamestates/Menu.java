package gamestates;

import audio.AudioPlayer;
import main.Game;
import ui.MenuButton;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.MenuBtn.*;

public class Menu extends States implements Statemethods{

    private MenuButton[] buttons = new MenuButton[2];
    private BufferedImage bg, title;
    public Menu(Game game) {
        super(game);
        loadBg();
        loadTitle();
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(460 - MARGIN_LR, 375 - MARGIN_TB, 0, GameStates.PICKCHAR);
        buttons[1] = new MenuButton(726 - MARGIN_LR, 375 - MARGIN_TB, 1, GameStates.EXIT);
    }

    private void loadBg(){
        bg = LoadSave.getSprite(LoadSave.BACKGROUND_DEFAULT);
    }
    private void loadTitle(){
        title = LoadSave.getSprite(LoadSave.MENU_TITLE);
    }

    @Override
    public void update() {
        for(MenuButton mb : buttons){
            mb.update();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(bg, 0, 0, 1400, 750, null);
        g2.drawImage(title, 390, 125, 636, 100, null);
        for(MenuButton mb : buttons){
            mb.draw(g2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb : buttons){
            mb.setMouseOver(false);
        }
        for(MenuButton mb : buttons){
            if(isInMb(mb, e)){
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton mb : buttons){
            if(isInMb(mb, e)){
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb : buttons){
            if(isInMb(mb, e)){
                if(mb.isMousePressed()){
                    game.setPickChar(new PickChar(game));
                    mb.applyGameState();
                    System.out.println(mb.getState());
                }
                break;
            }
        }
        resetButton();
    }

    private void resetButton() {
        for(MenuButton mb : buttons){
            mb.resetBool();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            GameStates.state = GameStates.PICKCHAR;
        }
    }
}
