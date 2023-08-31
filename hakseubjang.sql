-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hagseubjang
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `hagseubjang` ;

-- -----------------------------------------------------
-- Schema hagseubjang
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hagseubjang` DEFAULT CHARACTER SET utf8 ;
USE `hagseubjang` ;

-- -----------------------------------------------------
-- Table `hagseubjang`.`lecture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hagseubjang`.`Lecture` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lctreNm` VARCHAR(255) NULL,
  `instrctrNm` VARCHAR(255) NULL,
  `edcStartDay` DATE NULL,
  `edcEndDay` DATE NULL,
  `lctreCo` VARCHAR(1000) NULL,
  `edcTrgetType` VARCHAR(255) NULL,
  `edcMthType` VARCHAR(255) NULL,
  `operDay` VARCHAR(255) NULL,
  `edcPlace` VARCHAR(255) NULL,
  `psncpa` VARCHAR(45) NULL,
  `lctreCost` VARCHAR(45) NULL,
  `edcRdnmadr` VARCHAR(255) NULL,
  `operInstitutionNm` VARCHAR(255) NULL,
  `operPhoneNumber` VARCHAR(255) NULL,
  `rceptStartDate` DATE NULL,
  `rceptEndDate` DATE NULL,
  `rceptMthType` VARCHAR(255) NULL,
  `slctnMthType` VARCHAR(255) NULL,
  `homepageUrl` VARCHAR(255) NULL,
  `oadtCtLctreYn` TINYINT(1) NULL,
  `pntBankAckestYn` TINYINT(1) NULL,
  `lrnAcnutAckestYn` TINYINT(1) NULL,
  `referenceDate` DATE NULL,
  `insttCode` VARCHAR(45) NULL,
  `edcStartTime` VARCHAR(45) NULL,
  `edcCloseTime` VARCHAR(45) NULL,
  `remainDay` BIGINT(20) NULL,
  `inWish` TINYINT(1) NULL,
 PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hagseubjang`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hagseubjang`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `picture` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hagseubjang`.`wish`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hagseubjang`.`Wish` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lectureId` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_wish_lecture_idx` (`lectureId` ASC) VISIBLE,
  INDEX `fk_wish_user1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_wish_lecture`
    FOREIGN KEY (`lectureId`)
    REFERENCES `hagseubjang`.`Lecture` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_wish_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `hagseubjang`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hagseubjang`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hagseubjang`.`Review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `content` VARCHAR(1000) NULL,
  `createdDate` TIMESTAMP NOT NULL,
  `modifiedDate` TIMESTAMP NOT NULL,
  `lectureId` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_lecture1_idx` (`lectureId` ASC) VISIBLE,
  INDEX `fk_review_user1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_review_lecture1`
    FOREIGN KEY (`lectureId`)
    REFERENCES `hagseubjang`.`Lecture` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `hagseubjang`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE SPRING_SESSION (
    PRIMARY_ID CHAR(36) NOT NULL,
    SESSION_ID CHAR(36) NOT NULL,
    CREATION_TIME BIGINT NOT NULL,
    LAST_ACCESS_TIME BIGINT NOT NULL,
    MAX_INACTIVE_INTERVAL INT NOT NULL,
    EXPIRY_TIME BIGINT NOT NULL,
    PRINCIPAL_NAME VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
    SESSION_PRIMARY_ID CHAR(36) NOT NULL,
    ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
    ATTRIBUTE_BYTES LONG VARBINARY NOT NULL,
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
