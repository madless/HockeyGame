package com.example.student.hockeygame.ui.activity;

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

/**
 * Created by student on 18.12.2015.
 */
public class PlayerDetailsActivity extends AppCompatActivity {
    private TextView textViewPlayerName, textViewPlayerTeam, textViewPositionNumber, textViewPlayerPosition,
            textViewPlayerOffenceRating, textViewPlayerDefenceRating;
    private ImageView imageViewPlayerAvatar;
    private AvatarManager avatarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
        textViewPlayerName = (TextView) findViewById(R.id.textViewPlayerName);
        textViewPlayerTeam = (TextView) findViewById(R.id.textViewPlayerTeam);
        textViewPositionNumber = (TextView) findViewById(R.id.textViewPositionNumber);
        textViewPlayerPosition = (TextView) findViewById(R.id.textViewPlayerPosition);
        textViewPlayerOffenceRating = (TextView) findViewById(R.id.textViewPlayerOffenceRating);
        textViewPlayerDefenceRating = (TextView) findViewById(R.id.textViewPlayerDefenceRating);
        imageViewPlayerAvatar = (ImageView) findViewById(R.id.imageViewPlayerAvatar);

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
