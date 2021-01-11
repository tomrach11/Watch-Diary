DROP DATABASE IF EXISTS MovieTVShowDB;
CREATE DATABASE MovieTVShowDB;
USE MovieTVShowDB;

CREATE TABLE Movie (
	movie_Id INT PRIMARY KEY AUTO_INCREMENT,
    Title varchar(20),
    Note varchar(225),
    Rating tinyint,
    ViewDate date,
    Watched BOOLEAN DEFAULT false,
    ToWatch BOOLEAN DEFAULT false
    );
    
CREATE TABLE TVShow (
	tvshow_Id INT PRIMARY KEY AUTO_INCREMENT,
    Title varchar(20),
    Note varchar(225),
    Rating tinyint,
    ViewDate date,
    Watched BOOLEAN DEFAULT false,
    ToWatch BOOLEAN DEFAULT false
    ); 
CREATE TABLE Season (
	season_Id INT PRIMARY KEY AUTO_INCREMENT,
    Title varchar(20),
    Note varchar(225),
    Rating tinyint,
    ViewDate date,
    Watched BOOLEAN DEFAULT false,
    ToWatch BOOLEAN DEFAULT false,
    tvshow_Id int,
    foreign key fk_Season_TVShow (tvshow_Id)
		references TVShow(tvshow_Id)
    );
CREATE TABLE Episode (
	episode_Id INT PRIMARY KEY AUTO_INCREMENT,
    Title varchar(20),
    Note varchar(225),
    Rating tinyint,
    ViewDate date,
    Watched BOOLEAN DEFAULT false,
    ToWatch BOOLEAN DEFAULT false,
    season_Id int,
    foreign key fk_Episode_Season (season_Id)
		references Season(season_Id)
    );    
    
-- USE MovieTVShowDB;
-- SET SQL_SAFE_UPDATES=0;

-- INSERT INTO TVShow(tvshow_Id, name,watched,towatch) VALUES (1,"NCIS", true, false);
-- INSERT INTO Season(name,watched,towatch,tvshow_Id) VALUES ("1",true,false,1);
-- INSERT INTO Season(name,watched,towatch,tvshow_Id) VALUES ("2",true,false,1);



-- CREATE TABLE Temp AS (SELECT COUNT(season_Id) FROM Season 
-- INNER JOIN TVShow ON TVShow.tvshow_Id=Season.tvshow_Id 
-- WHERE TVShow.name="NCIS");
-- UPDATE TVShow SET numSeasons=(SELECT * FROM Temp) WHERE name="NCIS";
-- DROP TABLE Temp;

-- -- UPDATE TVShow 
-- -- INNER JOIN Season ON TVShow.tvshow_Id=Season.tvshow_Id 
-- -- SET numSeasons=Count(season_ID)
-- -- WHERE TVShow.name="NCIS";

-- INSERT INTO Episode(name,watched,towatch,season_Id) VALUES ("ep1",true,false,1);
-- INSERT INTO Episode(name,watched,towatch,season_Id) VALUES ("ep2",true,false,1);
-- INSERT INTO Episode(name,watched,towatch,season_Id) VALUES ("ep3",true,false,1);

-- CREATE TABLE Temp AS (SELECT COUNT(episode_ID) FROM Episode 
-- INNER JOIN Season ON Season.season_Id=Episode.season_Id
-- WHERE Season.season_Id=1);
-- UPDATE Season SET numEpisodes=(SELECT * FROM Temp) WHERE season_Id=1;
-- DROP TABLE Temp;
-- -- UPDATE Season
-- -- INNER JOIN Episode ON Season.season_Id=Episode.season_Id 
-- -- SET numSeasons=Count(season_ID) 
-- -- WHERE Season.season_ID=1;

-- SET SQL_SAFE_UPDATES=1;
-- SELECT * FROM TVShow;
-- SELECT * FROM Season;
-- -- SELECT numEpisodes FROM Season WHERE season_Id=1;

