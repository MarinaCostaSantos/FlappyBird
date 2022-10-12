package fb_projectgame.View.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import fb_projectgame.Model.Elements.Pipe;

public class PipeViewer  extends  ElementViewer {

        private Pipe pipe;
        public PipeViewer(Pipe pipe) {
            this.pipe = pipe;
        }

        @Override
        public void draw(){
            setBackgroundColor("#6BCF68");
            getGraphics().fillRectangle(new TerminalPosition(pipe.getPosition().getX(), 3), new TerminalSize(1, pipe.getY1()), ' ');
            getGraphics().fillRectangle(new TerminalPosition(pipe.getPosition().getX(), pipe.getY2()), new TerminalSize(1, pipe.getYmax() ), ' ');
        }

    }
