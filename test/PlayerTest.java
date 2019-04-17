import animal.Ayam;
import cell.Cell;
import org.junit.jupiter.api.Test;
import player.Player;
import product.DagingAyam;
import product.TelorAyam;
import tools.Point;
import tools.Renderable;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player = new Player();

    @Test
    void gerak() {
        player.gerak("DOWN",new Cell(), new LinkedList<>());
        assertTrue(player.getLokasi().compareTo(new Point<>(0,1)) == 0);
    }

    @Test
    void getProductAnimal() {
        Ayam c = new Ayam("Ayam", new Point<Integer>(1,0),10);
        c.produce();
        player.getProductAnimal(c);
        assertTrue(player.getTas().contains(new TelorAyam()));
    }

    @Test
    void killAnimal() {
    }
}