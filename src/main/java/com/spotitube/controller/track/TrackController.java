package com.spotitube.controller.track;

import com.spotitube.services.track.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    private TrackService tService;

    @Inject
    public void setTrackService(TrackService service){
        this.tService = service;
    }

    @GET
    @Produces("application/json")
    public Response getAvailableTracksOfPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistID){
        return tService.getAvailableTracksForPlaylist(token,playlistID);
    }


}
