-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: market
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
-- Table structure for table `baskets`
--

DROP TABLE IF EXISTS `baskets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baskets` (
  `good_id` int NOT NULL,
  `order_id` int NOT NULL,
  `goods_count` int DEFAULT NULL,
  PRIMARY KEY (`good_id`,`order_id`),
  KEY `FKal3cgui8k9asq4yooe8op5d5c` (`order_id`),
  CONSTRAINT `FK8t8d5qf8h6a0dpuyluivno7ap` FOREIGN KEY (`good_id`) REFERENCES `goods` (`id`),
  CONSTRAINT `FKal3cgui8k9asq4yooe8op5d5c` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baskets`
--

LOCK TABLES `baskets` WRITE;
/*!40000 ALTER TABLE `baskets` DISABLE KEYS */;
/*!40000 ALTER TABLE `baskets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `count` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,80,'Масло',140),(2,100,'Хлеб',40),(3,50,'Сахар',80),(4,70,'Соль',25),(5,60,'Сыр',190),(6,90,'Молоко',80),(7,70,'Йогурт',50),(8,120,'Шоколад',60),(9,80,'Лимонад',75),(10,40,'Вода',40),(11,80,'Масло',140),(12,100,'Хлеб',40),(13,50,'Сахар',80),(14,70,'Соль',25),(15,60,'Сыр',190),(16,90,'Молоко',80),(17,70,'Йогурт',50),(18,120,'Шоколад',60),(19,80,'Лимонад',75),(20,40,'Вода',40),(21,80,'Масло',140),(22,100,'Хлеб',40),(23,50,'Сахар',80),(24,70,'Соль',25),(25,60,'Сыр',190),(26,90,'Молоко',80),(27,70,'Йогурт',50),(28,120,'Шоколад',60),(29,80,'Лимонад',75),(30,40,'Вода',40);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) DEFAULT NULL,
  `orderStatus` enum('NEW','PACKED','DELIVERED','PAYED','CANCELLED') DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `other_baskets`
--

DROP TABLE IF EXISTS `other_baskets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `other_baskets` (
  `good_id` int NOT NULL,
  `order_id` int NOT NULL,
  `goods_count` int DEFAULT NULL,
  PRIMARY KEY (`good_id`,`order_id`),
  KEY `FKaru6a27xor0gn2044l15o76pl` (`order_id`),
  CONSTRAINT `FKaru6a27xor0gn2044l15o76pl` FOREIGN KEY (`order_id`) REFERENCES `other_orders` (`id`),
  CONSTRAINT `FKmf3x5odp2f09qx583sowkxc82` FOREIGN KEY (`good_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `other_baskets`
--

LOCK TABLES `other_baskets` WRITE;
/*!40000 ALTER TABLE `other_baskets` DISABLE KEYS */;
/*!40000 ALTER TABLE `other_baskets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `other_goods`
--

DROP TABLE IF EXISTS `other_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `other_goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `count` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `other_goods`
--

LOCK TABLES `other_goods` WRITE;
/*!40000 ALTER TABLE `other_goods` DISABLE KEYS */;
INSERT INTO `other_goods` VALUES (1,80,'Масло',140),(2,100,'Хлеб',40),(3,50,'Сахар',80),(4,70,'Соль',25),(5,60,'Сыр',190),(6,90,'Молоко',80),(7,70,'Йогурт',50),(8,120,'Шоколад',60),(9,80,'Лимонад',75),(10,40,'Вода',40);
/*!40000 ALTER TABLE `other_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `other_orders`
--

DROP TABLE IF EXISTS `other_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `other_orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) DEFAULT NULL,
  `orderStatus` enum('NEW','PACKED','DELIVERED','PAYED','CANCELLED') DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `other_orders`
--

LOCK TABLES `other_orders` WRITE;
/*!40000 ALTER TABLE `other_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `other_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `registration_date` datetime(6) DEFAULT NULL,
  `user_credential_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpdrtsn40n6whuov280eqvrqw9` (`user_credential_id`),
  CONSTRAINT `FKpdrtsn40n6whuov280eqvrqw9` FOREIGN KEY (`user_credential_id`) REFERENCES `users_credentials` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_credentials`
--

DROP TABLE IF EXISTS `users_credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_credentials` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_credentials`
--

LOCK TABLES `users_credentials` WRITE;
/*!40000 ALTER TABLE `users_credentials` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_credentials` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-25 13:07:53
