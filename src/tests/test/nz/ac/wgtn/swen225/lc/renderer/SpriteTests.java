package test.nz.ac.wgtn.swen225.lc.renderer;
import org.junit.jupiter.api.Test;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.*;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;
import nz.ac.wgtn.swen225.lc.renderer.Sprite;

public class SpriteTests {
    @Test 
    public void test1(){
        Point center = new Point(0, 0);
        Sprite wall = new Sprite(new Wall(center));
        Sprite player = new Sprite(new Player(center, 1));
        wall.draw();
        player.draw();
    }
}
