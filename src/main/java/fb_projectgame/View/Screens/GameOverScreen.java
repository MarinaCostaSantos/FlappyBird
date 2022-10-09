package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import fb_projectgame.Constants;


import java.io.IOException;

public class GameOverScreen extends ScreenView {

   private String scoreValue;

    public GameOverScreen() {
        scoreValue = "";
    }

    @Override
    public void draw() throws IOException {
        clear();
        setForegroundColor("#000000");

        String gameOver = "G A M E   O V E R";
        String score = "Score : " + scoreValue;
        String press = "Press ESC to return to Menu";

        getGraphics().putString(getTerminalPosition(0.3, gameOver.length()), gameOver);
        getGraphics().putString(getTerminalPosition(0.4, score.length()), score);
        getGraphics().putString(getTerminalPosition(0.8, press.length()), press);

        refresh();
    }

    @Override
    public TerminalSize getSize() {
        return new TerminalSize(Constants.WIDTH, Constants.HEIGHT);
    }

    public TerminalPosition getTerminalPosition(double percentageRows, int stringLen) {
        return new TerminalPosition(getSize().getColumns() / 2 - stringLen / 2, (int) (getSize().getRows() * percentageRows));
    }

    public void setScore(int score) {
        scoreValue = Integer.toString(score);
    }




}
