import com.googlecode.lanterna.TextCharacter;
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
    hero = new Hero(10, 10);

    public Game() {
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.screen.setCursorPosition(null);
            this.screen.startScreen();
            this.screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw() throws IOException {
        this.screen.clear();
        this.screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        this.screen.refresh();
        processKey(screen.readInput());
    }
    public void run() throws IOException {
        while(true) {
            draw();
        }
    }
    private void processKey(KeyStroke key) throws IOException {
        System.out.println(key);
        switch(key.getKeyType()){
            case ArrowUp: y--;
                break;
            case ArrowDown: y++;
                break;
            case ArrowLeft: x--;
                break;
            case ArrowRight: x++;
                break;
            case Character: if(key.getCharacter() == 'q'){this.screen.stopScreen();}
                break;
            case EOF: System.exit(0);
        }
    }

}
