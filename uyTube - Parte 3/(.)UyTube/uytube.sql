-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: uytube
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CANAL`
--

DROP TABLE IF EXISTS `CANAL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CANAL` (
  `USER_ID` int(11) NOT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `PRIVACIDAD` bit(1) NOT NULL,
  `CATEGORIA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `FKprs7kj5lmwcpxe7ng3rj3mo9b` (`CATEGORIA`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CANAL`
--

LOCK TABLES `CANAL` WRITE;
/*!40000 ALTER TABLE `CANAL` DISABLE KEYS */;
INSERT INTO `CANAL` VALUES (1,'El canal Horacio es para publicar contenido divertido','Canal Horacio',_binary '\0','Entretenimiento'),(2,'Mi canal para colgar cosas','El bocha',_binary '\0',NULL),(3,'Canal HG','hectorg',_binary '\0',NULL),(4,'Mi música e ainda mais','Tabaré',_binary '\0',NULL),(5,'Para juntar cosas','El Cachila',_binary '',NULL),(6,'Canal de JB','juliob',_binary '\0',NULL),(7,'Canal de DP','diegop',_binary '\0',NULL),(8,'Videos de grandes canciones de hoy y siempre','Kairo música',_binary '\0','Música'),(9,'Henderson','robinh',_binary '\0',NULL),(10,'Todo lo que querías y más !','Tinelli total',_binary '\0',NULL),(11,'Preparando las elecciones','Con la gente',_binary '\0',NULL),(12,'Programas del ciclo y videos de cocina masterchef','Puglia invita',_binary '\0','Comida'),(13,'Canal de goles con Nacional','Chino Recoba',_binary '','Deporte'),(14,'Todos los goles con Peñarol','Tony Pacheco',_binary '','Deporte'),(15,'Canal información C y T','Desde Genexus',_binary '\0','Ciencia y Tecnología');
/*!40000 ALTER TABLE `CANAL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CANAL_ListaDeReproduccion`
--

DROP TABLE IF EXISTS `CANAL_ListaDeReproduccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CANAL_ListaDeReproduccion` (
  `Canal_USER_ID` int(11) NOT NULL,
  `listas_ID` int(11) NOT NULL,
  UNIQUE KEY `UK_rh1oo17r1xiybooh3yssxrn0y` (`listas_ID`),
  KEY `FK74ibg5m2x26w1osuv1qfj5aes` (`Canal_USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CANAL_ListaDeReproduccion`
--

LOCK TABLES `CANAL_ListaDeReproduccion` WRITE;
/*!40000 ALTER TABLE `CANAL_ListaDeReproduccion` DISABLE KEYS */;
INSERT INTO `CANAL_ListaDeReproduccion` VALUES (1,16),(2,17),(3,33),(4,34),(5,35),(6,36),(7,22),(8,38),(9,24),(10,25),(11,26),(12,27),(13,28),(14,29),(15,45),(1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(1,31),(2,32),(3,18),(4,19),(5,20),(6,21),(7,37),(8,23),(9,39),(10,40),(11,41),(12,42),(13,43),(14,44),(15,30),(8,46),(4,47),(3,48),(5,49),(15,50),(6,51);
/*!40000 ALTER TABLE `CANAL_ListaDeReproduccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CANAL_VIDEO`
--

DROP TABLE IF EXISTS `CANAL_VIDEO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CANAL_VIDEO` (
  `Canal_USER_ID` int(11) NOT NULL,
  `videos_ID` int(11) NOT NULL,
  UNIQUE KEY `UK_pv4pu1fwo7bedh8pdu7n6mihg` (`videos_ID`),
  KEY `FKpwfjd7shfxqx9ftj74ikwwn8b` (`Canal_USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CANAL_VIDEO`
--

LOCK TABLES `CANAL_VIDEO` WRITE;
/*!40000 ALTER TABLE `CANAL_VIDEO` DISABLE KEYS */;
INSERT INTO `CANAL_VIDEO` VALUES (4,2),(4,1),(6,11),(8,4),(6,3),(3,7),(3,6),(3,8),(5,10),(5,9),(6,5),(4,12),(6,13),(5,14),(15,15),(15,16);
/*!40000 ALTER TABLE `CANAL_VIDEO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CATEGORIA`
--

DROP TABLE IF EXISTS `CATEGORIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CATEGORIA` (
  `NOMBRE` varchar(255) NOT NULL,
  PRIMARY KEY (`NOMBRE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CATEGORIA`
--

LOCK TABLES `CATEGORIA` WRITE;
/*!40000 ALTER TABLE `CATEGORIA` DISABLE KEYS */;
INSERT INTO `CATEGORIA` VALUES ('Carnaval'),('Ciencia y Tecnología'),('Comida'),('Deporte'),('Entretenimiento'),('Gente y blogs'),('Mascotas y animales'),('Música'),('Noticias'),('ONG y activismo'),('Viajes y eventos'),('Videojuegos');
/*!40000 ALTER TABLE `CATEGORIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMENTARIO`
--

DROP TABLE IF EXISTS `COMENTARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMENTARIO` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENIDO` varchar(255) DEFAULT NULL,
  `FECHA` datetime DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `VIDEO_ID` int(11) DEFAULT NULL,
  `ID_PADRE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlqhc2jp7jqvqdjo0f4bsdqnte` (`ID_PADRE`),
  KEY `FKl42i5vyk7qpx4mppo3imtrdse` (`USER_ID`),
  KEY `FKjbnva11k78u5t9oggmukmyoi4` (`VIDEO_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMENTARIO`
--

LOCK TABLES `COMENTARIO` WRITE;
/*!40000 ALTER TABLE `COMENTARIO` DISABLE KEYS */;
INSERT INTO `COMENTARIO` VALUES (1,'Fue un gran evento','0017-12-05 14:35:00',15,7,NULL),(2,'Para el próximo aniversario ofrezco vamo’ con los Momo','2017-12-08 01:47:00',1,7,1),(3,'Yo ofrezco a la banda tb','2017-12-10 17:09:00',4,7,2),(4,'Felicitaciones FING!!!','2017-09-07 04:56:00',15,6,NULL),(5,'Un gusto cubrir eventos como este.','2017-10-23 12:58:00',8,8,NULL),(6,'Peñarol peñarol!!!','2016-11-14 05:34:00',8,13,NULL),(7,'Rock and Rolllll','2017-10-30 02:17:00',10,3,NULL),(8,'Anoche explotó!!!','2018-08-25 18:00:00',10,4,NULL),(9,'Se viene la edición 2018','2018-09-11 03:45:00',10,1,5),(10,'Mi preferido por lejos!!','2017-09-15 12:29:00',4,1,NULL);
/*!40000 ALTER TABLE `COMENTARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMENTARIO_COMENTARIO`
--

DROP TABLE IF EXISTS `COMENTARIO_COMENTARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMENTARIO_COMENTARIO` (
  `Comentario_id` bigint(20) NOT NULL,
  `hijos_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_sasyp2f8gtosaqnt5c5ojcenl` (`hijos_id`),
  KEY `FKajkd18w33p383uea6fdt96oqk` (`Comentario_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMENTARIO_COMENTARIO`
--

LOCK TABLES `COMENTARIO_COMENTARIO` WRITE;
/*!40000 ALTER TABLE `COMENTARIO_COMENTARIO` DISABLE KEYS */;
INSERT INTO `COMENTARIO_COMENTARIO` VALUES (1,2),(2,3),(5,9);
/*!40000 ALTER TABLE `COMENTARIO_COMENTARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Canal_Usuario`
--

DROP TABLE IF EXISTS `Canal_Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Canal_Usuario` (
  `seguidores_ID` int(11) NOT NULL,
  `suscripciones_USER_ID` int(11) NOT NULL,
  KEY `FKxdyvwim1dv5sfbaegep1u3jb` (`suscripciones_USER_ID`),
  KEY `FKdr17wvhbcp83qlh4o7iymp2d8` (`seguidores_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Canal_Usuario`
--

LOCK TABLES `Canal_Usuario` WRITE;
/*!40000 ALTER TABLE `Canal_Usuario` DISABLE KEYS */;
INSERT INTO `Canal_Usuario` VALUES (1,3),(1,7),(2,5),(2,4),(2,8),(3,2),(3,6),(4,1),(4,5),(5,1),(6,2),(6,7),(7,3),(8,12),(9,6),(9,3),(9,7),(10,6),(10,5),(10,8),(11,4),(11,1),(11,5),(12,6),(12,2),(12,7),(13,14),(14,12),(15,7);
/*!40000 ALTER TABLE `Canal_Usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ListaDeReproduccion`
--

DROP TABLE IF EXISTS `ListaDeReproduccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ListaDeReproduccion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `PRIVADA` bit(1) DEFAULT NULL,
  `CATEGORIA` varchar(255) DEFAULT NULL,
  `ID_PROPIETARIO` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKlynidq6j79ps59tgqrcghne4g` (`CATEGORIA`),
  KEY `FKnplbgd6nik5tq592ttje4mkmw` (`ID_PROPIETARIO`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ListaDeReproduccion`
--

LOCK TABLES `ListaDeReproduccion` WRITE;
/*!40000 ALTER TABLE `ListaDeReproduccion` DISABLE KEYS */;
INSERT INTO `ListaDeReproduccion` VALUES (1,'Escuchar más tarde',_binary '',NULL,1),(2,'Escuchar más tarde',_binary '',NULL,2),(3,'Escuchar más tarde',_binary '',NULL,3),(4,'Escuchar más tarde',_binary '',NULL,4),(5,'Escuchar más tarde',_binary '',NULL,5),(6,'Escuchar más tarde',_binary '',NULL,6),(7,'Escuchar más tarde',_binary '',NULL,7),(8,'Escuchar más tarde',_binary '',NULL,8),(9,'Escuchar más tarde',_binary '',NULL,9),(10,'Escuchar más tarde',_binary '',NULL,10),(11,'Escuchar más tarde',_binary '',NULL,11),(12,'Escuchar más tarde',_binary '',NULL,12),(13,'Escuchar más tarde',_binary '',NULL,13),(14,'Escuchar más tarde',_binary '',NULL,14),(15,'Escuchar más tarde',_binary '',NULL,15),(16,'Deporte total',_binary '',NULL,1),(17,'Deporte total',_binary '',NULL,2),(18,'Deporte total',_binary '',NULL,3),(19,'Deporte total',_binary '',NULL,4),(20,'Deporte total',_binary '',NULL,5),(21,'Deporte total',_binary '',NULL,6),(22,'Deporte total',_binary '',NULL,7),(23,'Deporte total',_binary '',NULL,8),(24,'Deporte total',_binary '',NULL,9),(25,'Deporte total',_binary '',NULL,10),(26,'Deporte total',_binary '',NULL,11),(27,'Deporte total',_binary '',NULL,12),(28,'Deporte total',_binary '',NULL,13),(29,'Deporte total',_binary '',NULL,14),(30,'Deporte total',_binary '',NULL,15),(31,'Novedades generales',_binary '',NULL,1),(32,'Novedades generales',_binary '',NULL,2),(33,'Novedades generales',_binary '',NULL,3),(34,'Novedades generales',_binary '',NULL,4),(35,'Novedades generales',_binary '',NULL,5),(36,'Novedades generales',_binary '',NULL,6),(37,'Novedades generales',_binary '',NULL,7),(38,'Novedades generales',_binary '',NULL,8),(39,'Novedades generales',_binary '',NULL,9),(40,'Novedades generales',_binary '',NULL,10),(41,'Novedades generales',_binary '',NULL,11),(42,'Novedades generales',_binary '',NULL,12),(43,'Novedades generales',_binary '',NULL,13),(44,'Novedades generales',_binary '',NULL,14),(45,'Novedades generales',_binary '',NULL,15),(46,'Nostalgia',_binary '\0','Música',8),(47,'De fiesta',_binary '','Carnaval',4),(48,'Novedades FING',_binary '\0','Noticias',3),(49,'De todo un poco',_binary '',NULL,5),(50,'Noticias y CYT',_binary '\0','Ciencia y Tecnología',15),(51,'Solo deportes',_binary '\0','Deporte',6);
/*!40000 ALTER TABLE `ListaDeReproduccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ListaDeReproduccion_PorDefecto`
--

DROP TABLE IF EXISTS `ListaDeReproduccion_PorDefecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ListaDeReproduccion_PorDefecto` (
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ListaDeReproduccion_PorDefecto`
--

LOCK TABLES `ListaDeReproduccion_PorDefecto` WRITE;
/*!40000 ALTER TABLE `ListaDeReproduccion_PorDefecto` DISABLE KEYS */;
INSERT INTO `ListaDeReproduccion_PorDefecto` VALUES ('Deporte total'),('Escuchar más tarde'),('Novedades generales');
/*!40000 ALTER TABLE `ListaDeReproduccion_PorDefecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ListaDeReproduccion_VIDEO`
--

DROP TABLE IF EXISTS `ListaDeReproduccion_VIDEO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ListaDeReproduccion_VIDEO` (
  `ListaDeReproduccion_ID` int(11) NOT NULL,
  `videos_ID` int(11) NOT NULL,
  KEY `FK5iy019nw7ded01dactbu5wju` (`videos_ID`),
  KEY `FKdyrbxl4u1s6e51nm89iy38oa2` (`ListaDeReproduccion_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ListaDeReproduccion_VIDEO`
--

LOCK TABLES `ListaDeReproduccion_VIDEO` WRITE;
/*!40000 ALTER TABLE `ListaDeReproduccion_VIDEO` DISABLE KEYS */;
INSERT INTO `ListaDeReproduccion_VIDEO` VALUES (46,4),(46,3),(46,5),(47,2),(47,1),(47,10),(48,7),(48,6),(48,8),(49,10),(49,2),(49,1),(49,9),(49,13),(50,8),(50,16),(51,11),(51,13);
/*!40000 ALTER TABLE `ListaDeReproduccion_VIDEO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACTIVO` bit(1) DEFAULT NULL,
  `APELLIDO` varchar(255) DEFAULT NULL,
  `FECHANAC` date NOT NULL,
  `IMAGEN` varchar(255) DEFAULT NULL,
  `MAIL` varchar(255) NOT NULL,
  `NICKNAME` varchar(255) NOT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
INSERT INTO `USUARIO` VALUES (1,_binary '','Rubino','1962-02-25','hrubino.jpg','horacio.rubino@guambia.com.uy','hrubino','Horacio','Rufus123'),(2,_binary '','Buscaglia','1972-06-14','mbusca.jpg','Martin.bus@agadu.org.uy','mbusca','Martín','Cookie234'),(3,_binary '','Guido','1954-01-07',NULL,'hector.gui@elgalpon.org.uy','hectorg','Héctor','Poncho345'),(4,_binary '','Cardozo','1971-07-24','tabarec.jpg','tabare.car@agadu.org.uy','tabarec','Tabaré','Ketchup1'),(5,_binary '','Silva','1947-01-01','cachilas.jpg','Cachila.sil@c1080.org.uy','cachilas','Waldemar \'Cachila\'','Sancho456'),(6,_binary '','Bocca','1967-03-16',NULL,'juliobocca@sodre.com.uy','juliob','Julio','Salome56'),(7,_binary '','Parodi','1975-01-01',NULL,'diego@efectocine.com','diegop','Diego','Ruffo678'),(8,_binary '','Herrera','1840-04-25','kairoh.jpg','kairoher@pilsenrock.com.uy','kairoh','Kairo','Corbata15'),(9,_binary '','Henderson','1940-08-03',NULL,'Robin.h@tinglesa.com.uy','robinh','Robin','Aquiles67'),(10,_binary '','Tinelli','1960-04-01',NULL,'marcelot@ideasdelsur.com.ar','marcelot','Marcelo','Mancha890'),(11,_binary '','Novick','1952-07-17',NULL,'edgardo@novick.com.uy','novick','Edgardo','Xenon987'),(12,_binary '','Puglia','1950-01-28',NULL,'puglia@alpanpan.com.uy','sergiop','Sergio','Sultan876'),(13,_binary '','Recoba','1976-03-17','chino.jpg','chino@trico.org.uy','chino','Alvaro','Laika765'),(14,_binary '','Pacheco','1955-02-14','tonyp.jpg','eltony@manya.org.uy','tonyp','Antonio','Kitty543'),(15,_binary '','Jodal','1960-08-09',NULL,'jodal@artech.com.uy','nicoJ','Nicolás','Albino80');
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VALORACION`
--

DROP TABLE IF EXISTS `VALORACION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VALORACION` (
  `USER_ID` int(11) NOT NULL,
  `VIDEO_ID` int(11) NOT NULL,
  `GUSTAR` bit(1) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`VIDEO_ID`),
  KEY `FK1sg64m59lihs5f9vdettsc0t8` (`VIDEO_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VALORACION`
--

LOCK TABLES `VALORACION` WRITE;
/*!40000 ALTER TABLE `VALORACION` DISABLE KEYS */;
INSERT INTO `VALORACION` VALUES (12,7,_binary '\0'),(12,8,_binary ''),(12,11,_binary ''),(15,4,_binary '\0'),(15,7,_binary ''),(8,7,_binary ''),(8,13,_binary ''),(10,8,_binary ''),(10,4,_binary '');
/*!40000 ALTER TABLE `VALORACION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VIDEO`
--

DROP TABLE IF EXISTS `VIDEO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VIDEO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CANAL_USER_ID` int(11) DEFAULT NULL,
  `CATEGORIA` varchar(255) DEFAULT NULL,
  `CODIGO` varchar(255) NOT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `DURACION` float NOT NULL,
  `FECHA_PUBLICACION` date NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `PRIVACIDAD` bit(1) NOT NULL,
  `URL` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK8a5nn95cintct8nw3cfi4np3l` (`CANAL_USER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VIDEO`
--

LOCK TABLES `VIDEO` WRITE;
/*!40000 ALTER TABLE `VIDEO` DISABLE KEYS */;
INSERT INTO `VIDEO` VALUES (1,4,'Música','KE8pwe0N8sY','Una cancion de fubol',3.04,'2019-11-03','Locura celeste',_binary '','https://youtu.be/PAfbzKcePx0'),(2,4,'Música','bFvK6cxJHRQ','la emocion explota',4.18,'2019-11-03','Niño payaso',_binary '','https://youtu.be/K-uEIUnyZPg'),(3,6,'Música','vpqPLJglDB8','cancion de los rolin',5.02,'2019-11-03','Sweet child\'o mine',_binary '\0','https://youtu.be/1w7OgIMMRc4'),(4,8,'Música','wQXsmn9HEkn','temaso para la noche de la nostalgia',5.58,'2019-11-03','Dancing in the Dark',_binary '\0','https://youtu.be/129kuDCQtHs'),(5,6,'Música','ukLTeewlhpS','este medio raro con los nenes',13.42,'2019-11-03','Thriller',_binary '\0','https://youtu.be/sOnqjkJTMaA'),(6,3,'Noticias','YgRPec6WP4p','mucha emocion',6.26,'2019-11-03','100 años de FING',_binary '\0','https://youtu.be/peGS4TBxSaI'),(7,3,'Noticias','DH1VwXy0weT','la mitad que la fing jeje',27.22,'2019-11-03','50 años del InCo',_binary '\0','https://youtu.be/GzOJSk4urlM'),(8,3,'Noticias','Lau4GGMK1JS','muy buenos trabajos',1,'2019-11-03','Ingeniería de Muestra 2017',_binary '\0','https://youtu.be/RnaYRA1k5j4'),(9,5,'Carnaval','DbOAxCw38VF','esta estuvo dificil',57.15,'2019-11-03','Etapa A contramano Liguilla',_binary '','https://youtu.be/Es6GRMHXeCQ'),(10,5,'Carnaval','zrYOX40xwzj','esta estuvo facil',51.38,'2019-11-03','Etapa Don Timoteo Liguilla',_binary '','https://youtu.be/I_spHBU9ZsI'),(11,6,'Deporte','EHSNydXppb6','tremendos goles variados',4.23,'2019-11-03','Show de goles',_binary '\0','https://youtu.be/g46w4_kD_lA'),(12,4,'Deporte','3DwsLQBcEzW','tremendos goles de pacheco',5.48,'2019-11-03','Pacheco goles más recordados',_binary '','https://youtu.be/wlEd6-HsIxI'),(13,6,'Deporte','GiXaJ57aNLV','Presentacion de el nuevo estadio peñarol',207.26,'2019-11-03','Inauguración Estadio Peñarol',_binary '\0','https://youtu.be/U6XPJ8Vz72A'),(14,5,'Deporte','LzlqAkCeIFJ','tremendos goles de recoba',13.36,'2019-11-03','Recoba 20 mejores goles',_binary '','https://youtu.be/Gy3fZhWdLEQ'),(15,15,'Ciencia y Tecnología','O9fQMd2jR0o','entrevista realizada el 13/7/1527',5.39,'2019-11-03','Entrevista a director CUTI',_binary '','https://youtu.be/Eq5uBEzI6qs'),(16,15,'Ciencia y Tecnología','X0Fwcvbr4Ks','Muy complicado este tema, hicimos un video para aclarar',19.21,'2019-11-03','Ventana al futuro Uruguay y déficit de ingenieros',_binary '','https://youtu.be/zBR2pnASlQE');
/*!40000 ALTER TABLE `VIDEO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-03 15:18:24
