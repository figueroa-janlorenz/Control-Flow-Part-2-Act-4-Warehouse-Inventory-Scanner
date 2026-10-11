package warehouse.inventory.scanner;
import java.util.Scanner;

public class WarehouseInventoryScanner {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] warehouse = {
            {10, 5, 8},
            {3, -1, 12},
            {7, 2, 15}
        };

        int choice;

        do {
            System.out.println("\nWAREHOUSE INVENTORY MENU");
            System.out.println("1. View Total Inventory");
            System.out.println("2. Find Low Stock Shelf");
            System.out.println("3. Restock Shelf");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            choice = readInt(sc);

            if (choice == 1) {
                int total = 0;

                for (int i = 0; i < warehouse.length; i++) {
                    for (int j = 0; j < warehouse[i].length; j++) {
                        if (warehouse[i][j] == -1) {
                            continue;
                        }

                        total += warehouse[i][j];
                    }
                }

                System.out.println("Total Inventory: " + total);

            } else if (choice == 2) {
                boolean found = false;

                for (int i = 0; i < warehouse.length; i++) {
                    for (int j = 0; j < warehouse[i].length; j++) {
                        if (warehouse[i][j] == -1) {
                            continue;
                        }

                        if (warehouse[i][j] <= 3) {
                            System.out.println("Low Stock Found!");
                            System.out.println("Row: " + (i + 1));
                            System.out.println("Column: " + (j + 1));
                            System.out.println("Items: " + warehouse[i][j]);

                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        break;
                    }
                }

                if (!found) {
                    System.out.println("No critically low shelves found.");
                }

            } else if (choice == 3) {
                restock(warehouse, sc);

            } else if (choice == 4) {
                System.out.println("Exiting Inventory System...");

            } else {
                System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);
    }

    public static void restock(int[][] warehouse, Scanner sc) {
        int row;
        int column;

        while (true) {
            System.out.print("Enter row (1-" + warehouse.length + "): ");
            row = readInt(sc);

            System.out.print("Enter column (1-" + warehouse[0].length + "): ");
            column = readInt(sc);

            if (row < 1 || row > warehouse.length
                    || column < 1 || column > warehouse[0].length) {
                System.out.println("Invalid shelf location. Try again.");
                continue;
            }

            if (warehouse[row - 1][column - 1] == -1) {
                System.out.println("Cannot restock a damaged shelf. Try again.");
                continue;
            }

            break;
        }

        int amount;

        while (true) {
            System.out.print("Enter quantity to add: ");
            amount = readInt(sc);

            if (amount <= 0) {
                System.out.println("Quantity must be greater than zero.");
                continue;
            }

            break;
        }

        warehouse[row - 1][column - 1] += amount;

        System.out.println("Restocking Successful!");
        System.out.println("Updated Shelf Quantity: " + warehouse[row - 1][column - 1]);

        return;
    }

    public static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Enter a whole number: ");
            sc.next();
        }

        return sc.nextInt();
    }
    
}
