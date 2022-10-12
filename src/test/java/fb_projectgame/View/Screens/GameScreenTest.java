package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import fb_projectgame.Constants;
import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.View.Game.BirdViewer;
import fb_projectgame.View.Game.LaserBeamViewer;
import fb_projectgame.View.Game.PipeViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

public class GameScreenTest extends Assertions {

    Arena arena;

    BirdViewer birdViewer;

    List<PipeViewer> pipesViewer;

    List<LaserBeamViewer> laserBeamsViewer;

    GameScreen screen;

    TextGraphics graphics;

    @BeforeEach
    void init() throws IOException {
        arena = Mockito.mock(Arena.class);
        screen = Mockito.spy(new GameScreen(arena));
        graphics = Mockito.mock(TextGraphics.class);

        screen.setGraphics(graphics);

        Mockito.doNothing().when(screen).clear();
        Mockito.doNothing().when(screen).refresh();

    }


    @Test
    void draw() throws IOException {

        birdViewer = Mockito.mock(BirdViewer.class);
        screen.setBirdViewer(birdViewer);
        Mockito.when(arena.getBird()).thenReturn(new Bird(5, 10));
        pipesViewer = Mockito.mock(List.class);
        screen.setPipesViewer(pipesViewer);
        laserBeamsViewer = Mockito.mock(List.class);
        screen.setLaserBeamViewer(laserBeamsViewer);
        screen.draw();

        Mockito.verify(screen, Mockito.times(1)).clear();

        Mockito.verify(birdViewer, Mockito.times(1)).draw();
        for (int i = 0; i < pipesViewer.size(); i++)
            Mockito.verify(pipesViewer, Mockito.times(1)).get(i).draw();
        Mockito.verify(screen, Mockito.times(1)).updateLaserBeams();
        for (int i = 0; i < laserBeamsViewer.size(); i++)
            Mockito.verify(laserBeamsViewer, Mockito.times(1)).get(i).draw();

        Mockito.verify(screen, Mockito.times(1)).refresh();

    }




    @Test
    void getSize(){
        // when
        TerminalSize size = screen.getSize();

        //then
        assertEquals(size, new TerminalSize(50, Constants.HEIGHT));
    }

    @Test
    void setGraphics(){
        // given
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        // when
        screen.setGraphics(graphics);

        //then
        assertEquals(screen.getGraphics(), graphics);
        Mockito.verify(screen, Mockito.times(1)).setGraphics(graphics);
    }

}
