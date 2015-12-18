package com.example.student.hockeygame.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 18.12.2015.
 */
public class Team implements ListItem, TableItem, Parcelable {
    private int id;
    private String teamName;
    private String shortTeamName;
    private String division;
    private String largeLogo;
    private String smallLogo;

    public Team(int id, String teamName, String shortTeamName, String division, String largeLogo, String smallLogo) {
        this.id = id;
        this.teamName = teamName;
        this.shortTeamName = shortTeamName;
        this.division = division;
        this.largeLogo = largeLogo;
        this.smallLogo = smallLogo;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", shortTeamName='" + shortTeamName + '\'' +
                ", division='" + division + '\'' +
                ", largeLogo='" + largeLogo + '\'' +
                ", smallLogo='" + smallLogo + '\'' +
                '}';
    }

    @Override
    public String getTitle() {
        return teamName;
    }

    private Team(Parcel parcel) {
        id = parcel.readInt();
        teamName = parcel.readString();
        shortTeamName = parcel.readString();
        division = parcel.readString();
        largeLogo = parcel.readString();
        smallLogo = parcel.readString();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(teamName);
        dest.writeString(shortTeamName);
        dest.writeString(division);
        dest.writeString(largeLogo);
        dest.writeString(smallLogo);
    }
}
