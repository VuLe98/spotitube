package com.spotitube;

import com.spotitube.controller.playlist.PlaylistController;
import com.spotitube.services.playlist.PlaylistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistControllerTest {

    private PlaylistController controller;
    private PlaylistServiceImpl pServiceImpl;

    private static final String testToken = "abcdef-12";

    @BeforeEach
    void setup() {
        controller = new PlaylistController();
        pServiceImpl = mock(PlaylistServiceImpl.class);
        controller.setPlaylistService(pServiceImpl);
    }

    @Test
    void doesPlaylistControllerInteractWithService(){
        when(pServiceImpl.getAllPlaylists(testToken)).thenReturn(Response.ok().build());

        Response actual = controller.getAllPlaylists(testToken);

        assertEquals(200, actual.getStatus());
    }
}