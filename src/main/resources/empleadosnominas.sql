-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.5.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para empleadosnominas
CREATE DATABASE IF NOT EXISTS `empleadosnominas` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `empleadosnominas`;

-- Volcando estructura para tabla empleadosnominas.empleado
CREATE TABLE IF NOT EXISTS `empleado` (
  `nombre` varchar(100) NOT NULL,
  `Dni` varchar(9) NOT NULL,
  `Sexo` char(1) NOT NULL,
  `Categoria` int(10) NOT NULL DEFAULT 1,
  `Anyos` int(10) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla empleadosnominas.empleado: ~2 rows (aproximadamente)
INSERT INTO `empleado` (`nombre`, `Dni`, `Sexo`, `Categoria`, `Anyos`) VALUES
	('David', '32000031G', 'F', 3, 3),
	('Ada Lovelace', '32000031R', 'F', 6, 9);

-- Volcando estructura para tabla empleadosnominas.nomina
CREATE TABLE IF NOT EXISTS `nomina` (
  `Dni` varchar(9) NOT NULL,
  `sueldo` double NOT NULL,
  PRIMARY KEY (`Dni`),
  CONSTRAINT `FK_n_p` FOREIGN KEY (`Dni`) REFERENCES `empleado` (`Dni`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla empleadosnominas.nomina: ~2 rows (aproximadamente)
INSERT INTO `nomina` (`Dni`, `sueldo`) VALUES
	('32000031G', 26000),
	('32000031R', 62000);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
