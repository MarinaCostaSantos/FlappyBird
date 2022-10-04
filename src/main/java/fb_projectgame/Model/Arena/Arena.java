package fb_projectgame.Model.Arena;

import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private final int width;
    private final int height;

    private Bird bird;

    private List<Pipe> pipes;


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) { this.bird = bird;}

    public List<Pipe> getPipes() {
        return pipes;
    }

    public void setPipes(List<Pipe> pipes) {
        this.pipes = pipes;
    }



    public boolean Collision(Position position) {
        for (Pipe pipe : pipes)

            //colisão lateral
            if ( (position.getY() <= pipe.getY1() && position.getX() == pipe.getX()) ||  (position.getY() >= pipe.getY2() && position.getX() == pipe.getX())) {
                return true;
            }
            //colisão superior
            else if (position.getY() == pipe.getY1() && (position.getX()==pipe.getX()  || position.getX()==pipe.getX()+1) ){
                return true;
            }
            //colisão inferior
            else if (position.getY() == pipe.getY2() && (position.getX()==pipe.getX()  || position.getX()==pipe.getX()+1) ){
                return true;
            }

        return false;
    }


    public Arena createArena() {

        Arena arena = new Arena(this.width,this.height);

        arena.setBird(createBird());
        arena.setPipes(createPipes());

        return arena;
    }


    public List<Pipe> createPipes() {

        Random random = new Random();
        List<Pipe> pipes = new ArrayList<>();
        int aux_x = this.width/2;

        for (int i = 0; i < 2000; i++) {

            if (i <= 250) {

                pipes.add(new Pipe(aux_x, this.height));
                aux_x += 150;
            }

            if (i > 250 && i <= 600) {

                pipes.add(new Pipe(aux_x, this.height));
                aux_x += 120;
            }

            if (i > 600 && i < 1200) {

                pipes.add(new Pipe(aux_x, this.height));
                aux_x += 90;
            }

            if (i > 1200 && i < 2000) {

                pipes.add(new Pipe(aux_x, this.height));
                aux_x += 50;
            }
        }
        return pipes;
    }

    public Bird createBird() {
        return new Bird(this.width / 4, this.height / 2);
    }
}

