package com.spotitube.dto;

import com.spotitube.entities.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistResponse {

    private List<Playlist> playlists = new ArrayList<>();
    private int length;

    public void addPlaylists(Playlist list){
        playlists.add(list);
    }
    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public List<Playlist> getPlaylists(){
        return playlists;
    }
}
