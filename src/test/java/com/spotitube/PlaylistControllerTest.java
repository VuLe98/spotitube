package com.spotitube;

import com.spotitube.controller.playlist.PlaylistController;
import com.spotitube.dto.PlaylistResponse;
import com.spotitube.entities.Playlist;
import com.spotitube.services.playlist.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistControllerTest {

    private PlaylistService pService;
    private PlaylistController controller;

    private static final String testToken = "abcdef-12";
    private static final int testPlaylist = 999999999;

    @BeforeEach
    void setup(){
        controller = new PlaylistController();
        pService = mock(PlaylistService.class);
    }

    @Test
    void checkifAllPlaylistsReturn() throws AuthenticationException {
        PlaylistResponse playlistResponse = new PlaylistResponse();

        controller.setPlaylistService(pService);
        when(pService.getAllPlaylists(any())).thenReturn(playlistResponse);

        Response response = controller.getAllPlaylists(testToken);

        assertEquals(200,response.getStatus());
        assertEquals(playlistResponse,response.getEntity());
    }

    @Test
    void addPlaylistTest() throws AuthenticationException{
        PlaylistResponse playlistResponse = new PlaylistResponse();

        controller.setPlaylistService(pService);
        when(pService.addPlaylist(any(),any(Playlist.class))).thenReturn(playlistResponse);

        Response response = controller.addPlaylist(testToken,new Playlist());

        assertEquals(200,response.getStatus());
        assertEquals(playlistResponse,response.getEntity());
    }

    @Test
    void updatePlaylistTest() throws AuthenticationException{
        PlaylistResponse playlistResponse = new PlaylistResponse();

        controller.setPlaylistService(pService);
        when(pService.updatePlaylist(any(),any(Playlist.class))).thenReturn(playlistResponse);

        Response response = controller.updatePlaylist(testToken,testPlaylist,new Playlist());

        assertEquals(200,response.getStatus());
        assertEquals(playlistResponse,response.getEntity());
    }

    @Test
    void deletePlaylistTest() throws AuthenticationException{
        PlaylistResponse playlistResponse = new PlaylistResponse();

        controller.setPlaylistService(pService);
        when(pService.deletePlaylist(any(),anyInt())).thenReturn(playlistResponse);

        Response response = controller.deletePlaylist(testToken,testPlaylist);

        assertEquals(200,response.getStatus());
        assertEquals(playlistResponse,response.getEntity());
    }

}
