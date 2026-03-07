package Classes;

import javafx.scene.control.Button;

public class Land {
    public Button btn;
    public double btnSize = 100;

    public Land() {
        btn = new Button("ici");
        btn.setStyle("-fx-background-color: green; -fx-border-radius: 0; -fx-background-radius: 0;");
        btn.setMinWidth(btnSize);
        btn.setMinHeight(btnSize);
        btnAction();
    }
    public void btnAction() {
        btn.setOnAction(_ -> {
            System.out.println("click");

        });
    }
    public Button getBtn() {return btn;}
}
