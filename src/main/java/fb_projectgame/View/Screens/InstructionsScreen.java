package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

public class InstructionsScreen extends ScreenView{
    private final int PADDING_X = 2;
    private static final int PADDING_Y = 1;

    public static String ROOT = new File(System.getProperty("user.dir")).getPath();

    private final java.util.List<Integer> bluelines;
    private final File informationFile;
    public String instructionPath = "/src/main/resources/instructionDraw.txt";


    public InstructionsScreen(List<Integer> bluelines) {
        this.bluelines = bluelines;
        informationFile = new File(ROOT+instructionPath);
    }

    @Override
    public void draw() throws IOException {
        clear();

        Scanner myReader = new Scanner(informationFile, Charset.defaultCharset());
        int y = PADDING_Y;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            setForegroundColor("#000000");
            printLine(line, PADDING_X, y);
            if (bluelines.contains(y))
                drawBlueLine(line, y);

            y++;
        }
        myReader.close();
        refresh();
    }

    private void drawBlueLine(String line, int y){
        setForegroundColor("#1B2BA3");
        int beginBorder = line.indexOf("||") + 2;
        int endBorder = line.indexOf("||", beginBorder);
        printLine(line.substring(beginBorder, endBorder), PADDING_X + beginBorder, y);
    }

    private void printLine(String line, int x, int y){
        getGraphics().putString(new TerminalPosition(x, y), line);
    }

    @Override
    public TerminalSize getSize() {
        return new TerminalSize(50, 25);
    }


}
