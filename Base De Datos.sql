-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.17-log - MySQL Community Server (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para sp
CREATE DATABASE IF NOT EXISTS `sp` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sp`;

-- Volcando estructura para tabla sp.distribuidores
CREATE TABLE IF NOT EXISTS `distribuidores` (
  `cve_distribuidor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `latitud` varchar(30) DEFAULT NULL,
  `longitud` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cve_distribuidor`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sp.distribuidores: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `distribuidores` DISABLE KEYS */;
INSERT INTO `distribuidores` (`cve_distribuidor`, `nombre`, `direccion`, `telefono`, `latitud`, `longitud`) VALUES
	(1, 'Abarrotes Sanchez', 'Principal Juan Aldama', '9935899991', '17.6066796', '-453.0263901'),
	(2, 'Universidad Henry Mendez', 'Principal Villa Juan Aldama', '9932280450', '17.6066796', '-453.0263901'),
	(3, 'Mendez Store', 'Hidalgo,27,Fracc. 27 de Octubre', '9932225581', '17.8848634', '-452.9181576'),
	(4, 'Jairo Olan Mayo', 'Fraccionamiento Las margaritas, Parrilla', '9933082324', '17.9016689', '-452.9235542');
/*!40000 ALTER TABLE `distribuidores` ENABLE KEYS */;

-- Volcando estructura para tabla sp.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `cve_p` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `imagen` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`cve_p`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sp.producto: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`cve_p`, `nombre`, `precio`, `imagen`) VALUES
	(1, 'Agua Purificada 20LT', 25, NULL),
	(2, 'Coca Cola 600Ml', 14, ''),
	(4, 'Lapicero', 10, ''),
	(5, 'Mica Celular 6 Pulgadas', 80, '');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;

-- Volcando estructura para tabla sp.puntoventa
CREATE TABLE IF NOT EXISTS `puntoventa` (
  `cve_pdv` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `latitud` varchar(30) DEFAULT NULL,
  `longitud` varchar(30) DEFAULT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cve_pdv`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sp.puntoventa: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `puntoventa` DISABLE KEYS */;
INSERT INTO `puntoventa` (`cve_pdv`, `nombre`, `direccion`, `latitud`, `longitud`, `telefono`) VALUES
	(1, 'Tacolan', 'Por ahi No.28, Centro', '17.9125926', '-452.9161835', '9938623549');
/*!40000 ALTER TABLE `puntoventa` ENABLE KEYS */;

-- Volcando estructura para tabla sp.stock
CREATE TABLE IF NOT EXISTS `stock` (
  `cve_stock` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cve_producto` int(11) DEFAULT NULL,
  `cve_pv` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`cve_stock`),
  KEY `cve_producto` (`cve_producto`),
  KEY `cve_pv` (`cve_pv`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sp.stock: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` (`cve_stock`, `cve_producto`, `cve_pv`, `cantidad`) VALUES
	(1, 1, 1, 55);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;

-- Volcando estructura para tabla sp.tipousuario
CREATE TABLE IF NOT EXISTS `tipousuario` (
  `cve_tipo_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cve_tipo_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sp.tipousuario: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` (`cve_tipo_usuario`, `nombre`) VALUES
	(1, 'Administrador'),
	(2, 'Empleado'),
	(3, 'Almacen');
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;

-- Volcando estructura para tabla sp.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `cve_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) DEFAULT NULL,
  `paterno` varchar(40) DEFAULT NULL,
  `materno` varchar(40) DEFAULT NULL,
  `login` varchar(20) DEFAULT NULL,
  `contrasena` varchar(20) DEFAULT NULL,
  `cve_tipo_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`cve_usuario`),
  KEY `cve_tipo_usuario` (`cve_tipo_usuario`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sp.usuarios: 7 rows
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`cve_usuario`, `nombre`, `paterno`, `materno`, `login`, `contrasena`, `cve_tipo_usuario`) VALUES
	(1, 'Octavio', 'Sanchez', 'Aquino', 'oesanchez', 'gatito', NULL),
	(2, 'Sandra', 'Bulock', 'Mendez', 'sandrita', 'Bulock', 3),
	(3, 'Cepillín', 'Okerys', 'Lopez', 'cepillin', 'Okerys', 1),
	(4, 'Henry', 'Mendez ', 'Maldonado', 'henry', '1234', 1),
	(5, 'okey', 'lopez', 'sabe', 'a', 'a', 2),
	(6, 'Jairo', 'Olan', 'May', 'olan', '1234', 3),
	(7, 'Randy Angel', 'Salvador', 'Bautista', 'root', 'Salvador', 1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

-- Volcando estructura para tabla sp.ventas
CREATE TABLE IF NOT EXISTS `ventas` (
  `cve_venta` int(11) NOT NULL AUTO_INCREMENT,
  `cve_producto` int(11) DEFAULT NULL,
  `cve_stock` int(11) DEFAULT NULL,
  `cliente` varchar(100) DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `latitud` varchar(30) DEFAULT NULL,
  `longuitud` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cve_venta`),
  KEY `cve_producto` (`cve_producto`),
  KEY `cve_stock` (`cve_stock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sp.ventas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;

-- Volcando estructura para tabla sp.ventasdist
CREATE TABLE IF NOT EXISTS `ventasdist` (
  `cve_ventaDis` int(11) NOT NULL AUTO_INCREMENT,
  `cve_distruibidor` int(11) DEFAULT NULL,
  `cve_producto` int(11) DEFAULT NULL,
  `cve_stock` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`cve_ventaDis`),
  KEY `cve_distruibidor` (`cve_distruibidor`),
  KEY `cve_producto` (`cve_producto`),
  KEY `cve_stock` (`cve_stock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sp.ventasdist: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ventasdist` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventasdist` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
