package fb_projectgame.Control.Game;

import fb_projectgame.Model.Arena.Arena;
import fb_projectgame.Model.Elements.LaserBeam;
import fb_projectgame.Model.Position;

import java.util.ArrayList;

public class LaserBeamController extends ElementController {

    public LaserBeamController(Arena arena) {
        super(arena);
    }

    public void moveLaserBeams(){

        ArrayList<LaserBeam> newLaserBeams = new ArrayList<>();

        LaserBeam lb_aux;

        for (int i=0; i<arena.getBird().getLaserBeams().size(); i++){
            lb_aux = arena.getBird().getLaserBeams().get(i);
            moveLaserBeam(lb_aux, lb_aux.getPosition().getRight());
            newLaserBeams.add(lb_aux);
        }

        arena.getBird().setLaserBeams(newLaserBeams);

    }

    public void moveLaserBeam(LaserBeam laserBeam, Position position ) {
        laserBeam.setPosition(position);
    }


}
