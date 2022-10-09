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

        setForegroundColor("#000000");
        String score = "Score : " + (arena.getBird().getScore());

        getGraphics().putString(1,1,score);

        drawBird(arena.getBird().getPosition());

        for (int i=0; i<arena.getPipes().size(); i++){
            if (arena.getPipes().get(i).getPosition().getX() >=0 || arena.getPipes().get(i).getPosition().getX() <= arena.getWidth()){
                drawPipe(arena.getPipes().get(i));
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
        return new TerminalSize(Constants.WIDTH, Constants.HEIGHT);
    }



}
