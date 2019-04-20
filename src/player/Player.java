package player;

import animal.*;
import cell.Cell;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import product.*;
import sideproduct.GeprekSusu;
import sideproduct.PaketSate;
import sideproduct.PaketSteak;
import tools.Point;
import tools.Renderable;

import java.util.LinkedList;
import java.util.Scanner;

public class Player {
    private IntegerProperty ember;
    private LinkedList<Product> tas;
    private final IntegerProperty kapasitasEmber;
    private final IntegerProperty kapasitasTas;
    private Point<Integer> lokasi;
    private IntegerProperty uang;

    public Player(){
        ember = new SimpleIntegerProperty(10);
        tas = new LinkedList<Product>();
        kapasitasEmber = new SimpleIntegerProperty(10);
        kapasitasTas = new SimpleIntegerProperty(10);
        lokasi = new Point<Integer>(0,0);
        uang = new SimpleIntegerProperty(0);
    }

    public Player(int e, int ke, int kt, Point<Integer> l, int u){
        ember = new SimpleIntegerProperty(e);
        tas = new LinkedList<Product>();
        kapasitasEmber = new SimpleIntegerProperty(ke);
        kapasitasTas = new SimpleIntegerProperty(kt);
        lokasi = l;
        uang = new SimpleIntegerProperty(u);
    }

    public int getEmber() {return ember.get();}
    public int getKapasitasEmber() {return kapasitasEmber.get();}
    public int getKapasitasTas() {return kapasitasTas.get();}
    public Point<Integer> getLokasi() {return lokasi;}
    public int getUang() {return uang.get();}
    public LinkedList<Product> getTas() { return tas; }

    public void setEmber(int _e) { ember.set(_e); }
    public void setTas(boolean input, Product p) {
        if (input) {
            tas.add(p);
        }
        else {
            tas.remove(p);
        }
    }
    public void setLokasi(Point<Integer> l) { lokasi = l; }
    public void setUang(int u){ uang.set(u); }

    public StringProperty lihatTas() {
        String listTas = "";
        for (Product p : tas) {
            listTas += "- " + p.print() + "\n";
        }
        StringProperty result = new SimpleStringProperty(listTas);
        return result;
    }

    public StringProperty status() {
        String st = "";
        st += "Ember = " + ember.get() + "/" + kapasitasEmber.get() + "\n";
        st += "Uang = " + uang.get() + "\n";
        st += "Tas = " + tas.size() + "/" + kapasitasTas.get() +"\n" + lihatTas().get();
        StringProperty status = new SimpleStringProperty(st);
        return status;
    }

    public void gerak(String direction, Cell c, LinkedList<Renderable> liveAnimal) {
        boolean valid = true;
        Point<Integer> newLokasi = null;
        if (direction.equals("UP")) {
            valid = validGerak(direction, c, liveAnimal);
            newLokasi = new Point<>(lokasi.getX(), lokasi.getY()-1);
        }
        else if (direction.equals("DOWN")) {
            valid = validGerak(direction, c, liveAnimal);
            newLokasi = new Point<>(lokasi.getX(), lokasi.getY()+1);
        }
        else if (direction.equals("RIGHT")) {
            valid = validGerak(direction, c, liveAnimal);
            newLokasi = new Point<>(lokasi.getX() + 1, lokasi.getY());
        }
        else if (direction.equals("LEFT")) {
            valid = validGerak(direction, c, liveAnimal);
            newLokasi = new Point<>(lokasi.getX() - 1, lokasi.getY());
        }
        if (valid) {
            lokasi = newLokasi;
        }
    }
    public Renderable getProductAnimal(Renderable a) {
        if (a.getStatus(7).equals("true")) {
            if (a.getClass().getName().equals("animal.Ayam")) {
                TelorAyam telor = new TelorAyam();
                setTas(true, telor);
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Ayam ayam = new Ayam(a.getStatus(0), lokasi, Integer.parseInt(a.getStatus(5)));
                ayam.setTicks(Integer.parseInt(a.getStatus(4)));
                ayam.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                ayam.setProduceAbleMeat(Boolean.parseBoolean(a.getStatus(8)));
                ayam.setProduceAbleEgg(false);
                return ayam;
            }
            else if (a.getClass().getName().equals("animal.Bebek")) {
                TelorBebek telor = new TelorBebek();
                setTas(true, telor);
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Bebek b = new Bebek(a.getStatus(0),lokasi, Integer.parseInt(a.getStatus(5)));
                b.setTicks(Integer.parseInt(a.getStatus(4)));
                b.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                b.setProduceAbleMeat(Boolean.parseBoolean(a.getStatus(8)));
                b.setProduceAbleEgg(false);
                return b;
            }
            else if (a.getClass().getName().equals("animal.Kambing")) {
                SusuKambing susu = new SusuKambing();
                setTas(true, susu);
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Kambing b = new Kambing(a.getStatus(0),lokasi, Integer.parseInt(a.getStatus(5)));
                b.setTicks(Integer.parseInt(a.getStatus(4)));
                b.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                b.setProduceAbleMeat(Boolean.parseBoolean(a.getStatus(8)));
                b.setProduceAbleMilk(false);
                return b;
            }
            else if (a.getClass().getName().equals("animal.Domba")) {
                SusuDomba susu = new SusuDomba();
                setTas(true, susu);
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Domba b = new Domba(a.getStatus(0),lokasi, Integer.parseInt(a.getStatus(5)));
                b.setTicks(Integer.parseInt(a.getStatus(4)));
                b.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                b.setProduceAbleMeat(Boolean.parseBoolean(a.getStatus(8)));
                b.setProduceAbleMilk(false);
                return b;
            }
            else if (a.getClass().getName().equals("animal.Sapi")) {
                SusuSapi susu = new SusuSapi();
                setTas(true, susu);
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Sapi b = new Sapi(a.getStatus(0),lokasi, Integer.parseInt(a.getStatus(5)));
                b.setTicks(Integer.parseInt(a.getStatus(4)));
                b.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                b.setProduceAbleMeat(Boolean.parseBoolean(a.getStatus(8)));
                b.setProduceAbleMilk(false);
                return b;
            }
            else if (a.getClass().getName().equals("animal.Kuda")) {
                SusuKuda susu = new SusuKuda();
                setTas(true, susu);
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Kuda b = new Kuda(a.getStatus(0),lokasi, Integer.parseInt(a.getStatus(5)));
                b.setTicks(Integer.parseInt(a.getStatus(4)));
                b.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                b.setProduceAbleMilk(false);
                return b;
            }
        }
        return a;
    }
    public boolean killAnimal(Renderable a) {
        if (a.getStatus(7).equals("true")) {
            if (a.getClass().getName().equals("animal.Ayam")) {
                DagingAyam daging = new DagingAyam();
                setTas(true, daging);
                return true; //kill
            }
            if (a.getClass().getName().equals("animal.Bebek")) {
                DagingBebek daging = new DagingBebek();
                setTas(true, daging);
                return true; //kill
            }
            if (a.getClass().getName().equals("animal.Kambing")) {
                DagingKambing daging = new DagingKambing();
                setTas(true, daging);
                return true; //kill
            }
            if (a.getClass().getName().equals("animal.Sapi")) {
                DagingSapi daging = new DagingSapi();
                setTas(true, daging);
                return true; //kill
            }
            if (a.getClass().getName().equals("animal.Domba")) {
                DagingDomba daging = new DagingDomba();
                setTas(true, daging);
                return true; //kill
            }
        }
        return false;
    }

