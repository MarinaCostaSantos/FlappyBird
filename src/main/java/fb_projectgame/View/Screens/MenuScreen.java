package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import fb_projectgame.Constants;
import fb_projectgame.Model.Menu.MenuItems;
import fb_projectgame.Model.Menu.Menu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class MenuScreen extends ScreenView {

    private final Menu menu;
    private final MenuItemScreen play;
    private final MenuItemScreen instructions;
    private final MenuItemScreen exit;

    public MenuScreen(Menu menu){
        this.menu = menu;
        play = new MenuItemScreen(MenuItems.PLAY);
        instructions = new MenuItemScreen(MenuItems.INSTRUCTIONS);
        exit = new MenuItemScreen(MenuItems.EXIT);

    }


    @Override
    public void initScreen() throws URISyntaxException, FontFormatException, IOException {
        super.initScreen();

        getPlay().setGraphics(getGraphics());
        getPlay().setPosition(getTerminalPosition(0.4, play.getMenuItem().toString().length()));

        getInstructions().setGraphics(getGraphics());
        getInstructions().setPosition(getTerminalPosition(0.5, instructions.getMenuItem().toString().length()));

        getExit().setGraphics(getGraphics());
        getExit().setPosition(getTerminalPosition(0.6, exit.getMenuItem().toString().length()));
    }

    @Override
    public void draw() throws IOException {

        choose(menu.getSelected());

        clear();
        drawTitle();

        getPlay().draw();
        getInstructions().draw();
        getExit().draw();

        refresh();

    }

    public void choose(MenuItems menuItem){
        getPlay().setSelected(false);
        getInstructions().setSelected(false);
        getExit().setSelected(false);

        switch (menuItem){
            case PLAY -> getPlay().setSelected(true);
            case INSTRUCTIONS -> getInstructions().setSelected(true);
            case EXIT -> getExit().setSelected(true);
        }
    }

    public MenuItemScreen getPlay() {
        return play;
    }
    public MenuItemScreen getInstructions() {
        return instructions;
    }
    public MenuItemScreen getExit() {
        return exit;
    }


    public void drawTitle(){
        String title = "FLAPPY BIRD";
        setForegroundColor("#000000");
        getGraphics().putString(getTerminalPosition(0.2, title.length()), title);
    }

    public TerminalPosition getTerminalPosition(double percentageRows, int stringLen){
        return new TerminalPosition(getSize().getColumns()/2-stringLen/2, (int)(getSize().getRows()*percentageRows));
    }

    @Override
    public TerminalSize getSize() {
        return new TerminalSize(Constants.WIDTH, Constants.HEIGHT);
    }
}
