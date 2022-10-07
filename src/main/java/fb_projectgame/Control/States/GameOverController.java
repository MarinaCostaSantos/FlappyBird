package fb_projectgame.Control.States;

import fb_projectgame.View.Screens.GameOverScreen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOverController implements StateController, KeyListener {
    private final ScreenController context;
    private GameOverScreen screenView;
    private Integer newScore;

    public GameOverController(ScreenController context) {
        this.context = context;
        screenView = new GameOverScreen();
        newScore = 0;
    }
    public GameOverScreen getScreenView() {
        return screenView;
    }
    @Override
    public void run() throws URISyntaxException, FontFormatException, IOException {
        getScreenView().initScreen();
        getScreenView().addKeyListenner(this);


        while (context.getApplicationState() == ApplicationState.GameOver) {
            getScreenView().draw();
        }

        getScreenView().removeKeyListenner(this);
        getScreenView().close();
    }

    @Override
    public void nextState() throws URISyntaxException, FontFormatException, IOException{
        context.changeState(ApplicationState.Menu);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

            try {
                nextState();
            } catch (URISyntaxException u){
                u.printStackTrace();
            }catch (FontFormatException f){
                f.printStackTrace();
            }catch (IOException i){
                i.printStackTrace();
            }

        }
         }



    public String formatScore(Integer scr) {
        StringBuilder formated = new StringBuilder(Integer.toString(scr));
        for(int i = 0; i < 5 - Integer.toString(scr).length();i++)
            formated.insert(0, "0");
        return formated.toString();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void setScore(int score){
        getScreenView().setScore(score);
        this.newScore = score;
    }

    public void setScreenView(GameOverScreen screenView) {
        this.screenView = screenView;
    }
}
