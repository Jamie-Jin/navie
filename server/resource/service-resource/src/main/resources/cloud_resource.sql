-- 资源表 --
CREATE TABLE `resource`(
	`id` INT NOT NULL auto_increment,
	`name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '资源名称',
	`count` INT NOT NULL DEFAULT 0 COMMENT '资源数量',
	`price` DECIMAL NOT NULL DEFAULT 0 COMMENT '价钱',
	`enable` INT(1) NOT NULL DEFAULT 0 COMMENT '是否上架 0：下架 1：上架',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NULL,cloud_resource
	PRIMARY KEY(`id`)
)
ENGINE=INNODB;