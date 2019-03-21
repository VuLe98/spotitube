package com.spotitube.helpers;

import com.spotitube.Playlist;
import com.spotitube.dto.PlaylistResponse;
import com.spotitube.dto.TrackResponse;

import javax.naming.AuthenticationException;

public interface PlaylistHelper {
    PlaylistResponse getAllPlaylists(String token) throws AuthenticationException;
    TrackResponse getContentOfPlaylist(String token, int playlistID) throws AuthenticationException;
    PlaylistResponse addPlaylist(String token, Playlist list) throws AuthenticationException;
    PlaylistResponse updatePlaylist(String token, Playlist list) throws AuthenticationException;
    PlaylistResponse deletePlaylist(String token, int playlistID) throws AuthenticationException;
}
