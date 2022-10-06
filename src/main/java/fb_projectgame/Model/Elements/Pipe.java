package fb_projectgame.Model.Elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Pipe extends Element {

    private int y1;
    private int y2;
    final private int h;

    final private int ymax;

    public Pipe(int x, int ymax){
        super(x, ymax);

        Random random=new Random();

        this.h=10;

        this.y1= random.nextInt(ymax-this.h-1)+1;
        this.y2=this.h+this.y1;

        this.ymax=ymax;

    }


    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getH() {
        return h;
    }

    public int getYmax() {
        return ymax;
    }



}