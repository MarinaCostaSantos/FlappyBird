package fb_projectgame.View.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import fb_projectgame.Model.Elements.LaserBeam;
import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LaserBeamViewerTest {

    LaserBeam laserBeamMock;

    Position positionMock;

    LaserBeamViewer laserBeamViewer;

    TextGraphics graphicsMock;

    @BeforeEach
    void init() {
        laserBeamMock = Mockito.mock(LaserBeam.class);
        positionMock = Mockito.mock(Position.class);

        Mockito.when(positionMock.getX()).thenReturn(30);
        Mockito.when(positionMock.getY()).thenReturn(20);

        Mockito.when(laserBeamMock.getPosition()).thenReturn(positionMock);

        laserBeamViewer = Mockito.spy(new LaserBeamViewer(laserBeamMock));

        graphicsMock = Mockito.mock(TextGraphics.class);
        laserBeamViewer.setGraphics(graphicsMock);
    }

    @Test
    public void drawLaserBeamTest(){

        // when
        laserBeamViewer.draw();

        // then

        Mockito.verify(laserBeamViewer, Mockito.times(1)).setBackgroundColor("#C4F5FE");
        Mockito.verify(laserBeamViewer, Mockito.times(1)).setForegroundColor("#000000");


        Mockito.verify(graphicsMock, Mockito.times(1)).fillRectangle(
                new TerminalPosition(positionMock.getX(), positionMock.getY()),
                new TerminalSize(1, 1), '$');

    }
}
