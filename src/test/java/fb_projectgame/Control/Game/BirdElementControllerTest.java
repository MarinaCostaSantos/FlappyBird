package fb_projectgame.Control.Game;

import fb_projectgame.Control.Game.BirdElementController;
import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class BirdElementControllerTest {

    Arena arena;

    BirdElementController birdController;

    @BeforeEach
    public void init() {

        arena=Mockito.mock(Arena.class);
        birdController=new BirdElementController(arena);

    }

    @Test
    public void jumpBird () throws IOException, URISyntaxException, FontFormatException {


        Bird bird=Mockito.mock(Bird.class);
        Position position=Mockito.mock(Position.class);

        Mockito.when (arena.getBird()).thenReturn(bird);
        Mockito.when (bird.getPosition()).thenReturn(position);

        birdController.jumpBird();

        Mockito.verify(position,Mockito.atLeastOnce()).getUp();


    }

    @Test
    public void downBird () throws IOException, URISyntaxException, FontFormatException {


        Bird bird=Mockito.mock(Bird.class);
        Position position=Mockito.mock(Position.class);

        Mockito.when(arena.getBird()).thenReturn(bird);
        Mockito.when(bird.getPosition()).thenReturn(position);

        birdController.downBird();
        Mockito.verify(position,Mockito.atLeastOnce()).getDown();


    }

    @Test
    public void moveBird () throws IOException, URISyntaxException, FontFormatException {


        Bird bird=Mockito.mock(Bird.class);
        Position position=Mockito.mock(Position.class);

        Mockito.when(arena.getBird()).thenReturn(bird);
        Mockito.when(bird.getPosition()).thenReturn(position);

        birdController.moveBird(position);
        Mockito.verify(bird,Mockito.atLeastOnce()).setPosition(position);

    }

    @Test
    public void getArena (){

        Assertions.assertTrue(arena==birdController.getArena());
    }



}

