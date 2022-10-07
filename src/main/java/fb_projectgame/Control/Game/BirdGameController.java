package fb_projectgame.Control.Game;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Position;
import fb_projectgame.gui.GUI;

public class BirdGameController extends GameController {

    public BirdGameController(Arena arena) {
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

}
