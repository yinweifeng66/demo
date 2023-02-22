/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 22/02/2023 13:07:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_goods
-- ----------------------------
DROP TABLE IF EXISTS `demo_goods`;
CREATE TABLE `demo_goods`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_desc` varchar(2024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `goods_price` int(10) NULL DEFAULT 9999999 COMMENT '商品价格，单位为：分',
  `goods_maket_price` int(10) NULL DEFAULT 9999999 COMMENT '商品市场价，即原价，单位为：分',
  `goods_stock` int(10) NULL DEFAULT 0 COMMENT '商品库存',
  `goods_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片链接',
  `goods_sell_time` datetime(0) NULL DEFAULT NULL COMMENT '商品上架时间，开始售卖时间',
  `goods_status` tinyint(3) NULL DEFAULT 1 COMMENT '商品状态，0待审核，1正常售卖，2已下架，3已售罄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_goods
-- ----------------------------
INSERT INTO `demo_goods` VALUES (1, '商品1', NULL, 20, 9999999, 10, NULL, NULL, 1);
INSERT INTO `demo_goods` VALUES (2, '商品2', NULL, 100, 9999999, 10, NULL, NULL, 1);

-- ----------------------------
-- Table structure for demo_order
-- ----------------------------
DROP TABLE IF EXISTS `demo_order`;
CREATE TABLE `demo_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户id',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `yesapi_shopxo_s_order_status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '订单状态（0待确认, 1已确认/待支付, 2已支付/待发货, 3已发货/待收货, 4已完成, 5已取消, 6已关闭）',
  `pay_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '支付状态（0未支付, 1已支付, 2已退款, 3部分退款）',
  `buy_number_count` int(11) NOT NULL DEFAULT 0 COMMENT '购买商品总数量',
  `pay_price` int(10) NOT NULL COMMENT '商品总价格',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_no`(`order_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5023 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of demo_order
-- ----------------------------

-- ----------------------------
-- Table structure for demo_user_balances
-- ----------------------------
DROP TABLE IF EXISTS `demo_user_balances`;
CREATE TABLE `demo_user_balances`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `balances` int(10) NOT NULL DEFAULT 0 COMMENT '结余',
  `pay_password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '支付密码',
  `user_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `user_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `user_password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户钱包' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_user_balances
-- ----------------------------
INSERT INTO `demo_user_balances` VALUES (1, 1, 100, '1234', '用户1', '123', '123');

-- ----------------------------
-- Table structure for demo_user_balances_info
-- ----------------------------
DROP TABLE IF EXISTS `demo_user_balances_info`;
CREATE TABLE `demo_user_balances_info`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `user_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `play_num` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收付金额日志',
  `play_data` datetime(0) NOT NULL COMMENT '收付金额时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户钱包明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_user_balances_info
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
