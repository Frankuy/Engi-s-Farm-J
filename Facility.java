public class Facility
{
	protected Point<Integer> lokasi;
	
	public Facility(Point<Integer> lokasi)
	{
		this.lokasi = lokasi;
	}
	
	public Point<Integer> getLokasi()
	{
		return this.lokasi;
	}
}
