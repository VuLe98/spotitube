package com.spotitube;

import com.spotitube.controller.PlaylistController;
import com.spotitube.models.PlaylistModel;
import com.spotitube.models.TrackModel;
import com.spotitube.services.playlist.PlaylistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;

public class PlaylistControllerTest {

    private PlaylistController controller;
    private PlaylistServiceImpl pServiceImpl;
    private PlaylistModel pModel;
    private TrackModel tModel;

    private static final String testToken = "abcdef-12";
    private static final int testPlaylistID = 999999;

    @BeforeEach
    void setup() {
        controller = new PlaylistController();
        pServiceImpl = mock(PlaylistServiceImpl.class);
        controller.setPlaylistService(pServiceImpl);
        pModel = new PlaylistModel();
        pModel.setName("EDM");
        pModel.setId(1);
        pModel.setOwner(true);
        tModel = new TrackModel();
        tModel.setId(1);
        tModel.setTitle("Champions");
        tModel.setPerformer("Queen");
        tModel.setDuration(200);
        tModel.setAlbum("End of the world");
        tModel.setPlaycount(0);
        tModel.setOfflineAvailable(true);
    }

    @Test
    void doesPlaylistControllerInteractWithServiceGetPlaylists(){
        when(pServiceImpl.getAllPlaylists(testToken)).thenReturn(Response.ok().build());

        controller.getAllPlaylists(testToken);

        verify(pServiceImpl).getAllPlaylists(testToken);
    }

    @Test
    void doesPlaylistControllerInteractWithServiceAddPlaylist() {
        when(pServiceImpl.addPlaylist(testToken, pModel)).thenReturn(Response.ok().build());

        controller.addPlaylist(testToken, pModel);

        verify(pServiceImpl).addPlaylist(testToken, pModel);
    }

    @Test
    void doesPlaylistControllerInteractWithServiceUpdatePlaylist() {
        when(pServiceImpl.updatePlaylist(testToken, testPlaylistID,pModel)).thenReturn(Response.ok().build());

        controller.updatePlaylist(testToken, testPlaylistID, pModel);

        verify(pServiceImpl).updatePlaylist(testToken, testPlaylistID, pModel);
    }

    @Test
    void doesPlaylistControllerInteractWithServiceDeletePlaylist() {
        when(pServiceImpl.deletePlaylist(testToken, testPlaylistID)).thenReturn(Response.ok().build());

        controller.deletePlaylist(testToken, testPlaylistID);

        verify(pServiceImpl).deletePlaylist(testToken, testPlaylistID);
    }

    @Test
    void doesPlaylistControllerInteractWithServiceGetContentOfPlaylist(){
        when(pServiceImpl.getContentOfPlaylist(testToken, testPlaylistID)).thenReturn(Response.ok().build());

        controller.getContentOfPlaylist(testToken, testPlaylistID);

        verify(pServiceImpl).getContentOfPlaylist(testToken, testPlaylistID);
    }

    @Test
    void doesPlaylistControllerInteractWithServiceAddTrackToPlaylist(){
        when(pServiceImpl.addTrackToPlaylist(testToken, testPlaylistID,tModel)).thenReturn(Response.ok().build());

        controller.addTrackToPlaylist(testToken, testPlaylistID, tModel);

        verify(pServiceImpl).addTrackToPlaylist(testToken, testPlaylistID,tModel);
    }


    @Test
    void doesPlaylistControllerInteractWithServiceDeleteTrackFromPlaylist(){
        when(pServiceImpl.deleteTrackOfPlaylist(testToken, testPlaylistID,tModel.getId())).thenReturn(Response.ok().build());

        controller.deleteTrackInPlaylist(testToken, testPlaylistID,tModel.getId());

        verify(pServiceImpl).deleteTrackOfPlaylist(testToken, testPlaylistID,tModel.getId());
    }


}