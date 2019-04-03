package com.spotitube.services.track;

import com.spotitube.dao.TokenDAO;
import com.spotitube.dao.TrackDAO;
import com.spotitube.dto.TrackResponse;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.core.Response;

public class TrackServiceImpl implements TrackService{

    private TrackDAO tDAO;
    private TokenDAO tokenDAO;

    public Response getAvailableTracksForPlaylist(String token, int playlistID){
        String doesUserExist = tokenDAO.getUserByToken(token);
        try {
            if (doesUserExist != null) {
                TrackResponse response = tDAO.getAvailableTracksOfPlaylist(playlistID);
                return Response.ok().entity(response).build();
            } else {
                throw new AuthenticationException();
            }
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Inject
    public void setTrackDAO(TrackDAO dao){
        this.tDAO = dao;
    }

    @Inject
    public void setTokenDAO(TokenDAO dao){
        this.tokenDAO = dao;
    }
}
