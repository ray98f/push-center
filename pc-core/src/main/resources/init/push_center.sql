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
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app`
(
    `id`         bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '服务ID',
    `app_name`   varchar(45) CHARACTER SET utf8 COLLATE utf8_bin        NOT NULL COMMENT '服务名',
    `app_key`    varchar(40) CHARACTER SET utf8 COLLATE utf8_bin        NOT NULL COMMENT '服务对应Key',
    `app_secret` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin       NOT NULL COMMENT '服务对应密码',
    `status`     int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    `created_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
    `updated_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '修改时间',
    `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '应用表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app`
VALUES (1, '告警中心', 'zte1611631143297BJjNA', 'f605974df0cc4635be07ddae886aa562O9WM8kyJUFzeItzM', 1,
        '2021-01-26 11:19:03', '2021-01-26 11:19:03', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for app_role
-- ----------------------------
DROP TABLE IF EXISTS `app_role`;
CREATE TABLE `app_role`
(
    `id`              bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '服务权限ID',
    `app_id`          bigint(20) UNSIGNED NOT NULL COMMENT '服务ID',
    `mode_id`         bigint(20) UNSIGNED NOT NULL COMMENT '权限ID',
    `sms_template_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '模板ID',
    `wechat_id`       bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '公众号id',
    `status`          int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态（0可用，1不可用）',
    `created_at`      timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
    `updated_at`      timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '修改时间',
    `created_by`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `updated_by`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
    `is_deleted`      tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '服务对应的推送权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for application_info
-- ----------------------------
DROP TABLE IF EXISTS `application_info`;
CREATE TABLE `application_info`
(
    `id`              bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `app_id`          bigint(20) UNSIGNED NOT NULL COMMENT 'appid',
    `message_id`      varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'message的uuid',
    `app_name`        varchar(45) COLLATE utf8_bin          DEFAULT NULL COMMENT 'app名称',
    `target_platform` tinyint(2) UNSIGNED DEFAULT NULL COMMENT '目标平台，1-android，2-ios，3-all',
    `audience`        varchar(45) COLLATE utf8_bin NOT NULL COMMENT '推送目标',
    `title`           varchar(45) COLLATE utf8_bin          DEFAULT NULL COMMENT '标题',
    `content`         varchar(500) COLLATE utf8_bin         DEFAULT NULL COMMENT '内容',
    `transmit_time`   timestamp                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间 ',
    `provider_name`   varchar(20) COLLATE utf8_bin NOT NULL COMMENT '推送平台',
    `delay`           int(10) unsigned NOT NULL COMMENT '延迟',
    `result`          int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '发送结果',
    `fail_code`       int(11) UNSIGNED DEFAULT NULL COMMENT '结果时间',
    `fail_reason`     varchar(255) COLLATE utf8_bin         DEFAULT NULL COMMENT '失败原因',
    `created_at`      timestamp                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`      timestamp                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `created_by`      varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人',
    `updated_by`      varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '更新人',
    `is_deleted`      tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY               `transmit_time_sequence` (`transmit_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13733 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for dic
-- ----------------------------
DROP TABLE IF EXISTS `dic`;
CREATE TABLE `dic`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `type`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
    `name`        varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
    `is_enable`   tinyint(1) UNSIGNED NOT NULL COMMENT '状态：0-停用，1-启用',
    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
    `created_at`  timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `created_by`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `updated_by`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `is_deleted`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dic
-- ----------------------------
INSERT INTO `dic`
VALUES (1, 'push_method', '推送方式', 1, '消息推送方式字典', '2021-01-26 11:20:31', '2021-01-26 11:20:31', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for dic_data
-- ----------------------------
DROP TABLE IF EXISTS `dic_data`;
CREATE TABLE `dic_data`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `key`         smallint(5) NOT NULL COMMENT '字典键值',
    `value`       varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典标签',
    `type`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
    `order`       tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '字典排序',
    `description` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
    `is_enable`   tinyint(1) UNSIGNED NOT NULL COMMENT '状态：0-停用，1-启用',
    `created_at`  timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `created_by`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `updated_by`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `is_deleted`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dic_data
-- ----------------------------
INSERT INTO `dic_data`
VALUES (1, 1, '短信', 'push_method', 1, '', 1, '2021-01-26 11:21:45', '2021-01-26 11:21:45', 'admin', 'admin', 0);
INSERT INTO `dic_data`
VALUES (2, 3, '邮件', 'push_method', 2, '', 1, '2021-01-26 11:21:57', '2021-01-26 11:21:57', 'admin', 'admin', 0);
INSERT INTO `dic_data`
VALUES (3, 3, 'App', 'push_method', 3, '', 1, '2021-01-26 11:22:25', '2021-01-26 11:22:25', 'admin', 'admin', 0);
INSERT INTO `dic_data`
VALUES (4, 4, '微信', 'push_method', 4, '', 1, '2021-01-26 11:22:40', '2021-01-26 11:22:40', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for early_warn_config
-- ----------------------------
DROP TABLE IF EXISTS `early_warn_config`;
CREATE TABLE `early_warn_config`
(
    `id`                 bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `alarm_cycle`        bigint(20) UNSIGNED NOT NULL COMMENT '周期',
    `threshold`          bigint(20) UNSIGNED NOT NULL COMMENT '阈值',
    `alarm_interval`     bigint(20) UNSIGNED NOT NULL COMMENT '报警间隔',
    `user_ids`           varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '处置人员，user表主键逗号拼接',
    `sms_template_id`    bigint(20) UNSIGNED NOT NULL COMMENT '短信模板id',
    `mail_title`         varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮件标题',
    `mail_body`          text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '邮件内容',
    `mail_provider_id`   bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '邮箱，消息平台id',
    `open_ids`           varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '目标微信id，逗号拼接',
    `wechat_provider_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '公众号，消息平台id',
    `wechat_template_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '公众号模板',
    `wechat_data`        varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公众号内容',
    `app_id`             bigint(20) UNSIGNED NOT NULL COMMENT 'appId',
    `created_at`         timestamp(0)                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
    `updated_at`         timestamp(0)                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '修改时间',
    `created_by`         varchar(32) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL COMMENT '创建人',
    `updated_by`         varchar(32) CHARACTER SET utf8 COLLATE utf8_bin  NOT NULL COMMENT '更新人',
    `is_deleted`         tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '预警配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for early_warn_info
-- ----------------------------
DROP TABLE IF EXISTS `early_warn_info`;
CREATE TABLE `early_warn_info`
(
    `id`         bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `time`       timestamp                    NOT NULL COMMENT '预警时间',
    `reason`     varchar(45) COLLATE utf8_bin          DEFAULT NULL COMMENT '预警原因',
    `content`    text COLLATE utf8_bin COMMENT '预警内容',
    `disposer`   varchar(45) COLLATE utf8_bin          DEFAULT NULL,
    `is_handle`  tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否处理，0-未处理，1-已处理',
    `created_at` timestamp                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
    `updated_at` timestamp                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `created_by` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '创建人',
    `updated_by` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '更新人',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标志',
    PRIMARY KEY (`id`),
    KEY          `time_sequence` (`time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='预警记录表';
-- ----------------------------
-- Table structure for mail_info
-- ----------------------------
DROP TABLE IF EXISTS `mail_info`;
CREATE TABLE `mail_info`
(
    `id`              bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `app_id`          bigint(20) UNSIGNED NOT NULL COMMENT '服务ID',
    `app_name`        varchar(45) CHARACTER SET utf8 COLLATE utf8_bin        NOT NULL COMMENT '服务名称',
    `message_id`      varchar(32) CHARACTER SET utf8 COLLATE utf8_bin        NOT NULL COMMENT 'messageId',
    `receive_address` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '收件地址',
    `cc_address`      text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '抄送地址',
    `mail_title`      varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮件标题',
    `mail_body`       text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '邮件正文',
    `provider_name`   varchar(20) CHARACTER SET utf8 COLLATE utf8_bin        NOT NULL COMMENT '短信平台',
    `transmit_time`   timestamp(0) NULL DEFAULT NULL COMMENT '发送时间',
    `result`          int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '发送结果（0成功，1失败）',
    `fail_code`       int(11) UNSIGNED NULL DEFAULT NULL COMMENT '失败code',
    `fail_reason`     varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '失败原因',
    `delay`           int(10) UNSIGNED NULL DEFAULT NULL COMMENT '延迟',
    `created_at`      timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
    `updated_at`      timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '修改时间',
    `created_by`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `updated_by`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `is_deleted`      tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`
(
    `id`             bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单管理id',
    `is_catalog`     tinyint(4) UNSIGNED NULL DEFAULT 1 COMMENT '是否为目录',
    `catalog_id`     bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '目录id',
    `is_menu`        tinyint(4) UNSIGNED NULL DEFAULT 1 COMMENT '是否为菜单',
    `menu_id`        bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '菜单id',
    `menu_name`      varchar(45) CHARACTER SET utf8 COLLATE utf8_bin        NOT NULL COMMENT '名称',
    `menu_type`      tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '类型',
    `menu_icon`      varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标',
    `menu_sort`      varchar(45) CHARACTER SET utf8 COLLATE utf8_bin        NOT NULL COMMENT '排序',
    `menu_url`       varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '路由地址',
    `role_identify`  varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限标识',
    `component_path` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组件路径',
    `is_use`         tinyint(4) UNSIGNED NULL DEFAULT 0 COMMENT '是否使用',
    `is_show`        tinyint(4) UNSIGNED NULL DEFAULT 0 COMMENT '是否显示',
    `created_at`     timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '新增时间',
    `updated_at`     timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '修改时间',
    `created_by`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `updated_by`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `is_deleted`     tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu`
VALUES (4, 0, NULL, 1, NULL, '首页', 0, 'dashboard', '1', '/', NULL, NULL, 0, 0, '2021-01-26 05:59:29',
        '2021-01-26 05:59:29', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (5, 1, 4, 0, NULL, '首页', 1, 'dashboard', '1', 'dashboard', 'list', 'dashboard/index', 0, 0,
        '2021-01-26 05:59:49', '2021-01-26 05:59:49', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (6, 0, NULL, 1, NULL, '系统管理', 0, 'el-icon-s-platform', '9', '/system', NULL, NULL, 0, 0, '2021-01-26 06:01:08',
        '2021-01-26 06:01:08', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (7, 1, 6, 0, NULL, '菜单管理', 1, '', '3', 'menu', 'system:tab:list', 'system/menu', 0, 0, '2021-01-26 06:21:09',
        '2021-01-26 06:21:09', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (8, 1, NULL, 1, 7, '菜单新增', 2, '', '1', '', 'system:tab:add', '', 0, 0, '2021-01-26 01:52:17',
        '2021-01-26 01:52:17', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (9, 0, NULL, 1, NULL, '消息推送', 0, 'guide', '2', '/sendMessage', NULL, NULL, 0, 0, '2021-01-26 06:00:05',
        '2021-01-26 06:00:05', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (10, 1, NULL, 1, 7, '菜单修改', 2, '', '2', NULL, 'system:tab:modify', NULL, 0, 0, '2021-01-26 01:58:47',
        '2021-01-26 01:58:47', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (11, 1, NULL, 1, 7, '菜单删除', 2, '', '3', NULL, 'system:tab:remove', NULL, 0, 0, '2021-01-26 01:59:03',
        '2021-01-26 01:59:03', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (13, 1, 6, 0, NULL, '用户管理', 1, '', '1', 'user', 'system:user:list', 'system/user', 0, 0, '2021-01-26 06:20:57',
        '2021-01-26 06:20:57', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (14, 1, 6, 0, NULL, '角色管理', 1, '', '2', 'role', 'system:role:list', 'system/role', 0, 0, '2021-01-26 06:21:05',
        '2021-01-26 06:21:05', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (15, 1, NULL, 1, 14, '角色新增', 2, '', '1', NULL, 'system:role:add', NULL, 0, 0, '2021-01-26 06:22:21',
        '2021-01-26 06:22:21', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (16, 1, NULL, 1, 14, '角色修改', 2, '', '2', NULL, 'system:role:modify', NULL, 0, 0, '2021-01-26 06:22:25',
        '2021-01-26 06:22:25', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (17, 1, NULL, 1, 14, '角色删除', 2, '', '3', NULL, 'system:role:remove', NULL, 0, 0, '2021-01-26 06:22:28',
        '2021-01-26 06:22:28', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (18, 1, NULL, 1, 13, '用户新增', 2, '', '1', NULL, 'system:user:add', NULL, 0, 0, '2021-01-26 06:22:01',
        '2021-01-26 06:22:01', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (19, 1, NULL, 1, 13, '修改用户', 2, '', '2', NULL, 'system:user:modify', NULL, 0, 0, '2021-01-26 06:22:05',
        '2021-01-26 06:22:05', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (20, 1, NULL, 1, 13, '删除用户', 2, '', '3', NULL, 'system:user:remove', NULL, 0, 0, '2021-01-26 06:22:08',
        '2021-01-26 06:22:08', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (21, 1, NULL, 1, 13, '用户重置密码', 2, '', '4', NULL, 'system:user:reset-psw', NULL, 0, 0, '2021-01-26 06:22:13',
        '2021-01-26 06:22:13', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (22, 1, 9, 0, NULL, '短信推送', 1, '', '1', 'sms', 'sms:send', 'sendMessage/sms', 0, 0, '2021-01-26 07:23:33',
        '2021-01-26 07:23:33', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (23, 1, 9, 0, NULL, '邮件推送', 1, '', '2', 'email', 'mail:send', 'sendMessage/email', 0, 0, '2021-01-26 07:23:33',
        '2021-01-26 07:23:33', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (24, 1, 9, 0, NULL, '公众号推送', 1, '', '3', 'weixin', 'wechat:send', 'sendMessage/weixin', 0, 0,
        '2021-01-26 07:23:33', '2021-01-26 07:23:33', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (25, 1, 9, 0, NULL, 'App推送', 1, '', '4', 'app', 'app:send', 'sendMessage/app', 0, 0, '2021-01-26 07:23:33',
        '2021-01-26 07:23:33', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (26, 1, 6, 0, NULL, '字典管理', 1, '', '4', 'dictionary', 'system:dic-type:list', 'system/dictionary', 0, 0,
        '2021-01-26 06:21:28', '2021-01-26 06:21:28', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (27, 1, NULL, 1, 26, '新增字典类型', 2, '', '1', NULL, 'system:dic-type:add', NULL, 0, 0, '2021-01-26 06:22:48',
        '2021-01-26 06:22:48', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (28, 1, NULL, 1, 26, '修改字典', 2, '', '2', NULL, 'system:dic-type:modify', NULL, 0, 0, '2021-01-26 06:22:52',
        '2021-01-26 06:22:52', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (29, 1, NULL, 1, 26, '删除字典', 2, '', '3', NULL, 'system:dic-type:remove', NULL, 0, 0, '2021-01-26 06:22:56',
        '2021-01-26 06:22:56', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (30, 1, 6, 0, NULL, '字典数据', 1, '', '5', 'dictionaryData', 'system:dic-data:list', 'system/dictionaryData', 0, 1,
        '2021-01-26 06:21:32', '2021-01-26 06:21:32', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (31, 1, NULL, 1, 30, '新增字典数据', 2, '', '1', NULL, 'system:dic-data:add', NULL, 0, 0, '2021-01-26 06:23:03',
        '2021-01-26 06:23:03', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (32, 1, NULL, 1, 30, '修改字典数据', 2, '', '2', NULL, 'system:dic-data:modify', NULL, 0, 0, '2021-01-26 06:23:06',
        '2021-01-26 06:23:06', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (33, 1, NULL, 1, 30, '删除字典数据', 2, '', '3', NULL, 'system:dic-data:remove', NULL, 0, 0, '2021-01-26 06:23:09',
        '2021-01-26 06:23:09', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (34, 0, NULL, 1, NULL, '推送记录', 0, 'email', '3', '/sendRecord', NULL, NULL, 0, 0, '2021-01-26 06:00:18',
        '2021-01-26 06:00:18', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (35, 1, 34, 0, NULL, '短信记录', 1, '', '1', 'smsRecord', 'sms:info:list', 'sendRecord/sms', 0, 0,
        '2021-01-26 06:08:40', '2021-01-26 06:08:40', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (36, 1, 34, 0, NULL, '邮件记录', 1, '', '2', 'emailRecord', 'mail:info:list', 'sendRecord/email', 0, 0,
        '2021-01-26 06:08:45', '2021-01-26 06:08:45', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (37, 1, 34, 0, NULL, '公众号记录', 1, '', '3', 'weixinRecord', 'wechat:info:list', 'sendRecord/weixin', 0, 0,
        '2021-01-26 06:08:49', '2021-01-26 06:08:49', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (38, 1, 34, 0, NULL, 'App记录', 1, '', '4', 'appRecord', 'app:info:list', 'sendRecord/app', 0, 0,
        '2021-01-26 06:08:52', '2021-01-26 06:08:52', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (39, 0, NULL, 1, NULL, '模板管理', 0, 'documentation', '4', '/tplManager', NULL, NULL, 0, 0, '2021-01-26 06:00:24',
        '2021-01-26 06:00:24', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (40, 1, 39, 0, NULL, '短信模板配置', 1, '', '1', 'smsTpl', 'template:sms:list', 'tplManager/smsTpl', 0, 0,
        '2021-01-26 06:09:04', '2021-01-26 06:09:04', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (41, 1, 39, 0, NULL, '公众号模板配置', 1, '', '2', 'weixinTpl', 'template:wechat:list', 'tplManager/weixinTpl', 0, 0,
        '2021-01-26 06:09:10', '2021-01-26 06:09:10', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (42, 1, NULL, 1, 40, '新增短信模板', 2, '', '1', NULL, 'template:sms:add', NULL, 0, 0, '2021-01-26 06:18:19',
        '2021-01-26 06:18:19', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (43, 1, NULL, 1, 40, '修改短信模板', 2, '', '2', NULL, 'template:sms:modify', NULL, 0, 0, '2021-01-26 06:18:26',
        '2021-01-26 06:18:26', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (44, 1, NULL, 1, 40, '删除短信模板', 2, '', '3', NULL, 'template:sms:remove', NULL, 0, 0, '2021-01-26 06:18:30',
        '2021-01-26 06:18:30', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (45, 1, NULL, 1, 41, '新增微信模板', 2, '', '1', NULL, 'template:wechat:add', NULL, 0, 0, '2021-01-26 06:18:40',
        '2021-01-26 06:18:40', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (46, 1, NULL, 1, 41, '修改微信模板', 2, '', '2', NULL, 'template:wechat:modify', NULL, 0, 0, '2021-01-26 06:18:44',
        '2021-01-26 06:18:44', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (47, 1, NULL, 1, 41, '删除微信模板', 2, '', '3', NULL, 'template:wechat:remove', NULL, 0, 0, '2021-01-26 06:18:48',
        '2021-01-26 06:18:48', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (48, 0, NULL, 1, NULL, '消息平台管理', 0, 'example', '5', '/msgPlatform', NULL, NULL, 0, 0, '2021-01-26 06:00:32',
        '2021-01-26 06:00:32', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (49, 1, 48, 1, 48, '消息平台管理', 2, 'example', '0', 'index', 'provider:config:add', 'msgPlatform/index', 0, 0,
        '2021-01-26 05:38:15', '2021-01-26 05:38:15', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (50, 0, NULL, 1, NULL, '应用配置', 0, 'set', '6', '/appSet', NULL, NULL, 0, 0, '2021-01-26 06:00:40',
        '2021-01-26 06:00:40', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (51, 1, 50, 0, NULL, '应用配置', 1, 'set', '1', 'appSet', 'application:config:list', 'appSet/index', 0, 0,
        '2021-01-26 06:19:17', '2021-01-26 06:19:17', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (52, 0, NULL, 1, NULL, '数据统计', 0, 'dashboard', '7', '/dataStatistics', NULL, NULL, 0, 0, '2021-01-26 06:00:45',
        '2021-01-26 06:00:45', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (53, 1, 52, 0, NULL, '分类统计', 1, '', '1', 'sortData', 'statistic:type:list', 'dataStatistics/sortData', 0, 0,
        '2021-01-26 06:19:28', '2021-01-26 06:19:28', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (54, 1, 52, 0, NULL, '应用统计', 1, '', '2', 'appData', 'statistic:application:list', 'dataStatistics/appData', 0, 0,
        '2021-01-26 06:19:34', '2021-01-26 06:19:34', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (55, 1, 52, 0, NULL, '条件统计', 1, '', '3', 'conditionData', 'statistic:condition:list',
        'dataStatistics/conditionData', 0, 0, '2021-01-26 06:19:37', '2021-01-26 06:19:37', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (56, 1, 52, 0, NULL, '消息平台统计', 1, '', '4', 'platData', 'statistic:provider:list', 'dataStatistics/platData', 0,
        0, '2021-01-26 06:19:42', '2021-01-26 06:19:42', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (57, 0, NULL, 1, NULL, '预警管理', 0, 'warning', '8', '/warning', NULL, NULL, 0, 0, '2021-01-26 06:00:50',
        '2021-01-26 06:00:50', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (58, 1, 57, 0, NULL, '预警配置', 1, '', '1', 'warningSet', 'early-warn', 'warning/warningSet', 0, 0,
        '2021-01-26 06:19:54', '2021-01-26 06:19:54', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (59, 1, NULL, 1, 58, '查看预警', 2, '', '1', NULL, 'early-warn:config:query', NULL, 0, 0, '2021-01-26 06:20:15',
        '2021-01-26 06:20:15', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (60, 1, NULL, 1, 58, '添加\\修改预警', 2, '', '2', NULL, 'early-warn:config:modify', NULL, 0, 0, '2021-01-26 06:20:18',
        '2021-01-26 06:20:18', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (61, 1, 57, 0, NULL, '预警记录', 1, '', '2', 'warningData', 'early-warn:info:list', 'warning/warningData', 0, 0,
        '2021-01-26 06:19:57', '2021-01-26 06:19:57', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (62, 1, NULL, 1, 51, '新增应用', 2, '', '1', NULL, 'application:config:add', NULL, 0, 0, '2021-01-26 06:16:38',
        '2021-01-26 06:16:38', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (63, 1, NULL, 1, 51, '修改应用', 2, '', '2', NULL, 'application:config:modify', NULL, 0, 0, '2021-01-26 06:16:43',
        '2021-01-26 06:16:43', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (64, 1, NULL, 1, 51, '删除应用', 2, '', '3', NULL, 'application:config:remove', NULL, 0, 0, '2021-01-26 06:16:47',
        '2021-01-26 06:16:47', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (65, 1, 48, 0, NULL, '消息平台管理', 1, 'example', '1', 'msgPlatform', 'provider:config:list', 'msgPlatform/index', 0,
        0, '2021-01-26 06:09:38', '2021-01-26 06:09:38', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (66, 1, NULL, 1, 65, '新增消息平台', 2, '', '1', NULL, 'provider:config:add', NULL, 0, 0, '2021-01-26 06:18:57',
        '2021-01-26 06:18:57', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (67, 1, NULL, 1, 65, '修改消息平台', 2, '', '2', NULL, 'provider:config:modify', NULL, 0, 0, '2021-01-26 06:19:02',
        '2021-01-26 06:19:02', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (68, 1, NULL, 1, 65, '删除消息平台', 2, '', '3', NULL, 'provider:config:remove', NULL, 0, 0, '2021-01-26 06:19:06',
        '2021-01-26 06:19:06', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (69, 1, NULL, 1, 51, '应用权限配置', 2, '', '4', NULL, 'application:config:role', NULL, 0, 0, '2021-01-26 06:16:52',
        '2021-01-26 06:16:52', 'admin', 'admin', 0);
INSERT INTO `menu`
VALUES (70, 1, NULL, 1, 51, '重置密钥', 2, '', '5', NULL, 'application:config:reset-key', NULL, 0, 0, '2021-01-26 06:17:42',
        '2021-01-26 06:17:42', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for pinyin_config
-- ----------------------------
DROP TABLE IF EXISTS `pinyin_config`;
CREATE TABLE `pinyin_config`
(
    `pin_yin_` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
    `code_`    int(11) NOT NULL,
    PRIMARY KEY (`code_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pinyin_config
-- ----------------------------
INSERT INTO pinyin_config (pin_yin_,code_)  VALUES ("a", 20319),("ai", 20317),("an", 20304),("ang", 20295),("ao", 20292),("ba", 20283),("bai", 20265),("ban", 20257),("bang", 20242),("bao", 20230),("bei", 20051),("ben", 20036),("beng", 20032),("bi", 20026),("bian", 20002),("biao", 19990),("bie", 19986),("bin", 19982),("bing", 19976),("bo", 19805),("bu", 19784),("ca", 19775),("cai", 19774),("can", 19763),("cang", 19756),("cao", 19751),("ce", 19746),("ceng", 19741),("cha", 19739),("chai", 19728),("chan", 19725),("chang", 19715),("chao", 19540),("che", 19531),("chen", 19525),("cheng", 19515),("chi", 19500),("chong", 19484),("chou", 19479),("chu", 19467),("chuai", 19289),("chuan", 19288),("chuang", 19281),("chui", 19275),("chun", 19270),("chuo", 19263),("ci", 19261),("cong", 19249),("cou", 19243),("cu", 19242),("cuan", 19238),("cui", 19235),("cun", 19227),("cuo", 19224),("da", 19218),("dai", 19212),("dan", 19038),("dang", 19023),("dao", 19018),("de", 19006),("deng", 19003),("di", 18996),("dian", 18977),("diao", 18961),("die", 18952),("ding", 18783),("diu", 18774),("dong", 18773),("dou", 18763),("du", 18756),("duan", 18741),("dui", 18735),("dun", 18731),("duo", 18722),("e", 18710),("en", 18697),("er", 18696),("fa", 18526),("fan", 18518),("fang", 18501),("fei", 18490),("fen", 18478),("feng", 18463),("fo", 18448),("fou", 18447),("fu", 18446),("ga", 18239),("gai", 18237),("gan", 18231),("gang", 18220),("gao", 18211),("ge", 18201),("gei", 18184),("gen", 18183),("geng", 18181),("gong", 18012),("gou", 17997),("gu", 17988),("gua", 17970),("guai", 17964),("guan", 17961),("guang", 17950),("gui", 17947),("gun", 17931),("guo", 17928),("ha", 17922),("hai", 17759),("han", 17752),("hang", 17733),("hao", 17730),("he", 17721),("hei", 17703),("hen", 17701),("heng", 17697),("hong", 17692),("hou", 17683),("hu", 17676),("hua", 17496),("huai", 17487),("huan", 17482),("huang", 17468),("hui", 17454),("hun", 17433),("huo", 17427),("ji", 17417),("jia", 17202),("jian", 17185),("jiang", 16983),("jiao", 16970),("jie", 16942),("jin", 16915),("jing", 16733),("jiong", 16708),("jiu", 16706),("ju", 16689),("juan", 16664),("jue", 16657),("jun", 16647),("ka", 16474),("kai", 16470),("kan", 16465),("kang", 16459),("kao", 16452),("ke", 16448),("ken", 16433),("keng", 16429),("kong", 16427),("kou", 16423),("ku", 16419),("kua", 16412),("kuai", 16407),("kuan", 16403),("kuang", 16401),("kui", 16393),("kun", 16220),("kuo", 16216),("la", 16212),("lai", 16205),("lan", 16202),("lang", 16187),("lao", 16180),("le", 16171),("lei", 16169),("leng", 16158),("li", 16155),("lia", 15959),("lian", 15958),("liang", 15944),("liao", 15933),("lie", 15920),("lin", 15915),("ling", 15903),("liu", 15889),("long", 15878),("lou", 15707),("lu", 15701),("lv", 15681),("luan", 15667),("lue", 15661),("lun", 15659),("luo", 15652),("ma", 15640),("mai", 15631),("man", 15625),("mang", 15454),("mao", 15448),("me", 15436),("mei", 15435),("men", 15419),("meng", 15416),("mi", 15408),("mian", 15394),("miao", 15385),("mie", 15377),("min", 15375),("ming", 15369),("miu", 15363),("mo", 15362),("mou", 15183),("mu", 15180),("na", 15165),("nai", 15158),("nan", 15153),("nang", 15150),("nao", 15149),("ne", 15144),("nei", 15143),("nen", 15141),("neng", 15140),("ni", 15139),("nian", 15128),("niang", 15121),("niao", 15119),("nie", 15117),("nin", 15110),("ning", 15109),("niu", 14941),("nong", 14937),("nu", 14933),("nv", 14930),("nuan", 14929),("nue", 14928),("nuo", 14926),("o", 14922),("ou", 14921),("pa", 14914),("pai", 14908),("pan", 14902),("pang", 14894),("pao", 14889),("pei", 14882),("pen", 14873),("peng", 14871),("pi", 14857),("pian", 14678),("piao", 14674),("pie", 14670),("pin", 14668),("ping", 14663),("po", 14654),("pu", 14645),("qi", 14630),("qia", 14594),("qian", 14429),("qiang", 14407),("qiao", 14399),("qie", 14384),("qin", 14379),("qing", 14368),("qiong", 14355),("qiu", 14353),("qu", 14345),("quan", 14170),("que", 14159),("qun", 14151),("ran", 14149),("rang", 14145),("rao", 14140),("re", 14137),("ren", 14135),("reng", 14125),("ri", 14123),("rong", 14122),("rou", 14112),("ru", 14109),("ruan", 14099),("rui", 14097),("run", 14094),("ruo", 14092),("sa", 14090),("sai", 14087),("san", 14083),("sang", 13917),("sao", 13914),("se", 13910),("sen", 13907),("seng", 13906),("sha", 13905),("shai", 13896),("shan", 13894),("shang", 13878),("shao", 13870),("she", 13859),("shen", 13847),("sheng", 13831),("shi", 13658),("shou", 13611),("shu", 13601),("shua", 13406),("shuai", 13404),("shuan", 13400),("shuang", 13398),("shui", 13395),("shun", 13391),("shuo", 13387),("si", 13383),("song", 13367),("sou", 13359),("su", 13356),("suan", 13343),("sui", 13340),("sun", 13329),("suo", 13326),("ta", 13318),("tai", 13147),("tan", 13138),("tang", 13120),("tao", 13107),("te", 13096),("teng", 13095),("ti", 13091),("tian", 13076),("tiao", 13068),("tie", 13063),("ting", 13060),("tong", 12888),("tou", 12875),("tu", 12871),("tuan", 12860) ,("tui", 12858),("tun", 12852),("tuo", 12849),("wa", 12838),("wai", 12831),("wan", 12829),("wang", 12812),("wei", 12802),("wen", 12607),("weng", 12597),("wo", 12594),("wu", 12585),("xi", 12556),("xia", 12359),("xian", 12346),("xiang", 12320),("xiao", 12300),("xie", 12120),("xin", 12099),("xing", 12089),("xiong", 12074),("xiu", 12067),("xu", 12058),("xuan", 12039),("xue", 11867),("xun", 11861),("ya", 11847),("yan", 11831),("yang", 11798),("yao", 11781),("ye", 11604),("yi", 11589),("yin", 11536),("ying", 11358),("yo", 11340),("yong", 11339),("you", 11324),("yu", 11303),("yuan", 11097),("yue", 11077),("yun", 11067),("za", 11055),("zai", 11052),("zan", 11045),("zang", 11041),("zao", 11038),("ze", 11024),("zei", 11020),("zen", 11019),("zeng", 11018),("zha", 11014),("zhai", 10838),("zhan", 10832),("zhang", 10815),("zhao", 10800),("zhe", 10790),("zhen", 10780),("zheng", 10764),("zhi", 10587),("zhong", 10544),("zhou", 10533),("zhu", 10519),("zhua", 10331),("zhuai", 10329),("zhuan", 10328),("zhuang", 10322),("zhui", 10315),("zhun", 10309),("zhuo", 10307),("zi", 10296),("zong", 10281),("zou", 10274),("zu", 10270),("zuan", 10262),("zui", 10260),("zun", 10256),("zuo", 10254);

-- ----------------------------
-- Table structure for provider
-- ----------------------------
DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider`
(
    `id`             bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `provider_name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方服务商名',
    `type`           int(11) UNSIGNED NULL DEFAULT NULL COMMENT '第三方服务商类型：1-短信，2-邮件，3-App，4-微信',
    `script_context` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '脚本内容',
    `script_tag`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '脚本唯一标识',
    `config`         text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '配置信息json字符串',
    `description`    varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息  ',
    `created_at`     timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`     timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0),
    `created_by`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `updated_by`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `is_deleted`     tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for provider_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `provider_sms_template`;
CREATE TABLE `provider_sms_template`
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `provider_id` bigint(20) UNSIGNED NOT NULL,
    `code`        varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
    `sign`        varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '短信签名',
    `content`     varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
    `is_enable`   tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态 0 禁用 1 启用',
    `created_by`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '创建人',
    `created_at`  timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '更新人',
    `updated_at`  timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `is_deleted`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 正常 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台短信模板 消息供应商模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`         bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `role_name`  varchar(45) CHARACTER SET utf8 COLLATE utf8_bin        NOT NULL COMMENT '角色名',
    `role_str`   varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限字符',
    `role_sort`  int(11) UNSIGNED NULL DEFAULT NULL COMMENT '角色排序',
    `status`     tinyint(4) UNSIGNED NULL DEFAULT 0 COMMENT '状态',
    `remark`     varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
    `created_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '新增时间',
    `updated_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '修改时间',
    `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES (1, '超级管理员', 'admin', 1, 0, NULL, '2021-01-25 17:03:02', '2021-01-25 17:03:02', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`
(
    `id`         bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `role_id`    bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
    `menu_id`    bigint(20) UNSIGNED NOT NULL COMMENT '菜单权限id',
    `created_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '新增时间',
    `updated_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '修改时间',
    `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 285 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu`
VALUES (9, '1', 4, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (10, '1', 5, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (11, '1', 9, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (12, '1', 22, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (13, '1', 23, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (14, '1', 24, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (15, '1', 25, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (16, '1', 34, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (17, '1', 35, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (18, '1', 36, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (19, '1', 37, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (20, '1', 38, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (21, '1', 39, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (22, '1', 40, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (23, '1', 42, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (24, '1', 43, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (25, '1', 44, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (26, '1', 41, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (27, '1', 45, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (28, '1', 46, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (29, '1', 47, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (30, '1', 48, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (31, '1', 65, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (32, '1', 66, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (33, '1', 67, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (34, '1', 68, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (35, '1', 50, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (36, '1', 51, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (37, '1', 62, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (38, '1', 63, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (39, '1', 64, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (40, '1', 69, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (41, '1', 70, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (42, '1', 52, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (43, '1', 53, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (44, '1', 54, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (45, '1', 55, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (46, '1', 56, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (47, '1', 57, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (48, '1', 58, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (49, '1', 59, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (50, '1', 60, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (51, '1', 61, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (52, '1', 6, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (53, '1', 13, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (54, '1', 18, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (55, '1', 19, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (56, '1', 20, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (57, '1', 21, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (58, '1', 14, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (59, '1', 15, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (60, '1', 16, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (61, '1', 17, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (62, '1', 7, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (63, '1', 8, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (64, '1', 10, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (65, '1', 11, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (66, '1', 26, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (67, '1', 27, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (68, '1', 28, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (69, '1', 29, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (70, '1', 30, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (71, '1', 31, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (72, '1', 32, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);
INSERT INTO `role_menu`
VALUES (73, '1', 33, '2021-01-26 07:40:50', '2021-01-26 07:40:50', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for send_mode
-- ----------------------------
DROP TABLE IF EXISTS `send_mode`;
CREATE TABLE `send_mode`
(
    `id`         bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限ID',
    `mode_name`  varchar(45) CHARACTER SET utf8 COLLATE utf8_bin        NOT NULL COMMENT '权限名称',
    `created_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0),
    `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of send_mode
-- ----------------------------
INSERT INTO `send_mode`
VALUES (1, '短信', '2021-01-26 05:42:11', '2021-01-26 05:42:11', 'admin', 'admin', 0);
INSERT INTO `send_mode`
VALUES (2, '邮件', '2021-01-26 05:42:12', '2021-01-26 05:42:12', 'admin', 'admin', 0);
INSERT INTO `send_mode`
VALUES (3, '移动端消息', '2021-01-26 05:42:12', '2021-01-26 05:42:12', 'admin', 'admin', 0);
INSERT INTO `send_mode`
VALUES (4, '公众号消息', '2021-01-26 05:42:12', '2021-01-26 05:42:12', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for sms_info
-- ----------------------------
DROP TABLE IF EXISTS `sms_info`;
CREATE TABLE `sms_info`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `message_id`    varchar(32)  NOT NULL COMMENT '消息uuid',
    `app_id`        bigint(20) UNSIGNED NOT NULL COMMENT 'appid',
    `app_name`      varchar(45)           DEFAULT NULL COMMENT 'app名称',
    `phone_num`     char(11)     NOT NULL COMMENT '接收人号码',
    `transmit_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间 ',
    `content`       varchar(500) NOT NULL COMMENT '短信内容',
    `provider_name` varchar(20)  NOT NULL COMMENT '短信平台',
    `template_id`   bigint(20) UNSIGNED NOT NULL COMMENT '模版id',
    `result`        int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '发送结果',
    `fail_code`     int(11) UNSIGNED DEFAULT NULL COMMENT '消息平台失败errorCode',
    `fail_reason`   varchar(255)          DEFAULT NULL COMMENT '失败原因',
    `result_time`   timestamp NULL DEFAULT NULL COMMENT '接收到回调的时间',
    `delay`         int(10) unsigned NOT NULL COMMENT '延迟',
    `created_at`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created_by`    varchar(32)  NOT NULL COMMENT '创建人',
    `updated_by`    varchar(32)  NOT NULL COMMENT '更新人',
    `is_deleted`    tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0-否，1-是',
    PRIMARY KEY (`id`) USING BTREE,
    KEY             `transmit_time_sequence` (`transmit_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=259330 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template`
(
    `id`         bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `content`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板内容',
    `params`     varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数列表',
    `is_enable`  tinyint(1) UNSIGNED NOT NULL COMMENT '状态: 0-禁用 1-启用',
    `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '创建人',
    `created_at` timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '更新人',
    `updated_at` timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标识 0 正常 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信模板 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_template_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_template_relation`;
CREATE TABLE `sms_template_relation`
(
    `id`                   bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sms_template_id`      bigint(20) UNSIGNED NOT NULL COMMENT '短信模板编号',
    `provider_template_id` bigint(20) UNSIGNED NOT NULL COMMENT '平台模板编号',
    `priority`             int(11) UNSIGNED NOT NULL COMMENT '优先级 值越大 优先级越高',
    `created_by`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `created_at`           timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `updated_at`           timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `is_deleted`           tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0 正常 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信模板关系 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`             bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `user_name`      varchar(45) CHARACTER SET utf8 COLLATE utf8_bin        NOT NULL COMMENT '用户名',
    `user_real_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户真实姓名',
    `password`       text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码（加密）',
    `phone`          char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
    `mail`           varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
    `status`         int(11) UNSIGNED NULL DEFAULT 0 COMMENT '状态',
    `role_id`        bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '用户角色',
    `remark`         varchar(80) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
    `created_at`     timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '新增时间',
    `updated_at`     timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '修改时间',
    `created_by`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `updated_by`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `is_deleted`     tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理平台用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, 'admin', 'admin', 'xF0EJZJQ/2fzjYIlfwOG5w==', NULL, NULL, 0, 1, NULL, '2021-01-25 17:01:07',
        '2021-01-25 17:01:07', 'admin', 'admin', 0);

-- ----------------------------
-- Table structure for user_role TODO 用户多权限
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`         bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`    bigint(20) UNSIGNED NOT NULL,
    `role_id`    bigint(20) UNSIGNED NOT NULL,
    `created_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '新增时间',
    `updated_at` timestamp(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '修改时间',
    `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
    `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wechat_access_token
-- ----------------------------
DROP TABLE IF EXISTS `wechat_access_token`;
CREATE TABLE `wechat_access_token`
(
    `id`           bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `app_id`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '微信公众号id',
    `expire`       bigint(20) UNSIGNED NOT NULL COMMENT '过期时间',
    `access_token` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'token',
    `created_at`   timestamp(0)                                             NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`   timestamp(0)                                             NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0),
    `created_by`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '创建人',
    `updated_by`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '更新人',
    `is_deleted`   tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wechat_info
-- ----------------------------
DROP TABLE IF EXISTS `wechat_info`;
CREATE TABLE `wechat_info`
(
    `id`            bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `message_id`    varchar(32) COLLATE utf8_bin NOT NULL,
    `app_id`        bigint(20) UNSIGNED NOT NULL COMMENT 'appid',
    `app_name`      varchar(45) COLLATE utf8_bin          DEFAULT NULL COMMENT 'app名称',
    `wechat_name`   varchar(45) COLLATE utf8_bin NOT NULL,
    `open_id`       varchar(45) COLLATE utf8_bin          DEFAULT NULL,
    `template_id`   bigint(20) UNSIGNED NOT NULL COMMENT '模版id',
    `template_data` varchar(3000) COLLATE utf8_bin        DEFAULT NULL,
    `skip_url`      varchar(45) COLLATE utf8_bin          DEFAULT NULL,
    `applet_data`   varchar(45) COLLATE utf8_bin          DEFAULT NULL,
    `transmit_time` timestamp                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间 ',
    `result`        int(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '发送结果',
    `fail_code`     int(11) UNSIGNED DEFAULT NULL COMMENT '结果时间',
    `fail_reason`   varchar(255) COLLATE utf8_bin         DEFAULT NULL COMMENT '失败原因',
    `delay`         int(10) UNSIGNED DEFAULT NULL COMMENT '延迟',
    `created_at`    timestamp                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created_by`    varchar(32) COLLATE utf8_bin NOT NULL COMMENT '创建人',
    `updated_by`    varchar(32) COLLATE utf8_bin NOT NULL COMMENT '更新人',
    `is_deleted`    tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY             `transmit_time_sequence` (`transmit_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for wechat_template
-- ----------------------------
DROP TABLE IF EXISTS `wechat_template`;
CREATE TABLE `wechat_template`
(
    `id`                 bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `provider_id`        bigint(20) UNSIGNED NOT NULL COMMENT '消息平台id',
    `provider_name`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `wechat_template_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公众号模板ID',
    `title`              varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
    `content`            text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
    `status`             int(2) UNSIGNED NOT NULL COMMENT '启用状态',
    `created_at`         timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`         timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0),
    `created_by`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '创建人',
    `updated_by`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '更新人',
    `is_deleted`         tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- -------------------------------  Functions  -------------------------------
-- ----------------------------
-- Function structure for fristPinyin
-- ----------------------------
DROP FUNCTION IF EXISTS `fristPinyin`;
delimiter $
CREATE
FUNCTION `fristPinyin`(P_NAME VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
BEGIN
    DECLARE
V_RETURN VARCHAR(255);
    SET
V_RETURN = ELT(INTERVAL(CONV(HEX(left(CONVERT(P_NAME USING gbk),1)),16,10),
        0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,
        0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,
        0xC8F6,0xCBFA,0xCDDA,0xCEF4,0xD1B9,0xD4D1),
    'a','b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s','t','w','x','y','z');
    IF
(ISNULL(V_RETURN) or V_RETURN = '') THEN
        SET V_RETURN = P_NAME;
END IF;
RETURN V_RETURN;
END
$
delimiter ;

-- ----------------------------
-- Function structure for Num_char_extract
-- ----------------------------
DELIMITER $$

DROP FUNCTION IF EXISTS `num_char_extract`$$

CREATE FUNCTION `num_char_extract`(Varstring VARCHAR(100)CHARSET utf8, flag INT) RETURNS VARCHAR(50) CHARSET utf8
BEGIN
    DECLARE len INT DEFAULT 0;
    DECLARE Tmp VARCHAR(100) DEFAULT '';
    SET len=CHAR_LENGTH(Varstring);
    IF flag = 0
    THEN
        WHILE len > 0 DO
        IF MID(Varstring,len,1)REGEXP'[0-9]' THEN
        SET Tmp=CONCAT(Tmp,MID(Varstring,len,1));
        END IF;
        SET len = len - 1;
        END WHILE;
    ELSEIF flag=1
    THEN
        WHILE len > 0 DO
        IF (MID(Varstring,len,1)REGEXP '[a-zA-Z]')
        THEN
        SET Tmp=CONCAT(Tmp,MID(Varstring,len,1));
        END IF;
        SET len = len - 1;
        END WHILE;
    ELSEIF flag=2
    THEN
        WHILE len > 0 DO
        IF ( (MID(Varstring,len,1)REGEXP'[0-9]')
        OR (MID(Varstring,len,1)REGEXP '[a-zA-Z]') )
        THEN
        SET Tmp=CONCAT(Tmp,MID(Varstring,len,1));
        END IF;
        SET len = len - 1;
        END WHILE;
    ELSEIF flag=3
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
    END$$

DELIMITER ;

-- ----------------------------
-- Function structure for pinyin
-- ----------------------------
DROP FUNCTION IF EXISTS `pinyin`;
delimiter $
CREATE FUNCTION `pinyin`(P_NAME VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
BEGIN
    DECLARE V_COMPARE VARCHAR(255);
    DECLARE V_RETURN VARCHAR(255);
    DECLARE I INT;
    SET I = 1;
    SET V_RETURN = '';
    while I < LENGTH(P_NAME) do
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
$
delimiter ;

-- ----------------------------
-- Function structure for to_pinyin
-- ----------------------------
-- 建立汉字转换拼音函数
DROP FUNCTION IF EXISTS to_pinyin;
DELIMITER $
CREATE FUNCTION to_pinyin(NAME VARCHAR(255) CHARSET gbk)
RETURNS VARCHAR(255) CHARSET gbk
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
END;
$
DELIMITER ;

SET
FOREIGN_KEY_CHECKS = 1;
