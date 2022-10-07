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

    public List<Pipe> pipes;


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        this.setBird(createBird());
        this.setPipes(createPipes());
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
            if ( (position.getY() <= pipe.getY1() && position.getX() == pipe.getPosition().getX()) ||  (position.getY() >= pipe.getY2() && position.getX() == pipe.getPosition().getX())) {
                return true;
            }
            //colisão superior
            else if (position.getY() == pipe.getY1() && position.getX()==pipe.getPosition().getX()  ){
                return true;
            }
            //colisão inferior
            else if (position.getY() == pipe.getY2() && position.getX()==pipe.getPosition().getX()){
                return true;
            }
            //colisão céu e solo
            else if (position.getY() == this.getHeight() || position.getY() ==0){
                return true;
            }


        return false;
    }



    public List<Pipe> createPipes() {

        Random random = new Random();
        List<Pipe> pipes = new ArrayList<>();
        int aux_x = 4* this.width/5;   //posição x dos tubos


        //i - nr tubos
        for (int i = 0; i < 2000; i++) {

            if (i <= 250) { //1ºnível

                pipes.add(new Pipe(aux_x, this.height));
                aux_x += 20;
            }

            if (i > 250 && i <= 600) {//2ºnível

                pipes.add(new Pipe(aux_x, this.height));
                aux_x += 17;
            }

            if (i > 600 && i < 1200) {//3ºnível

                pipes.add(new Pipe(aux_x, this.height));
                aux_x += 12;
            }

            if (i > 1200 && i < 2000) {//4ºnível

                pipes.add(new Pipe(aux_x, this.height));
                aux_x += 10;
            }
        }
        return pipes;
    }

    public Bird createBird() {
        return new Bird(this.width / 5, this.height/3);
    }
}

