/*
SQLyog Trial v13.1.8 (64 bit)
MySQL - 8.0.27 : Database - ski_bar
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ski_bar` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ski_bar`;

/*Table structure for table `activity` */

DROP TABLE IF EXISTS `activity`;

CREATE TABLE `activity` (
  `activity_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL COMMENT 'creatorid',
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `all_person` int DEFAULT NULL,
  `remain_person` int DEFAULT NULL,
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `state` int DEFAULT NULL,
  `approve` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `level` int DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `activity` */

insert  into `activity`(`activity_id`,`user_id`,`name`,`description`,`all_person`,`remain_person`,`start_time`,`end_time`,`state`,`approve`,`create_time`,`update_time`,`level`,`address`) values 
(1,1,'niuniu','niuniuzi',5,4,'2022-09-14 18:01:24','2022-09-14 18:01:24',1,0,'2022-09-23 16:54:13','2022-09-21 16:54:16',1,'beijing'),
(2,1,'das','fdsf',2,23,'2022-09-14 23:04:20','2022-09-14 23:04:20',1,0,'2022-09-22 14:47:58','2022-09-05 14:48:02',2,'shanghai'),
(312,1,'213214','123',1,3,'2022-09-14 23:04:23','2022-09-14 23:04:23',3,0,'2022-08-31 14:50:09','2022-09-24 14:50:11',3,'shenzhen'),
(1570032096526311425,1,'demoData',NULL,1,0,'2022-09-14 23:04:28','2022-09-14 23:04:28',0,0,'2022-09-14 20:49:56','2022-09-14 20:49:56',1,'demoData');

/*Table structure for table `activity_records` */

DROP TABLE IF EXISTS `activity_records`;

CREATE TABLE `activity_records` (
  `record_id` bigint NOT NULL,
  `activity_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `activity_records` */

/*Table structure for table `activity_result` */

DROP TABLE IF EXISTS `activity_result`;

CREATE TABLE `activity_result` (
  `result_id` bigint NOT NULL,
  `activity_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`result_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `activity_result` */

insert  into `activity_result`(`result_id`,`activity_id`,`user_id`,`time`,`score`) values 
(1,1,1,'2022-09-13 17:22:45',97),
(2,1,2,'2022-09-23 17:25:30',100),
(3,1,3,'2022-09-22 17:25:38',80),
(4,2,1,'2022-09-10 18:07:25',99);

/*Table structure for table `group` */

DROP TABLE IF EXISTS `group`;

CREATE TABLE `group` (
  `group_id` bigint NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `group` */

/*Table structure for table `group_records` */

DROP TABLE IF EXISTS `group_records`;

CREATE TABLE `group_records` (
  `record_id` bigint NOT NULL,
  `group_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `group_records` */

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `message_id` bigint NOT NULL,
  `group_id` bigint NOT NULL,
  `send_user_id` bigint NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `message` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` bigint NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(96) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `type` varchar(32) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `roles` varchar(32) DEFAULT NULL,
  `perms` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`password`,`gender`,`email`,`telephone`,`type`,`create_time`,`roles`,`perms`) values 
(1,'ruize','123','1','qwedoqi@qq.com','123e1','1','2022-09-13 17:19:21','user','user:visit'),
(2,'zhangze','123','1','3213412@qq.com','1231241','1','2022-09-13 17:20:13','user',NULL),
(3,'demoData','123','1','demoData','demoData','1','2022-09-13 17:20:34','user',NULL),
(1567195276346888194,'oppo','cf4b8daea76471fbcb2364fd2d837c07','0','7702024@gmail','123sd','1','2022-09-07 00:57:26','user','user:visit'),
(1568274808059441153,'vivo','527cde558b92fcb4d23c8cdd253b763d','1','7702asd4@gmail','345345','2','2022-09-10 00:27:06','admin','admin:manage'),
(1568634429643149314,'papi','74e0d209e2e1ed3a662997b8881ea764','1','02asd4@gmail','34123','0','2022-09-11 00:16:07','user','user:visit'),
(1569363872909045762,'jay','7e92b4b5492ff2d846aee25e0f72ae9e','1','02asd4@gmail','34123','user','2022-09-13 00:34:39','user','user:visit');

/* Procedure structure for procedure `NewProc` */

/*!50003 DROP PROCEDURE IF EXISTS  `NewProc` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `NewProc`()
BEGIN
  #Routine body goes here...
CREATE TABLE `user` (
`user_id` int(32) NOT NULL,
`username` varchar(32) NOT NULL,
`password` varchar(96) NOT NULL,
`gender` char(1) NULL,
`email` varchar(45) NULL,
`telephone` varchar(11) NULL,
`type` varchar(32) NOT NULL,
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`user_id`)
);

CREATE TABLE `activity_records` (
`record_id` int(32) NOT NULL,
`activity_id` int(32) NOT NULL,
`user_id` int(32) NOT NULL,
`time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`record_id`)
);

CREATE TABLE `activity` (
`activity_id` int(32) NOT NULL,
`group_id` int(32) NOT NULL,
`name` varchar(45) NULL,
`description` varchar(255) NULL,
`all_person` int(255) NULL,
`remain_person` int(255) NULL,
`start_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`end_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`state` int(10) NULL,
`approve` int(10) NULL,
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`activity_id`)
);

CREATE TABLE `activity_result` (
`result_id` int(32) NOT NULL,
`activity_id` int(32) NOT NULL,
`user_id` int(32) NOT NULL,
`time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`score` int(255) NULL,
PRIMARY KEY (`result_id`)
);

CREATE TABLE `group_records` (
`record_id` int(32) NOT NULL,
`group_id` int(32) NOT NULL,
`user_id` int(32) NOT NULL,
PRIMARY KEY (`record_id`)
);

CREATE TABLE `group` (
`group_id` int(32) NOT NULL,
`name` varchar(45) NULL,
`description` varchar(255) NULL,
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`group_id`)
);

CREATE TABLE `message` (
`message_id` int(32) NOT NULL,
`group_id` int(32) NOT NULL,
`send_user_id` int(32) NOT NULL,
`create_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`update_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`message_id`)
);


END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
