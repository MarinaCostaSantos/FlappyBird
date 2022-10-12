package fb_projectgame.View.Game;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ElementViewerTest extends Assertions {

    ElementViewer view;
    TextGraphics textGraphics;

    String color;

    @BeforeEach
    void init(){
        view = Mockito.mock(ElementViewer.class, Mockito.CALLS_REAL_METHODS);
        textGraphics = Mockito.mock(TextGraphics.class);
        view.setGraphics(textGraphics);

        color = "#000000";
    }

    @Test
    void setForegroundColor(){

        // when
        view.setForegroundColor(color);

        // then
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color));
    }

    @Test
    void setBackgroundColor(){

        view.setBackgroundColor(color);

        // then
        Mockito.verify(textGraphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(color));
    }
}
