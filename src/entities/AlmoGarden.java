package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AlmoGarden {
    int x, y;
    BufferedImage mapImg;
    public AlmoGarden(int x, int y){
        this.x = x;
        this.y = y;
        loadMap();
    }

    public void loadMap(){
        mapImg = LoadSave.getSprite(LoadSave.ALMO_GARDEN);
    }

    public void render(Graphics2D g2){
        g2.drawImage(mapImg, 0, 0, null);
    }
}
