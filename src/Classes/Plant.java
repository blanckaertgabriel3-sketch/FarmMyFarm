package Classes;

public class Plant {
    public String name;
    public double price;
    public double growTime;
    public int seedQuantity = 0;
    public Plant(String name, double price, double growTime) {
        this.name = name;
        this.price = price;
        this.growTime = growTime;
    }
    public void incPlantQuantityInInventory() {seedQuantity += 1;}
}
