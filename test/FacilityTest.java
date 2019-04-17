import facility.Mixer;
import org.junit.jupiter.api.Test;
import tools.Point;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FacilityTest {
    Mixer m = new Mixer(new Point<>(2,2));

    @Test
    void render() {
        assertEquals('M', m.render());
    }

    @Test
    void getStatus() {
        Random r = new Random();
        assertNull(m.getStatus(r.nextInt()));
    }
}