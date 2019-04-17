package animal;

import cell.Cell;
import player.Player;
import tools.Point;
import tools.Renderable;
import java.util.Random;
import java.util.Vector;

public class Ayam implements EggProducing, MeatProducing, Renderable {
    private String nama;
    private String suara;
    private Point<Integer> lokasi;
    private int ticks;
    private int waktuLapar;
    private boolean lapar;
    private boolean produceAbleMeat = true;
    private boolean produceAbleEgg;

    public Ayam(String nama, Point<Integer> lokasi, int waktuLapar) {
        this.nama = nama;
        this.suara = "Kukuruuyuuk";
        this.lokasi = lokasi;
        this.ticks = 0;
        this.waktuLapar = waktuLapar;
        this.produceAbleEgg = true;
        this.lapar = false;
    }
    public String getNama() {
        return nama;
    }
    public Point<Integer> getLokasi() {
        return lokasi;
    }
    public int getTicks() {
        return ticks;
    }

    @Override
    public void produceEgg() {
        produceAbleEgg = true;
    }

    @Override
    public void produceMeat() {
        produceAbleMeat = true;
    }

    public boolean isLapar() {
        return lapar;
    }
    public boolean isProduceAbleMeat() {
        return produceAbleMeat;
    }
    public boolean isProduceAbleEgg() {
        return produceAbleEgg;
    }
    public void setProduceAbleEgg(boolean b) {
        produceAbleEgg = b;
    }
    public void setProduceAbleMeat(boolean b) {
        produceAbleMeat = b;
    }
    public void setTicks(int t) {
        ticks = t;
    }
    public void setLapar(boolean l) {
        lapar = l;
    }
    public void produce() {
        produceEgg();
        produceMeat();
    }

    public void gerak(Cell c, Player player) {
        Random rand = new Random();
        boolean valid = true;
        Point<Integer> newLokasi = null;
        int number = rand.nextInt(4);
        if (number == 0) { //Up
            valid = FarmAnimal.validAnimalGerak(number, this, c, player);
            newLokasi = new Point<>(lokasi.getX(), lokasi.getY()-1);
        }
        else if (number == 1) { //Down
            valid = FarmAnimal.validAnimalGerak(number, this, c, player);
            newLokasi = new Point<>(lokasi.getX(), lokasi.getY()+1);
        }
        else if (number == 2) { //Right
            valid = FarmAnimal.validAnimalGerak(number, this, c, player);
            newLokasi = new Point<>(lokasi.getX()+1, lokasi.getY());
        }
        else if (number == 3) { //Left
            valid = FarmAnimal.validAnimalGerak(number, this, c, player);
            newLokasi = new Point<>(lokasi.getX() - 1, lokasi.getY());
        }

        if (valid) lokasi = newLokasi;

        //Waktu hidup bertambah
        if (ticks != -1) ticks+=1;

        //Kalau lapar, Makan
        if (lapar) {
            makan(c);
        }
        //Producing time
        if (ticks % waktuLapar == 0) {
            produce();
            lapar = true;
        }
        //cek masih dapat bertahan hidup kah
        if (lapar && ticks % (waktuLapar + 2) == 0) {
            ticks = -1;
            System.out.println(mati());
        }
    }
    public String bersuara() {
        return suara;
    }
    public void makan(Cell c) {
        Renderable currentCell = c.getMap(lokasi);
        if (currentCell != null) {
            if (currentCell.getStatus(2).equals("true")) {
                c.setRumputCell(lokasi, false);
                lapar = false;
            }
        }
    }
    public String mati() {
        ticks = -1;
        return nama + " mati";
    }
    public char render() {
        return 'a';
    }
    public String getStatus(int i) {
        Vector<String> temp = new Vector<>();
        temp.add(nama);
        temp.add(suara);
        temp.add(Integer.toString(lokasi.getX()));
        temp.add(Integer.toString(lokasi.getY()));
        temp.add(Integer.toString(ticks));
        temp.add(Integer.toString(waktuLapar));
        temp.add(Boolean.toString(lapar));
        temp.add(Boolean.toString(produceAbleEgg));
        temp.add(Boolean.toString(produceAbleMeat));
        return temp.get(i);
    }
//    private boolean validAnimalGerak(int direct, Cell c, Player player) {
//        Point<Integer> newLokasi = null;
//        if (direct == 0) {
//            newLokasi = new Point<>(lokasi.getX(),lokasi.getY()-1);
//            if (newLokasi.getY() < 0) return false;
//        }
//        else if (direct == 1) {
//            newLokasi = new Point<>(lokasi.getX(),lokasi.getY()+1);
//            if (newLokasi.getY() >= c.getnBrs()) return false;
//        }
//        else if (direct == 2) {
//            newLokasi = new Point<>(lokasi.getX()+1,lokasi.getY());
//            if (newLokasi.getX() >= c.getnKol()) return false;
//        }
//        else if (direct == 3) {
//            newLokasi = new Point<>(lokasi.getX()-1,lokasi.getY());
//            if (newLokasi.getX() < 0) return false;
//        }
//
//        //Cek newLokasi
//        if (newLokasi != null) {
//            if (c.getMap(newLokasi) != null) {
//                if (c.getMap(newLokasi).getClass().getName().equals("land.Coop") && player.getLokasi().compareTo(newLokasi) != 0) {
//                    return true;
//                }
//            }
//            else {
//                if (player.getLokasi().compareTo(newLokasi) != 0) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
