/*
 Navicat Premium Data Transfer

 Source Server         : xuyiwei
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : ski_bar

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 11/10/2022 17:39:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `activity_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT 'creatorid',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `all_person` int(11) NULL DEFAULT NULL,
  `remain_person` int(11) NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `end_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `state` int(11) NULL DEFAULT NULL,
  `approve` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 335 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (331, 22, 'Downhill Skiing', NULL, 6, 4, '2022-12-23 20:22:22', '2022-12-25 21:22:22', 0, 1, '2022-10-09 16:26:59', '2022-10-09 16:36:42', 3, 'Sydney');
INSERT INTO `activity` VALUES (332, 22, 'Backcountry Skiing', NULL, 4, 0, '2022-12-24 19:00:00', '2022-12-25 19:00:00', 0, 1, '2022-10-09 16:27:47', '2022-10-09 16:27:47', 1, 'Beijing');
INSERT INTO `activity` VALUES (334, 22, 'Freestyle skiing', NULL, 6, 0, '2022-11-12 20:00:00', '2022-11-14 21:00:00', 0, 0, '2022-10-11 15:16:05', '2022-10-11 15:16:05', 1, 'Sydney');

-- ----------------------------
-- Table structure for activity_records
-- ----------------------------
DROP TABLE IF EXISTS `activity_records`;
CREATE TABLE `activity_records`  (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_records
-- ----------------------------
INSERT INTO `activity_records` VALUES (30, 330, 21, NULL);
INSERT INTO `activity_records` VALUES (31, 330, 22, NULL);
INSERT INTO `activity_records` VALUES (33, 331, 22, NULL);
INSERT INTO `activity_records` VALUES (34, 331, 21, NULL);
INSERT INTO `activity_records` VALUES (35, 331, 23, NULL);
INSERT INTO `activity_records` VALUES (36, 331, 24, NULL);

-- ----------------------------
-- Table structure for activity_result
-- ----------------------------
DROP TABLE IF EXISTS `activity_result`;
CREATE TABLE `activity_result`  (
  `result_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `score` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`result_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_result
-- ----------------------------
INSERT INTO `activity_result` VALUES (47, 330, 22, '2022-10-08 20:32:08', 76);
INSERT INTO `activity_result` VALUES (48, 330, 21, '2022-10-08 20:32:08', 77);
INSERT INTO `activity_result` VALUES (49, 331, 22, '2022-10-09 16:36:42', 100);
INSERT INTO `activity_result` VALUES (50, 331, 21, '2022-10-09 16:36:42', 99);

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `group_id` bigint(20) NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_records
-- ----------------------------
DROP TABLE IF EXISTS `group_records`;
CREATE TABLE `group_records`  (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL,
  `send_user_id` bigint(20) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(96) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `roles` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `perms` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(32) NULL DEFAULT NULL,
  `level` int(32) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (20, 'oppo', 'bedfe476f8a2800b2711c7fdbc4d1e50', '1', '4827020@qq.com', '14324535', '2022-10-07 23:48:41', 'admin', 'admin:manage', 23, 0);
INSERT INTO `user` VALUES (21, 'jack', 'bedfe476f8a2800b2711c7fdbc4d1e50', '1', '511878701@qq.com', '55662233', '2022-10-08 17:44:50', 'user', 'user:visit', 23, 0);
INSERT INTO `user` VALUES (22, 'mike', 'bedfe476f8a2800b2711c7fdbc4d1e50', '1', '511878701@qq.com', '8757821', '2022-10-08 19:46:59', 'user', 'user:visit', 33, 0);
INSERT INTO `user` VALUES (23, 'tom', 'bedfe476f8a2800b2711c7fdbc4d1e50', '1', '511878701@qq.com', '88819921', '2022-10-09 16:40:49', 'user', 'user:visit', 21, 0);
INSERT INTO `user` VALUES (24, 'lucy', 'bedfe476f8a2800b2711c7fdbc4d1e50', '2', '511878701@qq.com', '8841133', '2022-10-09 18:05:06', 'user', 'user:visit', 23, 0);

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
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
