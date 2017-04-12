CREATE USER 'prospring5'@'localhost' IDENTIFIED BY 'prospring5';

CREATE SCHEMA MUSICDB;
GRANT ALL PRIVILEGES ON MUSICDB . * TO 'prospring5'@'localhost';
FLUSH PRIVILEGES;

/* view the inserted data */
SELECT * FROM SINGER;
SELECT * FROM ALBUM;

SELECT * FROM ALBUM A, SINGER S
WHERE SINGER_ID = S.ID AND S.ID=1;

/*in case of java.sql.SQLException: The server timezone value 'UTC' is unrecognized or represents more than one timezone. */
SET GLOBAL time_zone = '+3:00';