package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TextColor;
import fb_projectgame.Model.Menu.MenuItems;
import fb_projectgame.Model.Menu.Menu;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuItemScreenTest extends Assertions {
    MenuItemScreen menuItemScreen;
    TextGraphics graphics;
    TerminalPosition position;

    @BeforeEach
    void init(){
        graphics = Mockito.mock(TextGraphics.class);
        position = Mockito.mock(TerminalPosition.class);

        menuItemScreen = Mockito.spy(new MenuItemScreen(MenuItems.PLAY));
        menuItemScreen.setGraphics(graphics);
        menuItemScreen.setPosition(position);
    }

    @Test
    void drawCallNotSelected() throws IOException {
        // given
        Mockito.doReturn(false).when(menuItemScreen).isSelected();
        //when
        menuItemScreen.draw();

        //then
        Mockito.verify(menuItemScreen, Mockito.times(0)).drawSelected();
        Mockito.verify(menuItemScreen, Mockito.times(1)).drawNotSelected();
    }

    @Test
    void drawCallSelected() throws IOException{
        // given
        Mockito.doReturn(true).when(menuItemScreen).isSelected();
        //when
        menuItemScreen.draw();

        //then
        Mockito.verify(menuItemScreen, Mockito.times(1)).drawSelected();
        Mockito.verify(menuItemScreen, Mockito.times(0)).drawNotSelected();
    }

    @Test
    public void drawSelected(){
        Mockito.doReturn(position).when(menuItemScreen).getPosition();
        // when
        menuItemScreen.drawSelected();

        //then
        Mockito.verify(graphics).setForegroundColor(TextColor.Factory.fromString("#1B2BA3"));
        Mockito.verify(graphics).putString(position, menuItemScreen.getMenuItem().toString());
    }

    @Test
    public void drawNotSelected(){
        Mockito.doReturn(position).when(menuItemScreen).getPosition();
        // when
        menuItemScreen.drawNotSelected();

        //then
        Mockito.verify(graphics).setForegroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics).putString(position, menuItemScreen.getMenuItem().toString());
    }

    @Test
    void isSelected(){
        // when
        boolean selected1 = menuItemScreen.isSelected();
        menuItemScreen.setSelected(true);
        boolean selected2 = menuItemScreen.isSelected();
        menuItemScreen.setSelected(false);
        boolean selected3 = menuItemScreen.isSelected();

        // then

        assertFalse(selected1);
        assertTrue(selected2);
        assertFalse(selected3);

    }

    @Test
    void getMenuItem(){
        // given
        MenuItemScreen play = new MenuItemScreen(MenuItems.PLAY);
       MenuItemScreen exit = new MenuItemScreen(MenuItems.EXIT);

        // when
        MenuItems p = play.getMenuItem();
        MenuItems e = exit.getMenuItem();

        //then
        assertEquals(p, MenuItems.PLAY);
        assertEquals(e, MenuItems.EXIT);

    }

    @Test
    void getTerminalPosition() {
        //given
        TerminalPosition terminalPositionMock = Mockito.mock(TerminalPosition.class);
        MenuItemScreen play = new MenuItemScreen(MenuItems.PLAY);

        //when
        play.setPosition(terminalPositionMock);

        //then
        assertEquals(terminalPositionMock, play.getPosition());
    }

}
