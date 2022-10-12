package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import fb_projectgame.Model.Menu.MenuItems;

import java.io.IOException;

public class MenuItemScreen {

    private final MenuItems menuItem;
    private TerminalPosition position;

    protected TextGraphics graphics;
    private boolean selected;

    public MenuItemScreen(MenuItems menuItem){
        this.menuItem = menuItem;
        this.selected = false;
        this.position = null;
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public void draw() throws IOException {
        if (isSelected())
            drawSelected();
        else
            drawNotSelected();
    }

    public void drawSelected(){
        getGraphics().setForegroundColor(TextColor.Factory.fromString("#1B2BA3"));
        getGraphics().putString(getPosition(), menuItem.toString());
    }
    public void drawNotSelected(){
        getGraphics().setForegroundColor(TextColor.Factory.fromString("#000000"));
        getGraphics().putString(getPosition(), menuItem.toString());
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setPosition(TerminalPosition position) {
        this.position = position;
    }

    public TerminalPosition getPosition() {
        return position;
    }

    public MenuItems getMenuItem() {
        return menuItem;
    }
}

