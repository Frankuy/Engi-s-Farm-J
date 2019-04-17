package land;

import tools.Point;
import tools.Renderable;
import java.util.Vector;

public class Coop extends Land implements Renderable {
    public Coop(Point<Integer> lokasi) {
        super(lokasi);
    }
    public char render() {
        if (getRumput()) return 'v';
        else return 'C';
    }
    public String getStatus(int i) {
        Vector<String> status = new Vector<>();
        status.add(Integer.toString(lokasi.getX()));
        status.add(Integer.toString(lokasi.getY()));
        status.add(Boolean.toString(getRumput()));
        return status.get(i);
    }
}
