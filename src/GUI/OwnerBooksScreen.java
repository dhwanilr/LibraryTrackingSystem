package GUI;

import Base.Book;
import Base.Owner;
import Util.FileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OwnerBooksScreen extends JPanel {
    
    // Necessary references to frame and table for books
    private MainFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    
    // Text fields for the name and price of book 
    private JTextField nameField;
    private JTextField priceField;
    
    // Singleton instance of owner
    private Owner owner = Owner.getInstance();

    public OwnerBooksScreen(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        // Column names for the table
        String[] columnNames = {"Book Name", "Book Price"};
        // Models the table with column names with 0 initial rows
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        owner.setBooks(Util.FileManager.loadBooks());
        // Create a table then wrap in a scroll pane so it is 
        // scrollable if there are many books
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadTable(); // load existing books

        // Creates a new middle panel to handle the elements there
        JPanel middlePanel = new JPanel();
        // Fields to add characteristics of a book
        nameField = new JTextField(10);
        priceField = new JTextField(5);
        // Creates a button 
        JButton addButton = new JButton("Add");
        
        // Add them to the middle panel
        middlePanel.add(new JLabel("Name:"));
        middlePanel.add(nameField);
        middlePanel.add(new JLabel("Price:"));
        middlePanel.add(priceField);
        middlePanel.add(addButton);
        
        // Add the middle panel to the overall panel 
        add(middlePanel, BorderLayout.NORTH);
        
        // If add is clicked then it adds book
        addButton.addActionListener(e -> addBook());

        // Creates a bottom panel
        JPanel bottomPanel = new JPanel();
        // Creates two buttons for delete and back 
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");

        // Adds the buttons to the panel 
        bottomPanel.add(deleteButton);
        bottomPanel.add(backButton);
        
        // Adds bottom panel to the bottom
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Deletes book if the button is pressed
        deleteButton.addActionListener(e -> deleteBook());
        // Goes back to start screen if back is pressed
        backButton.addActionListener(e -> frame.showScreen("ownerStart"));
    }

    private void loadTable() {
        tableModel.setRowCount(0); // clear table
        // Gets the list of books from the owner
        List<Book> books = owner.getBooks();
        // Adds a row for each book 
        for (Book b : books) {
            tableModel.addRow(new Object[]{b.getName(), b.getCost()});
        }
    }

    private void addBook() {
        // Gets the name and price of the book
        String title = nameField.getText().trim();
        String priceText = priceField.getText().trim();
        
        // If name or price is empty then it shows an error message
        if(title.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter name and price.");
            return;
        }
        
        // If price isn't a number then it shows then error message
        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Price must be a number.");
            return;
        }
        
        // Adds book through owner 
        owner.addBook(new Book(title, price));
        
        // Saves new book list to file
        Util.FileManager.saveBooks(owner.getBooks());
        
        // Re-loads the table
        loadTable();
        
        // Sets the fields to empty again 
        nameField.setText("");
        priceField.setText("");
    }

    private void deleteBook() {
        // Gets selected row 
        int selectedRow = table.getSelectedRow();
        // If row isn't selected then it leads to an error 
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(this,"Please select a row to delete");
            return;
        }
        
        // Gets the title at selected row
        String title = (String) tableModel.getValueAt(selectedRow,0);
        
        // removes the book from owners book ArrayList
        owner.getBooks().stream()
             .filter(b -> b.getName().equals(title))
             .findFirst()
             .ifPresent(owner::removeBook);
        
        // Re-loads the table and resaves the new list 
        loadTable();
        Util.FileManager.saveBooks(owner.getBooks());
    }
    
    // Refresh function necessary for MainFrame
    public void refresh() {
        loadTable();
    }   
}