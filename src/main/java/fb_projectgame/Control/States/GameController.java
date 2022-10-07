package fb_projectgame.Control.States;

import fb_projectgame.Constants;
import fb_projectgame.Control.Game.BirdGameController;
import fb_projectgame.Control.Game.PipeGameController;
import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.View.Screens.GameScreen;
import fb_projectgame.View.Screens.ScreenView;
import fb_projectgame.gui.GUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameController implements StateController, KeyListener {

    private final GameScreen screenView;

    private final Arena arena;
    private final ScreenController context;

    private final BirdGameController birdController;
    private final PipeGameController piperController;


    public GameController(ScreenController context)  throws URISyntaxException, FontFormatException, IOException{
        this.context = context;
        arena = new Arena(Constants.WIDTH,Constants.HEIGHT);

        this.screenView = new GameScreen(arena);

        this.birdController = new BirdGameController(arena);
        this.piperController = new PipeGameController(arena);
    }

    public Arena getArena() {
        return arena;
    }
    public BirdGameController getBirdController() {
        return birdController;
    }

    public PipeGameController getPiperController() {
        return piperController;
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


        while (context.getApplicationState() == ApplicationState.Game && getArena().Collision(getArena().getBird().getPosition()) == false) {
            long startTime = System.currentTimeMillis();

           // viewer.draw(getArena());
            getScreenView().draw();

            GUI.ACTION action = this.screenView.getGui().getNextAction();
            if (action == GUI.ACTION.QUIT) break;

            if (startTime - lastBirdMovement > 100) {
                birdController.doAction(action);
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
            }

        }
    }




    @Override
    public void nextState() throws URISyntaxException, FontFormatException, IOException{
        context.changeState(ApplicationState.GameOver);
      //  ((GameOverController)context.getStateControler()).setScore(getGameModel().getPlayer().getScore());
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

            try {
                nextState();
            } catch (URISyntaxException u){
                u.printStackTrace();
            }catch (FontFormatException f){
                f.printStackTrace();
            }catch (IOException i){
                i.printStackTrace();
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}