package com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.models;

/**
 * Created by SteveYang on 3/6/15.
 */
public class VideoGameModel {
    private int thumbnailResourceId;
    private float rating;
    private String gameTitle;
    private String console;
    private String fileName;
    private boolean finished;

    public VideoGameModel(int imageId, String title, String console, boolean finished, float rating) {
        this.thumbnailResourceId = imageId;
        this.gameTitle = title;
        this.console = console;
        this.finished = finished;
        this.rating = rating;
    }

    public VideoGameModel(String filename, String title, String console, boolean finished, float rating) {
        this.fileName = filename;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
