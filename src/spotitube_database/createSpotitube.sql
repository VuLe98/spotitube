/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2012                    */
/* Created on:     15-3-2019 11:37:52                           */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PLAYLIST') and o.name = 'FK_PLAYLIST_USER_MET__USER')
alter table PLAYLIST
   drop constraint FK_PLAYLIST_USER_MET__USER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TRACK_IN_PLAYLIST') and o.name = 'FK_TRACK_IN_TRACK_IN__PLAYLIST')
alter table TRACK_IN_PLAYLIST
   drop constraint FK_TRACK_IN_TRACK_IN__PLAYLIST
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TRACK_IN_PLAYLIST') and o.name = 'FK_TRACK_IN_TRACK_IN__TRACK')
alter table TRACK_IN_PLAYLIST
   drop constraint FK_TRACK_IN_TRACK_IN__TRACK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PLAYLIST')
            and   name  = 'USER_MET_PLAYLIST_FK'
            and   indid > 0
            and   indid < 255)
   drop index PLAYLIST.USER_MET_PLAYLIST_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PLAYLIST')
            and   type = 'U')
   drop table PLAYLIST
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TRACK')
            and   type = 'U')
   drop table TRACK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('TRACK_IN_PLAYLIST')
            and   name  = 'TRACK_IN_PLAYLIST2_FK'
            and   indid > 0
            and   indid < 255)
   drop index TRACK_IN_PLAYLIST.TRACK_IN_PLAYLIST2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('TRACK_IN_PLAYLIST')
            and   name  = 'TRACK_IN_PLAYLIST_FK'
            and   indid > 0
            and   indid < 255)
   drop index TRACK_IN_PLAYLIST.TRACK_IN_PLAYLIST_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TRACK_IN_PLAYLIST')
            and   type = 'U')
   drop table TRACK_IN_PLAYLIST
go

if exists (select 1
            from  sysobjects
           where  id = object_id('"USER"')
            and   type = 'U')
   drop table "USER"
go

if exists(select 1 from systypes where name='DESCRIPTION')
   drop type DESCRIPTION
go

if exists(select 1 from systypes where name='DURATION')
   drop type DURATION
go

if exists(select 1 from systypes where name='ID')
   drop type ID
go

if exists(select 1 from systypes where name='LENGTH')
   drop type LENGTH
go

if exists(select 1 from systypes where name='NAME')
   drop type NAME
go

if exists(select 1 from systypes where name='OWNER')
   drop type OWNER
go

if exists(select 1 from systypes where name='O_AVAILABLE')
   drop type O_AVAILABLE
go

if exists(select 1 from systypes where name='PASSWORD')
   drop type PASSWORD
go

if exists(select 1 from systypes where name='PERFORMER')
   drop type PERFORMER
go

if exists(select 1 from systypes where name='PLAYCOUNT')
   drop type PLAYCOUNT
go

if exists(select 1 from systypes where name='PUBLICATION_DATE')
   drop type PUBLICATION_DATE
go

if exists(select 1 from systypes where name='TOKEN')
   drop type TOKEN
go

if exists(select 1 from systypes where name='USERNAME')
   drop type USERNAME
go

/*==============================================================*/
/* Domain: DESCRIPTION                                          */
/*==============================================================*/
create type DESCRIPTION
   from varchar(256)
go

/*==============================================================*/
/* Domain: DURATION                                             */
/*==============================================================*/
create type DURATION
   from int
go

/*==============================================================*/
/* Domain: ID                                                   */
/*==============================================================*/
create type ID
   from int
go

/*==============================================================*/
/* Domain: LENGTH                                               */
/*==============================================================*/
create type LENGTH
   from int
go

/*==============================================================*/
/* Domain: NAME                                                 */
/*==============================================================*/
create type NAME
   from varchar(256)
go

/*==============================================================*/
/* Domain: OWNER                                                */
/*==============================================================*/
create type OWNER
   from bit
go

/*==============================================================*/
/* Domain: O_AVAILABLE                                          */
/*==============================================================*/
create type O_AVAILABLE
   from bit
go

/*==============================================================*/
/* Domain: PASSWORD                                             */
/*==============================================================*/
create type PASSWORD
   from varchar(50)
go

