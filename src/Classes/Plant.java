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
    public double getGrowTime() { return growTime; }
    @Override
    public String toString() {
        return name + " | price: " + price + " | grow time: " + growTime;
    }
    public String infoPlantInventory() {
        return "x" + seedQuantity + " " + name;
    }
    public void incPlantQuantityInInventory() {
        seedQuantity += 1;
    }
    public void decPlantQuantityInInventory() {
        seedQuantity -= 1;
    }

}
