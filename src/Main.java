import animal.Ayam;
import cell.Cell;
import facility.Mixer;
import land.Coop;
import player.Player;
import product.DagingAyam;
import product.Product;
import product.SusuSapi;
import sideproduct.GeprekSusu;
import tools.Point;
import tools.Renderable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Cell c = new Cell();
        Player p = new Player();
        LinkedList<Renderable> liveAnimal = new LinkedList<Renderable>();
        Ayam a = new Ayam("Jago", new Point<>(2, 3), 5);
        liveAnimal.add(a);
        a = new Ayam("HEHE", new Point<>(3, 3), 5);
        liveAnimal.add(a);
        Coop coop = new Coop(new Point<>(1, 2));
        c.setMap(coop.getLokasi(), coop);
        coop = new Coop(new Point<>(3, 2));
        c.setMap(coop.getLokasi(), coop);
        coop = new Coop(new Point<>(2, 1));
        c.setMap(coop.getLokasi(), coop);
        int ticks = 0;
//        Mixer m = new Mixer();
//        c.setMap(new Point<>(1, 0), m);
        p.setTas(true, new SusuSapi());
        p.setTas(true, new DagingAyam());

        System.out.println(p.status());
        c.print(p, liveAnimal);
        String cmd = "";
        do {
            Scanner in = new Scanner(System.in);
            cmd = in.nextLine();
            if (cmd.equals("UP")) {
                p.gerak(cmd, c, liveAnimal);
            } else if (cmd.equals("DOWN")) {
                p.gerak(cmd, c, liveAnimal);
            } else if (cmd.equals("RIGHT")) {
                p.gerak(cmd, c, liveAnimal);
            } else if (cmd.equals("LEFT")) {
                p.gerak(cmd, c, liveAnimal);
            } else if (cmd.equals("INTERACT")) {
                int idx = 0;
                for (Renderable animal : liveAnimal) {
                    if (p.getLokasi().getX() + 1 == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() == Integer.parseInt(animal.getStatus(3))) {
                        animal = p.getProductAnimal(animal);
                        liveAnimal.set(idx, animal);
                    }
                    if (p.getLokasi().getX() == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() + 1 == Integer.parseInt(animal.getStatus(3))) {
                        animal = p.getProductAnimal(animal);
                        liveAnimal.set(idx, animal);
                    }
                    if (p.getLokasi().getX() - 1 == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() == Integer.parseInt(animal.getStatus(3))) {
                        animal = p.getProductAnimal(animal);
                        liveAnimal.set(idx, animal);
                    }
                    if (p.getLokasi().getX() == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() - 1 == Integer.parseInt(animal.getStatus(3))) {
                        animal = p.getProductAnimal(animal);
                        liveAnimal.set(idx, animal);
                    }
                    idx += 1;
                }
            } else if (cmd.equals("TALK")) {
                for (Renderable animal : liveAnimal) {
                    if (p.getLokasi().getX() + 1 == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() == Integer.parseInt(animal.getStatus(3))) {
                        System.out.println(animal.getStatus(1));
                    }
                    if (p.getLokasi().getX() == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() + 1 == Integer.parseInt(animal.getStatus(3))) {
                        System.out.println(animal.getStatus(1));
                    }
                    if (p.getLokasi().getX() - 1 == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() == Integer.parseInt(animal.getStatus(3))) {
                        System.out.println(animal.getStatus(1));
                    }
                    if (p.getLokasi().getX() == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() - 1 == Integer.parseInt(animal.getStatus(3))) {
                        System.out.println(animal.getStatus(1));
                    }
                }
            } else if (cmd.equals("KILL")) {
                for (Renderable animal : liveAnimal) {
                    if (p.getLokasi().getX() + 1 == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() == Integer.parseInt(animal.getStatus(3))) {
                        if (p.killAnimal(animal)) {
                            liveAnimal.remove(animal);
                        }
                    }
                    if (p.getLokasi().getX() == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() + 1 == Integer.parseInt(animal.getStatus(3))) {
                        if (p.killAnimal(animal)) {
                            liveAnimal.remove(animal);
                        }
                    }
                    if (p.getLokasi().getX() - 1 == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() == Integer.parseInt(animal.getStatus(3))) {
                        if (p.killAnimal(animal)) {
                            liveAnimal.remove(animal);
                        }
                    }
                    if (p.getLokasi().getX() == Integer.parseInt(animal.getStatus(2)) && p.getLokasi().getY() - 1 == Integer.parseInt(animal.getStatus(3))) {
                        if (p.killAnimal(animal)) {
                            liveAnimal.remove(animal);
                        }
                    }
                }
            } else if (cmd.equals("GROW")) {
                if (p.getEmber() > 0) {
                    if (c.getMap(p.getLokasi()).getClass().getSuperclass().getName().equals("land")) {
                        c.setRumputCell(p.getLokasi(), true);
                        p.setEmber(p.getEmber() - 1);
                    } else {
                        System.out.println("Tidak ada land.land");
                    }
                } else {
                    System.out.println("Tidak ada air di ember");
                }
            } else if (cmd.equals("MIX")) {
                boolean validLoc = false;
                if (c.getMap(new Point<>(p.getLokasi().getX() + 1, p.getLokasi().getY())).getClass().getName().equals("facility.Mixer")) {
                    validLoc = true;
                } else if (c.getMap(new Point<>(p.getLokasi().getX(), p.getLokasi().getY() + 1)).getClass().getName().equals("facility.Mixer")) {
                    validLoc = true;
                } else if (c.getMap(new Point<>(p.getLokasi().getX() - 1, p.getLokasi().getY())).getClass().getName().equals("facility.Mixer")) {
                    validLoc = true;
                } else if (c.getMap(new Point<>(p.getLokasi().getX(), p.getLokasi().getY() - 1)).getClass().getName().equals("facility.Mixer")) {
                    validLoc = true;
                }
                if (validLoc) {
                    System.out.println("MAU BUAT APA?");
                    System.out.println("1. Geprek Susu");
                    Scanner sc = new Scanner(System.in);
                    int side = sc.nextInt();
                    if (side == 1) {
                        GeprekSusu gs = new GeprekSusu();
                        LinkedList<Product> recipesLinked = new LinkedList<>(gs.getRecipes());
                        if (p.getTas().containsAll(gs.getRecipes())) {
                            p.setTas(true, gs);
                            for (Product prod : recipesLinked) {
                                p.setTas(false, prod);
                            }
                        }
                    }
                } else System.out.println("Tidak berada di dekat facility.Mixer");
            } else {
                System.out.println("Command tidak ads");
            }
            if (ticks % 5 == 0) {
                liveAnimal = c.gerakAnimal(liveAnimal, p);
            }
            System.out.println(p.status());
            c.print(p, liveAnimal);
            ticks += 1;
        } while (!cmd.equals("EXIT") && liveAnimal.size() != 0);
    }
}
