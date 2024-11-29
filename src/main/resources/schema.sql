CREATE TABLE `presupuestodb`.`fichero_cargado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha_carga` DATE NOT NULL,
  `nombre_fichero` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `presupuestodb`.`movimiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha_mov` DATE NOT NULL,
  `descripcion_mov` VARCHAR(150) NOT NULL,
  `documento` VARCHAR(45) NOT NULL,
  `info_adicional` VARCHAR(45) NULL,
  `tipo_mov` INT NULL,
  `valor_mov` DECIMAL NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `presupuestodb`.`tipo_movimiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
  ALTER TABLE `presupuestodb`.`movimiento` 
ADD INDEX `tipo_mov_fk_idx` (`tipo_mov` ASC) VISIBLE;
;
ALTER TABLE `presupuestodb`.`movimiento` 
ADD CONSTRAINT `tipo_mov_fk`
  FOREIGN KEY (`tipo_mov`)
  REFERENCES `presupuestodb`.`movimiento` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;