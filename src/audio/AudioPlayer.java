package audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class AudioPlayer {
    public static int MENU = 0;
    public static int FIGHT_BG = 1;
    public static int FIREBALL = 0;
    public static int FIREJET = 1;
    public static int SLASH_1 = 2;
    public static int SLASH_2 = 3;
    public static int SLASH_3 = 4;
    public static int SLASH_4 = 5;
    public static int STAB_1 = 6;
    public static int STAB_2 = 7 ;
    public static int STAB_3 = 8;
    public static int RAGE_DWARF = 9;
    public static int HEALING = 10;

//    public static int WINNER = 11;
//    public static int DEAD = 12;
//    public static int HIT = 13;

    private Clip[] songs, effects;
    private int currentSongId;
    private float volume = 0.5f;
//    private boolean songMute, effectMute;
//    private Random ran = new Random();

    public AudioPlayer(){
        loadSongs();
        loadEffect();
        playSong(MENU);
    }

    private void loadSongs(){
        String song[] = {"menu", "fightBGSong"};
        songs = new Clip[song.length];

        for (int i = 0; i < songs.length; i++) {
            songs[i] = getClip(song[i]);
        }
    }

    private void loadEffect(){
        String[] effectsNames = {"fireball", "fireJett", "slash_1", "slash_2", "slash_3_dwarf", "slash_4", "stab_1", "stab_2", "stab_3", "dwarfRage", "heal"};
        effects = new Clip[effectsNames.length];
        for (int i = 0; i < effects.length; i++) {
            effects[i] = getClip(effectsNames[i]);
        }
    }

    private Clip getClip(String name){
        URL url = getClass().getResource("/audio/" + name + ".wav");
        AudioInputStream audio;

        try {
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopSong(){
        if(songs[currentSongId].isActive()){
            songs[currentSongId].stop();
        }
    }

//    public void winSong(){
//        stopSong();
//        playEffect();
//    }

    public void playEffect(int effect){
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
    }

    public void playSong(int song){
        stopSong();

        currentSongId = song;
        songs[currentSongId].setMicrosecondPosition(0);
        songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
    }
}
