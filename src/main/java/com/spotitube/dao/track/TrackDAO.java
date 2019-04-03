package com.spotitube.dao.track;

import com.spotitube.models.TrackModel;
import com.spotitube.dao.ConnectionFactory;
import com.spotitube.dto.TrackResponse;

import javax.inject.Inject;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrackDAO {

    @Inject
    private ConnectionFactory request;

    public TrackResponse getAvailableTracksOfPlaylist(int playlistID){
        TrackResponse response = new TrackResponse();
        ResultSet resultSet = null;
        try{
            Connection connection = request.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT T.*,T_OFFLINEAVAILABLE,T_ALBUM, T_PUBLICATIONDATE, T_DESCRIPTION  FROM TRACK T LEFT JOIN TRACK_IN_PLAYLIST TP ON T.T_ID = TP.T_ID WHERE T.T_ID NOT IN (SELECT T_ID FROM TRACK_IN_PLAYLIST WHERE P_ID = ?)");

            preparedStatement.setInt(1,playlistID);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int trackID = resultSet.getInt("T_ID");
                String trackTitle = resultSet.getString("T_TITLE");
                String trackPerformer = resultSet.getString("T_PERFORMER");
                int trackDuration = resultSet.getInt("T_DURATION");
                String trackAlbum = resultSet.getString("T_ALBUM");
                int trackPlayCount = resultSet.getInt("T_PLAYCOUNT");
                String trackDate = null;
                if(!(null == resultSet.getString("T_PUBLICATIONDATE"))){
                   SimpleDateFormat dbDate = new SimpleDateFormat("yyyy-MM-dd");
                   Date date = dbDate.parse(resultSet.getString("T_PUBLICATIONDATE"));
                   SimpleDateFormat newDate = new SimpleDateFormat("dd-MM-yyyy");

                   trackDate = newDate.format(date);
                }
                String trackDescription = resultSet.getString("T_DESCRIPTION");
                boolean trackOfflineAvailable = resultSet.getBoolean("T_OFFLINEAVAILABLE");

                response.getTracks().add(new TrackModel(trackID,trackTitle,trackPerformer,trackDuration,trackAlbum,trackPlayCount,trackDate,trackDescription,trackOfflineAvailable));
            }
        }
        catch(SQLException e){
            System.out.println("Error" + e);
        }
        catch(ParseException i){
            System.out.println("Hoi" + i);
        }
        return response;
    }



}
