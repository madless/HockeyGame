package com.example.student.hockeygame.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.student.hockeygame.db.DatabaseManager;
import com.example.student.hockeygame.entity.ListItem;
import com.example.student.hockeygame.entity.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 18.12.2015.
 */
public class TeamDAO extends TableItemDAO {

    public final static String TEAM_TABLE_NAME = "base_teams";

    private final static String FIELD_TEAM_NAME = "team_name";
    private final static String FIELD_TEAM_NAME_SHORT = "team_name_short";
    private final static String FIELD_DIVISION = "division";
    private final static String FIELD_LARGE_LOGO = "logo_large";
    private final static String FIELD_SMALL_LOGO = "logo_small";

    public static final String CREATE_TEAM_TABLE = "CREATE TABLE " + TEAM_TABLE_NAME + " ("+
            "" + FIELD_ID + " INTEGER PRIMARY KEY, "+
            "" + FIELD_TEAM_NAME + " VARCHAR(50),"+
            "" + FIELD_TEAM_NAME_SHORT + " VARCHAR(10),"+
            "" + FIELD_DIVISION + " VARCHAR(30),"+
            "" + FIELD_LARGE_LOGO + " VARCHAR(50),"+
            "" + FIELD_SMALL_LOGO + " VARCHAR(30)"+
            ")";

    private String[] projection = { FIELD_ID, FIELD_TEAM_NAME, FIELD_TEAM_NAME_SHORT, FIELD_DIVISION,
            FIELD_LARGE_LOGO, FIELD_SMALL_LOGO };

    public int getColumnIndex(String column) {
        switch (column) {
            case FIELD_ID: {
                return 0;
            }
            case FIELD_TEAM_NAME: {
                return 1;
            }
            case FIELD_TEAM_NAME_SHORT: {
                return 2;
            }
            case FIELD_DIVISION: {
                return 3;
            }
            case FIELD_LARGE_LOGO: {
                return 4;
            }
            case FIELD_SMALL_LOGO: {
                return 5;
            }
            default: {
                return -1;
            }
        }
    }

    public TeamDAO(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        databaseManager.createDatabase();
    }

    public List<ListItem> getTeams() {
        databaseManager.open();
        List<ListItem> teams = new ArrayList<>();
        SQLiteDatabase db = databaseManager.getDatabase();
        Cursor cursor = db.query(TEAM_TABLE_NAME, projection, null, null, null, null, null);
        while(cursor.moveToNext()) {
            Team team = getTeamFromCursor(cursor);
            teams.add(team);
        }
        cursor.close();
        databaseManager.close();
        return teams;
    }

    private Team getTeamFromCursor(Cursor cursor) {
        int id = cursor.getInt(getColumnIndex(FIELD_ID));
        String teamName = cursor.getString(getColumnIndex(FIELD_TEAM_NAME));;
        String shortTeamName = cursor.getString(getColumnIndex(FIELD_TEAM_NAME_SHORT));;
        String division = cursor.getString(getColumnIndex(FIELD_DIVISION));;
        String largeLogo = cursor.getString(getColumnIndex(FIELD_LARGE_LOGO));;
        String smallLogo = cursor.getString(getColumnIndex(FIELD_SMALL_LOGO));;
        return new Team(id, teamName, shortTeamName, division, largeLogo, smallLogo);
    }

    @Override
    String getTableName() {
        return TEAM_TABLE_NAME;
    }
}
