DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(ID LONG AUTO_INCREMENT PRIMARY KEY, TITLE VARCHAR(255), AUTHOR_ID INT, GENRE_ID INT);
DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR(ID LONG AUTO_INCREMENT PRIMARY KEY, AUTHORNAME VARCHAR(255));
DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE(ID LONG AUTO_INCREMENT PRIMARY KEY,GENRETITLE VARCHAR(255));
DROP TABLE IF EXISTS COMMENT;
CREATE TABLE COMMENT(ID LONG AUTO_INCREMENT PRIMARY KEY ,BOOKID LONG , COMMENTTEXT VARCHAR(255));