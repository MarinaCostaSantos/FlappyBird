package fb_projectgame.Control.States;

import com.googlecode.lanterna.screen.TerminalScreen;
import fb_projectgame.Control.States.ApplicationState;
import fb_projectgame.Control.States.MenuController;
import fb_projectgame.Control.States.ScreenController;
import fb_projectgame.Model.Menu.Menu;
import fb_projectgame.Model.Menu.MenuItems;
import fb_projectgame.View.Screens.ScreenView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuControllerTest {

    MenuController menuControllerSpy;
    ScreenController context;
    ScreenView screenViewMock;
    TerminalScreen screenMock;
    Menu menu;


    @BeforeEach
    void init(){
        // create context
        context = Mockito.mock(ScreenController.class);

        // create MenuController
        menuControllerSpy = Mockito.spy(new MenuController(context));

        // create menuMoCK
        menu = Mockito.mock(Menu.class);
        Mockito.when(menuControllerSpy.getMenu()).thenReturn(menu);

        // create screens Mocks
        screenViewMock = Mockito.mock(ScreenView.class);
        screenMock = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        Mockito.when(menuControllerSpy.getScreenView()).thenReturn(screenViewMock);

    }

    @Test
    void getScreenView (){

        menuControllerSpy.getScreenView();
        Mockito.verify(menuControllerSpy,Mockito.times(1)).getScreenView();
    }

    @Test
    public void keyTyped (){

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        menuControllerSpy.keyTyped(e);

        Mockito.verify(menuControllerSpy, Mockito.times(1)).keyTyped(e);

    }

    @Test
    public void keyReleased(){

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        menuControllerSpy.keyReleased(e);

        Mockito.verify(menuControllerSpy, Mockito.times(1)).keyReleased(e);

    }

    @Test
    void processKeyArrowDown(){
        // given
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);

        // when
        menuControllerSpy.keyPressed(e);
        // then
        Mockito.verify(menu, Mockito.times(1)).selectNext();
    }

    @Test
    void processKeyArrowUp(){
        // given
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);

        // when
        menuControllerSpy.keyPressed(e);

        // then
        Mockito.verify(menu, Mockito.times(1)).selectprevious();
    }

    @Test
    void processKeyEnter(){
        // given
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ENTER, '\n');

        // when
        menuControllerSpy.keyPressed(e);

        // then
        Mockito.verify(menu, Mockito.times(1)).choose();
    }

    @Test
    void processKeyEscape() throws URISyntaxException, IOException, FontFormatException {
        // given
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ESCAPE, '\n');

        // when
        menuControllerSpy.keyPressed(e);

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Exit);
    }

    @Test
    void startRun() throws IOException, URISyntaxException, FontFormatException {
        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(menuControllerSpy);

    }

    @Test
    void endRun() throws IOException, URISyntaxException, FontFormatException {
        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(screenViewMock, Mockito.times(1)).removeKeyListenner(menuControllerSpy);

    }

    @Test
    void testRunWhileStopGame() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Game);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(2)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileStopExit() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Exit);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(1)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }

    @Test
    void testRunWhileStopInstructions() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Instructions);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(1)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }

    @Test
    void testRunWhileStopGameOver() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.GameOver);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(1)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }

    @Test
    void testRunWhileChoosed_1true() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Exit);
        Mockito.when(menu.isChoosed()).thenReturn(false,true);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menuControllerSpy, Mockito.times(1)).nextState();
    }

    @Test
    void testRunWhileChoosed_2true() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Menu,ApplicationState.Exit);
        Mockito.when(menu.isChoosed()).thenReturn(false,true,true);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menuControllerSpy, Mockito.times(2)).nextState();
    }

    @Test
    void testRunWhileChoosed_0true() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Menu,ApplicationState.Exit);
        Mockito.when(menu.isChoosed()).thenReturn(false,false,false);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menuControllerSpy, Mockito.times(0)).nextState();
    }

    @Test
    void choosePlay() throws URISyntaxException, IOException, FontFormatException {
        // given
        Mockito.when(menu.getSelected()).thenReturn(MenuItems.PLAY);

        // when
        menuControllerSpy.nextState();

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Game);
    }

    @Test
    void chooseInstructions() throws URISyntaxException, IOException, FontFormatException {
        // given
        Mockito.when(menu.getSelected()).thenReturn(MenuItems.INSTRUCTIONS);

        // when
        menuControllerSpy.nextState();

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Instructions);
    }

    @Test
    void chooseExit() throws URISyntaxException, IOException, FontFormatException {
        // given
        Mockito.when(menu.getSelected()).thenReturn(MenuItems.EXIT);

        // when
        menuControllerSpy.nextState();

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Exit);
    }

    @Test
    void testRunStop() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Exit);

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(0)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(0)).draw();

    }





}
