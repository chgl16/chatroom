DROP DATABASE IF EXISTS `chatroom`;
CREATE DATABASE `chatroom` DEFAULT CHARACTER SET utf8;
USE `chatroom`;

-- ------------
-- 创建用户表   |
-- ------------
CREATE TABLE `sys_user` (
    `id` INT UNSIGNED AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(12) UNIQUE COMMENT '用户名',
    `shadow` VARCHAR(100) NOT NULL COMMENT '密文',
     PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ------------
-- 创建角色表   |
-- ------------
CREATE TABLE `sys_role` (
    `id` INT UNSIGNED AUTO_INCREMENT COMMENT '角色ID',
    `name` VARCHAR(12) UNIQUE COMMENT '角色名',
     PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ------------------
-- 创建用户角色关系表  |
-- ------------------
CREATE TABLE `sys_user_role` (
    `id` INT UNSIGNED AUTO_INCREMENT COMMENT '关系ID',
    `user_id` INT UNSIGNED COMMENT '用户ID',
    `role_id` INT UNSIGNED COMMENT '角色ID',
     PRIMARY KEY(`id`),
     FOREIGN KEY(`user_id`) REFERENCES `sys_user`(`id`),
     FOREIGN KEY(`role_id`) REFERENCES `sys_role`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
