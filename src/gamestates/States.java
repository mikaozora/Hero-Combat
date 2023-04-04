package gamestates;

import main.Game;

public class States {
    protected Game game;
    public States(Game game){
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
