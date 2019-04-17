import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import player.Player;

import java.io.IOException;

public class MainApp extends Application {
    public Stage window;
    public Scene startScene;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setMaxHeight(435);
        window.setMaxWidth(610);
        window.setMinWidth(610);
        window.setMinHeight(435);
        //Memberikan judul pada stage
        primaryStage.setTitle("Engi's Farm");

        //Memuat FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("startDisplay.fxml"));
        AnchorPane startLayout = loader.load();

        //Membuat scene Start ke dalam stage
        startScene = new Scene(startLayout);
        window.setScene(startScene);

        //Menampilkan stage ke layar
        window.show();
    }
}
