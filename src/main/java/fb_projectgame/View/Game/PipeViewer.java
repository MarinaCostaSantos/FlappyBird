package fb_projectgame.View.Game;

import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.View.gui.GUI;

public class PipeViewer implements ElementViewer<Pipe> {
    @Override
    public void drawElement(Pipe pipe, GUI gui) {
        gui.drawPipe(pipe);
    }
}
