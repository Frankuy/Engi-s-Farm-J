import java.util.Random;

public interface FarmAnimal {
    void gerak(Cell c);
    String bersuara();
    void makan(Cell c);
    String mati();
//    protected Point lokasi;
//    protected String nama;
//    protected  String suara;
//    protected final int waktuLapar;
//    protected boolean lapar;
//    protected int ticks;

//    public FarmAnimal(Point l, String n, String s, int t) {
//        lokasi = l;
//        nama = n;
//        suara = s;
//        waktuLapar = t;
//        lapar = false;
//        ticks = 0;
//    }

//    public Point getLokasi() { return lokasi; }
//    public String getNama() { return nama; }
//    public String getSuara() { return suara; }
//    public boolean getLapar() { return lapar; }
//    public void setLokasi(Point l) { lokasi = l; }
//    public void setNama (String n) { nama = n; }
//    public void setSuara (String s) { suara = s; }
//    public void setLapar (boolean b) { lapar = b; }

//    public void gerak() {
//        Random rand = new Random();
//        int number = rand.nextInt(4);
//        if (number == 0) { //Right
//            lokasi.setX(lokasi.getX() + 1);
//        }
//        else if (number == 1) { //Up
//            lokasi.setY(lokasi.getY() + 1);
//        }
//        else if (number == 2) { //Left
//            lokasi.setX(lokasi.getX() - 1);
//        }
//        else { //Down
//            lokasi.setY(lokasi.getY() + 1);
//        }
//        ticks+=1;
//        if (ticks == waktuLapar) {
//            lapar = true;
//        }
//    }
//    public String bersuara() {
//        return suara;
//    }
//    public void makan() {
//        lapar = false;
//        ticks = 0;
//    }
//    public void mati() {
//    }
//    abstract public Product produce();
}
