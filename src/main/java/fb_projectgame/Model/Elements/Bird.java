package fb_projectgame.Model.Elements;

import java.util.ArrayList;


public class Bird extends Element {

    public static final int MAX_SCORE = 99990;
    private int score;
    private static final int maxBeams = 3;

    public int countLaserBeams=0;


    private ArrayList<LaserBeam> laserBeams;


    public Bird(int x, int y) {
        super(x, y);
        this.score = 0;

        laserBeams = new ArrayList<>();

    }

    public int getScore() {
        return score;
    }

    public void addScore(int points){
        if(this.score + points > MAX_SCORE)
            this.score = MAX_SCORE;
        else
            this.score += points;
    }

    public ArrayList<LaserBeam> getLaserBeams() {
        return laserBeams;
    }

    public void setLaserBeams(ArrayList<LaserBeam> laserBeams) {
        this.laserBeams = laserBeams;
    }


    public void addLaserBeam(LaserBeam laserBeam){
        laserBeams.add(laserBeam);
    }

    public int getMaxBeams() {
        return maxBeams;
    }

    public int getCountLaserBeams() {
        return countLaserBeams;
    }
}
