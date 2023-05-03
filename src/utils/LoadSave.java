package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String WIZARD = "wizard.png";
    public static final String MENU_BUTTON = "menu-button.png";
    public static final String BACKGROUND_DEFAULT = "bg1.png";
    public static final String MENU_TITLE = "menu-title.png";
    public static final String PICKCHAR_TITLE = "pickchar-title.png";
    public static final String PICKCHAR_BUTTON = "charpick-button.png";
    public static final String PICKCHAR_CHAR = "pickchar-char.png";
    public static final String SAMURAI = "samurai.png";
    public static final String DWARF = "warior.png";
    public static final String MAPS_IMG = "maps-img.png";
    public static final String CHOOSE_BUTTON = "choose-button.png";
    public static final String ALMO_GARDEN = "almoGarden.png";
    public static final String KING_GARDEN = "kingGarden.png";
    public static final String WIZARD_SKILL = "wizardSkill.png";
    public static final String DWARF_SKILL = "dwarfSkill.png";
    public static final String SAMURAI_SKILL = "samuraiSkill.png";

    public static final String PICKITEM_TITLE = "pickitem-title.png";
    public static final String PICKITEM_ITEM = "pickitem-item.png";
    public static final String PICKITEM_BUTTON = "menu-button.png";
    public static final String BG_PAUSE = "bg-pause.png";
    public static final String PAUSE_BTN = "pause-button.png";
    public static final String BG_P1WIN = "bg-p1win.png";
    public static final String BG_P2WIN = "bg-p2win.png";
    public static  final String STATUS_BAR = "statusbar.png";
    public static BufferedImage getSprite(String fileName){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return img;
    }
}