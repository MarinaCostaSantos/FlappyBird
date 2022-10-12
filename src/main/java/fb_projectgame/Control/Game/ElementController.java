package fb_projectgame.Control.Game;

import fb_projectgame.Model.Arena.Arena;

public abstract class ElementController {
    protected final Arena arena;

    public ElementController(Arena arena) {
        this.arena = arena;
    }

    public Arena getArena() {
        return arena;
    }


}
