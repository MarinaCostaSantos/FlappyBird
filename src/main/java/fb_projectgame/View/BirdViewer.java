package fb_projectgame.View;

import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.gui.GUI;

public class BirdViewer implements ElementViewer <Bird> {
    @Override
    public void drawElement(Bird bird, GUI gui) {
        gui.drawBird(bird.getPosition());
    }

}