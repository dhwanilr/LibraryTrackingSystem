package GUI;

import Base.Book;
import Base.Customer;
import Base.Owner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.lang.Math;

public class CustomerStartScreen extends JPanel {

    // References to main frame and the logged-in customer
    private MainFrame frame;
    private Customer customer;

    // Table to show available books
    private JTable table;
    private DefaultTableModel tableModel;

    // Label to welcome the customer and show points/status
    private JLabel welcomeLabel;

    public CustomerStartScreen(MainFrame frame, Customer customer) {

        this.frame = frame;
        this.customer = customer;

        setLayout(new BorderLayout());

        // ----- TOP: Welcome message -----
        welcomeLabel = new JLabel();
        updateWelcomeMessage(); // Set initial welcome text
        add(welcomeLabel, BorderLayout.NORTH);

        // ----- CENTER: Books table -----
        String[] columns = {"Book Name", "Book Price", "Select"};

        // Table model with a Boolean column for selection
        tableModel = new DefaultTableModel(columns,0) {
            public Class<?> getColumnClass(int column) {
                if(column == 2) return Boolean.class; // Checkbox for selection
                return String.class; // Name and price columns as text
            }
        };

        table = new JTable(tableModel);

        loadBooks(); // Populate table with current books

        // Wrap table in scroll pane and add to center
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ----- BOTTOM: Action buttons -----
        JPanel bottom = new JPanel();

        JButton buyBtn = new JButton("Buy");
        JButton redeemBtn = new JButton("Redeem points and Buy");
        JButton logoutBtn = new JButton("Logout");

        bottom.add(buyBtn);
        bottom.add(redeemBtn);
        bottom.add(logoutBtn);

        add(bottom, BorderLayout.SOUTH);

        // Button actions
        buyBtn.addActionListener(e -> buy(false));      // Buy without redeeming points
        redeemBtn.addActionListener(e -> buy(true));   // Buy using points for discount
        logoutBtn.addActionListener(e -> frame.showScreen("login")); // Logout to login screen
    }

    // Update welcome message with current points and status
    private void updateWelcomeMessage() {
        welcomeLabel.setText(
                "Welcome " + customer.getUsername() +
                ". You have " + customer.getPoints() +
                " points. Your status is " + customer.getStatus()
        );
    }

    // Load all books into the table with a checkbox for selection
    private void loadBooks() {
        List<Book> books = Owner.getInstance().getBooks();

        for(Book b : books) {
            tableModel.addRow(new Object[]{
                    b.getName(),
                    b.getCost(),
                    false // default selection is false
            });
        }
    }

    // Handles the buying process
    private void buy(boolean redeem) {

        Owner owner = Owner.getInstance();
        double total = 0;

        // Loop through table rows to calculate total for selected books
        for(int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean selected = (Boolean) tableModel.getValueAt(i,2);

            if(selected != null && selected) {
                String title = tableModel.getValueAt(i,0).toString();
                double price = Double.parseDouble(tableModel.getValueAt(i,1).toString());

                total += price;

                // Remove purchased book from owner's list
                owner.getBooks().removeIf(b -> b.getName().equals(title));
            }
        }

        // Save updated book list to file
        Util.FileManager.saveBooks(owner.getBooks());

        // If redeeming points, apply discount and reset points
        if(redeem) {
            double discount = customer.getPoints() / 100.0;
            total -= discount;
            if(total < 0) {
                double temp_total = -1 * total;
                int total_int = (int) temp_total;
                customer.setPoints(total_int * 100);
                total = 0;
            }
            else {
            customer.setPoints(0);
            }
        }

        // Award new points based on total purchase
        int earnedPoints = (int)(total * 10);
        customer.setPoints(customer.getPoints() + earnedPoints);

        // Save updated customer points
        Util.FileManager.saveCustomers();

        // Show customer purchase summary screen
        frame.showCustomerBuyScreen(customer,total);
    }
}