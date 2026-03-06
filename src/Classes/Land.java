package Classes;

import javafx.scene.control.Button;

public class Land {
    public Button btn;

    public Land() {
        btn = new Button("ici");
        btnAction();
    }
    public void btnAction() {
        btn.setOnAction(event -> {
            System.out.println("click");
        });
    }
    public Button getBtn() {return btn;}
}
