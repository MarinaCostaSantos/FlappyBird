package fb_projectgame.Control.States;

import fb_projectgame.View.Screens.WinScreen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class WinController implements StateController, KeyListener {
    private final ScreenController context;
    private final WinScreen screenView;


    public WinController(ScreenController context) {
        this.context = context;
        screenView = new WinScreen();
    }
    public WinScreen getScreenView() {
        return screenView;
    }

    @Override
    public void run() throws URISyntaxException, FontFormatException, IOException {
        getScreenView().initScreen();
        getScreenView().addKeyListenner(this);


        while (context.getApplicationState() == ApplicationState.Win) {
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
            } catch (URISyntaxException| FontFormatException | IOException i){
                i.printStackTrace();
            }

        }
    }



    @Override
    public void keyReleased(KeyEvent e) {

    }


}
