package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import fb_projectgame.Constants;
import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Elements.Element;
import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.View.Game.BirdViewer;
import fb_projectgame.View.Game.ElementViewer;
import fb_projectgame.View.Game.PipeViewer;
import fb_projectgame.gui.GUI;
import fb_projectgame.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class GameScreen extends ScreenView {

    private final Arena arena;

    private GUI gui;

    public GameScreen(Arena arena) throws URISyntaxException, FontFormatException, IOException{
        this.arena = arena;
    //    this.gui = new LanternaGUI(arena.getWidth(), arena.getHeight());
    }

    @Override
    public void draw() throws IOException {
        clear();

        drawElement(arena.getBird(), new BirdViewer());

        for (int i=0; i<arena.getPipes().size(); i++){
            if (arena.getPipes().get(i).getPosition().getX() >=0 || arena.getPipes().get(i).getPosition().getX() <= arena.getWidth()){
                drawElement(arena.getPipes().get(i), new PipeViewer());
            }
        }

        refresh();
    }

    private <T extends Element> void drawElements(List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(element, viewer);
    }

    private <T extends Element> void drawElement(T element, ElementViewer<T> viewer) {
        viewer.drawElement(element, gui);
    }


  @Override
    public void setGraphics(TextGraphics graphics) {
        super.setGraphics(graphics);
    }

    @Override
    public TerminalSize getSize(){
        return new TerminalSize(Constants.WIDTH, Constants.HEIGHT);
    }


    public GUI getGui() {
        return gui;
    }
}
