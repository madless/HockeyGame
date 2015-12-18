package com.example.student.hockeygame.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.student.hockeygame.db.DatabaseManager;
import com.example.student.hockeygame.entity.ListItem;
import com.example.student.hockeygame.entity.Player;
import com.example.student.hockeygame.entity.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 18.12.2015.
 */
public class PlayerDAO extends TableItemDAO {
    public final static String PLAYER_TABLE_NAME = "base_cards";

    private final static String FIELD_TEAM_ID = "team_id";
    private final static String FIELD_POSITION_NUMBER = "position_number";
    private final static String FIELD_PLAYER_POSITION_NUMBER = "player_position";
    private final static String FIELD_OFFENCE_RATING = "offense_rating";
    private final static String FIELD_DEFENCE_RATING = "defense_rating";
    private final static String FIELD_PLAYER_NAME = "card_name";
    private final static String FIELD_AVATAR_INDEX = "avatar_index";

    public static final String CREATE_PLAYER_TABLE = "CREATE TABLE \"base_cards\" " +
            "('"+ FIELD_ID +"' INTEGER PRIMARY KEY ," +
            "'"+ FIELD_TEAM_ID +"' INTEGER," +
            "'"+ FIELD_POSITION_NUMBER +"' INTEGER," +
            "'"+ FIELD_PLAYER_POSITION_NUMBER +"' INTEGER," +
            "'"+ FIELD_OFFENCE_RATING +"' INTEGER," +
            "'"+ FIELD_DEFENCE_RATING +"' INTEGER," +
            "'"+ FIELD_PLAYER_NAME +"' VARCHAR(100)," +
            "'"+ FIELD_AVATAR_INDEX +"' INTEGER)";

    private final static String GET_PLAYERS_BY_TEAM_SELECTION = "team_id = ?";

    private String[] projection = { FIELD_ID, FIELD_TEAM_ID, FIELD_POSITION_NUMBER, FIELD_PLAYER_POSITION_NUMBER,
            FIELD_OFFENCE_RATING, FIELD_DEFENCE_RATING, FIELD_PLAYER_NAME, FIELD_AVATAR_INDEX };

    public int getColumnIndex(String column) {
        switch (column) {
            case FIELD_ID: {
                return 0;
            }
            case FIELD_TEAM_ID: {
                return 1;
            }
            case FIELD_POSITION_NUMBER: {
                return 2;
            }
            case FIELD_PLAYER_POSITION_NUMBER: {
                return 3;
            }
            case FIELD_OFFENCE_RATING: {
                return 4;
            }
            case FIELD_DEFENCE_RATING: {
                return 5;
            }
            case FIELD_PLAYER_NAME: {
                return 6;
            }
            case FIELD_AVATAR_INDEX: {
                return 7;
            }
            default: {
                return -1;
            }
        }
    }

    public PlayerDAO(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        databaseManager.createDatabase();

    }

    public List<ListItem> getPlayersByTeam(Team team) {
        databaseManager.open();
        List<ListItem> players = new ArrayList<>();
        SQLiteDatabase db = databaseManager.getDatabase();
        String[] selectionArgs = { String.valueOf(team.getId()) };
        String sortOrder = FIELD_PLAYER_NAME + " ASC ";
        Cursor cursor = db.query(PLAYER_TABLE_NAME, projection, GET_PLAYERS_BY_TEAM_SELECTION, selectionArgs, null, null, sortOrder);
        while(cursor.moveToNext()) {
            Player player = getPlayerFromCursor(cursor);
            players.add(player);
        }
        cursor.close();
        databaseManager.close();
        return players;
    }

    private Player getPlayerFromCursor(Cursor cursor) {
        int id = cursor.getInt(getColumnIndex(FIELD_ID));
        int teamId = cursor.getInt(getColumnIndex(FIELD_TEAM_ID));
        int positionNumber = cursor.getInt(getColumnIndex(FIELD_POSITION_NUMBER));
        int playerPosition = cursor.getInt(getColumnIndex(FIELD_PLAYER_POSITION_NUMBER));
        int offenceRating = cursor.getInt(getColumnIndex(FIELD_OFFENCE_RATING));
        int defenceRating = cursor.getInt(getColumnIndex(FIELD_DEFENCE_RATING));
        String name = cursor.getString(getColumnIndex(FIELD_PLAYER_NAME));
        int avatarIndex = cursor.getInt(getColumnIndex(FIELD_AVATAR_INDEX));
        return new Player(id, teamId, positionNumber, playerPosition, offenceRating, defenceRating, name, avatarIndex);
    }

    @Override
    String getTableName() {
        return PLAYER_TABLE_NAME;
    }
}
