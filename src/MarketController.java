import Classes.Plant;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


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
    //sell
    @FXML
    private ListView<Plant> sellList;
    @FXML
    private Button sellButton;
    @FXML
    private TextField sellQuantity;



    public Interface interfaceController;
    public Scene interfaceScene;


    public void initialize() {
        closeW();
        purchasePlantList();
        sellButton.setOnAction(_ -> {
            String text = sellQuantity.getText();
            int quantity = Integer.parseInt(text);
            Plant sellPlantSelected = sellList.getSelectionModel().getSelectedItem();
            if(sellPlantSelected != null && sellPlantSelected.seedQuantity >= quantity) {
                sellPlantSelected.seedQuantity -= quantity;
                interfaceController.availableFunds += (quantity * sellPlantSelected.price);
                sellList.refresh();
                updateView();
            }
        });
        sellList.setCellFactory(_ -> new ListCell<>() {

            protected void updateItem(Plant plant, boolean empty) {
                super.updateItem(plant, empty);

                if (empty || plant == null) {
                    setText(null);
                } else {
                    String str = " x" + plant.seedQuantity + " " + plant.name;
                    setText(str);
                }
            }
        });
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
                sellList.refresh();
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

        sellList.setItems(controller.availablePlantList.getItems());

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
    public void sellInventory() {

    }
}
