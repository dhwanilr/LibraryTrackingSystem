# Book Store Application

A Java Swing-based bookstore management system, which supports both owner and customer functionality through a single-window graphical user interface.

---

## Features

### Owner Features
- Secure owner login
- Add books to the bookstore
- Delete books from inventory
- Register new customers
- Delete customers
- View all books and customers

### Customer Features
- Customer login system
- Browse available books
- Purchase books
- Redeem loyalty points during purchases
- Gold/Silver membership status system
- Automatic point accumulation

### System Features
- Single-window GUI navigation
- Persistent data storage using text files
- Dynamic screen switching
- Object-oriented design using Java packages
- State-based customer membership system

---

## Technologies Used

- Java
- Java Swing
- Object-Oriented Programming (OOP)
- File Handling

---

## Project Structure

```bash id="h2m9qp"
src/
│
├── Base/
│   ├── Book.java
│   ├── Customer.java
│   ├── Owner.java
│   └── Person.java
│
├── GUI/
│   ├── CustomerBuyScreen.java
│   ├── CustomerStartScreen.java
│   ├── LoginScreen.java
│   ├── MainFrame.java
│   ├── OwnerBooksScreen.java
│   ├── OwnerCustomerScreen.java
│   └── OwnerStartScreen.java
│
├── Main/
│   └── main.java
│
└── Util/
    └── FileManager.java
```

---

## Package Overview

### Base Package
Contains the core system classes and business logic:
- Books
- Customers
- Owners
- Shared person attributes

### GUI Package
Contains all Java Swing user interface screens and navigation logic.

### Main Package
Contains `main.java`, which starts the entire application.

### Util Package
Handles file operations and persistent data storage.

---

## Application Flow

1. The application starts from `main.java`
2. Users are presented with a login screen
3. Owners can manage:
   - Books
   - Customers
4. Customers can:
   - Browse books
   - Purchase books
   - Redeem points
5. Data is saved automatically using file handling

---

## Loyalty System

Customers earn points based on purchases:
- Every $1 spent earns 10 points
- 100 points = $1 redeemed

Membership levels:
- **Silver** → Less than 1000 points
- **Gold** → 1000 points or more

---

## How to Run

1. Open the project in NetBeans
2. Compile the project
3. Run `main.java`

---

## Learning Outcomes

This project helped reinforce:
- Java Swing GUI development
- Package organization
- Object-oriented programming
- File handling
- Event-driven programming
- Application state management
- User authentication systems
