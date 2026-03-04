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
    private ListView<String> buyFoodList;
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
        //some food in an array
        ArrayList<String> food = new ArrayList<>();
        food.add("wheat");
        food.add("bananas");
        //print all food elements into the list
        buyFoodList.getItems().addAll(food);
        //getSelected buyListElement
        buyButton.setOnAction(event -> {
            String selectedFood = buyFoodList.getSelectionModel().getSelectedItem();
            System.out.println(selectedFood);
        });


    }
}
