package fb_projectgame.View.Screens;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import fb_projectgame.Constants;
import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.LaserBeam;
import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.View.Game.BirdViewer;
import fb_projectgame.View.Game.LaserBeamViewer;
import fb_projectgame.View.Game.PipeViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenView {

    private final Arena arena;

    private BirdViewer birdViewer;
    private List<PipeViewer> pipesViewer = new ArrayList<>();

    private List<LaserBeamViewer> laserBeamViewer = new ArrayList<>() ;


    public GameScreen(Arena arena) {
        this.arena = arena;
        this.birdViewer = new BirdViewer(arena.getBird());

        for (Pipe pipe: arena.getPipes())
            this.pipesViewer.add( new PipeViewer(pipe) );
    }

    public void setLaserBeamViewer(List<LaserBeamViewer> laserBeamViewer) {
        this.laserBeamViewer = laserBeamViewer;
    }

    public void setBirdViewer(BirdViewer birdViewer) {
        this.birdViewer = birdViewer;
    }

    public void setPipesViewer(List<PipeViewer> pipesViewer) {
        this.pipesViewer = pipesViewer;
    }

    public BirdViewer getBirdViewer() {
        return birdViewer;
    }

    public List<PipeViewer> getPipesViewer() {
        return pipesViewer;
    }

    public List<LaserBeamViewer> getLaserBeamViewer() {
        return laserBeamViewer;
    }

    public void updateLaserBeams(){
        List<LaserBeamViewer> newlaserBeamViewer = new ArrayList<>();
        for (LaserBeam laserBeam: arena.getBird().getLaserBeams())
            newlaserBeamViewer.add( new LaserBeamViewer(laserBeam) );
        setLaserBeamViewer(newlaserBeamViewer);
    }



    @Override
    public void draw() throws IOException {
        clear();

        String score = "Score : " + (arena.getBird().getScore());
        String laserbeams_String = "  Shoots: ";

        for (int i=arena.getBird().getMaxBeams()-arena.getBird().countLaserBeams;i>0;i--)
            laserbeams_String=laserbeams_String +" $ ";


        drawText(score+laserbeams_String);

       birdViewer.setGraphics(this.getGraphics());
       birdViewer.draw();


        for (int i=0; i<pipesViewer.size(); i++) {
            pipesViewer.get(i).setGraphics(this.graphics);

            if (arena.getPipes().get(i).getPosition().getX() >=0 || arena.getPipes().get(i).getPosition().getX() <= arena.getWidth()){
                pipesViewer.get(i).draw();
            }

        }

        updateLaserBeams();

        for (int i=0; i<laserBeamViewer.size(); i++) {
            laserBeamViewer.get(i).setGraphics(this.graphics);

            if (arena.getBird().getLaserBeams().get(i).getPosition().getX() >=0 || arena.getBird().getLaserBeams().get(i).getPosition().getX() <= arena.getWidth()){
                laserBeamViewer.get(i).draw();
            }

        }


        refresh();
    }




  @Override
    public void setGraphics(TextGraphics graphics) {
        super.setGraphics(graphics);
    }

    @Override
    public TerminalSize getSize(){
        return new TerminalSize(50, Constants.HEIGHT);
    }



}
