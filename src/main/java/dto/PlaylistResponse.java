package dto;

import com.spotitube.Playlist;

import java.util.List;

public class PlaylistResponse {

    private String name;
    private List<Playlist> playlists;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Playlist> getPlaylist(){
        return playlists;
    }

    public void setPlayList(List<Playlist> playlist){
        this.playlists = playlist;
    }

}
