// 用户表
CREATE TABLE `user`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`account` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '账号',
	`name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '用户名',
	`password` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '密码',
	`enable` INT(1) NOT NULL DEFAULT 0 COMMENT '是否启用 0不启用 1启用',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` TIMESTAMP NULL,
	PRIMARY KEY(`id`)
)
ENGINE=INNODB;

// 角色表
CREATE TABLE `role`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`role` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '角色',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` TIMESTAMP NULL,
	PRIMARY KEY(`id`)
)
ENGINE=INNODB;

// 用户角色表(中间表)
CREATE TABLE `user_role`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`role_id` INT NOT NULL DEFAULT 0 COMMENT '角色id',
	`user_id` INT NOT NULL DEFAULT 0 COMMENT '用户id',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` timestamp null,
	PRIMARY KEY(`id`)
)
ENGINE=INNODB;
