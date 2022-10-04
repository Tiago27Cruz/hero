import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;
    Hero hero = new Hero(new Position(10, 10));

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
    }

    private void moveHero(Position position) {
        if (canHeroMove(position)) {hero.setPosition(position);}
    }

    private boolean canHeroMove(Position position){
        if(position.getX() >= width) return false;
        if(position.getX() < 0) return false;
        if(position.getY() >= height) return false;
        if(position.getY() < 0) return false;
        return true;
    }

    public void processKey(KeyStroke key) throws IOException {
        System.out.println(key);
        switch(key.getKeyType()){
            case ArrowUp: moveHero(hero.moveUp());
                break;
            case ArrowDown: moveHero(hero.moveDown());
                break;
            case ArrowLeft: moveHero(hero.moveLeft());
                break;
            case ArrowRight: moveHero(hero.moveRight());
                break;
            case Character: if(key.getCharacter() == 'q'){System.exit(0);}
            case EOF: System.exit(0);
                break;
        }
    }

    public void draw(TextGraphics newTextGraphics) {
        newTextGraphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        newTextGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height * 2), ' ');
        hero.draw(newTextGraphics);
    }
}
