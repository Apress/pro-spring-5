CREATE USER 'prospring5_A'@'localhost' IDENTIFIED BY 'prospring5_A';
CREATE SCHEMA MUSICDB_A;
GRANT ALL PRIVILEGES ON MUSICDB_A . * TO 'prospring5_A'@'localhost';
FLUSH PRIVILEGES;

CREATE USER 'prospring5_B'@'localhost' IDENTIFIED BY 'prospring5_B';
CREATE SCHEMA MUSICDB_B;
GRANT ALL PRIVILEGES ON MUSICDB_B . * TO 'prospring5_B'@'localhost';
FLUSH PRIVILEGES;


/*in case of java.sql.SQLException: The server timezone value 'UTC' is unrecognized or represents more than one timezone. */
SET GLOBAL time_zone = '+3:00';