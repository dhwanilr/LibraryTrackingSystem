package Base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Owner extends Person {

    // Arraylists to manage books and customers 
    private List<Customer> customers = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private static Owner instance;
    
    // Constructor
    private Owner() {
        super("admin", "admin");
    }
    
    // Getters for username and password
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // Customer management methods
    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public void removeCustomer(Customer c) {
        customers.remove(c);
    }
    
    // Returns unmodifiable list so rep isn't exposed
    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }
    
    // Book management methods 
    public void addBook(Book b) {
        books.add(b);
    }

    public void removeBook(Book b) {
        books.remove(b);
    }
    
    public void setBooks(List<Book> bookList) {
    books.clear();
    books.addAll(bookList);
    }

    public List<Book> getBooks() {
        return books;
    }
    
    // Singleton so multiple owners can't be created
    // throughout program 
    public static Owner getInstance() {
        if (instance == null) {
            instance = new Owner();
        }
        return instance;
    }
}