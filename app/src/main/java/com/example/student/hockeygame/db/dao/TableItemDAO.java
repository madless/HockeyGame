package com.example.student.hockeygame.db.dao;

import android.database.sqlite.SQLiteDatabase;

import com.example.student.hockeygame.db.DatabaseManager;
import com.example.student.hockeygame.entity.TableItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 18.12.2015.
 */
public abstract class TableItemDAO {
    public final static String FIELD_ID = "id";
    DatabaseManager databaseManager;

    public void deleteTableItem(TableItem tableItem) {
        databaseManager.open();
        SQLiteDatabase db = databaseManager.getDatabase();
        String deletingClause = FIELD_ID + " = ?";
        String[] deletingArgs = { String.valueOf(tableItem.getId()) };
        db.delete(getTableName(), deletingClause, deletingArgs);
        databaseManager.close();
    }
    abstract String getTableName();
}
