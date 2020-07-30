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

 Date: 30/07/2020 23:16:24
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
  `is_active` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of judge_host
-- ----------------------------
BEGIN;
INSERT INTO `judge_host` VALUES (1, 'test1', 'http://47.106.202.255:8080', 0);
INSERT INTO `judge_host` VALUES (2, 'test2', 'http://182.254.197.28:8080', 0);
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
  `allowed_language` json DEFAULT NULL COMMENT '支持的语言',
  `is_closed` tinyint unsigned DEFAULT '0',
  `output_limit` int DEFAULT '50000',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3243389 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of judge_problem
-- ----------------------------
BEGIN;
INSERT INTO `judge_problem` VALUES (3243290, 'A+B Problem', '窗口前设一条黄线假设银行有*K*个窗口提供服务，窗口前设一条黄线，所有顾客按到达时间在黄线后排成一条长龙。当有窗口空闲时，下一位顾客即去该窗口处理事务。当有多个窗口可选择时，假设顾客总是选择编号最小的窗口。\r\n\r\n本题要求输出前来等待服务的*N*位顾客的平均等待时间、最长等待时间、最后完成时间，并且统计每个窗口服务了多少名顾客。\r\n\r\n### 输入格式:\r\n\r\n输入第1行给出正整数*N*（≤1000），为顾客总人数；随后*N*行，每行给出一位顾客的到达时间`T`和事务处理时间`P`，并且假设输入数据已经按到达时间先后排好了顺序；最后一行给出正整数*K*（≤10），为开设的营业窗口数。这里假设每位顾客事务被处理的最长时间为60分钟。\r\n\r\n### 输出格式:\r\n\r\n在第一行中输出平均等待时间（输出到小数点后1位）、最长等待时间、最后完成时间，之间用1个空格分隔，行末不能有多余空格。\r\n\r\n在第二行中按编号递增顺序输出每个窗口服务了多少名顾客，数字之间用1个空格分隔，行末不能有多余空格。\r\n\r\n### 输入样例：\r\n\r\n```in\r\n9\r\n0 20\r\n1 15\r\n1 61\r\n2 10\r\n10 5\r\n10 3\r\n30 18\r\n31 25\r\n31 2\r\n3\r\n```\r\n\r\n### 输出样例：\r\n\r\n```out\r\n6.2 17 61\r\n5 3 1\r\n```', 5, 32768, 5, '2020-07-30 10:27:21.439', NULL, '2020-07-30 14:32:11.027', NULL, 0, 0, '[\"C\", \"JAVA\", \"PYTHON\", \"C_PLUS_PLUS\"]', 0, 50000);
INSERT INTO `judge_problem` VALUES (3243388, 'get the longest string', '测试', 5, 32768, 5, '2020-07-30 18:13:22.325', NULL, '2020-07-30 18:33:40.294', NULL, 0, 0, '[\"C_PLUS_PLUS\"]', 0, 50000);
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
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of judge_solution
-- ----------------------------
BEGIN;
INSERT INTO `judge_solution` VALUES (75, 'http://cdn.yuzzl.top/a_add_b_1.in', 'http://cdn.yuzzl.top/a_add_b_1.out', '2020-07-29 23:32:32.002', NULL, '2020-07-29 23:32:32.043', 3243290, NULL);
INSERT INTO `judge_solution` VALUES (76, 'http://cdn.yuzzl.top/a_add_b_2.in', 'http://cdn.yuzzl.top/a_add_b_2.out', '2020-07-29 23:32:43.076', NULL, '2020-07-29 23:32:43.120', 3243290, NULL);
INSERT INTO `judge_solution` VALUES (78, 'http://cdn.yuzzl.top/map_test_0.in', 'http://cdn.yuzzl.top/map_test_0.out', '2020-07-30 18:15:25.918', NULL, '2020-07-30 18:15:25.937', 3243388, NULL);
INSERT INTO `judge_solution` VALUES (79, 'http://cdn.yuzzl.top/map_test_1.in', 'http://cdn.yuzzl.top/map_test_1.out', '2020-07-30 18:15:32.928', NULL, '2020-07-30 18:15:32.931', 3243388, NULL);
INSERT INTO `judge_solution` VALUES (80, 'http://cdn.yuzzl.top/map_test_2.in', 'http://cdn.yuzzl.top/map_test_2.out', '2020-07-30 18:15:36.301', NULL, '2020-07-30 18:15:36.303', 3243388, NULL);
INSERT INTO `judge_solution` VALUES (81, 'http://cdn.yuzzl.top/map_test_3.in', 'http://cdn.yuzzl.top/map_test_3.out', '2020-07-30 18:15:42.705', NULL, '2020-07-30 18:15:42.710', 3243388, NULL);
INSERT INTO `judge_solution` VALUES (82, 'http://cdn.yuzzl.top/map_test_4.in', 'http://cdn.yuzzl.top/map_test_4.out', '2020-07-30 18:15:46.678', NULL, '2020-07-30 18:15:46.680', 3243388, NULL);
COMMIT;

