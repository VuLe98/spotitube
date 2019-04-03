package com.spotitube.services.playlist;

import com.spotitube.dao.PlaylistDAO;
import com.spotitube.dao.TokenDAO;
import com.spotitube.dto.PlaylistResponse;
import com.spotitube.dto.TrackResponse;
import com.spotitube.models.PlaylistModel;
import com.spotitube.models.TrackModel;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.core.Response;

public class PlaylistServiceImpl implements PlaylistService{

    private PlaylistDAO pDAO;
    private TokenDAO tDAO;

    @Override
    public Response getAllPlaylists(String token){
        String doesUserExist = tDAO.getUserByToken(token);
        try {
            if (doesUserExist != null) {
                PlaylistResponse response = pDAO.getAllPlaylists(token);
                return Response.ok().entity(response).build();
            } else {
                throw new AuthenticationException();
            }
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Override
    public Response getContentOfPlaylist(String token, int playlistID){
        String doesUserExist = tDAO.getUserByToken(token);
        try {
            if (doesUserExist != null) {
                TrackResponse response = pDAO.getContentOfPlaylist(playlistID);
                return Response.ok().entity(response).build();
            } else {
                throw new AuthenticationException();
            }
        }
        catch (AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Override
    public Response addPlaylist(String token, PlaylistModel list){
        String doesUserExist = tDAO.getUserByToken(token);
        try {
            if (doesUserExist != null) {
                String getInputPlaylist = list.getName();
                pDAO.addPlaylist(token, getInputPlaylist);
                return getAllPlaylists(token);
            } else {
                throw new AuthenticationException();
            }
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Override
    public Response updatePlaylist(String token, int playlistID, PlaylistModel list){
        String doesUserExist = tDAO.getUserByToken(token);
        try {
            if (doesUserExist != null) {
                String getNewName = list.getName();
                pDAO.updatePlaylist(getNewName, playlistID);
                return getAllPlaylists(token);
            } else {
                throw new AuthenticationException();
            }
        }
        catch (AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Override
    public Response deletePlaylist(String token, int playlistID){
        String doesUserExist = tDAO.getUserByToken(token);
        try {
            if (doesUserExist != null) {
                pDAO.deletePlaylist(playlistID);
                return getAllPlaylists(token);
            } else {
                throw new AuthenticationException();
            }
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Override
    public Response deleteTrackOfPlaylist(String token, int playlistID, int trackID){
        String doesUserExist = tDAO.getUserByToken(token);
        try {
            if (doesUserExist != null) {
                pDAO.removeTrackOfPlaylist(playlistID, trackID);
                return getContentOfPlaylist(token, playlistID);
            } else {
                throw new AuthenticationException();
            }
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Override
    public Response addTrackToPlaylist(String token, int playlistID, TrackModel trackModel){
        String doesUserExist = tDAO.getUserByToken(token);
        try {
            if (doesUserExist != null) {
                pDAO.addTrackToPlaylist(playlistID, trackModel);
                return Response.ok().entity(trackModel).build();
            } else {
                throw new AuthenticationException();
            }
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Inject
    public void setTokenDAO(TokenDAO dao){
        this.tDAO = dao;
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO dao){
        this.pDAO = dao;
    }
}
