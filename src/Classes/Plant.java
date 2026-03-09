package Classes;

public class Plant {
    public String name;
    public double price;
    public double sellPrice;
    public double growTime;
    public int seedQuantity = 0;
    public String growingState1 = "/img/frutsState/wheat/state1.png";
    public String growingState2 = "/img/frutsState/wheat/state2.png";
    public String ready = "/img/frutsState/wheat/state3.png";
    public String plantItem = "/img/frutsState/wheat/item.png";
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
    public String getGrowingState1() { return growingState1; }
    public String getGrowingState2() { return growingState2; }
    public String getReady() { return ready; }
    public String getPlantItem() { return plantItem; }
}
