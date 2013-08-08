-- MySQL dump 10.13  Distrib 5.5.28, for Linux (x86_64)
--
-- Host: localhost    Database: brmspoc
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `EventTypes`
--

DROP TABLE IF EXISTS `EventTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EventTypes` (
  `InstanceId` bigint(20) NOT NULL,
  `eventTypes` varchar(255) DEFAULT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `FKB0E5621F7665489A` (`InstanceId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EventTypes`
--

LOCK TABLES `EventTypes` WRITE;
/*!40000 ALTER TABLE `EventTypes` DISABLE KEYS */;
/*!40000 ALTER TABLE `EventTypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NodeInstanceLog`
--

DROP TABLE IF EXISTS `NodeInstanceLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NodeInstanceLog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_date` datetime DEFAULT NULL,
  `nodeId` varchar(255) DEFAULT NULL,
  `nodeInstanceId` varchar(255) DEFAULT NULL,
  `nodeName` varchar(255) DEFAULT NULL,
  `processId` varchar(255) DEFAULT NULL,
  `processInstanceId` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NodeInstanceLog`
--

LOCK TABLES `NodeInstanceLog` WRITE;
/*!40000 ALTER TABLE `NodeInstanceLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `NodeInstanceLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProcessInstanceEventInfo`
--

DROP TABLE IF EXISTS `ProcessInstanceEventInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProcessInstanceEventInfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `eventType` varchar(255) DEFAULT NULL,
  `processInstanceId` bigint(20) NOT NULL,
  `OPTLOCK` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProcessInstanceEventInfo`
--

LOCK TABLES `ProcessInstanceEventInfo` WRITE;
/*!40000 ALTER TABLE `ProcessInstanceEventInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProcessInstanceEventInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProcessInstanceInfo`
--

DROP TABLE IF EXISTS `ProcessInstanceInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProcessInstanceInfo` (
  `InstanceId` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) DEFAULT NULL,
  `lastModificationDate` date DEFAULT NULL,
  `lastReadDate` date DEFAULT NULL,
  `processId` varchar(255) DEFAULT NULL,
  `processInstanceByteArray` longblob,
  `startDate` date DEFAULT NULL,
  `state` int(11) NOT NULL,
  `OPTLOCK` int(11) DEFAULT NULL,
  PRIMARY KEY (`InstanceId`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProcessInstanceInfo`
--

LOCK TABLES `ProcessInstanceInfo` WRITE;
/*!40000 ALTER TABLE `ProcessInstanceInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProcessInstanceInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProcessInstanceLog`
--

DROP TABLE IF EXISTS `ProcessInstanceLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProcessInstanceLog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_date` datetime DEFAULT NULL,
  `processId` varchar(255) DEFAULT NULL,
  `processInstanceId` bigint(20) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProcessInstanceLog`
--

LOCK TABLES `ProcessInstanceLog` WRITE;
/*!40000 ALTER TABLE `ProcessInstanceLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProcessInstanceLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SESSIONINFO_ID_SEQ`
--

DROP TABLE IF EXISTS `SESSIONINFO_ID_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SESSIONINFO_ID_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SESSIONINFO_ID_SEQ`
--

LOCK TABLES `SESSIONINFO_ID_SEQ` WRITE;
/*!40000 ALTER TABLE `SESSIONINFO_ID_SEQ` DISABLE KEYS */;
INSERT INTO `SESSIONINFO_ID_SEQ` VALUES (1);
/*!40000 ALTER TABLE `SESSIONINFO_ID_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SessionInfo`
--

DROP TABLE IF EXISTS `SessionInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SessionInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastModificationDate` datetime DEFAULT NULL,
  `rulesByteArray` longblob,
  `startDate` datetime DEFAULT NULL,
  `OPTLOCK` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SessionInfo`
--

LOCK TABLES `SessionInfo` WRITE;
/*!40000 ALTER TABLE `SessionInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `SessionInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VariableInstanceLog`
--

DROP TABLE IF EXISTS `VariableInstanceLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VariableInstanceLog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_date` datetime DEFAULT NULL,
  `processId` varchar(255) DEFAULT NULL,
  `processInstanceId` bigint(20) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `variableId` varchar(255) DEFAULT NULL,
  `variableInstanceId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VariableInstanceLog`
--

LOCK TABLES `VariableInstanceLog` WRITE;
/*!40000 ALTER TABLE `VariableInstanceLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `VariableInstanceLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WORKITEMINFO_ID_SEQ`
--

DROP TABLE IF EXISTS `WORKITEMINFO_ID_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WORKITEMINFO_ID_SEQ` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WORKITEMINFO_ID_SEQ`
--

LOCK TABLES `WORKITEMINFO_ID_SEQ` WRITE;
/*!40000 ALTER TABLE `WORKITEMINFO_ID_SEQ` DISABLE KEYS */;
INSERT INTO `WORKITEMINFO_ID_SEQ` VALUES (1);
/*!40000 ALTER TABLE `WORKITEMINFO_ID_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WorkItemInfo`
--

DROP TABLE IF EXISTS `WorkItemInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WorkItemInfo` (
  `workItemId` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `processInstanceId` bigint(20) NOT NULL,
  `state` bigint(20) NOT NULL,
  `OPTLOCK` int(11) DEFAULT NULL,
  `workItemByteArray` longblob,
  PRIMARY KEY (`workItemId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WorkItemInfo`
--

LOCK TABLES `WorkItemInfo` WRITE;
/*!40000 ALTER TABLE `WorkItemInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `WorkItemInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_call`
--

DROP TABLE IF EXISTS `_call`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_call` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `currentattendantgroup` varchar(255) DEFAULT NULL,
  `attendantshistory` varchar(255) DEFAULT NULL,
  `customeraccount` varchar(255) DEFAULT NULL,
  `customertype` varchar(255) DEFAULT NULL,
  `processid` bigint(20) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `servicelevel` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT 'RUNNING',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_call`
--

LOCK TABLES `_call` WRITE;
/*!40000 ALTER TABLE `_call` DISABLE KEYS */;
/*!40000 ALTER TABLE `_call` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `callfield`
--

DROP TABLE IF EXISTS `callfield`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `callfield` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `callid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC203F15C5096C973` (`callid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `callfield`
--

LOCK TABLES `callfield` WRITE;
/*!40000 ALTER TABLE `callfield` DISABLE KEYS */;
/*!40000 ALTER TABLE `callfield` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemuser`
--

DROP TABLE IF EXISTS `systemuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `ticketsOwned` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemuser`
--

LOCK TABLES `systemuser` WRITE;
/*!40000 ALTER TABLE `systemuser` DISABLE KEYS */;
INSERT INTO `systemuser` VALUES (1,'123','ATTENDANT','AVAILABLE',1,'atendente1'),(2,'123','ATTENDANT','BUSY',2,'atendente2'),(3,'123','SUPERVISOR','AVAILABLE',2,'supervisor1'),(4,'123','SUPERVISOR','BUSY',3,'supervisor2'),(5,'123','COORDINATOR','AVAILABLE',3,'coordenador1'),(6,'123','COORDINATOR','LUNCH',2,'coordenador2'),(7,'123','BACKOFFICE_PF','AVAILABLE',1,'backpf1'),(8,'123','BACKOFFICE_PF','LUNCH',2,'backpf2'),(9,'123','BACKOFFICE_PJ','AVAILABLE',3,'backpj1'),(10,'123','BACKOFFICE_PJ','AVAILABLE',2,'backpj2'),(11,'123','BACKOFFICE_REQUEST','BUSY',2,'backreq1'),(12,'123','BACKOFFICE_REQUEST','AVAILABLE',2,'backreq2'),(13,'123','BACKOFFICE_PLATINUM','LUNCH',3,'backpl1'),(14,'123','ATTENDANT','LUNCH',3,'unregistered'),(15,'123','BACKOFFICE_PLATINUM','LUNCH',1,'backpl2');
/*!40000 ALTER TABLE `systemuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-13 14:42:51
