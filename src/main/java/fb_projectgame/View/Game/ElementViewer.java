package fb_projectgame.View.Game;

import com.googlecode.lanterna.graphics.TextGraphics;
import fb_projectgame.Model.Elements.Element;
import fb_projectgame.gui.GUI;

public interface ElementViewer<T extends Element> {
    void drawElement(T element, GUI gui);
}

