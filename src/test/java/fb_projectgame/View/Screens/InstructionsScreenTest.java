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
import java.util.List;

public class InstructionsScreenTest extends Assertions {
    InstructionsScreen instructionsScreen;
    TextGraphics graphics;

    @BeforeEach
    void init(){
        List<Integer> redLines = List.of(3, 17, 22);

        instructionsScreen = Mockito.spy(new InstructionsScreen(redLines));

        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        graphics = Mockito.mock(TextGraphics.class);

        Mockito.doReturn(screen).when(instructionsScreen).getScreen();
        Mockito.doReturn(graphics).when(instructionsScreen).getGraphics();
    }

    @Test
    void draw(){
        // given

        try {
            // when
            instructionsScreen.draw();

            // then
            Mockito.verify(instructionsScreen, Mockito.times(23)).setForegroundColor("#000000");

            // red Lines
            Mockito.verify(instructionsScreen, Mockito.times(3)).setForegroundColor("#1B2BA3");

            Mockito.verify(instructionsScreen).refresh();

        }catch (IOException exception){
            assert false;
        }
    }

    @Test
    public void getSize() {

        assertEquals(new TerminalSize(50, 25), instructionsScreen.getSize());
    }
}