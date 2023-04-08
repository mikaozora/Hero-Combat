package gamestates;

import main.Game;
import ui.Charpick;
import ui.ItemPick;
import ui.ItempickButton;
import ui.MapsButton;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.*;
import static utils.Constant.UI.MapsBtn.MARGIN_TB_MAP;
import static utils.Constant.UI.SkillButton.ItemBtn.MARGIN_LR_MAP;


public class PickItem extends States implements Statemethods{
    private BufferedImage bg,title;
    private ItempickButton button;
    private ItemPick[] item = new ItemPick[2];
    private int player = 1;
    private ItemStates item_p1 = ItemStates.NULL, item_p2 = ItemStates.NULL,temp;
    PlayerStates p1, p2;
    public PickItem(Game game,PlayerStates p1, PlayerStates p2) {
        super(game);
        this.p1 = p1;
        this.p2 = p2;
        loadBg();
        loadTitle();
        loadImg();
        loadButton();
    }

    @Override
    public void update() {
        for (ItemPick ip : item) {
            ip.update();
        }
        button.update();
    }

    private void loadBg(){
        bg = LoadSave.getSprite(LoadSave.BACKGROUND_DEFAULT);
    }
    private void loadTitle(){
        title = LoadSave.getSprite((LoadSave.PICKITEM_TITLE));
    }
    private void loadButton(){
        button = new ItempickButton(510 - MARGIN_LR_MAP, 620 - MARGIN_TB_MAP, 0, GameStates.PICKMAP);
    }
    private void loadImg(){
        item[0] = new ItemPick(380,220,0);
        item[1] = new ItemPick(760,220,1);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(bg, 0, 0, 1400, 750, null);
        g2.drawImage(title, 399, 42, 611, 107, null);
        for (ItemPick item : item){
            item.draw(g2);
        }
        new ItemPick().drawDetails(g2);
        g2.drawString("Player: " + player, 100, 100);
        button.draw(g2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        button.setMouseOver(false);
        if (isInIb(button, e)) {
            button.setMouseOver(true);
        }
        for (ItemPick ip : item) {
            ip.setMouseOver(false);
        }
        for (ItemPick ip : item) {
            if (isInIp(ip, e)) {
                ip.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isInIb(button, e)) {
            button.setMousePressed(true);
        }
        for (ItemPick ip : item) {
            if (isInIp(ip, e)) {
                ip.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        temp = ItemStates.NULL;
        for (ItemPick ip : item) {
            if (isInIp(ip, e)) {
                if (ip.isMousePressed()) {
                    if (ip.getBounds().getY() == 220) {
                        if (ip.getBounds().getX() == 380) {
                            item[0].setMouseReleased(true);
                            item[1].setMouseReleased(false);
                            temp = ItemStates.SHIELD;
                        } else if (ip.getBounds().getX() == 760) {
                            item[0].setMouseReleased(false);
                            item[1].setMouseReleased(true);
                            temp = ItemStates.SWORD;
                        }
                    }
                    if(player == 1){
                        item_p1 = temp;
                    }else{
                        item_p2 = temp;
                    }

                }
                break;
            }
        }
        if (isInIb(button, e)) {
            if (button.isMousePressed()) {
                if (player == 2) {
                    if(item_p1 != ItemStates.NULL && item_p2 != ItemStates.NULL){
//                        game.setPlaying(new Playing(game, p1, p2));
//                        game.setPickMap(new PickMap(game, getP1(), getP2()));
                        game.setPickMap(new PickMap(game, p1,p2,getP1(), getP2()));
                        button.applyGameState();
                    }
                }
                if(item_p1 != ItemStates.NULL || item_p2 != ItemStates.NULL){
                    item[0].setMouseReleased(false);
                    item[1].setMouseReleased(false);
                    player = 2;
                }
            }
        }
        resetButton();
    }
    private void resetButton() {
        button.resetBool();
        for (ItemPick ip : item) {
            ip.resetBool();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
    public ItemStates getP1(){
        return item_p1;
    }
    public ItemStates getP2(){
        return item_p2;
    }
}