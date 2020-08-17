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

 Date: 17/08/2020 16:46:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for judge_host
-- ----------------------------
DROP TABLE IF EXISTS `judge_host`;
CREATE TABLE `judge_host` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime DEFAULT NULL,
  `is_active` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of judge_host
-- ----------------------------
BEGIN;
INSERT INTO `judge_host` VALUES (1, 'JudgeHost1', 'http://47.106.202.255:8080', '2020-08-16 21:04:13', '2020-08-17 14:45:41', NULL, 0);
INSERT INTO `judge_host` VALUES (2, 'JudgeHost2', 'http://182.254.197.28:8080', '2020-08-16 21:04:14', '2020-08-17 14:45:42', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for judge_problem
-- ----------------------------
DROP TABLE IF EXISTS `judge_problem`;
CREATE TABLE `judge_problem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问题名称',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '问题内容',
  `time_limit` int unsigned DEFAULT '5' COMMENT '程序运行的时间限制，单位为毫秒',
  `memory_limit` int unsigned DEFAULT '32768' COMMENT '程序运行的内存限制，单位为kb',
  `cpu_time_limit` int unsigned DEFAULT '5' COMMENT '程序运行的时间限制(cpu)，单位为毫秒',
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `character_tags` json DEFAULT NULL COMMENT '题目标签',
  `accept_amount` int unsigned DEFAULT '0' COMMENT '通过数量',
  `total_submisstion_amount` int unsigned DEFAULT '0' COMMENT '总提交数目',
  `is_closed` tinyint unsigned DEFAULT '0',
  `output_limit` int DEFAULT '50000',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3243509 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of judge_problem
-- ----------------------------
BEGIN;
INSERT INTO `judge_problem` VALUES (3243388, '字符串匹配 ', '## 字符串匹配 \n\n给出一个最大长度为10^6的母串t，请你在t里面找到长度为len的出现次数最多的子串，如果找到多个出现次数一样的子串，请你输出字典序最小的。\n\n### 输入格式:\n\n在第一行输入一个正整数Len（Len<=10^6），第二行输入一个母串t，t的长度小于等于10^6。\n\n### 输出格式:\n\n输出答案子串和它在t中的出现次数，用一个空格分隔，行末尾没有多余空格！\n\n### 输入样例:\n\n在这里给出一组输入。例如：\n\n```in\n3\naba ababababababaaababababa\n```\n\n### 输出样例:\n\n在这里给出相应的输出。例如：\n\n```out\naba 11\n```', 5, 32768, 5, '2020-07-30 18:13:22.325', NULL, '2020-08-12 01:10:41.082', '[\"测试\"]', 0, 0, 0, 50000);
INSERT INTO `judge_problem` VALUES (3243409, 'A+B Problem', '## 两数之和\n\n输入两个整数 a,b，输出它们的和（|a|,|b|\\leq 10^9∣*a*∣,∣*b*∣≤109）。\n\n## 输入格式\n\n两个整数以空格分开。\n\n## 输出格式\n\n一个整数。\n\n## 输入输出样例\n\n**输入 #1**\n\n```\n20 30\n```\n\n**输出 #1**\n\n```\n50\n```\n\n## 说明/提示\n\n本题各种语言的程序范例：\n\nC\n\n```c\n#include <stdio.h>\n\nint main() {\n    int a,b;\n    scanf(\"%d%d\",&a,&b);\n    printf(\"%d\", a+b);\n    return 0;\n}\n```\n\n\n\nC++\n\n```cpp\n#include <iostream>\n#include <cstdio>\n\nusing namespace std;\n\nint main() {\n    int a,b;\n    cin >> a >> b;\n    cout << a+b;\n    return 0;\n}\n```\n\n\n\nPython3\n\n```cpp\ns = input().split()\nprint(int(s[0]) + int(s[1]))\n```\n\n\n\n', 5, 32768, 5, '2020-08-06 20:41:18.026', NULL, '2020-08-11 10:37:05.141', '[\"EASY\"]', 0, 0, 0, 50000);
INSERT INTO `judge_problem` VALUES (3243505, '两数之和', '## 两数之和\n\n输入两个整数 a,b，输出它们的和（|a|,|b|\\leq 10^9∣*a*∣,∣*b*∣≤109）。\n\n## 输入格式\n\n两个整数以空格分开。\n\n## 输出格式\n\n一个整数。\n\n## 输入输出样例\n\n**输入 #1**\n\n```\n20 30\n```\n\n**输出 #1**\n\n```\n50\n```\n\n## 说明/提示\n\n本题各种语言的程序范例：\n\nC\n\n```c\n#include <stdio.h>\n\nint main() {\n    int a,b;\n    scanf(\"%d%d\",&a,&b);\n    printf(\"%d\", a+b);\n    return 0;\n}\n```\n\n\n\nC++\n\n```cpp\n#include <iostream>\n#include <cstdio>\n\nusing namespace std;\n\nint main() {\n    int a,b;\n    cin >> a >> b;\n    cout << a+b;\n    return 0;\n}\n```\n\n\n\nPython3\n\n```cpp\ns = input().split()\nprint(int(s[0]) + int(s[1]))\n```\n\n\n\n', 6, 30000, NULL, '2020-08-13 10:50:40.015', NULL, '2020-08-15 18:51:43.517', '[\"入门\", \"测试\", \"demo\"]', 0, 0, 0, 50000);
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
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of judge_solution
-- ----------------------------
BEGIN;
INSERT INTO `judge_solution` VALUES (91, 'http://cdn.yuzzl.top/1596680709217.in', 'http://cdn.yuzzl.top/1596680709217.out', '2020-08-06 10:25:33.722', NULL, '2020-08-06 22:40:36.477', 3243409, '小数字');
INSERT INTO `judge_solution` VALUES (92, 'http://cdn.yuzzl.top/1596680895742.in', 'http://cdn.yuzzl.top/1596680895742.out', '2020-08-06 10:28:28.435', NULL, '2020-08-06 22:40:35.219', 3243409, '大数字');
INSERT INTO `judge_solution` VALUES (94, 'http://cdn.yuzzl.top/1596680709217.in', 'http://cdn.yuzzl.top/1596680709217.out', '2020-08-13 21:42:11.984', NULL, '2020-08-13 21:42:12.006', 3243505, '1');
INSERT INTO `judge_solution` VALUES (95, 'http://cdn.yuzzl.top/1596680709217.in', 'http://cdn.yuzzl.top/1596680709217.out', '2020-08-13 21:42:13.291', NULL, '2020-08-13 21:42:13.333', 3243505, '1');
COMMIT;

-- ----------------------------
-- Table structure for problem_set
-- ----------------------------
DROP TABLE IF EXISTS `problem_set`;
CREATE TABLE `problem_set` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pk_user` int DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `allowed_language` json DEFAULT NULL,
  `judge_preference` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'ACM',
  `time_penalty` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of problem_set
-- ----------------------------
BEGIN;
INSERT INTO `problem_set` VALUES (100, '测试题目集', 'test!aaa', 10007, '2020-07-27 10:23:38', '2020-08-15 23:14:09', NULL, '2020-08-15 11:35:55', '2020-08-13 18:00:00', '[\"C\", \"C_PLUS_PLUS\", \"PYTHON\", \"JAVA\"]', 'ACM', 20);
COMMIT;

-- ----------------------------
-- Table structure for problem_set_problem
-- ----------------------------
DROP TABLE IF EXISTS `problem_set_problem`;
CREATE TABLE `problem_set_problem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pk_problem_set` int DEFAULT NULL,
  `pk_problem` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=560 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of problem_set_problem
-- ----------------------------
BEGIN;
INSERT INTO `problem_set_problem` VALUES (557, 100, 3243388);
INSERT INTO `problem_set_problem` VALUES (558, 100, 3243409);
INSERT INTO `problem_set_problem` VALUES (559, 100, 3243505);
COMMIT;

-- ----------------------------
-- Table structure for problem_set_user
-- ----------------------------
DROP TABLE IF EXISTS `problem_set_user`;
CREATE TABLE `problem_set_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pk_user` int DEFAULT NULL COMMENT '排名信息对应的用户ID',
  `pk_problem_set` int DEFAULT NULL COMMENT '排名信息对应的题目集ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of problem_set_user
-- ----------------------------
BEGIN;
INSERT INTO `problem_set_user` VALUES (2, 10007, 100);
INSERT INTO `problem_set_user` VALUES (3, 10011, 100);
COMMIT;

-- ----------------------------
-- Table structure for submission
-- ----------------------------
DROP TABLE IF EXISTS `submission`;
CREATE TABLE `submission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `pk_problem` int DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `pk_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '提交语言',
  `judge_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '这个提交的状态',
  `time_cost` int unsigned DEFAULT NULL COMMENT '花费时间',
  `memory_cost` int unsigned DEFAULT NULL COMMENT '花费内存',
  `judge_result` json DEFAULT NULL,
  `code_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `judge_preference` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'ACM',
  `pk_problem_set` int DEFAULT NULL COMMENT '对应的题目集',
  `is_ac_before` tinyint DEFAULT '0' COMMENT '本次提交之前是否已经AC了',
  `pk_judge_host` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=379 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of submission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '加密后的用户密码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户头像地址',
  `ac_amount` int DEFAULT '0',
  `submission_amount` int DEFAULT '0',
  `scope` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'COMMON',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10012 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (10007, 'admin', '4fa8d6515821cdbb82631985611f437a', '2020-08-03 18:46:00', '2020-08-16 15:35:13', NULL, 'yuzl1123@163.com', NULL, 100, 400, 'ADMIN');
INSERT INTO `user` VALUES (10011, 'yzl', '4fa8d6515821cdbb82631985611f437a', '2020-08-13 09:47:23', '2020-08-16 15:35:17', NULL, 'yuzl1123@163.com', NULL, 0, 0, 'MANAGER');
COMMIT;

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_user_group`;
CREATE TABLE `user_user_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pk_user` int DEFAULT NULL,
  `pk_user_group` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_user_group
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
