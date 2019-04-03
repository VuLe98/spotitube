package com.spotitube;

import com.spotitube.dao.TokenDAO;
import com.spotitube.dao.TrackDAO;
import com.spotitube.dto.TrackResponse;
import com.spotitube.models.TrackModel;
import com.spotitube.services.track.TrackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceImplTest {

    private TrackServiceImpl tServiceImpl;
    private TrackDAO tDAOMock;
    private TokenDAO tDAO;
    private TrackResponse response;
    private static final int playlistID = 1;
    private static final String testToken = "1234-ABCD";
    private TrackModel testTrack;

    @BeforeEach
    void setup(){
        tDAOMock = mock(TrackDAO.class);
        tDAO = mock(TokenDAO.class);
        response = new TrackResponse();
        tServiceImpl = new TrackServiceImpl();
        tServiceImpl.setTrackDAO(tDAOMock);
        tServiceImpl.setTokenDAO(tDAO);
        testTrack = new TrackModel(1,"Californication","Red Hot Chili Peppers",300,"Cali",3,"2005-01-01","Een leuk liedje",true);
    }

    @Test
    void getTracksOfPlaylistRightToken(){

        when(tDAO.getUserByToken(testToken)).thenReturn("Vu");

        TrackResponse expected = new TrackResponse();
        List<TrackModel> tracks = new ArrayList<>();
        tracks.add(testTrack);
        expected.setTracks(tracks);

        when(tDAOMock.getAvailableTracksOfPlaylist(playlistID)).thenReturn(expected);

        Response response = tServiceImpl.getAvailableTracksForPlaylist(testToken,playlistID);
        TrackResponse actual = (TrackResponse) response.getEntity();

        assertEquals(expected,actual);
    }

    @Test
    void getTracksOfPlaylistWrongToken(){

        when(tDAO.getUserByToken(testToken)).thenReturn("Vu");

        TrackResponse expected = new TrackResponse();
        List<TrackModel> tracks = new ArrayList<>();
        tracks.add(testTrack);
        expected.setTracks(tracks);

        when(tDAOMock.getAvailableTracksOfPlaylist(anyInt())).thenReturn(response);

        Response response = tServiceImpl.getAvailableTracksForPlaylist("",playlistID);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(),response.getStatus());
    }
}
