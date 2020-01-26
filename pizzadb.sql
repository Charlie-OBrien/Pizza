-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: pizzadb
-- ------------------------------------------------------
-- Server version	5.7.26

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
-- Table structure for table `crusttypes`
--

DROP TABLE IF EXISTS `crusttypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crusttypes` (
  `crustTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`crustTypeId`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crusttypes`
--

LOCK TABLES `crusttypes` WRITE;
/*!40000 ALTER TABLE `crusttypes` DISABLE KEYS */;
INSERT INTO `crusttypes` VALUES (1,'Thin Crust'),(2,'Handmade Pan'),(3,'Original'),(4,'Gluten'),(5,'Chicago Style');
/*!40000 ALTER TABLE `crusttypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerid` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `houseNumber` int(11) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `province` varchar(2) DEFAULT NULL,
  `postalCode` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (13,'Mike','Bee','204-290-2363','mikeb@mail.com',999,'Some Street','NS','M43-1B7'),(12,'Jimmy','Jones','204-888-6000','jimmy.jones@mail.com',2600,'Portage Ave','MB','R3J 2G3'),(11,'Jack','O\'Brien','506-333-1901','charlesobriennb@gmail.com',26,'Chippin','NB','E3B-4S5'),(10,'Charlie','O\'Brien','506-292-4563','charlie@charlesjobrien.com',26,'Chippin','NB','E3B 4S5'),(14,'Above','None-Of-The','204-989-6000','noneoftheabove@mail.com',420,'Pineapple-Express Ave','MB','R1J 3D0'),(15,'Rick & Morty',' ','555-555-5555','jeff@amazon.com',742,'Spooner','ON','A1A-1A1');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employeeid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`employeeid`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'admin','12345');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `totalPrice` float NOT NULL DEFAULT '0',
  `deliveryDateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `placedDateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `customerId` int(11) NOT NULL,
  `orderStatus` varchar(45) NOT NULL DEFAULT 'PENDING',
  PRIMARY KEY (`orderId`),
  KEY `customeridFK_idx` (`customerId`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (9,29.88,'2019-12-11 09:01:48','2019-12-11 08:31:48',12,'filled'),(8,149.39,'2019-12-11 08:56:25','2019-12-11 08:26:25',11,'filled'),(7,24.13,'2019-12-11 08:35:34','2019-12-11 08:05:34',10,'pending'),(10,29.88,'2019-12-11 09:13:04','2019-12-11 08:43:04',13,'pending'),(11,29.88,'2019-12-11 09:15:21','2019-12-11 08:45:21',14,'filled'),(12,18.38,'2019-12-11 09:33:01','2019-12-11 09:03:01',15,'pending');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza`
--

DROP TABLE IF EXISTS `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza` (
  `pizzaId` int(11) NOT NULL AUTO_INCREMENT,
  `sizeId` int(11) NOT NULL,
  `isFinished` tinyint(4) NOT NULL DEFAULT '0',
  `crustTypeId` int(11) NOT NULL,
  `price` float NOT NULL,
  `orderId` int(11) NOT NULL,
  PRIMARY KEY (`pizzaId`),
  KEY `crusttypeFK_idx` (`crustTypeId`),
  KEY `sizeidFK_idx` (`sizeId`),
  KEY `orderidFK_idx` (`orderId`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` VALUES (23,3,1,3,25.98,8),(22,3,1,3,25.98,8),(21,3,1,3,25.98,8),(20,3,1,3,25.98,8),(19,3,1,3,25.98,8),(27,1,1,1,15.98,12),(26,3,1,3,25.98,11),(25,3,1,3,25.98,10),(24,3,1,3,25.98,9),(18,2,1,2,20.98,7);
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza_toppings_map`
--

DROP TABLE IF EXISTS `pizza_toppings_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza_toppings_map` (
  `toppingId` int(11) NOT NULL,
  `pizzaId` int(11) NOT NULL,
  `pizza_toppings_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pizza_toppings_id`),
  KEY `pizzaidFK_idx` (`pizzaId`),
  KEY `toppingidFK` (`toppingId`)
) ENGINE=MyISAM AUTO_INCREMENT=153 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza_toppings_map`
--

LOCK TABLES `pizza_toppings_map` WRITE;
/*!40000 ALTER TABLE `pizza_toppings_map` DISABLE KEYS */;
INSERT INTO `pizza_toppings_map` VALUES (17,27,152),(17,26,151),(5,26,150),(17,25,149),(5,25,148),(7,24,147),(5,24,146),(17,23,145),(5,23,144),(17,22,143),(5,22,142),(17,21,141),(5,21,140),(17,20,139),(5,20,138),(17,19,137),(5,19,136),(8,18,135),(5,18,134);
/*!40000 ALTER TABLE `pizza_toppings_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sizes`
--

DROP TABLE IF EXISTS `sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sizes` (
  `sizeid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`sizeid`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sizes`
--

LOCK TABLES `sizes` WRITE;
/*!40000 ALTER TABLE `sizes` DISABLE KEYS */;
INSERT INTO `sizes` VALUES (1,'Small'),(2,'Medium'),(3,'Large');
/*!40000 ALTER TABLE `sizes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toppings`
--

DROP TABLE IF EXISTS `toppings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `toppings` (
  `toppingId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` float NOT NULL DEFAULT '0',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `empAddedBy` int(11) NOT NULL,
  `isActive` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`toppingId`),
  KEY `employeeidFK_idx` (`empAddedBy`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toppings`
--

LOCK TABLES `toppings` WRITE;
/*!40000 ALTER TABLE `toppings` DISABLE KEYS */;
INSERT INTO `toppings` VALUES (5,'cheese',0,'2017-11-05 06:35:40',1,1),(6,'extra cheese',1.99,'2017-11-05 06:35:40',1,1),(7,'pepperoni',1.99,'2017-11-05 06:35:40',1,1),(8,'green peppers',1.99,'2017-11-05 06:35:40',1,1),(19,'anchovies',1.99,'2019-12-11 17:05:39',1,0),(18,'anchovies',1.99,'2019-12-11 16:25:15',1,0),(17,'pineapple',1.99,'2019-12-11 16:25:05',1,1);
/*!40000 ALTER TABLE `toppings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pizzadb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-11 11:23:47
