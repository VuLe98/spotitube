package com.spotitube.services.playlist;

import com.spotitube.models.PlaylistModel;
import com.spotitube.models.TrackModel;

import javax.ws.rs.core.Response;

public interface PlaylistService {
    Response getAllPlaylists(String token);
    Response getContentOfPlaylist(String token, int playlistID);
    Response addPlaylist(String token, PlaylistModel list);
    Response updatePlaylist(String token, int playlistID, PlaylistModel list);
    Response deletePlaylist(String token, int playlistID);
    Response deleteTrackOfPlaylist(String token, int playlistID, int trackID);
    Response addTrackToPlaylist(String token, int playlistID, TrackModel trackModel);
}
