package facility;

import tools.Point;
import tools.Renderable;

public class Mixer extends Facility implements Renderable {
    public Mixer(Point<Integer> lokasi) {
        super(lokasi);
    }
    public char render() {
        return 'M';
    }
    public String getStatus(int i) {
        return null;
    }
}
