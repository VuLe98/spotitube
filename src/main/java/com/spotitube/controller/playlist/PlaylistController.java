package com.spotitube.controller.playlist;

import com.spotitube.models.PlaylistModel;
import com.spotitube.models.TrackModel;
import com.spotitube.services.playlist.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    private PlaylistService pService;

    @Inject
    public void setPlaylistService(PlaylistService service){
        this.pService = service;
    }

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public Response getAllPlaylists(@QueryParam("token") String token){
        return pService.getAllPlaylists(token);
    }

    @Path("{id}/tracks")
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public Response getContentOfPlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        return pService.getContentOfPlaylist(token, id);
    }

    @Path("{id}")
    @DELETE
    @Consumes("application/json")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        return pService.deletePlaylist(token, id);
    }

    @Path("{id}")
    @PUT
    @Consumes("application/json")
    public Response updatePlaylist(@QueryParam("token") String token, @PathParam("id") int id, PlaylistModel list){
        return pService.updatePlaylist(token,list);

    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPlaylist(@QueryParam("token") String token, PlaylistModel list){
        return pService.addPlaylist(token, list);
    }

    @Path("{playlistID}/tracks/{trackID}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteTrackInPlaylist(@QueryParam("token") String token, @PathParam("playlistID") int playlistID, @PathParam("trackID") int trackID){
        return pService.deleteTrackOfPlaylist(token, playlistID, trackID);
    }

    @Path("{playlistID}/tracks")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("playlistID") int playlistID, TrackModel trackModel){
        return pService.addTrackToPlaylist(token, playlistID, trackModel);
    }

}
