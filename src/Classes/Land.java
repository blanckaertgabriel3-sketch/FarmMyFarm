package Classes;

import javafx.scene.control.Button;
import javafx.animation.AnimationTimer;
import javafx.scene.control.ListView;

public class Land {
    public Button btn;
    public double btnSize = 75;
    //plant animation
    public String soil = "rgba(139,90,43,0.25)";
    public String growingState1;
    public String growingState2;
    public String ready;
    public String plantItem;
    //animation
    public boolean isAnimate = false;
    public boolean isReady = false;
    public int landAction = 0;
    private long timerStart = -1;
    public double animationDuration;
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

    public void animatePlant() {
        if(growingState1 != null && growingState2!=null && ready!=null && plantItem!=null) {
            animate();
        }
    }
    public void animate() {
        if (landAction == 0 && inventorySelection.seedQuantity > 0) {
            timerStart = -1;
            isAnimate = true;
            inventorySelection.seedQuantity -= 1;
            plantList.refresh();
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
                        isReady = true;
                        btn.setStyle(
                                "-fx-background-image: url('" + ready + "');" +
                                        "-fx-background-repeat: no-repeat;" +
                                        "-fx-background-size: 100% 100%;" +
                                        "-fx-background-position: center;" +
                                        "-fx-border-radius: 0;" +
                                        "-fx-background-radius: 0;"
                        );
                        this.stop();

                    }
                }
            };
            timer.start();
            landAction +=1;
        }
        else if (landAction > 0 && isAnimate && isReady){
            btn.setStyle("-fx-background-color: " + soil + "; -fx-border-radius: 0; -fx-background-radius: 0;");
            inventorySelection.seedQuantity += 2;
            plantList.refresh();
            landAction = 0;
            isReady = false;
            isAnimate = false;
        }
    }
    public void getSelectedSeedInventory(ListView<Plant> plantList) {
        plantList.getSelectionModel().selectedItemProperty().addListener((_, _, inventorySelection) -> {
            if (inventorySelection != null && !isAnimate) {
                stockSelectedSeed(inventorySelection);
                urlPlant(inventorySelection);
                this.animationDuration = inventorySelection.growTime;
            }
        });
    }
    public void stockSelectedSeed(Plant inventorySelection) {
        this.inventorySelection = inventorySelection;
    }
    public void urlPlant(Plant inventorySelection) {
        this.growingState1 = "/img/frutsState/" + inventorySelection.name + "/state1.png";
        this.growingState2 = "/img/frutsState/" + inventorySelection.name + "/state2.png";
        this.ready = "/img/frutsState/" + inventorySelection.name + "/state3.png";
        this.plantItem = "/img/frutsState/" + inventorySelection.name + "/item.png";
    }

}
