package fb_projectgame.View.Game;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public abstract class ElementViewer {
    protected TextGraphics graphics;
    public TextGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public abstract void draw() throws IOException;

    public void setForegroundColor(String color){
        getGraphics().setForegroundColor(TextColor.Factory.fromString(color));
    }

    public void setBackgroundColor(String color){
        getGraphics().setBackgroundColor(TextColor.Factory.fromString(color));
    }

}

