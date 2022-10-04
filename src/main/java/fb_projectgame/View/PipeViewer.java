package fb_projectgame.View;

import com.googlecode.lanterna.graphics.TextGraphics;
import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;
import fb_projectgame.gui.GUI;

public class PipeViewer implements ElementViewer<Pipe> {
    @Override
    public void drawElement(Pipe pipe, GUI gui) {
        gui.drawPipe(pipe);
    }
}
