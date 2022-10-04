package fb_projectgame.Control;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;

public class PipeController extends GameController {

    public PipeController(Arena arena) {
        super(arena);
    }


    public void movePipers() {
        for (Pipe pipe : arena.getPipes())
            movePipers(pipe, pipe.getPosition().run(pipe.getVx()));
    }

    private void movePipers(Pipe pipe,Position position ) {
            pipe.setPosition(position);
    }
}

