DROP DATABASE IF EXISTS MovieTVShowTestDB;
CREATE DATABASE MovieTVShowTestDB;
USE MovieTVShowTestDB;

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