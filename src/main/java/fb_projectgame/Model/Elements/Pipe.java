package fb_projectgame.Model.Elements;

import java.util.Random;

public class Pipe extends Element {

    private int y1;
    private int y2;
    final private int ymax;

    private final int points;

    public Pipe(int x, int ymax, int points){
        super(x, ymax);
        this.points = points;

        Random random=new Random();

        int h = 9;

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

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getYmax() {
        return ymax;
    }


    public int getPoints() {
        return points;
    }


}