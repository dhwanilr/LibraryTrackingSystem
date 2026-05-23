package Util;

import Base.Book;
import Base.Customer;
import Base.Owner;

import java.io.*;
import java.util.*;

public class FileManager {
    
    // Name of the necessary files 
    private static final String BOOK_FILE = "books.txt";
    private static final String CUSTOMER_FILE = "customers.txt";
    
    // Method of loading books from file
    public static List<Book> loadBooks() {
        // Arraylist for books
        List<Book> books = new ArrayList<>();

        try {
            // Uses memory buffer and filereader to load books in
            BufferedReader br = new BufferedReader(new FileReader(BOOK_FILE));
            String line;
            
            // Goes until EOF
            while ((line = br.readLine()) != null) {
                // Splits the line along the comma 
                String[] parts = line.split(",");
                
                // name is the first part, price is the second part 
                String title = parts[0];
                double price = Double.parseDouble(parts[1]);
                
                // adds it to ArrayList
                books.add(new Book(title, price));
            }
            
            // Closes the file 
            br.close();
            
        } catch (IOException e) { //Return exception in case of error
            System.out.println("Error loading books.");
        }
        
        // return the list of books 
        return books;
    }
    
    // Saves the books to the specified file given an ArrayList
    public static void saveBooks(List<Book> books) {
        
        try {
            // Opens filewriter with a wrapper of buffered writer 
            BufferedWriter bw = new BufferedWriter(new FileWriter(BOOK_FILE));
            
            // Writes each book name with name,cost
            for (Book b : books) {
                bw.write(b.getName() + "," + b.getCost());
                bw.newLine();
            }

            bw.close();

        } catch (IOException e) { // Exception for errors 
            System.out.println("Error saving books.");
        }
    }
    
    // Loads the customers from the specified file
    public static void loadCustomers() {

        Owner owner = Owner.getInstance();

        try {
            // Loads filereader with a memory buffer for better efficiency 
            BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE));
            String line;
            
            // Continues reading until EOF
            while ((line = br.readLine()) != null) {
                
                // Same split as the books
                String[] parts = line.split(",");
                
                // username,password,points
                String username = parts[0];
                String password = parts[1];
                int points = Integer.parseInt(parts[2]);
                
                // Creates a new customer and adds it to ArrayList through owner 
                Customer c = new Customer(username, password, points);

                owner.addCustomer(c);
            }

            br.close();

        } catch (IOException e) { // Exception in case of file problems 
            System.out.println("Error loading customers.");
        }
    }

    public static void saveCustomers() {

        Owner owner = Owner.getInstance();

        try {
            // Opens filewriter with a wrapper of buffered writer 
            BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMER_FILE));
            
            // For all customers in arraylist, it writes them to the file then
            // inserts a new line 
            for (Customer c : owner.getCustomers()) {

                bw.write(c.getUsername() + "," + c.getPassword() + "," + c.getPoints());
                bw.newLine();
            }

            bw.close();

        } catch (IOException e) { // Exception in case of file problems
            System.out.println("Error saving customers.");
        }
    }
}