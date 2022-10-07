package fb_projectgame;

import fb_projectgame.Control.States.ScreenController;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {

    public static void main(String[] args) throws URISyntaxException, FontFormatException, IOException{

        try {
            ScreenController controller = new ScreenController();
            controller.run() ;
        }catch (IOException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
