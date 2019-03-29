package com.spotitube.controller.playlist;

import com.spotitube.entities.Playlist;
import com.spotitube.entities.Track;
import com.spotitube.services.playlist.PlaylistService;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
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

        try{
            return Response.ok().entity(pService.getAllPlaylists(token)).build();
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path("{id}/tracks")
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public Response getContentOfPlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        try {
            return Response.ok().entity(pService.getContentOfPlaylist(token,id)).build();
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path("{id}")
    @DELETE
    @Consumes("application/json")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        try{
            return Response.ok().entity(pService.deletePlaylist(token,id)).build();
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path("{id}")
    @PUT
    @Consumes("application/json")
    public Response updatePlaylist(@QueryParam("token") String token, @PathParam("id") int id, Playlist list){
        try{
            return Response.ok().entity(pService.updatePlaylist(token,list)).build();
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPlaylist(@QueryParam("token") String token, Playlist list){
        try{
            return Response.ok().entity(pService.addPlaylist(token,list)).build();
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


    @Path("{playlistID}/tracks/{trackID}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteTrackInPlaylist(@QueryParam("token") String token, @PathParam("playlistID") int playlistID, @PathParam("trackID") int trackID){
        try{
            return Response.ok().entity(pService.deleteTrackOfPlaylist(token,playlistID,trackID)).build();
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path("{playlistID}/tracks")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("playlistID") int playlistID, Track track){
        try{
            return Response.ok().entity(pService.addTrackToPlaylist(token,playlistID,track)).build();
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
