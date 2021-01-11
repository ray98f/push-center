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

 Date: 29/12/2020 16:35:45
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
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `flag` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for app_role
-- ----------------------------
DROP TABLE IF EXISTS `app_role`;
CREATE TABLE `app_role` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `app_id` bigint NOT NULL COMMENT '服务ID',
    `role_id` bigint NOT NULL COMMENT '权限ID',
    `status` int NOT NULL DEFAULT '0',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `flag` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='服务对应的推送权限表';

-- ----------------------------
-- Table structure for provider
-- ----------------------------
DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `provider_name` varchar(20) DEFAULT NULL COMMENT '第三方服务商名',
  `description` varchar(120) DEFAULT NULL COMMENT '描述信息  ',
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `flag` int(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for provider_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `provider_sms_template`;
CREATE TABLE `provider_sms_template` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '服务商短信模版表',
  `provider_id` int(8) NOT NULL COMMENT '服务供应商表id',
  `sms_template_id` int(8) NOT NULL COMMENT 'sms_template表id',
  `example` text NOT NULL COMMENT '短信内容',
  `sms_config_id` int(8) NOT NULL COMMENT '模版对应的配置id',
  `s_template_id` varchar(30) NOT NULL COMMENT '服务商短信模版id',
  `sign` varchar(45) NOT NULL COMMENT '短信签名',
  `order` int(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `flag` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
-- ----------------------------
-- Table structure for dic
-- ----------------------------
DROP TABLE IF EXISTS `dic`;
CREATE TABLE `dic`  (
    `id` int(8) NOT NULL DEFAULT 0,
    `key` int(8) NOT NULL,
    `value` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `flag` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sendMode
-- ----------------------------
DROP TABLE IF EXISTS `sendMode`;
CREATE TABLE `sendMode`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `flag` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for script
-- ----------------------------
DROP TABLE IF EXISTS `script`;
CREATE TABLE `script` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(1) NOT NULL COMMENT '脚本类型',
  `related` int(1) NOT NULL DEFAULT '0' COMMENT '是否关联',
  `script_tag` varchar(255) DEFAULT NULL COMMENT '脚本唯一标识',
  `provider_id` int(8) DEFAULT NULL COMMENT 'config表主键',
  `description` varchar(120) DEFAULT NULL COMMENT '描述',
  `context` text NOT NULL COMMENT '脚本内容',
  `script_name` varchar(255) NOT NULL COMMENT '脚本名',
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `flag` int(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for secret_key
-- ----------------------------
DROP TABLE IF EXISTS `secret_key`;
CREATE TABLE `secret_key`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `app_id` bigint(20) NOT NULL,
    `app_key` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'Key',
    `app_secret` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密钥',
    `app_token` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'Key和密钥生成的Token（2小时过期）',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `flag` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'App的Key和Secret存储表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_config
-- ----------------------------
DROP TABLE IF EXISTS `sms_config`;
CREATE TABLE `sms_config` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `description` varchar(120) DEFAULT NULL,
  `provider_id` int(8) NOT NULL COMMENT 'config表id',
  `s_app_id` varchar(255) DEFAULT NULL COMMENT '第三方短信服务配置的appId',
  `secret_id` varchar(255) DEFAULT NULL COMMENT '第三方短信服务配置的secretId',
  `secret_key` varchar(255) DEFAULT NULL COMMENT '第三方短信服务配置的secretKey',
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `flag` int(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sms_info
-- ----------------------------
DROP TABLE IF EXISTS `sms_info`;
CREATE TABLE `sms_info`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `app_id` bigint(20) NOT NULL COMMENT 'appid',
    `app_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'app名称',
    `phone_num` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收人号码',
    `transmit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发送时间 ',
    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
    `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信内容',
    `provider` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信平台',
    `template_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模版id',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `flag` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `sms_info_id_sequence`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `type` int(4) DEFAULT '0',
  `name` varchar(45) DEFAULT '',
  `description` varchar(120) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `flag` int(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
-- ----------------------------
-- Table structure for table `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `user_name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名',
    `user_real_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户真实姓名',
    `password` text COLLATE utf8_bin NOT NULL COMMENT '密码（加密）',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `flag` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='管理平台用户';
SET FOREIGN_KEY_CHECKS = 1;



-- 创建汉字拼音对照临时表
CREATE TABLE IF NOT EXISTS `t_base_pinyin` (
    `pin_yin_` varchar(255) CHARACTER SET gbk NOT NULL,
    `code_` int(11) NOT NULL,
    PRIMARY KEY (`code_`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 汉字拼音表初始化
INSERT INTO t_base_pinyin (pin_yin_,code_)  VALUES ("a", 20319),("ai", 20317),("an", 20304),("ang", 20295),("ao", 20292),("ba", 20283),("bai", 20265),("ban", 20257),("bang", 20242),("bao", 20230),("bei", 20051),("ben", 20036),("beng", 20032),("bi", 20026),("bian", 20002),("biao", 19990),("bie", 19986),("bin", 19982),("bing", 19976),("bo", 19805),("bu", 19784),("ca", 19775),("cai", 19774),("can", 19763),("cang", 19756),("cao", 19751),("ce", 19746),("ceng", 19741),("cha", 19739),("chai", 19728),("chan", 19725),("chang", 19715),("chao", 19540),("che", 19531),("chen", 19525),("cheng", 19515),("chi", 19500),("chong", 19484),("chou", 19479),("chu", 19467),("chuai", 19289),("chuan", 19288),("chuang", 19281),("chui", 19275),("chun", 19270),("chuo", 19263),("ci", 19261),("cong", 19249),("cou", 19243),("cu", 19242),("cuan", 19238),("cui", 19235),("cun", 19227),("cuo", 19224),("da", 19218),("dai", 19212),("dan", 19038),("dang", 19023),("dao", 19018),("de", 19006),("deng", 19003),("di", 18996),("dian", 18977),("diao", 18961),("die", 18952),("ding", 18783),("diu", 18774),("dong", 18773),("dou", 18763),("du", 18756),("duan", 18741),("dui", 18735),("dun", 18731),("duo", 18722),("e", 18710),("en", 18697),("er", 18696),("fa", 18526),("fan", 18518),("fang", 18501),("fei", 18490),("fen", 18478),("feng", 18463),("fo", 18448),("fou", 18447),("fu", 18446),("ga", 18239),("gai", 18237),("gan", 18231),("gang", 18220),("gao", 18211),("ge", 18201),("gei", 18184),("gen", 18183),("geng", 18181),("gong", 18012),("gou", 17997),("gu", 17988),("gua", 17970),("guai", 17964),("guan", 17961),("guang", 17950),("gui", 17947),("gun", 17931),("guo", 17928),("ha", 17922),("hai", 17759),("han", 17752),("hang", 17733),("hao", 17730),("he", 17721),("hei", 17703),("hen", 17701),("heng", 17697),("hong", 17692),("hou", 17683),("hu", 17676),("hua", 17496),("huai", 17487),("huan", 17482),("huang", 17468),("hui", 17454),("hun", 17433),("huo", 17427),("ji", 17417),("jia", 17202),("jian", 17185),("jiang", 16983),("jiao", 16970),("jie", 16942),("jin", 16915),("jing", 16733),("jiong", 16708),("jiu", 16706),("ju", 16689),("juan", 16664),("jue", 16657),("jun", 16647),("ka", 16474),("kai", 16470),("kan", 16465),("kang", 16459),("kao", 16452),("ke", 16448),("ken", 16433),("keng", 16429),("kong", 16427),("kou", 16423),("ku", 16419),("kua", 16412),("kuai", 16407),("kuan", 16403),("kuang", 16401),("kui", 16393),("kun", 16220),("kuo", 16216),("la", 16212),("lai", 16205),("lan", 16202),("lang", 16187),("lao", 16180),("le", 16171),("lei", 16169),("leng", 16158),("li", 16155),("lia", 15959),("lian", 15958),("liang", 15944),("liao", 15933),("lie", 15920),("lin", 15915),("ling", 15903),("liu", 15889),("long", 15878),("lou", 15707),("lu", 15701),("lv", 15681),("luan", 15667),("lue", 15661),("lun", 15659),("luo", 15652),("ma", 15640),("mai", 15631),("man", 15625),("mang", 15454),("mao", 15448),("me", 15436),("mei", 15435),("men", 15419),("meng", 15416),("mi", 15408),("mian", 15394),("miao", 15385),("mie", 15377),("min", 15375),("ming", 15369),("miu", 15363),("mo", 15362),("mou", 15183),("mu", 15180),("na", 15165),("nai", 15158),("nan", 15153),("nang", 15150),("nao", 15149),("ne", 15144),("nei", 15143),("nen", 15141),("neng", 15140),("ni", 15139),("nian", 15128),("niang", 15121),("niao", 15119),("nie", 15117),("nin", 15110),("ning", 15109),("niu", 14941),("nong", 14937),("nu", 14933),("nv", 14930),("nuan", 14929),("nue", 14928),("nuo", 14926),("o", 14922),("ou", 14921),("pa", 14914),("pai", 14908),("pan", 14902),("pang", 14894),("pao", 14889),("pei", 14882),("pen", 14873),("peng", 14871),("pi", 14857),("pian", 14678),("piao", 14674),("pie", 14670),("pin", 14668),("ping", 14663),("po", 14654),("pu", 14645),("qi", 14630),("qia", 14594),("qian", 14429),("qiang", 14407),("qiao", 14399),("qie", 14384),("qin", 14379),("qing", 14368),("qiong", 14355),("qiu", 14353),("qu", 14345),("quan", 14170),("que", 14159),("qun", 14151),("ran", 14149),("rang", 14145),("rao", 14140),("re", 14137),("ren", 14135),("reng", 14125),("ri", 14123),("rong", 14122),("rou", 14112),("ru", 14109),("ruan", 14099),("rui", 14097),("run", 14094),("ruo", 14092),("sa", 14090),("sai", 14087),("san", 14083),("sang", 13917),("sao", 13914),("se", 13910),("sen", 13907),("seng", 13906),("sha", 13905),("shai", 13896),("shan", 13894),("shang", 13878),("shao", 13870),("she", 13859),("shen", 13847),("sheng", 13831),("shi", 13658),("shou", 13611),("shu", 13601),("shua", 13406),("shuai", 13404),("shuan", 13400),("shuang", 13398),("shui", 13395),("shun", 13391),("shuo", 13387),("si", 13383),("song", 13367),("sou", 13359),("su", 13356),("suan", 13343),("sui", 13340),("sun", 13329),("suo", 13326),("ta", 13318),("tai", 13147),("tan", 13138),("tang", 13120),("tao", 13107),("te", 13096),("teng", 13095),("ti", 13091),("tian", 13076),("tiao", 13068),("tie", 13063),("ting", 13060),("tong", 12888),("tou", 12875),("tu", 12871),("tuan", 12860) ,("tui", 12858),("tun", 12852),("tuo", 12849),("wa", 12838),("wai", 12831),("wan", 12829),("wang", 12812),("wei", 12802),("wen", 12607),("weng", 12597),("wo", 12594),("wu", 12585),("xi", 12556),("xia", 12359),("xian", 12346),("xiang", 12320),("xiao", 12300),("xie", 12120),("xin", 12099),("xing", 12089),("xiong", 12074),("xiu", 12067),("xu", 12058),("xuan", 12039),("xue", 11867),("xun", 11861),("ya", 11847),("yan", 11831),("yang", 11798),("yao", 11781),("ye", 11604),("yi", 11589),("yin", 11536),("ying", 11358),("yo", 11340),("yong", 11339),("you", 11324),("yu", 11303),("yuan", 11097),("yue", 11077),("yun", 11067),("za", 11055),("zai", 11052),("zan", 11045),("zang", 11041),("zao", 11038),("ze", 11024),("zei", 11020),("zen", 11019),("zeng", 11018),("zha", 11014),("zhai", 10838),("zhan", 10832),("zhang", 10815),("zhao", 10800),("zhe", 10790),("zhen", 10780),("zheng", 10764),("zhi", 10587),("zhong", 10544),("zhou", 10533),("zhu", 10519),("zhua", 10331),("zhuai", 10329),("zhuan", 10328),("zhuang", 10322),("zhui", 10315),("zhun", 10309),("zhuo", 10307),("zi", 10296),("zong", 10281),("zou", 10274),("zu", 10270),("zuan", 10262),("zui", 10260),("zun", 10256),("zuo", 10254);

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
SELECT CONCAT(mypy,pin_yin_) INTO mypy FROM t_base_pinyin WHERE CODE_ >= ABS(mycode) ORDER BY CODE_ ASC LIMIT 1;
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

-- 获取汉字首字母拼音函数
DROP FUNCTION IF EXISTS fristPinyin;
DELIMITER $
CREATE FUNCTION `fristPinyin`(P_NAME VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
BEGIN
    DECLARE V_RETURN VARCHAR(255);
    SET V_RETURN = ELT(INTERVAL(CONV(HEX(left(CONVERT(P_NAME USING gbk),1)),16,10),
        0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,
        0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,
        0xC8F6,0xCBFA,0xCDDA,0xCEF4,0xD1B9,0xD4D1),
    'a','b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s','t','w','x','y','z');
RETURN V_RETURN;
END;
$
DELIMITER ;

-- 获取所有汉字拼音首字母函数
DROP FUNCTION IF EXISTS pinyin;
DELIMITER $
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
END;
$
DELIMITER ;


-- 字符串提取字段函数(0数字、1字母、2数字+字母、3汉字)
DELIMITER $$
DROP FUNCTION IF EXISTS `Num_char_extract`$$

CREATE FUNCTION `Num_char_extract`(Varstring VARCHAR(100)CHARSET utf8, flag INT) RETURNS VARCHAR(50) CHARSET utf8
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