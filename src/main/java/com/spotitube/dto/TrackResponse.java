package com.spotitube.dto;

import com.spotitube.models.TrackModel;

import java.util.ArrayList;
import java.util.List;


public class TrackResponse {

    private List<TrackModel> tracks = new ArrayList<>();

    public List<TrackModel> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackModel> tracks) {
        this.tracks = tracks;
    }
}
