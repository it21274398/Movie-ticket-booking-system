import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Configurations {

    // Method to save the current state of the system to a text file
    public static void saveSystemState(TicketPool ticketPool, int ticketReleaseRate, int customerRetrievalRate) {

        // Create a scanner to take user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to save the system details to a text file? (yes/no): ");

        // Check if the user wants to save the system state
        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
            try (FileWriter writer = new FileWriter("system_state.txt")) {

                // Write the current state details to the file
                writer.write("Tickets Available: " + ticketPool.getTicketsAvailable() + "\n");
                writer.write("Max Ticket Capacity: " + ticketPool.getMaxCapacity() + "\n");
                writer.write("Ticket Release Rate: " + ticketReleaseRate + "\n");
                writer.write("Customer Retrieval Rate: " + customerRetrievalRate + "\n");

                // Confirm the system state is saved
                System.out.println("System details saved to system_state.txt");
            } catch (IOException e) {
                // Handle any errors that occur during file writing
                System.out.println("Error saving system state: " + e.getMessage());
            }
        }
    }

    // Method to load the system state from a previously saved file
    public static boolean loadSystemState(TicketPool ticketPool) {

        // Create a scanner to take user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to start from the previous state? (yes/no): ");

        // Check if the user wants to load the previous state
        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
            try (BufferedReader reader = new BufferedReader(new FileReader("system_state.txt"))) {

                String line;
                int ticketsAvailable = 0, maxCapacity = 0;

                // Read the file line by line to extract saved details
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Tickets Available: ")) {
                        ticketsAvailable = Integer.parseInt(line.split(": ")[1]);
                        ticketPool.setTicketsAvailable(ticketsAvailable);
                    } else if (line.startsWith("Max Ticket Capacity: ")) {
                        maxCapacity = Integer.parseInt(line.split(": ")[1]);
                        ticketPool.setMaxCapacity(maxCapacity);
                    }
                }

                // Display the loaded system state details
                System.out.println("Loaded tickets: " + ticketsAvailable + ", Max capacity: " + maxCapacity);
                return true; // Indicate that the state was successfully loaded
            } catch (IOException | NumberFormatException e) {
                // Handle any errors that occur during file reading or parsing
                System.out.println("Error loading system state: " + e.getMessage());
            }
        }

        return false; // Indicate that the state was not loaded
    }
}
