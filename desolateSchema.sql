-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema midtermproject
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `midtermproject` ;

-- -----------------------------------------------------
-- Schema midtermproject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `midtermproject` DEFAULT CHARACTER SET utf8 ;
USE `midtermproject` ;

-- -----------------------------------------------------
-- Table `platform`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `platform` ;

CREATE TABLE IF NOT EXISTS `platform` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game` ;

CREATE TABLE IF NOT EXISTS `game` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `platform_id` INT NOT NULL,
  `max_players` INT(11) NULL,
  `status` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `FK_GAME_TO_PLATFORM_ID_idx` (`platform_id` ASC),
  CONSTRAINT `FK_GAME_TO_PLATFORM_ID`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platform` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(120) NOT NULL,
  `admin` TINYINT NOT NULL DEFAULT 0,
  `status` TINYINT NOT NULL DEFAULT 1,
  `profile_summary` VARCHAR(140) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event` ;

CREATE TABLE IF NOT EXISTS `event` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `game_id` INT(11) NOT NULL,
  `start_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_id` INT(11) NOT NULL,
  `status` TINYINT(4) NOT NULL DEFAULT 1,
  `location` VARCHAR(45) NULL,
  `visibility` TINYINT(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `FK_GAME_TO_EVENT_idx` (`game_id` ASC),
  INDEX `FK_EVENT_TO_CREATOR_ID_idx` (`creator_id` ASC),
  CONSTRAINT `FK_GAME_TO_EVENT`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_EVENT_TO_CREATOR_ID`
    FOREIGN KEY (`creator_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friends` ;

CREATE TABLE IF NOT EXISTS `friends` (
  `user_id` INT(11) NOT NULL,
  `friend_id` INT(11) NOT NULL,
  INDEX `FK_FRIEND_TO_USER_ID_idx` (`friend_id` ASC),
  INDEX `FK_USER_TO_USER_ID_idx` (`user_id` ASC),
  CONSTRAINT `FK_FRIEND_TO_USER_ID`
    FOREIGN KEY (`friend_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_TO_USER_ID`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

CREATE TABLE IF NOT EXISTS `message` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(500) NOT NULL,
  `sender_id` INT(11) NOT NULL,
  `time_sent` DATETIME NOT NULL,
  `event_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_MESSAGE_TO_EVENT_ID_idx` (`event_id` ASC),
  INDEX `FK_SENDER_TO_USER_ID_idx` (`sender_id` ASC),
  CONSTRAINT `FK_MESSAGE_TO_EVENT_ID`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SENDER_TO_USER_ID`
    FOREIGN KEY (`sender_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_event` ;

CREATE TABLE IF NOT EXISTS `user_event` (
  `user_id` INT(11) NOT NULL,
  `event_id` INT(11) NOT NULL,
  INDEX `FK_EVENT_TO_USER_idx` (`user_id` ASC),
  INDEX `FK_USER_TO_EVENT_ID_idx` (`event_id` ASC),
  CONSTRAINT `FK_EVENT_TO_USER_ID`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_TO_EVENT_ID`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_game` ;

CREATE TABLE IF NOT EXISTS `user_game` (
  `user_id` INT(11) NOT NULL,
  `game_id` INT(11) NOT NULL,
  INDEX `FK_Game_User_ID` (`user_id` ASC),
  INDEX `FK_Game_Game_ID` (`game_id` ASC),
  CONSTRAINT `game_id`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO admin@localhost;
 DROP USER admin@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'admin'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `platform`
-- -----------------------------------------------------
START TRANSACTION;
USE `midtermproject`;
INSERT INTO `platform` (`id`, `name`) VALUES (1, 'PC');
INSERT INTO `platform` (`id`, `name`) VALUES (2, 'PS4');
INSERT INTO `platform` (`id`, `name`) VALUES (3, 'in-Person');

COMMIT;


-- -----------------------------------------------------
-- Data for table `game`
-- -----------------------------------------------------
START TRANSACTION;
USE `midtermproject`;
INSERT INTO `game` (`id`, `title`, `platform_id`, `max_players`, `status`) VALUES (1, 'League of Legends', 1, 5, 1);
INSERT INTO `game` (`id`, `title`, `platform_id`, `max_players`, `status`) VALUES (2, 'Call of Duty', 2, 6, 1);
INSERT INTO `game` (`id`, `title`, `platform_id`, `max_players`, `status`) VALUES (3, 'Magic the Gathering', 3, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `midtermproject`;
INSERT INTO `user` (`id`, `name`, `password`, `admin`, `status`, `profile_summary`) VALUES (1, 'PurpleFuzz', '$2a$10$i0eYFjeW5XaQeMH8qu7vwumuGGXhL8CvE5isg2lIhTl3wa513NJ6O', 0, 1, NULL);
INSERT INTO `user` (`id`, `name`, `password`, `admin`, `status`, `profile_summary`) VALUES (2, 'JGMoney', '$2a$10$i0eYFjeW5XaQeMH8qu7vwumuGGXhL8CvE5isg2lIhTl3wa513NJ6O', 0, 1, NULL);
INSERT INTO `user` (`id`, `name`, `password`, `admin`, `status`, `profile_summary`) VALUES (3, 'ASauceyBoy', '$2a$10$i0eYFjeW5XaQeMH8qu7vwumuGGXhL8CvE5isg2lIhTl3wa513NJ6O', 0, 1, NULL);
INSERT INTO `user` (`id`, `name`, `password`, `admin`, `status`, `profile_summary`) VALUES (4, 'admin', 'admin', 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event`
-- -----------------------------------------------------
START TRANSACTION;
USE `midtermproject`;
INSERT INTO `event` (`id`, `game_id`, `start_date`, `creator_id`, `status`, `location`, `visibility`) VALUES (1, 1, DEFAULT, 1, DEFAULT, NULL, 1);
INSERT INTO `event` (`id`, `game_id`, `start_date`, `creator_id`, `status`, `location`, `visibility`) VALUES (2, 3, '2018-07-04 13:00:00', 3, DEFAULT, 'Alex\'s Murder Dungeon', 1);

COMMIT;
