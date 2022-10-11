package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import fb_projectgame.Constants;

import java.io.IOException;

public class WinScreen extends ScreenView {

    @Override
    public void draw() throws IOException {
        clear();

        String win = "Y O U   W I N ";
        String congratulations = "!! Congratulations !!";
        String press = "Press ESC to return to Menu";

        setForegroundColor("#000000");
        getGraphics().putString(getTerminalPosition(0.3, win.length()), win);
        setForegroundColor("#1B2BA3");
        getGraphics().putString(getTerminalPosition(0.6, congratulations.length()), congratulations);
        setForegroundColor("#000000");
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






}
