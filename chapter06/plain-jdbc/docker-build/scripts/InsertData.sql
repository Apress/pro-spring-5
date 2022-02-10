insert into SINGER (first_name, last_name, birth_date) values ('John', 'Mayer', '1977-10-16');
insert into SINGER (first_name, last_name, birth_date) values ('Eric', 'Clapton', '1945-03-30');
insert into SINGER (first_name, last_name, birth_date) values ('John', 'Butler', '1975-04-01');

insert into ALBUM (singer_id, title, release_date) values (1, 'The Search For Everything', '2017-01-20');
insert into ALBUM (singer_id, title, release_date) values (1, 'Battle Studies', '2009-11-17');
insert into ALBUM (singer_id, title, release_date) values (2, ' From The Cradle ', '1994-09-13');

/* view the inserted data */
SELECT * FROM SINGER;
SELECT * FROM ALBUM;

/* simple join sample */
SELECT * FROM ALBUM A, SINGER S
WHERE SINGER_ID = S.ID AND S.ID=1;