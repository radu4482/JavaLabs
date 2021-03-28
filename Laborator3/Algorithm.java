public class Algorithm {
    public static void main(String[] args)
    {
        Knapsack knapsack= new Knapsack(10);
        Book book1= new Book("Dragon Rising",3,5 );
        Book book2= new Book("A Blade in the Dark",3,5 );
        Food food1= new Food ("Cabbage",2);
        Food food2= new Food("Rabbit",2);
        Weapon weapon= new Weapon(WeaponType.Sword,5,10);

        knapsack.addItem(weapon);
        knapsack.addItem(book1);
        knapsack.addItem(food1);

        knapsack.toString();

    }
}
