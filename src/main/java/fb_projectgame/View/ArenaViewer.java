package fb_projectgame.View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Element;
import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;
import fb_projectgame.gui.GUI;

import java.io.IOException;
import java.util.List;

public class ArenaViewer {
    private final GUI gui;

    public ArenaViewer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Arena arena) throws IOException {
        gui.clear();

        drawElement(arena.getBird(), new BirdViewer());

        for (int i=0; i<arena.getPipes().size(); i++){
            if (arena.getPipes().get(i).getPosition().getX() >=0 || arena.getPipes().get(i).getPosition().getX() <= arena.getWidth()){
                drawElement(arena.getPipes().get(i), new PipeViewer());
            }
        }

        gui.refresh();
    }

    private <T extends Element> void drawElements(List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(element, viewer);
    }

    private <T extends Element> void drawElement(T element, ElementViewer<T> viewer) {
        viewer.drawElement(element, gui);
    }

    public void close() throws IOException {
        gui.close();
    }
}