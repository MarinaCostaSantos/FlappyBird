package fb_projectgame.View;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Element;
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

        drawElements(arena.getPipes(), new PipeViewer());
        drawElement(arena.getBird(), new BirdViewer());


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