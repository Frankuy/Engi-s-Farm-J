package animal;

import cell.Cell;
import javafx.beans.property.IntegerProperty;
import player.Player;
import tools.Point;
import tools.Renderable;

public interface FarmAnimal {
    void gerak(Cell c, Player p);
    String bersuara();
    void makan(Cell c);
    String mati();
    static boolean validAnimalGerak(int direct, Renderable animal, Cell c, Player player) {
        Point<Integer> newLokasi = null;
        Point<Integer> lokasi = new Point<>(Integer.parseInt(animal.getStatus(2)), Integer.parseInt(animal.getStatus(3)));
        if (direct == 0) {
            newLokasi = new Point<>(lokasi.getX(),lokasi.getY()-1);
            if (newLokasi.getY() < 0) return false;
        }
        else if (direct == 1) {
            newLokasi = new Point<>(lokasi.getX(),lokasi.getY()+1);
            if (newLokasi.getY() >= c.getnBrs()) return false;
        }
        else if (direct == 2) {
            newLokasi = new Point<>(lokasi.getX()+1,lokasi.getY());
            if (newLokasi.getX() >= c.getnKol()) return false;
        }
        else if (direct == 3) {
            newLokasi = new Point<>(lokasi.getX()-1,lokasi.getY());
            if (newLokasi.getX() < 0) return false;
        }

        //Cek newLokasi
        if (newLokasi != null) {
            if (c.getMap(newLokasi) != null) {
                if (animal.getClass().getName().equals("animal.Ayam") || animal.getClass().getName().equals("animal.Bebek")) {
                    return c.getMap(newLokasi).getClass().getName().equals("land.Coop") && player.getLokasi().compareTo(newLokasi) != 0;
                }
                else if (animal.getClass().getName().equals("animal.Sapi") || animal.getClass().getName().equals("animal.Kambing") || animal.getClass().getName().equals("animal.Domba")) {
                    return c.getMap(newLokasi).getClass().getName().equals("land.Barn") && player.getLokasi().compareTo(newLokasi) != 0;
                }
                else if (animal.getClass().getName().equals("animal.Kuda")) {
                    return c.getMap(newLokasi).getClass().getName().equals("land.Grassland") && player.getLokasi().compareTo(newLokasi) != 0;
                }
            }
        }
        return false;
    }
}
