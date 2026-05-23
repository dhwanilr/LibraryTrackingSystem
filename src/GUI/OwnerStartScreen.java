package GUI;

import java.awt.GridLayout;
import javax.swing.*;


public class OwnerStartScreen extends JPanel {
    
    public OwnerStartScreen(MainFrame frame) {
        
        // Sets grid layout to fit 3 buttons
        setLayout(new GridLayout(3, 0, 10, 10));
           
        // Creates required buttons 
        JButton books = new JButton("Books");
        JButton customers = new JButton("Customers");
        JButton logout = new JButton("Logout");
        
        // Adds them to the panel 
        add(books);
        add(customers);
        add(logout);
        
        // Adds an action listener on each one and switches screens
        // accordingly 
        logout.addActionListener(e -> frame.showScreen("login"));
        books.addActionListener(e -> frame.showScreen("ownerbooks"));
        customers.addActionListener(e -> frame.showScreen("ownercustomer"));
    }
}
