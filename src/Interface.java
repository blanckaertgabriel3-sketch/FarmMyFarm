import Classes.Land;
import Classes.Plant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.io.IOException;

public class Interface {
    //market
    @FXML
    public Button openMarketW;
    //land Farm
    @FXML
    private GridPane landFarm;
    //inventory
    @FXML
    private ListView<Plant> availablePlantList;
    @FXML
    private Label visualAvailableFunds;

    public static final List<Plant> shopPlant = List.of(
            new Plant("wheat", 10, 20, 2),
            new Plant("carrot", 15, 30, 3)/*,
                new Plant("potato", 20, 40, 4),
                new Plant("tomato", 25, 50, 5),
                new Plant("chili", 30, 60, 6),
                new Plant("strawberry", 35, 70, 7),
                new Plant("grape", 40, 80, 8),
                new Plant("lemon", 45, 90, 9),
                new Plant("banana", 50, 100, 10),
                new Plant("peach", 55, 110, 11),
                new Plant("mushroom", 60, 120, 12),
                new Plant("red_flower", 65, 130, 13),
                new Plant("sugarcane", 70, 140, 14),
                new Plant("pineapple", 80, 160, 15),
                new Plant("watermelon", 90, 180, 16)
                */
    );
    public double availableFunds = 200;



    public void initialize() {
        updateView();
        openMarketW();
        cellDef();
        initializeField();

    }
    public void openMarketW() {
        openMarketW.setOnAction(_ -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Market.fxml"));
                Parent root = loader.load();
                MarketController marketController = loader.getController();
                marketController.setInterfaceController(this);

                Stage stage = (Stage) openMarketW.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Market");
                stage.setFullScreen(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void cellDef() {
        availablePlantList.setCellFactory(_ -> new ListCell<>() {

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
    //MARKET
    public boolean canPay(double reference, double itemPrice) {
        return reference >= itemPrice;
    }
    public void paySeed(Plant selectedPlant) {
        if(canPay(availableFunds, selectedPlant.price)) {
            availableFunds -= selectedPlant.price;
            updateView();
            addSeedInventory(selectedPlant);

        }
    }
    public void addSeedInventory(Plant selectedPlant) {
        selectedPlant.incPlantQuantityInInventory();
        if(!availablePlantList.getItems().contains(selectedPlant)) {
            availablePlantList.getItems().add(selectedPlant);
        }
        availablePlantList.refresh();
    }
    public void updateView() {
        //money
        visualAvailableFunds.setText(String.valueOf(availableFunds));
    }
    public void initializeField() {
        int rows = 5;
        int columns = 5;
        double spaceBetweenBnt = 2;
        landFarm.setHgap(spaceBetweenBnt);
        landFarm.setVgap(spaceBetweenBnt);

        // Land[][] lands = new Land[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Land land = new Land(availablePlantList);
                /*
                landFarm.setHalignment(land.getBtn(), javafx.geometry.HPos.CENTER);
                landFarm.setValignment(land.getBtn(), javafx.geometry.VPos.CENTER);

                 */
                landFarm.setAlignment(Pos.TOP_CENTER);

                landFarm.add(land.getBtn(), col, row);
            }
        }
    }
}
