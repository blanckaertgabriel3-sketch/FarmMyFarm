package Classes;

import javafx.scene.control.Button;
import javafx.animation.AnimationTimer;

public class Land {
    public Button btn;
    public double btnSize = 100;
    public String soil = "#8B5A2B";
    public String growing = "#3CB043";
    public String ready = "#FFD700";

    final double animationDuration = 2;
    private long timerStart = -1;

    public Land() {
        btn = new Button("ici");
        btn.setStyle("-fx-background-color: " + soil + "; -fx-border-radius: 0; -fx-background-radius: 0;");
        btn.setMinWidth(btnSize);
        btn.setMinHeight(btnSize);
        btnAction();
    }
    public void btnAction() {
        btn.setOnAction(_ -> plantAnimate());
    }
    public Button getBtn() {return btn;}
    public void plantAnimate() {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(timerStart < 0) timerStart = now;
                double lapSeedSeconds = (now - timerStart) / 1_000_000_000.0;
                if (lapSeedSeconds < animationDuration) {
                    btn.setStyle("-fx-background-color: " + growing + "; -fx-border-radius: 0; -fx-background-radius: 0;");
                }
                else {
                    btn.setStyle("-fx-background-color: " + ready + "; -fx-border-radius: 0; -fx-background-radius: 0;");
                    System.out.println("plant is ready");
                    this.stop();
                }
            }
        };
        timer.start();
    }
}
