package Base;

public class Book {
    // Variables for the book
    private String name;
    private double cost;
    
    // Constructor
    public Book(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
    
    // Return the name 
    public String getName() {
        return name;
    }
    
    // Return the cost
    public double getCost() {
        return cost;
    }
}
