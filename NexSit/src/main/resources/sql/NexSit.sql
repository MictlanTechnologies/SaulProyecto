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
  `correo` VARCHAR(100) NOT NULL,
  `contraseña` VARCHAR(255) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `ubicacion` VARCHAR(100) NOT NULL,
  `rol` ENUM('cliente','admin') NOT NULL DEFAULT 'cliente',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC)
)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `NexSit`.`citas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NexSit`.`citas` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `NexSit`.`citas` (
  `id_cita` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NULL,
  `dia` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `estado` ENUM('disponible','ocupado','cancelada') NOT NULL DEFAULT 'disponible',
  PRIMARY KEY (`id_cita`),
  INDEX `idx_citas_usuario` (`id_usuario` ASC),
  INDEX `idx_citas_dia_hora` (`dia` ASC, `hora` ASC),
  CONSTRAINT `fk_citas_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `NexSit`.`usuario` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
