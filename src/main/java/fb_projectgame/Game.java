package fb_projectgame;

import fb_projectgame.Control.ArenaController;
import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.View.ArenaViewer;
import fb_projectgame.gui.GUI;
import fb_projectgame.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        createGame();
    }

    private static void createGame() throws FontFormatException, IOException, URISyntaxException {
        Arena arena = new Arena(40,20);
        GUI gui = new LanternaGUI(arena.getWidth(), arena.getHeight());

        ArenaViewer viewer = new ArenaViewer(gui);
        ArenaController controller = new ArenaController(arena, viewer, gui);

        controller.start();
    }
}
