package fb_projectgame.Control.States;

import fb_projectgame.Control.MusicManager;
import fb_projectgame.Control.Sounds;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class ScreenController {

    private StateController stateControler;
    private ApplicationState applicationState;

    public ScreenController() throws URISyntaxException, FontFormatException, IOException{
        changeState(ApplicationState.Menu);
    }

    public void run() throws  IOException, URISyntaxException, FontFormatException{

            while (getStateControler() != null)
                getStateControler().run();

    }


    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public void changeState(ApplicationState state) throws URISyntaxException, FontFormatException, IOException{
        applicationState = state;
        switch (state){
            case Game -> stateControler=new GameController(this);
            case Menu -> {
                if (!MusicManager.getInstance().isPlaying(Sounds.SOUNDTRACK)) {
                    MusicManager.getInstance().stopAll();
                    MusicManager.getInstance().start(Sounds.SOUNDTRACK);
                }
                stateControler = new MenuController(this);
            }
           case Instructions -> stateControler = new InstructionsController(this);
            case GameOver -> {
                MusicManager.getInstance().stopAll();
                MusicManager.getInstance().start(Sounds.GAMEOVER);
                stateControler = new GameOverController(this);
            }
            case Exit-> {
                MusicManager.getInstance().stopAll();
                stateControler=null;
            }
        }
    }

    public StateController getStateControler() {
        return stateControler;
    }
}
