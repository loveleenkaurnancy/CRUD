package com.example.crud.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseQueries {

    DatabaseCreation db;
    SQLiteDatabase sql;

    public DatabaseQueries(Context context)
    {
        db = new DatabaseCreation(context);
    }

    public long addData(String name, String email, String password)
    {
        sql = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseColumns.NAME, name);
        cv.put(DatabaseColumns.EMAIL, email);
        cv.put(DatabaseColumns.PASSWORD, password);

        long result =sql.insert(DatabaseColumns.TABLE_NAME, null, cv);
        return result;
    }

    public Cursor getData()
    {
        sql = db.getReadableDatabase();
        Cursor cursor = sql.query(DatabaseColumns.TABLE_NAME,null,null,null,null,null,null);
//        Cursor cursor = sql.rawQuery("SELECT * FROM " + DatabaseColumns.TABLE_NAME, null);
        return cursor;
    }

    public long editData(String id, String name, String email, String password)
    {
        sql = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseColumns.NAME, name);
        cv.put(DatabaseColumns.EMAIL, email);
        cv.put(DatabaseColumns.PASSWORD, password);

       long result = sql.update(DatabaseColumns.TABLE_NAME, cv, DatabaseColumns.ID + "=" + id, null);
      
        return result;
    }

    public long deleteData(String id)
    {
        sql = db.getWritableDatabase();
        long result = sql.delete(DatabaseColumns.TABLE_NAME, DatabaseColumns.ID + "=" + id, null);
        return result;
    }

}
