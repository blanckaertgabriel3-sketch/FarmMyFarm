import Classes.Plant;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

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
    private Label visualAvailableFunds;

    public double availableFunds = 200;



    public void initialize() {
        updateView();
        //load the first field State.
        Image image = new Image(getClass().getResourceAsStream("/img/frutsState/dirt.png"));
        thePictureView.setImage(image);
        //food into market list
        purchasablePlantList.getItems().addAll(
                new Plant("wheat", 10, 20, 2),
                new Plant("carrot", 15, 30, 3),
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
        );
        //buy seed -> seed inventory
        //getSelected purchasablePlantList and add it to availablePlantList
        buyButton.setOnAction(event -> {
            Plant selectedPlant = purchasablePlantList.getSelectionModel().getSelectedItem();
            //if a seed is selected
            if(selectedPlant != null) {
                paySeed(selectedPlant);
            }
        });


        //drag and drop plant int field
        //drag plant in availablePlantList    drop in thePane
    }
    //MARKET
    public boolean canPay(double reference, double itemPrice) {
        return reference >= itemPrice;
    }
    public void paySeed(Plant selectedPlant) {
        if(canPay(availableFunds, selectedPlant.getPrice())) {
            availableFunds -= selectedPlant.getPrice();
            updateView();
            addSeedInventory(selectedPlant);
        }
    }
    public void addSeedInventory(Plant selectedPlant) {
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
    public void updateView() {
        //money
        visualAvailableFunds.setText(String.valueOf(availableFunds));
    }
    //DRAG-DROP
}
