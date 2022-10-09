package fb_projectgame.View.Game;

import fb_projectgame.Model.Elements.Element;
import fb_projectgame.View.gui.GUI;

public interface ElementViewer<T extends Element> {
    void drawElement(T element, GUI gui);
}

