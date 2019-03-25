package com.spotitube.services.playlist;

import com.spotitube.dto.PlaylistResponse;
import com.spotitube.dto.TrackResponse;
import com.spotitube.entities.Playlist;
import com.spotitube.entities.Track;

import javax.naming.AuthenticationException;

public interface PlaylistService {
    PlaylistResponse getAllPlaylists(String token) throws AuthenticationException;
    TrackResponse getContentOfPlaylist(String token, int playlistID) throws AuthenticationException;
    PlaylistResponse addPlaylist(String token, Playlist list) throws AuthenticationException;
    PlaylistResponse updatePlaylist(String token, Playlist list) throws AuthenticationException;
    PlaylistResponse deletePlaylist(String token, int playlistID) throws AuthenticationException;
    TrackResponse deleteTrackOfPlaylist(String token, int playlistID, int trackID) throws AuthenticationException;
    TrackResponse addTrackToPlaylist(String token, int playlistID, Track track) throws AuthenticationException;
}
