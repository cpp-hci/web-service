CREATE DATABASE IF NOT EXISTS db;

ALTER DATABASE db
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON db.* TO root@localhost
IDENTIFIED BY 'root';
DROP TABLE IF EXISTS rmp_rating;
CREATE TABLE IF NOT EXISTS rmp_rating (
  id                  INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  date                VARCHAR(30),
  description         VARCHAR(30),
  overall_quality     INT(4),
  level_of_difficulty INT(4),
  class_name          VARCHAR(30),
  for_credit          VARCHAR(30),
  attendance          VARCHAR(30),
  text_book_used      VARCHAR(30),
  would_take_again    VARCHAR(30),
  grade_received      VARCHAR(30),
  rating_text         BLOB,
  found_helpful       INT(4),
  found_unhelpful     INT(4),
  professor_id        INT(4)
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS rmp_professor;
CREATE TABLE IF NOT EXISTS rmp_professor (
  id                  INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  overall_quality     DOUBLE,
  level_of_difficulty DOUBLE,
  would_take_again    DOUBLE,
  name                VARCHAR(30),
  school              BLOB
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS rmp_tag;
CREATE TABLE IF NOT EXISTS rmp_tag (
  id        INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  rating_id INT(4),
  text      VARCHAR(30)
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS koofers_professor;
CREATE TABLE IF NOT EXISTS koofers_professor (
  id             INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name           VARCHAR(30),
  period         VARCHAR(30),
  school         BLOB,
  department     VARCHAR(30),
  overall_rating DOUBLE,
  overall_gpa    DOUBLE
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS koofers_rating;
CREATE TABLE IF NOT EXISTS koofers_rating (
  id             INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  course_number  VARCHAR(30),
  course_name    VARCHAR(30),
  period         VARCHAR(30),
  overall_rating DOUBLE,
  overall_gpa    DOUBLE,
  review_text    BLOB,
  professor_id   INTEGER
)
  ENGINE = InnoDB;
