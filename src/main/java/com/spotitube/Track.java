package com.spotitube;

import java.time.LocalDate;
import java.util.Date;

public class Track {

    private int id;
    private String title;
    private String performer;
    private int duration;
    private String album;
    private int playcount;
    private String publicationDate;
    private String description;
    private Boolean offlineAvailable;

    public Track(int id, String title, String performer, int duration, String album, int playcount, String publicationDate, String description, Boolean offlineAvailable) {
        setId(id);
        setTitle(title);
        setPerformer(performer);
        setDuration(duration);
        setAlbum(album);
        setPlaycount(playcount);
        setPublicationDate(publicationDate);
        setDescription(description);
        setOfflineAvailable(offlineAvailable);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(Boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}
