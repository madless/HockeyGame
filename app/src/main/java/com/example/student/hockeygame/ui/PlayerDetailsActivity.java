package com.example.student.hockeygame.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.hockeygame.R;
import com.example.student.hockeygame.app_constant.TransferConstant;
import com.example.student.hockeygame.entity.Player;
import com.example.student.hockeygame.entity.Team;
import com.example.student.hockeygame.resource.AvatarManager;
import com.google.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_player_details)
public class PlayerDetailsActivity extends RoboActivity {
    @InjectView(R.id.textViewPlayerName) private TextView textViewPlayerName;
    @InjectView(R.id.textViewPlayerTeam) private TextView textViewPlayerTeam;
    @InjectView(R.id.textViewPositionNumber) private TextView textViewPositionNumber;
    @InjectView(R.id.textViewPlayerPosition) private TextView textViewPlayerPosition;
    @InjectView(R.id.textViewPlayerOffenceRating) private TextView textViewPlayerOffenceRating;
    @InjectView(R.id.textViewPlayerDefenceRating) private TextView textViewPlayerDefenceRating;
    @InjectView(R.id.imageViewPlayerAvatar) private ImageView imageViewPlayerAvatar;
    private AvatarManager avatarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Player chosenPlayer = getIntent().getParcelableExtra(TransferConstant.CURRENT_PLAYER.toString());
        Team chosenTeam = getIntent().getParcelableExtra(TransferConstant.CURRENT_TEAM.toString());
        setTitle(chosenPlayer.getTitle());

        avatarManager = new AvatarManager(this);

        fillViews(chosenPlayer, chosenTeam);
    }

    public void fillViews(Player player, Team team) {
        textViewPlayerName.setText(getString(R.string.player_details_name) + player.getName());
        textViewPlayerTeam.setText(getString(R.string.player_details_team) + team.getTitle());
        textViewPositionNumber.setText(getString(R.string.player_details_position_number) + player.getPositionNumber());
        textViewPlayerPosition.setText(getString(R.string.player_details_player_position) + player.getPlayerPosition());
        textViewPlayerOffenceRating.setText(getString(R.string.player_details_offence_rating) + player.getOffenceRating());
        textViewPlayerDefenceRating.setText(getString(R.string.player_details_defence_rating) + player.getDefenceRating());
        Drawable drawable = avatarManager.getAvatar(player);
        imageViewPlayerAvatar.setImageDrawable(drawable);
    }
}
