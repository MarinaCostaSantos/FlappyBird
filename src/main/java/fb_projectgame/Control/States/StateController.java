package fb_projectgame.Control.States;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface StateController {
    void run() throws IOException, FontFormatException, URISyntaxException;

    void nextState() throws URISyntaxException, FontFormatException, IOException;


}