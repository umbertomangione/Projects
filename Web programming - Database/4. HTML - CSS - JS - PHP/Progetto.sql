-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`CapoSicurezza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CapoSicurezza` (
  `idCapoSicurezza` INT NOT NULL AUTO_INCREMENT,
  `NomeCapo` VARCHAR(15) NOT NULL,
  `CognomeCapo` VARCHAR(15) NOT NULL,
  `Eta` INT NOT NULL,
  PRIMARY KEY (`idCapoSicurezza`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Nazione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Nazione` (
  `Sigla` VARCHAR(4) NOT NULL,
  `NomeNazione` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Sigla`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Localita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Localita` (
  `CAP` VARCHAR(8) NOT NULL,
  `NomeLocalita` VARCHAR(30) NOT NULL,
  `Nazione` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`CAP`),
  INDEX `fk_Località_Nazione1_idx` (`Nazione` ASC),
  CONSTRAINT `fk_Località_Nazione1`
    FOREIGN KEY (`Nazione`)
    REFERENCES `mydb`.`Nazione` (`Sigla`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Concerto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Concerto` (
  `idConcerto` INT NOT NULL AUTO_INCREMENT,
  `NomeConcerto` VARCHAR(20) NOT NULL,
  `Descrizione` VARCHAR(45) NOT NULL,
  `DataConcerto` DATE NOT NULL,
  `Prezzo` INT NOT NULL,
  `CapoSicurezza` INT NOT NULL,
  `CapLocalita` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`idConcerto`),
  INDEX `fk_Concerto_CapoSicurezza1_idx` (`CapoSicurezza` ASC) ,
  INDEX `fk_Concerto_Località1_idx` (`CapLocalita` ASC) ,
  CONSTRAINT `fk_Concerto_CapoSicurezza1`
    FOREIGN KEY (`CapoSicurezza`)
    REFERENCES `mydb`.`CapoSicurezza` (`idCapoSicurezza`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Concerto_Località1`
    FOREIGN KEY (`CapLocalita`)
    REFERENCES `mydb`.`Localita` (`CAP`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Manager` (
  `CodifeFiscale` VARCHAR(16) NOT NULL,
  `NomeManager` VARCHAR(15) NOT NULL,
  `CognomeManager` VARCHAR(15) NOT NULL,
  `Eta` INT NOT NULL,
  PRIMARY KEY (`CodifeFiscale`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Gruppo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Gruppo` (
  `idGruppo` INT NOT NULL AUTO_INCREMENT,
  `NomeGruppo` VARCHAR(20) NOT NULL,
  `NumeroComponenti` INT NOT NULL,
  `Concerto` INT NOT NULL,
  `CodiceFiscaleManager` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`idGruppo`),
  INDEX `fk_Gruppo_Concerto1_idx` (`Concerto` ASC) ,
  INDEX `fk_Gruppo_Manager1_idx` (`CodiceFiscaleManager` ASC) ,
  CONSTRAINT `fk_Gruppo_Concerto1`
    FOREIGN KEY (`Concerto`)
    REFERENCES `mydb`.`Concerto` (`idConcerto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Gruppo_Manager1`
    FOREIGN KEY (`CodiceFiscaleManager`)
    REFERENCES `mydb`.`Manager` (`CodifeFiscale`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Componente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Componente` (
  `idComponente` INT NOT NULL AUTO_INCREMENT,
  `NomeComponente` VARCHAR(15) NOT NULL,
  `CognomeComponente` VARCHAR(15) NOT NULL,
  `Eta` INT NOT NULL,
  `Gruppo` INT NOT NULL,
  PRIMARY KEY (`idComponente`),
  INDEX `fk_Componenti_Gruppo1_idx` (`Gruppo` ASC) ,
  CONSTRAINT `fk_Componenti_Gruppo1`
    FOREIGN KEY (`Gruppo`)
    REFERENCES `mydb`.`Gruppo` (`idGruppo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Brani`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Brani` (
  `idBrani` INT NOT NULL AUTO_INCREMENT,
  `NomeBrano` VARCHAR(30) NOT NULL,
  `Gruppo` INT NOT NULL,
  PRIMARY KEY (`idBrani`),
  INDEX `fk_Brani_Gruppo1_idx` (`Gruppo` ASC) ,
  CONSTRAINT `fk_Brani_Gruppo1`
    FOREIGN KEY (`Gruppo`)
    REFERENCES `mydb`.`Gruppo` (`idGruppo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TipoDiMezzo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TipoDiMezzo` (
  `idTipoDiMezzo` INT NOT NULL AUTO_INCREMENT,
  `NomeMezzo` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idTipoDiMezzo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Albergo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Albergo` (
  `idAlbergo` INT NOT NULL AUTO_INCREMENT,
  `NomeAlbero` VARCHAR(20) NOT NULL,
  `IndirizzoAlbergo` VARCHAR(35) NOT NULL,
  `Prezzo` INT NOT NULL,
  PRIMARY KEY (`idAlbergo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`MezzoDiTrasporto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`MezzoDiTrasporto` (
  `Targa` VARCHAR(7) NOT NULL,
  `NumeroPosti` INT NOT NULL,
  `Concerto` INT NOT NULL,
  `TipoDiMezzo` INT NOT NULL,
  `Albergo` INT NOT NULL,
  PRIMARY KEY (`Targa`),
  INDEX `fk_MezzoDiTrasporto_Concerto1_idx` (`Concerto` ASC) ,
  INDEX `fk_MezzoDiTrasporto_TipoDiMezzo1_idx` (`TipoDiMezzo` ASC) ,
  INDEX `fk_MezzoDiTrasporto_Albergo1_idx` (`Albergo` ASC) ,
  CONSTRAINT `fk_MezzoDiTrasporto_Concerto1`
    FOREIGN KEY (`Concerto`)
    REFERENCES `mydb`.`Concerto` (`idConcerto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MezzoDiTrasporto_TipoDiMezzo1`
    FOREIGN KEY (`TipoDiMezzo`)
    REFERENCES `mydb`.`TipoDiMezzo` (`idTipoDiMezzo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MezzoDiTrasporto_Albergo1`
    FOREIGN KEY (`Albergo`)
    REFERENCES `mydb`.`Albergo` (`idAlbergo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
