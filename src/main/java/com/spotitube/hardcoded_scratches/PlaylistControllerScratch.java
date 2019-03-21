package com.spotitube.hardcoded_scratches;

public class PlaylistControllerScratch {


    //    PlaylistResponse response = new PlaylistResponse();

//    private Playlist deathmetal = new Playlist(1, "Death metal", true, new ArrayList<>());
//    private Playlist pop = new Playlist(2, "Pop", false, new ArrayList<>());




    //addPlaylists//

    //        response.addPlaylists(deathmetal);
//        response.addPlaylists(pop);
//        response.setLength(123445);


//    @Inject
//    public void setPlaylistHelper(PlaylistHelper helper){
//        this.helper = helper;
//    }

//    @Path("/{id}/tracks")
//    @GET
//    @Produces("application/json")
//    @Consumes("application/json")
//    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") int id){
//
//            Playlist playlist;
//
//            ArrayList<Track> deathTracks = new ArrayList<>();
//            ArrayList<Track> popTracks = new ArrayList<>();
//            pop.setTracks(popTracks);
//
//            Track track1 = new Track();
//            track1.setId(1);
//            track1.setTitle("Song for someone");
//            track1.setPerformer("The Frames");
//            track1.setDuration(350);
//            track1.setAlbum("The cost");
//            track1.setOfflineAvailable(false);
//
//            Track track2 = new Track();
//            track2.setId(2);
//            track2.setTitle("The cost");
//            track2.setPerformer("The Frames");
//            track2.setDuration(423);
//            track2.setPlaycount(37);
//            track2.setPublicationDate(LocalDate.parse("2005-01-10"));
//            track2.setDescription("Title song from the Album The Cost");
//            track2.setOfflineAvailable(true);
//
//            deathTracks.add(track1);
//            deathTracks.add(track2);
//
//            deathmetal.setTracks(deathTracks);
//
//            switch(id){
//                case 1:
//                    playlist = deathmetal;
//                    break;
//                case 2:
//                    playlist = pop;
//                    break;
//                default: playlist = deathmetal;
//            }
//
//            TrackResponse response = new TrackResponse();
//            response.setTracks(playlist.getTracks());
//
//
//            return Response.ok().entity(response).build();
//    }
}
