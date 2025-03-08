package com.ramenenjoyer_69.Controller;

import com.ramenenjoyer_69.Dao.ProductDao;
import com.ramenenjoyer_69.Modal.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import static com.ramenenjoyer_69.Utilities.Validator.*;

public class ProductController {
    private static final Scanner scanner = new Scanner(System.in);

    // Show product method
    public static void showProducts() {
        System.out.println("\n==== Product List ====");
        ProductDao.getAllProducts();
    }

    // Add product method
    public static void addProduct() {
        String name = validateName();
        BigDecimal price = validatePrice();
        int quantity = validateQuantity();

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

    // Update product method
    public static void updateProduct() {
        System.out.print("\nEnter Product ID to Update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String name = validateName();
        BigDecimal price = validatePrice();
        int quantity = validateQuantity();

        Product updatedProduct = new Product(id, name, price, quantity);
        ProductDao.updateProduct(updatedProduct);

        System.out.println("Product updated successfully!");
    }

    // Delete product method
    public static void deleteProduct() {
        System.out.print("\nEnter Product ID to Delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ProductDao.deleteProduct(id);
        System.out.println("Product deleted successfully!");
    }

    // Search product method
    public static void searchProduct() {
        System.out.print("\nEnter Product Name to Search: ");
        String name = scanner.nextLine();
        ProductDao.searchProduct(name);
    }
}
