
--创建用户表
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


--USE soundxp;

insert into `users`(`userid`,`username`,`password`,`enabled`) values (1,'wangdaowen','$2a$04$aVS1uFHy4Ki7W421fLJNT.ptsU/mrIvSvrDc4RiAA27iSkUfa5S0O',1);
insert into `users`(`userid`,`username`,`password`,`enabled`) values (2,'wangfeixiang','$2a$04$aVS1uFHy4Ki7W421fLJNT.ptsU/mrIvSvrDc4RiAA27iSkUfa5S0O',0);
insert into `users`(`userid`,`username`,`password`,`enabled`) values (3,'zhangmin','$2a$04$OEFHjOw4sLx5220ARuo7QuQTlvEKVqMGJbUbUQxshzm/MY4uQeEk.',1);

--创建角色表

CREATE TABLE `roles` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  `roledesc` varchar(128) NOT NULL,
  PRIMARY KEY (`roleid`),
  UNIQUE KEY `uni_username_role` (`role`,`roledesc`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


--USE soundxp;

insert into `roles`(`roleid`,`role`,`roledesc`) values (1,'ROLE_ADMIN','管理员');
insert into `roles`(`roleid`,`role`,`roledesc`) values (2,'ROLE_USER','普通用户');
insert into `roles`(`roleid`,`role`,`roledesc`) values (3,'ROLE_UNDERTAKER','承办人');
insert into `roles`(`roleid`,`role`,`roledesc`) values (4,'ROLE_SUPERVISOR','督办人');
--insert into `roles`(`roleid`,`role`,`roledesc`) values (5,'ROLE_DEMO01','测试角色01');
--insert into `roles`(`roleid`,`role`,`roledesc`) values (6,'ROLE_DEMO02','测试角色012');


--创建用户角色表
CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`userid`),
  KEY `fk_username_idx` (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


--USE soundxp;

insert into `user_roles`(`user_role_id`,`userid`,`role`) values (4,2,'ROLE_USER');
insert into `user_roles`(`user_role_id`,`userid`,`role`) values (5,1,'ROLE_ADMIN');
insert into `user_roles`(`user_role_id`,`userid`,`role`) values (6,1,'ROLE_USER');


--菜单表
CREATE TABLE `menus` (
  `menu_id` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(255) DEFAULT NULL COMMENT '菜单代码',
  `menu_created` datetime DEFAULT NULL COMMENT '添加时间',
  `menu_updated` datetime DEFAULT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `menus`(`menu_id`,`menu_name`,`menu_code`,`menu_created`,`menu_updated`) values ('93809d00-7825-11e8-ba3e-4ccc6a660ebd','工作录入','ROLE_RECORDWORK','2018-06-25 00:00:00','2018-06-25 00:00:00');
insert into `menus`(`menu_id`,`menu_name`,`menu_code`,`menu_created`,`menu_updated`) values ('a14279ff-7825-11e8-ba3e-4ccc6a660ebd','任务办理','ROLE_DEALTASK','2018-06-25 00:00:00','2018-06-25 00:00:00');
insert into `menus`(`menu_id`,`menu_name`,`menu_code`,`menu_created`,`menu_updated`) values ('cb45d0d5-7825-11e8-ba3e-4ccc6a660ebd','督办工作','ROLE_SUPERVISIONWORK','2018-06-25 00:00:00','2018-06-25 00:00:00');
insert into `menus`(`menu_id`,`menu_name`,`menu_code`,`menu_created`,`menu_updated`) values ('d5f1c689-7825-11e8-ba3e-4ccc6a660ebd','督办任务','ROLE_SUPERVISIONTASK','2018-06-25 00:00:00','2018-06-25 00:00:00');
insert into `menus`(`menu_id`,`menu_name`,`menu_code`,`menu_created`,`menu_updated`) values ('f7548ebc-7825-11e8-ba3e-4ccc6a660ebd','承办任务','ROLE_UNDERTAKETASK','2018-06-25 00:00:00','2018-06-25 00:00:00');
insert into `menus`(`menu_id`,`menu_name`,`menu_code`,`menu_created`,`menu_updated`) values ('029ee48a-7826-11e8-ba3e-4ccc6a660ebd','用户管理','ROLE_USERMANAGEMENT','2018-06-25 00:00:00','2018-06-25 00:00:00');
insert into `menus`(`menu_id`,`menu_name`,`menu_code`,`menu_created`,`menu_updated`) values ('194acfab-7826-11e8-ba3e-4ccc6a660ebd','角色/菜单管理','ROLE_ROLEMENUMANAGEMENT','2018-06-25 00:00:00','2018-06-25 00:00:00');


--菜单角色关系表
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(255) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

insert into `role_menu`(`id`,`role_id`,`menu_id`) values (1,1,'029ee48a-7826-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (2,1,'f7548ebc-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (3,1,'d5f1c689-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (4,1,'cb45d0d5-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (5,1,'a14279ff-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (6,1,'93809d00-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (7,1,'194acfab-7826-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (8,3,'a14279ff-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (9,3,'cb45d0d5-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (10,3,'f7548ebc-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (11,4,'a14279ff-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (12,4,'cb45d0d5-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (13,4,'f7548ebc-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (14,4,'d5f1c689-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (15,5,'93809d00-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (16,5,'a14279ff-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (17,5,'cb45d0d5-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (20,6,'93809d00-7825-11e8-ba3e-4ccc6a660ebd');
insert into `role_menu`(`id`,`role_id`,`menu_id`) values (21,6,'d5f1c689-7825-11e8-ba3e-4ccc6a660ebd');


