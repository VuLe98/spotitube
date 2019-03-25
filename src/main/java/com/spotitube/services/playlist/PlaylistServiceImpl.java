package com.spotitube.services.playlist;

import com.spotitube.dao.playlist.PlaylistDAO;
import com.spotitube.dao.token.TokenDAO;
import com.spotitube.dto.PlaylistResponse;
import com.spotitube.dto.TrackResponse;
import com.spotitube.entities.Playlist;
import com.spotitube.entities.Token;

import javax.inject.Inject;
import javax.naming.AuthenticationException;

public class PlaylistServiceImpl implements PlaylistService{

    @Inject
    private PlaylistDAO pDAO;

    @Inject
    private TokenDAO tDAO;

    @Override
    public PlaylistResponse getAllPlaylists(String token) throws AuthenticationException {
        Token userToken = tDAO.getToken(token);
        if(tDAO.isTokenValid(userToken)){
            return pDAO.getAllPlaylists(token);
        }
        else{
            throw new AuthenticationException("Incorrect token");
        }
    }

    @Override
    public TrackResponse getContentOfPlaylist(String token, int playlistID) throws AuthenticationException {
        Token userToken = tDAO.getToken(token);
        if(tDAO.isTokenValid(userToken)){
            return pDAO.getContentOfPlaylist(playlistID);
        } else{
            throw new AuthenticationException("Incorrect token");
        }
    }

    @Override
    public PlaylistResponse addPlaylist(String token, Playlist list) throws AuthenticationException {
        Token userToken = tDAO.getToken(token);
        if(tDAO.isTokenValid(userToken)){
            String getInputPlaylist = list.getName();
            pDAO.addPlaylist(token,getInputPlaylist);
            return pDAO.getAllPlaylists(token);
        }
        else{
            throw new AuthenticationException("Incorrect token");
        }
    }

    @Override
    public PlaylistResponse updatePlaylist(String token, Playlist list) throws AuthenticationException {
        Token userToken = tDAO.getToken(token);
        if(tDAO.isTokenValid(userToken)){
            String getNewName = list.getName();
            int getID = list.getId();
            pDAO.updatePlaylist(token,getNewName,getID);
            return pDAO.getAllPlaylists(token);
        }
        else{
            throw new AuthenticationException("Incorrect token");
        }
    }

    @Override
    public PlaylistResponse deletePlaylist(String token, int playlistID) throws AuthenticationException {
        Token userToken = tDAO.getToken(token);
        if(tDAO.isTokenValid(userToken)){
            pDAO.deletePlaylist(playlistID);
            return pDAO.getAllPlaylists(token);
        }
        else{
            throw new AuthenticationException("Incorrect token");
        }
    }
}
