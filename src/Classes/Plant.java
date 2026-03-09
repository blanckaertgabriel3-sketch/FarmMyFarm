package Classes;

public class Plant {
    public String name;
    public double price;
    public double sellPrice;
    public double growTime;
    public int seedQuantity = 0;
    public Plant(String name, double price,double sellPrice, double growTime) {
        this.name = name;
        this.price = price;
        this.sellPrice = sellPrice;
        this.growTime = growTime;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getSeedQuantity() { return seedQuantity; }
    public void incPlantQuantityInInventory() {seedQuantity += 1;}
}
