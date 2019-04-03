package com.spotitube;

import com.spotitube.dao.PlaylistDAO;
import com.spotitube.dao.TokenDAO;
import com.spotitube.dto.PlaylistResponse;
import com.spotitube.dto.TrackResponse;
import com.spotitube.models.PlaylistModel;
import com.spotitube.models.TrackModel;
import com.spotitube.services.playlist.PlaylistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class PlaylistServiceImplTest {
    private PlaylistServiceImpl pServiceImpl;
    private PlaylistDAO pDAOMock;
    private TokenDAO tDAOMock;

    private static final String testToken = "abcdef-12";
    private static final int testPlaylistNo = 999999999;

    private PlaylistModel testPlaylistModel = new PlaylistModel(99, "Dood metaal", true, new ArrayList<>());
    private TrackModel testTrack;

    @BeforeEach
    void setup() {
        pDAOMock = mock(PlaylistDAO.class);
        tDAOMock = mock(TokenDAO.class);
        pServiceImpl = new PlaylistServiceImpl();
        pServiceImpl.setPlaylistDAO(pDAOMock);
        pServiceImpl.setTokenDAO(tDAOMock);
        testTrack = new TrackModel();
        testTrack.setId(1);
        testTrack.setTitle("Californication");
        testTrack.setPerformer("Red Hot Chili Peppers");
        testTrack.setDuration(300);
        testTrack.setAlbum("Cali");
        testTrack.setPlaycount(3);
        testTrack.setPublicationDate("2005-01-01");
        testTrack.setDescription("Een leuk liedje");
        testTrack.setOfflineAvailable(true);
    }

    @Test
    void checkifAllPlaylistsRightToken(){
        PlaylistResponse playlistResponse = new PlaylistResponse();

        when(tDAOMock.getUserByToken(testToken)).thenReturn("Vu");

        when(pDAOMock.getAllPlaylists(testToken)).thenReturn(playlistResponse);

        Response response = pServiceImpl.getAllPlaylists(testToken);

        assertEquals(200, response.getStatus());
        assertEquals(playlistResponse, response.getEntity());
    }

    @Test
    void checkifAllPlaylistsWrongToken(){
        PlaylistResponse playlistResponse = new PlaylistResponse();

        when(pDAOMock.getAllPlaylists(any())).thenReturn(playlistResponse);

        Response response = pServiceImpl.getAllPlaylists("");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    void getContentOfPlaylistRightToken(){
        TrackResponse tResponse = new TrackResponse();

        when(tDAOMock.getUserByToken(testToken)).thenReturn("Vu");

        when(pDAOMock.getContentOfPlaylist(anyInt())).thenReturn(tResponse);

        Response response = pServiceImpl.getContentOfPlaylist(testToken, testPlaylistNo);

        assertEquals(200, response.getStatus());
        assertEquals(tResponse, response.getEntity());
    }

    @Test
    void getContentOfPlaylistWrongToken(){
        TrackResponse tResponse = new TrackResponse();

        when(pDAOMock.getContentOfPlaylist(anyInt())).thenReturn(tResponse);

        Response response = pServiceImpl.getContentOfPlaylist("", testPlaylistNo);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    void addPlaylistRightToken(){
        PlaylistResponse expected = new PlaylistResponse();
        PlaylistResponse actual = new PlaylistResponse();
        expected.addPlaylists(testPlaylistModel);

        when(tDAOMock.getUserByToken(testToken)).thenReturn("Vu");

        doAnswer(invocationOnMock -> actual.getPlaylists().add(testPlaylistModel)).
                when(pDAOMock).addPlaylist(testToken, testPlaylistModel.getName());

        pServiceImpl.addPlaylist(testToken, testPlaylistModel);

        assertEquals(expected.getPlaylists(), actual.getPlaylists());
    }

    @Test
    void addPlaylistWrongToken(){
        PlaylistResponse expected = new PlaylistResponse();
        PlaylistResponse actual = new PlaylistResponse();
        expected.addPlaylists(testPlaylistModel);

        doAnswer(invocationOnMock -> actual.getPlaylists().add(testPlaylistModel)).
                when(pDAOMock).addPlaylist(testToken, testPlaylistModel.getName());

        Response response = pServiceImpl.addPlaylist(testToken, testPlaylistModel);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());

    }

    @Test
    void updatePlaylistRightToken(){
        PlaylistModel request = new PlaylistModel();
        request.setName(testPlaylistModel.getName());
        request.setId(testPlaylistNo);

        PlaylistModel updated = new PlaylistModel();
        updated.setName(testPlaylistModel.getName());

        PlaylistModel origin = new PlaylistModel();
        origin.setName("Pop");

        when(tDAOMock.getUserByToken(testToken)).thenReturn("Vu");

        doAnswer(invocationOnMock -> {
            origin.setName(request.getName());
            return origin;
        }).when(pDAOMock).updatePlaylist(request.getName(),testPlaylistNo);

        pServiceImpl.updatePlaylist(testToken,testPlaylistNo,request);

        assertEquals(updated.getName(),origin.getName());
    }

    @Test
    void updatePlaylistWrongToken(){
        PlaylistModel request = new PlaylistModel();
        request.setName(testPlaylistModel.getName());
        request.setId(testPlaylistNo);

        PlaylistModel updated = new PlaylistModel();
        updated.setName(testPlaylistModel.getName());

        PlaylistModel origin = new PlaylistModel();
        origin.setName("Pop");

        when(tDAOMock.getUserByToken(testToken)).thenReturn("Vu");

        doAnswer(invocationOnMock -> {
            origin.setName(request.getName());
            return origin;
        }).when(pDAOMock).updatePlaylist(request.getName(),testPlaylistNo);

        Response response = pServiceImpl.updatePlaylist("",testPlaylistNo,request);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    void deletePlaylistRightToken(){
        PlaylistResponse expected = new PlaylistResponse();
        PlaylistResponse actual = new PlaylistResponse();

        PlaylistModel origin = new PlaylistModel();
        origin.setName("Pop");
        origin.setId(2);
        origin.setOwner(true);

        actual.addPlaylists(origin);

        when(tDAOMock.getUserByToken(testToken)).thenReturn("Vu");

        doAnswer(invocationOnMock -> {
            actual.getPlaylists().remove(0);
            return actual;
        }).when(pDAOMock).deletePlaylist(testPlaylistNo);

        pServiceImpl.deletePlaylist(testToken,testPlaylistNo);

        assertEquals(expected.getPlaylists(),actual.getPlaylists());
    }

    @Test
    void deletePlaylistWrongToken(){
        PlaylistResponse actual = new PlaylistResponse();

        PlaylistModel origin = new PlaylistModel();
        origin.setName("Pop");
        origin.setId(2);
        origin.setOwner(true);

        actual.addPlaylists(origin);

        when(tDAOMock.getUserByToken(testToken)).thenReturn("Vu");

        doAnswer(invocationOnMock -> {
            actual.getPlaylists().remove(0);
            return actual;
        }).when(pDAOMock).deletePlaylist(testPlaylistNo);

        Response response = pServiceImpl.deletePlaylist("",testPlaylistNo);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(),response.getStatus());
    }

    @Test
    void addTrackToPlaylistRightToken(){
        when(tDAOMock.getUserByToken(testToken)).thenReturn("Vu");

        doAnswer(invocationOnMock -> testTrack).when(pDAOMock).addTrackToPlaylist(testPlaylistNo,testTrack);

        Response response = pServiceImpl.addTrackToPlaylist(testToken,testPlaylistNo,testTrack);
        TrackModel actual = (TrackModel) response.getEntity();

        assertEquals(testTrack,actual);
    }

    @Test
    void addTrackToPlaylistWrongToken(){
        when(tDAOMock.getUserByToken(testToken)).thenReturn("Vu");

        doAnswer(invocationOnMock -> testTrack).when(pDAOMock).addTrackToPlaylist(testPlaylistNo,testTrack);

        Response response = pServiceImpl.addTrackToPlaylist("",testPlaylistNo,testTrack);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(),response.getStatus());
    }

    @Test
    void deleteTrackFromPlaylistRightToken(){
        TrackResponse expected = new TrackResponse();
        TrackResponse actual = new TrackResponse();
        List<TrackModel> tracks = new ArrayList<>();
        tracks.add(testTrack);
        actual.setTracks(tracks);
        when(tDAOMock.getUserByToken(testToken)).thenReturn("Vu");

        doAnswer(invocationOnMock -> {
            actual.getTracks().remove(0); return actual;
        }).when(pDAOMock).removeTrackOfPlaylist(testPlaylistNo,testTrack.getId());

        pServiceImpl.deleteTrackOfPlaylist(testToken,testPlaylistNo,testTrack.getId());

        assertEquals(expected.getTracks(),actual.getTracks());
    }

    @Test
    void deleteTrackFromPlaylistWrongToken(){
        TrackResponse actual = new TrackResponse();
        List<TrackModel> tracks = new ArrayList<>();
        tracks.add(testTrack);
        actual.setTracks(tracks);
        when(tDAOMock.getUserByToken(testToken)).thenReturn("Vu");

        doAnswer(invocationOnMock -> {
            actual.getTracks().remove(0); return actual;
        }).when(pDAOMock).removeTrackOfPlaylist(testPlaylistNo,testTrack.getId());

        Response trackResponse = pServiceImpl.deleteTrackOfPlaylist("",testPlaylistNo,testTrack.getId());

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(),trackResponse.getStatus());
    }
}
