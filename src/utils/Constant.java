package utils;

public class Constant {
    public static class WizardConstant{
        public static final int IDLE = 0;
        public static final int RUN = 1;
        public static final int BASIC = 2;
        public static final int ATT1 = 3;
        public static final int ATT2 = 4;
        public static final int ATT3 = 5;
        public static final int DEAD = 6;
        public static final int HITTED = 7;
        public static final int WIDTH = 128;
        public static final int HEIGHT = 128;
        public static final int HP = 500;
        public static final int ATK = 50;
        public static final int DEF = 50;

        public static int getSpriteAmount(int action){
            if(action == IDLE || action == HITTED){
                return 7;
            }else if(action == BASIC || action == ATT1){
                return 4;
            }else if(action == RUN || action == ATT2){
                return 8;
            }else if(action == DEAD){
                return 6;
            }else if(action == ATT3){
                return 14;
            }
            else{
                return 0;
            }
        }
    }
    public static class SamuraiConstant{
        public static final int IDLE = 0;
        public static final int RUN = 2;
        public static final int BASIC = 3;
        public static final int ATT1 = 4;
        public static final int ATT2 = 5;
        public static final int ATT3 = 6;
        public static final int DEAD = 1;
        public static final int HITTED = 7;
        public static final int WIDTH = 128;
        public static final int HEIGHT = 128;
        public static final int HP = 400;
        public static final int ATK = 60;
        public static final int DEF = 50;

        public static int getSpriteAmount(int action){
            if(action == BASIC || action == ATT2){
                return 4;
            }else if(action == RUN){
                return 8;
            }else if(action == DEAD || action == IDLE){
                return 6;
            }else if(action == ATT1){
                return 5;
            }else if(action == ATT3) {
                return 2;
            }else if(action == HITTED){
                return 1;
            } else{
                return 0;
            }
        }
    }
    public static class DwarfConstant{
        public static final int IDLE = 0;
        public static final int RUN = 2;
        public static final int BASIC = 3;
        public static final int ATT1 = 4;
        public static final int ATT2 = 5;
        public static final int ATT3 = 1;
        public static final int DEAD = 6;
        public static final int HITTED = 7;
        public static final int WIDTH = 128;
        public static final int HEIGHT = 128;
        public static final int HP = 600;
        public static final int ATK = 40;
        public static final int DEF = 50;

        public static int getSpriteAmount(int action){
            if(action == BASIC || action == ATT2 || action == ATT1 || action == DEAD){
                return 4;
            }else if(action == RUN || action == IDLE || action == HITTED){
                return 6;
            }else if(action == ATT3){
                return 2;
            } else{
                return 0;
            }
        }
    }
    public static class PlayerPosition{
        public static final int xPosP1 = 167;
        public static final int yPosP1 = 195;
        public static final int xPosP2 = 1265;
        public static final int yPosP2 = 195;
    }

    public static class SkillPosition{
        public static final int xPosBasicAtkP1 = 60, yPosBasicAtkP1 = 600;
        public static final int xPosSkill1P1 = 60, yPosSkill1P1 = 470;
        public static final int xPosSkill2P1 = 60, yPosSkill2P1 = 340;
        public static final int xPosUltP1 = 60, yPosUltP1 = 210;


        public static final int xPosBasicAtkP2 = 1250, yPosBasicAtkP2 = 600;
        public static final int xPosSkill1P2 = 1250, yPosSkill1P2 = 470;
        public static final int xPosSkill2P2 = 1250, yPosSkill2P2 = 340;
        public static final int xPosUltP2 = 1250, yPosUltP2 = 210;

    }

    public static class UI{
        public static class MenuBtn{
            public static final int WIDTH_BUTTON = 389;
            public static final int HEIGHT_BUTTON = 257;
            public static final int MARGIN_TB = 178/2;
            public static final int MARGIN_LR = 179/2;
        }

        public static class CharpickBtn{
            public static final int WIDTH_BUTTON = 803;
            public static final int HEIGHT_BUTTON = 383;
            public static final int MARGIN_TB = 304/2;
            public static final int MARGIN_LR = 304/2;
        }

        public static class Charpick{
            public static final int WIDTH = 282;
            public static final int HEIGHT = 343;
            public static final int MARGIN = 380;
        }

        public static class MapsBtn{
            public static final int WIDTH_BUTTON = 685;
            public static final int HEIGHT_BUTTON = 383;
            public static final int MARGIN_TB_MAP = (685-381)/2;
            public static final int MARGIN_LR_MAP = (383-79)/2;
        }

        public static class MapsImg{
            public static final int WIDTH_IMAGE = 282;
            public static final int HEIGHT_IMAGE = 343;
//            public static final int MARGIN_TB_MAP = (685-381)/2;
//            public static final int MARGIN_LR_MAP = (383-79)/2;
        }
        public static class PauseBtn{
            public static final int WIDTH_BUTTON = 412;
            public static final int HEIGHT_BUTTON = 171;
            public static final int MARGIN_TB = 46;
            public static final int MARGIN_LR = 46;
        }
        public static class SkillButton{
            public static final int SIZE = 89;
        public static class ItemBtn{
            public static final int WIDTH_BUTTON = 685;
            public static final int HEIGHT_BUTTON = 383;
            public static final int MARGIN_TB_MAP = (685-381)/2;
            public static final int MARGIN_LR_MAP = (383-79)/2;
        }

        public static class ItemImg{
            public static final int WIDTH_IMAGE = 282;
            public static final int HEIGHT_IMAGE = 343;
        }
    }
}}