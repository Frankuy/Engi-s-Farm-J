import animal.*;
import cell.Cell;

import facility.Mixer;
import facility.Truck;
import facility.Well;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import land.Barn;
import land.Coop;
import land.Grassland;
import player.Player;
import product.DagingAyam;
import product.Product;
import product.SusuSapi;
import sideproduct.GeprekSusu;
import sideproduct.PaketSate;
import sideproduct.PaketSteak;
import sideproduct.SideProduct;
import tools.Point;
import tools.Renderable;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Player player;
    private Cell cell;
    private LinkedList<Renderable> farmAnimal;
    private GridPane grid;
    private boolean wantToMix = false;

    @FXML
    private AnchorPane root;

    @FXML
    private TextArea gameStatus;

    @FXML
    private TextField commandEntry;

    @FXML
    private Button commandButton;

    @FXML
    private StackPane mapImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initialize player and cell
        player = new Player();
        cell = new Cell();
        farmAnimal = new LinkedList<>();
        //Make Grid
        grid = new GridPane();
        for (int i = 0; i < cell.getnKol(); i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / cell.getnKol());
            grid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < cell.getnBrs(); i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / cell.getnBrs());
            grid.getRowConstraints().add(rowConst);
        }
        grid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setGridLinesVisible(true);
        mapImage.getChildren().addAll(grid);

        //Create default game
        //LAND
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Grassland grass = new Grassland(new Point<>(i,j));
                cell.setMap(grass.getLokasi(), grass);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Coop coop = new Coop(new Point<>(i,j));
                cell.setMap(coop.getLokasi(), coop);
            }
        }
        for (int i = 4; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                Barn barn = new Barn(new Point<>(i,j));
                cell.setMap(barn.getLokasi(), barn);
            }
        }
        //FACILITY
        Mixer mixer = new Mixer(new Point<>(9,4));
        cell.setMap(mixer.getLokasi(), mixer);
        Truck truck = new Truck(new Point<>(9,5));
        cell.setMap(truck.getLokasi(), truck);
        Well well = new Well(new Point<>(9,6));
        cell.setMap(well.getLokasi(), well);
        //ANIMAL
        Ayam ayam = new Ayam("Aay", new Point<>(2,2), 10);
        insertAnimal(ayam);
        Bebek bebek = new Bebek("Beb", new Point<>(3,3), 10);
        insertAnimal(bebek);
        Kambing kambing = new Kambing("Kams", new Point<>(7,2), 15);
        insertAnimal(kambing);
        Sapi sapi = new Sapi("Sup", new Point<>(7,1), 20);
        insertAnimal(sapi);
        Domba domba = new Domba("Domsky", new Point<>(6,1), 10);
        insertAnimal(domba);
        Kuda kuda = new Kuda("KudI", new Point<>(3, 7), 25);
        insertAnimal(kuda);
        kuda = new Kuda("Kudo", new Point<>(7, 7), 25);
        insertAnimal(kuda);

        //Colouring grid
        colourGrid();

        //Status Player awal
        updateStatus();
    }

    @FXML
    public void enterCommand(ActionEvent event) {
        if (!commandEntry.getText().equals("")) {
            selectCommand(commandEntry.getText());
            colourGrid();
            commandEntry.setText("");
        }
    }

    @FXML
    public void runCommand(MouseEvent event) {
        if (!commandEntry.getText().equals("")) {
            selectCommand(commandEntry.getText());
            colourGrid();
            commandEntry.setText("");
        }
    }

    private void colourGrid() {
        for (int i = 0; i < cell.getnKol(); i++) {
            for (int j = 0; j < cell.getnBrs(); j++) {
                Rectangle rect = new Rectangle(mapImage.getPrefWidth()/cell.getnKol(),mapImage.getPrefHeight()/cell.getnBrs());
                if (cell.getMap(new Point<>(i,j)) == null) rect.setFill(Color.WHITE);
                else if (cell.getMap(new Point<>(i,j)).getClass().getName().equals("land.Coop")) rect.setFill(Color.YELLOW);
                else if (cell.getMap(new Point<>(i,j)).getClass().getName().equals("land.Barn")) rect.setFill(Color.RED);
                else if (cell.getMap(new Point<>(i,j)).getClass().getName().equals("land.Grassland")) rect.setFill(Color.LIGHTGREEN);
                else if (cell.getMap(new Point<>(i,j)).getClass().getSuperclass().getName().equals("facility.Facility")) rect.setFill(Color.LIGHTBLUE);

                StackPane gridview = new StackPane();
                if (player.getLokasi().compareTo(new Point<>(i,j)) == 0) {
                    gridview.getChildren().addAll(rect, new Label("P"));
                    grid.add(gridview,i,j);
                }
                else if (cell.getMap(new Point<>(i,j)) != null) {
                    boolean animalthere = false;
                    for (Renderable animal : farmAnimal) {
                        Point<Integer> animalLokasi = new Point<>(Integer.parseInt(animal.getStatus(2)),Integer.parseInt(animal.getStatus(3)));
                        if (animalLokasi.compareTo(new Point<>(i,j)) == 0 && !animalthere) {
                            animalthere = true;
                            if (animal.getStatus(6).equals("true")) gridview.getChildren().addAll(rect, new Label(animal.render() + "*"));
                            else gridview.getChildren().addAll(rect, new Label(Character.toString(animal.render())));
                        }
                    }
                    if (!animalthere) {
                        gridview.getChildren().addAll(rect, new Label(Character.toString(cell.getMap(new Point<>(i, j)).render())));
                    }
                    grid.add(gridview,i,j);
                }
                else grid.add(rect, i, j);
            }
        }
    }

    private void selectCommand(String cmd) {
        if (cmd.equals("UP")) {
            player.gerak(cmd, cell, farmAnimal);
            farmAnimal = cell.gerakAnimal(farmAnimal, player);
            updateStatus();
        }
        else if (cmd.equals("DOWN")) {
            player.gerak(cmd, cell, farmAnimal);
            farmAnimal = cell.gerakAnimal(farmAnimal, player);
            updateStatus();
        }
        else if (cmd.equals("RIGHT")) {
            player.gerak(cmd, cell, farmAnimal);
            farmAnimal = cell.gerakAnimal(farmAnimal, player);
            updateStatus();
        }
        else if (cmd.equals("LEFT")) {
            player.gerak(cmd, cell, farmAnimal);
            farmAnimal = cell.gerakAnimal(farmAnimal, player);
            updateStatus();
        }
        else if (cmd.equals("GROW")) {
            if (player.getEmber() > 0) {
                if (cell.getMap(player.getLokasi()) != null) {
                    if (cell.getMap(player.getLokasi()).getClass().getSuperclass().getName().equals("land.Land")) {
                        if (cell.getMap(player.getLokasi()).getStatus(2).equals("false")) {
                            cell.setRumputCell(player.getLokasi(), true);
                            player.setEmber(player.getEmber() - 1);
                        }
                    } else {
                        System.out.println("Tidak ada land");
                    }
                }
            } else {
                System.out.println("Tidak ada air di ember");
            }
            updateStatus();
        }
        else if (cmd.equals("INTERACT")) {
            int idx = 0;
            for (Renderable animal : farmAnimal) {
                Point<Integer> animalLokasi = new Point<>(Integer.parseInt(animal.getStatus(2)), Integer.parseInt(animal.getStatus(3)));
                if (player.getLokasi().getY() - 1 >= 0 && player.getLokasi().getX().equals(animalLokasi.getX()) && (player.getLokasi().getY()-1) == (animalLokasi.getY()) ) {
                    animal = player.getProductAnimal(animal);
                    farmAnimal.set(idx, animal);
                }
                if (player.getLokasi().getY() + 1 < cell.getnBrs() && player.getLokasi().getX().equals(animalLokasi.getX()) && (player.getLokasi().getY()+1) == (animalLokasi.getY())) {
                    animal = player.getProductAnimal(animal);
                    farmAnimal.set(idx, animal);
                }
                if (player.getLokasi().getX() + 1 < cell.getnKol() && (player.getLokasi().getX()+1) == (animalLokasi.getX()) && player.getLokasi().getY().equals(animalLokasi.getY())) {
                    animal = player.getProductAnimal(animal);
                    farmAnimal.set(idx, animal);
                }
                if (player.getLokasi().getY() - 1 >= 0 && (player.getLokasi().getX()-1) == (animalLokasi.getX()) && player.getLokasi().getY().equals(animalLokasi.getY())) {
                    animal = player.getProductAnimal(animal);
                    farmAnimal.set(idx, animal);
                }
                idx += 1;
            }
            //INTERACT WITH FACILITY
            if (player.getLokasi().getY() - 1 >= 0) {
                player.interactFacility(cell.getMap(new Point<>(player.getLokasi().getX(), player.getLokasi().getY()-1)));
            }
            if (player.getLokasi().getY() + 1 < cell.getnBrs()) {
                player.interactFacility(cell.getMap(new Point<>(player.getLokasi().getX(), player.getLokasi().getY()+1)));
            }
            if (player.getLokasi().getX() + 1 < cell.getnKol()) {
                player.interactFacility(cell.getMap(new Point<>(player.getLokasi().getX() + 1, player.getLokasi().getY())));
            }
            if (player.getLokasi().getX() - 1 >= 0) {
                player.interactFacility(cell.getMap(new Point<>(player.getLokasi().getX() - 1, player.getLokasi().getY())));
            }
            updateStatus();
        }
        else if (cmd.equals("KILL")) {
            for (Renderable animal : farmAnimal) {
                if (player.getLokasi().getX() + 1 == Integer.parseInt(animal.getStatus(2)) && player.getLokasi().getY() == Integer.parseInt(animal.getStatus(3))) {
                    if (player.killAnimal(animal)) {
                        farmAnimal.remove(animal);
                    }
                }
                if (player.getLokasi().getX() == Integer.parseInt(animal.getStatus(2)) && player.getLokasi().getY() + 1 == Integer.parseInt(animal.getStatus(3))) {
                    if (player.killAnimal(animal)) {
                        farmAnimal.remove(animal);
                    }
                }
                if (player.getLokasi().getX() - 1 == Integer.parseInt(animal.getStatus(2)) && player.getLokasi().getY() == Integer.parseInt(animal.getStatus(3))) {
                    if (player.killAnimal(animal)) {
                        farmAnimal.remove(animal);
                    }
                }
                if (player.getLokasi().getX() == Integer.parseInt(animal.getStatus(2)) && player.getLokasi().getY() - 1 == Integer.parseInt(animal.getStatus(3))) {
                    if (player.killAnimal(animal)) {
                        farmAnimal.remove(animal);
                    }
                }
            }
            updateStatus();
        }
        else if (cmd.equals("MIX")) {
            if (player.getLokasi().getY() - 1 >= 0) {
                if (cell.getMap(new Point<>(player.getLokasi().getX(), player.getLokasi().getY() - 1)).getClass().getName().equals("facility.Mixer")) {
                    showMenu();
                    wantToMix = true;
                }
            }
            if (player.getLokasi().getY() + 1 < cell.getnBrs()) {
                if (cell.getMap(new Point<>(player.getLokasi().getX(), player.getLokasi().getY() + 1)).getClass().getName().equals("facility.Mixer")) {
                    showMenu();
                    wantToMix = true;
                }
            }
            if (player.getLokasi().getX() + 1 < cell.getnKol()) {
                if (cell.getMap(new Point<>(player.getLokasi().getX() + 1, player.getLokasi().getY())).getClass().getName().equals("facility.Mixer")) {
                    showMenu();
                    wantToMix = true;
                }
            }
            if (player.getLokasi().getX() - 1 >= 0) {
                if (cell.getMap(new Point<>(player.getLokasi().getX() - 1, player.getLokasi().getY())).getClass().getName().equals("facility.Mixer")) {
                    showMenu();
                    wantToMix = true;
                }
            }
        }
        else if (cmd.equals("TALK")) {
            int idx = 0;
            for (Renderable animal : farmAnimal) {
                Point<Integer> animalLokasi = new Point<>(Integer.parseInt(animal.getStatus(2)), Integer.parseInt(animal.getStatus(3)));
                if (player.getLokasi().getY() - 1 >= 0 && player.getLokasi().getX().equals(animalLokasi.getX()) && (player.getLokasi().getY()-1) == (animalLokasi.getY()) ) {
                    updateStatus(animal.getStatus(1));
                }
                if (player.getLokasi().getY() + 1 < cell.getnBrs() && player.getLokasi().getX().equals(animalLokasi.getX()) && (player.getLokasi().getY()+1) == (animalLokasi.getY())) {
                    updateStatus(animal.getStatus(1));
                }
                if (player.getLokasi().getX() + 1 < cell.getnKol() && (player.getLokasi().getX()+1) == (animalLokasi.getX()) && player.getLokasi().getY().equals(animalLokasi.getY())) {
                    updateStatus(animal.getStatus(1));
                }
                if (player.getLokasi().getY() - 1 >= 0 && (player.getLokasi().getX()-1) == (animalLokasi.getX()) && player.getLokasi().getY().equals(animalLokasi.getY())) {
                    updateStatus(animal.getStatus(1));
                }
                idx += 1;
            }
        }
        if (wantToMix) {
            if (cmd.equals("1")) {
                GeprekSusu gs = new GeprekSusu();
                LinkedList<Product> recipesLinked = new LinkedList<>(gs.getRecipes());
                if (player.getTas().containsAll(gs.getRecipes())) {
                    player.setTas(true, gs);
                    for (Product prod : recipesLinked) {
                        player.setTas(false, prod);
                    }
                }
                wantToMix = false;
                updateStatus();
            }
            else if (cmd.equals("2")) {
                PaketSate gs = new PaketSate();
                LinkedList<Product> recipesLinked = new LinkedList<>(gs.getRecipes());
                if (player.getTas().containsAll(gs.getRecipes())) {
                    player.setTas(true, gs);
                    for (Product prod : recipesLinked) {
                        player.setTas(false, prod);
                    }
                }
                wantToMix = false;
                updateStatus();
            }
            else if (cmd.equals("3")) {
                PaketSteak gs = new PaketSteak();
                LinkedList<Product> recipesLinked = new LinkedList<>(gs.getRecipes());
                if (player.getTas().containsAll(gs.getRecipes())) {
                    player.setTas(true, gs);
                    for (Product prod : recipesLinked) {
                        player.setTas(false, prod);
                    }
                }
                wantToMix = false;
                updateStatus();
            }
        }
    }

    private void insertAnimal(Renderable o) {
        Point<Integer> animalLokasi = new Point<>(Integer.parseInt(o.getStatus(2)), Integer.parseInt(o.getStatus(3)));
        if (cell.getMap(animalLokasi).getClass().getSuperclass().getName().equals("facility.Facility")) {
        }
        else if (animalLokasi.compareTo(player.getLokasi()) == 0) {
        }
        else {
            if ((o.getClass().getName().equals("animal.Ayam") || o.getClass().getName().equals("animal.Bebek")) && (cell.getMap(animalLokasi).getClass().getName().equals("land.Coop"))) {
                farmAnimal.add(o);
            }
            else if ((o.getClass().getName().equals("animal.Kambing") || o.getClass().getName().equals("animal.Sapi") || o.getClass().getName().equals("animal.Domba")) && cell.getMap(animalLokasi).getClass().getName().equals("land.Barn")) {
                farmAnimal.add(o);
            }
            else if ((o.getClass().getName().equals("animal.Kuda")) && cell.getMap(animalLokasi).getClass().getName().equals("land.Grassland")) {
                farmAnimal.add(o);
            }
        }
    }

    private void updateStatus() {
        gameStatus.setText(player.status().getValue());
    }

    private void updateStatus(String str) {
        gameStatus.setText(player.status().getValue());
        gameStatus.appendText("\n");
        gameStatus.appendText(str);
    }

    private void showMenu() {
        String menu = "MENU: \n";
        menu += "1. Geprek Susu\n ";
        menu += new GeprekSusu().listResep();
        menu += "2. Paket Sate\n ";
        menu += new PaketSate().listResep();
        menu += "3. Paket Steak\n ";
        menu += new PaketSteak().listResep();
        updateStatus(menu);
    }
}
