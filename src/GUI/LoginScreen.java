package GUI;

import Base.Owner;
import Base.Customer;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel {
    
    // Text field for username
    JTextField usernameField;
    // Redacted field for password (hidden input)
    JPasswordField passwordField;

    public LoginScreen(MainFrame frame) {
        
        // Set a grid layout for proper display
        setLayout(new GridLayout(5, 0, 10, 10));
        
        // Creates the fields
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        
        // Adds labels to the fields and add them to the layout
        add(new JLabel("Username"));
        add(usernameField);
        add(new JLabel("Password"));
        add(passwordField);
        add(loginButton);
        
        // Add an action listner to handle clicks 
        loginButton.addActionListener(e -> {
            
            // Get the inputs from the user
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            // Get singleton instance for customer management
            Owner owner = Owner.getInstance();
            
            // Check if the input match the owners credentials
            if(username.equals(owner.getUsername()) &&
               password.equals(owner.getPassword())) {
                
                // Shows the owners start screen 
                frame.showScreen("ownerStart");
                return;
            }
            
            // Checks all customers for matches
            for(Customer c : owner.getCustomers()) {
                if(c.getUsername().equals(username) &&
                   c.getPassword().equals(password)) {
                    
                    // Shows start screen for that particular customer
                    frame.showCustomerStartScreen(c);
                    return;
                }
            }
            
            // Error message dialog if nothing matches
            JOptionPane.showMessageDialog(this,"Invalid login");

        });
    }
}
