package fb_projectgame.View.gui;

import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;

import java.io.IOException;

public interface GUI {

    void drawBird(Position position);

    void drawPipe(Pipe pipe);


    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

}
