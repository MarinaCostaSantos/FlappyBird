package fb_projectgame.Control;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Position;
import fb_projectgame.gui.GUI;

public class BirdController extends Controller {

    public BirdController(Arena arena) {
        super(arena);
    }

    public void jumpBird() {
        moveBird(arena.getBird().getPosition().getUp());
    }

    public void downBird() {
        moveBird(arena.getBird().getPosition().getDown());
    }

    private void moveBird(Position position) {
        arena.getBird().setPosition(position);
    }

    public void doAction(GUI.ACTION action) {
        if (action == GUI.ACTION.JUMP) jumpBird();
        if (action == GUI.ACTION.NONE) downBird();
    }
}
