/*
SQLyog Community v11.51 (32 bit)
MySQL - 5.5.42 : Database - multikeywordranked
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `multikeywordranked`;

/*Table structure for table `consumerfiles` */

DROP TABLE IF EXISTS `consumerfiles`;

CREATE TABLE `consumerfiles` (
  `fileid` int(5) NOT NULL AUTO_INCREMENT,
  `filename` varchar(5000) DEFAULT NULL,
  `consumer` varchar(50) DEFAULT NULL,
  `provider` varchar(50) DEFAULT NULL,
  `filekey` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Table structure for table `files` */

DROP TABLE IF EXISTS `files`;

CREATE TABLE `files` (
  `fileid` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(5000) NOT NULL,
  `rank_` int(50) unsigned DEFAULT NULL,
  `key_` varchar(45) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `keyword` varchar(45) DEFAULT NULL,
  `cat` varchar(45) DEFAULT NULL,
  `userid` varchar(50) DEFAULT NULL,
  `filedata` varchar(50000) DEFAULT NULL,
  PRIMARY KEY (`fileid`,`key_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Table structure for table `regpage` */

DROP TABLE IF EXISTS `regpage`;

CREATE TABLE `regpage` (
  `name` varchar(45) NOT NULL DEFAULT '',
  `userid` varchar(45) NOT NULL DEFAULT '',
  `pass` varchar(45) NOT NULL DEFAULT '',
  `mail` varchar(45) NOT NULL DEFAULT '',
  `age` varchar(45) NOT NULL DEFAULT '',
  `loc` varchar(45) NOT NULL DEFAULT '',
  `sex` varchar(45) NOT NULL DEFAULT '',
  `time_` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `utype` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `request` */

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(5000) NOT NULL,
  `user` varchar(45) NOT NULL,
  `filename` varchar(5000) NOT NULL,
  `owner` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
