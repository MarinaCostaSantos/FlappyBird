package fb_projectgame.Model.Arena;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Elements.LaserBeam;
import fb_projectgame.Model.Elements.Pipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArenaTest {

    private int width;
    private int height;
    private Arena arena;



    @BeforeEach
    public void init(){

        width=40;
        height=20;
        arena=new Arena (width,height);

    }

    @Test
    public void create (){

        Assertions.assertNotNull(arena);

    }

    @Test
    public void getWidth(){

        Assertions.assertEquals(width,arena.getWidth());

    }

    @Test
    public void getHeight(){

        Assertions.assertEquals(height, arena.getHeight());

    }

    @Test
    public void getBird (){

        Assertions.assertNotNull(arena.getBird());

    }

    @Test
    public void getPipes_1(){

        Assertions.assertNotNull(arena.getPipes());

    }
    @Test
    public void getPipes_2(){

        Assertions.assertNotNull(arena.getPipes());

    }

    @Test
    public void setBird(){

        Bird new_bird= new Bird(0,0);
        arena.setBird(new_bird);

        Assertions.assertEquals(new_bird,arena.getBird());

    }

    @Test
    public void setPipe(){

        Pipe pipe1 =new Pipe(0,20,1);
        Pipe pipe2 =new Pipe(10,20,1);

        List<Pipe> new_pipes= Arrays.asList(pipe1,pipe2);
        arena.setPipes(new_pipes);

        Assertions.assertEquals(new_pipes,arena.getPipes());

    }

    @Test //dentro y1
    public void collision_1(){

        Pipe pipe_teste=new Pipe(0,20,1);
        List <Pipe> pipes=Arrays.asList(pipe_teste);
        arena.setPipes(pipes);

        Bird bird=new Bird(0,pipe_teste.getY1()-2);

        Assertions.assertTrue(arena.Collision(bird));

    }

    @Test //dentro y2
    public void collision_2(){

        Pipe pipe_teste=new Pipe(0,20,1);
        List <Pipe> pipes=Arrays.asList(pipe_teste);
        arena.setPipes(pipes);

        Bird bird=new Bird(0,pipe_teste.getY2()+2);

        Assertions.assertTrue(arena.Collision(bird));

    }

    @Test //dentro da ranhura em y1
    public void collision_3(){

        Pipe pipe_teste=new Pipe(0,20,1);
        List <Pipe> pipes=Arrays.asList(pipe_teste);
        arena.setPipes(pipes);

        Bird bird=new Bird(0,pipe_teste.getY1());

        Assertions.assertTrue(arena.Collision(bird));

    }

    @Test //dentro da ranhura em y2
    public void collision_5(){

        Pipe pipe_teste=new Pipe(0,20,1);
        List <Pipe> pipes=Arrays.asList(pipe_teste);
        arena.setPipes(pipes);

        Bird bird=new Bird(0,pipe_teste.getY2());

        Assertions.assertTrue(arena.Collision(bird));

    }


    @Test //solo
    public void collision_7(){

        Pipe pipe_teste=new Pipe(0,20,1);
        List <Pipe> pipes=Arrays.asList(pipe_teste);
        arena.setPipes(pipes);

        Bird bird=new Bird(4,height);

        Assertions.assertTrue(arena.Collision(bird));

    }

    @Test //ceu
    public void collision_6(){

        Pipe pipe_teste=new Pipe(0,20,1);
        List <Pipe> pipes=Arrays.asList(pipe_teste);
        arena.setPipes(pipes);

        Bird bird=new Bird(4,3);

        Assertions.assertTrue(arena.Collision(bird));

    }

    @Test //sem colisão
    public void collision_8(){

        Pipe pipe_teste=new Pipe(0,20,1);
        List <Pipe> pipes=Arrays.asList(pipe_teste);
        arena.setPipes(pipes);

        Bird bird=new Bird(0,10);

        Assertions.assertFalse(arena.Collision(bird));

    }

    @Test //sem colisão
    public void collision_9(){

        Pipe pipe_teste=new Pipe(0,20,1);
        List <Pipe> pipes=Arrays.asList(pipe_teste);
        arena.setPipes(pipes);

        Bird bird=new Bird(5,10);

        Assertions.assertFalse(arena.Collision(bird));

    }

    @Test
    public void createBird (){
        Bird bird_teste= arena.createBird();

        Assertions.assertTrue(bird_teste.getPosition().getX()==arena.getWidth()/5 && bird_teste.getPosition().getY()==arena.getHeight()/3);
    }

    @Test
    public void createPipes (){
        arena.createPipes();

        Assertions.assertNotNull(arena.getPipes());
    }

    @Test
    public void createPipes_checkSize (){
        arena.createPipes();

        Assertions.assertEquals(138,arena.getPipes().size());
    }



    @Test
    public  void CollisionLaserBeam_1 (){

        Pipe pipe=new Pipe(0,20,1);
        List <Pipe> pipez=new ArrayList<>();
        pipez.add(pipe);
        arena.setPipes(pipez);

        LaserBeam laserBeam2=new LaserBeam(0,pipe.getY1()-1);
        ArrayList <LaserBeam> laserBeams=new ArrayList<>();
        laserBeams.add(laserBeam2);

        arena.CollisionLaserBeam(laserBeams);

        Assertions.assertEquals(2,arena.getPipes().get(0).getY1());
        Assertions.assertEquals(arena.getHeight()-1,arena.getPipes().get(0).getY2());
    }

    @Test
    public  void CollisionLaserBeam_2 (){

        Pipe pipe=new Pipe(0,20,1);
        List <Pipe> pipez=new ArrayList<>();
        pipez.add(pipe);
        arena.setPipes(pipez);

        LaserBeam laserBeam2=new LaserBeam(0,pipe.getY2()+1);
        ArrayList <LaserBeam> laserBeams=new ArrayList<>();
        laserBeams.add(laserBeam2);

        arena.CollisionLaserBeam(laserBeams);

        Assertions.assertEquals(2,arena.getPipes().get(0).getY1());
        Assertions.assertEquals(arena.getHeight()-1,arena.getPipes().get(0).getY2());
    }

    @Test
    public  void CollisionLaserBeam_3 () {


        arena.createPipes();

        for (int i = 0; i < arena.getPipes().size();i++) {

            LaserBeam laserBeam2 = new LaserBeam(arena.getPipes().get(i).getPosition().getX(), arena.getPipes().get(i).getY1()-1);
            ArrayList<LaserBeam> laserBeams = new ArrayList<>();
            laserBeams.add(laserBeam2);

            arena.CollisionLaserBeam(laserBeams);

            Assertions.assertEquals(2,arena.getPipes().get(0).getY1());
            Assertions.assertEquals(arena.getHeight()-1,arena.getPipes().get(0).getY2());
        }

    }

    @Test
    public  void CollisionLaserBeam_4 () {


        arena.createPipes();

        for (int i = 0; i < arena.getPipes().size();i++) {

            LaserBeam laserBeam2 = new LaserBeam(arena.getPipes().get(i).getPosition().getX(), arena.getPipes().get(i).getY2()+1);
            ArrayList<LaserBeam> laserBeams = new ArrayList<>();
            laserBeams.add(laserBeam2);

            arena.CollisionLaserBeam(laserBeams);

            Assertions.assertEquals(2,arena.getPipes().get(0).getY1());
            Assertions.assertEquals(arena.getHeight()-1,arena.getPipes().get(0).getY2());
        }

    }

    @Test
    public  void RemoveBeam () {


        arena.createPipes();

        for (int i = 0; i < arena.getPipes().size();i++) {

            LaserBeam laserBeam2 = new LaserBeam(-5, arena.getPipes().get(i).getY1()-1);
            ArrayList<LaserBeam> laserBeams = new ArrayList<>();
            laserBeams.add(laserBeam2);

            arena.CollisionLaserBeam(laserBeams);

            Assertions.assertEquals(0,laserBeams.size());
        }

    }

    @Test
    public void getScore_1 (){

        Pipe pipe=new Pipe(0,20,1);
        Bird bird=new Bird(0, pipe.getY1()+1);

        arena.getScore(bird,pipe);

        Assertions.assertEquals(1,bird.getScore());
    }

    @Test
    public void getScore_2 (){

        Pipe pipe=new Pipe(0,20,1);
        Bird bird=new Bird(0, pipe.getY2()-1);

        arena.getScore(bird,pipe);

        Assertions.assertEquals(1,bird.getScore());
    }

    @Test
    public void getScore_3 (){

        Pipe pipe=new Pipe(0,20,1);
        Bird bird=new Bird(-1, pipe.getY2()-1);

        arena.getScore(bird,pipe);

        Assertions.assertEquals(0,bird.getScore());
    }

    @Test
    public void checkPipeSpace_17 () {

        arena.createBird();

        Assertions.assertEquals(17,arena.getPipes().get(1).getPosition().getX()-arena.getPipes().get(0).getPosition().getX());
    }

    @Test
    public void checkPipeSpace_12 () {

        arena.createBird();

        Assertions.assertEquals(12,arena.getPipes().get(27).getPosition().getX()-arena.getPipes().get(26).getPosition().getX());
    }

    @Test
    public void checkPipeSpace_9 () {

        arena.createBird();

        Assertions.assertEquals(9,arena.getPipes().get(57).getPosition().getX()-arena.getPipes().get(56).getPosition().getX());
    }

    @Test
    public void checkPipeSpace_6 () {

        arena.createBird();

        Assertions.assertEquals(6,arena.getPipes().get(87).getPosition().getX()-arena.getPipes().get(86).getPosition().getX());
    }

    @Test
    public void checkPipeSpace_4 () {

        arena.createBird();

        Assertions.assertEquals(4,arena.getPipes().get(107).getPosition().getX()-arena.getPipes().get(106).getPosition().getX());
    }






}
