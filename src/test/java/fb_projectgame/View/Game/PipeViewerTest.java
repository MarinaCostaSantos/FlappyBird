package fb_projectgame.View.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PipeViewerTest {
    Pipe pipeMock;

    Position positionMock;

    PipeViewer pipeViewer;

    TextGraphics graphicsMock;

    @BeforeEach
    void init() {
        pipeMock = Mockito.mock(Pipe.class);
        positionMock = Mockito.mock(Position.class);

        Mockito.when(positionMock.getX()).thenReturn(30);
        Mockito.when(positionMock.getY()).thenReturn(20);

        Mockito.when(pipeMock.getPosition()).thenReturn(positionMock);

        pipeViewer = Mockito.spy(new PipeViewer(pipeMock));

        graphicsMock = Mockito.mock(TextGraphics.class);
        pipeViewer.setGraphics(graphicsMock);
    }

    @Test
    public void drawPipeTest(){

        // when
        pipeViewer.draw();

        // then

        Mockito.verify(pipeViewer, Mockito.times(1)).setBackgroundColor("#6BCF68");

        Mockito.verify(graphicsMock, Mockito.times(1)).fillRectangle(
                new TerminalPosition(positionMock.getX(), 3),
                new TerminalSize(1, pipeMock.getY1()), ' ');

        Mockito.verify(graphicsMock, Mockito.times(1)).fillRectangle(
                new TerminalPosition(positionMock.getX(), pipeMock.getY2()),
                new TerminalSize(1, pipeMock.getYmax()), ' ');

    }
}
