/*
 Navicat Premium Data Transfer

 Source Server         : wshmang.f3322.net
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : wshmang.f3322.net:33306
 Source Schema         : push_center

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : 65001

 Date: 26/01/2021 11:23:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '服务ID',
  `app_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '服务名',
  `app_key` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '服务对应Key',
  `app_secret` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '服务对应密码',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '应用表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES (1, '告警中心', 'zte1611631143297BJjNA', 'f605974df0cc4635be07ddae886aa562O9WM8kyJUFzeItzM', 1, '2021-01-26 11:19:03', '2021-01-26 11:19:03', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for app_role
-- ----------------------------
DROP TABLE IF EXISTS `app_role`;
CREATE TABLE `app_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '服务权限ID',
  `app_id` bigint(20) NOT NULL COMMENT '服务ID',
  `mode_id` bigint(20) NOT NULL COMMENT '权限ID',
  `sms_template_id` bigint(20) NULL DEFAULT NULL COMMENT '模板ID',
  `wechat_id` bigint(20) NULL DEFAULT NULL COMMENT '公众号id',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态（0可用，1不可用）',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '服务对应的推送权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for application_info
-- ----------------------------
DROP TABLE IF EXISTS `application_info`;
CREATE TABLE `application_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `app_id` bigint(20) NOT NULL COMMENT 'appid',
  `message_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'message的uuid',
  `app_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app名称',
  `target_platform` tinyint(2) NULL DEFAULT NULL COMMENT '目标平台，1-android，2-ios，3-all',
  `audience` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '推送目标',
  `title` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容',
  `transmit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发送时间 ',
  `provider_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '推送平台',
  `delay` smallint(5) UNSIGNED NOT NULL COMMENT '延迟',
  `result` int(11) NOT NULL DEFAULT 0 COMMENT '发送结果',
  `fail_code` int(11) NULL DEFAULT NULL COMMENT '结果时间',
  `fail_reason` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '失败原因',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dic
-- ----------------------------
DROP TABLE IF EXISTS `dic`;
CREATE TABLE `dic`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `is_enable` tinyint(1) UNSIGNED NOT NULL COMMENT '状态：0-停用，1-启用',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dic
-- ----------------------------
INSERT INTO `dic` VALUES (1, 'push_method', '推送方式', 1, '消息推送方式字典', '2021-01-26 11:20:31', '2021-01-26 11:20:31', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for dic_data
-- ----------------------------
DROP TABLE IF EXISTS `dic_data`;
CREATE TABLE `dic_data`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `key` smallint(5) NOT NULL COMMENT '字典键值',
  `value` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典标签',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `order` tinyint(3) NULL DEFAULT NULL COMMENT '字典排序',
  `description` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `is_enable` tinyint(1) UNSIGNED NOT NULL COMMENT '状态：0-停用，1-启用',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dic_data
-- ----------------------------
INSERT INTO `dic_data` VALUES (1, 1, '短信', 'push_method', 1, '', 1, '2021-01-26 11:21:45', '2021-01-26 11:21:45', 'admin', 'admin', 0);
INSERT INTO `dic_data` VALUES (2, 3, '邮件', 'push_method', 2, '', 1, '2021-01-26 11:21:57', '2021-01-26 11:21:57', 'admin', 'admin', 0);
INSERT INTO `dic_data` VALUES (3, 3, 'App', 'push_method', 3, '', 1, '2021-01-26 11:22:25', '2021-01-26 11:22:25', 'admin', 'admin', 0);
INSERT INTO `dic_data` VALUES (4, 4, '微信', 'push_method', 4, '', 1, '2021-01-26 11:22:40', '2021-01-26 11:22:40', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for early_warn_config
-- ----------------------------
DROP TABLE IF EXISTS `early_warn_config`;
CREATE TABLE `early_warn_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `alarm_cycle` bigint(20) NOT NULL COMMENT '周期',
  `threshold` bigint(20) NOT NULL COMMENT '阈值',
  `alarm_interval` bigint(20) NOT NULL COMMENT '报警间隔',
  `user_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '处置人员，user表主键逗号拼接',
  `sms_template_id` bigint(20) NOT NULL COMMENT '短信模板id',
  `mail_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮件标题',
  `mail_body` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '邮件内容',
  `mail_provider_id` bigint(20) NULL DEFAULT NULL COMMENT '邮箱，消息平台id',
  `open_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '目标微信id，逗号拼接',
  `wechat_provider_id` bigint(20) NULL DEFAULT NULL COMMENT '公众号，消息平台id',
  `wechat_template_id` bigint(20) NULL DEFAULT NULL COMMENT '公众号模板',
  `wechat_data` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公众号内容',
  `app_id` bigint(20) NOT NULL COMMENT 'appId',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '预警配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for early_warn_info
-- ----------------------------
DROP TABLE IF EXISTS `early_warn_info`;
CREATE TABLE `early_warn_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `time` timestamp(0) NOT NULL COMMENT '预警时间',
  `reason` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '预警原因',
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '预警内容',
  `disposer` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '预警记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mail_info
-- ----------------------------
DROP TABLE IF EXISTS `mail_info`;
CREATE TABLE `mail_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `app_id` bigint(20) NOT NULL COMMENT '服务ID',
  `app_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '服务名称',
  `message_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'messageId',
  `receive_address` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '收件地址',
  `cc_address` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '抄送地址',
  `mail_title` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮件标题',
  `mail_body` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '邮件正文',
  `provider_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '短信平台',
  `transmit_time` timestamp(0) NULL DEFAULT NULL COMMENT '发送时间',
  `result` int(11) NOT NULL DEFAULT 0 COMMENT '发送结果（0成功，1失败）',
  `fail_code` int(11) NULL DEFAULT NULL COMMENT '失败code',
  `fail_reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '失败原因',
  `delay` int(10) NULL DEFAULT NULL COMMENT '延迟',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单管理id',
  `is_catalog` tinyint(4) NULL DEFAULT 1 COMMENT '是否为目录',
  `catalog_id` bigint(20) NULL DEFAULT NULL COMMENT '目录id',
  `is_menu` tinyint(4) NULL DEFAULT 1 COMMENT '是否为菜单',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  `menu_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '名称',
  `menu_type` tinyint(4) NULL DEFAULT NULL COMMENT '类型',
  `menu_icon` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标',
  `menu_sort` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '排序',
  `menu_url` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '路由地址',
  `role_identify` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限标识',
  `component_path` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组件路径',
  `is_use` tinyint(4) NULL DEFAULT 0 COMMENT '是否使用',
  `is_show` tinyint(4) NULL DEFAULT 0 COMMENT '是否显示',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (4,0,NULL,1,NULL,'首页',0,'dashboard','1','/',NULL,NULL,0,0,'2021-01-26 05:59:29','2021-01-26 05:59:29','admin','admin',0);
INSERT INTO `menu` VALUES (5,1,4,0,NULL,'首页',1,'dashboard','1','dashboard','list','dashboard/index',0,0,'2021-01-26 05:59:49','2021-01-26 05:59:49','admin','admin',0);
INSERT INTO `menu` VALUES (6,0,NULL,1,NULL,'系统管理',0,'el-icon-s-platform','9','/system',NULL,NULL,0,0,'2021-01-26 06:01:08','2021-01-26 06:01:08','admin','admin',0);
INSERT INTO `menu` VALUES (7,1,6,0,NULL,'菜单管理',1,'','3','menu','system:tab:list','system/menu',0,0,'2021-01-26 06:21:09','2021-01-26 06:21:09','admin','admin',0);
INSERT INTO `menu` VALUES (8,1,NULL,1,7,'菜单新增',2,'','1','','system:tab:add','',0,0,'2021-01-26 01:52:17','2021-01-26 01:52:17','admin','admin',0);
INSERT INTO `menu` VALUES (9,0,NULL,1,NULL,'消息推送',0,'guide','2','/sendMessage',NULL,NULL,0,0,'2021-01-26 06:00:05','2021-01-26 06:00:05','admin','admin',0);
INSERT INTO `menu` VALUES (10,1,NULL,1,7,'菜单修改',2,'','2',NULL,'system:tab:modify',NULL,0,0,'2021-01-26 01:58:47','2021-01-26 01:58:47','admin','admin',0);
INSERT INTO `menu` VALUES (11,1,NULL,1,7,'菜单删除',2,'','3',NULL,'system:tab:remove',NULL,0,0,'2021-01-26 01:59:03','2021-01-26 01:59:03','admin','admin',0);
INSERT INTO `menu` VALUES (13,1,6,0,NULL,'用户管理',1,'','1','user','system:user:list','system/user',0,0,'2021-01-26 06:20:57','2021-01-26 06:20:57','admin','admin',0);
INSERT INTO `menu` VALUES (14,1,6,0,NULL,'角色管理',1,'','2','role','system:role:list','system/role',0,0,'2021-01-26 06:21:05','2021-01-26 06:21:05','admin','admin',0);
INSERT INTO `menu` VALUES (15,1,NULL,1,14,'角色新增',2,'','1',NULL,'system:role:add',NULL,0,0,'2021-01-26 06:22:21','2021-01-26 06:22:21','admin','admin',0);
INSERT INTO `menu` VALUES (16,1,NULL,1,14,'角色修改',2,'','2',NULL,'system:role:modify',NULL,0,0,'2021-01-26 06:22:25','2021-01-26 06:22:25','admin','admin',0);
INSERT INTO `menu` VALUES (17,1,NULL,1,14,'角色删除',2,'','3',NULL,'system:role:remove',NULL,0,0,'2021-01-26 06:22:28','2021-01-26 06:22:28','admin','admin',0);
INSERT INTO `menu` VALUES (18,1,NULL,1,13,'用户新增',2,'','1',NULL,'system:user:add',NULL,0,0,'2021-01-26 06:22:01','2021-01-26 06:22:01','admin','admin',0);
INSERT INTO `menu` VALUES (19,1,NULL,1,13,'修改用户',2,'','2',NULL,'system:user:modify',NULL,0,0,'2021-01-26 06:22:05','2021-01-26 06:22:05','admin','admin',0);
INSERT INTO `menu` VALUES (20,1,NULL,1,13,'删除用户',2,'','3',NULL,'system:user:remove',NULL,0,0,'2021-01-26 06:22:08','2021-01-26 06:22:08','admin','admin',0);
INSERT INTO `menu` VALUES (21,1,NULL,1,13,'用户重置密码',2,'','4',NULL,'system:user:reset-psw',NULL,0,0,'2021-01-26 06:22:13','2021-01-26 06:22:13','admin','admin',0);
INSERT INTO `menu` VALUES (22,1,9,0,NULL,'短信推送',1,'','1','sms','sms:send','sendMessage/sms',0,0,'2021-01-26 07:23:33','2021-01-26 07:23:33','admin','admin',0);
INSERT INTO `menu` VALUES (23,1,9,0,NULL,'邮件推送',1,'','2','email','mail:send','sendMessage/email',0,0,'2021-01-26 07:23:33','2021-01-26 07:23:33','admin','admin',0);
INSERT INTO `menu` VALUES (24,1,9,0,NULL,'公众号推送',1,'','3','weixin','wechat:send','sendMessage/weixin',0,0,'2021-01-26 07:23:33','2021-01-26 07:23:33','admin','admin',0);
INSERT INTO `menu` VALUES (25,1,9,0,NULL,'App推送',1,'','4','app','app:send','sendMessage/app',0,0,'2021-01-26 07:23:33','2021-01-26 07:23:33','admin','admin',0);
INSERT INTO `menu` VALUES (26,1,6,0,NULL,'字典管理',1,'','4','dictionary','system:dic-type:list','system/dictionary',0,0,'2021-01-26 06:21:28','2021-01-26 06:21:28','admin','admin',0);
INSERT INTO `menu` VALUES (27,1,NULL,1,26,'新增字典类型',2,'','1',NULL,'system:dic-type:add',NULL,0,0,'2021-01-26 06:22:48','2021-01-26 06:22:48','admin','admin',0);
INSERT INTO `menu` VALUES (28,1,NULL,1,26,'修改字典',2,'','2',NULL,'system:dic-type:modify',NULL,0,0,'2021-01-26 06:22:52','2021-01-26 06:22:52','admin','admin',0);
INSERT INTO `menu` VALUES (29,1,NULL,1,26,'删除字典',2,'','3',NULL,'system:dic-type:remove',NULL,0,0,'2021-01-26 06:22:56','2021-01-26 06:22:56','admin','admin',0);
INSERT INTO `menu` VALUES (30,1,6,0,NULL,'字典数据',1,'','5','dictionaryData','system:dic-data:list','system/dictionaryData',0,1,'2021-01-26 06:21:32','2021-01-26 06:21:32','admin','admin',0);
INSERT INTO `menu` VALUES (31,1,NULL,1,30,'新增字典数据',2,'','1',NULL,'system:dic-data:add',NULL,0,0,'2021-01-26 06:23:03','2021-01-26 06:23:03','admin','admin',0);
INSERT INTO `menu` VALUES (32,1,NULL,1,30,'修改字典数据',2,'','2',NULL,'system:dic-data:modify',NULL,0,0,'2021-01-26 06:23:06','2021-01-26 06:23:06','admin','admin',0);
INSERT INTO `menu` VALUES (33,1,NULL,1,30,'删除字典数据',2,'','3',NULL,'system:dic-data:remove',NULL,0,0,'2021-01-26 06:23:09','2021-01-26 06:23:09','admin','admin',0);
INSERT INTO `menu` VALUES (34,0,NULL,1,NULL,'推送记录',0,'email','3','/sendRecord',NULL,NULL,0,0,'2021-01-26 06:00:18','2021-01-26 06:00:18','admin','admin',0);
INSERT INTO `menu` VALUES (35,1,34,0,NULL,'短信记录',1,'','1','smsRecord','sms:info:list','sendRecord/sms',0,0,'2021-01-26 06:08:40','2021-01-26 06:08:40','admin','admin',0);
INSERT INTO `menu` VALUES (36,1,34,0,NULL,'邮件记录',1,'','2','emailRecord','mail:info:list','sendRecord/email',0,0,'2021-01-26 06:08:45','2021-01-26 06:08:45','admin','admin',0);
INSERT INTO `menu` VALUES (37,1,34,0,NULL,'公众号记录',1,'','3','weixinRecord','wechat:info:list','sendRecord/weixin',0,0,'2021-01-26 06:08:49','2021-01-26 06:08:49','admin','admin',0);
INSERT INTO `menu` VALUES (38,1,34,0,NULL,'App记录',1,'','4','appRecord','app:info:list','sendRecord/app',0,0,'2021-01-26 06:08:52','2021-01-26 06:08:52','admin','admin',0);
INSERT INTO `menu` VALUES (39,0,NULL,1,NULL,'模板管理',0,'documentation','4','/tplManager',NULL,NULL,0,0,'2021-01-26 06:00:24','2021-01-26 06:00:24','admin','admin',0);
INSERT INTO `menu` VALUES (40,1,39,0,NULL,'短信模板配置',1,'','1','smsTpl','template:sms:list','tplManager/smsTpl',0,0,'2021-01-26 06:09:04','2021-01-26 06:09:04','admin','admin',0);
INSERT INTO `menu` VALUES (41,1,39,0,NULL,'公众号模板配置',1,'','2','weixinTpl','template:wechat:list','tplManager/weixinTpl',0,0,'2021-01-26 06:09:10','2021-01-26 06:09:10','admin','admin',0);
INSERT INTO `menu` VALUES (42,1,NULL,1,40,'新增短信模板',2,'','1',NULL,'template:sms:add',NULL,0,0,'2021-01-26 06:18:19','2021-01-26 06:18:19','admin','admin',0);
INSERT INTO `menu` VALUES (43,1,NULL,1,40,'修改短信模板',2,'','2',NULL,'template:sms:modify',NULL,0,0,'2021-01-26 06:18:26','2021-01-26 06:18:26','admin','admin',0);
INSERT INTO `menu` VALUES (44,1,NULL,1,40,'删除短信模板',2,'','3',NULL,'template:sms:remove',NULL,0,0,'2021-01-26 06:18:30','2021-01-26 06:18:30','admin','admin',0);
INSERT INTO `menu` VALUES (45,1,NULL,1,41,'新增微信模板',2,'','1',NULL,'template:wechat:add',NULL,0,0,'2021-01-26 06:18:40','2021-01-26 06:18:40','admin','admin',0);
INSERT INTO `menu` VALUES (46,1,NULL,1,41,'修改微信模板',2,'','2',NULL,'template:wechat:modify',NULL,0,0,'2021-01-26 06:18:44','2021-01-26 06:18:44','admin','admin',0);
INSERT INTO `menu` VALUES (47,1,NULL,1,41,'删除微信模板',2,'','3',NULL,'template:wechat:remove',NULL,0,0,'2021-01-26 06:18:48','2021-01-26 06:18:48','admin','admin',0);
INSERT INTO `menu` VALUES (48,0,NULL,1,NULL,'消息平台管理',0,'example','5','/msgPlatform',NULL,NULL,0,0,'2021-01-26 06:00:32','2021-01-26 06:00:32','admin','admin',0);
INSERT INTO `menu` VALUES (49,1,48,1,48,'消息平台管理',2,'example','0','index','provider:config:add','msgPlatform/index',0,0,'2021-01-26 05:38:15','2021-01-26 05:38:15','admin','admin',0);
INSERT INTO `menu` VALUES (50,0,NULL,1,NULL,'应用配置',0,'set','6','/appSet',NULL,NULL,0,0,'2021-01-26 06:00:40','2021-01-26 06:00:40','admin','admin',0);
INSERT INTO `menu` VALUES (51,1,50,0,NULL,'应用配置',1,'set','1','appSet','application:config:list','appSet/index',0,0,'2021-01-26 06:19:17','2021-01-26 06:19:17','admin','admin',0);
INSERT INTO `menu` VALUES (52,0,NULL,1,NULL,'数据统计',0,'dashboard','7','/dataStatistics',NULL,NULL,0,0,'2021-01-26 06:00:45','2021-01-26 06:00:45','admin','admin',0);
INSERT INTO `menu` VALUES (53,1,52,0,NULL,'分类统计',1,'','1','sortData','statistic:type:list','dataStatistics/sortData',0,0,'2021-01-26 06:19:28','2021-01-26 06:19:28','admin','admin',0);
INSERT INTO `menu` VALUES (54,1,52,0,NULL,'应用统计',1,'','2','appData','statistic:application:list','dataStatistics/appData',0,0,'2021-01-26 06:19:34','2021-01-26 06:19:34','admin','admin',0);
INSERT INTO `menu` VALUES (55,1,52,0,NULL,'条件统计',1,'','3','conditionData','statistic:condition:list','dataStatistics/conditionData',0,0,'2021-01-26 06:19:37','2021-01-26 06:19:37','admin','admin',0);
INSERT INTO `menu` VALUES (56,1,52,0,NULL,'消息平台统计',1,'','4','platData','statistic:provider:list','dataStatistics/platData',0,0,'2021-01-26 06:19:42','2021-01-26 06:19:42','admin','admin',0);
INSERT INTO `menu` VALUES (57,0,NULL,1,NULL,'预警管理',0,'warning','8','/warning',NULL,NULL,0,0,'2021-01-26 06:00:50','2021-01-26 06:00:50','admin','admin',0);
INSERT INTO `menu` VALUES (58,1,57,0,NULL,'预警配置',1,'','1','warningSet','early-warn','warning/warningSet',0,0,'2021-01-26 06:19:54','2021-01-26 06:19:54','admin','admin',0);
INSERT INTO `menu` VALUES (59,1,NULL,1,58,'查看预警',2,'','1',NULL,'early-warn:config:query',NULL,0,0,'2021-01-26 06:20:15','2021-01-26 06:20:15','admin','admin',0);
INSERT INTO `menu` VALUES (60,1,NULL,1,58,'添加\\修改预警',2,'','2',NULL,'early-warn:config:modify',NULL,0,0,'2021-01-26 06:20:18','2021-01-26 06:20:18','admin','admin',0);
INSERT INTO `menu` VALUES (61,1,57,0,NULL,'预警记录',1,'','2','warningData','early-warn:info:list','warning/warningData',0,0,'2021-01-26 06:19:57','2021-01-26 06:19:57','admin','admin',0);
INSERT INTO `menu` VALUES (62,1,NULL,1,51,'新增应用',2,'','1',NULL,'application:config:add',NULL,0,0,'2021-01-26 06:16:38','2021-01-26 06:16:38','admin','admin',0);
INSERT INTO `menu` VALUES (63,1,NULL,1,51,'修改应用',2,'','2',NULL,'application:config:modify',NULL,0,0,'2021-01-26 06:16:43','2021-01-26 06:16:43','admin','admin',0);
INSERT INTO `menu` VALUES (64,1,NULL,1,51,'删除应用',2,'','3',NULL,'application:config:remove',NULL,0,0,'2021-01-26 06:16:47','2021-01-26 06:16:47','admin','admin',0);
INSERT INTO `menu` VALUES (65,1,48,0,NULL,'消息平台管理',1,'example','1','msgPlatform','provider:config:list','msgPlatform/index',0,0,'2021-01-26 06:09:38','2021-01-26 06:09:38','admin','admin',0);
INSERT INTO `menu` VALUES (66,1,NULL,1,65,'新增消息平台',2,'','1',NULL,'provider:config:add',NULL,0,0,'2021-01-26 06:18:57','2021-01-26 06:18:57','admin','admin',0);
INSERT INTO `menu` VALUES (67,1,NULL,1,65,'修改消息平台',2,'','2',NULL,'provider:config:modify',NULL,0,0,'2021-01-26 06:19:02','2021-01-26 06:19:02','admin','admin',0);
INSERT INTO `menu` VALUES (68,1,NULL,1,65,'删除消息平台',2,'','3',NULL,'provider:config:remove',NULL,0,0,'2021-01-26 06:19:06','2021-01-26 06:19:06','admin','admin',0);
INSERT INTO `menu` VALUES (69,1,NULL,1,51,'应用权限配置',2,'','4',NULL,'application:config:role',NULL,0,0,'2021-01-26 06:16:52','2021-01-26 06:16:52','admin','admin',0);
INSERT INTO `menu` VALUES (70,1,NULL,1,51,'重置密钥',2,'','5',NULL,'application:config:reset-key',NULL,0,0,'2021-01-26 06:17:42','2021-01-26 06:17:42','admin','admin',0);

-- ----------------------------
-- Table structure for pinyin_config
-- ----------------------------
DROP TABLE IF EXISTS `pinyin_config`;
CREATE TABLE `pinyin_config`  (
  `pin_yin_` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `code_` int(11) NOT NULL,
  PRIMARY KEY (`code_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pinyin_config
-- ----------------------------
INSERT INTO `pinyin_config` VALUES ('zuo', 10254);
INSERT INTO `pinyin_config` VALUES ('zun', 10256);
INSERT INTO `pinyin_config` VALUES ('zui', 10260);
INSERT INTO `pinyin_config` VALUES ('zuan', 10262);
INSERT INTO `pinyin_config` VALUES ('zu', 10270);
INSERT INTO `pinyin_config` VALUES ('zou', 10274);
INSERT INTO `pinyin_config` VALUES ('zong', 10281);
INSERT INTO `pinyin_config` VALUES ('zi', 10296);
INSERT INTO `pinyin_config` VALUES ('zhuo', 10307);
INSERT INTO `pinyin_config` VALUES ('zhun', 10309);
INSERT INTO `pinyin_config` VALUES ('zhui', 10315);
INSERT INTO `pinyin_config` VALUES ('zhuang', 10322);
INSERT INTO `pinyin_config` VALUES ('zhuan', 10328);
INSERT INTO `pinyin_config` VALUES ('zhuai', 10329);
INSERT INTO `pinyin_config` VALUES ('zhua', 10331);
INSERT INTO `pinyin_config` VALUES ('zhu', 10519);
INSERT INTO `pinyin_config` VALUES ('zhou', 10533);
INSERT INTO `pinyin_config` VALUES ('zhong', 10544);
INSERT INTO `pinyin_config` VALUES ('zhi', 10587);
INSERT INTO `pinyin_config` VALUES ('zheng', 10764);
INSERT INTO `pinyin_config` VALUES ('zhen', 10780);
INSERT INTO `pinyin_config` VALUES ('zhe', 10790);
INSERT INTO `pinyin_config` VALUES ('zhao', 10800);
INSERT INTO `pinyin_config` VALUES ('zhang', 10815);
INSERT INTO `pinyin_config` VALUES ('zhan', 10832);
INSERT INTO `pinyin_config` VALUES ('zhai', 10838);
INSERT INTO `pinyin_config` VALUES ('zha', 11014);
INSERT INTO `pinyin_config` VALUES ('zeng', 11018);
INSERT INTO `pinyin_config` VALUES ('zen', 11019);
INSERT INTO `pinyin_config` VALUES ('zei', 11020);
INSERT INTO `pinyin_config` VALUES ('ze', 11024);
INSERT INTO `pinyin_config` VALUES ('zao', 11038);
INSERT INTO `pinyin_config` VALUES ('zang', 11041);
INSERT INTO `pinyin_config` VALUES ('zan', 11045);
INSERT INTO `pinyin_config` VALUES ('zai', 11052);
INSERT INTO `pinyin_config` VALUES ('za', 11055);
INSERT INTO `pinyin_config` VALUES ('yun', 11067);
INSERT INTO `pinyin_config` VALUES ('yue', 11077);
INSERT INTO `pinyin_config` VALUES ('yuan', 11097);
INSERT INTO `pinyin_config` VALUES ('yu', 11303);
INSERT INTO `pinyin_config` VALUES ('you', 11324);
INSERT INTO `pinyin_config` VALUES ('yong', 11339);
INSERT INTO `pinyin_config` VALUES ('yo', 11340);
INSERT INTO `pinyin_config` VALUES ('ying', 11358);
INSERT INTO `pinyin_config` VALUES ('yin', 11536);
INSERT INTO `pinyin_config` VALUES ('yi', 11589);
INSERT INTO `pinyin_config` VALUES ('ye', 11604);
INSERT INTO `pinyin_config` VALUES ('yao', 11781);
INSERT INTO `pinyin_config` VALUES ('yang', 11798);
INSERT INTO `pinyin_config` VALUES ('yan', 11831);
INSERT INTO `pinyin_config` VALUES ('ya', 11847);
INSERT INTO `pinyin_config` VALUES ('xun', 11861);
INSERT INTO `pinyin_config` VALUES ('xue', 11867);
INSERT INTO `pinyin_config` VALUES ('xuan', 12039);
INSERT INTO `pinyin_config` VALUES ('xu', 12058);
INSERT INTO `pinyin_config` VALUES ('xiu', 12067);
INSERT INTO `pinyin_config` VALUES ('xiong', 12074);
INSERT INTO `pinyin_config` VALUES ('xing', 12089);
INSERT INTO `pinyin_config` VALUES ('xin', 12099);
INSERT INTO `pinyin_config` VALUES ('xie', 12120);
INSERT INTO `pinyin_config` VALUES ('xiao', 12300);
INSERT INTO `pinyin_config` VALUES ('xiang', 12320);
INSERT INTO `pinyin_config` VALUES ('xian', 12346);
INSERT INTO `pinyin_config` VALUES ('xia', 12359);
INSERT INTO `pinyin_config` VALUES ('xi', 12556);
INSERT INTO `pinyin_config` VALUES ('wu', 12585);
INSERT INTO `pinyin_config` VALUES ('wo', 12594);
INSERT INTO `pinyin_config` VALUES ('weng', 12597);
INSERT INTO `pinyin_config` VALUES ('wen', 12607);
INSERT INTO `pinyin_config` VALUES ('wei', 12802);
INSERT INTO `pinyin_config` VALUES ('wang', 12812);
INSERT INTO `pinyin_config` VALUES ('wan', 12829);
INSERT INTO `pinyin_config` VALUES ('wai', 12831);
INSERT INTO `pinyin_config` VALUES ('wa', 12838);
INSERT INTO `pinyin_config` VALUES ('tuo', 12849);
INSERT INTO `pinyin_config` VALUES ('tun', 12852);
INSERT INTO `pinyin_config` VALUES ('tui', 12858);
INSERT INTO `pinyin_config` VALUES ('tuan', 12860);
INSERT INTO `pinyin_config` VALUES ('tu', 12871);
INSERT INTO `pinyin_config` VALUES ('tou', 12875);
INSERT INTO `pinyin_config` VALUES ('tong', 12888);
INSERT INTO `pinyin_config` VALUES ('ting', 13060);
INSERT INTO `pinyin_config` VALUES ('tie', 13063);
INSERT INTO `pinyin_config` VALUES ('tiao', 13068);
INSERT INTO `pinyin_config` VALUES ('tian', 13076);
INSERT INTO `pinyin_config` VALUES ('ti', 13091);
INSERT INTO `pinyin_config` VALUES ('teng', 13095);
INSERT INTO `pinyin_config` VALUES ('te', 13096);
INSERT INTO `pinyin_config` VALUES ('tao', 13107);
INSERT INTO `pinyin_config` VALUES ('tang', 13120);
INSERT INTO `pinyin_config` VALUES ('tan', 13138);
INSERT INTO `pinyin_config` VALUES ('tai', 13147);
INSERT INTO `pinyin_config` VALUES ('ta', 13318);
INSERT INTO `pinyin_config` VALUES ('suo', 13326);
INSERT INTO `pinyin_config` VALUES ('sun', 13329);
INSERT INTO `pinyin_config` VALUES ('sui', 13340);
INSERT INTO `pinyin_config` VALUES ('suan', 13343);
INSERT INTO `pinyin_config` VALUES ('su', 13356);
INSERT INTO `pinyin_config` VALUES ('sou', 13359);
INSERT INTO `pinyin_config` VALUES ('song', 13367);
INSERT INTO `pinyin_config` VALUES ('si', 13383);
INSERT INTO `pinyin_config` VALUES ('shuo', 13387);
INSERT INTO `pinyin_config` VALUES ('shun', 13391);
INSERT INTO `pinyin_config` VALUES ('shui', 13395);
INSERT INTO `pinyin_config` VALUES ('shuang', 13398);
INSERT INTO `pinyin_config` VALUES ('shuan', 13400);
INSERT INTO `pinyin_config` VALUES ('shuai', 13404);
INSERT INTO `pinyin_config` VALUES ('shua', 13406);
INSERT INTO `pinyin_config` VALUES ('shu', 13601);
INSERT INTO `pinyin_config` VALUES ('shou', 13611);
INSERT INTO `pinyin_config` VALUES ('shi', 13658);
INSERT INTO `pinyin_config` VALUES ('sheng', 13831);
INSERT INTO `pinyin_config` VALUES ('shen', 13847);
INSERT INTO `pinyin_config` VALUES ('she', 13859);
INSERT INTO `pinyin_config` VALUES ('shao', 13870);
INSERT INTO `pinyin_config` VALUES ('shang', 13878);
INSERT INTO `pinyin_config` VALUES ('shan', 13894);
INSERT INTO `pinyin_config` VALUES ('shai', 13896);
INSERT INTO `pinyin_config` VALUES ('sha', 13905);
INSERT INTO `pinyin_config` VALUES ('seng', 13906);
INSERT INTO `pinyin_config` VALUES ('sen', 13907);
INSERT INTO `pinyin_config` VALUES ('se', 13910);
INSERT INTO `pinyin_config` VALUES ('sao', 13914);
INSERT INTO `pinyin_config` VALUES ('sang', 13917);
INSERT INTO `pinyin_config` VALUES ('san', 14083);
INSERT INTO `pinyin_config` VALUES ('sai', 14087);
INSERT INTO `pinyin_config` VALUES ('sa', 14090);
INSERT INTO `pinyin_config` VALUES ('ruo', 14092);
INSERT INTO `pinyin_config` VALUES ('run', 14094);
INSERT INTO `pinyin_config` VALUES ('rui', 14097);
INSERT INTO `pinyin_config` VALUES ('ruan', 14099);
INSERT INTO `pinyin_config` VALUES ('ru', 14109);
INSERT INTO `pinyin_config` VALUES ('rou', 14112);
INSERT INTO `pinyin_config` VALUES ('rong', 14122);
INSERT INTO `pinyin_config` VALUES ('ri', 14123);
INSERT INTO `pinyin_config` VALUES ('reng', 14125);
INSERT INTO `pinyin_config` VALUES ('ren', 14135);
INSERT INTO `pinyin_config` VALUES ('re', 14137);
INSERT INTO `pinyin_config` VALUES ('rao', 14140);
INSERT INTO `pinyin_config` VALUES ('rang', 14145);
INSERT INTO `pinyin_config` VALUES ('ran', 14149);
INSERT INTO `pinyin_config` VALUES ('qun', 14151);
INSERT INTO `pinyin_config` VALUES ('que', 14159);
INSERT INTO `pinyin_config` VALUES ('quan', 14170);
INSERT INTO `pinyin_config` VALUES ('qu', 14345);
INSERT INTO `pinyin_config` VALUES ('qiu', 14353);
INSERT INTO `pinyin_config` VALUES ('qiong', 14355);
INSERT INTO `pinyin_config` VALUES ('qing', 14368);
INSERT INTO `pinyin_config` VALUES ('qin', 14379);
INSERT INTO `pinyin_config` VALUES ('qie', 14384);
INSERT INTO `pinyin_config` VALUES ('qiao', 14399);
INSERT INTO `pinyin_config` VALUES ('qiang', 14407);
INSERT INTO `pinyin_config` VALUES ('qian', 14429);
INSERT INTO `pinyin_config` VALUES ('qia', 14594);
INSERT INTO `pinyin_config` VALUES ('qi', 14630);
INSERT INTO `pinyin_config` VALUES ('pu', 14645);
INSERT INTO `pinyin_config` VALUES ('po', 14654);
INSERT INTO `pinyin_config` VALUES ('ping', 14663);
INSERT INTO `pinyin_config` VALUES ('pin', 14668);
INSERT INTO `pinyin_config` VALUES ('pie', 14670);
INSERT INTO `pinyin_config` VALUES ('piao', 14674);
INSERT INTO `pinyin_config` VALUES ('pian', 14678);
INSERT INTO `pinyin_config` VALUES ('pi', 14857);
INSERT INTO `pinyin_config` VALUES ('peng', 14871);
INSERT INTO `pinyin_config` VALUES ('pen', 14873);
INSERT INTO `pinyin_config` VALUES ('pei', 14882);
INSERT INTO `pinyin_config` VALUES ('pao', 14889);
INSERT INTO `pinyin_config` VALUES ('pang', 14894);
INSERT INTO `pinyin_config` VALUES ('pan', 14902);
INSERT INTO `pinyin_config` VALUES ('pai', 14908);
INSERT INTO `pinyin_config` VALUES ('pa', 14914);
INSERT INTO `pinyin_config` VALUES ('ou', 14921);
INSERT INTO `pinyin_config` VALUES ('o', 14922);
INSERT INTO `pinyin_config` VALUES ('nuo', 14926);
INSERT INTO `pinyin_config` VALUES ('nue', 14928);
INSERT INTO `pinyin_config` VALUES ('nuan', 14929);
INSERT INTO `pinyin_config` VALUES ('nv', 14930);
INSERT INTO `pinyin_config` VALUES ('nu', 14933);
INSERT INTO `pinyin_config` VALUES ('nong', 14937);
INSERT INTO `pinyin_config` VALUES ('niu', 14941);
INSERT INTO `pinyin_config` VALUES ('ning', 15109);
INSERT INTO `pinyin_config` VALUES ('nin', 15110);
INSERT INTO `pinyin_config` VALUES ('nie', 15117);
INSERT INTO `pinyin_config` VALUES ('niao', 15119);
INSERT INTO `pinyin_config` VALUES ('niang', 15121);
INSERT INTO `pinyin_config` VALUES ('nian', 15128);
INSERT INTO `pinyin_config` VALUES ('ni', 15139);
INSERT INTO `pinyin_config` VALUES ('neng', 15140);
INSERT INTO `pinyin_config` VALUES ('nen', 15141);
INSERT INTO `pinyin_config` VALUES ('nei', 15143);
INSERT INTO `pinyin_config` VALUES ('ne', 15144);
INSERT INTO `pinyin_config` VALUES ('nao', 15149);
INSERT INTO `pinyin_config` VALUES ('nang', 15150);
INSERT INTO `pinyin_config` VALUES ('nan', 15153);
INSERT INTO `pinyin_config` VALUES ('nai', 15158);
INSERT INTO `pinyin_config` VALUES ('na', 15165);
INSERT INTO `pinyin_config` VALUES ('mu', 15180);
INSERT INTO `pinyin_config` VALUES ('mou', 15183);
INSERT INTO `pinyin_config` VALUES ('mo', 15362);
INSERT INTO `pinyin_config` VALUES ('miu', 15363);
INSERT INTO `pinyin_config` VALUES ('ming', 15369);
INSERT INTO `pinyin_config` VALUES ('min', 15375);
INSERT INTO `pinyin_config` VALUES ('mie', 15377);
INSERT INTO `pinyin_config` VALUES ('miao', 15385);
INSERT INTO `pinyin_config` VALUES ('mian', 15394);
INSERT INTO `pinyin_config` VALUES ('mi', 15408);
INSERT INTO `pinyin_config` VALUES ('meng', 15416);
INSERT INTO `pinyin_config` VALUES ('men', 15419);
INSERT INTO `pinyin_config` VALUES ('mei', 15435);
INSERT INTO `pinyin_config` VALUES ('me', 15436);
INSERT INTO `pinyin_config` VALUES ('mao', 15448);
INSERT INTO `pinyin_config` VALUES ('mang', 15454);
INSERT INTO `pinyin_config` VALUES ('man', 15625);
INSERT INTO `pinyin_config` VALUES ('mai', 15631);
INSERT INTO `pinyin_config` VALUES ('ma', 15640);
INSERT INTO `pinyin_config` VALUES ('luo', 15652);
INSERT INTO `pinyin_config` VALUES ('lun', 15659);
INSERT INTO `pinyin_config` VALUES ('lue', 15661);
INSERT INTO `pinyin_config` VALUES ('luan', 15667);
INSERT INTO `pinyin_config` VALUES ('lv', 15681);
INSERT INTO `pinyin_config` VALUES ('lu', 15701);
INSERT INTO `pinyin_config` VALUES ('lou', 15707);
INSERT INTO `pinyin_config` VALUES ('long', 15878);
INSERT INTO `pinyin_config` VALUES ('liu', 15889);
INSERT INTO `pinyin_config` VALUES ('ling', 15903);
INSERT INTO `pinyin_config` VALUES ('lin', 15915);
INSERT INTO `pinyin_config` VALUES ('lie', 15920);
INSERT INTO `pinyin_config` VALUES ('liao', 15933);
INSERT INTO `pinyin_config` VALUES ('liang', 15944);
INSERT INTO `pinyin_config` VALUES ('lian', 15958);
INSERT INTO `pinyin_config` VALUES ('lia', 15959);
INSERT INTO `pinyin_config` VALUES ('li', 16155);
INSERT INTO `pinyin_config` VALUES ('leng', 16158);
INSERT INTO `pinyin_config` VALUES ('lei', 16169);
INSERT INTO `pinyin_config` VALUES ('le', 16171);
INSERT INTO `pinyin_config` VALUES ('lao', 16180);
INSERT INTO `pinyin_config` VALUES ('lang', 16187);
INSERT INTO `pinyin_config` VALUES ('lan', 16202);
INSERT INTO `pinyin_config` VALUES ('lai', 16205);
INSERT INTO `pinyin_config` VALUES ('la', 16212);
INSERT INTO `pinyin_config` VALUES ('kuo', 16216);
INSERT INTO `pinyin_config` VALUES ('kun', 16220);
INSERT INTO `pinyin_config` VALUES ('kui', 16393);
INSERT INTO `pinyin_config` VALUES ('kuang', 16401);
INSERT INTO `pinyin_config` VALUES ('kuan', 16403);
INSERT INTO `pinyin_config` VALUES ('kuai', 16407);
INSERT INTO `pinyin_config` VALUES ('kua', 16412);
INSERT INTO `pinyin_config` VALUES ('ku', 16419);
INSERT INTO `pinyin_config` VALUES ('kou', 16423);
INSERT INTO `pinyin_config` VALUES ('kong', 16427);
INSERT INTO `pinyin_config` VALUES ('keng', 16429);
INSERT INTO `pinyin_config` VALUES ('ken', 16433);
INSERT INTO `pinyin_config` VALUES ('ke', 16448);
INSERT INTO `pinyin_config` VALUES ('kao', 16452);
INSERT INTO `pinyin_config` VALUES ('kang', 16459);
INSERT INTO `pinyin_config` VALUES ('kan', 16465);
INSERT INTO `pinyin_config` VALUES ('kai', 16470);
INSERT INTO `pinyin_config` VALUES ('ka', 16474);
INSERT INTO `pinyin_config` VALUES ('jun', 16647);
INSERT INTO `pinyin_config` VALUES ('jue', 16657);
INSERT INTO `pinyin_config` VALUES ('juan', 16664);
INSERT INTO `pinyin_config` VALUES ('ju', 16689);
INSERT INTO `pinyin_config` VALUES ('jiu', 16706);
INSERT INTO `pinyin_config` VALUES ('jiong', 16708);
INSERT INTO `pinyin_config` VALUES ('jing', 16733);
INSERT INTO `pinyin_config` VALUES ('jin', 16915);
INSERT INTO `pinyin_config` VALUES ('jie', 16942);
INSERT INTO `pinyin_config` VALUES ('jiao', 16970);
INSERT INTO `pinyin_config` VALUES ('jiang', 16983);
INSERT INTO `pinyin_config` VALUES ('jian', 17185);
INSERT INTO `pinyin_config` VALUES ('jia', 17202);
INSERT INTO `pinyin_config` VALUES ('ji', 17417);
INSERT INTO `pinyin_config` VALUES ('huo', 17427);
INSERT INTO `pinyin_config` VALUES ('hun', 17433);
INSERT INTO `pinyin_config` VALUES ('hui', 17454);
INSERT INTO `pinyin_config` VALUES ('huang', 17468);
INSERT INTO `pinyin_config` VALUES ('huan', 17482);
INSERT INTO `pinyin_config` VALUES ('huai', 17487);
INSERT INTO `pinyin_config` VALUES ('hua', 17496);
INSERT INTO `pinyin_config` VALUES ('hu', 17676);
INSERT INTO `pinyin_config` VALUES ('hou', 17683);
INSERT INTO `pinyin_config` VALUES ('hong', 17692);
INSERT INTO `pinyin_config` VALUES ('heng', 17697);
INSERT INTO `pinyin_config` VALUES ('hen', 17701);
INSERT INTO `pinyin_config` VALUES ('hei', 17703);
INSERT INTO `pinyin_config` VALUES ('he', 17721);
INSERT INTO `pinyin_config` VALUES ('hao', 17730);
INSERT INTO `pinyin_config` VALUES ('hang', 17733);
INSERT INTO `pinyin_config` VALUES ('han', 17752);
INSERT INTO `pinyin_config` VALUES ('hai', 17759);
INSERT INTO `pinyin_config` VALUES ('ha', 17922);
INSERT INTO `pinyin_config` VALUES ('guo', 17928);
INSERT INTO `pinyin_config` VALUES ('gun', 17931);
INSERT INTO `pinyin_config` VALUES ('gui', 17947);
INSERT INTO `pinyin_config` VALUES ('guang', 17950);
INSERT INTO `pinyin_config` VALUES ('guan', 17961);
INSERT INTO `pinyin_config` VALUES ('guai', 17964);
INSERT INTO `pinyin_config` VALUES ('gua', 17970);
INSERT INTO `pinyin_config` VALUES ('gu', 17988);
INSERT INTO `pinyin_config` VALUES ('gou', 17997);
INSERT INTO `pinyin_config` VALUES ('gong', 18012);
INSERT INTO `pinyin_config` VALUES ('geng', 18181);
INSERT INTO `pinyin_config` VALUES ('gen', 18183);
INSERT INTO `pinyin_config` VALUES ('gei', 18184);
INSERT INTO `pinyin_config` VALUES ('ge', 18201);
INSERT INTO `pinyin_config` VALUES ('gao', 18211);
INSERT INTO `pinyin_config` VALUES ('gang', 18220);
INSERT INTO `pinyin_config` VALUES ('gan', 18231);
INSERT INTO `pinyin_config` VALUES ('gai', 18237);
INSERT INTO `pinyin_config` VALUES ('ga', 18239);
INSERT INTO `pinyin_config` VALUES ('fu', 18446);
INSERT INTO `pinyin_config` VALUES ('fou', 18447);
INSERT INTO `pinyin_config` VALUES ('fo', 18448);
INSERT INTO `pinyin_config` VALUES ('feng', 18463);
INSERT INTO `pinyin_config` VALUES ('fen', 18478);
INSERT INTO `pinyin_config` VALUES ('fei', 18490);
INSERT INTO `pinyin_config` VALUES ('fang', 18501);
INSERT INTO `pinyin_config` VALUES ('fan', 18518);
INSERT INTO `pinyin_config` VALUES ('fa', 18526);
INSERT INTO `pinyin_config` VALUES ('er', 18696);
INSERT INTO `pinyin_config` VALUES ('en', 18697);
INSERT INTO `pinyin_config` VALUES ('e', 18710);
INSERT INTO `pinyin_config` VALUES ('duo', 18722);
INSERT INTO `pinyin_config` VALUES ('dun', 18731);
INSERT INTO `pinyin_config` VALUES ('dui', 18735);
INSERT INTO `pinyin_config` VALUES ('duan', 18741);
INSERT INTO `pinyin_config` VALUES ('du', 18756);
INSERT INTO `pinyin_config` VALUES ('dou', 18763);
INSERT INTO `pinyin_config` VALUES ('dong', 18773);
INSERT INTO `pinyin_config` VALUES ('diu', 18774);
INSERT INTO `pinyin_config` VALUES ('ding', 18783);
INSERT INTO `pinyin_config` VALUES ('die', 18952);
INSERT INTO `pinyin_config` VALUES ('diao', 18961);
INSERT INTO `pinyin_config` VALUES ('dian', 18977);
INSERT INTO `pinyin_config` VALUES ('di', 18996);
INSERT INTO `pinyin_config` VALUES ('deng', 19003);
INSERT INTO `pinyin_config` VALUES ('de', 19006);
INSERT INTO `pinyin_config` VALUES ('dao', 19018);
INSERT INTO `pinyin_config` VALUES ('dang', 19023);
INSERT INTO `pinyin_config` VALUES ('dan', 19038);
INSERT INTO `pinyin_config` VALUES ('dai', 19212);
INSERT INTO `pinyin_config` VALUES ('da', 19218);
INSERT INTO `pinyin_config` VALUES ('cuo', 19224);
INSERT INTO `pinyin_config` VALUES ('cun', 19227);
INSERT INTO `pinyin_config` VALUES ('cui', 19235);
INSERT INTO `pinyin_config` VALUES ('cuan', 19238);
INSERT INTO `pinyin_config` VALUES ('cu', 19242);
INSERT INTO `pinyin_config` VALUES ('cou', 19243);
INSERT INTO `pinyin_config` VALUES ('cong', 19249);
INSERT INTO `pinyin_config` VALUES ('ci', 19261);
INSERT INTO `pinyin_config` VALUES ('chuo', 19263);
INSERT INTO `pinyin_config` VALUES ('chun', 19270);
INSERT INTO `pinyin_config` VALUES ('chui', 19275);
INSERT INTO `pinyin_config` VALUES ('chuang', 19281);
INSERT INTO `pinyin_config` VALUES ('chuan', 19288);
INSERT INTO `pinyin_config` VALUES ('chuai', 19289);
INSERT INTO `pinyin_config` VALUES ('chu', 19467);
INSERT INTO `pinyin_config` VALUES ('chou', 19479);
INSERT INTO `pinyin_config` VALUES ('chong', 19484);
INSERT INTO `pinyin_config` VALUES ('chi', 19500);
INSERT INTO `pinyin_config` VALUES ('cheng', 19515);
INSERT INTO `pinyin_config` VALUES ('chen', 19525);
INSERT INTO `pinyin_config` VALUES ('che', 19531);
INSERT INTO `pinyin_config` VALUES ('chao', 19540);
INSERT INTO `pinyin_config` VALUES ('chang', 19715);
INSERT INTO `pinyin_config` VALUES ('chan', 19725);
INSERT INTO `pinyin_config` VALUES ('chai', 19728);
INSERT INTO `pinyin_config` VALUES ('cha', 19739);
INSERT INTO `pinyin_config` VALUES ('ceng', 19741);
INSERT INTO `pinyin_config` VALUES ('ce', 19746);
INSERT INTO `pinyin_config` VALUES ('cao', 19751);
INSERT INTO `pinyin_config` VALUES ('cang', 19756);
INSERT INTO `pinyin_config` VALUES ('can', 19763);
INSERT INTO `pinyin_config` VALUES ('cai', 19774);
INSERT INTO `pinyin_config` VALUES ('ca', 19775);
INSERT INTO `pinyin_config` VALUES ('bu', 19784);
INSERT INTO `pinyin_config` VALUES ('bo', 19805);
INSERT INTO `pinyin_config` VALUES ('bing', 19976);
INSERT INTO `pinyin_config` VALUES ('bin', 19982);
INSERT INTO `pinyin_config` VALUES ('bie', 19986);
INSERT INTO `pinyin_config` VALUES ('biao', 19990);
INSERT INTO `pinyin_config` VALUES ('bian', 20002);
INSERT INTO `pinyin_config` VALUES ('bi', 20026);
INSERT INTO `pinyin_config` VALUES ('beng', 20032);
INSERT INTO `pinyin_config` VALUES ('ben', 20036);
INSERT INTO `pinyin_config` VALUES ('bei', 20051);
INSERT INTO `pinyin_config` VALUES ('bao', 20230);
INSERT INTO `pinyin_config` VALUES ('bang', 20242);
INSERT INTO `pinyin_config` VALUES ('ban', 20257);
INSERT INTO `pinyin_config` VALUES ('bai', 20265);
INSERT INTO `pinyin_config` VALUES ('ba', 20283);
INSERT INTO `pinyin_config` VALUES ('ao', 20292);
INSERT INTO `pinyin_config` VALUES ('ang', 20295);
INSERT INTO `pinyin_config` VALUES ('an', 20304);
INSERT INTO `pinyin_config` VALUES ('ai', 20317);
INSERT INTO `pinyin_config` VALUES ('a', 20319);

-- ----------------------------
-- Table structure for provider
-- ----------------------------
DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provider_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方服务商名',
  `type` int(11) NULL DEFAULT NULL COMMENT '第三方服务商类型：1-短信，2-邮件，3-App，4-微信',
  `script_context` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '脚本内容',
  `script_tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '脚本唯一标识',
  `config` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '配置信息json字符串',
  `description` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息  ',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for provider_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `provider_sms_template`;
CREATE TABLE `provider_sms_template`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `provider_id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `sign` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信签名',
  `content` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `is_enable` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态 0 禁用 1 启用',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 正常 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台短信模板 消息供应商模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名',
  `role_str` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限字符',
  `role_sort` int(11) NULL DEFAULT NULL COMMENT '角色排序',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '状态',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 'admin', 1, 0, NULL, '2021-01-25 17:03:02', '2021-01-25 17:03:02', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单权限id',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 285 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (9,'1',4,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (10,'1',5,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (11,'1',9,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (12,'1',22,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (13,'1',23,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (14,'1',24,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (15,'1',25,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (16,'1',34,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (17,'1',35,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (18,'1',36,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (19,'1',37,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (20,'1',38,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (21,'1',39,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (22,'1',40,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (23,'1',42,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (24,'1',43,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (25,'1',44,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (26,'1',41,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (27,'1',45,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (28,'1',46,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (29,'1',47,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (30,'1',48,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (31,'1',65,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (32,'1',66,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (33,'1',67,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (34,'1',68,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (35,'1',50,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (36,'1',51,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (37,'1',62,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (38,'1',63,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (39,'1',64,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (40,'1',69,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (41,'1',70,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (42,'1',52,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (43,'1',53,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (44,'1',54,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (45,'1',55,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (46,'1',56,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (47,'1',57,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (48,'1',58,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (49,'1',59,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (50,'1',60,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (51,'1',61,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (52,'1',6,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (53,'1',13,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (54,'1',18,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (55,'1',19,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (56,'1',20,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (57,'1',21,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (58,'1',14,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (59,'1',15,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (60,'1',16,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (61,'1',17,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (62,'1',7,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (63,'1',8,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (64,'1',10,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (65,'1',11,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (66,'1',26,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (67,'1',27,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (68,'1',28,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (69,'1',29,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (70,'1',30,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (71,'1',31,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (72,'1',32,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);
INSERT INTO `role_menu` VALUES (73,'1',33,'2021-01-26 07:40:50','2021-01-26 07:40:50','admin','admin',0);

-- ----------------------------
-- Table structure for send_mode
-- ----------------------------
DROP TABLE IF EXISTS `send_mode`;
CREATE TABLE `send_mode`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `mode_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限名称',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of send_mode
-- ----------------------------
INSERT INTO `send_mode` VALUES (1,'短信','2021-01-26 05:42:11','2021-01-26 05:42:11','admin','admin',0);
INSERT INTO `send_mode` VALUES (2,'邮件','2021-01-26 05:42:12','2021-01-26 05:42:12','admin','admin',0);
INSERT INTO `send_mode` VALUES (3,'移动端消息','2021-01-26 05:42:12','2021-01-26 05:42:12','admin','admin',0);
INSERT INTO `send_mode` VALUES (4,'公众号消息','2021-01-26 05:42:12','2021-01-26 05:42:12','admin','admin',0);

-- ----------------------------
-- Table structure for sms_info
-- ----------------------------
DROP TABLE IF EXISTS `sms_info`;
CREATE TABLE `sms_info`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `message_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息uuid',
  `app_id` bigint(20) NOT NULL COMMENT 'appid',
  `app_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'app名称',
  `phone_num` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收人号码',
  `transmit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发送时间 ',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信内容',
  `provider_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信平台',
  `template_id` bigint(20) NOT NULL COMMENT '模版id',
  `result` int(11) NOT NULL DEFAULT 0 COMMENT '发送结果',
  `fail_code` int(11) NULL DEFAULT NULL COMMENT '消息平台失败errorCode',
  `fail_reason` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败原因',
  `result_time` timestamp(0) NULL DEFAULT NULL COMMENT '接收到回调的时间',
  `delay` smallint(5) UNSIGNED NOT NULL COMMENT '延迟',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除：0-否，1-是',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sms_info_id_sequence`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板内容',
  `params` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数列表',
  `is_enable` tinyint(1) UNSIGNED NOT NULL COMMENT '状态: 0-禁用 1-启用',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标识 0 正常 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信模板 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_template_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_template_relation`;
CREATE TABLE `sms_template_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sms_template_id` bigint(20) NOT NULL COMMENT '短信模板编号',
  `provider_template_id` bigint(20) NOT NULL COMMENT '平台模板编号',
  `priority` int(11) NOT NULL COMMENT '优先级 值越大 优先级越高',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 正常 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信模板关系 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `user_real_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户真实姓名',
  `password` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码（加密）',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `mail` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '用户角色',
  `remark` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理平台用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 'xF0EJZJQ/2fzjYIlfwOG5w==', NULL, NULL, 0, 1, NULL, '2021-01-25 17:01:07', '2021-01-25 17:01:07', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for user_role TODO 用户多权限
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wechat_access_token
-- ----------------------------
DROP TABLE IF EXISTS `wechat_access_token`;
CREATE TABLE `wechat_access_token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信公众号id',
  `expire` bigint(20) NOT NULL COMMENT '过期时间',
  `access_token` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'token',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wechat_info
-- ----------------------------
DROP TABLE IF EXISTS `wechat_info`;
CREATE TABLE `wechat_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_id` bigint(20) NOT NULL COMMENT 'appid',
  `app_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app名称',
  `wechat_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `open_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `template_id` bigint(20) NOT NULL COMMENT '模版id',
  `template_data` varchar(3000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `skip_url` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `applet_data` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `transmit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发送时间 ',
  `result` int(11) NOT NULL DEFAULT 0 COMMENT '发送结果',
  `fail_code` int(11) NULL DEFAULT NULL COMMENT '结果时间',
  `fail_reason` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '失败原因',
  `delay` int(10) NULL DEFAULT NULL COMMENT '延迟',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wechat_template
-- ----------------------------
DROP TABLE IF EXISTS `wechat_template`;
CREATE TABLE `wechat_template`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `provider_id` bigint(20) NOT NULL COMMENT '消息平台id',
  `provider_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `wechat_template_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公众号模板ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `status` int(2) NOT NULL COMMENT '启用状态',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- -------------------------------  Functions  -------------------------------
-- ----------------------------
-- Function structure for fristPinyin
-- ----------------------------
DROP FUNCTION IF EXISTS `fristPinyin`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `fristPinyin`(P_NAME VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
BEGIN
    DECLARE V_RETURN VARCHAR(255);
    SET V_RETURN = ELT(INTERVAL(CONV(HEX(left(CONVERT(P_NAME USING gbk),1)),16,10),
        0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,
        0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,
        0xC8F6,0xCBFA,0xCDDA,0xCEF4,0xD1B9,0xD4D1),
    'a','b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s','t','w','x','y','z');
    IF (ISNULL(V_RETURN) or V_RETURN = '') THEN
        SET V_RETURN = P_NAME;
END IF;
RETURN V_RETURN;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for Num_char_extract
-- ----------------------------
DROP FUNCTION IF EXISTS `Num_char_extract`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `Num_char_extract`(Varstring VARCHAR(100)CHARSET utf8, is_deleted INT) RETURNS varchar(50) CHARSET utf8
BEGIN
	DECLARE len INT DEFAULT 0;
	DECLARE Tmp VARCHAR(100) DEFAULT '';
	SET len=CHAR_LENGTH(Varstring);
	IF is_deleted = 0
	THEN
		WHILE len > 0 DO
		IF MID(Varstring,len,1)REGEXP'[0-9]' THEN
		SET Tmp=CONCAT(Tmp,MID(Varstring,len,1));
END IF;
		SET len = len - 1;
END WHILE;
	ELSEIF is_deleted=1
	THEN
		WHILE len > 0 DO
		IF (MID(Varstring,len,1)REGEXP '[a-zA-Z]')
		THEN
		SET Tmp=CONCAT(Tmp,MID(Varstring,len,1));
END IF;
		SET len = len - 1;
END WHILE;
	ELSEIF is_deleted=2
	THEN
		WHILE len > 0 DO
		IF ( (MID(Varstring,len,1)REGEXP'[0-9]')
		OR (MID(Varstring,len,1)REGEXP '[a-zA-Z]') )
		THEN
		SET Tmp=CONCAT(Tmp,MID(Varstring,len,1));
END IF;
		SET len = len - 1;
END WHILE;
	ELSEIF is_deleted=3
	THEN
		WHILE len > 0 DO
		IF NOT (MID(Varstring,len,1)REGEXP '^[u0391-uFFE5]')
		THEN
		SET Tmp=CONCAT(Tmp,MID(Varstring,len,1));
END IF;
		SET len = len - 1;
END WHILE;
ELSE
		SET Tmp = 'Error: The second paramter should be in (0,1,2,3)';
RETURN Tmp;
END IF;
RETURN REVERSE(Tmp);
END
;;
delimiter ;

-- ----------------------------
-- Function structure for pinyin
-- ----------------------------
DROP FUNCTION IF EXISTS `pinyin`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `pinyin`(P_NAME VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
BEGIN
    DECLARE V_COMPARE VARCHAR(255);
    DECLARE V_RETURN VARCHAR(255);
    DECLARE I INT;
    SET I = 1;
    SET V_RETURN = '';
    while I <= char_length(P_NAME) do
        SET V_COMPARE = SUBSTR(P_NAME, I, 1);
        IF (V_COMPARE != '') THEN
            #SET V_RETURN = CONCAT(V_RETURN, ',', V_COMPARE);
            SET V_RETURN = CONCAT(V_RETURN, fristPinyin(V_COMPARE));
            #SET V_RETURN = fristPinyin(V_COMPARE);
END IF;
        SET I = I + 1;
end while;

    IF (ISNULL(V_RETURN) or V_RETURN = '') THEN
        SET V_RETURN = P_NAME;
END IF;

RETURN V_RETURN;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for to_pinyin
-- ----------------------------
DROP FUNCTION IF EXISTS `to_pinyin`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `to_pinyin`(NAME VARCHAR(255) CHARSET gbk) RETURNS varchar(255) CHARSET gbk
BEGIN
    DECLARE mycode INT;
    DECLARE tmp_lcode VARCHAR(2) CHARSET gbk;
    DECLARE lcode INT;

    DECLARE tmp_rcode VARCHAR(2) CHARSET gbk;
    DECLARE rcode INT;

    DECLARE mypy VARCHAR(255) CHARSET gbk DEFAULT '';
    DECLARE lp INT;

    SET mycode = 0;
    SET lp = 1;

    SET NAME = HEX(NAME);

    WHILE lp < LENGTH(NAME) DO

        SET tmp_lcode = SUBSTRING(NAME, lp, 2);
        SET lcode = CAST(ASCII(UNHEX(tmp_lcode)) AS UNSIGNED);
        SET tmp_rcode = SUBSTRING(NAME, lp + 2, 2);
        SET rcode = CAST(ASCII(UNHEX(tmp_rcode)) AS UNSIGNED);
        IF lcode > 128 THEN
            SET mycode =65536 - lcode * 256 - rcode ;
SELECT CONCAT(mypy,pin_yin_) INTO mypy FROM pinyin_config WHERE CODE_ >= ABS(mycode) ORDER BY CODE_ ASC LIMIT 1;
SET lp = lp + 4;
ELSE
            SET mypy = CONCAT(mypy,CHAR(CAST(ASCII(UNHEX(SUBSTRING(NAME, lp, 2))) AS UNSIGNED)));
            SET lp = lp + 2;
END IF;
END WHILE;
RETURN LOWER(mypy);
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
