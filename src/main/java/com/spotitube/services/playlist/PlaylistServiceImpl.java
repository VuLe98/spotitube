package com.spotitube.services.playlist;

import com.spotitube.dao.playlist.PlaylistDAO;
import com.spotitube.dao.token.TokenDAO;
import com.spotitube.dto.PlaylistResponse;
import com.spotitube.dto.TrackResponse;
import com.spotitube.entities.Playlist;
import com.spotitube.entities.Token;
import com.spotitube.entities.Track;

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
        if(userToken != null){
            return pDAO.getAllPlaylists(token);
        }
        else{
            throw new AuthenticationException("Incorrect token");
        }
    }

    @Override
    public TrackResponse getContentOfPlaylist(String token, int playlistID) throws AuthenticationException {
        Token userToken = tDAO.getToken(token);
        if(userToken != null){
            return pDAO.getContentOfPlaylist(playlistID);
        } else{
            throw new AuthenticationException("Incorrect token");
        }
    }

    @Override
    public PlaylistResponse addPlaylist(String token, Playlist list) throws AuthenticationException {
        Token userToken = tDAO.getToken(token);
        if(userToken != null){
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
        if(userToken != null){
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
        if(userToken != null){
            pDAO.deletePlaylist(playlistID);
            return pDAO.getAllPlaylists(token);
        }
        else{
            throw new AuthenticationException("Incorrect token");
        }
    }

    @Override
    public TrackResponse deleteTrackOfPlaylist(String token, int playlistID, int trackID) throws AuthenticationException {
        Token userToken = tDAO.getToken(token);
        if(userToken != null) {
            pDAO.removeTrackOfPlaylist(playlistID, trackID);
            return pDAO.getContentOfPlaylist(playlistID);
        }
        else{
            throw new AuthenticationException("Incorrect token");
        }
    }

    @Override
    public TrackResponse addTrackToPlaylist(String token, int playlistID, Track track) throws AuthenticationException {
        Token userToken = tDAO.getToken(token);
        if(userToken != null) {
            pDAO.addTrackToPlaylist(playlistID, track.getId(), track.getOfflineAvailable());
            return pDAO.getContentOfPlaylist(playlistID);
        }
        else{
            throw new AuthenticationException("Incorrect token");
        }
    }
}
