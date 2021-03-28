import javax.xml.namespace.QName;
import java.util.*;

public class Knapsack {
    private double capacity;
    private List<Item> items = new ArrayList<>();
    //… constructors, getters, setters

    public Knapsack(double capacity) {
        this.capacity = capacity;
    }

    public double getEmptySpace() {
        double aux = 0;
        for (int i = 0; i < items.size(); i++)
            aux += items.get(i).getWeight();
        return capacity - aux;
    }

    public void addItem(Item item) {
        if (getEmptySpace() < item.getWeight())
            System.out.println("Nu mai este loc!");
        else
            items.add(item);
    }


    public String toString()
    {
        for(int i=0;i<items.size();i++)
            System.out.println(items.get(i).ToString());

        return null;
    }
    //… toString, etc.
}
