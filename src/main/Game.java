package main;

import entities.Wizard;
import gamestates.*;
import gamestates.Menu;

import java.awt.*;

public class Game implements Runnable{

    private Thread gameThread;
    private final int fps = 120;
    private final int ups = 200;
    GamePanel gp;
    private Playing playing;
    private Menu menu;
    private PickChar pickChar;
    private PickItem pickItem;
    private PickMap pickMap;
    public Game() {
        initClasses();
        gp = new GamePanel(this);
        GameWIndow gw = new GameWIndow(gp);
        gp.requestFocus();
        startGame();
    }

    private void initClasses(){
        menu = new Menu(this);
        playing = new Playing(this);
        pickChar = new PickChar(this);
        pickItem = new PickItem(this);
        pickMap = new PickMap(this);
    }

    public void startGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    void update(){
        switch (GameStates.state){
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case PICKCHAR:
                pickChar.update();
                break;
            case PICKITEM:
                pickItem.update();
                break;
            case PICKMAP:
                pickMap.update();
                break;
            case EXIT:
                System.exit(0);
            default:
                break;
        }
    }
    public void render(Graphics2D g2){
        switch (GameStates.state){
            case MENU:
                menu.draw(g2);
                break;
            case PLAYING:
                playing.draw(g2);
                break;
            case PICKCHAR:
                pickChar.draw(g2);
                break;
            case PICKITEM:
                pickItem.draw(g2);
                break;
            case PICKMAP:
                pickMap.draw(g2);
                break;
            default:
                break;
        }
    }

    public Playing getPlaying() {
        return playing;
    }

    public Menu getMenu() {
        return menu;
    }

    public PickChar getPickChar() {
        return pickChar;
    }

    public PickItem getPickItem() {
        return pickItem;
    }

    public PickMap getPickMap() {
        return pickMap;
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / fps;
        double timePerUpdate = 1000000000.0 / ups;
        long prevTime = System.nanoTime();

        int frame = 0;
        int update = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while(true){
            long currTime = System.nanoTime();
            deltaU += (currTime - prevTime) / timePerUpdate;
            deltaF += (currTime - prevTime) / timePerFrame;
            prevTime = currTime;

            if(deltaU >= 1){
                update();
                update++;
                deltaU--;
            }

            if(deltaF >= 1){
                gp.repaint();
                frame++;
                deltaF--;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: "+frame + "| UPS: " + update);
                frame = 0;
                update = 0;
            }
        }
    }


}
