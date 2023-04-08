package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class KingGarden {
    int x, y;
    BufferedImage mapImg;
    public KingGarden(int x, int y){
        this.x = x;
        this.y = y;
        loadMap();
    }

    public void loadMap(){
        mapImg = LoadSave.getSprite(LoadSave.KING_GARDEN);
    }

    public void render(Graphics2D g2){
        g2.drawImage(mapImg, 0, 0, null);
    }
}
