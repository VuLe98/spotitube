package com.spotitube.dao.playlist;

import com.spotitube.dao.login.LoginDAO;
import com.spotitube.entities.Playlist;
import com.spotitube.entities.Track;
import com.spotitube.database.ConnectionFactory;
import com.spotitube.dto.PlaylistResponse;
import com.spotitube.dto.TrackResponse;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlaylistDAO {

    @Inject
    private ConnectionFactory request;

    @Inject
    private LoginDAO dao;

    public PlaylistResponse getAllPlaylists(String token){

        PlaylistResponse response = new PlaylistResponse();
        int playlistLength = 0;

        try{
            Connection connection = request.getConnection();
            PreparedStatement preparestatement = connection.prepareStatement("SELECT DISTINCT P.U_NAME, P_ID, P_NAME FROM PLAYLIST P INNER JOIN [USER] U ON P.U_NAME = U.U_NAME INNER JOIN [TOKEN] T ON U.U_NAME = T.U_NAME WHERE T.U_TOKEN = ?");
            preparestatement.setString(1,token);
            ResultSet resultSet = preparestatement.executeQuery();

            while (resultSet.next()) {
                Playlist list = new Playlist();
                int playlistID = resultSet.getInt("P_ID");
                String playlistName = resultSet.getString("P_NAME");
                String playlistUser = resultSet.getString("U_NAME");
                list.setId(playlistID);
                list.setName(playlistName);
                if(dao.getUserByToken(token).equals(playlistUser)){
                    list.setOwner(true);
                }
                else{
                    list.setOwner(false);
                }
                list.setTracks(new ArrayList<>());
                response.getPlaylists().add(list);
                playlistLength += getLengthOfPlaylist(playlistID);

            }
            response.setLength(playlistLength);
        } catch(SQLException e){
            throw new RuntimeException();
        }
        return response;
    }

    private int getLengthOfPlaylist(int playlistID){
        int playlistlength = 0;

        try(
           Connection connection = request.getConnection();
           PreparedStatement preparestatement = connection.prepareStatement("SELECT SUM(T_DURATION) AS playlist_length FROM TRACK_IN_PLAYLIST INNER JOIN TRACK ON TRACK_IN_PLAYLIST.T_ID = TRACK.T_ID WHERE P_ID = ?")
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


    public TrackResponse getContentOfPlaylist(int playlistID){
        TrackResponse response = new TrackResponse();
        ResultSet resultSet = null;
        try{
            Connection connection = request.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT T.*, T_OFFLINEAVAILABLE FROM TRACK_IN_PLAYLIST TP INNER JOIN TRACK T ON TP.T_ID = T.T_ID WHERE P_ID = ?");
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

                response.getTracks().add(new Track(trackID,trackTitle,trackPerformer,trackDuration,trackAlbum,trackPlayCount,trackDate,trackDescription,trackOfflineAvailable));
            }
        }
        catch(SQLException e){
            System.out.println("getContentOfPlaylist fault" + e);
        }
        catch(ParseException i){
            System.out.println("getContentOfPlaylist parse fault" + i);
        }
        return response;
    }

    public void addPlaylist(String token, String playlistName){
        try{
            Connection connection = request.getConnection();
            PreparedStatement st = connection.prepareStatement("INSERT INTO PLAYLIST VALUES (?,?,?)");

            int newID = getMaxIDPlaylist() + 1;
            String getUser = dao.getUserByToken(token);

            st.setInt(1, newID);
            st.setString(2,getUser);
            st.setString(3,playlistName);

            st.execute();
        }
        catch(SQLException e){
            System.out.println("addPlaylist" + e);
        }
    }

    public void updatePlaylist(String token, String newPlaylistName, int playListID){
        try{
            Connection connection = request.getConnection();
            PreparedStatement st = connection.prepareStatement("UPDATE PLAYLIST SET P_NAME = ? WHERE P_ID = ? AND U_NAME = ?");

            String userName = dao.getUserByToken(token);

            st.setString(1,newPlaylistName);
            st.setInt(2,playListID);
            st.setString(3,userName);

            st.execute();
        }
        catch(SQLException e){
            System.out.println("updatePlaylist fault" + e);
        }
    }

    public void deletePlaylist(int playlistID){
        try{
            Connection connection = request.getConnection();
            PreparedStatement st = connection.prepareStatement("DELETE FROM PLAYLIST WHERE P_ID = ?");

            st.setInt(1,playlistID);

            st.execute();
        }
        catch(SQLException e){
            System.out.println("deletePlaylist fault" + e);
        }
    }

    private int getMaxIDPlaylist(){
        ResultSet set = null;
        int maxPlaylistID = 0;
        try{
            Connection connection = request.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT max(P_ID) as maxID FROM PLAYLIST");
            set = preparedStatement.executeQuery();

            if(set.next()){
                maxPlaylistID += set.getInt("maxID");
            }
        }
        catch(SQLException e){
            System.out.println("getMaxID fault" + e);
        }
        return maxPlaylistID;
    }

    public void removeTrackOfPlaylist(int playlistID, int trackID){
        try{
            Connection connection = request.getConnection();
            PreparedStatement st = connection.prepareStatement("DELETE FROM TRACK_IN_PLAYLIST WHERE P_ID = ? AND T_ID = ?");

            st.setInt(1,playlistID);
            st.setInt(2,trackID);

            st.execute();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void addTrackToPlaylist(int playlistID, int trackID, boolean offlineAvailable){
        try{
            Connection connection = request.getConnection();
            PreparedStatement st = connection.prepareStatement("INSERT INTO TRACK_IN_PLAYLIST VALUES (?,?,?)");

            st.setInt(1,playlistID);
            st.setInt(2,trackID);
            st.setBoolean(3,offlineAvailable);

            st.execute();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }







}
