/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : yu-judge

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 26/07/2020 15:30:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for judge_problem
-- ----------------------------
DROP TABLE IF EXISTS `judge_problem`;
CREATE TABLE `judge_problem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问题名称',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '问题内容',
  `time_limit` int unsigned DEFAULT '3000' COMMENT '程序运行的时间限制，单位为毫秒',
  `memory_limit` int unsigned DEFAULT '32768' COMMENT '程序运行的内存限制，单位为kb',
  `cpu_time_limit` int unsigned DEFAULT '3000' COMMENT '程序运行的时间限制(cpu)，单位为毫秒',
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `character_tags` json DEFAULT NULL COMMENT '题目标签',
  `accept_amount` int unsigned DEFAULT '0' COMMENT '通过数量',
  `total_submisstion_amount` int unsigned DEFAULT '0' COMMENT '总提交数目',
  `allowed_language` json DEFAULT NULL COMMENT '支持的语言',
  `is_closed` tinyint unsigned DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3243388 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of judge_problem
-- ----------------------------
BEGIN;
INSERT INTO `judge_problem` VALUES (3243290, 'Test Problem', '给定一个华氏温度*F*，本题要求编写程序，计算对应的摄氏温度*C*。计算公式：*C*=5×(*F*−32)/9。题目保证输入与输出均在整型范围内。\n\n### 输入格式:\n\n输入在一行中给出一个华氏温度。\n\n### 输出格式:\n\n在一行中按照格式“Celsius = C”输出对应的摄氏温度C的整数值。\n\n### 输入样例:\n\n```js\n150\n```\n\n### 输出样例:\n\n```out\nCelsius = 65\n```', 3000, 32768, 3000, '2020-07-19 00:14:50.416', NULL, '2020-07-26 10:37:11.500', '[\"入门\", \"项目测试\"]', 0, 0, '[\"C\", \"C++\", \"PYTHON\", \"JAVA\"]', 0);
INSERT INTO `judge_problem` VALUES (3243365, 'a + b problem', 'a + b problem', 3000, 32768, 3000, '2020-07-22 22:17:22.190', NULL, '2020-07-25 21:56:26.778', NULL, 0, 0, '[\"C\", \"C++\", \"PYTHON\", \"JAVA\"]', 0);
INSERT INTO `judge_problem` VALUES (3243366, 'a + b problem', 'a + b problem', 3000, 32768, 3000, '2020-07-22 22:18:26.604', NULL, '2020-07-25 21:56:27.465', NULL, 0, 0, '[\"C\", \"C++\", \"PYTHON\", \"JAVA\"]', 0);
INSERT INTO `judge_problem` VALUES (3243367, 'a + b problem', 'a + b problem', 3000, 32768, 3000, '2020-07-22 22:19:19.861', NULL, '2020-07-25 21:56:28.096', NULL, 0, 0, '[\"C\", \"C++\", \"PYTHON\", \"JAVA\"]', 0);
INSERT INTO `judge_problem` VALUES (3243368, 'a + b problem', 'a + b problem', 3000, 32768, 3000, '2020-07-22 23:05:03.527', NULL, '2020-07-25 21:56:28.667', NULL, 0, 0, '[\"C\", \"C++\", \"PYTHON\", \"JAVA\"]', 0);
INSERT INTO `judge_problem` VALUES (3243369, 'a + b problem', 'a + b problem', 3000, 32768, 3000, '2020-07-22 23:05:04.239', NULL, '2020-07-25 21:56:29.446', NULL, 0, 0, '[\"C\", \"C++\", \"PYTHON\", \"JAVA\"]', 0);
INSERT INTO `judge_problem` VALUES (3243370, 'a + b problem', 'a + b problem', 3000, 32768, 3000, '2020-07-22 23:05:04.809', NULL, '2020-07-25 21:56:30.127', NULL, 0, 0, '[\"C\", \"C++\", \"PYTHON\", \"JAVA\"]', 0);
INSERT INTO `judge_problem` VALUES (3243371, 'a + b problem', 'a + b problem', 3000, 32768, 3000, '2020-07-22 23:05:05.171', NULL, '2020-07-25 21:56:30.723', NULL, 0, 0, '[\"C\", \"C++\", \"PYTHON\", \"JAVA\"]', 0);
INSERT INTO `judge_problem` VALUES (3243372, 'a + b problem', 'a + b problem', 3000, 32768, 3000, '2020-07-22 23:05:05.444', NULL, '2020-07-25 21:56:32.011', NULL, 0, 0, '[\"C\", \"C++\", \"PYTHON\", \"JAVA\"]', 0);
INSERT INTO `judge_problem` VALUES (3243373, 'a + b problem', 'a + b problem', 3000, 32768, 3000, '2020-07-22 23:05:07.626', NULL, '2020-07-25 21:56:33.659', NULL, 0, 0, '[\"C\", \"C++\", \"PYTHON\", \"JAVA\"]', 0);
COMMIT;

-- ----------------------------
-- Table structure for judge_solution
-- ----------------------------
DROP TABLE IF EXISTS `judge_solution`;
CREATE TABLE `judge_solution` (
  `id` int NOT NULL AUTO_INCREMENT,
  `std_in` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `expected_std_out` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `pk_problem` int DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of judge_solution
-- ----------------------------
BEGIN;
INSERT INTO `judge_solution` VALUES (1, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.out', '2020-07-19 00:15:05.554', NULL, '2020-07-24 20:11:10.155', 3243290, '一般数据');
INSERT INTO `judge_solution` VALUES (2, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.out', '2020-07-19 00:16:08.239', NULL, '2020-07-24 20:11:18.505', 3243290, '一般数据');
INSERT INTO `judge_solution` VALUES (46, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.out', '2020-07-22 09:45:09.562', NULL, '2020-07-24 20:11:19.372', 3243357, '一般数据');
INSERT INTO `judge_solution` VALUES (47, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.out', '2020-07-22 09:45:09.607', NULL, '2020-07-24 20:11:20.128', 3243357, '一般数据');
INSERT INTO `judge_solution` VALUES (48, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.out', '2020-07-22 09:45:10.215', NULL, '2020-07-24 20:11:21.020', 3243358, '一般数据');
INSERT INTO `judge_solution` VALUES (49, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.out', '2020-07-22 09:45:10.217', NULL, '2020-07-24 20:11:21.705', 3243358, '一般数据');
INSERT INTO `judge_solution` VALUES (50, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.out', '2020-07-22 09:45:10.798', NULL, '2020-07-24 20:11:22.346', 3243359, '一般数据');
INSERT INTO `judge_solution` VALUES (51, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.out', '2020-07-22 09:45:10.800', NULL, '2020-07-24 20:11:23.216', 3243359, '一般数据');
INSERT INTO `judge_solution` VALUES (52, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.out', '2020-07-22 09:45:11.300', NULL, '2020-07-24 20:11:23.803', 3243360, '一般数据');
INSERT INTO `judge_solution` VALUES (53, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.out', '2020-07-22 09:45:11.301', NULL, '2020-07-24 20:11:24.854', 3243360, '一般数据');
INSERT INTO `judge_solution` VALUES (54, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.out', '2020-07-22 09:45:11.784', NULL, '2020-07-22 09:45:11.832', 3243361, NULL);
INSERT INTO `judge_solution` VALUES (55, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.out', '2020-07-22 09:45:11.785', NULL, '2020-07-22 09:45:11.833', 3243361, NULL);
INSERT INTO `judge_solution` VALUES (56, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_1.out', '2020-07-22 09:45:12.348', NULL, '2020-07-22 09:45:12.411', 3243362, NULL);
INSERT INTO `judge_solution` VALUES (57, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.out', '2020-07-22 09:45:12.391', NULL, '2020-07-22 09:45:12.413', 3243362, NULL);
INSERT INTO `judge_solution` VALUES (60, 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.in', 'http://qc8mhtkrp.bkt.clouddn.com/a_add_b_2.out', '2020-07-22 14:32:44.384', NULL, '2020-07-22 14:32:44.421', 3243362, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
