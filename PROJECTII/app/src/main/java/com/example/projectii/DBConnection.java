package com.example.projectii;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnection extends SQLiteOpenHelper {
    private static final String DB_NAME = " ";
    private static final int VERSION = 1;

    public DBConnection(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String adminMessageQuery = "CREATE TABLE AdminMessage(id INTEGER PRIMARY KEY, name TEXT, address TEXT, phone TEXT, email TEXT, message TEXT)";
        sqLiteDatabase.execSQL(adminMessageQuery);

        String goodsQuery = "CREATE TABLE Goods(id INTEGER PRIMARY KEY, productname TEXT, price INTEGER, productqty INTEGER, productdes TEXT)";
        sqLiteDatabase.execSQL(goodsQuery);
    }

    public Cursor selectStudents(){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * from AdminMessage ";
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }
    public void deleteRecord(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = { String.valueOf(id) };
        db.delete("AdminMessage", whereClause, whereArgs);
        db.close();
    }
    public void deleteRecordG(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = { String.valueOf(id) };
        db.delete("Goods", whereClause, whereArgs);
        db.close();
    }





    public Cursor selectProducts() {
        SQLiteDatabase db = getReadableDatabase(); // Use the instance method here
        String query = "Select * from Goods ";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop the old tables if they exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS AdminMessage");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Goods");

        // Recreate the tables
        onCreate(sqLiteDatabase);
    }
}
