package fb_projectgame.View.Game;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Element;
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