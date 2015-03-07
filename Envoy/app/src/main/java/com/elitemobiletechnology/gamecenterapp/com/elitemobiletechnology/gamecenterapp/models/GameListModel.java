package com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SteveYang on 3/6/15.
 */
public class GameListModel {
    private int thumbnailResourceId;
    private float rating;
    private String gameTitle;
    private String console;
    private boolean finished;


    public GameListModel(int imageId, String title, String console, float rating,boolean finished) {
        this.thumbnailResourceId = imageId;
        this.gameTitle = title;
        this.console = console;
        this.finished = finished;
        this.rating = rating;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getThumbnailResourceId() {
        return thumbnailResourceId;
    }

    public void setThumbnailResourceId(int thumbnailResourceId) {
        this.thumbnailResourceId = thumbnailResourceId;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


}