    public static void main(String []args) {
        Player p = new Player();
        LinkedList<Renderable> test = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            p.setTas(true, new Product("ahau", 20));
        }
        Cell c = new Cell();
        p.gerak("RIGHT", c, test);
//        c.print(p,test);
        System.out.println();

        p.gerak("DOWN", c, test);
//        c.print(p,new LinkedList<>());
        System.out.println();

        p.gerak("LEFT", c, test);
//        c.print(p,new LinkedList<>());
        System.out.println();

        p.gerak("UP", c, test);
//        c.print(p,new LinkedList<>());
        System.out.println();

        System.out.print(p.status());
    }

    private boolean validGerak(String direction, Cell c, LinkedList<Renderable> farmAnimal) {
        Point<Integer> newLokasi = null;
        if (direction.equals("UP")) {
            newLokasi = new Point<>(lokasi.getX(),lokasi.getY()-1);
            if (newLokasi.getY() < 0) return false;
        }
        else if (direction.equals("DOWN")) {
            newLokasi = new Point<>(lokasi.getX(),lokasi.getY()+1);
            if (newLokasi.getY() >= c.getnBrs()) return false;
        }
        else if (direction.equals("RIGHT")) {
            newLokasi = new Point<>(lokasi.getX()+1,lokasi.getY());
            if (newLokasi.getX() >= c.getnKol()) return false;
        }
        else if (direction.equals("LEFT")) {
            newLokasi = new Point<>(lokasi.getX()-1,lokasi.getY());
            if (newLokasi.getX() < 0) return false;
        }

        //Cek newLokasi
        if (newLokasi != null) {
            if (c.getMap(newLokasi) != null) {
                if (c.getMap(newLokasi).getClass().getSuperclass().getName().equals("facility.Facility")) {
                    return false;
                }
                for (Renderable animal : farmAnimal) {
                    Point<Integer> animalLokasi = new Point<>(Integer.parseInt(animal.getStatus(2)),Integer.parseInt(animal.getStatus(3)));
                    if (animalLokasi.compareTo(newLokasi) == 0) {
                        return false;
                    }
                }
                return true;
            }
            else {
                for (Renderable animal : farmAnimal) {
                    Point<Integer> animalLokasi = new Point<>(Integer.parseInt(animal.getStatus(2)),Integer.parseInt(animal.getStatus(3)));
                    if (animalLokasi.compareTo(newLokasi) == 0) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void interactFacility(Renderable facility) {
        if (facility.getClass().getName().equals("facility.Truck")) {
            //JUAL SEMUA BARANG YANG ADA DI TAS
            int penghasilan = 0;
            for (Product item : tas) {
                penghasilan += item.getHarga();
            }
            uang.setValue(uang.getValue() + penghasilan);
            tas.clear();
        }
        else if (facility.getClass().getName().equals("facility.Well")) {
            //Nimba air
            ember.setValue(kapasitasEmber.getValue());
        }
    }
}
