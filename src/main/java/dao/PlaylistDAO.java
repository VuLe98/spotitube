package dao;

import com.spotitube.Playlist;
import database.DatabaseRequest;
import dto.PlaylistResponse;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistDAO {

    @Inject
    private DatabaseRequest request;
    private Playlist playlist;

    public PlaylistResponse getAllPlaylists(){
        PlaylistResponse response = new PlaylistResponse();
        int playlistLength = 0;

        try(
            Connection connection = request.connectToDB();
            PreparedStatement preparestatement = connection.prepareStatement("SELECT * FROM PLAYLIST");
        ) {
            ResultSet resultSet = preparestatement.executeQuery();

            while (resultSet.next()) {
                int playlistID = resultSet.getInt("P_ID");
                String playlistName = resultSet.getString("P_NAME");
                Boolean owner = resultSet.getBoolean("P_OWNER");

                response.getPlaylists().add(new Playlist(playlistID,playlistName,owner,new ArrayList<>()));
                playlistLength += getLengthOfPlaylist(playlistID);

            }
            response.setLength(playlistLength);
        } catch(SQLException e){
            throw new RuntimeException();
        }
        return response;
    }

    public int getLengthOfPlaylist(int playlistID){
        int playlistlength = 0;

        try(
           Connection connection = request.connectToDB();
           PreparedStatement preparestatement = connection.prepareStatement("SELECT SUM(T_DURATION) AS playlist_length FROM TRACK_IN_PLAYLIST INNER JOIN TRACK ON TRACK_IN_PLAYLIST.T_ID = TRACK.T_ID");
        ){
            preparestatement.setInt(1,playlistID);
            ResultSet resultSet = preparestatement.executeQuery();

            while(resultSet.next()){
                playlistlength += resultSet.getInt("playlist_length");
            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return playlistlength;
    }




}
