use Spotitube_Database

/************************************************Create test user********************************************************/

INSERT INTO [USER] VALUES ('Vu', 'vu1998le12', 'Vu Le');
INSERT INTO [USER] VALUES('Test', 'test123', 'Test User');

/************************************************************************************************************************/

/************************************************Create test tracks******************************************************/

INSERT INTO TRACK (T_ID,T_TITLE,T_PERFORMER,T_DURATION) 
		VALUES (1, 'Song for someone', 'The Frames', 350);
INSERT INTO SONG VALUES (1,'The cost');
INSERT INTO TRACK (T_ID,T_TITLE,T_PERFORMER,T_DURATION,T_PLAYCOUNT) 
		VALUES (2, 'The cost', 'The Frames', 423, 37);
INSERT INTO VIDEO VALUES (2,'2005-01-10','Title song from the Album The Cost')
INSERT INTO TRACK (T_ID,T_TITLE,T_PERFORMER,T_DURATION) 
		VALUES (3, 'California Gurls', 'Katy Perry', 234);
INSERT INTO SONG VALUES(3,'Teenage Dream');

/************************************************************************************************************************/

/************************************************Create playlists********************************************************/

INSERT INTO PLAYLIST VALUES (1,'Vu','Death metal'); 
INSERT INTO PLAYLIST VALUES (2,'Test', 'Pop'); 
INSERT INTO PLAYLIST VALUES (3,'Test', 'Death metal');


/************************************************Create tracks in playlist***********************************************/ 

INSERT INTO TRACK_IN_PLAYLIST VALUES(1,  1, 0);
INSERT INTO TRACK_IN_PLAYLIST VALUES(2,  3, 0);
INSERT INTO TRACK_IN_PLAYLIST VALUES(3,  1, 0);
INSERT INTO TRACK_IN_PLAYLIST VALUES(3,  2, 1);
/************************************************************************************************************************/