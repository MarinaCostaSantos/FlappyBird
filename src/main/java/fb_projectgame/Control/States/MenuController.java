package fb_projectgame.Control.States;

import fb_projectgame.Model.Menu.Menu;
import fb_projectgame.View.Screens.MenuScreen;
import fb_projectgame.View.Screens.ScreenView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;


public class MenuController implements StateController, KeyListener {

    private final ScreenController context;
    private final ScreenView screenView;
    private final Menu menu;

    public MenuController(ScreenController context){
        this.context = context;
        this.menu = new Menu();
        this.screenView = new MenuScreen(menu);
    }

    @Override
    public void run() throws URISyntaxException, FontFormatException, IOException{
        getScreenView().initScreen();
        getScreenView().addKeyListenner(this);

        while (context.getApplicationState() == ApplicationState.Menu) {
            getScreenView().draw();

            if(getMenu().isChoosed())
                nextState();
        }


        getScreenView().removeKeyListenner(this);
        getScreenView().close();
    }

    public ScreenView getScreenView() {
        return screenView;
    }

    @Override
    public void nextState() throws URISyntaxException, FontFormatException, IOException{
        switch (getMenu().getSelected()){
            case PLAY -> context.changeState(ApplicationState.Game);
            case INSTRUCTIONS -> context.changeState(ApplicationState.Instructions);
            case EXIT -> context.changeState(ApplicationState.Exit);
        }
    }

    public Menu getMenu() {
        return menu;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

            try {
                switch (e.getKeyCode()){
                case KeyEvent.VK_DOWN -> getMenu().selectNext();
                case KeyEvent.VK_UP -> getMenu().selectprevious();
                case KeyEvent.VK_ENTER -> getMenu().choose();
                case KeyEvent.VK_ESCAPE -> context.changeState(ApplicationState.Exit);

                }

            } catch (URISyntaxException | FontFormatException | IOException i){
                i.printStackTrace();
            }


    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}