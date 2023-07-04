DROP TABLE IF EXISTS ATL.REL_CLASS_HAS_ACTIVITIES
DROP TABLE IF EXISTS ATL.REL_STUDENT_PARTICIPATES_IN_ACTIVITY;
DROP TABLE IF EXISTS ATL.REL_STUDENT_HAS_AUTH_PERSON;
DROP TABLE IF EXISTS ATL.STUDENT;
DROP TABLE IF EXISTS ATL.AUTHORIZED_PERSON;
DROP TABLE IF EXISTS ATL.CLASS;
DROP TABLE IF EXISTS ATL.TEACHER;
DROP TABLE IF EXISTS ATL.PERSON;
DROP TABLE IF EXISTS ATL.ACTIVITY;
DROP SCHEMA IF EXISTS ATL;
GO

CREATE SCHEMA ATL;
GO

CREATE TABLE ATL.CLASS (
    id                  INT             NOT NULL    PRIMARY KEY,
    type                INT             NOT NULL,
    school_year         VARCHAR(9)      NOT NULL,
    designation         VARCHAR(50)     NOT NULL,
    max_students        INT,
    teacher_id          INT             NOT NULL
)

CREATE TABLE ATL.ACTIVITY (
    id                  INT             NOT NULL    PRIMARY KEY,
    designation         VARCHAR(100)    NOT NULL,
    cost                INT             NOT NULL    DEFAULT 0
)

CREATE TABLE ATL.PERSON (
    cc_number           INT             NOT NULL    PRIMARY KEY,
    name                VARCHAR(250)    NOT NULL,
    address             VARCHAR(250),
    date_of_birth       DATE,
    CHECK (cc_number <= 99999999) -- 8 dígitos
)

CREATE TABLE ATL.TEACHER (
    cc_number           INT             NOT NULL    PRIMARY KEY,
    employee_id         INT             NOT NULL    UNIQUE,
    phone               INT             NOT NULL    UNIQUE,
    email               VARCHAR(100)    NOT NULL    UNIQUE,
    FOREIGN KEY (cc_number) REFERENCES ATL.PERSON(cc_number)
)

ALTER TABLE ATL.CLASS ADD CONSTRAINT id_professor FOREIGN KEY (teacher_id) REFERENCES ATL.TEACHER(employee_id);

CREATE TABLE ATL.AUTHORIZED_PERSON (
    cc_number           INT             NOT NULL    PRIMARY KEY,
    phone               INT             NOT NULL    UNIQUE,
    email               VARCHAR(100)    NOT NULL    UNIQUE,
    FOREIGN KEY (cc_number) REFERENCES ATL.PERSON(cc_number)
)

CREATE TABLE ATL.STUDENT (
    cc_number           INT             NOT NULL    PRIMARY KEY,
    class_id            INT             NOT NULL,
    cc_number_parent    INT             NOT NULL,
    FOREIGN KEY (cc_number) REFERENCES ATL.PERSON(cc_number),
    FOREIGN KEY (class_id) REFERENCES ATL.CLASS(id),
    FOREIGN KEY (cc_number_parent) REFERENCES ATL.AUTHORIZED_PERSON(cc_number)
)

CREATE TABLE ATL.REL_STUDENT_HAS_AUTH_PERSON (
    cc_number_student   INT             NOT NULL    REFERENCES ATL.STUDENT(cc_number),
    cc_number_auth_p    INT             NOT NULL    REFERENCES ATL.AUTHORIZED_PERSON(cc_number),
    relationship        VARCHAR(20)     NOT NULL,
    PRIMARY KEY (cc_number_student, cc_number_auth_p)
)

CREATE TABLE ATL.REL_STUDENT_PARTICIPATES_IN_ACTIVITY (
    cc_number_student   INT             NOT NULL    REFERENCES ATL.STUDENT(cc_number),
    activity_id         INT             NOT NULL    REFERENCES ATL.ACTIVITY(id),
    PRIMARY KEY (cc_number_student, activity_id)
)

CREATE TABLE ATL.REL_CLASS_HAS_ACTIVITIES (
    class_id            INT             NOT NULL    REFERENCES ATL.CLASS(id),
    activity_id         INT             NOT NULL    REFERENCES ATL.ACTIVITY(id),
    PRIMARY KEY (class_id, activity_id)
)