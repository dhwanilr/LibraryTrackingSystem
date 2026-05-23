package Base;

// Abstract class for implementation of owner and customer
public abstract class Person {
    // Variables for people 
    protected String username;
    protected String password;
    
    // Constructor
    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Abstract methods 
    public abstract String getUsername();
    public abstract String getPassword();
}
