package com.spotitube.models;

import java.util.ArrayList;

public class PlaylistModel {

    private Integer id;
    private String name;
    private boolean owner;
    private ArrayList<TrackModel> tracks;

    public PlaylistModel(){

    }
    public PlaylistModel(Integer id, String name, boolean owner, ArrayList<TrackModel> tracks){
        setId(id);
        setName(name);
        setOwner(owner);
        setTracks(tracks);
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean getOwner(){
        return owner;
    }

    public void setOwner(boolean owner){
        this.owner = owner;
    }

    public ArrayList<TrackModel> getTracks(){
        return tracks;
    }

    public void setTracks(ArrayList<TrackModel> tracks){
        this.tracks = tracks;
    }








}
