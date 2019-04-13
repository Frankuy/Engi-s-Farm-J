public class Coop extends Land implements Renderable
{
    public Coop(Point<Integer> lokasi)
    {
        super(lokasi);
    }
    
    public char render()
    {
		return this.getRumput() ? '*' : 'C';
    }
    
    public String getStatus(int i)
    {
        String[] status = new String[3];
        status[0] = Integer.toString(lokasi.getX());
        status[1] = Integer.toString(lokasi.getY());
        status[2] = Boolean.toString(getRumput());
        return status[i];
    }
}
