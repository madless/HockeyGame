package com.example.student.hockeygame.resource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.drawable.DrawableWrapper;

import com.example.student.hockeygame.entity.Player;

import java.io.IOException;

/**
 * Created by student on 18.12.2015.
 */
public class AvatarManager {
    private Context context;

    public AvatarManager(Context context) {
        this.context = context;
    }

    public Drawable getAvatar(Player player) {
        Drawable drawable = null;
        try {
            switch (player.getAvatarIndex()) {
                case 1: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-1.jpg"), null);
                    break;
                }
                case 2: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-2.jpg"), null);
                    break;
                }
                case 3: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-3.jpg"), null);
                    break;
                }case 4: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-4.jpg"), null);
                    break;
                }
                case 5: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-5.jpg"), null);
                    break;
                }
                case 6: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-6.jpg"), null);
                    break;
                }
                case 7: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-7.jpg"), null);
                    break;
                }
                case 8: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-8.jpg"), null);
                    break;
                }
                case 9: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-9.jpg"), null);
                    break;
                }
                case 10: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-10.jpg"), null);
                    break;
                }
                case 11: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-11.jpg"), null);
                    break;
                }
                case 12: {
                    drawable = Drawable.createFromStream(context.getAssets().open("hockey_player-12.jpg"), null);
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return drawable;
    }
}
