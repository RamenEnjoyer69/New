package com.ramenenjoyer_69.View;

import com.ramenenjoyer_69.Controller.ProductController;
import java.util.Scanner;

public class View {
    public static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== Product Management System ======");
            System.out.println("[W] Write (Add Product)");
            System.out.println("[R] Read (Show Products)");
            System.out.println("[U] Update Product");
            System.out.println("[D] Delete Product");
            System.out.println("[S] Search Product");
            System.out.println("[SA] Save Product");
            System.out.println("[SU] Show Unsaved Product");
            System.out.println("[E] Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "W":
                    ProductController.addProduct();
                    break;
                case "R":
                    ProductController.showProducts();
                    break;
                case "U":
                    ProductController.updateProduct();
                    break;
                case "D":
                    ProductController.deleteProduct();
                    break;
                case "S":
                    ProductController.searchProduct();
                    break;
                case "SA":
                    ProductController.saveProducts();
                    break;
                case "SU":
                    ProductController.showUnsavedProducts();
                    break;
                    case "E":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
