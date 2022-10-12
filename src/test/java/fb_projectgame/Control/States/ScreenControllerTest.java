package fb_projectgame.Control.States;

import fb_projectgame.Control.MusicManager;
import fb_projectgame.Control.Sounds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class ScreenControllerTest {

    ScreenController controllerSpy;

    @BeforeEach
    void init(){

        MusicManager manager= Mockito.mock(MusicManager .class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            ScreenController controller = new ScreenController();
            controllerSpy = Mockito.spy(controller);

        } catch (URISyntaxException |IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getApplicationstate(){

       Assertions.assertNotNull(controllerSpy.getApplicationState());
    }

    @Test
    void getStateController(){

        Assertions.assertNotNull(controllerSpy.getStateControler());
    }



    @Test
    void runState() throws IOException {

        MusicManager manager= Mockito.mock(MusicManager .class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            // given
            StateController stateController = Mockito.mock(StateController.class);
            StateController stateController2 = Mockito.mock(StateController.class);

            Mockito.when(controllerSpy.getStateControler()).thenReturn(stateController, stateController, stateController2, stateController2, null);
            // when
            controllerSpy.run();

            // then
            Mockito.verify(stateController, Mockito.times(1)).run();
            Mockito.verify(stateController2, Mockito.only()).run();
            Mockito.verify(controllerSpy, Mockito.times(5)).getStateControler();

        } catch (URISyntaxException| FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void initialState(){

        Assertions.assertEquals(controllerSpy.getApplicationState(), ApplicationState.Menu);
    }

    @Test
    void changeStateGame(){

        MusicManager manager= Mockito.mock(MusicManager .class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            controllerSpy.changeState(ApplicationState.Game);

            // then
            Assertions.assertTrue(controllerSpy.getStateControler() instanceof GameController);
            Assertions.assertEquals(controllerSpy.getApplicationState(), ApplicationState.Game);
        } catch (URISyntaxException | IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void changeStateGameOver(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            controllerSpy.changeState(ApplicationState.GameOver);

            // then
            Mockito.verify(manager, Mockito.times(1)).stopAll();
            Mockito.verify(manager, Mockito.times(1)).start(Sounds.GAMEOVER);
            Assertions.assertTrue(controllerSpy.getStateControler() instanceof GameOverController);
            Assertions.assertEquals(controllerSpy.getApplicationState(), ApplicationState.GameOver);
        } catch (URISyntaxException | IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void changeStateInstructions(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            controllerSpy.changeState(ApplicationState.Instructions);

            // then
            Assertions.assertTrue(controllerSpy.getStateControler() instanceof InstructionsController);
            Assertions.assertEquals(controllerSpy.getApplicationState(), ApplicationState.Instructions);
        } catch (URISyntaxException | IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void changeStateWin(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            controllerSpy.changeState(ApplicationState.Win);

            // then
            Assertions.assertTrue(controllerSpy.getStateControler() instanceof WinController);
            Assertions.assertEquals(controllerSpy.getApplicationState(), ApplicationState.Win);
        } catch (URISyntaxException | IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void changeStateExit(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            controllerSpy.changeState(ApplicationState.Exit);

            // then
            Mockito.verify(manager, Mockito.times(1)).stopAll();
            Assertions.assertNull(controllerSpy.getStateControler());
            Assertions.assertEquals(controllerSpy.getApplicationState(), ApplicationState.Exit);
        } catch (URISyntaxException | IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void changeStateMenuIsPlaying(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            Mockito.when(manager.isPlaying(Sounds.SOUNDTRACK)).thenReturn(true);
            controllerSpy.changeState(ApplicationState.Menu);

            // then
            Mockito.verify(manager, Mockito.never()).stopAll();
            Mockito.verify(manager, Mockito.never()).start(Sounds.SOUNDTRACK);
            Assertions.assertTrue(controllerSpy.getStateControler() instanceof MenuController);
            Assertions.assertEquals(controllerSpy.getApplicationState(), ApplicationState.Menu);
        } catch (URISyntaxException | IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void changeStateMenuIsNotPlaying() throws URISyntaxException, IOException, FontFormatException {

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            Mockito.when(manager.isPlaying(Sounds.SOUNDTRACK)).thenReturn(false);
            controllerSpy.changeState(ApplicationState.Menu);

            // then
            Mockito.verify(manager, Mockito.times(1)).stopAll();
            Mockito.verify(manager, Mockito.times(1)).start(Sounds.SOUNDTRACK);
            Assertions.assertTrue(controllerSpy.getStateControler() instanceof MenuController);
            Assertions.assertEquals(controllerSpy.getApplicationState(), ApplicationState.Menu);
        }
    }

}
