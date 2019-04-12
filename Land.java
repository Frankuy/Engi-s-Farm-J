import java.util.Random;

public class Land {
    protected boolean rumput;
    protected Point<Integer> lokasi;

    Land(Point<Integer> lokasi) {
        this.lokasi = lokasi;
        Random rand = new Random();
        int n = rand.nextInt(2);
        if (n == 0) {
            rumput = false;
        }
        else {
            rumput = true;
        }
    }
    public Point<Integer> getLokasi() {
        return lokasi;
    }
    public void setRumput(boolean rumput) {
        this.rumput = rumput;
    }
    public boolean getRumput() {
        return rumput;
    }
}
