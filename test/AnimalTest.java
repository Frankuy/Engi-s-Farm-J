import animal.*;
import cell.Cell;
import land.Coop;
import player.Player;
import tools.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    Ayam a = new Ayam("Ayam Testing", new Point<>(2,2), 10);
    Bebek bebek = new Bebek("Bebek Testing", new Point<>(5,5), 10);
    Domba domba = new Domba("Domba Testing", new Point<>(3,5), 6);
    Kambing kambing = new Kambing("Kambing Testing", new Point<>(3,1), 6);
    Kuda kuda = new Kuda("Kuda Testing", new Point<>(1,1), 15);
    Sapi sapi = new Sapi("Sapi Testing", new Point<>(3,3), 12);

    @Test
    void getNama() {
        assertEquals("Ayam Testing", a.getNama());
        assertEquals( "Bebek Testing", bebek.getNama());
        assertEquals( "Domba Testing", domba.getNama());
        assertEquals( "Kambing Testing", kambing.getNama());
        assertEquals( "Kuda Testing", kuda.getNama());
        assertEquals( "Sapi Testing", sapi.getNama());
    }

    @Test
    void getLokasi() {
        Point<Integer> p = new Point<>(2, 2);
        assertEquals(p.getX(), a.getLokasi().getX());
        assertEquals(p.getY(), a.getLokasi().getY());
    }

    @Test
    void getTicks() {
        assertEquals(0,a.getTicks());
    }

    @Test
    void produceEgg() {
        assertEquals(true, a.isProduceAbleEgg());
    }

    @Test
    void produceMeat() {
        a.produceMeat();
        assertTrue(a.isProduceAbleMeat());
    }

    @Test
    void isLapar() {
        assertFalse(a.isLapar());
    }

    @Test
    void isProduceAbleMeat() {
        assertTrue(a.isProduceAbleMeat());
    }

    @Test
    void isProduceAbleEgg() {
        assertTrue(a.isProduceAbleMeat());
    }

    @Test
    void setProduceAbleEgg() {
        a.setProduceAbleEgg(false);
        assertFalse(a.isProduceAbleEgg());
    }

    @Test
    void setProduceAbleMeat() {
        a.setProduceAbleMeat(false);
        assertFalse(a.isProduceAbleMeat());
    }

    @Test
    void setTicks() {
        a.setTicks(10);
        assertEquals(10, a.getTicks());
    }

    @Test
    void setLapar() {
        a.setLapar(true);
        assertTrue(a.isLapar());
    }

    @Test
    void produce() {
        a.produce();
        assertTrue(a.isProduceAbleEgg());
        assertTrue(a.isProduceAbleMeat());
    }

    @Test
    void gerak() {
        Cell c = new Cell();
        Point<Integer> lokasi = new Point<>(a.getLokasi());
        a.gerak(c, new Player());
        assertEquals(-1, a.getLokasi().compareTo(lokasi));
    }

    @Test
    void bersuara() {
        assertNotEquals("testboy",a.bersuara());
    }

    @Test
    void makan() {
        Cell c = new Cell();
        Coop coop = new Coop(new Point<>(2,2));
        coop.setRumput(true);
        c.setMap(new Point<>(2,2), coop);
        a.setLapar(true);
        a.makan(c);
        assertFalse(a.isLapar());
    }

    @Test
    void mati() {
        assertEquals(a.getNama() + " mati", a.mati());
    }

    @Test
    void render() {
        assertEquals('a', a.render());
    }

    @Test
    void getStatus() {
        assertEquals(a.getStatus(0),a.getNama());
        assertEquals(a.getStatus(1),a.bersuara());
    }
}