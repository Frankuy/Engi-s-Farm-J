package facility;

import tools.Point;
import tools.Renderable;

public class Truck extends Facility implements Renderable {
    public Truck(Point<Integer> lokasi) {
        super(lokasi);
    }
    public char render()
    {
        return 'T';
    }
    public String getStatus(int i)
    {
        return null;
    }
}

