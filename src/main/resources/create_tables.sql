CREATE TABLE picture (
pictureId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
url VARCHAR(255) NOT NULL,
title VARCHAR(50) NOT NULL,
authorId INT(6) NOT NULL,
created Datetime NOT NULL,
lastUpdate Datetime,
nlike INT(6) DEFAULT 0,
ndislike INT(6) DEFAULT 0,
tags VARCHAR(255)
) ;

CREATE TABLE comment (
pictureId INT(6) UNSIGNED NOT NULL,
authorId INT(6) UNSIGNED NOT NULL,
commentId INT(6)  UNSIGNED AUTO_INCREMENT PRIMARY KEY,
commentText VARCHAR(255) NOT NULL,
title VARCHAR(50) NOT NULL,
authorCommentId INT(6) UNSIGNED,
created Datetime NOT NULL,
lastUpdate Datetime,
nlike INT(6) DEFAULT 0,
ndislike INT(6) DEFAULT 0
) ENGINE=INNODB;

CREATE TABLE  author_comment(
authorCommentId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
authorId INT(6) UNSIGNED NOT NULL,
commentId INT(6) UNSIGNED NOT NULL
) ENGINE=INNODB;

CREATE TABLE  author(
authorId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(20) NOT NULL,
registration Datetime NOT NULL
) ENGINE=INNODB;


ALTER TABLE  author_comment add
FOREIGN KEY (authorId)
        REFERENCES author(authorId)
        ON DELETE CASCADE;

ALTER TABLE  author_comment add
	FOREIGN KEY (commentId)
        REFERENCES comment(commentId)
        ON DELETE CASCADE;

alter TABLE comment add
FOREIGN KEY (pictureId)
        REFERENCES picture(pictureId)
        ON DELETE CASCADE;

alter TABLE comment add
FOREIGN KEY (authorCommentId)
        REFERENCES author_comment(authorCommentId)
        ON DELETE CASCADE;
