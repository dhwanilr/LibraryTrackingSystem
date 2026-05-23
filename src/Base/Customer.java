package Base;

public class Customer extends Person {
    // Special variable for customer
    private int points;
    
    // Constructor
    public Customer(String username, String password, int points) {
        super(username, password);
        this.points = points;
    }
    
    @Override
    // Getters for values 
    public String getUsername() { return username; }
    @Override
    public String getPassword() { return password; }
    
    // Adds points to customer 
    public void addPoints(int p) {
        points += p;
    }
    
    // Returns the points 
    public int getPoints() {
        return points;
    }
    
    // Sets points
    public void setPoints(int p) {
        points = p;
    }
    
    // Returns status based on points
    public String getStatus() {
        if (points < 1000) {
            return "Silver";
        }
        return "Gold";
    }
}
