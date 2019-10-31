// 记录生产端和消费端的消息消费情况
CREATE TABLE `message`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`correlation_id` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '消息唯一标识',
	`exchange_remark` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '消息投递到Exchange的情况',
	`exchange_status` INT(1) NOT NULL DEFAULT 0 COMMENT '消息是正确投递到exchange 0初始值 1正确投递 -1投递失败',
	`consume_status` INT(1) NOT NULL DEFAULT 0 COMMENT '消息是否被消费者消费 0未消费 1已消费 -1消费失败',
	`consume_remark` varchar(100) NOT NULL DEFAULT '' COMMENT '消息的消费情况',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATEcloud_messageTIME NULL,
	PRIMARY KEY (`id`)
)
ENGINE=INNODB;