-- Exercise 4.1.1

DROP TABLE IF EXISTS RENT_A_CAR.RENTAL;
DROP TABLE IF EXISTS RENT_A_CAR.VEHICLE;
DROP TABLE IF EXISTS RENT_A_CAR.HEAVY;
DROP TABLE IF EXISTS RENT_A_CAR.LIGHT;
DROP TABLE IF EXISTS RENT_A_CAR.SIMILARITY;
DROP TABLE IF EXISTS RENT_A_CAR.TYPE_VEHICLE;
DROP TABLE IF EXISTS RENT_A_CAR.COUNTER;
DROP TABLE IF EXISTS RENT_A_CAR.CLIENT;
DROP SCHEMA IF EXISTS RENT_A_CAR;
GO

CREATE SCHEMA RENT_A_CAR;
GO

CREATE TABLE RENT_A_CAR.CLIENT (
    nif                    INT             NOT NULL    PRIMARY KEY,
    name                   VARCHAR(100)    NOT NULL,
    address                VARCHAR(250),
    driving_license        INT             NOT NULL    UNIQUE
);

CREATE TABLE RENT_A_CAR.COUNTER (
    number                 INT             NOT NULL    PRIMARY KEY,
    name                   VARCHAR(100)    NOT NULL,
    address                VARCHAR(100)
);

CREATE TABLE RENT_A_CAR.TYPE_VEHICLE (
    code                   INT             NOT NULL    PRIMARY KEY,
    air_conditioning       BIT,
    designation            VARCHAR(250)    NOT NULL
);

CREATE TABLE RENT_A_CAR.SIMILARITY (
    TYPE_VEHICLE_code1     INT             NOT NULL,
    TYPE_VEHICLE_code2     INT             NOT NULL,

    PRIMARY KEY (TYPE_VEHICLE_code1, TYPE_VEHICLE_code2),
    FOREIGN KEY (TYPE_VEHICLE_code1) REFERENCES RENT_A_CAR.TYPE_VEHICLE(code) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (TYPE_VEHICLE_code2) REFERENCES RENT_A_CAR.TYPE_VEHICLE(code)
);

CREATE TABLE RENT_A_CAR.LIGHT (
    TYPE_VEHICLE_code      INT             NOT NULL    PRIMARY KEY,
    fuel                   VARCHAR(50)     NOT NULL,
    doors_num              INT,
    seats_num              INT             NOT NULL,
    
    FOREIGN KEY (TYPE_VEHICLE_code) REFERENCES RENT_A_CAR.TYPE_VEHICLE(code) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE RENT_A_CAR.HEAVY (
    TYPE_VEHICLE_code      INT             NOT NULL    PRIMARY KEY,
    height                 INT,
    passengers             INT             NOT NULL,

    FOREIGN KEY (TYPE_VEHICLE_code) REFERENCES RENT_A_CAR.TYPE_VEHICLE(code) ON DELETE CASCADE ON UPDATE CASCADE,
)

CREATE TABLE RENT_A_CAR.VEHICLE (
    registration           VARCHAR(6)      NOT NULL    PRIMARY KEY,
    year                   INT             NOT NULL,
    brand                  VARCHAR(20)     NOT NULL,
    TYPE_VEHICLE_code      INT             NOT NULL,

    FOREIGN KEY (TYPE_VEHICLE_code) REFERENCES RENT_A_CAR.TYPE_VEHICLE(code) ON UPDATE CASCADE,
)

CREATE TABLE RENT_A_CAR.RENTAL(
    number                 INT            NOT NULL     PRIMARY KEY,
    duration               INT            NOT NULL,
    RENTAL_date            DATE           NOT NULL,
    VEHICLE_registration   VARCHAR(6)     NOT NULL,
    CLIENT_nif             INT            NOT NULL,
    COUNTER_number         INT            NOT NULL,

    FOREIGN KEY (VEHICLE_registration) REFERENCES RENT_A_CAR.VEHICLE(registration) ON UPDATE CASCADE,
    FOREIGN KEY (CLIENT_nif) REFERENCES RENT_A_CAR.CLIENT(nif) ON UPDATE CASCADE,
    FOREIGN KEY (COUNTER_number) REFERENCES RENT_A_CAR.COUNTER(number) ON UPDATE CASCADE
)