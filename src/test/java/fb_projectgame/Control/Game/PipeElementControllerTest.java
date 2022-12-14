package fb_projectgame.Control.Game;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

public class PipeElementControllerTest {

    Arena arena;

    PipeElementController pipeController;

    @BeforeEach
    public void init() {

        arena= Mockito.mock(Arena.class);
        pipeController=new PipeElementController(arena);

    }


    @Test
    public void movePipes() {


        List<Pipe> pipez= new ArrayList<>();
        Pipe pipe1=new Pipe(5,20,1);
        Pipe pipe2=new Pipe(8,20,1);
        pipez.add(pipe1);
        pipez.add(pipe2);


        Mockito.when(arena.getPipes()).thenReturn(pipez);


        pipeController.movePipes();

        Assertions.assertEquals(4,arena.getPipes().get(0).getPosition().getX());
        Assertions.assertEquals(7,arena.getPipes().get(1).getPosition().getX());


    }

    @Test
    public void movePipe()  {


       Pipe pipe=Mockito.mock(Pipe.class);
       Position position=Mockito.mock(Position.class);


        pipeController.movePipe(pipe,position);

        Mockito.verify(pipe,Mockito.atLeastOnce()).setPosition(position);


    }

    @Test
    public void getArena (){

        Assertions.assertSame(arena, pipeController.getArena());
    }



}

