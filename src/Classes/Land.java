package Classes;

import javafx.scene.control.Button;
import javafx.animation.AnimationTimer;
import javafx.scene.control.ListView;

public class Land {
    public Button btn;
    public double btnSize = 100;
    //plant animation
    public String soil = "#8B5A2B";
    public String growingState1;
    public String growingState2;
    public String ready;
    public String plantItem;
    //animation
    private long timerStart = -1;
    final double animationDuration = 4;
    //selection
    public ListView<Plant> plantList;
    public Plant inventorySelection;


    public Land(ListView<Plant> plantList) {
        this.plantList = plantList;
        initializeBtn();
        getSelectedSeedInventory(plantList);
    }
    public void initializeBtn() {
        btn = new Button();
        btn.setStyle("-fx-background-color: " + soil + "; -fx-border-radius: 0; -fx-background-radius: 0;");
        btn.setMinWidth(btnSize);
        btn.setMinHeight(btnSize);
        btnAction();
    }
    public void btnAction() {
        btn.setOnAction(_ -> animatePlant());
    }
    public Button getBtn() {return btn;}
    public void animatePlant() {
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {
                if(timerStart < 0) timerStart = now;
                double lapSeedSeconds = (now - timerStart) / 1_000_000_000.0;
                if (lapSeedSeconds < animationDuration/2) {
                    btn.setStyle(
                            "-fx-background-image: url('" + growingState1 + "');" +
                                    "-fx-background-repeat: no-repeat;" +
                                    "-fx-background-size: 100% 100%;" +
                                    "-fx-background-position: center;" +
                                    "-fx-border-radius: 0;" +
                                    "-fx-background-radius: 0;"
                    );
                }
                else if (lapSeedSeconds < animationDuration){
                    btn.setStyle(
                            "-fx-background-image: url('" + growingState2 + "');" +
                                    "-fx-background-repeat: no-repeat;" +
                                    "-fx-background-size: 100% 100%;" +
                                    "-fx-background-position: center;" +
                                    "-fx-border-radius: 0;" +
                                    "-fx-background-radius: 0;"
                    );
                }
                else {
                    btn.setStyle(
                            "-fx-background-image: url('" + ready + "');" +
                                    "-fx-background-repeat: no-repeat;" +
                                    "-fx-background-size: 100% 100%;" +
                                    "-fx-background-position: center;" +
                                    "-fx-border-radius: 0;" +
                                    "-fx-background-radius: 0;"
                    );
                    System.out.println("plant is ready");
                    this.stop();
                }
            }
        };
        timer.start();
    }
    public void getSelectedSeedInventory(ListView<Plant> plantList) {
        plantList.getSelectionModel().selectedItemProperty().addListener((_, _, inventorySelection) -> {
            if (inventorySelection != null) {
                stockSelectedSeed(inventorySelection);
                urlPlant(inventorySelection);
            }
        });
    }
    public void stockSelectedSeed(Plant inventorySelection) {
        this.inventorySelection = inventorySelection;
    }
    public void urlPlant(Plant inventorySelection) {
        this.growingState1 = "/img/frutsState/" + inventorySelection.getName() + "/state1.png";
        this.growingState2 = "/img/frutsState/" + inventorySelection.getName() + "/state2.png";
        this.ready = "/img/frutsState/" + inventorySelection.getName() + "/state3.png";
        this.plantItem = "/img/frutsState/" + inventorySelection.getName() + "/item.png";
    }

}
