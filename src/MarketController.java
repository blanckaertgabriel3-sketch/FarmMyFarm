import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MarketController {
    @FXML
    private Button closeWBtn;
    public void initialize() {
        closeWBtn.setOnAction(_ -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/interface.fxml"));
                Stage stage = (Stage) closeWBtn.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Market");
                stage.setFullScreen(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
