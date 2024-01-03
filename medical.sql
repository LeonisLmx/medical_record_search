/*
 Navicat Premium Data Transfer

 Source Server         : Huawei_Cloud
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : 124.70.185.49:3306
 Source Schema         : medical

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 26/05/2023 23:50:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for encrypt_record
-- ----------------------------
DROP TABLE IF EXISTS `encrypt_record`;
CREATE TABLE `encrypt_record` (
                                  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                                  `hash_id` varchar(255) DEFAULT NULL,
                                  `encrypt_key` text,
                                  `encrypt_data` text,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of encrypt_record
-- ----------------------------
BEGIN;
INSERT INTO `encrypt_record` VALUES (16, '091e0a6e5cde04b715d7c5cd5f3437f2b52a5c3b7176d46d15bbc25df652a767', 'dB4YT3h27EDepY/3jG6XgTuSSEWZwqPO5esS9nt7CnkXGCgOogGS7gMaRWmMIZKgzLVb4moZiNXZ3ea7Q7SB0j0rOqh490PU6JswL3zHL/CEEe6/PnXPG+4OACqJGjiUul/5RB+IcvW1bcNJW8al4zVnAkw2CurZgqtk5BxfqBY=', 'W5JUeOuGFCyp7cBWxlmiZw==');
INSERT INTO `encrypt_record` VALUES (17, '33e0bd02fb36d2d86c1d426e9a886e74fac6c5eaaf4608806fb308f547b9ff64', 'SeoyIIuY1vNQXdvwsgbAdIFwtEHx97bd7rxi5fcRG4SEPv3zdZYv8/ShoAiBsw34iCHuwD2NUI91gYk8rmgQgbmhPlonehYOPjBvw1B37qaYX5X6UlN4Hb8nXUxEYEG+KhBfpf3QppcU1AFcVO29tDe4BhFsDZBmdHOUcYheRhY=', 'Q4QVMkb3Z2alQI0T2MEUzA==');
INSERT INTO `encrypt_record` VALUES (18, '869822e7ea935e169f1b1fcf5bec6d655fe05b05d428c1e1d9c37dcb30abe8d4', 'VLuT00gMw5TI2qxkZEHcqVpuYj2sK6mj55jyMdOmIqhNiQdCy4zvDCvTCyA/Mcn5MZ9fjvuXfjWQPH2x9RB2saJPcOS7Rz4A65gvDiaB8MrSB4E1Hwq4EDcp+5BmiTvYAuxGsLA048CAXPH0pvDWcjkLkQakdZgOqSypFVWU+bs=', 'W8TMZAD0nNsYhGlgwfWu8Q==');
INSERT INTO `encrypt_record` VALUES (19, 'aafbb7ba4f23e785d354d3cb5f953cbc366e2f51b5a9efd843362df0cc1c05e2', 'UT0A82T0enimWjyipQgi2hWMJdZXgq6ES/aWa2SeQjzisZjbdQxqrUVD2OVxc9LL49Fj7dGLjmaOXz2fLuAWfNws/P4f5NpdNmFBCfSxaTgKidqRtWpN+Xa7m7wE0QRQpquWK7Hqz/HrCRLeyGuo5PXwRdPCq7DaVjQzcSNCoHA=', 'kB5shRiLOXVz/806fOMgUQ==');
INSERT INTO `encrypt_record` VALUES (20, '5c36fcc0eeab8ab80ebaecbdd0a43f609e9405e5350351e9b576ac926df3ee5a', 'aOOK4lGh7ZenjvsIKSTtxUqvhFH+mmeOfhXigrCqwT1EgGHoFsGGqj44UWjyLgLR5n7aPmF7rC9JWqzhCDm4QQ0L0OMTe3AagDW2YvS+zGxFTEjRtdDOE1XGQ/7cc+FXutC0YOTIndoe2Erb9k5rKTSv8GT3f65J1uAuQ9Lvino=', 'Jr6eZlrEt7DuOpN13wAfDA==');
INSERT INTO `encrypt_record` VALUES (21, 'c4e5869e736ef621474ab6b74190730e2ddab65bac81bde79ead53644ca6b642', 'upxkQSJFuZZn++X247w9EvJNSLBo/DtNjuVVPjOYkXwyGdZ56PrKk1vkoIEKxYV3dHXapS6FgAWQ0cmFineLHGaR6qAvAbuOCqq45jwyUb26ZeBGZJ6cW8QDI0uc/utlAITYxdSkgFX4S/D470CmyDJ7TS1a0HdwnKxudeWX48Y=', 'MbrA279wo20o3Z+4nFuqdw==');
INSERT INTO `encrypt_record` VALUES (22, '8bee8f8b9fe1e5a4ce2600af639e3c9f8b07688d1ee8f4fcc7f60a2ba9fe31aa', 'bDXjM4YSJ6TuLnA2d3t5XVa0OHEaXIbTiDhdfUMTcdJxb2APe1LI4do8tXWUiSuT2oOrTkunMgZoNV3t2o3BEdMzTmlrPAERGkgBNFzJ/pnIWu0g3dz9VSacS7c6hYWLJBluuGo3icxPXiSlGwtDSy9SzOfsXsLVP35vGKNDGrU=', 'M0VkbtxQaYLLSr3dX3vGSw==');
INSERT INTO `encrypt_record` VALUES (23, 'aafbb7ba4f23e785d354d3cb5f953cbc366e2f51b5a9efd843362df0cc1c05e2', 'E/43q3zSap3KwCW0lxNaZIJj6TLeoF+7AXfgDVCKetWmOftEcPY+JoN0OXBdNuVspv10ayl9QjL9fbr/CdCty/CRLKRE4M32Bnppj3wI/ebTPeA+atzw32oGI7eKc1C9kJkR1Op0HsbcKij209gXli1f0Ri5b4fuwt7nLmBU+tQ=', 'kB5shRiLOXVz/806fOMgUQ==');
INSERT INTO `encrypt_record` VALUES (24, '80c77161b4c8a5733eda8cfc1559a881dbd91ca0c6d8f64925439bf61b70a1b4', 'SE5hdnDeFCBjI0OUNmeUD46GA9XE85m+D2JlGqhHOBQKfEixsTHZ0FDJRDClCp3HcjiOhxjop/m9hulchXErnsh/+90I2GcYpeMje9esGMHHKqeCibGnfP98EvKYW0FmtFqZ7fAJISzG261girIvT8DmxjGqJ39L6HoxyxD4d68=', 'Mb91d88bOZkgeF14Y+g4jg==');
INSERT INTO `encrypt_record` VALUES (25, 'bcf712eae28a6945b9d3d59b8186905eef12292194a6b244fe0cfb0ed224985c', 'bRBhws+NvsvKH8ZblvhWvGrv+I4i1dR+NaRwBximLTsLp60TiKuVQgMKUTOKHFPJbT/miFoCacLuERsOFlHH2b5FbhVYpcQFU0+yKw5CRB9ZuvSRDSN1hgyGXGfTAtlUFgTsMlCZ0e7C7/HnPyJ93IYanefWcSua/zVFlIg8N0U=', 'x+Unsf7CANljZOVrIh3Y8A==');
INSERT INTO `encrypt_record` VALUES (26, '05a0f7a9dc73a864fdffc6dc8b5e0d11da4d8ed3f21b14fd0955b58e7bbdacf1', 'sbY8aDJ/EoS2stVj1InIxF6cKPls85qeXnPrLehCw0LPJJ7+xqx3ncZFd7J9I75VXN5eY/q4+2x1w90QscF/R+U6BD1iGSJotorhrGr08TxqjWz0mYljKUKhpAWNDNoqSpEXfTxMM8LOVKqkXvromYsqYdsCY75On4kn2khJBGU=', 'la5KO1jfvYZE5oxF3405Yg==');
INSERT INTO `encrypt_record` VALUES (27, 'c56d09af6276f2f2b712db39289e726d18f9872335a9967918c58bb401a29c06', 'uO0qbzLBwmcM/A2X0Q4f2qbdfm6BqMTSRRTqbVrw1kJIFd+0C+vi0lXHNt09iw8ZC3WUj57dUXts3vHYM+c3HmRe8g+iCmpjcRZ9HnF4dZSrurBuW0y0whdNw8GOHH3Y+7pUeOsBb1U3n/Y0jxdQU0boAOZIfhuBcc4xISGEZKQ=', 'O2sIn0/A3V4ebmUEZfkT9w==');
INSERT INTO `encrypt_record` VALUES (28, '396503f8e796e2ba27588b4b6317e6b411eec7a8a02fadaac0acb1fa7cbb131c', 'XOWk04DSLqhnQQpgUklnePBTBAkOyk8222l1WbmddOyZ0vjyLoAPghhIt3xZ0CeEmkgo+UAovD5x7cvNxYMbAaGV9L2xJ0i/BJnldlYHyyjmbP9t8tG17SHNjyxut0jpRRTLMZkoZAXDcNHyJUT0XEuHEx8FM+JYcerjg5WJfFw=', 'qZ0iuSYxgqwEjAljDZ/jQI1VWaP6NZOmdSS3iFGYyTM=');
INSERT INTO `encrypt_record` VALUES (29, '81e4d3dda6b87f265c587f3b098e2a54a3379d31dbfa63076a54332a2ca686e6', 'mjppSMmMcoUznoyS8jS9+gcwBlZ/GEim/qNSMJUAa7bfG6VEfhA05n9g3JdSt68mHYV5vlgIXyBFomkvatI+AZLTkeweaL53szUN4CbcvcKkhDRuZrKmmHXfLxCUN8JM6QppVIQfm3IqYaxxE0AIIvgQ2t8XRORiCl3M/JBbm/c=', 'vqlybQXrySBpkm/xqepB2g==');
INSERT INTO `encrypt_record` VALUES (30, '25f6619afc9e70be1e7eeda551b2fd15621291e073a012f67cc2acb996592d62', 'ot1LiYnbzjhkCqHZf3lyWTmwh7a58ZzcSPi2xDOS9p4yjEDlDfIH2jopg2mKjNpZPnWfwXs5O2S9N8j9Grha9bSzhP8aAxt/Lp9VS653EEM7rM4eHkew+WPuAsk8Tr3yTrPjPRZAqFv7aI9ZhqDd1FpE+bIrYf3JsUuBMtu8jyM=', 'iRTPG2/ORtcQrj1qmg1itg==');
INSERT INTO `encrypt_record` VALUES (31, '0b97b8de27283fc569c8a292d5680b6122e1a13f94a5a361e34ef990d8c4ee26', 'ZMuM9vNSWUOK/WdhMAIItjFAbmXVxxtzjffUBQndpN8o9uHUXi2pCqJQkyfUHhuTe1kPMnkRfUzCuAVw+aDPuI3d51Yxy23sMjCGfIXGbX3bQmjAr4J+gEzG9MqtxcEP2PBeB/XwDOgJ3iuWghyYvxdYuTyUJn1gyzE5L5twCJU=', 'HQT9f/GCl+XqI5ly1ygRFg==');
INSERT INTO `encrypt_record` VALUES (32, 'fac326d900486bc8beccbc23dbe72ee55147cc197c91b517d647999621e9c9c5', 'iJ9qPKqVQ1lIdIfu3zZZgOEkVvhKgIoNWF6yB2bhvz5RQfMsJbHq1e7rtAa0qWXSnJ4lc6CPUrPYBLjIkelOijlccjExHd3v3fd2Xyqxi4KySzk9rN0Om5rJm+WxQH2y78AnbYcwq7eRg6d4klWq1KWD9GgaGShiAmmxWQ9C8oU=', 'oXwlxktFNCyBpX5lMxRxIw==');
INSERT INTO `encrypt_record` VALUES (33, '2e0b1685c22321008a9b565ef265ada3d50b84c31e9b2b7c1c8966e446929754', 'ZRrQrrKZb0OYOlWCKyOHqiPBleJOzWRxqRJabK1Odmp/4Eo2kzq3PYVg5teNtCGfkWc/qTdQxcNWLYHASyfyZj+C2mdAtFolnPmGhu5RObKxaajd2nOzVTlQBUMAz80+JbpZ0cg4rd5EtlNqSph433ZsxKNcZqIFCo+JBHevs/Y=', 'JwmDpSJMgKiFEWZttNGZtw==');
INSERT INTO `encrypt_record` VALUES (34, '97c3e0e20985403ba704b8c50630b2af7709f60e7e840e1a6d8feba74816f97b', 'E0LBGByrCH0R5SpRbHiamBZLbtkcIlDBzEOF5ssDSl8i43rOCcj2uRjNQPFJUVEfJ5B+NfRmSZnkYYFYWQJ3lxzB6sOEXSyZmBnUJJkL6/OQWWYljCLhrpdzP8VNSvO5s+bOOzZVpOp0d7l+5Z6cKExyV8A0byeCnrH+ClJZjrY=', 'MzGWhwLtw2noYFI+RvT7qA0w7QUNxhNoXmDIEj3jayc=');
INSERT INTO `encrypt_record` VALUES (35, '647eb2afee429c3cfd8ac979880a1b4018de68fecfbaa9050a4accdb8b93251a', 'X14BTI45gA/TbujCnMDHzfySn6cu1/nQDLzPZJJ4+8b32aFcejs0UAkHA6VCNmyvAO47FC3n2fhhaBlTJCjvUcUst3Toi+fVerFOqmRjMkdvHgmyMjC29wss/rC5mFdtWbdcvLxrwK2myWcGVzUH2wjh+gCTYk7DM0EbYgCiFFg=', 'w2Q4K8pDV7X5+nw/3ni/ng==');
COMMIT;

-- ----------------------------
-- Table structure for medical_record
-- ----------------------------
DROP TABLE IF EXISTS `medical_record`;
CREATE TABLE `medical_record` (
                                  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                                  `name` varchar(255) DEFAULT NULL,
                                  `sex` varchar(255) DEFAULT NULL,
                                  `age` varchar(255) DEFAULT NULL,
                                  `description` varchar(255) DEFAULT NULL,
                                  `doctor_name` varchar(255) DEFAULT NULL,
                                  `history_description` varchar(255) DEFAULT NULL,
                                  `drug` varchar(255) DEFAULT NULL,
                                  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of medical_record
-- ----------------------------
BEGIN;
INSERT INTO `medical_record` VALUES (3, '8bee8f8b9fe1e5a4ce2600af639e3c9f8b07688d1ee8f4fcc7f60a2ba9fe31aa', '0', '30', 'c4e5869e736ef621474ab6b74190730e2ddab65bac81bde79ead53644ca6b642', '80c77161b4c8a5733eda8cfc1559a881dbd91ca0c6d8f64925439bf61b70a1b4', 'aafbb7ba4f23e785d354d3cb5f953cbc366e2f51b5a9efd843362df0cc1c05e2', 'bcf712eae28a6945b9d3d59b8186905eef12292194a6b244fe0cfb0ed224985c', '2023-05-26 15:22:32', '2023-05-26 15:22:32');
INSERT INTO `medical_record` VALUES (4, 'c56d09af6276f2f2b712db39289e726d18f9872335a9967918c58bb401a29c06', '1', '50', '05a0f7a9dc73a864fdffc6dc8b5e0d11da4d8ed3f21b14fd0955b58e7bbdacf1', '81e4d3dda6b87f265c587f3b098e2a54a3379d31dbfa63076a54332a2ca686e6', '396503f8e796e2ba27588b4b6317e6b411eec7a8a02fadaac0acb1fa7cbb131c', '25f6619afc9e70be1e7eeda551b2fd15621291e073a012f67cc2acb996592d62', '2023-05-26 15:39:37', '2023-05-26 15:39:37');
INSERT INTO `medical_record` VALUES (5, 'fac326d900486bc8beccbc23dbe72ee55147cc197c91b517d647999621e9c9c5', '0', '30', '0b97b8de27283fc569c8a292d5680b6122e1a13f94a5a361e34ef990d8c4ee26', '97c3e0e20985403ba704b8c50630b2af7709f60e7e840e1a6d8feba74816f97b', '2e0b1685c22321008a9b565ef265ada3d50b84c31e9b2b7c1c8966e446929754', '647eb2afee429c3cfd8ac979880a1b4018de68fecfbaa9050a4accdb8b93251a', '2023-05-26 15:40:20', '2023-05-26 15:40:20');
COMMIT;

-- ----------------------------
-- Table structure for user_record
-- ----------------------------
DROP TABLE IF EXISTS `user_record`;
CREATE TABLE `user_record` (
                               `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                               `user_name` varchar(255) DEFAULT NULL,
                               `password` varchar(255) DEFAULT NULL,
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                               `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_record
-- ----------------------------
BEGIN;
INSERT INTO `user_record` VALUES (1, 'goLfAELVjjdnoF2e9yy11w==', 'goLfAELVjjdnoF2e9yy11w==', '2023-05-25 05:59:33', '2023-05-25 05:59:33');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
