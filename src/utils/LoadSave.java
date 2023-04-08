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
    public static final String ALL_SKILLS = "all_skills.png";
    public static final String BASIC_ATTACK = "bscAtk.png";
    public static final String WIZARD_SKILL1 = "wizSkill1.png";
    public static final String WIZARD_SKILL2 = "wizSkill2.png";
    public static final String WIZARD_ULT = "wizUlt.png";
    public static final String SAMURAI_SKILL1 = "samuraiSkill1.png";
    public static final String SAMURAI_SKILL2 = "samuraiSkill2.png";
    public static final String SAMURAI_ULT = "samuraiUlt.png";
    public static final String WARRIOR_SKILL1 = "warriorSkill1.png";
    public static final String WARRIOR_SKILL2 = "warriorSkill2.png";
    public static final String WARRIOR_ULT = "warriorUlt.png";

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


