public interface Item {
    String getName();
    String getClassName();
    double getValue();
    double getWeight();

    default double profitFactor() {
        return getValue() / getWeight();
    }

    default String ToString() {
        return  this.getClassName() +"{name=" + this.getName() + ", weight = " + this.getWeight() + ", value=" + this.getValue() + ", profit=" + this.profitFactor() + '}';
    }
}