-- ----------------------------
-- Table structure for problem_set
-- ----------------------------
DROP TABLE IF EXISTS `problem_set`;
CREATE TABLE `problem_set` (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `creator` int DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of problem_set
-- ----------------------------
BEGIN;
INSERT INTO `problem_set` VALUES (1, '1212', '121', 212, '2020-07-27 10:23:38', '2020-07-27 10:23:44', NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of problem_set_problem
-- ----------------------------
BEGIN;
INSERT INTO `problem_set_problem` VALUES (1, 1, 3243290);
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of submission
-- ----------------------------
BEGIN;
INSERT INTO `submission` VALUES (236, 3243388, NULL, '2020-07-30 18:34:41', NULL, NULL, 'C_PLUS_PLUS', 'SUCCESS', 1129, 10996, '{\"extraInfo\": [\"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/Main.cpp: In function ‘int main()’:\", \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/Main.cpp:15:41: warning: comparison between signed and unsigned integer expressions [-Wsign-compare]\", \"     for(int i = 0; i < s.length() - n + 1; i++) mp[s.substr(i, n)]++;\", \"                                         ^\", \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/Main.cpp:12:10: warning: unused variable ‘c’ [-Wunused-variable]\", \"     char c;\", \"          ^\"], \"judgeEndTime\": 1596105281721, \"judgeResults\": [{\"message\": \"ACCEPT\", \"condition\": 0, \"stdinPath\": \"/home/judgeEnvironment/resolutions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/3738d9d1-810d-4d41-8220-f8d2195cd168.in\", \"memoryCost\": \"1464\", \"stderrPath\": \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/d1d2ab07-5ec6-4639-a4db-968d19060ae8.err\", \"stdoutPath\": \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/dc807f88-ae5c-4e56-94c3-63dc02506d3b.out\", \"cpuTimeCost\": \"2\", \"realTimeCost\": \"3\"}, {\"message\": \"ACCEPT\", \"condition\": 0, \"stdinPath\": \"/home/judgeEnvironment/resolutions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/e65758a6-d33d-4c33-9d3e-95c7a2bdcf3a.in\", \"memoryCost\": \"10996\", \"stderrPath\": \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/28d0e697-d9d1-4342-9d4b-0e3e03a18fc1.err\", \"stdoutPath\": \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/19074e5b-c614-42f1-a726-1f7154fe0a5c.out\", \"cpuTimeCost\": \"9\", \"realTimeCost\": \"18\"}, {\"message\": \"ACCEPT\", \"condition\": 0, \"stdinPath\": \"/home/judgeEnvironment/resolutions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/9101b832-3173-436d-b773-7331ba6f308c.in\", \"memoryCost\": \"1500\", \"stderrPath\": \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/8e246b08-3fc8-4c99-b5c3-04c2eb017e73.err\", \"stdoutPath\": \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/25e22921-4e73-46c1-8159-7c4e64c0b62a.out\", \"cpuTimeCost\": \"23\", \"realTimeCost\": \"24\"}, {\"message\": \"ACCEPT\", \"condition\": 0, \"stdinPath\": \"/home/judgeEnvironment/resolutions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/089ed8b6-1ab6-464f-9757-83705ba00b02.in\", \"memoryCost\": \"4544\", \"stderrPath\": \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/34dc9154-0967-4405-a629-207696bb9456.err\", \"stdoutPath\": \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/37610d09-a4ed-4ca3-9f79-56639e48cb0a.out\", \"cpuTimeCost\": \"53\", \"realTimeCost\": \"64\"}, {\"message\": \"ACCEPT\", \"condition\": 0, \"stdinPath\": \"/home/judgeEnvironment/resolutions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/0773d7ac-cc0f-4a90-b46b-04d8e5c9318d.in\", \"memoryCost\": \"3704\", \"stderrPath\": \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/8026c76d-4279-4e7a-9584-46adb906c004.err\", \"stdoutPath\": \"/home/judgeEnvironment/submissions/904112f9-0527-44bc-8ed0-eaf3c6e085a7/59be8f30-050e-4682-89c6-7639d15fe16d.out\", \"cpuTimeCost\": \"1126\", \"realTimeCost\": \"1129\"}], \"submissionId\": \"904112f9-0527-44bc-8ed0-eaf3c6e085a7\"}', '#include <iostream>\n#include<map>\n#include <cstdio>\nusing namespace std;\n\nint main()\n{\n    int n;\n    scanf(\"%d\", &n);\n    map<string, int>mp;\n    string s;\n    char c;\n    getchar();\n    getline(cin, s);\n    for(int i = 0; i < s.length() - n + 1; i++) mp[s.substr(i, n)]++;\n    map<string,int>::iterator pointer;\n    string resolution;\n    int max = 0;\n    for(pointer = mp.begin(); pointer != mp.end(); pointer++)\n    {\n        if(pointer -> second > max)\n        {\n            max = pointer -> second;\n            resolution = pointer -> first;\n        }\n    }\n    printf(\"%s %d\", resolution.c_str(), max);\n    return 0;\n}', 'ACM');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
