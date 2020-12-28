/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : push_center

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 28/12/2020 10:10:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
SET sql_safe_updates=0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `flag` int(1) NULL DEFAULT 0 COMMENT '发送短信的curl命令',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for app_role
-- ----------------------------
DROP TABLE IF EXISTS `app_role`;
CREATE TABLE `app_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '服务ID',
  `role_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限ID',
  `created_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `flag` int(1) NULL DEFAULT 0 COMMENT '发送短信的curl命令',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '服务对应的推送权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置标题',
  `description` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息  ',
  `provider_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方服务商名',
  `created_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `flag` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dic
-- ----------------------------
DROP TABLE IF EXISTS `dic`;
CREATE TABLE `dic`  (
  `id` int(8) NOT NULL DEFAULT 0,
  `key` int(8) NOT NULL,
  `value` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `flag` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `flag` int(1) NULL DEFAULT 0 COMMENT '发送短信的curl命令',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for script
-- ----------------------------
DROP TABLE IF EXISTS `script`;
CREATE TABLE `script`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `script_tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cinfig_id` int(8) NOT NULL COMMENT 'config表主键',
  `context` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `script_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `flag` int(1) NULL DEFAULT 0 COMMENT '发送短信的curl命令',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for secret_key
-- ----------------------------
DROP TABLE IF EXISTS `secret_key`;
CREATE TABLE `secret_key` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `app_id` bigint NOT NULL,
    `app_key` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'Key',
    `app_secret` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密钥',
    `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `flag` int DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='App的Key和Secret存储表';
-- ----------------------------
-- Table structure for sms_config
-- ----------------------------
DROP TABLE IF EXISTS `sms_config`;
CREATE TABLE `sms_config`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `config_id` int(8) NOT NULL COMMENT 'config表id',
  `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方短信服务配置的appId',
  `secret_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方短信服务配置的secretId',
  `secret_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方短信服务配置的secretKey',
  `created_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `flag` int(1) NULL DEFAULT 0 COMMENT '发送短信的curl命令',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_info
-- ----------------------------
DROP TABLE IF EXISTS `sms_info`;
CREATE TABLE `sms_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` int(8) NOT NULL COMMENT 'appid',
  `phone_num` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收人号码',
  `transmit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发送时间 ',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信内容',
  `provider` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信平台',
  `template_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模版id',
  `created_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_at` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `flag` int(1) NULL DEFAULT 0 COMMENT '是否已删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sms_info_id_sequence`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `sms_config_id` int(8) NOT NULL COMMENT 'sms_config表id',
  `template_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `example` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模版样例，其中的变量用括号标识出来，例如：【中轨技术】您的验证码是{code}，有效期{m}分钟',
  `created_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `flag` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for table `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `user_name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名',
    `user_real_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户真实姓名',
    `password` text COLLATE utf8_bin NOT NULL COMMENT '密码（加密）',
    `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `flag` int DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='管理平台用户';
SET FOREIGN_KEY_CHECKS = 1;
