package fb_projectgame.Model;

import fb_projectgame.Model.Elements.Pipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PipeTest {

    private Pipe pipe;
    private int x;
    private int ymax;

    @BeforeEach
    public void init (){

        x=0;
        ymax=20;
        pipe=new Pipe(0,ymax,1);

    }

    @Test
    public void create (){

        Assertions.assertNotNull(pipe);

    }

    @Test
    public void getY1 () {

        Assertions.assertTrue(pipe.getY1()<=((pipe.getYmax()- 9-1)+1));
    }

    @Test
    public void setY1 () {

        pipe.setY1(0);

        Assertions.assertEquals(0,pipe.getY1());
    }

    @Test
    public void getY2 () {

        Assertions.assertEquals(pipe.getY1()+9,pipe.getY2());
    }

    @Test
    public void setY2 () {

        pipe.setY2(0);

        Assertions.assertEquals(0,pipe.getY2());
    }


    @Test
    public void getYmax () {

        Assertions.assertEquals(ymax,pipe.getYmax());
    }

    @Test
    public void getPoints () {

        Assertions.assertEquals(1,pipe.getPoints());
    }



    @Test
    public void getPosition (){

        Position position=new Position(0,pipe.getYmax());
        Assertions.assertTrue(pipe.getPosition().getX()==position.getX() && pipe.getPosition().getY()==position.getY());

    }

    @Test
    public void setPosition (){

        Position position=new Position(1,1);

        pipe.setPosition(position);

        Assertions.assertTrue(pipe.getPosition()==position);

    }

}
