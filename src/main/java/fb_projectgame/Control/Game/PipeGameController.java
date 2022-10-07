package fb_projectgame.Control.Game;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;

import java.util.*;

public class PipeGameController extends GameController {

    public PipeGameController(Arena arena) {
        super(arena);
    }


    public void movePipes() {

        List<Pipe> newpipes = new ArrayList<>();

        Pipe pipe_aux;

        for (int i=0; i<arena.pipes.size(); i++){
            pipe_aux = arena.pipes.get(i);
            pipe_aux.setPosition(pipe_aux.getPosition().getLeft()) ;
            newpipes.add(pipe_aux);
        }

        arena.setPipes(newpipes);

    }

    private void movePipe(Pipe pipe,Position position ) {
            pipe.setPosition(position);
    }
}

