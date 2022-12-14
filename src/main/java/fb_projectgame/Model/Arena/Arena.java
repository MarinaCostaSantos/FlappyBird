package fb_projectgame.Model.Arena;

import fb_projectgame.Control.MusicManager;
import fb_projectgame.Control.Sounds;
import fb_projectgame.Model.Elements.Bird;
import fb_projectgame.Model.Elements.LaserBeam;
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
                break;
            }
            //colisão superior
            else if (bird.getPosition().getY() == pipe.getY1() && bird.getPosition().getX() == pipe.getPosition().getX()) {
                col = true;
                MusicManager.getInstance().start(Sounds.GAMEOVER);
                break;
            }
            //colisão inferior
            else if (bird.getPosition().getY() == pipe.getY2() && bird.getPosition().getX() == pipe.getPosition().getX()) {
                col = true;
                MusicManager.getInstance().start(Sounds.GAMEOVER);
                break;
            }
            //colisão céu e solo
            else if (bird.getPosition().getY() == this.getHeight() || bird.getPosition().getY() == 3) {
                col = true;
                MusicManager.getInstance().start(Sounds.GAMEOVER);
                break;
            }


            getScore(bird, pipe);


        }
        return col;
    }

    public void CollisionLaserBeam(List<LaserBeam> laserbeam_list) {
        boolean entrou =false;
       for (int i=0; i<laserbeam_list.size();i++) {

           if (laserbeam_list.get(i).getPosition().getX() <0 || laserbeam_list.get(i).getPosition().getX() > this.getWidth()) {
               laserbeam_list.remove(i);
               break;
           }

        for (int j=0; j<pipes.size();j++) {

            if (pipes.get(j).getPosition().getX() <0 || pipes.get(j).getPosition().getX() > this.getWidth()) {
                continue;
            }

            if ((laserbeam_list.get(i).getPosition().getY() <= pipes.get(j).getY1() && laserbeam_list.get(i).getPosition().getX() == pipes.get(j).getPosition().getX()) ||
                    (laserbeam_list.get(i).getPosition().getY() >= pipes.get(j).getY2() && laserbeam_list.get(i).getPosition().getX() == pipes.get(j).getPosition().getX())) {

                entrou = true;

                    pipes.get(j).setY1(2);
                    pipes.get(j).setY2(this.getHeight()-1);

                MusicManager.getInstance().start(Sounds.DESTRUCTIONS);

                break;

            }

        }
           if (entrou){
               laserbeam_list.remove(i);
               break;
           }

       }

    }





    public List<Pipe> createPipes() {

        List<Pipe> pipes = new ArrayList<>();
        int position_X = 4* this.width/5;   //posição x dos tubos

        //i - nr tubos
        for (int i = 0; i < 150; i++) {

            if (i <= 20) { //1ºnível

                pipes.add(new Pipe(position_X, this.height, 1));
                position_X += 17;
            }

            if (i > 25 && i <= 50) {//2ºnível

                pipes.add(new Pipe(position_X, this.height, 2));
                position_X += 12;
            }

            if (i > 50 && i < 80) {//3ºnível

                pipes.add(new Pipe(position_X, this.height, 3));
                position_X += 9;
            }

            if (i > 85 && i < 100) {//4ºnível

                pipes.add(new Pipe(position_X, this.height, 4));
                position_X += 6;
            }

            if (i > 100) {//5ºnível

                pipes.add(new Pipe(position_X, this.height, 4));
                position_X += 4;
            }
        }
        return pipes;
    }

    public Bird createBird() {
        return new Bird(this.width / 5, this.height/3);
    }

    public void getScore(Bird b, Pipe p) {

        if (b.getPosition().getX() == p.getPosition().getX() && b.getPosition().getY() > p.getY1() && b.getPosition().getY() < p.getY2())
            b.addScore(p.getPoints());

    }
}

