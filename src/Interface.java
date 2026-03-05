import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Pane;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Interface {
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

        //getSelected buyListElement
        buyButton.setOnAction(event -> {
            Plant selectedFood = purchasablePlantList.getSelectionModel().getSelectedItem();
            System.out.println(selectedFood.getName());
        });


    }
}
