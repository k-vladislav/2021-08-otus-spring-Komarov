

DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK(ID BIGINT PRIMARY KEY auto_increment, TITLE VARCHAR(255));

DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR(ID BIGINT PRIMARY KEY auto_increment, LAST_NAME VARCHAR(255));

DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE(ID BIGINT PRIMARY KEY auto_increment, GENRE VARCHAR(255));

DROP TABLE IF EXISTS BOOK_AUTHOR;
CREATE TABLE BOOK_AUTHOR(ID BIGINT PRIMARY KEY auto_increment, BOOK_ID BIGINT, AUTHOR_ID BIGINT);

DROP TABLE IF EXISTS BOOK_GENRE;
CREATE TABLE BOOK_GENRE(ID BIGINT PRIMARY KEY auto_increment, BOOK_ID BIGINT, GENRE_ID BIGINT);