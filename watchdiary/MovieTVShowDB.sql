DROP DATABASE IF EXISTS MovieTVShowDB;
CREATE DATABASE MovieTVShowDB;
USE MovieTVShowDB;

CREATE TABLE Movie (
	movie_Id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(20),
    watched BOOLEAN DEFAULT false,
    towatch BOOLEAN DEFAULT false
    );
    
CREATE TABLE TVShow (
	tvshow_Id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(20),
    watched BOOLEAN DEFAULT false,
    towatch BOOLEAN DEFAULT false
    ); 
CREATE TABLE Season (
	season_Id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(20),
    watched BOOLEAN DEFAULT false,
    towatch BOOLEAN DEFAULT false,
    foreign key fk_Season_TVShow (tvshow_Id)
		references TVShow(tvshow_Id)
    );
CREATE TABLE Episode (
	episode_Id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(4),
    watched BOOLEAN DEFAULT false,
    towatch BOOLEAN DEFAULT false,
    foreign key fk_Episode_Season (season_Id)
		references Season(season_Id)
    );    