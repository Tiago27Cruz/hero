import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private List<Wall> walls;

    Hero hero = new Hero(new Position(10, 10));

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        this.walls = createWalls();
    }


    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    private void moveHero(Position position) {
        if (canHeroMove(position)) {hero.setPosition(position);}
    }

    private boolean canHeroMove(Position position){
        if(position.getX()+1 >= width) return false;
        if(position.getX()-1 < 0) return false;
        if(position.getY()+1 >= height) return false;
        if(position.getY()-1 < 0) return false;
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
        for (Wall wall : walls)
            wall.draw(newTextGraphics);
        hero.draw(newTextGraphics);
    }
}
