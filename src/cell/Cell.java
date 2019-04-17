package cell;

import animal.*;
import land.Barn;
import land.Coop;
import land.Grassland;
import player.Player;
import tools.Point;
import tools.Renderable;

import java.util.LinkedList;

public class Cell {
    private Renderable[][] map;
    private LinkedList<FarmAnimal> liveAnimal;
    private final int nKol;
    private final int nBrs;

    public Cell() {
        nKol = 10;
        nBrs = 10;
        map = new Renderable[10][10];
    }
    public Cell(int nBrs, int nKol) {
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
        if (getMap(lokasi).getClass().getName().equals("land.Coop")) {
            Coop c = new Coop(lokasi);
            c.setRumput(rumput);
            setMap(lokasi, c);
        }
        else if (getMap(lokasi).getClass().getName().equals("land.Barn")) {
            Barn b = new Barn(lokasi);
            b.setRumput(rumput);
            setMap(lokasi, b);
        }
        else if (getMap(lokasi).getClass().getName().equals("land.Grassland")) {
            Grassland g = new Grassland(lokasi);
            g.setRumput(rumput);
            setMap(lokasi, g);
        }
    }

    public LinkedList<Renderable> gerakAnimal(LinkedList<Renderable> animal, Player p) {
        LinkedList<Renderable> animalbaru = new LinkedList<>();
        while (animal.size() != 0) {
            Renderable a = animal.pop();
            if (a.getClass().getName().equals("animal.Ayam")) {
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Ayam ayam = new Ayam(a.getStatus(0), lokasi, Integer.parseInt(a.getStatus(5)));
                ayam.setTicks(Integer.parseInt(a.getStatus(4)));
                ayam.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                ayam.setProduceAbleEgg(Boolean.parseBoolean(a.getStatus(7)));
                ayam.setProduceAbleMeat(Boolean.parseBoolean(a.getStatus(8)));
                ayam.gerak(this, p);
                if (ayam.getTicks() != -1) {
                    if (!cekDoubleAnimal(animalbaru, ayam)) {
                        animalbaru.push(ayam);
                    }
                    else {
                        animalbaru.push(a);
                    }
                }
            }
            else if (a.getClass().getName().equals("animal.Bebek")) {
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Bebek b = new Bebek(a.getStatus(0), lokasi, Integer.parseInt(a.getStatus(5)));
                b.setTicks(Integer.parseInt(a.getStatus(4)));
                b.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                b.setProduceAbleEgg(Boolean.parseBoolean(a.getStatus(7)));
                b.setProduceAbleMeat(Boolean.parseBoolean(a.getStatus(8)));
                b.gerak(this, p);
                if (b.getTicks() != -1) {
                    if (!cekDoubleAnimal(animalbaru, b)) {
                        animalbaru.push(b);
                    }
                    else {
                        animalbaru.push(a);
                    }
                }
            }
            else if (a.getClass().getName().equals("animal.Kuda")) {
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Kuda b = new Kuda(a.getStatus(0), lokasi, Integer.parseInt(a.getStatus(5)));
                b.setTicks(Integer.parseInt(a.getStatus(4)));
                b.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                b.setProduceAbleMilk(Boolean.parseBoolean(a.getStatus(7)));
                b.gerak(this, p);
                if (b.getTicks() != -1) {
                    if (!cekDoubleAnimal(animalbaru, b)) {
                        animalbaru.push(b);
                    }
                    else {
                        animalbaru.push(a);
                    }
                }
            }
            else if (a.getClass().getName().equals("animal.Sapi")) {
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Sapi b = new Sapi(a.getStatus(0), lokasi, Integer.parseInt(a.getStatus(5)));
                b.setTicks(Integer.parseInt(a.getStatus(4)));
                b.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                b.setProduceAbleMilk(Boolean.parseBoolean(a.getStatus(7)));
                b.setProduceAbleMeat(Boolean.parseBoolean(a.getStatus(8)));
                b.gerak(this, p);
                if (b.getTicks() != -1) {
                    if (!cekDoubleAnimal(animalbaru, b)) {
                        animalbaru.push(b);
                    }
                    else {
                        animalbaru.push(a);
                    }
                }
            }
            else if (a.getClass().getName().equals("animal.Kambing")) {
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Kambing b = new Kambing(a.getStatus(0), lokasi, Integer.parseInt(a.getStatus(5)));
                b.setTicks(Integer.parseInt(a.getStatus(4)));
                b.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                b.setProduceAbleMilk(Boolean.parseBoolean(a.getStatus(7)));
                b.setProduceAbleMeat(Boolean.parseBoolean(a.getStatus(8)));
                b.gerak(this, p);
                if (b.getTicks() != -1) {
                    if (!cekDoubleAnimal(animalbaru, b)) {
                        animalbaru.push(b);
                    }
                    else {
                        animalbaru.push(a);
                    }
                }
            }
            else if (a.getClass().getName().equals("animal.Domba")) {
                Point<Integer> lokasi = new Point<>(Integer.parseInt(a.getStatus(2)), Integer.parseInt(a.getStatus(3)));
                Domba b = new Domba(a.getStatus(0), lokasi, Integer.parseInt(a.getStatus(5)));
                b.setTicks(Integer.parseInt(a.getStatus(4)));
                b.setLapar(Boolean.parseBoolean(a.getStatus(6)));
                b.setProduceAbleMilk(Boolean.parseBoolean(a.getStatus(7)));
                b.setProduceAbleMeat(Boolean.parseBoolean(a.getStatus(8)));
                b.gerak(this, p);
                if (b.getTicks() != -1) {
                    if (!cekDoubleAnimal(animalbaru, b)) {
                        animalbaru.push(b);
                    }
                    else {
                        animalbaru.push(a);
                    }
                }
            }
        }
        return animalbaru;
    }

    public void print(Player p, LinkedList<Renderable> liveAnimal) {
        boolean write = false;
        for (int i = 0; i < getnBrs(); i++) {
            for (int j = 0; j < getnKol(); j++) {
                Renderable currentCell = getMap(new Point<>(j, i));
                if (p.getLokasi().compareTo(new Point<>(j, i)) == 0) {
                    System.out.print("P");
                    write = true;
                } else if (liveAnimal.size() != 0) {
                    for (Renderable animal : liveAnimal) {
                        if (!write && animal.getStatus(2).equals(Integer.toString(j)) && animal.getStatus(3).equals(Integer.toString(i))) {
                            System.out.print(animal.render());
                            write = true;
                        }
                    }
                }
                if (currentCell != null && !write) {
                    System.out.print(currentCell.render());
                } else if (!write) {
                    System.out.print("-");
                }
                write = false;

                if (j == getnKol() - 1) {
                    System.out.println();
                }
            }
        }
    }
    private boolean cekDoubleAnimal(LinkedList<Renderable> farmAnimal, Renderable animal) {
        boolean berdua = false;
        for (Renderable a : farmAnimal) {
            if (animal.getStatus(2).equals(a.getStatus(2)) && animal.getStatus(3).equals(a.getStatus(3)) && !berdua) {
                berdua = true;
            }
        }
        return berdua;
    }
}
