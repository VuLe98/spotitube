package com.spotitube;

import com.spotitube.controller.TrackController;
import com.spotitube.services.track.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;

public class TrackControllerTest {

    private TrackService tService;
    private TrackController controller;

    private static final int playlistID = 1;
    private static final String testToken = "1234-ABCD";

    @BeforeEach
    void setup(){
        controller = new TrackController();
        tService = mock(TrackService.class);
        controller.setTrackService(tService);
    }

    @Test
    void doesControllerInteractWithService(){
        when(tService.getAvailableTracksForPlaylist(testToken,playlistID)).thenReturn(Response.ok().build());

        controller.getAvailableTracksOfPlaylist(testToken,playlistID);

        verify(tService).getAvailableTracksForPlaylist(testToken,playlistID);
    }
}
