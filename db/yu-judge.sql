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

 Date: 29/07/2020 19:50:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for judge_problem
-- ----------------------------
DROP TABLE IF EXISTS `judge_problem`;
CREATE TABLE `judge_problem`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问题名称',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问题内容',
  `time_limit` int(0) UNSIGNED NULL DEFAULT 3000 COMMENT '程序运行的时间限制，单位为毫秒',
  `memory_limit` int(0) UNSIGNED NULL DEFAULT 32768 COMMENT '程序运行的内存限制，单位为kb',
  `cpu_time_limit` int(0) UNSIGNED NULL DEFAULT 3000 COMMENT '程序运行的时间限制(cpu)，单位为毫秒',
  `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) NULL DEFAULT NULL,
  `update_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `character_tags` json NULL COMMENT '题目标签',
  `accept_amount` int(0) UNSIGNED NULL DEFAULT 0 COMMENT '通过数量',
  `total_submisstion_amount` int(0) UNSIGNED NULL DEFAULT 0 COMMENT '总提交数目',
  `allowed_language` json NULL COMMENT '支持的语言',
  `is_closed` tinyint(0) UNSIGNED NULL DEFAULT 0,
  `output_limit` int(0) NULL DEFAULT 50000,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3243388 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of judge_problem
-- ----------------------------
INSERT INTO `judge_problem` VALUES (3243290, '2342hhhh4324', '窗口前设一条黄线假设银行有*K*个窗口提供服务，窗口前设一条黄线，所有顾客按到达时间在黄线后排成一条长龙。当有窗口空闲时，下一位顾客即去该窗口处理事务。当有多个窗口可选择时，假设顾客总是选择编号最小的窗口。\r\n\r\n本题要求输出前来等待服务的*N*位顾客的平均等待时间、最长等待时间、最后完成时间，并且统计每个窗口服务了多少名顾客。\r\n\r\n### 输入格式:\r\n\r\n输入第1行给出正整数*N*（≤1000），为顾客总人数；随后*N*行，每行给出一位顾客的到达时间`T`和事务处理时间`P`，并且假设输入数据已经按到达时间先后排好了顺序；最后一行给出正整数*K*（≤10），为开设的营业窗口数。这里假设每位顾客事务被处理的最长时间为60分钟。\r\n\r\n### 输出格式:\r\n\r\n在第一行中输出平均等待时间（输出到小数点后1位）、最长等待时间、最后完成时间，之间用1个空格分隔，行末不能有多余空格。\r\n\r\n在第二行中按编号递增顺序输出每个窗口服务了多少名顾客，数字之间用1个空格分隔，行末不能有多余空格。\r\n\r\n### 输入样例：\r\n\r\n```in\r\n9\r\n0 20\r\n1 15\r\n1 61\r\n2 10\r\n10 5\r\n10 3\r\n30 18\r\n31 25\r\n31 2\r\n3\r\n```\r\n\r\n### 输出样例：\r\n\r\n```out\r\n6.2 17 61\r\n5 3 1\r\n```', 22222, 234324, 34234, '2020-07-19 00:14:50.416', NULL, '2020-07-29 14:50:07.151', '[\"2313\"]', 0, 0, '[\"C\", \"C_PLUS_PLUS\", \"JAVA\"]', 0, 3423);

-- ----------------------------
-- Table structure for judge_solution
-- ----------------------------
DROP TABLE IF EXISTS `judge_solution`;
CREATE TABLE `judge_solution`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `std_in` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `expected_std_out` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) NULL DEFAULT NULL,
  `update_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `pk_problem` int(0) NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of judge_solution
-- ----------------------------

-- ----------------------------
-- Table structure for problem_set
-- ----------------------------
DROP TABLE IF EXISTS `problem_set`;
CREATE TABLE `problem_set`  (
  `id` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `creator` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `delete_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_set
-- ----------------------------
INSERT INTO `problem_set` VALUES (1, '1212', '121', 212, '2020-07-27 10:23:38', '2020-07-27 10:23:44', NULL);

-- ----------------------------
-- Table structure for problem_set_problem
-- ----------------------------
DROP TABLE IF EXISTS `problem_set_problem`;
CREATE TABLE `problem_set_problem`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pk_problem_set` int(0) NULL DEFAULT NULL,
  `pk_problem` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_set_problem
-- ----------------------------
INSERT INTO `problem_set_problem` VALUES (1, 1, 3243290);

-- ----------------------------
-- Table structure for submission
-- ----------------------------
DROP TABLE IF EXISTS `submission`;
CREATE TABLE `submission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pk_problem` int(0) NULL DEFAULT NULL,
  `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3),
  `pk_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '提交语言',
  `judge_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '这个提交的状态',
  `time_cost` int(0) NULL DEFAULT NULL COMMENT '花费时间',
  `memory_cost` int(0) NULL DEFAULT NULL COMMENT '花费内存',
  `judge_result` json NULL,
  `code_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of submission
-- ----------------------------
INSERT INTO `submission` VALUES (121, 3243290, NULL, NULL, '2020-07-29 19:38:24.843', NULL, 'C', 'PENDING', NULL, NULL, NULL, '4324234');
INSERT INTO `submission` VALUES (122, 3243290, NULL, NULL, '2020-07-29 19:49:04.668', NULL, 'C', 'PENDING', NULL, NULL, NULL, '4324234');

SET FOREIGN_KEY_CHECKS = 1;
