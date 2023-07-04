-- Exercise 4.1.2

DROP TABLE IF EXISTS FLIGHT_RESERVATION.CAN_LAND;
DROP TABLE IF EXISTS FLIGHT_RESERVATION.FARE;
DROP TABLE IF EXISTS FLIGHT_RESERVATION.SEAT;
DROP TABLE IF EXISTS FLIGHT_RESERVATION.LEG_INSTANCE;
DROP TABLE IF EXISTS FLIGHT_RESERVATION.AIRPLANE;
DROP TABLE IF EXISTS FLIGHT_RESERVATION.AIRPLANE_TYPE;
DROP TABLE IF EXISTS FLIGHT_RESERVATION.FLIGHT_LEG;
DROP TABLE IF EXISTS FLIGHT_RESERVATION.FLIGHT;
DROP TABLE IF EXISTS FLIGHT_RESERVATION.AIRPORT;
DROP SCHEMA IF EXISTS FLIGHT_RESERVATION;
GO

CREATE SCHEMA FLIGHT_RESERVATION;
GO

CREATE TABLE FLIGHT_RESERVATION.AIRPORT (
    code                    VARCHAR(20)      NOT NULL    PRIMARY KEY,
    city                    VARCHAR(100)    NOT NULL,
    state                   VARCHAR(50),
    name                    VARCHAR(100)    NOT NULL
);

CREATE TABLE FLIGHT_RESERVATION.FLIGHT (
    number                  INT             NOT NULL    PRIMARY KEY,
    airline                 VARCHAR(100)    NOT NULL,
    weekdays                INT
);

CREATE TABLE FLIGHT_RESERVATION.FLIGHT_LEG (
    leg_no                  INT             NOT NULL,
    FLIGHT_number           INT             NOT NULL,
    AIRPORT_code            VARCHAR(20)     NOT NULL,
    scheduled_arr_time      DATETIME        NOT NULL,
    scheduled_dep_time      DATETIME        NOT NULL,

    PRIMARY KEY (leg_no, FLIGHT_number),
    FOREIGN KEY (FLIGHT_number) REFERENCES FLIGHT_RESERVATION.FLIGHT(number),
    FOREIGN KEY (AIRPORT_code) REFERENCES FLIGHT_RESERVATION.AIRPORT(code),
    CHECK (scheduled_dep_time < scheduled_arr_time)
);

CREATE TABLE FLIGHT_RESERVATION.AIRPLANE_TYPE (
    type_name               VARCHAR(100)    NOT NULL    PRIMARY KEY,
    max_seats               INT             DEFAULT 0,
    company                 VARCHAR(100)    NOT NULL
);

CREATE TABLE FLIGHT_RESERVATION.AIRPLANE (
    id                      INT             NOT NULL    PRIMARY KEY,
    total_no_of_seats       INT             DEFAULT 0   CHECK (total_no_of_seats < 1000),
    AIRPLANE_TYPE_name      VARCHAR(100)    NOT NULL,

    FOREIGN KEY (AIRPLANE_TYPE_name) REFERENCES FLIGHT_RESERVATION.AIRPLANE_TYPE(type_name)
);

CREATE TABLE FLIGHT_RESERVATION.LEG_INSTANCE (
    leg_date                DATE            NOT NULL,
    FLIGHT_LEG_no           INT             NOT NULL,
    no_of_available_SEATs   INT             NOT NULL,
    arr_time                DATETIME        NOT NULL,
    dep_time                DATETIME        NOT NULL,
    AIRPORT_code            VARCHAR(20)     NOT NULL,
    FLIGHT_number           INT             NOT NULL,
    AIRPLANE_id             INT             NOT NULL,

    PRIMARY KEY (leg_date, FLIGHT_LEG_no),
    FOREIGN KEY (FLIGHT_LEG_no, FLIGHT_number) REFERENCES FLIGHT_RESERVATION.FLIGHT_LEG(leg_no, FLIGHT_number),
    FOREIGN KEY (AIRPORT_code) REFERENCES FLIGHT_RESERVATION.AIRPORT(code),
    FOREIGN KEY (AIRPLANE_id) REFERENCES FLIGHT_RESERVATION.AIRPLANE(id),
    CHECK (dep_time < arr_time)
);

CREATE TABLE FLIGHT_RESERVATION.SEAT (
    seat_no                 INT             NOT NULL,
    LEG_INSTANCE_date       DATE            NOT NULL,
    FLIGHT_LEG_no           INT             NOT NULL,
    c_name                  VARCHAR(25)     NOT NULL,
    c_phone                 INT             NOT NULL,

    PRIMARY KEY (seat_no, LEG_INSTANCE_date, FLIGHT_LEG_no),
    FOREIGN KEY (LEG_INSTANCE_date, FLIGHT_LEG_no) REFERENCES FLIGHT_RESERVATION.LEG_INSTANCE(leg_date, FLIGHT_LEG_no),
);

CREATE TABLE FLIGHT_RESERVATION.FARE (
    flight_code             INT             NOT NULL,
    FLIGHT_number           INT             NOT NULL,
    amount                  INT             NOT NULL,
    restrictions            VARCHAR(300)    NOT NULL,

    PRIMARY KEY (flight_code, FLIGHT_number),
    FOREIGN KEY (FLIGHT_number) REFERENCES FLIGHT_RESERVATION.FLIGHT(number)
);

CREATE TABLE FLIGHT_RESERVATION.CAN_LAND (
    AIRPORT_code            VARCHAR(20)     NOT NULL,
    AIRPLANE_TYPE_name      VARCHAR(100)    NOT NULL,

    PRIMARY KEY (AIRPORT_code, AIRPLANE_TYPE_name),
    FOREIGN KEY (AIRPORT_code) REFERENCES FLIGHT_RESERVATION.AIRPORT(code),
    FOREIGN KEY (AIRPLANE_TYPE_name) REFERENCES FLIGHT_RESERVATION.AIRPLANE_TYPE(type_name)
);