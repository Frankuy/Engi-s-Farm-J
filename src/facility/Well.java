package facility;

import tools.Point;
import tools.Renderable;

public class Well extends Facility implements Renderable {
    public Well(Point<Integer> lokasi) {
        super(lokasi);
    }
    public char render()
    {
        return 'W';
    }
    public String getStatus(int i)
    {
        return null;
    }
}


