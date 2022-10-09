package fb_projectgame.Model.Elements;

import java.util.Random;

public class Pipe extends Element {

    private final int y1;
    private final int y2;
    final private int ymax;

    private int points;

    public Pipe(int x, int ymax, int points){
        super(x, ymax);
        this.points = points;

        Random random=new Random();

        int h = 10;

        this.y1= random.nextInt(ymax- h -1)+1;
        this.y2= h +this.y1;

        this.ymax=ymax;

    }


    public int getY1() {
        return y1;
    }


    public int getY2() {
        return y2;
    }


    public int getYmax() {
        return ymax;
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}