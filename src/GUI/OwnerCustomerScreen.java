package GUI;

import Base.Customer;
import Base.Owner;
import Util.FileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OwnerCustomerScreen extends JPanel {

    // Necessary references to frame and table for customer management
    private MainFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    // Text fields for username and password of new customer
    private JTextField usernameField;
    private JTextField passwordField;

    // Singleton instance of owner
    private Owner owner = Owner.getInstance();

    public OwnerCustomerScreen(MainFrame frame) {

        this.frame = frame;
        setLayout(new BorderLayout());

        // Load existing customers from file
        FileManager.loadCustomers();

        // ----- TOP PART: TABLE -----
        String[] columnNames = {"Customer Username", "Customer Password", "Points"};

        // Model the table with column names and 0 initial rows
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };

        // Create table and wrap in scroll pane for scrolling
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadTable(); // Load existing customers into table

        // ----- MIDDLE PART: ADD CUSTOMER PANEL -----
        JPanel middlePanel = new JPanel();

        // Fields to enter new customer's username and password
        usernameField = new JTextField(10);
        passwordField = new JTextField(10);

        JButton addButton = new JButton("Add");

        // Add labels and fields to panel
        middlePanel.add(new JLabel("Username:"));
        middlePanel.add(usernameField);
        middlePanel.add(new JLabel("Password:"));
        middlePanel.add(passwordField);
        middlePanel.add(addButton);

        // Add the middle panel to the top of the screen
        add(middlePanel, BorderLayout.NORTH);

        // Add customer when button clicked
        addButton.addActionListener(e -> addCustomer());

        // ----- BOTTOM PART: DELETE + BACK PANEL -----
        JPanel bottomPanel = new JPanel();

        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");

        bottomPanel.add(deleteButton);
        bottomPanel.add(backButton);

        // Add bottom panel to the bottom of the screen
        add(bottomPanel, BorderLayout.SOUTH);

        // Delete selected customer
        deleteButton.addActionListener(e -> deleteCustomer());

        // Go back to owner's main screen
        backButton.addActionListener(e -> frame.showScreen("ownerStart"));
    }

    // Load all customers into the table
    private void loadTable() {
        tableModel.setRowCount(0); // Clear existing rows

        List<Customer> customers = owner.getCustomers();

        for (Customer c : customers) {
            tableModel.addRow(new Object[]{c.getUsername(), c.getPassword(), c.getPoints()});
        }
    }

    // Add a new customer from input fields
    private void addCustomer() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password");
            return;
        }

        // Add customer with 0 initial points
        owner.addCustomer(new Customer(username, password, 0));

        // Save updated customers to file
        FileManager.saveCustomers();

        loadTable(); // Refresh table

        // Clear input fields
        usernameField.setText("");
        passwordField.setText("");
    }

    // Delete the selected customer
    private void deleteCustomer() {
        int selectedRow = table.getSelectedRow();

        // Check if a row is selected
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete");
            return;
        }

        // Get the username of the selected customer
        String username = (String) tableModel.getValueAt(selectedRow, 0);

        // Remove customer from owner's list
        owner.getCustomers().stream()
                .filter(c -> c.getUsername().equals(username))
                .findFirst()
                .ifPresent(owner::removeCustomer);

        // Save updated list and refresh table
        FileManager.saveCustomers();
        loadTable();
    }
    
    public void refresh() {
        loadTable();
    }
}