package fb_projectgame.Control.Game;

import fb_projectgame.Model.Arena.Arena;

public abstract class GameController {
    protected final Arena arena;

    public GameController(Arena arena) {
        this.arena = arena;
    }

    public Arena getArena() {
        return arena;
    }


}
