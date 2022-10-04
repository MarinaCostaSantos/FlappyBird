package fb_projectgame.Control;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.View.ArenaViewer;
import fb_projectgame.gui.GUI;

import java.io.IOException;

public class ArenaController extends GameController {

    private final BirdController birdController;
    private final PipeController piperController;

    private final ArenaViewer viewer;
    private GUI gui;

    public ArenaController(Arena arena, ArenaViewer viewer, GUI gui) {
        super(arena);

        this.viewer = viewer;

        this.gui = gui;

        this.birdController = new BirdController(arena);
        this.piperController = new PipeController(arena);
    }

    public void start() throws IOException {

        while (getArena().Collision(getArena().getBird().getPosition()) == false) {
            long startTime = System.currentTimeMillis();

            viewer.draw(getArena());

            GUI.ACTION action = gui.getNextAction();
            if (action == GUI.ACTION.QUIT) break;

            birdController.doAction(action);
            piperController.movePipers();

        }

        viewer.close();
    }
}
