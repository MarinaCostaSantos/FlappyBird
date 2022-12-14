package fb_projectgame.Control.States;

import com.googlecode.lanterna.screen.TerminalScreen;
import fb_projectgame.Control.Game.BirdElementController;
import fb_projectgame.Control.Game.LaserBeamController;
import fb_projectgame.Control.Game.PipeElementController;
import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Elements.LaserBeam;
import fb_projectgame.Model.Position;
import fb_projectgame.View.Screens.ScreenView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GameControllerTest {

    ScreenController context;

    GameController gamecontrollerspy;

    Arena arena;

    Bird bird;

    Position position;

    BirdElementController birdelementcontroller;

    PipeElementController pipeElementController;

    LaserBeamController laserBeamController;

    ScreenView screenviewmock;

    TerminalScreen screenmock;

    ArrayList<LaserBeam> laserbeams;


    @BeforeEach
    public void init() {

        context = Mockito.mock(ScreenController.class);
        Mockito.when(context.getStateControler()).thenReturn(Mockito.mock(GameOverController.class));

        GameController gameController = new GameController(context);
        gamecontrollerspy = Mockito.spy(gameController);

        screenviewmock = Mockito.mock(ScreenView.class);
        screenmock = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenviewmock.getScreen()).thenReturn(screenmock);
        Mockito.when(gamecontrollerspy.getScreenView()).thenReturn(screenviewmock);

        birdelementcontroller = Mockito.mock(BirdElementController.class);
        Mockito.when(gamecontrollerspy.getBirdController()).thenReturn(birdelementcontroller);

        pipeElementController=Mockito.mock(PipeElementController.class);
        Mockito.when(gamecontrollerspy.getPiperController()).thenReturn(pipeElementController);

        laserBeamController=Mockito.mock(LaserBeamController.class);
        Mockito.when(gamecontrollerspy.getLaserBeamController()).thenReturn(laserBeamController);

        arena = Mockito.mock(Arena.class);
        Mockito.when(gamecontrollerspy.getArena()).thenReturn(arena);
        Mockito.when(birdelementcontroller.getArena()).thenReturn(arena);


        bird = Mockito.mock(Bird.class);
        Mockito.when(arena.getBird()).thenReturn(bird);

        position = Mockito.mock(Position.class);
        Mockito.when(bird.getPosition()).thenReturn(position);

        laserbeams=Mockito.mock(ArrayList.class);
        Mockito.when(bird.getLaserBeams()).thenReturn(laserbeams);


    }

    @Test
    public void getArena() {

        Assertions.assertNotNull(gamecontrollerspy.getArena());
    }

    @Test
    public void getScreenView() {

        Assertions.assertNotNull(gamecontrollerspy.getScreenView());
    }

    @Test
    public void getBirdController() {

        Assertions.assertNotNull(gamecontrollerspy.getBirdController());
    }

    @Test
    public void getPipeController() {

        Assertions.assertNotNull(gamecontrollerspy.getPiperController());
    }

    @Test
    public void getLaserBeamController() {

        Assertions.assertNotNull(gamecontrollerspy.getLaserBeamController());
    }

    @Test
    public void keyTyped (){

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        gamecontrollerspy.keyTyped(e);

        Mockito.verify(gamecontrollerspy, Mockito.times(1)).keyTyped(e);

    }

    @Test
    public void keyReleased(){

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        gamecontrollerspy.keyReleased(e);

        Mockito.verify(gamecontrollerspy, Mockito.times(1)).keyReleased(e);

    }

    @Test
    public void exitGameWhenBirdDies() throws IOException, URISyntaxException, FontFormatException {

        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Game, ApplicationState.Game, ApplicationState.Menu);
        Mockito.when(arena.Collision(bird)).thenReturn(false, true);

        gamecontrollerspy.run();

        Mockito.verify(gamecontrollerspy, Mockito.times(5)).getScreenView();
        Mockito.verify(screenviewmock, Mockito.times(1)).draw();
        Mockito.verify(arena, Mockito.times(2)).Collision(bird);


    }

    @Test
    public void exitGameEscape() throws URISyntaxException, IOException, FontFormatException {

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);

        gamecontrollerspy.keyPressed(e);

        Mockito.verify(gamecontrollerspy, Mockito.times(1)).nextState();
    }

    @Test
    public void jumpBird()  {

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);

        gamecontrollerspy.keyPressed(e);

        Mockito.verify(birdelementcontroller, Mockito.times(1)).jumpBird();

        Mockito.verify(arena,Mockito.times(2)).CollisionLaserBeam(laserbeams);

    }

    @Test
    public void shootBean() {

        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        Mockito.when(bird.getCountLaserBeams()).thenReturn(4);

        gamecontrollerspy.keyPressed(e);

        Mockito.verify(arena, Mockito.times(4)).getBird();
        Assertions.assertEquals(1,bird.countLaserBeams);


    }

    @Test
    public void nextStateGameOver() throws URISyntaxException, IOException, FontFormatException {

        gamecontrollerspy.nextState();
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.GameOver);
        Mockito.verify((GameOverController)context.getStateControler(), Mockito.times(1)).setScore(0);



    }

    @Test
    void startRun() throws IOException, URISyntaxException, FontFormatException {
        // when
        gamecontrollerspy.run();

        // then
        Mockito.verify(screenviewmock, Mockito.times(1)).initScreen();

        Mockito.verify(screenviewmock, Mockito.times(1)).addKeyListenner(gamecontrollerspy);

    }

   @Test
    void runLaserBeamsCollision () throws URISyntaxException, IOException, FontFormatException {

        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Menu);

        gamecontrollerspy.run();

        Mockito.verify(arena,Mockito.times(5)).CollisionLaserBeam(laserbeams);


    }

    @Test
    void runMovePipes () throws URISyntaxException, IOException, FontFormatException {

        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Menu);

        gamecontrollerspy.run();

        Mockito.verify(pipeElementController,Mockito.times(1)).movePipes();


    }

    @Test
    void runMoveLaserBeams () throws URISyntaxException, IOException, FontFormatException {

        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Menu);

        gamecontrollerspy.run();

        Mockito.verify(laserBeamController,Mockito.times(1)).moveLaserBeams();


    }



    @Test
    void endRun() throws IOException, URISyntaxException, FontFormatException {
        // when
        gamecontrollerspy.run();

        // then
        Mockito.verify(gamecontrollerspy, Mockito.times(1)).nextState();
        Mockito.verify(screenviewmock, Mockito.times(1)).close();
        Mockito.verify(screenviewmock, Mockito.times(1)).removeKeyListenner(gamecontrollerspy);

    }

    @Test
    void testRunWhileStopMenu() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Game, ApplicationState.Menu);

        // when
        gamecontrollerspy.run();

        // then
        Mockito.verify(birdelementcontroller, Mockito.times(1)).downBird();
        Mockito.verify(screenviewmock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileStopExit() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Game , ApplicationState.Exit);


        // when
        gamecontrollerspy.run();

        // then
        Mockito.verify(birdelementcontroller, Mockito.times(1)).downBird();
        Mockito.verify(screenviewmock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileStopGameOver() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Game , ApplicationState.GameOver);


        // when
        gamecontrollerspy.run();

        // then
        Mockito.verify(birdelementcontroller, Mockito.times(1)).downBird();
        Mockito.verify(screenviewmock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileInstructions() throws IOException, URISyntaxException, FontFormatException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Game , ApplicationState.Instructions);


        // when
        gamecontrollerspy.run();

        // then
        Mockito.verify(birdelementcontroller, Mockito.times(1)).downBird();
        Mockito.verify(screenviewmock, Mockito.times(2)).draw();

    }



}


