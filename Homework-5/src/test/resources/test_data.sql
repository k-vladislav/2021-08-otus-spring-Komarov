INSERT INTO AUTHOR (LAST_NAME)
VALUES ('AUTHOR_ONE'),
       ('AUTHOR_TWO'),
       ('AUTHOR_THREE');

INSERT INTO BOOK (TITLE)
VALUES ('BOOK_ONE'),
       ('BOOK_TWO'),
       ('BOOK_THREE');

INSERT INTO GENRE (GENRE)
VALUES ('GENRE_ONE'),
       ('GENRE_TWO'),
       ('GENRE_THREE');

INSERT INTO BOOK_AUTHOR (BOOK_ID, AUTHOR_ID)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 3),
       (3, 1);

INSERT INTO BOOK_GENRE (BOOK_ID, GENRE_ID)
VALUES (1, 1),
       (1, 3),
       (2, 2),
       (3, 2),
       (3, 3);
