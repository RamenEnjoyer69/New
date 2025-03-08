package com.ramenenjoyer_69.Modal;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private int id;
    private String name;
    private BigDecimal unitPrice;
    private int quantity;
    private LocalDateTime importDate;

    public Product(int id, String name, BigDecimal unitPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.importDate = LocalDateTime.now(); // Auto-generate timestamp
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }
    public LocalDateTime getImportDate() { return importDate; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
