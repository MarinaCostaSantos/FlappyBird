package fb_projectgame.Control;

import fb_projectgame.Model.Arena.Arena;

public abstract class Controller {
    protected final Arena arena;

    public Controller(Arena arena) {
        this.arena = arena;
    }

    public Arena getArena() {
        return arena;
    }


}
