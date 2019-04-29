-- MySQL dump 10.13  Distrib 8.0.13, for Linux (x86_64)
--
-- Host: localhost    Database: mairie
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actServ`
--

DROP TABLE IF EXISTS `actServ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `actServ` (
  `typeAct` char(40) DEFAULT NULL,
  `service` varchar(20) DEFAULT NULL,
  KEY `Fk_actServ_activites` (`typeAct`),
  KEY `Fk_actServ_service` (`service`),
  CONSTRAINT `Fk_actServ_activites` FOREIGN KEY (`typeAct`) REFERENCES `activites` (`typeact`),
  CONSTRAINT `Fk_actServ_service` FOREIGN KEY (`service`) REFERENCES `service` (`libelle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actServ`
--

LOCK TABLES `actServ` WRITE;
/*!40000 ALTER TABLE `actServ` DISABLE KEYS */;
INSERT INTO `actServ` VALUES ('films','cinemaLivres'),('jeux','cinemaLivres'),('musique','cinemaLivres'),('sport','cinemaLivres'),('jeux','entretien'),('musique','entretien'),('sport','entretien'),('films','scolaire'),('jeux','scolaire'),('musique','scolaire'),('sport','scolaire'),('jeux','sport'),('films','sport'),('musique','sport'),('sport','sport'),('films','entretien');
/*!40000 ALTER TABLE `actServ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activites`
--

DROP TABLE IF EXISTS `activites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activites` (
  `typeAct` char(40) NOT NULL,
  PRIMARY KEY (`typeAct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activites`
--

LOCK TABLES `activites` WRITE;
/*!40000 ALTER TABLE `activites` DISABLE KEYS */;
INSERT INTO `activites` VALUES ('films'),('jeux'),('musique'),('sport');
/*!40000 ALTER TABLE `activites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `films`
--

DROP TABLE IF EXISTS `films`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `films` (
  `date` date DEFAULT NULL,
  `lieu` char(40) DEFAULT NULL,
  `responsable` char(40) DEFAULT NULL,
  `nbParticipants` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` char(40) NOT NULL,
  `nomAct` char(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Fk_films_activites` (`type`),
  CONSTRAINT `Fk_films_activites` FOREIGN KEY (`type`) REFERENCES `activites` (`typeact`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `films`
--

LOCK TABLES `films` WRITE;
/*!40000 ALTER TABLE `films` DISABLE KEYS */;
INSERT INTO `films` VALUES ('2019-04-21','Mediatheque','Monsieur Jefasio Octave',40,1,'films','Debout : Debut 21h05 01h30');
/*!40000 ALTER TABLE `films` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jeux`
--

DROP TABLE IF EXISTS `jeux`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `jeux` (
  `date` date DEFAULT NULL,
  `lieu` char(40) DEFAULT NULL,
  `responsable` char(40) DEFAULT NULL,
  `nbParticipants` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` char(40) NOT NULL,
  `nomAct` char(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Fk_jeux_activites` (`type`),
  CONSTRAINT `Fk_jeux_activites` FOREIGN KEY (`type`) REFERENCES `activites` (`typeact`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jeux`
--

LOCK TABLES `jeux` WRITE;
/*!40000 ALTER TABLE `jeux` DISABLE KEYS */;
INSERT INTO `jeux` VALUES ('2018-04-22','Les 33 Hectares_La plaine','Monsieur Mr Sarivoya Gerard',0,1,'jeux','Organisation Chasse aux oeufs'),('2019-04-20','Creche des tulipes','Monsieur Higono',0,2,'jeux','Jeux de societe : 09h00');
/*!40000 ALTER TABLE `jeux` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listeReserv`
--

DROP TABLE IF EXISTS `listeReserv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `listeReserv` (
  `type` varchar(40) NOT NULL,
  `NomEleve` varchar(25) NOT NULL,
  `Parents` varchar(25) NOT NULL,
  `Adresse` varchar(255) NOT NULL,
  `solde` int(11) NOT NULL,
  PRIMARY KEY (`type`,`NomEleve`,`Parents`,`Adresse`),
  CONSTRAINT `listeReserv_ibfk_1` FOREIGN KEY (`type`) REFERENCES `scolaire` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listeReserv`
--

LOCK TABLES `listeReserv` WRITE;
/*!40000 ALTER TABLE `listeReserv` DISABLE KEYS */;
INSERT INTO `listeReserv` VALUES ('Liste des reservations','Higogyn Sebastien','Higogyn Hector','91000 Evry 4,rue Pablo Picasso',80);
/*!40000 ALTER TABLE `listeReserv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `menus` (
  `type` varchar(40) NOT NULL,
  `date` date NOT NULL,
  `menuSemaine` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type`,`date`),
  CONSTRAINT `menus_ibfk_1` FOREIGN KEY (`type`) REFERENCES `scolaire` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
INSERT INTO `menus` VALUES ('Menus de la semaine','2019-04-29','Lundi :                                                       Mardi:           Mercredi:                      Jeudi:                                               Vendredi:                              ');
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musique`
--

DROP TABLE IF EXISTS `musique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `musique` (
  `date` date DEFAULT NULL,
  `lieu` char(40) DEFAULT NULL,
  `responsable` char(40) DEFAULT NULL,
  `nbParticipants` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` char(40) NOT NULL,
  `nomAct` char(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Fk_musique_activites` (`type`),
  CONSTRAINT `Fk_musique_activites` FOREIGN KEY (`type`) REFERENCES `activites` (`typeact`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musique`
--

LOCK TABLES `musique` WRITE;
/*!40000 ALTER TABLE `musique` DISABLE KEYS */;
INSERT INTO `musique` VALUES ('2019-04-21','Mairie','Madame Jofunsv',0,1,'musique','Niger musique  14h00'),('2019-06-21','Place Stalingrad','Madame Kivige',0,2,'musique','Fete de la musique : 18h30(5H)');
/*!40000 ALTER TABLE `musique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `santeAssoc`
--

DROP TABLE IF EXISTS `santeAssoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `santeAssoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identiteResp` varchar(50) DEFAULT NULL,
  `adresse` varchar(60) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `commentaire` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `santeAssoc`
--

LOCK TABLES `santeAssoc` WRITE;
/*!40000 ALTER TABLE `santeAssoc` DISABLE KEYS */;
INSERT INTO `santeAssoc` VALUES (1,'Karadjia igor','4,Boulevard charles de gaulle','Pediatre',NULL),(2,'Sagive Zonar','6,rue de la seine','medecineGenerale',NULL),(3,'Exoce Ozoir','4,Impasse contesse du Barry','Neurologue',NULL),(4,'Dinosa Gregoire','42,Avenue mont-de-marsan','Dentiste',NULL),(5,'Toxez Nadege','82,Boulevard charles de gaulle','Orthopediste',NULL),(6,'Yozami Igage','15,rue Georges Foulon','Neurologue',NULL),(7,'Villich Adèle','87,rue du Général Leclerc','Genecologue',NULL),(8,'Fitso Amelie','114,rue de la Libération','Associations','Restaurant du coeur');
/*!40000 ALTER TABLE `santeAssoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scolaire`
--

DROP TABLE IF EXISTS `scolaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `scolaire` (
  `type` varchar(40) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scolaire`
--

LOCK TABLES `scolaire` WRITE;
/*!40000 ALTER TABLE `scolaire` DISABLE KEYS */;
INSERT INTO `scolaire` VALUES ('Liste des reservations'),('Menus de la semaine');
/*!40000 ALTER TABLE `scolaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scolaireServ`
--

DROP TABLE IF EXISTS `scolaireServ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `scolaireServ` (
  `service` varchar(20) NOT NULL,
  `typeSco` varchar(30) NOT NULL,
  PRIMARY KEY (`service`,`typeSco`),
  KEY `scolaire_ibfk_1` (`typeSco`),
  CONSTRAINT `scolaireServ_ibfk_1` FOREIGN KEY (`service`) REFERENCES `service` (`libelle`),
  CONSTRAINT `scolaire_ibfk_1` FOREIGN KEY (`typeSco`) REFERENCES `scolaire` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scolaireServ`
--

LOCK TABLES `scolaireServ` WRITE;
/*!40000 ALTER TABLE `scolaireServ` DISABLE KEYS */;
INSERT INTO `scolaireServ` VALUES ('Scolaire','Liste des reservations'),('Scolaire','Menus de la semaine');
/*!40000 ALTER TABLE `scolaireServ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `service` (
  `libelle` varchar(20) NOT NULL,
  `droitAjoutActivites` tinyint(1) DEFAULT NULL,
  `droitModifActivites` tinyint(1) DEFAULT NULL,
  `droitAjoutScolaire` tinyint(1) DEFAULT NULL,
  `droitModifScolaire` tinyint(1) DEFAULT NULL,
  `droitAjoutSanteAssoc` tinyint(1) DEFAULT NULL,
  `droitModifSanteAssoc` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`libelle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES ('cinemaLivres',1,1,0,0,0,0),('entretien',1,1,1,1,0,0),('Scolaire',0,0,1,1,0,0),('sport',1,1,0,0,1,1);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sport`
--

DROP TABLE IF EXISTS `sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sport` (
  `date` date DEFAULT NULL,
  `lieu` char(40) DEFAULT NULL,
  `responsable` char(40) DEFAULT NULL,
  `nbParticipants` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` char(40) NOT NULL,
  `nomAct` char(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Fk_sport_activites` (`type`),
  CONSTRAINT `Fk_sport_activites` FOREIGN KEY (`type`) REFERENCES `activites` (`typeact`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport`
--

LOCK TABLES `sport` WRITE;
/*!40000 ALTER TABLE `sport` DISABLE KEYS */;
INSERT INTO `sport` VALUES ('2019-04-27','Stade Pablo Picasso','Madame Leballon',200,1,'sport','Course Scolaire : Dep 09h30');
/*!40000 ALTER TABLE `sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `login` varchar(20) NOT NULL,
  `mdp` varchar(20) NOT NULL,
  `service` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `service` (`service`),
  CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`service`) REFERENCES `service` (`libelle`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (1,'Karadjia','Arnold','aKaradjia','azerty','cinemaLivres'),(2,'Garlhon','Louis','lGarlhon','azerty','sport'),(3,'Onezon','Daniel','dOnezon','azerty','entretien'),(4,'Hilzon','Thomas','tHilzon','azerty','Scolaire'),(6,'Usbeq','Michel','mUsbeq','azerty','sport');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-29  9:52:19
