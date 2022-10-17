DROP TABLE if exists User;
DROP TABLE if exists Person;
DROP TABLE if exists Events;
DROP TABLE if exists AuthToken;



CREATE TABLE User (
  username varchar(255) NOT NULL PRIMARY KEY,
  password varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  firstName varchar(255) NOT NULL,
  lastName varchar(255) NOT NULL,
  gender varchar(255) NOT NULL,
  personID varchar(255) NOT NULL
);

CREATE TABLE Person (
  personID varchar(255) NOT NULL PRIMARY KEY,
  associatedUsername varchar(255) NOT NULL,
  firstName varchar(255) NOT NULL,
  lastName varchar(255) NOT NULL,
  gender varchar(255) NOT NULL,
  fatherID varchar(255),
  motherID varchar(255),
  spouseID varchar(255)
);

CREATE TABLE Events (
  eventID varchar(255) NOT NULL PRIMARY KEY,
  associatedUsername varchar(255) NOT NULL,
  personID varchar(255) NOT NULL,
  latitude float NOT NULL,
  longitude float NOT NULL,
  country varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  eventType varchar(255) NOT NULL,
  year int NOT NULL
);

CREATE TABLE AuthToken (
  authtoken varchar(255) NOT NULL PRIMARY KEY,
  username varchar(255) NOT NULL
);