package fb_projectgame.Model;

import fb_projectgame.Model.Elements.Bird;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {

    private int x;
    private int y;

    private Position position;

    @BeforeEach
    public void init(){

        x=1;
        y=2;
        position=new Position(x,y);

    }

    @Test
    public void create (){

        Assertions.assertNotNull(position);
    }

    @Test
    public void getX(){

        Assertions.assertEquals(x,position.getX());
    }

    @Test
    public void geY(){

        Assertions.assertEquals(y,position.getY());
    }

    @Test
    public void getLeft(){

        Position position_moved=position.getLeft();

        Assertions.assertTrue(position_moved.getX()==position.getX()-1 && position_moved.getY()==position.getY());
    }

    @Test
    public void getRight(){

        Position position_moved=position.getRight();

        Assertions.assertTrue(position_moved.getX()==position.getX()+1 && position_moved.getY()==position.getY());
    }

    @Test
    public void getUp(){

        Position position_moved=position.getUp();

        Assertions.assertTrue(position_moved.getX()==position.getX() && position_moved.getY()==position.getY()-3);
    }

    @Test
    public void getDown(){

        Position position_moved=position.getDown();

        Assertions.assertTrue(position_moved.getX()==position.getX() && position_moved.getY()==position.getY()+1);
    }

    @Test
    public void equals_1(){
        Position position_equals=position;

        Assertions.assertTrue(position.equals(position_equals));
    }

    @Test
    public void equals_2(){
        Position position_equals=null;

        Assertions.assertFalse(position.equals(position_equals));
    }

    @Test
    public void hashcode_1(){

        Position position_hash=position;

        Assertions.assertEquals(position.hashCode(), position_hash.hashCode());
    }

    @Test
    public void hashcode_2(){

        Assertions.assertNotNull(position.hashCode());
    }



}
