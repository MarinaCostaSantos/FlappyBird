package fb_projectgame.Control.Game;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Elements.LaserBeam;
import fb_projectgame.Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


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
    public void moveLaserBeams() {


        Bird bird=Mockito.mock(Bird.class);

        List <LaserBeam> laser_beams=new ArrayList<>();
        LaserBeam laser_beam_1=new LaserBeam(5,20);
        LaserBeam laser_beam_2=new LaserBeam(8,20);
        laser_beams.add(laser_beam_1);
        laser_beams.add(laser_beam_2);


        Mockito.when(arena.getBird()).thenReturn(bird);
        Mockito.when(bird.getLaserBeams()).thenReturn((ArrayList<LaserBeam>) laser_beams);


        laserbeamController.moveLaserBeams();

        Assertions.assertEquals(6,bird.getLaserBeams().get(0).getPosition().getX());
        Assertions.assertEquals(9,bird.getLaserBeams().get(1).getPosition().getX());


    }

    @Test
    public void moveLaserBeam()  {


        LaserBeam laser_beam=Mockito.mock(LaserBeam.class);
        Position position=Mockito.mock(Position.class);


        laserbeamController.moveLaserBeam(laser_beam,position);

        Mockito.verify(laser_beam,Mockito.atLeastOnce()).setPosition(position);


    }

    @Test
    public void getArena (){

        Assertions.assertSame(arena, laserbeamController.getArena());
    }
}
