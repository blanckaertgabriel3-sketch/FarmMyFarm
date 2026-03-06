package Classes;
import javafx.scene.layout.Pane;

public class LandFarm extends Pane{
    public int rows = 10;
    public int columns = 10;

    public LandFarm() {
    Land land = new Land();
    getChildren().add(land.getBtn());

    }
}
