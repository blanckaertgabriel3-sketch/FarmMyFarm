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
    public void incPlantQuantityInInventory() {
        seedQuantity += 1;
    }
    public void getSeedToPlant(Plant inventorySelection) {
        if (inventorySelection != null) {
            System.out.println("Selected seed: " + inventorySelection.getName() + " " + inventorySelection.getSeedQuantity());
        }
    }

}
