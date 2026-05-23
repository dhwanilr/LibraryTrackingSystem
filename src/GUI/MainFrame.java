package GUI;

import Base.Customer;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    // Cardlayout to be able to switch between screens
    CardLayout cardLayout;
    // Main container that will hold all the screens
    JPanel container;
    
    // Keeping a reference of this screen because I needed 
    // to refresh it 
    private OwnerBooksScreen ownerBooksScreen;
    private OwnerCustomerScreen ownerCustomerScreen;

    // Constructor to set everything up
    public MainFrame() {
        
        // Opens new cardlayout and container using JPanel
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        
        // Adds all the screens to the container
        container.add(new LoginScreen(this), "login");
        container.add(new OwnerStartScreen(this), "ownerStart");
        
        ownerBooksScreen = new OwnerBooksScreen(this);
        ownerCustomerScreen = new OwnerCustomerScreen(this);
        container.add(ownerBooksScreen, "ownerbooks");
        container.add(ownerCustomerScreen, "ownercustomer");
        
        // Adds the container to the main frame
        add(container);
        
        // Default frame settings
        setTitle("Book Store");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // Method to switch screens by name
    public void showScreen(String name) {
        
        // This specific screen needs to be refreshed 
        if(name.equals("ownerbooks")) {
            ownerBooksScreen.refresh();
        }
        
        if(name.equals("ownercustomer")) {
            ownerCustomerScreen.refresh();
        }
        
        // Shows the screen that needs to shown
        cardLayout.show(container, name);
    }
    
    // Shows the start screen for a specific customer
    public void showCustomerStartScreen(Customer customer) {
        // Create a new screen for this customer 
        CustomerStartScreen screen = new CustomerStartScreen(this, customer);
        // Add the screen to the container and show it 
        container.add(screen, "customerStart");
        cardLayout.show(container, "customerStart");
    }
    
    // Show purchase screen for a specific customer
    public void showCustomerBuyScreen(Customer customer, double total) {
        // Create a new buy screen for this customer
        CustomerBuyScreen costScreen = new CustomerBuyScreen(this, customer, total);
        // Add the screen to the container and show it 
        container.add(costScreen, "customerBuy");
        cardLayout.show(container, "customerBuy");
}
}