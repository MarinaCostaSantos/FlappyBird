package fb_projectgame.Control.States;

import com.googlecode.lanterna.screen.TerminalScreen;
import fb_projectgame.View.Screens.ScreenView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class InstructionsControllerTest {

    ScreenController context;
    ScreenView screenViewMock;
    TerminalScreen screenMock;

    InstructionsController instructionControllerSpy;

    @BeforeEach
    void init(){
        // create context
        context = Mockito.mock(ScreenController.class);

        // create InstructionController
        instructionControllerSpy = Mockito.spy(new InstructionsController(context));

        // create screens Mocks
        screenViewMock = Mockito.mock(ScreenView.class);
        screenMock = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        Mockito.when(instructionControllerSpy.getScreenView()).thenReturn(screenViewMock);
    }

    @Test
    void getScreenView (){

        instructionControllerSpy.getScreenView();
        Mockito.verify(instructionControllerSpy,Mockito.times(1)).getScreenView();
    }

    @Test
    public void keyTyped (){

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        instructionControllerSpy.keyTyped(e);

        Mockito.verify(instructionControllerSpy, Mockito.times(1)).keyTyped(e);

    }

    @Test
    public void keyReleased(){

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        instructionControllerSpy.keyReleased(e);

        Mockito.verify(instructionControllerSpy, Mockito.times(1)).keyReleased(e);

    }

    @Test
    void processKeyEscape() throws URISyntaxException, IOException, FontFormatException {
        // when
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ESCAPE, '\n');
        instructionControllerSpy.keyPressed(e);

        // then
        Mockito.verify(instructionControllerSpy, Mockito.times(1)).nextState();
    }

    @Test
    void startRun() throws IOException, URISyntaxException, FontFormatException {
        // when
        instructionControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(instructionControllerSpy);
    }

    @Test
    void endRun() throws IOException, URISyntaxException, FontFormatException {
        // when
        instructionControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(screenViewMock, Mockito.times(1)).removeKeyListenner(instructionControllerSpy);
    }

    @Test
    void testRun() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Instructions,ApplicationState.Menu);


        // when
        instructionControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }

    @Test
    void nexstate() throws URISyntaxException, IOException, FontFormatException {
        instructionControllerSpy.nextState();
        Mockito.verify(context,Mockito.times(1)).changeState(ApplicationState.Menu);

    }


}
