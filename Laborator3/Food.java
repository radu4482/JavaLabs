public class Food implements Item {
    private String name;
    private double weight; // → getWeight, getValue

    public Food(String name, double weight)
    {
        this.name=name;
        this.weight=weight;
    }

    @Override
    public String getClassName() {
        return "Food";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getValue() {
        return 2*this.weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    //...
}