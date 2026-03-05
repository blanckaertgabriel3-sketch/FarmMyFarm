import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Interface {
    //market
    @FXML
    private ListView<Plant> purchasablePlantList;
    @FXML
    private Button buyButton;
    //field Group
    @FXML
    private Pane thePane;
    @FXML
    private ImageView thePictureView;
    @FXML
    private ProgressBar theProgressBar;
    //inventory
    @FXML
    private ListView<Plant> availablePlantList;
    @FXML
    private TextField availableFunds;
    


    public void initialize() {
        //load the first field State.
        Image image = new Image(getClass().getResourceAsStream("/img/frutsState/dirt.png"));
        thePictureView.setImage(image);

        //put all food elements into the list
        purchasablePlantList.getItems().addAll(
                new Plant("wheat", 10, 2),
                new Plant("strawberry", 20, 3),
                new Plant("carrot", 35, 3.5)
        );

        //getSelected purchasablePlantList and add it to availablePlantList
        buyButton.setOnAction(event -> {
            Plant selectedPlant = purchasablePlantList.getSelectionModel().getSelectedItem();
            availablePlantList.getItems().add(selectedPlant);
        });
        //drag and drop plant int field
        //drag plant in availablePlantList    drop in thePane

        


    }
}
