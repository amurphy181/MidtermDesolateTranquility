-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
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
-- Table `midtermproject`.`platform`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `midtermproject`.`platform` ;

CREATE TABLE IF NOT EXISTS `midtermproject`.`platform` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `midtermproject`.`game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `midtermproject`.`game` ;

CREATE TABLE IF NOT EXISTS `midtermproject`.`game` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `platform_id` INT NOT NULL,
  `max_players` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_GAME_TO_PLATFORM_ID_idx` (`platform_id` ASC),
  CONSTRAINT `FK_GAME_TO_PLATFORM_ID`
    FOREIGN KEY (`platform_id`)
    REFERENCES `midtermproject`.`platform` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `midtermproject`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `midtermproject`.`event` ;

CREATE TABLE IF NOT EXISTS `midtermproject`.`event` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `game_id` INT(11) NOT NULL,
  `start_date` DATETIME NOT NULL,
  `creator_id` INT(11) NOT NULL,
  `status` TINYINT(4) NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `visibility` TINYINT(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `FK_GAME_TO_EVENT_idx` (`game_id` ASC),
  CONSTRAINT `FK_GAME_TO_EVENT`
    FOREIGN KEY (`game_id`)
    REFERENCES `midtermproject`.`game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `midtermproject`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `midtermproject`.`user` ;

CREATE TABLE IF NOT EXISTS `midtermproject`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `midtermproject`.`friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `midtermproject`.`friends` ;

CREATE TABLE IF NOT EXISTS `midtermproject`.`friends` (
  `user_id` INT(11) NOT NULL,
  `friend_id` INT(11) NOT NULL,
  INDEX `FK_FRIEND_TO_USER_ID_idx` (`friend_id` ASC),
  INDEX `FK_USER_TO_USER_ID_idx` (`user_id` ASC),
  CONSTRAINT `FK_FRIEND_TO_USER_ID`
    FOREIGN KEY (`friend_id`)
    REFERENCES `midtermproject`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_TO_USER_ID`
    FOREIGN KEY (`user_id`)
    REFERENCES `midtermproject`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `midtermproject`.`message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `midtermproject`.`message` ;

CREATE TABLE IF NOT EXISTS `midtermproject`.`message` (
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
    REFERENCES `midtermproject`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SENDER_TO_USER_ID`
    FOREIGN KEY (`sender_id`)
    REFERENCES `midtermproject`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `midtermproject`.`user_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `midtermproject`.`user_event` ;

CREATE TABLE IF NOT EXISTS `midtermproject`.`user_event` (
  `user_id` INT(11) NOT NULL,
  `event_id` INT(11) NOT NULL,
  INDEX `FK_EVENT_TO_USER_idx` (`user_id` ASC),
  INDEX `FK_USER_TO_EVENT_ID_idx` (`event_id` ASC),
  CONSTRAINT `FK_EVENT_TO_USER_ID`
    FOREIGN KEY (`user_id`)
    REFERENCES `midtermproject`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_TO_EVENT_ID`
    FOREIGN KEY (`event_id`)
    REFERENCES `midtermproject`.`event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `midtermproject`.`user_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `midtermproject`.`user_game` ;

CREATE TABLE IF NOT EXISTS `midtermproject`.`user_game` (
  `user_id` INT(11) NOT NULL,
  `game_id` INT(11) NOT NULL,
  INDEX `FK_Game_User_ID` (`user_id` ASC),
  INDEX `FK_Game_Game_ID` (`game_id` ASC),
  CONSTRAINT `game_id`
    FOREIGN KEY (`game_id`)
    REFERENCES `midtermproject`.`game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `midtermproject`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

GRANT SELECT, INSERT, UPDATE, DELETE on midtermproject.* to student;

-- -----------------------------------------------------
-- Data for table `midtermproject`.`platform`
-- -----------------------------------------------------
START TRANSACTION;
USE `midtermproject`;
INSERT INTO `midtermproject`.`platform` (`id`, `name`) VALUES (1, 'PC');
INSERT INTO `midtermproject`.`platform` (`id`, `name`) VALUES (2, 'PS4');

COMMIT;


-- -----------------------------------------------------
-- Data for table `midtermproject`.`game`
-- -----------------------------------------------------
START TRANSACTION;
USE `midtermproject`;
INSERT INTO `midtermproject`.`game` (`id`, `title`, `platform_id`, `max_players`) VALUES (1, 'League of Legends', 1, 5);
INSERT INTO `midtermproject`.`game` (`id`, `title`, `platform_id`, `max_players`) VALUES (2, 'Call of Duty', 2, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `midtermproject`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `midtermproject`;
INSERT INTO `midtermproject`.`user` (`id`, `name`, `password`) VALUES (1, 'PurpleFuzz', 'password');
INSERT INTO `midtermproject`.`user` (`id`, `name`, `password`) VALUES (2, 'JGMoney', 'password');
INSERT INTO `midtermproject`.`user` (`id`, `name`, `password`) VALUES (3, 'ASauceyBoy', 'password');

COMMIT;
