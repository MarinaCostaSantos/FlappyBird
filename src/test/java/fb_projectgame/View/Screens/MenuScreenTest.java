package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import fb_projectgame.Model.Menu.MenuItems;
import fb_projectgame.Model.Menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class MenuScreenTest extends Assertions {

    MenuScreen menuScreen;
    Menu menu;
    TextGraphics textGraphics;
    MenuItemScreen play;
    MenuItemScreen instructions;
    MenuItemScreen exit;

    @BeforeEach
    void init(){

        play = Mockito.mock(MenuItemScreen.class);
        instructions = Mockito.mock(MenuItemScreen.class);
        exit = Mockito.mock(MenuItemScreen.class);

        menu = Mockito.mock(Menu.class);
        menuScreen = Mockito.spy(new MenuScreen(menu));

        Mockito.when(menu.getSelected()).thenReturn(MenuItems.PLAY);


        Mockito.doReturn(play).when(menuScreen).getPlay();
        Mockito.doReturn(instructions).when(menuScreen).getInstructions();
        Mockito.doReturn(exit).when(menuScreen).getExit();


        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        Mockito.doReturn(screen).when(menuScreen).getScreen();
        Mockito.doNothing().when(menuScreen).choose(Mockito.any());

        textGraphics = Mockito.mock(TextGraphics.class);
        menuScreen.setGraphics(textGraphics);

    }

    @Test
    void initScreen()  {
        try{
            //given
            menuScreen.setGraphics(null);
            Mockito.doCallRealMethod().when(menuScreen).getScreen();

            TerminalPosition p1 = Mockito.mock(TerminalPosition.class);
            TerminalPosition p2 = Mockito.mock(TerminalPosition.class);
            TerminalPosition p3 = Mockito.mock(TerminalPosition.class);

            Mockito.doReturn(p1).when(menuScreen).getTerminalPosition(0.4, MenuItems.PLAY.toString().length());
            Mockito.doReturn(p2).when(menuScreen).getTerminalPosition(0.5, MenuItems.INSTRUCTIONS.toString().length());
            Mockito.doReturn(p3).when(menuScreen).getTerminalPosition(0.6, MenuItems.EXIT.toString().length());

            //when
            menuScreen.initScreen();

            //then
            Mockito.verify(play).setGraphics(Mockito.notNull());
            Mockito.verify(instructions).setGraphics(Mockito.notNull());
            Mockito.verify(exit).setGraphics(Mockito.notNull());

            Mockito.verify(play).setPosition(p1);
            Mockito.verify(instructions).setPosition(p2);
            Mockito.verify(exit).setPosition(p3);

        } catch (IOException | FontFormatException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createPlay(){
        MenuScreen menuS = new MenuScreen(menu);

        // when
        MenuItemScreen play = menuS.getPlay();
        MenuItemScreen instructions = menuS.getInstructions();
        MenuItemScreen exit = menuS.getExit();

        // then
        assertEquals(play.getMenuItem(), MenuItems.PLAY);
        assertEquals(instructions.getMenuItem(),MenuItems.INSTRUCTIONS);
        assertEquals(exit.getMenuItem(), MenuItems.EXIT);
    }

    @Test
    void getTerminalPosition(){
        //given
        TerminalSize size = new TerminalSize(50,40);
        double percentage = 0.7;
        int strlen = 20;
        TerminalPosition position = new TerminalPosition(size.getColumns()/2-strlen/2, (int)(size.getRows()*percentage));

        Mockito.doReturn(size).when(menuScreen).getSize();

        // when
        TerminalPosition p = menuScreen.getTerminalPosition(percentage, strlen);


        //then
        assertEquals(p, position);

    }


    @Test
    void choosePlay(){
        // given
        Mockito.doCallRealMethod().when(menuScreen).choose(Mockito.any());

        // when
        menuScreen.choose(MenuItems.PLAY);

        //then
        Mockito.verify(play, Mockito.times(1)).setSelected(false);
         Mockito.verify(instructions, Mockito.times(1)).setSelected(false);
        Mockito.verify(exit, Mockito.times(1)).setSelected(false);

        Mockito.verify(play, Mockito.times(1)).setSelected(true);
        Mockito.verify(instructions, Mockito.never()).setSelected(true);
        Mockito.verify(exit,Mockito.never()).setSelected(true);
    }


    @Test
    void chooseInstructions(){
        // given
        Mockito.doCallRealMethod().when(menuScreen).choose(Mockito.any());

        // when
        menuScreen.choose(MenuItems.INSTRUCTIONS);

        //then
        Mockito.verify(play, Mockito.times(1)).setSelected(false);
        Mockito.verify(instructions, Mockito.times(1)).setSelected(false);
        Mockito.verify(exit, Mockito.times(1)).setSelected(false);

        Mockito.verify(play, Mockito.never()).setSelected(true);
        Mockito.verify(instructions, Mockito.times(1)).setSelected(true);
        Mockito.verify(exit,Mockito.never()).setSelected(true);
    }

    @Test
    void chooseExit(){
        // given
        Mockito.doCallRealMethod().when(menuScreen).choose(Mockito.any());

        // when
        menuScreen.choose(MenuItems.EXIT);

        //then
        Mockito.verify(play, Mockito.times(1)).setSelected(false);
        Mockito.verify(instructions, Mockito.times(1)).setSelected(false);
        Mockito.verify(exit, Mockito.times(1)).setSelected(false);

        Mockito.verify(play, Mockito.never()).setSelected(true);
        Mockito.verify(instructions, Mockito.never()).setSelected(true);
        Mockito.verify(exit,Mockito.times(1)).setSelected(true);
    }

    @Test
    void draw() throws IOException {

        // when
        menuScreen.draw();

        // then
        Mockito.verify(menuScreen, Mockito.times(1)).choose(Mockito.any());
        Mockito.verify(menuScreen, Mockito.times(1)).clear();
        Mockito.verify(menuScreen, Mockito.times(1)).refresh();
    }

    @Test
    void drawCallTitle() throws IOException {

        // when
        menuScreen.draw();

        // then
        Mockito.verify(menuScreen, Mockito.times(1)).drawTitle();
    }

    @Test
    void drawPlay() throws IOException {

        // when
        menuScreen.draw();

        // then
        Mockito.verify(play, Mockito.times(1)).draw();
    }


    @Test
    void drawInstructions() throws IOException {

        // when
        menuScreen.draw();

        // then
        Mockito.verify(instructions, Mockito.times(1)).draw();
    }

    @Test
    void drawExit() throws IOException {

        // when
        menuScreen.draw();

        // then
        Mockito.verify(exit, Mockito.times(1)).draw();
    }

}