public class Plant {
    public String name;
    public double price;
    public double growTime;
    public Plant(String name, double price, double growTime) {
        this.name = name;
        this.price = price;
        this.growTime = growTime;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getGrowTime() { return growTime; }
    @Override
    public String toString() {
        return name + " | price: " + price + " | grow time: " + growTime;
    }

}
