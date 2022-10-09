package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;


import fb_projectgame.Model.Elements.Pipe;
import fb_projectgame.Model.Position;
import fb_projectgame.View.gui.GUI;


import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class ScreenView implements GUI{

    protected TextGraphics graphics;
    private TerminalScreen screen;

    public void initScreen() throws IOException , FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(fontConfig);
        screen = createScreen(terminal);
        setGraphics(screen.newTextGraphics());
    }

    private TerminalScreen createScreen(Terminal terminal) throws IOException {
        final TerminalScreen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    private Terminal createTerminal(AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = getSize();
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/veraSansMono.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    public void addKeyListenner(KeyListener keyListener){
        ((AWTTerminalFrame)getScreen().getTerminal()).getComponent(0).addKeyListener(keyListener);
    }

    public void removeKeyListenner(KeyListener keyListener){
        ((AWTTerminalFrame)getScreen().getTerminal()).getComponent(0).removeKeyListener(keyListener);
    }

    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.JUMP;

        return ACTION.NONE;
    }


    public void drawBird (Position position) {
        setBackgroundColor("#F3C91B");
        getGraphics().fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(1, 1), ' ');
    }

    public void drawPipe(Pipe pipe) {
        setBackgroundColor("#6BCF68");
        getGraphics().fillRectangle(new TerminalPosition(pipe.getPosition().getX(), 0), new TerminalSize(1, pipe.getY1()), ' ');
        getGraphics().fillRectangle(new TerminalPosition(pipe.getPosition().getX(), pipe.getY2()), new TerminalSize(1, pipe.getYmax() ), ' ');
    }



    public abstract void draw() throws IOException;

    public TerminalScreen getScreen() {
        return screen;
    }

    public abstract TerminalSize getSize();

    public void refresh() throws IOException {
        getScreen().refresh(Screen.RefreshType.AUTOMATIC);
    }

    public void close() throws IOException {
        getScreen().close();
    }

    public void setForegroundColor(String color){
        getGraphics().setForegroundColor(TextColor.Factory.fromString(color));
    }

    public void setBackgroundColor(String color){
        getGraphics().setBackgroundColor(TextColor.Factory.fromString(color));
    }

    public void clear() {
        setBackgroundColor("#C4F5FE");
        getGraphics().fillRectangle(new TerminalPosition(0, 0),  getSize(), ' ');
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }



}
