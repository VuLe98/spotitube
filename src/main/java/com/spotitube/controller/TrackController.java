package com.spotitube.controller;

import com.spotitube.dao.TrackDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    @Inject
    private TrackDAO dao;

    @GET
    @Produces("application/json")
    public Response getTracksOfPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistID){
        return Response.ok().entity(dao.getAvailableTracksOfPlaylist(playlistID)).build();
    }

}
