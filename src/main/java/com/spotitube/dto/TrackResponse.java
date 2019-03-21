package com.spotitube.dto;
import com.spotitube.Track;

import java.util.ArrayList;
import java.util.List;


public class TrackResponse {

    private List<Track> tracks = new ArrayList<>();

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
