package fb_projectgame.View.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import fb_projectgame.Model.Elements.LaserBeam;
import fb_projectgame.Model.Elements.Pipe;

public class LaserBeamViewer extends  ElementViewer {

    private LaserBeam laserBeam;

    public LaserBeamViewer(LaserBeam laserBeam) {
        this.laserBeam = laserBeam;
    }

    @Override
    public void draw(){
        setBackgroundColor("#C4F5FE");
        setForegroundColor("#000000");
        getGraphics().fillRectangle(new TerminalPosition(laserBeam.getPosition().getX(), laserBeam.getPosition().getY()), new TerminalSize(1, 1), '$');
    }

}
