package task4;
import java.util.*;

class Item {
    String name;
    String category;

    Item(String name, String category) {
        this.name = name;
        this.category = category;
    }
}

public class RecommendationSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample data
        List<Item> items = new ArrayList<>();
        items.add(new Item("Laptop", "Electronics"));
        items.add(new Item("Headphones", "Electronics"));
        items.add(new Item("Shirt", "Clothing"));
        items.add(new Item("Jeans", "Clothing"));
        items.add(new Item("Book", "Education"));
        items.add(new Item("Notebook", "Education"));

        System.out.println("Available Categories: Electronics, Clothing, Education");
        System.out.print("Enter your preferred category: ");
        String userPref = sc.nextLine();

        System.out.println("\nRecommended Items:");

        boolean found = false;

        for (Item item : items) {
            if (item.category.equalsIgnoreCase(userPref)) {
                System.out.println("- " + item.name);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No recommendations found.");
        }

        sc.close();
    }
}