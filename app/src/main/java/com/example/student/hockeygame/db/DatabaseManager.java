package com.example.student.hockeygame.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by student on 18.12.2015.
 */
public class DatabaseManager {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public DatabaseManager(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public DatabaseManager createDatabase() {
        try {
            databaseHelper.createDataBase();
        }
        catch (IOException ex) {
            Log.e("mylog", ex.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DatabaseManager open() {
        try {
            databaseHelper.openDatabase();
            databaseHelper.close();
            database = databaseHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException) {
            Log.e("mylog", "open >>"+ mSQLException.toString());
        }
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}
