use Spotitube_Database

/************************************************Create test user********************************************************/

INSERT INTO [USER] VALUES ('Vu', 'vu1998le12', 'Vu Le');

/************************************************************************************************************************/

/************************************************Create test token*******************************************************/

INSERT INTO TOKEN VALUES('Vu','51246-kash-e7r','2019-3-18');

/************************************************************************************************************************/

/************************************************Create test tracks******************************************************/

INSERT INTO TRACK (T_ID,T_TITLE,T_PERFORMER,T_DURATION,T_ALBUM,T_OFFLINEAVAILABLE) 
		VALUES (1, 'Song for someone', 'The Frames', 350, 'The cost', 0);
INSERT INTO TRACK (T_ID,T_TITLE,T_PERFORMER,T_DURATION,T_PLAYCOUNT,T_PUBLICATIONDATE,T_DESCRIPTION,T_OFFLINEAVAILABLE) 
		VALUES (2, 'The cost', 'The Frames', 423, 37, '2005-01-10','Title song from the Album The Cost',1);

/************************************************************************************************************************/

/************************************************Create playlists********************************************************/

INSERT INTO PLAYLIST VALUES ('Vu',1,'Song for someone','Death metal',1); 
INSERT INTO PLAYLIST VALUES ('Vu',1,'The cost', 'Death metal',1); 


/************************************************Create tracks in playlist***********************************************/

INSERT INTO TRACK_IN_PLAYLIST VALUES('Vu', 1, 'Song for someone', 1);

/************************************************************************************************************************/
