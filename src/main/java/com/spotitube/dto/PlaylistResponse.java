package com.spotitube.dto;

import com.spotitube.models.PlaylistModel;

import java.util.ArrayList;
import java.util.List;

public class PlaylistResponse {

    private List<PlaylistModel> playlist = new ArrayList<>();
    private int length;

    public void addPlaylists(PlaylistModel list){
        playlist.add(list);
    }
    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public List<PlaylistModel> getPlaylists(){
        return playlist;
    }
}