/*==============================================================*/
/* Domain: PERFORMER                                            */
/*==============================================================*/
create type PERFORMER
   from varchar(50)
go

/*==============================================================*/
/* Domain: PLAYCOUNT                                            */
/*==============================================================*/
create type PLAYCOUNT
   from int
go

/*==============================================================*/
/* Domain: PUBLICATION_DATE                                     */
/*==============================================================*/
create type PUBLICATION_DATE
   from datetime
go

/*==============================================================*/
/* Domain: TOKEN                                                */
/*==============================================================*/
create type TOKEN
   from varchar(15)
go

/*==============================================================*/
/* Domain: USERNAME                                             */
/*==============================================================*/
create type USERNAME
   from varchar(25)
go

/*==============================================================*/
/* Table: PLAYLIST                                              */
/*==============================================================*/
create table PLAYLIST (
   U_NAME               USERNAME             not null,
   U_TOKEN              TOKEN                not null,
   P_ID                 ID                   not null,
   P_TRACK              NAME                 not null,
   P_NAME               NAME                 not null,
   P_OWNER              OWNER                not null,
   constraint PK_PLAYLIST primary key nonclustered (U_NAME, U_TOKEN, P_ID, P_TRACK)
)
go

/*==============================================================*/
/* Index: USER_MET_PLAYLIST_FK                                  */
/*==============================================================*/
create index USER_MET_PLAYLIST_FK on PLAYLIST (
U_NAME ASC,
U_TOKEN ASC
)
go

/*==============================================================*/
/* Table: TRACK                                                 */
/*==============================================================*/
create table TRACK (
   T_ID                 ID                   not null,
   T_TITLE              NAME                 not null,
   T_PERFORMER          PERFORMER            not null,
   T_DURATION           DURATION             not null,
   T_ALBUM              NAME                 null,
   T_PLAYCOUNT          PLAYCOUNT            null,
   T_PUBLICATIONDATE    PUBLICATION_DATE     null,
   T_DESCRIPTION        DESCRIPTION          null,
   T_OFFLINEAVAILABLE   O_AVAILABLE          not null,
   constraint PK_TRACK primary key nonclustered (T_ID)
)
go

/*==============================================================*/
/* Table: TRACK_IN_PLAYLIST                                     */
/*==============================================================*/
create table TRACK_IN_PLAYLIST (
   U_NAME               USERNAME             not null,
   U_TOKEN              TOKEN                not null,
   P_ID                 ID                   not null,
   P_TRACK              NAME                 not null,
   T_ID                 ID                   not null,
   constraint PK_TRACK_IN_PLAYLIST primary key (U_NAME, U_TOKEN, P_ID, P_TRACK, T_ID)
)
go

/*==============================================================*/
/* Index: TRACK_IN_PLAYLIST_FK                                  */
/*==============================================================*/
create index TRACK_IN_PLAYLIST_FK on TRACK_IN_PLAYLIST (
U_NAME ASC,
U_TOKEN ASC,
P_ID ASC,
P_TRACK ASC
)
go

/*==============================================================*/
/* Index: TRACK_IN_PLAYLIST2_FK                                 */
/*==============================================================*/
create index TRACK_IN_PLAYLIST2_FK on TRACK_IN_PLAYLIST (
T_ID ASC
)
go

/*==============================================================*/
/* Table: "USER"                                                */
/*==============================================================*/
create table "USER" (
   U_NAME               USERNAME             not null,
   U_TOKEN              TOKEN                not null,
   U_PASSWORD           PASSWORD             not null,
   constraint PK_USER primary key nonclustered (U_NAME, U_TOKEN)
)
go

alter table PLAYLIST
   add constraint FK_PLAYLIST_USER_MET__USER foreign key (U_NAME, U_TOKEN)
      references "USER" (U_NAME, U_TOKEN)
go

alter table TRACK_IN_PLAYLIST
   add constraint FK_TRACK_IN_TRACK_IN__PLAYLIST foreign key (U_NAME, U_TOKEN, P_ID, P_TRACK)
      references PLAYLIST (U_NAME, U_TOKEN, P_ID, P_TRACK)
go

alter table TRACK_IN_PLAYLIST
   add constraint FK_TRACK_IN_TRACK_IN__TRACK foreign key (T_ID)
      references TRACK (T_ID)
go

