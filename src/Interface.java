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
    private ListView<String> availablePlantList;
    @FXML
    private TextField availableFunds;
    


    public void initialize() {
        //load the first field State.
        Image image = new Image(getClass().getResourceAsStream("/img/frutsState/dirt.png"));
        thePictureView.setImage(image);

        //put all food elements into the list
        purchasablePlantList.getItems().addAll(
                new Plant("wheat", 10, 20, 2),
                new Plant("carrot", 20, 50, 3),
                new Plant("strawberry", 35, 80, 4.5)
        );

        //getSelected purchasablePlantList and add it to availablePlantList
        buyButton.setOnAction(event -> {
            Plant selectedPlant = purchasablePlantList.getSelectionModel().getSelectedItem();
            if(selectedPlant != null) {
                selectedPlant.incPlantQuantityInInventory();
                boolean found = false;
                //plant is in inventory? add quantity
                for(int i=0 ; i<availablePlantList.getItems().size() ; i++) {
                    String item = availablePlantList.getItems().get(i);
                    if (item.contains(selectedPlant.getName())) {
                        availablePlantList.getItems().set(i, selectedPlant.infoPlantInventory());
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    availablePlantList.getItems().add(selectedPlant.infoPlantInventory());
                }

            }
        });
        //drag and drop plant int field
        //drag plant in availablePlantList    drop in thePane
        
        


    }
}
