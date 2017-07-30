CREATE USER 'xd'@'localhost' IDENTIFIED BY 'xd';
CREATE SCHEMA XD;
GRANT ALL PRIVILEGES ON XD . * TO 'xd'@'localhost';
FLUSH PRIVILEGES;

/*in case of java.sql.SQLException: The server timezone value 'UTC' is unrecognized or represents more than one timezone. */
SET GLOBAL time_zone = '+3:00';