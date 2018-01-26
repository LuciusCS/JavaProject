/*
 Navicat MySQL Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : database

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 26/01/2018 10:35:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for assets
-- ----------------------------
DROP TABLE IF EXISTS `assets`;
CREATE TABLE `assets`  (
  `AssetsID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TypeID` int(11) NOT NULL,
  `Model` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Price` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BuyDate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0000-00-00',
  `Status` int(11) NOT NULL DEFAULT 0,
  `Other` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`AssetsID`) USING BTREE,
  INDEX `TypeID`(`TypeID`) USING BTREE,
  CONSTRAINT `assets_ibfk_1` FOREIGN KEY (`TypeID`) REFERENCES `assetstype` (`TypeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of assets
-- ----------------------------
INSERT INTO `assets` VALUES (5, 'asdf', 1, '5456', '1345', '1345', 1, '1345');
INSERT INTO `assets` VALUES (6, 'xfgh', 1, 'fgh', 'shfg', 'gfhf', 1, 'fgh');
INSERT INTO `assets` VALUES (7, 'fgh', 1, 'fgh', 'hg', 'fhfh', 2, 'gf');
INSERT INTO `assets` VALUES (8, '413', 1, '235', '343', '0000-00-00', 3, '5546');
INSERT INTO `assets` VALUES (31, '4546', 2, '54', '354', '45', 3, '54');
INSERT INTO `assets` VALUES (32, '21', 1, '2345', '13', '0000-00-00', 3, '1445');
INSERT INTO `assets` VALUES (123, '23', 2, '123', '231', '32', 3, '145');

-- ----------------------------
-- Table structure for assetstype
-- ----------------------------
DROP TABLE IF EXISTS `assetstype`;
CREATE TABLE `assetstype`  (
  `TypeID` int(11) NOT NULL AUTO_INCREMENT,
  `B_Type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `S_Type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`TypeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of assetstype
-- ----------------------------
INSERT INTO `assetstype` VALUES (1, '123sds', '123sds');
INSERT INTO `assetstype` VALUES (2, '123sds', '123sds');
INSERT INTO `assetstype` VALUES (3, '123sds', '123sds');
INSERT INTO `assetstype` VALUES (4, 'qwe', 'qwe');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `PersonID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Dept` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Job` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Other` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`PersonID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES (6, 'ssdf', 'sdf', 'sdfs', 'dsf', 'sdfs');
INSERT INTO `person` VALUES (7, '测试', '女', 'asd', 'hajis', 'as');

SET FOREIGN_KEY_CHECKS = 1;
