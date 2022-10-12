package fb_projectgame.Control;

import com.googlecode.lanterna.screen.TerminalScreen;
import fb_projectgame.Control.States.ApplicationState;
import fb_projectgame.Control.States.InstructionsController;
import fb_projectgame.Control.States.ScreenController;
import fb_projectgame.Control.States.WinController;
import fb_projectgame.View.Screens.ScreenView;
import fb_projectgame.View.Screens.WinScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class WinControllerTest {

    ScreenController context;
    WinScreen screenViewMock;
    TerminalScreen screenMock;

    WinController WinControllerSpy;

    @BeforeEach
    void init(){
        // create context
        context = Mockito.mock(ScreenController.class);

        // create InstructionController
        WinControllerSpy = Mockito.spy(new WinController(context));

        // create screens Mocks
        screenViewMock = Mockito.mock(WinScreen.class);
        screenMock = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        Mockito.when(WinControllerSpy.getScreenView()).thenReturn(screenViewMock);
    }

    @Test
    void getScreenView (){

        WinControllerSpy.getScreenView();
        Mockito.verify(WinControllerSpy,Mockito.times(1)).getScreenView();
    }

    @Test
    public void keyTyped (){

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        WinControllerSpy.keyTyped(e);

        Mockito.verify(WinControllerSpy, Mockito.times(1)).keyTyped(e);

    }

    @Test
    public void keyReleased(){

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        WinControllerSpy.keyReleased(e);

        Mockito.verify(WinControllerSpy, Mockito.times(1)).keyReleased(e);

    }

    @Test
    void processKeyEscape() throws URISyntaxException, IOException, FontFormatException {
        // when
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ESCAPE, '\n');
        WinControllerSpy.keyPressed(e);

        // then
        Mockito.verify(WinControllerSpy, Mockito.times(1)).nextState();
    }

    @Test
    void startRun() throws IOException, URISyntaxException, FontFormatException {
        // when
        WinControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(WinControllerSpy);
    }

    @Test
    void endRun() throws IOException, URISyntaxException, FontFormatException {
        // when
        WinControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(screenViewMock, Mockito.times(1)).removeKeyListenner(WinControllerSpy);
    }

    @Test
    void testRun() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Win,ApplicationState.Menu);


        // when
        WinControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }

    @Test
    void nexstate() throws URISyntaxException, IOException, FontFormatException {
        WinControllerSpy.nextState();
        Mockito.verify(context,Mockito.times(1)).changeState(ApplicationState.Menu);

    }
}
