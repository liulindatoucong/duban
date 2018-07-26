
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



CREATE TABLE `bts_metrialinfo` (
  `HANDLE` varchar(200) COLLATE utf8_bin NOT NULL,
  `PLANT` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '工厂',
  `MATERIAL` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '物料编码',
  `MATDESCRIPTION` varchar(412) COLLATE utf8_bin NOT NULL COMMENT '物料描述',
  `SYSTEMCODE` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '储能装置中的编号\n对应吉利API serial字段',
  `PACKMODLE` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '电池包型号\n对应吉利API modelId字段',
  `SYSTEMID` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '所属储能装置编码',
  `SYSTEMMODELID` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '储能装置型号',
  `MODELID` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '模块型号',
  `CELLMODELID` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '单体型号',
  `STATUS` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATEUSER` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '创建用户',
  `CHANGETIME` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `CHANGEUSER` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`HANDLE`),
  UNIQUE KEY `MATERIAL_UNIQUE` (`MATERIAL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='物料信息表';


CREATE TABLE `bts_uploadlog` (
  `HANDLE` varchar(200) COLLATE utf8_bin NOT NULL,
  `PLANT` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '站点',
  `REQUESTMSG` mediumtext COLLATE utf8_bin NOT NULL COMMENT '发送的消息',
  `RESPONSEMSG` mediumtext COLLATE utf8_bin COMMENT '返回的消息',
  `IP` varchar(412) COLLATE utf8_bin DEFAULT NULL COMMENT 'IP',
  `STATUS` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `STARTTIME` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `ENDTIME` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `USER` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '操作用户',
  PRIMARY KEY (`HANDLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='发送电芯到吉利的日志表';


CREATE TABLE `bts_packcode_preview` (
  `PACKHANDLE` varchar(200) COLLATE utf8_bin NOT NULL COMMENT 'PACK唯一索引序列号',
  `PLANT` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '工厂',
  `PACKCODE` varchar(24) COLLATE utf8_bin NOT NULL COMMENT 'PACK序列号',
  `PACKMATERIAL` varchar(9) COLLATE utf8_bin NOT NULL COMMENT 'PACK物料编码',
  `PACKORDER` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT 'PACK订单号',
  `STATUS` varchar(6) COLLATE utf8_bin NOT NULL COMMENT '10新增\n20 删除\n30 已上传\n',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日期时间',
  `CREATEUSER` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '创建用户',
  `CHANGETIME` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `CHANGEUSER` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '修改用户',
  `UPLOADTIME` timestamp NULL DEFAULT NULL COMMENT '上传时间',
  `UPLOADUSER` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '上传用户',
  PRIMARY KEY (`PACKHANDLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PACK电池包信息表预览表';


CREATE TABLE `bts_packmodule_preview` (
  `MOUDLEHANDLE` varchar(200) COLLATE utf8_bin NOT NULL COMMENT 'handle',
  `PACKHANDLE` varchar(200) COLLATE utf8_bin NOT NULL COMMENT 'Pack HANDLE',
  `PACKCODE` varchar(24) COLLATE utf8_bin NOT NULL COMMENT 'PACK序列号',
  `MOUDLECODE` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '模块序列号',
  `MOUDLEMATERIAL` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '模块物料号',
  `STATUS` varchar(6) COLLATE utf8_bin NOT NULL COMMENT '状态',
  PRIMARY KEY (`MOUDLEHANDLE`),
  KEY `PACKMODULE2PACKPREVIEWFORK_idx` (`PACKHANDLE`),
  CONSTRAINT `PACKMODULE2PACKPREVIEWFORK` FOREIGN KEY (`PACKHANDLE`) REFERENCES `bts_packcode_preview` (`PACKHANDLE`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PACK与模块关系预览表';



CREATE TABLE `bts_moudlecell_preview` (
  `CELLHANDLE` varchar(200) COLLATE utf8_bin NOT NULL COMMENT 'HANDLE',
  `MOUDLEHANDLE` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '模块对应的handle',
  `MOUDLECODE` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '模块序列号',
  `CELLCODE` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '电芯序列号',
  `CELLMATERIAL` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '电芯物料号',
  `STATUS` varchar(6) COLLATE utf8_bin NOT NULL COMMENT '状态\n10新增\n20 删除\n30 已上传\n',
  PRIMARY KEY (`CELLHANDLE`),
  KEY `CELL2MODULEPREVIEW_idx` (`MOUDLEHANDLE`),
  CONSTRAINT `CELL2MODULEPREVIEW` FOREIGN KEY (`MOUDLEHANDLE`) REFERENCES `bts_packmodule_preview` (`MOUDLEHANDLE`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模块与电芯关系表';


CREATE TABLE `bts_packcode` (
  `PACKHANDLE` varchar(200) COLLATE utf8_bin NOT NULL COMMENT 'PACK唯一索引序列号',
  `PLANT` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '工厂',
  `PACKCODE` varchar(24) COLLATE utf8_bin NOT NULL COMMENT 'PACK序列号',
  `PACKMATERIAL` varchar(9) COLLATE utf8_bin NOT NULL COMMENT 'PACK物料编码',
  `PACKORDER` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT 'PACK订单号',
  `STATUS` varchar(6) COLLATE utf8_bin NOT NULL COMMENT '10新增\n20 删除\n30 已上传\n',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日期时间',
  `CREATEUSER` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '创建用户',
  `CHANGETIME` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `CHANGEUSER` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '修改用户',
  `UPLOADTIME` timestamp NULL DEFAULT NULL COMMENT '上传时间',
  `UPLOADUSER` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '上传用户',
  PRIMARY KEY (`PACKHANDLE`),
  UNIQUE KEY `PACKCODE_UNIQUE` (`PACKCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PACK电池包信息表';




CREATE TABLE `bts_packmodule` (
  `MOUDLEHANDLE` varchar(200) COLLATE utf8_bin NOT NULL COMMENT 'handle',
  `PACKHANDLE` varchar(200) COLLATE utf8_bin NOT NULL COMMENT 'Pack HANDLE',
  `PACKCODE` varchar(24) COLLATE utf8_bin NOT NULL COMMENT 'PACK序列号',
  `MOUDLECODE` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '模块序列号',
  `MOUDLEMATERIAL` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '模块物料号',
  `STATUS` varchar(6) COLLATE utf8_bin NOT NULL COMMENT '状态',
  PRIMARY KEY (`MOUDLEHANDLE`),
  UNIQUE KEY `MOUDLECODE_UNIQUE` (`MOUDLECODE`),
  KEY `PACKMODULE2PACKFORK_idx` (`PACKHANDLE`),
  CONSTRAINT `PACKMODULE2PACKFORK` FOREIGN KEY (`PACKHANDLE`) REFERENCES `bts_packcode` (`PACKHANDLE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PACK与模块关系表';



CREATE TABLE `bts_moudlecell` (
  `CELLHANDLE` varchar(200) COLLATE utf8_bin NOT NULL COMMENT 'HANDLE',
  `MOUDLEHANDLE` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '模块对应的handle',
  `MOUDLECODE` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '模块序列号',
  `CELLCODE` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '电芯序列号',
  `CELLMATERIAL` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '电芯物料号',
  `STATUS` varchar(6) COLLATE utf8_bin NOT NULL COMMENT '状态\n10新增\n20 删除\n30 已上传\n',
  PRIMARY KEY (`CELLHANDLE`),
  UNIQUE KEY `CELLCODE_UNIQUE` (`CELLCODE`),
  KEY `CELL2MODULE_idx` (`MOUDLEHANDLE`),
  CONSTRAINT `CELL2MODULE` FOREIGN KEY (`MOUDLEHANDLE`) REFERENCES `bts_packmodule` (`MOUDLEHANDLE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模块与电芯关系表';




INSERT INTO `soundtrace`.`bts_metrialinfo`
(`HANDLE`,
`PLANT`,
`MATERIAL`,
`MATDESCRIPTION`,
`SYSTEMCODE`,
`PACKMODLE`,
`SYSTEMID`,
`SYSTEMMODELID`,
`MODELID`,
`CELLMODELID`,
`STATUS`,
`CREATETIME`,
`CREATEUSER`)
VALUES
('3002103010336',
'3002',
'103010336',
'动力电池系统（321.2V128Ah)无加热',
'SYSTEMCODE',
'STJ-HSD-H01-45.68-1',
'SYSTEMID',
'STJ-HSD-H01-45.68-1',
'8688190-4P',
'SEPNi8688190-15Ah',
'10',
current_timestamp,
'liulin');


