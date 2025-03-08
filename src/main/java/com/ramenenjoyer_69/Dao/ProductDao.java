package com.ramenenjoyer_69.Dao;

import com.ramenenjoyer_69.Modal.Product;
import com.ramenenjoyer_69.Utilities.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static final List<Product> unsavedProducts = new ArrayList<>();

    // Add product to the unsaved list
    public static void addProduct(Product product) {
        unsavedProducts.add(product);
        System.out.println("Product added to the unsaved list.");
    }

    // Get the list of unsaved products
    public static List<Product> getUnsavedProducts() {
        return new ArrayList<>(unsavedProducts);
    }

    // Save all unsaved products to the database
    public static void saveUnsavedProducts() {
        if (unsavedProducts.isEmpty()) {
            System.out.println("No unsaved products to save.");
            return;
        }

        String sql = "INSERT INTO productTbl (name, unit_price, quantity, import_date) VALUES (?, ?, ?, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Product product : unsavedProducts) {
                stmt.setString(1, product.getName());
                stmt.setBigDecimal(2, product.getUnitPrice());
                stmt.setInt(3, product.getQuantity());
                stmt.addBatch(); // Add to batch for efficiency
            }

            stmt.executeBatch(); // Execute all at once
            unsavedProducts.clear(); // Clear list after saving
            System.out.println("All unsaved products have been saved to the database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getAllProducts() {
        String sql = "SELECT * FROM productTbl";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("ID | Name | Unit Price | Qty | Import Date");
            while (rs.next()) {
                System.out.printf("%d | %s | %.2f | %d | %s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("unit_price"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("import_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct(Product product) {
        String sql = "UPDATE productTbl SET name=?, unit_price=?, quantity=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setBigDecimal(2, product.getUnitPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int id) {
        String sql = "DELETE FROM productTbl WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchProduct(String name) {
        String sql = "SELECT * FROM productTbl WHERE name ILIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("ID | Name | Unit Price | Qty | Import Date");
                while (rs.next()) {
                    System.out.printf("%d | %s | %.2f | %d | %s%n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("unit_price"),
                            rs.getInt("quantity"),
                            rs.getTimestamp("import_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
