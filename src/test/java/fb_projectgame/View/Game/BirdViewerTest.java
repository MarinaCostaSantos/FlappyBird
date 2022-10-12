package fb_projectgame.View.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BirdViewerTest {

    Bird birdMock;

    Position positionMock;

    BirdViewer birdViewer;

    TextGraphics graphicsMock;

    @BeforeEach
    void init() {
        birdMock = Mockito.mock(Bird.class);
        positionMock = Mockito.mock(Position.class);

        Mockito.when(positionMock.getX()).thenReturn(10);
        Mockito.when(positionMock.getY()).thenReturn(15);

        Mockito.when(birdMock.getPosition()).thenReturn(positionMock);

        birdViewer = Mockito.spy(new BirdViewer(birdMock));

        graphicsMock = Mockito.mock(TextGraphics.class);
        birdViewer.setGraphics(graphicsMock);
    }

    @Test
    public void drawBirdTest(){

        // when
        birdViewer.draw();

        // then

        Mockito.verify(birdViewer, Mockito.times(1)).setBackgroundColor("#C4F5FE");
        Mockito.verify(birdViewer, Mockito.times(1)).setForegroundColor("#000000");


        Mockito.verify(graphicsMock, Mockito.times(1)).fillRectangle(
                            new TerminalPosition(positionMock.getX(), positionMock.getY()),
                            new TerminalSize(1, 1), '%');

    }

}


