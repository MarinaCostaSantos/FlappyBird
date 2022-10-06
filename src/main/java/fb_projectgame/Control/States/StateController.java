package fb_projectgame.Control.States;

import java.io.IOException;

public interface StateController {
    void run() throws IOException;

    void nextState();

    public enum ApplicationState {
        Menu, Game, LeaderBoard,Instructions, Exit, GameOver
    }

}