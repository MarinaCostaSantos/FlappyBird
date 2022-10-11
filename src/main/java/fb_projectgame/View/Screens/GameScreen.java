package fb_projectgame.View.Screens;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import fb_projectgame.Constants;
import fb_projectgame.Model.Arena.Arena;

import java.io.IOException;

public class GameScreen extends ScreenView {

    private final Arena arena;


    public GameScreen(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void draw() throws IOException {
        clear();

        String score = "Score : " + (arena.getBird().getScore());
        String laserbeams_String = "  Shoots: ";

        for (int i=arena.getBird().getMaxBeams()-arena.getBird().countLaserBeams;i>0;i--)
            laserbeams_String=laserbeams_String +" $ ";


        drawText(score+laserbeams_String);

        drawBird(arena.getBird().getPosition());

        for (int i=0; i<arena.getPipes().size(); i++){
            if (arena.getPipes().get(i).getPosition().getX() >=0 || arena.getPipes().get(i).getPosition().getX() <= arena.getWidth()){
                drawPipe(arena.getPipes().get(i));
            }
        }

        if (arena.getBird().getLaserBeams().size()>0){
            for (int i=0; i<arena.getBird().getLaserBeams().size(); i++){
                if (arena.getBird().getLaserBeams().get(i).getPosition().getX() >=0 || arena.getBird().getLaserBeams().get(i).getPosition().getX() <= arena.getWidth()){
                    drawLaserBeam(arena.getBird().getLaserBeams().get(i));
                }
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
