package com.spotitube.controller.track;

import com.spotitube.services.track.TrackService;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    @Inject
    private TrackService service;

    @GET
    @Produces("application/json")
    public Response getTracksOfPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistID){
        try {
            return Response.ok().entity(service.getAvailableTracksForPlaylist(token, playlistID)).build();
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


}
