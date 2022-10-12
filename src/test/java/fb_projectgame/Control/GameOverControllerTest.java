package fb_projectgame.Control;

import com.googlecode.lanterna.screen.TerminalScreen;
import fb_projectgame.Control.States.ApplicationState;
import fb_projectgame.Control.States.GameOverController;
import fb_projectgame.Control.States.ScreenController;
import fb_projectgame.View.Screens.GameOverScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameOverControllerTest {

    ScreenController context;
    GameOverScreen screenViewMock;
    TerminalScreen screenMock;

    GameOverController gameOverControllerSpy;

    @BeforeEach
    void init(){
        // create context
        context = Mockito.mock(ScreenController.class);

        // create GameOverController
        gameOverControllerSpy = Mockito.spy(new GameOverController(context));

        // create screens Mocks
        screenViewMock = Mockito.mock(GameOverScreen.class);
        screenMock = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        Mockito.when(gameOverControllerSpy.getScreenView()).thenReturn(screenViewMock);
    }

    @Test
    void getScreenView (){

        gameOverControllerSpy.getScreenView();
        Mockito.verify(gameOverControllerSpy,Mockito.times(1)).getScreenView();
    }

    @Test
    public void keyTyped (){

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        gameOverControllerSpy.keyTyped(e);

        Mockito.verify(gameOverControllerSpy, Mockito.times(1)).keyTyped(e);

    }

    @Test
    public void keyReleased(){

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        gameOverControllerSpy.keyReleased(e);

        Mockito.verify(gameOverControllerSpy, Mockito.times(1)).keyReleased(e);

    }

    @Test
    void processKeyEscape() throws URISyntaxException, IOException, FontFormatException {
        // when
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ESCAPE, '\n');
        gameOverControllerSpy.keyPressed(e);

        // then
        Mockito.verify(gameOverControllerSpy, Mockito.times(1)).nextState();
    }

    @Test
    void startRun() throws IOException, URISyntaxException, FontFormatException {
        // when
        gameOverControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(gameOverControllerSpy);
    }

    @Test
    void endRun() throws IOException, URISyntaxException, FontFormatException {
        // when
        gameOverControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(screenViewMock, Mockito.times(1)).removeKeyListenner(gameOverControllerSpy);
    }

    @Test
    void testRun() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.GameOver,ApplicationState.Menu);


        // when
        gameOverControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }

    @Test
    void setScore(){

        //when
        gameOverControllerSpy.setScore(200);

        //then
        Mockito.verify(screenViewMock,Mockito.times(1)).setScore(200);
    }

    @Test
    void nexstate() throws URISyntaxException, IOException, FontFormatException {
        gameOverControllerSpy.nextState();
        Mockito.verify(context,Mockito.times(1)).changeState(ApplicationState.Menu);

    }


}
