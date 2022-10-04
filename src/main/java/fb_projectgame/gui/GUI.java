package fb_projectgame.gui;

import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawBird(Position position);

    void drawPipe(Pipe pipe);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {JUMP, DOWN, NONE, QUIT}
}
