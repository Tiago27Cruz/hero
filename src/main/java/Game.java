import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;
import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(190, 50);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.screen.setCursorPosition(null);
            this.screen.startScreen();
            this.screen.doResizeIfNecessary();
            arena = new Arena(190, 50);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw() throws IOException {
        this.screen.clear();
        arena.draw(screen.newTextGraphics());
        this.screen.refresh();
        processKey(screen.readInput());
    }
    public void run() throws IOException {
        while(true) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q'))
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }
    private void processKey(KeyStroke key) throws IOException{
        arena.processKey(key);
    }

}
