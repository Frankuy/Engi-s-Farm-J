import java.util.Iterator;
import java.util.LinkedList;

public class Player {
    private int ember;
    private LinkedList<Product> tas;
    private final int kapasitasEmber;
    private final int kapasitasTas;
    private Point<Integer> lokasi;
    private int uang;

    public Player(){
        ember = 10;
        tas = new LinkedList<Product>();
        kapasitasEmber = 10;
        kapasitasTas = 10;
        lokasi = new Point<Integer>(0,0);
        uang = 0;
    }

    public Player(int e, int ke, int kt, Point<Integer> l, int u){
        ember = e;
        tas = new LinkedList<Product>();
        kapasitasEmber = ke;
        kapasitasTas = kt;
        lokasi = l;
        uang = u;
    }

    public int getEmber() {return ember;}
    public int getKapasitasEmber() {return kapasitasEmber;}
    public int getKapasitasTas() {return kapasitasTas;}
    public Point<Integer> getLokasi() {return lokasi;}
    public int getUang() {return uang;}
    public LinkedList<Product> getTas() { return tas; }

    public void setEmber(int _e) { ember = _e; }
    public void setTas(boolean input, Product p) {
        if (input) {
            tas.add(p);
        }
        else {
            tas.remove(p);
        }
    }
    public void setLokasi(Point<Integer> l) { lokasi = l; }
    public void setUang(int u){ uang = u; }

    public String lihatTas() {
        String listTas = "";
        for (Product p : tas) {
            listTas += "- " + p.print() + "\n";
        }
        return listTas;
    }

    public String status() {
        String st = "";
        st += "Ember = " + ember + "/" + kapasitasEmber + "\n";
        st += "Uang = " + uang + "\n";
        st += "Tas = " + tas.size() + "/" + kapasitasTas +"\n" + lihatTas();
        return st;
    }

    public void gerak(String direction, Cell c, LinkedList<Renderable> liveAnimal) {
        boolean valid = true;
        if (direction.equals("UP")) {
            if (lokasi.getY() - 1 >= 0) {
                Point<Integer> newLokasi = new Point<Integer>(lokasi.getX(),lokasi.getY() - 1);
                for (Renderable animal : liveAnimal) {
                    if (animal.getStatus(2).equals(Integer.toString(newLokasi.getX())) && animal.getStatus(3).equals(Integer.toString(newLokasi.getY()))) {
                        System.out.println("Waduh, ada binatang gan");
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    lokasi.setY(lokasi.getY() - 1);
                }
            }
            else {
                System.out.println("Mentok gan!");
            }
        }
        else if (direction.equals("DOWN")) {
            if (lokasi.getY() + 1 < c.getnBrs()) {
                Point<Integer> newLokasi = new Point<>(lokasi.getX(),lokasi.getY() + 1);
                for (Renderable animal : liveAnimal) {
                    if (animal.getStatus(2).equals(Integer.toString(newLokasi.getX())) && animal.getStatus(3).equals(Integer.toString(newLokasi.getY()))) {
                        System.out.println("Waduh, ada binatang gan");
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    lokasi.setY(lokasi.getY() + 1);
                }
            }
            else {
                System.out.println("Mentok gan!");
            }
        }
        else if (direction.equals("RIGHT")) {
            if (lokasi.getX() + 1 < c.getnKol()) {
                Point<Integer> newLokasi = new Point<>(lokasi.getX() + 1,lokasi.getY());

                for (Renderable animal : liveAnimal) {
                    if (animal.getStatus(2).equals(Integer.toString(newLokasi.getX())) && animal.getStatus(3).equals(Integer.toString(newLokasi.getY()))) {
                        System.out.println("Waduh, ada binatang gan");
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    lokasi.setX(lokasi.getX() + 1);
                }
            }
            else {
                System.out.println("Mentok gan!");
            }
        }
        else if (direction.equals("LEFT")) {
            if (lokasi.getX() - 1 >= 0) {
                Point<Integer> newLokasi = new Point<>(lokasi.getX() - 1,lokasi.getY());
                for (Renderable animal : liveAnimal) {
                    if (animal.getStatus(2).equals(Integer.toString(newLokasi.getX())) && animal.getStatus(3).equals(Integer.toString(newLokasi.getY()))) {
                        System.out.println("Waduh, ada binatang gan");
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    lokasi.setX(lokasi.getX() - 1);
                }
            }
            else {
                System.out.println("Mentok gan!");
            }
        }
    }
    public Renderable getProductAnimal(Renderable a, Cell c) {
        if (a.getStatus(7).equals("true")) {
            if (a.getClass().getName().equals("Ayam")) {
                TelorAyam telor = new TelorAyam();
                setTas(true, telor);
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Ayam ayam = new Ayam(a.getStatus(0), lokasi, Integer.parseInt(a.getStatus(5)));
                ayam.setTicks(Integer.parseInt(a.getStatus(4)));
                ayam.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                ayam.setProduceAbleEgg(false);
                return ayam;
            }
        }
        return a;
    }
    public boolean killAnimal(Renderable a) {
        if (a.getStatus(7).equals("true")) {
            if (a.getClass().getName().equals("Ayam")) {
                DagingAyam daging = new DagingAyam();
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
        c.print(p,test);
        System.out.println();

        p.gerak("DOWN", c, test);
        c.print(p,new LinkedList<>());
        System.out.println();

        p.gerak("LEFT", c, test);
        c.print(p,new LinkedList<>());
        System.out.println();

        p.gerak("UP", c, test);
        c.print(p,new LinkedList<>());
        System.out.println();

        System.out.print(p.status());
    }
}
