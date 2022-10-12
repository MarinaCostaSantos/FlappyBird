package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class WinScreenTest extends Assertions {
    WinScreen winScreen;

    @BeforeEach
    void helper(){
        winScreen = Mockito.spy(new WinScreen());
    }

    @Test
    void getTerminalPosition(){
        //given
        TerminalSize size = new TerminalSize(50,40);
        double percentage = 0.7;
        int strlen = 20;
        TerminalPosition position = new TerminalPosition(size.getColumns()/2-strlen/2, (int)(size.getRows()*percentage));

        Mockito.doReturn(size).when(winScreen).getSize();

        // when
        TerminalPosition p = winScreen.getTerminalPosition(percentage, strlen);


        //then
        assertEquals(p, position);

    }
    @Test
    void draw() throws IOException {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        winScreen.setGraphics(graphics);

        Mockito.when(winScreen.getScreen()).thenReturn(Mockito.mock(TerminalScreen.class));
        // when
        winScreen.draw();

        // then
        Mockito.verify(winScreen, Mockito.times(1)).clear();
        Mockito.verify(winScreen, Mockito.times(8)).getGraphics();
        Mockito.verify(winScreen, Mockito.times(1)).refresh();
    }

}
