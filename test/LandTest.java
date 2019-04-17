import land.Coop;
import org.junit.jupiter.api.Test;
import tools.Point;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class LandTest {
    Coop coop = new Coop(new Point<>(2,2));
    @Test
    void render() {
        if (coop.getRumput()) assertEquals('v', coop.render());
        else assertEquals('c', coop.render());
    }

    @Test
    void getStatus() {
        coop.setRumput(false);
        assertEquals("2", coop.getStatus(0));
        assertEquals("2", coop.getStatus(1));
        assertFalse(Boolean.parseBoolean(coop.getStatus(2)));
    }

    @Test
    void getLokasi() {
        assertTrue(coop.getLokasi().compareTo(new Point<>(2,2)) == 0);
    }

    @Test
    void setRumput() {
        coop.setRumput(false);
        assertFalse(coop.getRumput());
    }

    @Test
    void getRumput() {
        coop.setRumput(false);
        assertFalse(coop.getRumput());
    }
}