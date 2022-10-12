package fb_projectgame.View.Screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;


import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class ScreenView {

    protected TextGraphics graphics;
    private TerminalScreen screen;

    public void initScreen() throws IOException , FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(fontConfig);
        screen = createScreen(terminal);
        setGraphics(screen.newTextGraphics());
    }

    public TerminalScreen createScreen(Terminal terminal) throws IOException {
        final TerminalScreen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    public Terminal createTerminal(AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = getSize();
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    public AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/FlappyBird_Font.ttf");
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



    public void drawText(String string){
        setBackgroundColor("#8ABAC1");
        getGraphics().fillRectangle(new TerminalPosition(0, 0),  new TerminalSize(getSize().getColumns(), 3), ' ');
        setForegroundColor("#000000");
        getGraphics().putString(1,1,string);

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
