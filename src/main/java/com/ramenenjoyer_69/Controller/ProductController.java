package com.ramenenjoyer_69.Controller;

import com.ramenenjoyer_69.Dao.ProductDao;
import com.ramenenjoyer_69.Modal.Product;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.List;
public class ProductController {
    private static final Scanner scanner = new Scanner(System.in);



    // show product method
    public static void showProducts() {
        System.out.println("\n==== Product List ====");
        ProductDao.getAllProducts();
    }

    // Add product method
    public static void addProduct() {
        String name;
        BigDecimal price;
        int quantity;

        // Validate Name (Allows letters and numbers)
        while (true) {
            System.out.print("\nEnter Product Name: ");
            name = scanner.nextLine().trim();

            if (!name.isEmpty() && name.matches("^[a-zA-Z0-9 ]+$")) {
                break;
            } else {
                System.out.println("Invalid name! Only letters, numbers, and spaces are allowed.");
            }
        }

        // Validate Price (Only numbers or decimals)
        while (true) {
            System.out.print("Enter Unit Price: ");
            String priceInput = scanner.nextLine().replace(",", "."); // Allow comma as decimal separator

            if (priceInput.matches("^[0-9]+(\\.[0-9]{1,2})?$")) { // Matches whole number or decimal
                price = new BigDecimal(priceInput);
                break;
            } else {
                System.out.println("Invalid price! Enter a valid number (e.g., 10 or 10.99).");
            }
        }

        // Validate Quantity (Only positive integers)
        while (true) {
            System.out.print("Enter Quantity: ");
            String quantityInput = scanner.nextLine();

            if (quantityInput.matches("^[1-9][0-9]*$")) { // Matches positive integers only
                quantity = Integer.parseInt(quantityInput);
                break;
            } else {
                System.out.println("Invalid quantity! Enter a positive whole number.");
            }
        }

        // Create product and add to unsaved list
        Product newProduct = new Product(0, name, price, quantity);
        ProductDao.addProduct(newProduct);
    }

    // Show unsaved products
    public static void showUnsavedProducts() {
        System.out.println("\n==== Unsaved Products ====");
        List<Product> unsavedProducts = ProductDao.getUnsavedProducts();

        if (unsavedProducts.isEmpty()) {
            System.out.println("No unsaved products.");
        } else {
            System.out.println("Name | Unit Price | Quantity");
            for (Product product : unsavedProducts) {
                System.out.printf("%s | %.2f | %d%n", product.getName(), product.getUnitPrice(), product.getQuantity());
            }
        }
    }

    // Save all unsaved products to the database
    public static void saveProducts() {
        ProductDao.saveUnsavedProducts();
    }

    // update method
    public static void updateProduct() {
        System.out.print("\nEnter Product ID to Update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Unit Price: ");
        BigDecimal price = scanner.nextBigDecimal();
        System.out.print("Enter New Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product updatedProduct = new Product(id, name, price, quantity);
//        ProductDao.updateProduct(updatedProduct);
        System.out.println("Product updated successfully!");
    }

    // delete method
    public static void deleteProduct() {
        System.out.print("\nEnter Product ID to Delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProductDao.deleteProduct(id);
        System.out.println("Product deleted successfully!");
    }

    // search method
    public static void searchProduct() {
        System.out.print("\nEnter Product Name to Search: ");
        String name = scanner.nextLine();
        ProductDao.searchProduct(name);
    }
}
