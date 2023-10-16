package com.projects.dwainebrannoninventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;


    public UserDatabase(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    private static final class UserEntry {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_PASSWORD = "password";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a table to hold user data
        db.execSQL("create table " + UserEntry.TABLE_NAME + " (" +
                UserEntry.COLUMN_PASSWORD + " text not null);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        }

    // Add user to database
    public void addUser(String username, String password) {

            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL("INSERT INTO " + UserEntry.TABLE_NAME + " (" +
                    UserEntry.COLUMN_PASSWORD + ") VALUES ('" + password + "');");

            db.close();
    }
    public boolean checkUserCredentials(String username, String password) {

            SQLiteDatabase db = this.getReadableDatabase();

            String query = "SELECT * FROM " + UserEntry.TABLE_NAME + " WHERE " +
                    UserEntry.COLUMN_PASSWORD + " = '" + password + "';";

            db.execSQL(query);

            db.close();

            return true;
    }
    }

