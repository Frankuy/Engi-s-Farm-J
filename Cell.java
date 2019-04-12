import java.util.LinkedList;

public class Cell {
    private Renderable[][] map;
    private LinkedList<FarmAnimal> liveAnimal;
    private final int nKol;
    private final int nBrs;

    Cell() {
        nKol = 10;
        nBrs = 10;
        map = new Renderable[10][10];
    }
    Cell(int nBrs, int nKol) {
        this.nKol = nKol;
        this.nBrs = nBrs;
        map = new Renderable[nBrs][nKol];
    }
    public int getnBrs() { return nBrs; }
    public int getnKol() { return nKol; }

    public void setMap(Point<Integer> lokasi, Renderable o) {
        map[lokasi.getY()][lokasi.getX()] = o;
    }
    public Renderable getMap(Point<Integer> lokasi) {
        return map[lokasi.getY()][lokasi.getX()];
    }
    public void setRumputCell(Point<Integer> lokasi, boolean rumput) {
        if (getMap(lokasi).getClass().getName().equals("Coop")) {
            Coop c = new Coop(lokasi);
            c.setRumput(rumput);
            setMap(lokasi, c);
        }
        else if (getMap(lokasi).getClass().getName().equals("Barn")) {
//            Barn b = new Barn(lokasi);
//            b.setRumput(rumput);
//            setMap(lokasi, b);
        }
        else if (getMap(lokasi).getClass().getName().equals("Grassland")) {
//            Grassland g = new Grassland(lokasi);
//            g.setRumput(rumput);
//            setMap(lokasi, g);
        }
    }

    LinkedList<Renderable> gerakAnimal(LinkedList<Renderable> animal) {
        LinkedList<Renderable> animalbaru = new LinkedList<Renderable>();
        while (animal.size() != 0) {
            Renderable a = animal.pop();
            if (a.getClass().getName().equals("Ayam")) {
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Ayam ayam = new Ayam(a.getStatus(0), lokasi, Integer.parseInt(a.getStatus(5)));
                ayam.setTicks(Integer.parseInt(a.getStatus(4)));
                ayam.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                ayam.gerak(this);
                if (ayam.getTicks() != -1) {
                    animalbaru.push(ayam);
                }
            }
        }
        return animalbaru;
    }

    public void print(Player p, LinkedList<Renderable> liveAnimal) {
        boolean write = false;
        for(int i = 0; i < getnBrs(); i++) {
            for(int j = 0; j < getnKol(); j++) {
                Renderable currentCell = getMap(new Point<>(j,i));
                if (p.getLokasi().compareTo(new Point<>(j,i)) == 0) {
                    System.out.print("P");
                    write = true;
                }
                else if (liveAnimal.size() != 0) {
                    for(Renderable animal : liveAnimal) {
                        if (!write && animal.getStatus(2).equals(Integer.toString(j)) && animal.getStatus(3).equals(Integer.toString(i))) {
                            System.out.print(animal.render());
                            write = true;
                        }
                    }
                }
                if (currentCell != null && !write) {
                    System.out.print(currentCell.render());
                }
                else if (!write) {
                    System.out.print("-");
                }
                write = false;

                if (j == getnKol() - 1) {
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {
        Cell cell = new Cell();
        Coop c = new Coop(new Point<>(0,0));
        Player p = new Player();
        cell.setMap(new Point<>(0,0), c);
//        cell.print();

        cell.setRumputCell(new Point<>(0,0), true);
        System.out.println();
//        cell.print();
    }
}
