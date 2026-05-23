package GUI;

import Base.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerBuyScreen extends JPanel {

    public CustomerBuyScreen(MainFrame frame, Customer customer, double totalCost) {

        // Use a grid layout with 3 rows, 1 column for cost, points, and button
        setLayout(new GridLayout(3,1));

        // Label showing the total cost of the purchase
        JLabel costLabel = new JLabel("Total Cost: " + totalCost);

        // Label showing the customer's current points and status after purchase
        JLabel pointsLabel = new JLabel(
                "Points: " + customer.getPoints() +
                ", Status: " + customer.getStatus()
        );

        // Button to log out and return to login screen
        JButton logoutBtn = new JButton("Logout");

        // Add components to the panel in order
        add(costLabel);
        add(pointsLabel);
        add(logoutBtn);

        // Logout button action returns user to login screen
        logoutBtn.addActionListener(e -> frame.showScreen("login"));
    }
}