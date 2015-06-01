-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema jado_dev
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `jado_dev` ;

-- -----------------------------------------------------
-- Schema jado_dev
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jado_dev` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `jado_dev` ;

-- -----------------------------------------------------
-- Table `jado_dev`.`USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`USER` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`USER` (
  `ID` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(64) NOT NULL,
  `NAME` VARCHAR(20) NOT NULL,
  `PHONE` VARCHAR(45) NOT NULL,
  `ADDRESS` VARCHAR(200) NOT NULL,
  `INSERT_TIME` DATETIME NOT NULL,
  `UPDATE_TIME` TIMESTAMP NOT NULL,
  `EMAIL_VALIDATE_STATUS` CHAR(1) NOT NULL DEFAULT 'F',
  `ENABLED` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`MAIL_AUTH`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`MAIL_AUTH` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`MAIL_AUTH` (
  `USER_ID` VARCHAR(45) NULL,
  `UUID_KEY` VARCHAR(256) NOT NULL,
  `TIME` TIMESTAMP NOT NULL,
  PRIMARY KEY (`USER_ID`),
  CONSTRAINT `fk_MAIL_USER1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `jado_dev`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`SHOP`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`SHOP` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`SHOP` (
  `URL` VARCHAR(45) NOT NULL,
  `TITLE` VARCHAR(100) NOT NULL DEFAULT '제목을 입력해 주세요',
  `PHONE` VARCHAR(45) NOT NULL,
  `MAIN_URL` VARCHAR(256) NOT NULL DEFAULT '/userImg/shop/main/default.jpg',
  `PROFILE_URL` VARCHAR(45) NOT NULL DEFAULT '/userImg/shop/profile/default.png',
  `FOOTER` LONGTEXT NULL,
  `THEME` INT NOT NULL DEFAULT 1,
  `SUB_IMG1_URL` VARCHAR(45) NOT NULL DEFAULT '/userImg/shop/subImg1/default.jpg',
  `SUB_IMG2_URL` VARCHAR(45) NOT NULL DEFAULT '/userImg/shop/subImg2/default.jpg',
  `SUB_IMG3_URL` VARCHAR(45) NOT NULL DEFAULT '/userImg/shop/subImg3/default.jpg',
  PRIMARY KEY (`URL`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`SELLER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`SELLER` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`SELLER` (
  `SHOP_URL` VARCHAR(45) NOT NULL,
  `ID` VARCHAR(45) NULL,
  `BANK` VARCHAR(45) NOT NULL,
  `BANK_ACCOUNT` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SHOP_URL`, `ID`),
  INDEX `fk_SELLER_USER1_idx` (`ID` ASC),
  CONSTRAINT `fk_SELLER_SHOP1`
    FOREIGN KEY (`SHOP_URL`)
    REFERENCES `jado_dev`.`SHOP` (`URL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SELLER_USER1`
    FOREIGN KEY (`ID`)
    REFERENCES `jado_dev`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`CATEGORY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`CATEGORY` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`CATEGORY` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `SHOP_URL` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_CATEGORY_SHOP1_idx` (`SHOP_URL` ASC),
  CONSTRAINT `fk_CATEGORY_SHOP1`
    FOREIGN KEY (`SHOP_URL`)
    REFERENCES `jado_dev`.`SHOP` (`URL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`PRODUCT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`PRODUCT` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`PRODUCT` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CATEGORY_ID` INT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  `PRICE` INT NOT NULL,
  `STOCK` INT NOT NULL,
  `IMG_URL` VARCHAR(256) NOT NULL DEFAULT '/userImg/product/img/default.png',
  `DESC` LONGTEXT NOT NULL,
  `INSERT_TIME` TIMESTAMP NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PRODUCT_CATEGORY1_idx` (`CATEGORY_ID` ASC),
  CONSTRAINT `fk_PRODUCT_CATEGORY1`
    FOREIGN KEY (`CATEGORY_ID`)
    REFERENCES `jado_dev`.`CATEGORY` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`PRODUCT_COMMENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`PRODUCT_COMMENT` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`PRODUCT_COMMENT` (
  `PRODUCT_ID` INT NULL,
  `USER_ID` VARCHAR(45) NOT NULL,
  `COMMENT_TIME` TIMESTAMP NOT NULL,
  `CONTENT` LONGTEXT NOT NULL,
  PRIMARY KEY (`PRODUCT_ID`, `USER_ID`, `COMMENT_TIME`),
  CONSTRAINT `fk_PRODUCT_COMMENT_PRODUCT`
    FOREIGN KEY (`PRODUCT_ID`)
    REFERENCES `jado_dev`.`PRODUCT` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`BOARD`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`BOARD` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`BOARD` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `SHOP_URL` VARCHAR(45) NOT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_BOARD_SHOP1`
    FOREIGN KEY (`SHOP_URL`)
    REFERENCES `jado_dev`.`SHOP` (`URL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`ARTICLE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`ARTICLE` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`ARTICLE` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `BOARD_ID` INT NOT NULL,
  `TITLE` VARCHAR(128) NOT NULL,
  `CONTENT` LONGTEXT NOT NULL,
  `ARTICLE_TIME` TIMESTAMP NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_ARTICLE_BOARD1`
    FOREIGN KEY (`BOARD_ID`)
    REFERENCES `jado_dev`.`BOARD` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`ARTICLE_COMMENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`ARTICLE_COMMENT` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`ARTICLE_COMMENT` (
  `ARTICLE_ID` INT NULL,
  `USER_ID` VARCHAR(45) NOT NULL,
  `COMMENT_TIME` TIMESTAMP NOT NULL,
  `CONTENT` LONGTEXT NOT NULL,
  PRIMARY KEY (`ARTICLE_ID`, `USER_ID`, `COMMENT_TIME`),
  CONSTRAINT `fk_ARTICLE_COMMENT_ARTICLE1`
    FOREIGN KEY (`ARTICLE_ID`)
    REFERENCES `jado_dev`.`ARTICLE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`CART`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`CART` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`CART` (
  `SHOP_URL` VARCHAR(45) NOT NULL,
  `CUSTOMER_ID` VARCHAR(45) NOT NULL,
  `PRODUCT_ID` INT NOT NULL,
  `AMOUNT` INT NOT NULL,
  `IS_PAY` INT NULL DEFAULT 0,
  PRIMARY KEY (`SHOP_URL`, `CUSTOMER_ID`, `PRODUCT_ID`),
  INDEX `fk_PAYMENT_USER1_idx` (`CUSTOMER_ID` ASC),
  INDEX `fk_PAYMENT_PRODUCT1_idx` (`PRODUCT_ID` ASC),
  CONSTRAINT `fk_PAYMENT_SHOP1`
    FOREIGN KEY (`SHOP_URL`)
    REFERENCES `jado_dev`.`SHOP` (`URL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PAYMENT_USER1`
    FOREIGN KEY (`CUSTOMER_ID`)
    REFERENCES `jado_dev`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PAYMENT_PRODUCT1`
    FOREIGN KEY (`PRODUCT_ID`)
    REFERENCES `jado_dev`.`PRODUCT` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`USER_ROLE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`USER_ROLE` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`USER_ROLE` (
  `USER_ID` VARCHAR(45) NOT NULL,
  `ROLE` VARCHAR(45) NOT NULL DEFAULT 'ROLE_EMAIL_NOT_VERIFIED_USER',
  PRIMARY KEY (`USER_ID`),
  INDEX `fk_USER_ROLE_USER1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_USER_ROLE_USER1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `jado_dev`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jado_dev`.`PAYMENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jado_dev`.`PAYMENT` ;

CREATE TABLE IF NOT EXISTS `jado_dev`.`PAYMENT` (
  `SHOP_URL` VARCHAR(45) NOT NULL,
  `CUSTOMER_ID` VARCHAR(45) NOT NULL,
  `PRODUCT_ID` INT NOT NULL,
  `BANK` VARCHAR(45) NOT NULL,
  `PRICE` INT NOT NULL,
  `PAY_TIME` TIMESTAMP NOT NULL,
  PRIMARY KEY (`SHOP_URL`, `CUSTOMER_ID`, `PRODUCT_ID`, `PAY_TIME`),
  INDEX `fk_PAYMENT_CART1_idx` (`SHOP_URL` ASC, `CUSTOMER_ID` ASC, `PRODUCT_ID` ASC),
  CONSTRAINT `fk_PAYMENT_CART1`
    FOREIGN KEY (`SHOP_URL` , `CUSTOMER_ID` , `PRODUCT_ID`)
    REFERENCES `jado_dev`.`CART` (`SHOP_URL` , `CUSTOMER_ID` , `PRODUCT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
