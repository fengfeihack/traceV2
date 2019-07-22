CREATE TABLE `user_info` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `username` varchar(20) NOT NULL DEFAULT '',
                             `password` varchar(20) NOT NULL DEFAULT '',
                             `email` varchar(50) NOT NULL DEFAULT '',
                             `actual_name` varchar(20) NOT NULL DEFAULT '',
                             `status` int(11) NOT NULL DEFAULT '0',
                             `level` tinyint(3) NOT NULL DEFAULT '1',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


CREATE TABLE `recorder` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `title` varchar(20) NOT NULL DEFAULT '',
                            `log_time` int(11) NOT NULL DEFAULT '0',
                            `create_time` int(11) NOT NULL DEFAULT '0',
                            `location` varchar(255) NOT NULL DEFAULT '',
                            `partner` varchar(20) NOT NULL DEFAULT '',
                            `user_id` int(11) NOT NULL DEFAULT '0',
                            `marker_id` int(11) NOT NULL DEFAULT '0',
                            `note` varchar(255) NOT NULL DEFAULT '',
                            `images` varchar(255) NOT NULL DEFAULT '',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `marker` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `create_time` int(11) NOT NULL DEFAULT '0',
                          `pointX` decimal(15,10) NOT NULL DEFAULT '0.0000000000',
                          `pointY` decimal(15,10) NOT NULL DEFAULT '0.0000000000',
                          `user_id` int(11) NOT NULL DEFAULT '0',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;


CREATE TABLE `care_info` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `careID` int(11) NOT NULL DEFAULT '0',
                             `beCareID` int(11) NOT NULL DEFAULT '0',
                             `careTime` int(11) NOT NULL DEFAULT '0',
                             `remark` varchar(255) NOT NULL DEFAULT '',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


CREATE TABLE `image` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `path` varchar(255) NOT NULL DEFAULT '',
                         `create_time` int(11) NOT NULL DEFAULT '0',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `trace`.`user_info`(`id`, `username`, `password`, `email`, `actual_name`, `status`, `level`) VALUES (1, 'admin', '123456', '', '', 0, 1);
INSERT INTO `trace`.`user_info`(`id`, `username`, `password`, `email`, `actual_name`, `status`, `level`) VALUES (2, 'zqh', '123456', '', '', 0, 1);
INSERT INTO `trace`.`user_info`(`id`, `username`, `password`, `email`, `actual_name`, `status`, `level`) VALUES (3, 'yyy', '123456', '', '', 0, 1);
INSERT INTO `trace`.`user_info`(`id`, `username`, `password`, `email`, `actual_name`, `status`, `level`) VALUES (4, 'ddt', '123456', '', '', 0, 1);
INSERT INTO `trace`.`user_info`(`id`, `username`, `password`, `email`, `actual_name`, `status`, `level`) VALUES (5, 'ewx', '123456', '', '', 0, 1);




INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (8, 1563805256, 116.6983570000, 39.7447810000, 1);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (9, 1563805259, 111.2527570000, 33.4618860000, 1);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (10, 1563805261, 117.4342490000, 31.1496340000, 1);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (11, 1563805263, 118.6116760000, 28.1938130000, 1);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (12, 1563805264, 111.6207030000, 25.0189960000, 1);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (13, 1563805289, 114.6378600000, 39.7447810000, 2);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (14, 1563805290, 92.6346920000, 33.0909910000, 2);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (15, 1563805292, 98.4482380000, 29.4253200000, 2);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (16, 1563805306, 112.3565950000, 27.4741780000, 3);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (17, 1563805308, 127.6631460000, 36.0130450000, 3);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (18, 1563805309, 116.1096430000, 42.5804200000, 3);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (19, 1563805311, 116.0360540000, 38.7721900000, 3);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (20, 1563805328, 102.7164110000, 34.6866370000, 4);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (21, 1563805329, 105.2920330000, 27.0796300000, 4);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (22, 1563805330, 115.6681080000, 32.7185090000, 4);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (23, 1563805331, 114.1963240000, 27.8018830000, 4);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (24, 1563805332, 113.0924870000, 23.1951900000, 4);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (25, 1563805334, 118.9796220000, 24.7504710000, 4);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (26, 1563805348, 92.5611030000, 35.1715090000, 5);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (27, 1563805349, 100.0672000000, 29.9394120000, 5);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (28, 1563805350, 109.0450810000, 37.7860430000, 5);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (29, 1563805351, 114.7114490000, 31.5286250000, 5);
INSERT INTO `trace`.`marker`(`id`, `create_time`, `pointX`, `pointY`, `user_id`) VALUES (30, 1563805352, 108.1620110000, 26.6175430000, 5);




INSERT INTO `trace`.`care_info`(`id`, `careID`, `beCareID`, `careTime`, `remark`) VALUES (1, 1, 2, 0, '');
INSERT INTO `trace`.`care_info`(`id`, `careID`, `beCareID`, `careTime`, `remark`) VALUES (2, 1, 3, 0, '');
INSERT INTO `trace`.`care_info`(`id`, `careID`, `beCareID`, `careTime`, `remark`) VALUES (3, 1, 4, 0, '');
INSERT INTO `trace`.`care_info`(`id`, `careID`, `beCareID`, `careTime`, `remark`) VALUES (4, 1, 5, 0, '');
