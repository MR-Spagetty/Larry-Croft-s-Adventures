package nz.ac.wgtn.swen225.lc.Renderer;
import nz.ac.wgtn.swen225.lc.domain.tiles.*; // pretty sure its needed
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.entities.Player; // replace new Point in ping with player getPos
import nz.ac.wgtn.swen225.lc.domain.Point;
import java.util.List;

public class Renderer {
    void ping() {
        // store renderable images in list
        List<Sprite> tiles = new Maze(1, "").getTiles(new Point(0, 0), 10).stream().map(t->new Sprite(t)).toList(); // TODO: get maze and player

        tiles.forEach(t->t.draw());
    }
}
