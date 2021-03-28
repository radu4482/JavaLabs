public class Book implements Item {
    private String name;
    private int pageNumber; //→ getWeight
    private double value;

    public Book(String name,int pageNumber,double value){
        this.name=name;
        this.pageNumber=pageNumber;
        this.value=value;
    }

    @Override
    public String getClassName() {
        return "Book";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public double getWeight() {
        return this.pageNumber/100;
    }

//...
}
