package land;

import tools.Point;
import tools.Renderable;
import java.util.Vector;

public class Grassland extends Land implements Renderable
{
	public Grassland(Point<Integer> lokasi)
	{
		super(lokasi);
	}
	
	public char render()
	{
		return this.getRumput() ? '@' : 'G';
	}
	
	public String getStatus(int i)
	{
		Vector<String> status = new Vector<>();
		status.add(Integer.toString(lokasi.getX()));
		status.add(Integer.toString(lokasi.getY()));
		status.add(Boolean.toString(getRumput()));
		return status.get(i);
	}
}
