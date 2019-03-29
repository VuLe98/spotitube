package com.spotitube;

import com.spotitube.controller.track.TrackController;
import com.spotitube.dao.track.TrackDAO;
import com.spotitube.services.track.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class TrackControllerTest {

    private TrackService tService;
    private TrackController controller;
    private TrackDAO tDAO;

    @BeforeEach
    void setup(){
        controller = new TrackController();
        tDAO = mock(TrackDAO.class);
        tService = mock(TrackService.class);
    }

    @Test
    void getTracksOfPlaylistTest(){

    }
}
