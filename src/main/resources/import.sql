
INSERT INTO author (name) VALUES ('Franta');
INSERT INTO author (name) VALUES ('Petr');
INSERT INTO author (name) VALUES ('Pavel');
INSERT INTO author (name) VALUES ('Skocdopole');
INSERT INTO author (name) VALUES ('Osolsobe');

INSERT INTO author (name, registration) VALUES ('a-CURRENT_DATE', CURRENT_DATE);
INSERT INTO author (name, registration) VALUES ('a-CURRENT_TIME', CURRENT_TIME);
INSERT INTO author (name, registration) VALUES ('a-CURRENT_TIMESTAMP', CURRENT_TIMESTAMP);
INSERT INTO author (name, registration) VALUES ('a-NULL', NULL );


INSERT INTO picture (author, url, title, created) VALUES (1, 'www.url.cz/img1', 'title 1', CURRENT_TIME);
INSERT INTO picture (author, url, title, created) VALUES (2, 'www.url.cz/img2', 'title 2', CURRENT_TIME);
-- INSERT INTO picture (authorId, url, title, created) VALUES (2, 'www.url.cz/img3', 'title 3', CURRENT_TIME);
-- INSERT INTO picture (authorId, url, title, created) VALUES (3, 'www.url.cz/img4', 'title 4', CURRENT_TIME);
