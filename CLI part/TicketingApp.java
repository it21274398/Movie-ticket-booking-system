import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketingApp {
    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Create a TicketPool object with initial values (will be updated based on user input or saved data)
        TicketPool ticketPool = new TicketPool(0, 0);

        // Try to load the system state from a file (e.g., system_state.txt)
        boolean loaded = Configurations.loadSystemState(ticketPool);
        int ticketReleaseRate = 0, customerRetrievalRate = 0;
        int totalTickets = 0, maxTicketCapacity = 0;

        // If the system state is not loaded, prompt the user for initial values
        if (!loaded) {
            System.out.println("Welcome to the Ticketing System!");
            totalTickets = getPositiveInteger(scanner, "Enter total number of tickets: ");
            maxTicketCapacity = getPositiveInteger(scanner, "Enter max ticket capacity: ");
            ticketReleaseRate = getPositiveInteger(scanner, "Enter ticket release rate (seconds): ");
            customerRetrievalRate = getPositiveInteger(scanner, "Enter customer retrieval rate (seconds): ");
            // Create a new TicketPool with the user input
            ticketPool = new TicketPool(totalTickets, maxTicketCapacity);
        } else {
            // If the system state is loaded from a file, read the saved data
            try (BufferedReader reader = new BufferedReader(new FileReader("system_state.txt"))) {
                ArrayList<Integer> values = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    // Extract values from the file (format: "key: value")
                    values.add(Integer.parseInt(line.split(": ")[1]));
                }

                // Assign values from the file to the variables
                totalTickets = values.get(0);
                maxTicketCapacity = values.get(1);
                ticketReleaseRate = values.get(2);
                customerRetrievalRate = values.get(3);

                // Create a new TicketPool with the loaded data
                ticketPool = new TicketPool(totalTickets, maxTicketCapacity);

            } catch (IOException | NumberFormatException e) {
                System.out.println("Error loading system state: " + e.getMessage());
            }
        }

        // Create Vendor and Customer objects
        Vendor vendor = new Vendor(ticketPool, ticketReleaseRate);
        Customer customer = new Customer(ticketPool, customerRetrievalRate);

        // Declare threads for the Vendor and Customer
        Thread vendorThread = null, customerThread = null;

        // Main loop for the application
        while (true) {
            // Display menu options to the user
            System.out.println("\nCommands:\n1. Start\n2. Stop\n3. Exit");
            // Get the user's choice
            int choice = getValidChoice(scanner, "Enter choice: ");

            // Handle the user's choice
            switch (choice) {
                case 1:  // Start the system
                    if (vendorThread == null || !vendorThread.isAlive()) {
                        // Start vendor and customer threads
                        vendorThread = new Thread(vendor);
                        customerThread = new Thread(customer);
                        vendorThread.start();
                        customerThread.start();
                        System.out.println("System started!");
                    } else {
                        System.out.println("System already running!");
                    }
                    break;
                case 2:  // Stop the system
                    if (vendorThread != null && customerThread != null) {
                        // Interrupt both threads to stop the system
                        vendorThread.interrupt();
                        customerThread.interrupt();
                        System.out.println("System stopped!");
                        // Save the current system state to a file
                        Configurations.saveSystemState(ticketPool, ticketReleaseRate, customerRetrievalRate);
                    } else {
                        System.out.println("No system running!");
                    }
                    break;
                case 3:  // Exit the application
                    if (vendorThread != null && customerThread != null) {
                        // Interrupt both threads to stop the system
                        vendorThread.interrupt();
                        customerThread.interrupt();
                    }
                    System.out.println("Exiting...");
                    System.exit(0);  // Exit the application
                    break;
                default:
                    // Handle invalid choice
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Method to get a positive integer from the user
    private static int getPositiveInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;  // Return the value if it's positive
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                // Handle invalid input
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
    }

    // Method to get a valid choice (1, 2, or 3) from the user
    private static int getValidChoice(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 3) {
                    return choice;  // Return the valid choice
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                // Handle invalid choice
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }
}
