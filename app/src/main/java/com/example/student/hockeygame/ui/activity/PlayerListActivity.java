package com.example.student.hockeygame.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.student.hockeygame.adapter.HockeyGameListAdapter;
import com.example.student.hockeygame.app_constant.TransferConstant;
import com.example.student.hockeygame.db.DatabaseManager;
import com.example.student.hockeygame.db.dao.TableItemDAO;
import com.example.student.hockeygame.db.dao.PlayerDAO;
import com.example.student.hockeygame.entity.Player;
import com.example.student.hockeygame.entity.Team;

/**
 * Created by student on 18.12.2015.
 */
public class PlayerListActivity extends HockeyGameListActivity{
    private Team chosenTeam;
    private PlayerDAO playerDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chosenTeam = getIntent().getParcelableExtra(TransferConstant.CURRENT_TEAM.toString());
        databaseManager = new DatabaseManager(this);
        playerDAO = new PlayerDAO(databaseManager);
        listItems = playerDAO.getPlayersByTeam(chosenTeam);
        hockeyGameListAdapter = new HockeyGameListAdapter(listItems);
        recyclerView.setAdapter(hockeyGameListAdapter);
        setTitle(chosenTeam.getTitle());
    }

    @Override
    public void onListItemClick(View view, int position) {
        Intent playerDetailsIntent = new Intent(PlayerListActivity.this, PlayerDetailsActivity.class);
        playerDetailsIntent.putExtra(TransferConstant.CURRENT_PLAYER.toString(), (Player) listItems.get(position));
        playerDetailsIntent.putExtra(TransferConstant.CURRENT_TEAM.toString(), chosenTeam);
        startActivity(playerDetailsIntent);
    }

    @Override
    protected TableItemDAO getListItemDAO() {
        return playerDAO;
    }
}
