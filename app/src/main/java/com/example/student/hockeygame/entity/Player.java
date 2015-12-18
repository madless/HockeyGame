package com.example.student.hockeygame.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 18.12.2015.
 */
public class Player implements ListItem, TableItem, Parcelable {
    private int id;
    private int teamId;
    private int positionNumber;
    private int playerPosition;
    private int offenceRating;
    private int defenceRating;
    private String name;
    private int avatarIndex;

    public Player(int id, int teamId, int positionNumber, int playerPosition, int offenceRating, int defenceRating, String name, int avatarIndex) {
        this.id = id;
        this.teamId = teamId;
        this.positionNumber = positionNumber;
        this.playerPosition = playerPosition;
        this.offenceRating = offenceRating;
        this.defenceRating = defenceRating;
        this.name = name;
        this.avatarIndex = avatarIndex;
    }

    public int getId() {
        return id;
    }

    public int getPositionNumber() {
        return positionNumber;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public int getOffenceRating() {
        return offenceRating;
    }

    public int getDefenceRating() {
        return defenceRating;
    }

    public String getName() {
        return name;
    }

    public int getAvatarIndex() {
        return avatarIndex;
    }

    @Override
    public String getTitle() {
        return name;
    }

    private Player(Parcel parcel) {
        id = parcel.readInt();
        teamId = parcel.readInt();
        positionNumber = parcel.readInt();
        playerPosition = parcel.readInt();
        offenceRating = parcel.readInt();
        defenceRating = parcel.readInt();
        name = parcel.readString();
        avatarIndex = parcel.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(teamId);
        dest.writeInt(positionNumber);
        dest.writeInt(playerPosition);
        dest.writeInt(offenceRating);
        dest.writeInt(defenceRating);
        dest.writeString(name);
        dest.writeInt(avatarIndex);
    }
}
