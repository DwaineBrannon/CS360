package com.projects.dwainebrannoninventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;

    public InventoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Define the Inventory table schema
    static final class InventoryEntry {
        public static final String TABLE_NAME = "inventory";
        public static final String COLUMN_ID = "_id"; // Unique ID for each item
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_QUANTITY = "quantity";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a table to hold inventory items
        db.execSQL("CREATE TABLE " + InventoryEntry.TABLE_NAME + " (" +
                InventoryEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                InventoryEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                InventoryEntry.COLUMN_DESCRIPTION + " TEXT, " +
                InventoryEntry.COLUMN_QUANTITY + " INTEGER DEFAULT 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS inventory");
    }
}