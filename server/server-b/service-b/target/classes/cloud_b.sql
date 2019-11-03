// 消息消费日志
CREATE TABLE `consumer_b_log`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`correlation_id` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '消息唯一标识',
	`routing_key` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '消息路由键',
	`status` INT(1) NOT NULL DEFAULT 0 COMMENT '消息是否被消费 0未消费 1已消费 -1消费失败',
	`consume_count` INT NOT NULL DEFAULT 0 COMMENT '消息消费次数',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NULL,
	`body` MEDIUMTEXT not null comment '消息体（JSON字符串）',
	PRIMARY KEY (`id`)
)
ENGINE=INNODB;


// 消息生产日志
CREATE TABLE producer_b_log(
	`id` INT NOT NULL AUTO_INCREMENT,
	`correlation_id` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '消息唯一标识',
	`routing_key` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '消息路由键',
	`status` INT(1) NOT NULL DEFAULT 0 COMMENT '是否被消费 0：未消费 1：已消费 -1：消费失败',
	`create_time` DATETIME NOT NULL default CURRENT_TIMESTAMP,
	`update_time` DATETIME NULL,
	`body` MEDIUMTEXT not null comment '消息体（JSON字符串）',
	PRIMARY KEY (`id`)
)
ENGINE=INNODB;

CREATE TABLE `table_b`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`msg` VARCHAR(50) NOT NULL DEFAULT '',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NULL,
	PRIMARY KEY (`id`)
)
ENGINE=INNODB;