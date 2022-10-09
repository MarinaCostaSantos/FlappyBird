package fb_projectgame.Model.Arena;

import fb_projectgame.Control.MusicManager;
import fb_projectgame.Control.Sounds;
import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Elements.Pipe;

import java.util.ArrayList;
import java.util.List;

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



    public boolean Collision(Bird bird) {
        boolean col = false;
        for (Pipe pipe : pipes) {

            //colisão lateral
            if ((bird.getPosition().getY() <= pipe.getY1() && bird.getPosition().getX() == pipe.getPosition().getX()) ||
                    (bird.getPosition().getY() >= pipe.getY2() && bird.getPosition().getX() == pipe.getPosition().getX())) {
                col = true;
                MusicManager.getInstance().start(Sounds.GAMEOVER);
            }
            //colisão superior
            else if (bird.getPosition().getY() == pipe.getY1() && bird.getPosition().getX() == pipe.getPosition().getX()) {
                col = true;
                MusicManager.getInstance().start(Sounds.GAMEOVER);
            }
            //colisão inferior
            else if (bird.getPosition().getY() == pipe.getY2() && bird.getPosition().getX() == pipe.getPosition().getX()) {
                col = true;
                MusicManager.getInstance().start(Sounds.GAMEOVER);
            }
            //colisão céu e solo
            else if (bird.getPosition().getY() == this.getHeight() || bird.getPosition().getY() == 0) {
                col = true;
                MusicManager.getInstance().start(Sounds.GAMEOVER);
            }

            if (col){
                getScore(bird, pipe);
                MusicManager.getInstance().start(Sounds.WELLDONE);
            }



        }
        return col;
    }



    public List<Pipe> createPipes() {

        List<Pipe> pipes = new ArrayList<>();
        int aux_x = 4* this.width/5;   //posição x dos tubos

        //i - nr tubos
        for (int i = 0; i < 2000; i++) {

            if (i <= 250) { //1ºnível

                pipes.add(new Pipe(aux_x, this.height, 1));
                aux_x += 20;
            }

            if (i > 250 && i <= 600) {//2ºnível

                pipes.add(new Pipe(aux_x, this.height, 2));
                aux_x += 17;
            }

            if (i > 600 && i < 1200) {//3ºnível

                pipes.add(new Pipe(aux_x, this.height, 3));
                aux_x += 12;
            }

            if (i > 1200) {//4ºnível

                pipes.add(new Pipe(aux_x, this.height, 4));
                aux_x += 10;
            }
        }
        return pipes;
    }

    public Bird createBird() {
        return new Bird(this.width / 5, this.height/3);
    }

    private void getScore(Bird b, Pipe p) {

        if (b.getPosition().getX() == p.getPosition().getX() && b.getPosition().getY() > p.getY1() && b.getPosition().getY() < p.getY2())
            b.addScore(p.getPoints());

    }
}

