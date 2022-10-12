package fb_projectgame.Model;

import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Elements.LaserBeam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BirdTest {

    private Bird bird;

    @BeforeEach

    public void init (){

        bird=new Bird(0,0);

    }

    @Test
    public void create (){

        Assertions.assertNotNull(bird);

    }

    @Test
    public void getPosition (){

        Position position=new Position(0,0);
        Assertions.assertTrue(bird.getPosition().getX()==position.getX() && bird.getPosition().getY()==position.getY());

    }

    @Test
    public void setPosition (){

        Position position=new Position(1,1);

        bird.setPosition(position);

        Assertions.assertTrue(bird.getPosition()==position);

    }

    @Test
    public void getScore () {

        Assertions.assertNotNull(bird.getScore());
    }
    @Test
    public void addScore_1 () {

        bird.addScore(5);

        Assertions.assertEquals(5,bird.getScore());
    }

    @Test
    public void addScore_2 () {

        bird.addScore(100000);

        Assertions.assertEquals(99990,bird.getScore());
    }
    @Test
    public void getLaserBeams () {

        Assertions.assertNotNull(bird.getCountLaserBeams());
    }

    @Test
    public void setLaserBeams () {

        LaserBeam laserBeam=new LaserBeam(0,1);
        ArrayList <LaserBeam> laserBeams=new ArrayList<>();
        laserBeams.add(laserBeam);

        bird.setLaserBeams(laserBeams);

        Assertions.assertEquals(laserBeams,bird.getLaserBeams());
    }

    @Test
    public void addLaserBeams () {

        LaserBeam laserBeam=new LaserBeam(0,1);
        ArrayList <LaserBeam> laserBeams=new ArrayList<>();
        laserBeams.add(laserBeam);
        LaserBeam laserBeam1=new LaserBeam(3,1);

        bird.setLaserBeams(laserBeams);
        bird.addLaserBeam(laserBeam1);

        Assertions.assertEquals(laserBeam1,bird.getLaserBeams().get(1));
    }

    @Test
    public void getMaxBeams () {

        Assertions.assertNotNull(bird.getMaxBeams());
    }










}
