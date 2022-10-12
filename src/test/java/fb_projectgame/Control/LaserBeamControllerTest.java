package fb_projectgame.Control;

import fb_projectgame.Control.Game.LaserBeamController;
import fb_projectgame.Control.Game.PipeElementController;
import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Elements.LaserBeam;
import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class LaserBeamControllerTest {

    Arena arena;

    LaserBeamController laserbeamController;

    @BeforeEach
    public void init() {

        arena= Mockito.mock(Arena.class);
        laserbeamController=new LaserBeamController(arena);

    }


    @Test
    public void moveLaserBeams() throws IOException, URISyntaxException, FontFormatException {


        Bird bird=Mockito.mock(Bird.class);

        List <LaserBeam> laser_beams=new ArrayList<>();
        LaserBeam laser_beam_1=new LaserBeam(5,20);
        LaserBeam laser_beam_2=new LaserBeam(8,20);
        laser_beams.add(laser_beam_1);
        laser_beams.add(laser_beam_2);
        Position position=Mockito.mock(Position.class);


        Mockito.when(arena.getBird()).thenReturn(bird);
        Mockito.when(bird.getLaserBeams()).thenReturn((ArrayList<LaserBeam>) laser_beams);


        laserbeamController.moveLaserBeams();

        Assertions.assertEquals(6,bird.getLaserBeams().get(0).getPosition().getX());
        Assertions.assertEquals(9,bird.getLaserBeams().get(1).getPosition().getX());


    }

    @Test
    public void moveLaserBeam() throws IOException, URISyntaxException, FontFormatException {


        LaserBeam laser_beam=Mockito.mock(LaserBeam.class);
        Position position=Mockito.mock(Position.class);


        laserbeamController.moveLaserBeam(laser_beam,position);

        Mockito.verify(laser_beam,Mockito.atLeastOnce()).setPosition(position);


    }

    @Test
    public void getArena (){

        Assertions.assertTrue(arena==laserbeamController.getArena());
    }
}
