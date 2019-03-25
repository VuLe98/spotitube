package com.spotitube.services.track;

import com.spotitube.dao.token.TokenDAO;
import com.spotitube.dao.track.TrackDAO;
import com.spotitube.dto.TrackResponse;
import com.spotitube.entities.Token;

import javax.inject.Inject;
import javax.naming.AuthenticationException;

public class TrackServiceImpl implements TrackService{

    @Inject
    private TrackDAO tDAO;

    @Inject
    private TokenDAO tokenDAO;

    public TrackResponse getAvailableTracksForPlaylist(String token, int playlistID) throws AuthenticationException{
        Token userToken = tokenDAO.getToken(token);
        if(tokenDAO.isTokenValid(userToken)){
            return tDAO.getAvailableTracksOfPlaylist(playlistID);
        }
        else{
            throw new AuthenticationException("Incorrect token");
        }
    }
}
