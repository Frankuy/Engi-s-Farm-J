import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.util.Random;

public class Sapi implements MilkProducing, MeatProducing, Renderable {
    private String nama;
    private String suara;
    private Point<Integer> lokasi;
    private int ticks;
    private int waktuLapar;
    private boolean lapar;
    private boolean produceAbleMeat;
    private boolean produceAbleMilk;

    Ayam(String nama, Point<Integer> lokasi, int waktuLapar) {
        this.nama = nama;
        this.suara = "Emooooh";
        this.lokasi = lokasi;
        this.ticks = 0;
        this.waktuLapar = waktuLapar;
        this.produceAbleMilk = true;
        this.produceAbleMeat = true;
        this.lapar = false;
    }
    public String getNama() {
        return nama;
    }
    public Point getLokasi() {
        return lokasi;
    }
    public int getTicks() {
        return ticks;
    }

    @Override
    public void produceMilk() {
        produceAbleMilk = true;
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
    public boolean isProduceAbleMilk() {
        return produceAbleMilk;
    }
    public void setProduceAbleMilk(boolean b) {
        produceAbleMilk = b;
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
        produceMilk();
        produceMeat();
    }

    public void gerak(Cell c) {
        Random rand = new Random();
        int number = rand.nextInt(4);
        if (number == 0) { //Right
            if (lokasi.getX() + 1 < c.getnBrs()) {
                lokasi.setX(lokasi.getX() + 1);
            }
        }
        else if (number == 1) { //Up
            if (lokasi.getY() - 1 >= 0) {
                lokasi.setY(lokasi.getY() - 1);
            }
        }
        else if (number == 2) { //Left
            if (lokasi.getX() - 1 >= 0) {
                lokasi.setX(lokasi.getX() - 1);
            }
        }
        else { //Down
            if (lokasi.getY() + 1 < c.getnKol()) {
                lokasi.setY(lokasi.getY() + 1);
            }
        }
        //Waktu hidup bertambah
        if (ticks != -1) ticks+=1;

        //Kalau sudah waktuLapar
        if (ticks == waktuLapar) {
            lapar = true;
        }
        //Kalau lapar, Makan
        if (lapar) {
            makan(c);
        }
        //Producing time
        if (ticks % waktuLapar == 0) {
            produce();
        }
        //cek masih dapat bertahan hidup kah
        if (lapar && ticks % (waktuLapar + 2) == 0) {
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
        return 's';
    }
    public String getStatus(int i) {
        String[] temp = new String[9];
        temp[0] = nama;
        temp[1] = suara;
        temp[2] = Integer.toString(lokasi.getX());
        temp[3] = Integer.toString(lokasi.getY());
        temp[4] = Integer.toString(ticks);
        temp[5] = Integer.toString(waktuLapar);
        temp[6] = Boolean.toString(lapar);
        temp[7] = Boolean.toString(produceAbleMilk);
        temp[8] = Boolean.toString(produceAbleMeat);
        return temp[i];
    }
}
