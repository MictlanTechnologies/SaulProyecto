-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema NexSit
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `NexSit` ;

-- -----------------------------------------------------
-- Schema NexSit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `NexSit` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema nexsit
-- -----------------------------------------------------
SHOW WARNINGS;
USE `NexSit` ;

-- -----------------------------------------------------
-- Table `NexSit`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NexSit`.`usuario` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `NexSit`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `ubicacion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
