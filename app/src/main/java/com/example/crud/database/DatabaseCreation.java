package com.example.crud.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DatabaseCreation extends SQLiteOpenHelper {
    public Context context;

    int i = 1;
    int i1 = 2;
    private static final String DB_NAME="student.db";

    public String query = "CREATE TABLE " + DatabaseColumns.TABLE_NAME +
            " ( " + DatabaseColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DatabaseColumns.NAME + " TEXT, " +
            DatabaseColumns.EMAIL + " TEXT, " +
            DatabaseColumns.PASSWORD + " TEXT)";

    public DatabaseCreation(Context context) {
        super(context, "student.db", null, 2);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + DatabaseColumns.TABLE_NAME;
        db.execSQL(sql);
        db.execSQL(query);
        onCreate(db);
    }

    public void importDB(String inFileName) {

        final String outFileName = context.getDatabasePath(DB_NAME).toString();

        try {

            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);

            // Transfer bytes from the input file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Close the streams
            output.flush();
            output.close();
            fis.close();

            Toast.makeText(context, "Import Completed", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Unable to import database. Retry", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    public void backup(String outFileName) {
        Log.e("outf",outFileName);
        //database path
        final String inFileName = context.getDatabasePath(DB_NAME).toString();

        try {

            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);

            // Transfer bytes from the input file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Close the streams
            output.flush();
            output.close();
            fis.close();

            Toast.makeText(context, "Backup Completed", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Unable to backup database. Retry", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
