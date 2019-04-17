import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML
    private Button startButton;

    @FXML
    void startGame(MouseEvent event) throws IOException {
        //Redirect to MainDIsplay
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainDisplay.fxml"));
        AnchorPane mainLayout = loader.load();
        Scene mainScene = new Scene(mainLayout);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
    }
}