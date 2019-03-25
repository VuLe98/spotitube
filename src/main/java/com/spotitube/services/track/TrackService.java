package com.spotitube.services.track;

import com.spotitube.dto.TrackResponse;

import javax.naming.AuthenticationException;

public interface TrackService {
    TrackResponse getAvailableTracksForPlaylist(String token, int playlistID) throws AuthenticationException;
}
