/*
 Navicat Premium Data Transfer

 Source Server         : zzy
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : ski_bar

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 13/09/2022 15:14:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `activity_id` bigint(32) NOT NULL,
  `group_id` bigint(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `all_person` int(255) DEFAULT NULL,
  `remain_person` int(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `state` int(10) DEFAULT NULL,
  `approve` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_records
-- ----------------------------
DROP TABLE IF EXISTS `activity_records`;
CREATE TABLE `activity_records` (
  `record_id` bigint(32) NOT NULL,
  `activity_id` bigint(32) NOT NULL,
  `user_id` bigint(32) NOT NULL,
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_result
-- ----------------------------
DROP TABLE IF EXISTS `activity_result`;
CREATE TABLE `activity_result` (
  `result_id` bigint(32) NOT NULL,
  `activity_id` bigint(32) NOT NULL,
  `user_id` bigint(32) NOT NULL,
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `score` int(255) DEFAULT NULL,
  PRIMARY KEY (`result_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `group_id` bigint(32) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for group_records
-- ----------------------------
DROP TABLE IF EXISTS `group_records`;
CREATE TABLE `group_records` (
  `record_id` bigint(32) NOT NULL,
  `group_id` bigint(32) NOT NULL,
  `user_id` bigint(32) NOT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` bigint(32) NOT NULL,
  `group_id` bigint(32) NOT NULL,
  `send_user_id` bigint(32) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(32) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1567195276346888194, 'oppo', 'cf4b8daea76471fbcb2364fd2d837c07', '0', '7702024@gmail', '123sd', '1', '2022-09-07 00:57:26', 'user', 'user:visit');
INSERT INTO `user` VALUES (1568274808059441153, 'vivo', '527cde558b92fcb4d23c8cdd253b763d', '1', '7702asd4@gmail', '345345', '2', '2022-09-10 00:27:06', 'admin', 'admin:manage');
INSERT INTO `user` VALUES (1568634429643149314, 'papi', '74e0d209e2e1ed3a662997b8881ea764', '1', '02asd4@gmail', '34123', '0', '2022-09-11 00:16:07', 'user', 'user:visit');
INSERT INTO `user` VALUES (1569363872909045762, 'jay', '7e92b4b5492ff2d846aee25e0f72ae9e', '1', '02asd4@gmail', '34123', 'user', '2022-09-13 00:34:39', 'user', 'user:visit');
COMMIT;

-- ----------------------------
-- Procedure structure for NewProc
-- ----------------------------
DROP PROCEDURE IF EXISTS `NewProc`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `NewProc`()
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


END;
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
