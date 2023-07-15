/*
 Navicat Premium Data Transfer

 Source Server         : Material flow
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : material flow

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 15/07/2023 18:19:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `a_id` int NOT NULL COMMENT '管理员id',
  `a_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员密码',
  `a_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员姓名',
  `a_phone` bigint NULL DEFAULT NULL COMMENT '管理员电话',
  `c_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司姓名',
  PRIMARY KEY (`a_id`) USING BTREE,
  INDEX `c_name`(`c_name`) USING BTREE,
  CONSTRAINT `administrator_ibfk_1` FOREIGN KEY (`c_name`) REFERENCES `company` (`c_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES (9, '789', 'jyh', 17358639907, '轩然公司');
INSERT INTO `administrator` VALUES (355, '123', 'zyx', 17300224091, '轩然公司');
INSERT INTO `administrator` VALUES (1218, '456', 'cjr', 17358651327, '轩然公司');
INSERT INTO `administrator` VALUES (6524, '7488', 'lyx', 17546981200, '轩然公司');

-- ----------------------------
-- Table structure for cargo
-- ----------------------------
DROP TABLE IF EXISTS `cargo`;
CREATE TABLE `cargo`  (
  `g_id` bigint NOT NULL COMMENT '货物快递号',
  `adresser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发件人',
  `consignee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收件人',
  `weight` int NOT NULL COMMENT 'kg',
  `departure_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发件地',
  `reach_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收获地',
  `c_id` int NOT NULL COMMENT '公司代号',
  `g_type` int NOT NULL COMMENT '1:运输中2:仓库中',
  `s_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '如果在仓库中储存的地方',
  `v_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '如果在运输中在什么车上',
  PRIMARY KEY (`g_id`) USING BTREE,
  INDEX `c_id`(`c_id`) USING BTREE,
  INDEX `s_name`(`s_name`) USING BTREE,
  INDEX `v_id`(`v_id`) USING BTREE,
  CONSTRAINT `cargo_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `company` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cargo_ibfk_2` FOREIGN KEY (`s_name`) REFERENCES `stash` (`s_name`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `cargo_ibfk_3` FOREIGN KEY (`v_id`) REFERENCES `vehicle` (`v_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '货物信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cargo
-- ----------------------------
INSERT INTO `cargo` VALUES (9002358255, '小牛', '小猪', 10, '攀枝花', '成都', 7, 2, '青青草原仓库', NULL);
INSERT INTO `cargo` VALUES (9002358814, '小咩', '小汪', 5, '湖北', '攀枝花', 7, 1, NULL, '川A-40989');
INSERT INTO `cargo` VALUES (9005778814, '小委', '小书', 7, '浙江', '攀枝花', 7, 1, NULL, '浙B-55473');

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `c_id` int NOT NULL COMMENT '公司代号',
  `c_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司名称',
  `representative_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '法定代表人',
  `c_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司地址',
  `registered_capital` int NULL DEFAULT NULL COMMENT '单位：万',
  `c_phone` bigint NULL DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`c_id`) USING BTREE,
  INDEX `c_name`(`c_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公司信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES (1, '中通快递', '赖梅松', '上海市青浦区', 60000, 95720);
INSERT INTO `company` VALUES (2, '韵达快递', '刘晓光', '上海市青浦区', 18977, 95546);
INSERT INTO `company` VALUES (3, '申通快递', '陈德军', '上海市青浦区', 175000, 4008995543);
INSERT INTO `company` VALUES (4, '德邦', '黄海波', '上海市青浦区', 102695, 4001991773);
INSERT INTO `company` VALUES (5, '圆通快递', '喻会蛟', '上海市青浦区', 117487, 2169777888);
INSERT INTO `company` VALUES (6, '百世', '周韵宁', '浙江省杭州市', 217196, 4009565656);
INSERT INTO `company` VALUES (7, '轩然公司', '喜羊羊', '四川省攀枝花', 200, 18875066276);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `e_id` int NOT NULL COMMENT '员工id',
  `c_id` int NOT NULL DEFAULT 7 COMMENT '公司代号',
  `e_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工姓名',
  `e_phone` bigint NULL DEFAULT NULL COMMENT '联系电话',
  `e_sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工性别',
  `salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '工资',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '职位',
  PRIMARY KEY (`e_id`) USING BTREE,
  INDEX `c_id`(`c_id`) USING BTREE,
  INDEX `e_name`(`e_name`) USING BTREE,
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `company` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '员工信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1001, 7, '何仁', 13800500001, '女', 3000.00, '技术人员');
INSERT INTO `employee` VALUES (1002, 7, '郭英让', 13800500002, '男', 6000.00, '快递员');
INSERT INTO `employee` VALUES (1003, 7, '纪笑', 13800500003, '女', 4000.00, '技术人员');
INSERT INTO `employee` VALUES (1004, 7, '章君', 13800500004, '男', 10000.00, '货车司机');
INSERT INTO `employee` VALUES (1005, 7, '路云心', 13800500005, '女', 8000.00, '仓库管理员');
INSERT INTO `employee` VALUES (1006, 7, '胡数', 13800500006, '男', 10000.00, '技术人员');
INSERT INTO `employee` VALUES (1007, 7, '向空', 13800500007, '男', 6000.00, '快递员');
INSERT INTO `employee` VALUES (1008, 7, '陈伟宸', 13800500008, '男', 5000.00, '快递员');
INSERT INTO `employee` VALUES (1009, 7, '叶院', 13800500009, '男', 5000.00, '货车司机');
INSERT INTO `employee` VALUES (1010, 7, '李玉恭', 13800500010, '女', 6100.00, '货车司机');
INSERT INTO `employee` VALUES (1011, 7, '温如', 13800500011, '男', 7000.00, '货车司机');
INSERT INTO `employee` VALUES (1012, 7, '木只晴', 13800500012, '女', 12000.00, '技术人员');
INSERT INTO `employee` VALUES (1013, 7, '苏柠', 13800500013, '女', 6000.00, '修理工');
INSERT INTO `employee` VALUES (1014, 7, '屈轩', 13800500014, '男', 7000.00, '技术人员');
INSERT INTO `employee` VALUES (1015, 7, '李习懿', 13800500015, '女', 5300.00, '快递员');
INSERT INTO `employee` VALUES (1027, 7, '古英', 13800000102, '男', 6000.00, '快递员');
INSERT INTO `employee` VALUES (1065, 7, '纪伯君', 13800000103, '女', 4000.00, '技术人员');
INSERT INTO `employee` VALUES (1118, 7, '那嘉懿', 13800010015, '女', 5300.00, '快递员');
INSERT INTO `employee` VALUES (1184, 7, '展为晨', 15846991746, '男', 6000.00, '快递员');
INSERT INTO `employee` VALUES (1185, 7, '湛伟宸', 13800010008, '男', 5000.00, '快递员');
INSERT INTO `employee` VALUES (1256, 7, '马清', 13800010005, '女', 8000.00, '货车司机');
INSERT INTO `employee` VALUES (1398, 7, '胡识', 13800010006, '男', 10000.00, '技术人员');
INSERT INTO `employee` VALUES (1555, 7, '姜瑜', 13800010014, '男', 7000.00, '仓库管理员');
INSERT INTO `employee` VALUES (1557, 7, '刘屈宇', 15894712348, '男', 7000.00, '修理工');
INSERT INTO `employee` VALUES (1789, 7, '季博军', 13502178964, '男', 5000.00, '技术人员');
INSERT INTO `employee` VALUES (1818, 7, '那易', 17342001874, '女', 8000.00, '货车司机');
INSERT INTO `employee` VALUES (1984, 7, '张璇', 15124710894, '女', 6000.00, '货车司机');
INSERT INTO `employee` VALUES (1985, 7, '章旋', 13800090004, '男', 10000.00, '货车司机');
INSERT INTO `employee` VALUES (2000, 7, '李三', 13800090002, '男', 6000.00, '快递员');
INSERT INTO `employee` VALUES (2389, 7, '向四', 13800090007, '男', 6000.00, '快递员');
INSERT INTO `employee` VALUES (2398, 7, '胡愿', 17785641299, '女', 8000.00, '快递员');
INSERT INTO `employee` VALUES (4561, 7, '陆深', 17356851377, '男', 6000.00, '货车司机');
INSERT INTO `employee` VALUES (4563, 7, '叶智原', 19985471463, '男', 9000.00, '修理工');
INSERT INTO `employee` VALUES (4568, 7, '叶志远', 13800600009, '男', 5000.00, '货车司机');
INSERT INTO `employee` VALUES (5552, 7, '邵晓', 13800100001, '女', 3000.00, '仓库管理员');
INSERT INTO `employee` VALUES (5698, 7, '林霖', 13804000001, '女', 3000.00, '技术人员');
INSERT INTO `employee` VALUES (6544, 7, '钱世', 17325668745, '男', 7000.00, '货车司机');
INSERT INTO `employee` VALUES (6585, 7, '顾习', 17854695554, '男', 7000.00, '货车司机');
INSERT INTO `employee` VALUES (6590, 7, '钱和', 13800070010, '女', 6100.00, '修理工');
INSERT INTO `employee` VALUES (6661, 7, '秦力财', 13800054763, '男', 7000.00, '货车司机');
INSERT INTO `employee` VALUES (6666, 7, '刘伟', 13800000011, '男', 7000.00, '货车司机');
INSERT INTO `employee` VALUES (7743, 7, '苏隽岩', 15320198735, '男', 6000.00, '快递员');
INSERT INTO `employee` VALUES (7745, 7, '苏秦', 13800000013, '女', 6000.00, '修理工');
INSERT INTO `employee` VALUES (8885, 7, '赵欣', 13800000012, '女', 12000.00, '技术人员');
INSERT INTO `employee` VALUES (8888, 7, '吴米', 13602761999, '女', 10000.00, '技术人员');

-- ----------------------------
-- Table structure for machine
-- ----------------------------
DROP TABLE IF EXISTS `machine`;
CREATE TABLE `machine`  (
  `m_id` int NOT NULL COMMENT '机电编号',
  `m_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `model_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '型号',
  `m_status` int NOT NULL COMMENT '1:Settings that are still in use 2:Scrap equipment',
  `stash_id` int NULL DEFAULT NULL COMMENT '仓库的id',
  PRIMARY KEY (`m_id`) USING BTREE,
  INDEX `stash_id`(`stash_id`) USING BTREE,
  CONSTRAINT `machine_ibfk_1` FOREIGN KEY (`stash_id`) REFERENCES `stash` (`stash_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '机电信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of machine
-- ----------------------------
INSERT INTO `machine` VALUES (1744, '电动机', 'Y2-180M-4', 1, 1247);
INSERT INTO `machine` VALUES (1745, '电动机', 'Y2-180M-4', 2, 1247);
INSERT INTO `machine` VALUES (1746, '发电机', 'DG6000SE', 1, 1247);
INSERT INTO `machine` VALUES (1747, '电动叉车', 'Toyota Electric Forklift 8FBE15', 1, 1247);
INSERT INTO `machine` VALUES (1748, '托盘码垛机', 'ABB IRB 660P', 1, 1247);

-- ----------------------------
-- Table structure for place
-- ----------------------------
DROP TABLE IF EXISTS `place`;
CREATE TABLE `place`  (
  `p_id` int NOT NULL COMMENT '地点id',
  `p_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省份',
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '国家',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '地址信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of place
-- ----------------------------
INSERT INTO `place` VALUES (1455, '攀枝花', '四川', '中国');
INSERT INTO `place` VALUES (1758, '丰台', '四川', '中国');
INSERT INTO `place` VALUES (7456, '杭州', '浙江', '中国');

-- ----------------------------
-- Table structure for stash
-- ----------------------------
DROP TABLE IF EXISTS `stash`;
CREATE TABLE `stash`  (
  `stash_id` int NOT NULL COMMENT '仓库id',
  `s_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '仓库名字',
  `s_area` int NOT NULL COMMENT '面积',
  `s_adress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '仓库地址',
  `c_id` int NOT NULL COMMENT '所属公司',
  `e_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库管理人',
  `s_time` date NOT NULL COMMENT '租赁时间',
  `c_time` date NOT NULL COMMENT '截止时间',
  PRIMARY KEY (`stash_id`) USING BTREE,
  INDEX `c_id`(`c_id`) USING BTREE,
  INDEX `s_time`(`s_time`) USING BTREE,
  INDEX `s_name`(`s_name`) USING BTREE,
  INDEX `stash_ibfk_2`(`e_name`) USING BTREE,
  CONSTRAINT `stash_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `company` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `stash_ibfk_2` FOREIGN KEY (`e_name`) REFERENCES `employee` (`e_name`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stash
-- ----------------------------
INSERT INTO `stash` VALUES (1247, '狼堡仓库', 4231, '攀枝花仁和区', 7, '姜瑜', '2022-06-23', '2025-09-23');
INSERT INTO `stash` VALUES (4563, '青青草原仓库', 5000, '成都市新都区', 7, '路云心', '2023-01-26', '2025-01-26');
INSERT INTO `stash` VALUES (5436, '靠谱仓库', 6000, '成都市双流区', 7, '邵晓', '2023-04-20', '2024-12-20');

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle`  (
  `v_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车牌号',
  `v_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车辆类型',
  `v_status` int NOT NULL COMMENT '1:well 2:scrap',
  `stash_id` int NOT NULL COMMENT '所属仓库',
  `e_id` int NOT NULL COMMENT '驾驶员',
  `license_id` int NOT NULL COMMENT '许可证编号',
  `t_limit` date NOT NULL COMMENT '有效期限',
  PRIMARY KEY (`v_id`) USING BTREE,
  INDEX `e_id`(`e_id`) USING BTREE,
  INDEX `stash_id`(`stash_id`) USING BTREE,
  CONSTRAINT `vehicle_ibfk_2` FOREIGN KEY (`e_id`) REFERENCES `employee` (`e_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `vehicle_ibfk_3` FOREIGN KEY (`stash_id`) REFERENCES `stash` (`stash_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '车辆信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES ('川A-40989', '丰田', 1, 4563, 1009, 1234, '2023-09-23');
INSERT INTO `vehicle` VALUES ('川B-54789', '五菱宏光', 2, 1247, 1011, 4563, '2024-04-14');
INSERT INTO `vehicle` VALUES ('川B-6578', '长安神骐', 1, 5436, 1010, 5147, '2024-07-21');
INSERT INTO `vehicle` VALUES ('浙B-55473', '鸿兴达客', 1, 5436, 4561, 3365, '2023-09-23');

SET FOREIGN_KEY_CHECKS = 1;
