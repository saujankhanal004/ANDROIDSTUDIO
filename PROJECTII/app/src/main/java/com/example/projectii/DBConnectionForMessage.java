package com.example.projectii;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnectionForMessage extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;

    // SQL query to create the table
    private static final String CREATE_TABLE_ADMIN_MESSAGE =
            "CREATE TABLE AdminMessage (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "address TEXT," +
                    "phone TEXT," +
                    "email TEXT," +
                    "message TEXT" +
                    ");";

    public DBConnectionForMessage(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the "AdminMessage" table when the database is first created
        db.execSQL(CREATE_TABLE_ADMIN_MESSAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade the database when needed (if you change the DATABASE_VERSION)
        // Here, you can implement your logic to handle database migration
        // For this example, we will just drop the existing table and recreate it
        db.execSQL("DROP TABLE IF EXISTS AdminMessage");
        onCreate(db);
    }
}

