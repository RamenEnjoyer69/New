package com.ramenenjoyer_69;

import com.ramenenjoyer_69.View.View;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "50th34rith";
    public static void main(String[] args) {
//        display();
    View.showMainMenu();

    }

    public static void display() {
        System.out.println("Inventory management system");
        System.out.println("1. Add Product");
        System.out.println("2. Display Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("\n=================================================");
        System.out.print("Select an option: ");
        int option = sc.nextInt();
        sc.nextLine();
        optionHandler(option);

    }

    public static void optionHandler(int option) {
        switch (option) {
            case 1 -> {
                add();
            }
            case 2 -> {
                displayProducts();
            }
            default -> System.out.println("Invalid option");
        }
    }

    public static void insertProduct(String name, double unitPrice, int quantity) {
        String sql = "INSERT INTO Products (Name, UnitPrice, Quantity, ImportedDate) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, unitPrice);
            pstmt.setInt(3, quantity);
            pstmt.setDate(4, Date.valueOf(LocalDate.now()));

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void add() {
        System.out.println("Add Product");
        System.out.print("Product name: ");
        String name = sc.nextLine();
        System.out.print("Price per unit: ");
        double unitPrice = sc.nextDouble();
        sc.nextLine();
        System.out.print("Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();


        insertProduct(name, unitPrice, quantity);
    }

    public static void showProducts() {
        System.out.println("Inventory management system");
        System.out.println("======================================");

        System.out.println("======================================");
    }

    public static void getProducts() {
        String sql = "SELECT * FROM Products";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("ID | Name | Unit Price | Quantity | Imported Date");
            System.out.println("---------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                double unitPrice = rs.getDouble("UnitPrice");
                int quantity = rs.getInt("Quantity");
                String importedDate = rs.getDate("ImportedDate").toString();

                System.out.printf("%d | %s | %.2f | %d | %s%n", id, name, unitPrice, quantity, importedDate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public static void displayProducts() {
            String sql = "SELECT * FROM Products";

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                System.out.println("ID | Name | Unit Price | Quantity | Imported Date");
                System.out.println("---------------------------------------------------");

                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("Name");
                    double unitPrice = rs.getDouble("UnitPrice");
                    int quantity = rs.getInt("Quantity");
                    String importedDate = rs.getDate("ImportedDate").toString();

                    System.out.printf("%d | %s | %.2f | %d | %s%n", id, name, unitPrice, quantity, importedDate);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


}