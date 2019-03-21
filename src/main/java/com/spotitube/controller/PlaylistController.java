package com.spotitube.controller;

import com.spotitube.Playlist;
import com.spotitube.helpers.PlaylistHelper;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @Inject
    private PlaylistHelper helper;


    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public Response getAllPlaylists(@QueryParam("token") String token){

        try{
            return Response.ok().entity(helper.getAllPlaylists(token)).build();
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
            return Response.ok().entity(helper.getContentOfPlaylist(token,id)).build();
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        try{
            return Response.ok().entity(helper.deletePlaylist(token,id)).build();
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
            return Response.ok().entity(helper.addPlaylist(token,list)).build();
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
            return Response.ok().entity(helper.updatePlaylist(token,list)).build();
        }
        catch(AuthenticationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
