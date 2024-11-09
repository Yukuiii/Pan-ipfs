CREATE TABLE `file` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `file_hash` varchar(100) NOT NULL COMMENT '文件哈希值',
    `file_name` varchar(255) NOT NULL COMMENT '文件名',
    `file_size` varchar(50) NOT NULL COMMENT '文件大小',
    `file_url` varchar(255) NOT NULL COMMENT '文件访问地址',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件记录表'; 