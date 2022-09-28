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

    public void draw(Screen screen) throws IOException {
        hero.draw(screen);
    }
    private void moveHero(Position position) {
        hero.setPosition(position);
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
            case Character: if(key.getCharacter() == 'q'){screen.stopScreen();}
                break;
            case EOF: System.exit(0);
                break;
        }
    }

}
