package com.projects.dwainebrannoninventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class InventoryDataSource {
    private SQLiteDatabase database;
    private final InventoryDatabase dbHelper;

    public InventoryDataSource(Context context) {
        dbHelper = new InventoryDatabase(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertItem(InventoryItem item) {
        // Insert an item into the database
        ContentValues values = new ContentValues();
        values.put(InventoryDatabase.InventoryEntry.COLUMN_ID, item.getId());
        values.put(InventoryDatabase.InventoryEntry.COLUMN_NAME, item.getName());
        values.put(InventoryDatabase.InventoryEntry.COLUMN_DESCRIPTION, item.getDescription());
        values.put(InventoryDatabase.InventoryEntry.COLUMN_QUANTITY, item.getQuantity());

        return database.insert(InventoryDatabase.InventoryEntry.TABLE_NAME, null, values);
    }

    public List<InventoryItem> getAllItems() {
        List<InventoryItem> items = new ArrayList<>();
        // Retrieve data from the database and populate the 'items' list

        // Use a query and cursor to fetch data
        Cursor cursor = database.query(InventoryDatabase.InventoryEntry.TABLE_NAME,
                null, null, null, null, null, null);
        // Iterate through the cursor and add items to the 'items' list
        if (cursor != null && cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(InventoryDatabase.InventoryEntry.COLUMN_ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryDatabase.InventoryEntry.COLUMN_NAME);
            int descriptionColumnIndex = cursor.getColumnIndex(InventoryDatabase.InventoryEntry.COLUMN_DESCRIPTION);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryDatabase.InventoryEntry.COLUMN_QUANTITY);

            long id;
            String name;
            String description;
            int quantity;
            do {
                id = cursor.getLong(idColumnIndex);
                name = cursor.getString(nameColumnIndex);
                description = cursor.getString(descriptionColumnIndex);
                quantity = cursor.getInt(quantityColumnIndex);

                // Process the data here
            } while (cursor.moveToNext());

            InventoryItem item = new InventoryItem(id, name, description, quantity);
            items.add(item);
        }
            if (cursor != null) {
                cursor.close();
             }
        return items;
    }


}