import java.io.*;
import java.util.Scanner;

public class FileUtilityMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "sample.txt";
        int choice;

        do {
            System.out.println("\n===== FILE HANDLING MENU =====");
            System.out.println("1. Write to File");
            System.out.println("2. Read File");
            System.out.println("3. Modify File (Append)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    writeFile(fileName, sc);
                    break;

                case 2:
                    readFile(fileName);
                    break;

                case 3:
                    modifyFile(fileName, sc);
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }

    // Write Method
    public static void writeFile(String fileName, Scanner sc) {
        try {
            FileWriter writer = new FileWriter(fileName);
            System.out.print("Enter text to write: ");
            String text = sc.nextLine();
            writer.write(text + "\n");
            writer.close();
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    // Read Method
    public static void readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            System.out.println("\n--- File Content ---");
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }

    // Modify Method (Append)
    public static void modifyFile(String fileName, Scanner sc) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            System.out.print("Enter text to append: ");
            String text = sc.nextLine();
            writer.write(text + "\n");
            writer.close();
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file.");
        }
    }
}