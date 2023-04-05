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

        public static int getSpriteAmount(int action){
            if(action == IDLE){
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

    public static class UI{
        public static class MenuBtn{
            public static final int WIDTH_BUTTON = 389;
            public static final int HEIGHT_BUTTON = 257;
            public static final int MARGIN_TB = 178/2;
            public static final int MARGIN_LR = 179/2;
        }
    }
}
