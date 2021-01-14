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
    
INSERT INTO Movie (title, note, rating, viewDate, toWatch, watched) VALUES
    ("Clue", "funny", 5 , "2015-02-01", false, true),
    ("The Aristocats", "nostalgic", 3 , "2019-03-01", false, true),
    ("Joker", "controversial", 4 , "2020-05-01", true, false);
    
INSERT INTO TVShow (Title, Note, Rating, viewDate, ToWatch, Watched) VALUES
	("Firefly","short lived", 7, "2015-06-03",false,true),
    ("BBC Sherlock","mini series", 5, "2014-05-03",false,true);
--    ("Pushing Daisies",null, null, null,true,false);
    
-- SELECT * FROM TVShow;
INSERT INTO Season (Title, Note, Rating, viewDate, ToWatch, Watched, tvshow_Id) VALUES
	("Series 1", "the best", 7, "2010-11-01", false, true, 2),
    ("Series 2", "still good", 6, "2012-03-01", false, true, 2),
    ("Series 3", "ehhhhh", 4, "2014-4-09", false, true, 2),
    ("Series 4", "didn't watch", null, null, false, true, 2);
-- SELECT * FROM Season;
INSERT INTO Episode (Title, Note, Rating, viewDate, ToWatch, Watched, season_Id) VALUES
	("A Study in Pink",null, 7, "2010-11-01", false, true, 1),
    ("The Blind Banker",null, 7, "2010-11-01", false, true, 1),
    ("The Great Game",null, 7, "2010-11-01", false, true, 1);
INSERT INTO Episode (Title, Note, Rating, viewDate, ToWatch, Watched, season_Id) VALUES
	("A Scandal in Belgravia",null, 6, "2012-03-01", false, true, 2),
    ("The Hounds of Baskerville",null, 6, "2012-03-01", false, true, 2),
    ("The Reichenbach Fall",null, 6, "2012-03-01", false, true, 2);
INSERT INTO Episode (Title, Note, Rating, viewDate, ToWatch, Watched, season_Id) VALUES
	("The Empty Hearse",null, 4, "2014-4-09", false, true, 3),
    ("The Sign of Three",null, 4, "2014-4-09", false, true, 3),
    ("His Last Vow",null, 4, "2014-4-09", false, true, 3),
    ("The Abominable Bridge",null, 4, "2012-03-01", false, true, 3);
INSERT INTO Episode (Title, Note, Rating, viewDate, ToWatch, Watched, season_Id) VALUES
	("The Six Thatchers",null, null, null, false, false, 4),
    ("The Lying Detective",null, null, null, false, false, 4),
    ("The Final Problem",null, null, null, false, false, 4);

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

