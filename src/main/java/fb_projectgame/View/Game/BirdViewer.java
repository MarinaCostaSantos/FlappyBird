package fb_projectgame.View.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import fb_projectgame.Model.Elements.Bird;

public class BirdViewer extends  ElementViewer {

    private final Bird bird;
    public BirdViewer(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void draw(){
        setBackgroundColor("#C4F5FE");
        setForegroundColor("#000000");
        getGraphics().fillRectangle(new TerminalPosition(this.bird.getPosition().getX(), this.bird.getPosition().getY()), new TerminalSize(1, 1), '%');

    }

}