import Classes.Plant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;


public class MarketController {
    @FXML
    private Button closeWBtn;
    //market List
    @FXML
    private ListView<Plant> purchasablePlantList;
    @FXML
    private Button buyButton;
    @FXML
    private Label visualAvailableFunds;

    public Interface interfaceController;
    public Scene interfaceScene;



    public MarketController() {}
    public void initialize() {
        closeW();
        purchasePlantList();
    }
    public void purchasePlantList() {
        //Seed List
        purchasablePlantList.getItems().addAll(Interface.shopPlant);
        //pay seed if enought money
        buyButton.setOnAction(_ -> {
            Plant selectedPlant = purchasablePlantList.getSelectionModel().getSelectedItem();
            //if a seed is selected
            if(selectedPlant != null) {
                interfaceController.paySeed(selectedPlant);
                updateView();
            }
        });
        //cell def
        purchasablePlantList.setCellFactory(_ -> new ListCell<>() {

            protected void updateItem(Plant plant, boolean empty) {
                super.updateItem(plant, empty);

                if (empty || plant == null) {
                    setText(null);
                } else {
                    setText(plant.name+ " | price: " + plant.price + " | grow time: " + plant.growTime);
                }
            }
        });
    }
    public void setInterfaceController(Interface controller) {
        this.interfaceController = controller;
        this.interfaceScene = controller.openMarketW.getScene();
        updateView();
    }

    public void closeW() {
        closeWBtn.setOnAction(_ -> {
            Stage stage = (Stage) closeWBtn.getScene().getWindow();
            stage.setScene(interfaceScene);
            stage.setTitle("Farm");
            stage.setFullScreen(true);
        });
    }
    public void updateView() {
        //money
        visualAvailableFunds.setText(String.valueOf(interfaceController.availableFunds));
    }
}
