package fb_projectgame.Control.Game;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Position;


public class BirdElementController extends ElementController {
        public BirdElementController(Arena arena) {
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
