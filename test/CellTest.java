import animal.Ayam;
import animal.FarmAnimal;
import cell.Cell;
import land.Coop;
import org.junit.jupiter.api.Test;
import player.Player;
import tools.Point;
import tools.Renderable;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Cell c = new Cell();

    @Test
    void getnBrs() {
        assertEquals(10, c.getnBrs());
    }

    @Test
    void getnKol() {
        assertEquals(10, c.getnKol());
    }

    @Test
    void setMap() {
        Coop coop = new Coop(new Point<Integer>(2,2));
        c.setMap(new Point<Integer>(2,2), coop);
        assertEquals(coop, c.getMap(new Point<Integer>(2,2)));
    }

    @Test
    void getMap() {
        assertNull(c.getMap(new Point<Integer>(0,0)));
    }

    @Test
    void setRumputCell() {
        Coop coop = new Coop(new Point<Integer>(2,2));
        Player p = new Player();
        c.setMap(new Point<Integer>(2,2), coop);
        c.setRumputCell(coop.getLokasi(), true);
        assertTrue(Boolean.parseBoolean(c.getMap(coop.getLokasi()).getStatus(2)));
    }

    @Test
    void gerakAnimal() {
        LinkedList<Renderable> animal = new LinkedList<>();
        animal.add(new Ayam("A",new Point<>(3,3),10));
        animal.add(new Ayam("B",new Point<>(5,5),10));
        animal.add(new Ayam("C",new Point<>(7,7),10));
        LinkedList<Renderable> newAnimal = c.gerakAnimal(animal, new Player());
        assertNotEquals(animal, newAnimal);
    }

    @Test
    void print() {
        LinkedList<Renderable> animal = new LinkedList<>();
        animal.add(new Ayam("A",new Point<>(3,3),10));
        animal.add(new Ayam("B",new Point<>(5,5),10));
        animal.add(new Ayam("C",new Point<>(7,7),10));
        c.print(new Player(), animal);
    }
}