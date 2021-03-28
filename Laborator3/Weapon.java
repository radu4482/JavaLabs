public class Weapon implements Item {
    private WeaponType type;
    private double weight;
    private double value;

    @Override
    public String getClassName() {
        return "Weapon";
    }

    public Weapon(WeaponType type,double weight,double value){
        this.type=type;
        this.weight=weight;
        this.value=value;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
    @Override
    public String getName() {
        return this.type.name();
    }

    @Override
    public double getValue() {
        return this.value;
    }

}