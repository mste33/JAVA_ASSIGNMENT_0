import java.util.Scanner;

public class Book {
    private String title;
    private String author;
    private double price; 
    private long ISBN;
    private static int bookcounter = 0;

    public Book(String title, String author, double price, long ISBN) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.ISBN = ISBN;
        bookcounter++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static int findNumberOfCreatedBooks() {
        return bookcounter;
    }

    public boolean equals(Book otherBook) {
        return this.ISBN == otherBook.ISBN && this.price == otherBook.price;
    }

    public String toString() {
        return "Title: " + title + ", Author: " + author + 
               ", ISBN: " + ISBN + ", Price: $" + price;
    }
}

public class BookstoreApplication {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("Welcome to the Bookstore Management System!");
        System.out.println("==========================================");

        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.print("Enter the maximum number of books: ");
        int maxBooks = scanner.nextInt();
        Book[] inventory = new Book[maxBooks];

        int choice;
        int currentBookCount = 0;

        do {
            // Display the main menu
            System.out.println("Main Menu:");
            System.out.println("1. Enter new books (password required)");
            System.out.println("2. Change information of a book (password required)");
            System.out.println("3. Display all books by a specific author");
            System.out.println("4. Display all books under a certain price");
            System.out.println("5. Quit");
            System.out.print("Please enter your choice (1-5): ");
            choice = scanner.nextInt(); // Read user choice

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice < 1 || choice > 5);

        final String PASSWORD = "249";
        int passwordAttempts = 0;

        if (choice == 1) {
            while (passwordAttempts < 3) {
                System.out.print("Enter password: ");
                String inputPassword = scanner.next();

                if (inputPassword.equals(PASSWORD)) {
                    System.out.print("How many books do you want to enter? ");
                    int numberOfBooks = scanner.nextInt();

                    // Check if there is enough space in inventory
                    if (numberOfBooks + currentBookCount <= maxBooks) {
                        scanner.nextLine(); // Clear the buffer
                        for (int i = 0; i < numberOfBooks; i++) {
                            System.out.print("Enter title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter author: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter price: ");
                            double price = scanner.nextDouble();
                            System.out.print("Enter ISBN: ");
                            long ISBN = scanner.nextLong();
                            scanner.nextLine(); // Clear the buffer

                            // Create a new Book object and add to inventory
                            inventory[currentBookCount] = new Book(title, author, price, ISBN);
                            currentBookCount++; // Increment the count of added books
                        }
                        System.out.println("Books added successfully!");
                    } else {
                        System.out.println("Not enough space in inventory. You can add " + (maxBooks - currentBookCount) + " more books.");
                    }
                    break; // Exit the password loop after successful entry
                } else {
                    passwordAttempts++;
                    System.out.println("Incorrect password. Attempts left: " + (3 - passwordAttempts));
                }
            }

            if (passwordAttempts == 3) {
                System.out.println("Too many failed attempts. Returning to main menu.");
            }
        }

        scanner.close();
    }
}








