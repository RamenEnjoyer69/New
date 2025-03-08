package com.ramenenjoyer_69.Utilities;
import java.math.BigDecimal;

import java.util.Scanner;

public class Validator {
    static Scanner scanner = new Scanner(System.in);
    // Method to validate name (Allows letters, numbers, and spaces)
    public static String validateName() {
        while (true) {
            System.out.print("\nEnter Product Name: ");
            String name = scanner.nextLine().trim();

            if (!name.isEmpty() && name.matches("^[a-zA-Z0-9 ]+$")) {
                return name;
            } else {
                System.out.println("Invalid name! Only letters, numbers, and spaces are allowed.");
            }
        }
    }

    // Method to validate price (Only numbers or decimals)
    public static BigDecimal validatePrice() {
        while (true) {
            System.out.print("Enter Unit Price: ");
            String priceInput = scanner.nextLine().replace(",", "."); // Convert comma to dot

            if (priceInput.matches("^[0-9]+(\\.[0-9]{1,2})?$")) { // Matches whole number or decimal
                return new BigDecimal(priceInput);
            } else {
                System.out.println("Invalid price! Enter a valid number (e.g., 10 or 10.99).");
            }
        }
    }

    // Method to validate quantity (Only positive integers)
    public static int validateQuantity() {
        while (true) {
            System.out.print("Enter Quantity: ");
            String quantityInput = scanner.nextLine();

            if (quantityInput.matches("^[1-9][0-9]*$")) { // Matches positive integers
                return Integer.parseInt(quantityInput);
            } else {
                System.out.println("Invalid quantity! Enter a positive whole number.");
            }
        }
    }
}
