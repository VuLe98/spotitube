package com.spotitube.services.track;

import javax.ws.rs.core.Response;

public interface TrackService {
    Response getAvailableTracksForPlaylist(String token, int playlistID);
}
