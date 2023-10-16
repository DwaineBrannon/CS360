package com.projects.dwainebrannoninventoryapp;

import java.util.ArrayList;
import java.util.List;

public class InventoryItem {
    private long id; // Unique ID for each item
    private String name;
    private String description;

    private int quantity;


    public InventoryItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public InventoryItem(long id, String name, String description, int quantity) {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }


}