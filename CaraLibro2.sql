SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `CaraLibro` ;
USE `CaraLibro` ;

-- -----------------------------------------------------
-- Table `CaraLibro`.`amities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CaraLibro`.`amities` (
  `id_amitie` INT NOT NULL AUTO_INCREMENT,
  `id_utilisateur_d` INT NOT NULL,
  `id_utilisateur_a` INT NOT NULL,
  `accepter` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_amitie`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CaraLibro`.`commentaires`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CaraLibro`.`commentaires` (
  `id_com` INT NOT NULL,
  `id_publi` INT NOT NULL,
  `id_utilisateur` INT NOT NULL,
  `heure` TIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date` DATE NOT NULL,
  `message` VARCHAR(300) NOT NULL,
  `nbr_like` INT NOT NULL,
  `visibilite` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_com`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CaraLibro`.`publications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CaraLibro`.`publications` (
  `id_publi` INT NOT NULL AUTO_INCREMENT,
  `id_utilisateur` INT NOT NULL,
  `heure` TIME NOT NULL,
  `date` DATE NOT NULL,
  `photo` BLOB NULL,
  `message` VARCHAR(300) NOT NULL,
  `nbr_like` INT NOT NULL,
  `nbr_com` INT NOT NULL,
  `visibilite` TINYINT(1) NOT NULL,
  `commentaires_id_com` INT NOT NULL,
  PRIMARY KEY (`id_publi`, `commentaires_id_com`),
  CONSTRAINT `fk_publications_commentaires1`
    FOREIGN KEY (`commentaires_id_com`)
    REFERENCES `CaraLibro`.`commentaires` (`id_com`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_publications_commentaires1_idx` ON `CaraLibro`.`publications` (`commentaires_id_com` ASC);


-- -----------------------------------------------------
-- Table `CaraLibro`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CaraLibro`.`messages` (
  `id_message` INT NOT NULL AUTO_INCREMENT,
  `id_utilisateur_e` INT NOT NULL,
  `id_utilisateur_r` INT NOT NULL,
  `heure` TIME NOT NULL,
  `date` DATE NOT NULL,
  `objet` VARCHAR(45) NOT NULL,
  `message` VARCHAR(1000) NOT NULL,
  `lu` TINYINT(1) NOT NULL,
  `visibilite_e` TINYINT(1) NOT NULL,
  `visibilite_r` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_message`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CaraLibro`.`signalements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CaraLibro`.`signalements` (
  `id_signalement` INT NOT NULL AUTO_INCREMENT,
  `id_com_publi` INT NOT NULL,
  `id_utilisateur` INT NOT NULL,
  `heure` TIME NOT NULL,
  `date` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_signalement`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CaraLibro`.`utilisateurs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CaraLibro`.`utilisateurs` (
  `id_utilisateur` INT NOT NULL AUTO_INCREMENT,
  `mail` VARCHAR(50) NOT NULL,
  `mdp` VARCHAR(10) NOT NULL,
  `nom` VARCHAR(20) NOT NULL,
  `prenom` VARCHAR(20) NOT NULL,
  `age` INT NOT NULL,
  `sexe` CHAR(1) NOT NULL,
  `photo` BLOB NOT NULL,
  `connecte` TINYINT(1) NULL,
  `supprime` TINYINT(1) NULL,
  `bloque` TINYINT(1) NULL,
  `publications_id_publi` INT NOT NULL,
  `amities_id_amitie` INT NOT NULL,
  `amities_id_utilisateur_D` INT NOT NULL,
  `amities_id_utilisateur_A` INT NOT NULL,
  `messages_id_message` INT NOT NULL,
  `commentaires_id_com` INT NOT NULL,
  `signalements_id_com_publi` INT NOT NULL,
  PRIMARY KEY (`id_utilisateur`, `publications_id_publi`, `amities_id_amitie`, `amities_id_utilisateur_D`, `amities_id_utilisateur_A`, `messages_id_message`, `commentaires_id_com`, `signalements_id_com_publi`),
  CONSTRAINT `fk_utilisateurs_publications1`
    FOREIGN KEY (`publications_id_publi`)
    REFERENCES `CaraLibro`.`publications` (`id_publi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_utilisateurs_amities1`
    FOREIGN KEY (`amities_id_amitie` , `amities_id_utilisateur_D` , `amities_id_utilisateur_A`)
    REFERENCES `CaraLibro`.`amities` (`id_amitie` , `id_utilisateur_d` , `id_utilisateur_a`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_utilisateurs_messages1`
    FOREIGN KEY (`messages_id_message`)
    REFERENCES `CaraLibro`.`messages` (`id_message`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_utilisateurs_commentaires1`
    FOREIGN KEY (`commentaires_id_com`)
    REFERENCES `CaraLibro`.`commentaires` (`id_com`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_utilisateurs_signalements1`
    FOREIGN KEY (`signalements_id_com_publi`)
    REFERENCES `CaraLibro`.`signalements` (`id_com_publi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_utilisateurs_publications1_idx` ON `CaraLibro`.`utilisateurs` (`publications_id_publi` ASC);

CREATE INDEX `fk_utilisateurs_amities1_idx` ON `CaraLibro`.`utilisateurs` (`amities_id_amitie` ASC, `amities_id_utilisateur_D` ASC, `amities_id_utilisateur_A` ASC);

CREATE INDEX `fk_utilisateurs_messages1_idx` ON `CaraLibro`.`utilisateurs` (`messages_id_message` ASC);

CREATE INDEX `fk_utilisateurs_commentaires1_idx` ON `CaraLibro`.`utilisateurs` (`commentaires_id_com` ASC);

CREATE INDEX `fk_utilisateurs_signalements1_idx` ON `CaraLibro`.`utilisateurs` (`signalements_id_com_publi` ASC);

CREATE UNIQUE INDEX `mail_UNIQUE` ON `CaraLibro`.`utilisateurs` (`mail` ASC);


-- -----------------------------------------------------
-- Table `CaraLibro`.`likes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CaraLibro`.`likes` (
  `id_like` INT NOT NULL AUTO_INCREMENT,
  `id_com_publi` INT NOT NULL,
  `id_utilisateur` INT NOT NULL,
  `type` TINYINT(1) NOT NULL,
  `commentaires_id_com` INT NOT NULL,
  `publications_id_publi` INT NOT NULL,
  `publications_commentaires_id_com` INT NOT NULL,
  PRIMARY KEY (`id_like`, `commentaires_id_com`, `publications_id_publi`, `publications_commentaires_id_com`),
  CONSTRAINT `fk_likes_commentaires1`
    FOREIGN KEY (`commentaires_id_com`)
    REFERENCES `CaraLibro`.`commentaires` (`id_com`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_likes_publications1`
    FOREIGN KEY (`publications_id_publi` , `publications_commentaires_id_com`)
    REFERENCES `CaraLibro`.`publications` (`id_publi` , `commentaires_id_com`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_likes_commentaires1_idx` ON `CaraLibro`.`likes` (`commentaires_id_com` ASC);

CREATE INDEX `fk_likes_publications1_idx` ON `CaraLibro`.`likes` (`publications_id_publi` ASC, `publications_commentaires_id_com` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
