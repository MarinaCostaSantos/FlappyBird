package fb_projectgame.Control.States;

import fb_projectgame.Constants;
import fb_projectgame.Control.Game.BirdElementController;
import fb_projectgame.Control.Game.PipeElementController;
import fb_projectgame.Control.MusicManager;
import fb_projectgame.Control.Sounds;
import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.View.Screens.GameScreen;
import fb_projectgame.View.Screens.ScreenView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameController implements StateController, KeyListener {

    private final GameScreen screenView;

    private final Arena arena;
    private final ScreenController context;

    private final BirdElementController birdController;
    private final PipeElementController piperController;


    public GameController(ScreenController context) {
        this.context = context;
        arena = new Arena(Constants.WIDTH,Constants.HEIGHT);

        this.screenView = new GameScreen(arena);

        this.birdController = new BirdElementController(arena);
        this.piperController = new PipeElementController(arena);
    }

    public Arena getArena() {
        return arena;
    }

    public ScreenView getScreenView() {
        return screenView;
    }
    @Override
    public void run() throws URISyntaxException, FontFormatException, IOException {

        int FPS = 20;
        int frameTime = 1000 / FPS;
        long lastPipeMovement = 0;
        long lastBirdMovement = 0;


        getScreenView().initScreen();
        getScreenView().addKeyListenner(this);

        while (context.getApplicationState() == ApplicationState.Game && !getArena().Collision(getArena().getBird())) {
            long startTime = System.currentTimeMillis();

            getScreenView().draw();


            if (startTime - lastBirdMovement > 100) {
                birdController.downBird();
                lastBirdMovement = startTime;
            }

            if (startTime - lastPipeMovement > 150) {
                piperController.movePipes();
                lastPipeMovement = startTime;
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        nextState();

        getScreenView().removeKeyListenner(this);
        getScreenView().close();

    }




    @Override
    public void nextState() throws URISyntaxException, FontFormatException, IOException{
        context.changeState(ApplicationState.GameOver);
        ((GameOverController)context.getStateControler()).setScore(getArena().getBird().getScore());
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        try {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                nextState();

            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                MusicManager.getInstance().start(Sounds.JUMP);
                this.birdController.jumpBird();
            }

        } catch (URISyntaxException u) {
            u.printStackTrace();
        } catch (FontFormatException f) {
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }





    @Override
    public void keyReleased(KeyEvent e) {

    }
}