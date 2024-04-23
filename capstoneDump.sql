-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: capstone
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity_log`
--

Drop database if exists capstone;
Create Database capstone;
use capstone;

DROP TABLE IF EXISTS `activity_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_log` (
  `logID` int NOT NULL AUTO_INCREMENT,
  `userID` varchar(255) DEFAULT NULL,
  `loginStatus` tinyint(1) DEFAULT '0',
  `activity` varchar(255) NOT NULL,
  `timeStamp` varchar(255) NOT NULL,
  PRIMARY KEY (`logID`),
  KEY `userID` (`userID`),
  CONSTRAINT `activity_log_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_log`
--

LOCK TABLES `activity_log` WRITE;
/*!40000 ALTER TABLE `activity_log` DISABLE KEYS */;
INSERT INTO `activity_log` VALUES (1,'user01',1,'Logged in','10-11-2023'),(2,'user02',1,'Logged in','10-11-2023'),(3,'user01',0,'Logged out','10-11-2023'),(4,'admin01',1,'Logged in','20-11-2023');
/*!40000 ALTER TABLE `activity_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `bookID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `summary` text,
  PRIMARY KEY (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'The Great Gatsby','F. Scott Fitzgerald','Novel','A portrayal of the Roaring Twenties, this novel explores themes of decadence, idealism, and resistance to change, focusing on millionaire Jay Gatsby and his infatuation with the beautiful Daisy Buchanan.'),(2,'To Kill a Mockingbird','Harper Lee','Southern Gothic','Narrated by young Scout Finch, the story delves into the roots of human behavior as she witnesses racial injustice and societal complexities in the American South.'),(3,'1984','George Orwell','Dystopian','Set in a totalitarian society, the story follows Winston Smith as he grapples with oppression, surveillance, and rebellion in a world where the Party seeks to control every aspect of life.'),(4,'The Catcher in the Rye','J.D. Salinger','Realistic Fiction','Following the experiences of teenager Holden Caulfield as he navigates the challenges and alienation of adolescence, trying to find a place in an adult world he finds phony.'),(5,'NewBook','BookAuthor','Book','A New Book.'),(6,'NewBook2','Books','Books','Books'),(7,'book3','book3','book3','book3'),(8,'book4','book4','book4','book4'),(9,'book5','book5','book5','book5');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `library` (
  `libraryID` int NOT NULL AUTO_INCREMENT,
  `bookID` int DEFAULT NULL,
  `availability` tinyint(1) DEFAULT '1',
  `stock` int NOT NULL,
  PRIMARY KEY (`libraryID`),
  KEY `bookID` (`bookID`),
  CONSTRAINT `library_ibfk_1` FOREIGN KEY (`bookID`) REFERENCES `books` (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library`
--

LOCK TABLES `library` WRITE;
/*!40000 ALTER TABLE `library` DISABLE KEYS */;
INSERT INTO `library` VALUES (1,1,1,10),(2,2,1,8),(3,3,1,7),(4,4,1,10),(5,5,1,8),(6,6,1,8),(7,7,1,8),(8,8,1,5),(9,9,1,50);
/*!40000 ALTER TABLE `library` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notificationID` int NOT NULL AUTO_INCREMENT,
  `userID` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `recipient` varchar(255) NOT NULL,
  PRIMARY KEY (`notificationID`),
  KEY `userID` (`userID`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `ratingID` int NOT NULL AUTO_INCREMENT,
  `userID` varchar(255) DEFAULT NULL,
  `rating` int NOT NULL,
  `review` text,
  `bookID` int DEFAULT NULL,
  PRIMARY KEY (`ratingID`),
  KEY `userID` (`userID`),
  KEY `bookID` (`bookID`),
  CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  CONSTRAINT `rating_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `books` (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settings` (
  `settingID` int NOT NULL AUTO_INCREMENT,
  `userID` varchar(255) DEFAULT NULL,
  `emailVerified` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`settingID`),
  KEY `userID` (`userID`),
  CONSTRAINT `settings_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fName` varchar(255) NOT NULL,
  `lName` varchar(255) NOT NULL,
  `loginStatus` tinyint(1) DEFAULT '0',
  `isAdmin` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('a','a','a@a.com','b','b',0,0),('admin01','adminpass','admin@example.com','Admin','User',0,1),('drew','pass','a@a.com','a','a',0,0),('Joey','pass','Joey@email.com','Joey','Gilbert',0,0),('Miriam','pass','Miriam@email.com','Miriam','Gonzalezgaldamez',0,0),('user01','password123','john.doe@example.com','John','Doe',0,0),('user02','password456','jane.smith@example.com','Jane','Smith',0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-22 16:49:28
