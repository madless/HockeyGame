package com.example.student.hockeygame.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.student.hockeygame.adapter.HockeyGameListAdapter;
import com.example.student.hockeygame.app_constant.TransferConstant;
import com.example.student.hockeygame.db.DatabaseManager;
import com.example.student.hockeygame.db.dao.TableItemDAO;
import com.example.student.hockeygame.db.dao.TeamDAO;
import com.example.student.hockeygame.entity.Team;

public class TeamListActivity extends HockeyGameListActivity {
    private TeamDAO teamDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseManager = new DatabaseManager(this);
        teamDAO = new TeamDAO(databaseManager);
        listItems = teamDAO.getTeams();
        hockeyGameListAdapter = new HockeyGameListAdapter(listItems);
        recyclerView.setAdapter(hockeyGameListAdapter);
    }

    @Override
    public void onListItemClick(View view, int position) {
        Intent playerListIntent = new Intent(TeamListActivity.this, PlayerListActivity.class);
        playerListIntent.putExtra(TransferConstant.CURRENT_TEAM.toString(), (Team) listItems.get(position));
        startActivity(playerListIntent);
    }

    @Override
    protected TableItemDAO getListItemDAO() {
        return teamDAO;
    }
}
