package fb_projectgame.Model.Elements;

public class Bird extends Element {

    public static final int MAX_SCORE = 99990;
    private int score;

    public Bird(int x, int y) {
        super(x, y);
        this.score = 0;
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



}
