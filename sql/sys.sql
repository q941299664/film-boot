-- 用户表
CREATE TABLE
    IF
    NOT EXISTS `sys_user_tb`
(
    `id`          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    `username`    VARCHAR(255) NOT NULL COMMENT '用户名',
    `password`    VARCHAR(255) NOT NULL COMMENT '密码',
    `email`       VARCHAR(255) COMMENT '电子邮箱',
    `deleted`     BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '删除标志',
    `update_id`   BIGINT       NOT NULL COMMENT '更新者ID',
    `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`   BIGINT       NOT NULL COMMENT '创建者ID',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '用户表';
-- 角色表
CREATE TABLE
    IF
    NOT EXISTS `sys_role_tb`
(
    `id`          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '角色ID',
    `parent_id`   BIGINT               DEFAULT 0 COMMENT '上一级角色ID',
    `name`        VARCHAR(30) NOT NULL COMMENT '角色名称',
    `deleted`     BOOLEAN     NOT NULL DEFAULT FALSE COMMENT '删除标志',
    `update_id`   BIGINT      NOT NULL COMMENT '更新者ID',
    `update_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`   BIGINT      NOT NULL COMMENT '创建者ID',
    `create_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '角色表';
-- 权限表
CREATE TABLE
    IF
    NOT EXISTS `sys_permission_tb`
(
    `id`          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '权限ID',
    `name`        VARCHAR(20) NOT NULL COMMENT '权限名称',
    `parent_id`   BIGINT               DEFAULT 0 COMMENT '上一级权限ID',
    `type`        CHAR(1) COMMENT '权限类型',
    `uri`         VARCHAR(50) COMMENT '权限字符串',
    `meta`        TEXT COMMENT '权限元数据',
    `sort`        INT                  DEFAULT 0 COMMENT '排序依据字段',
    `deleted`     BOOLEAN     NOT NULL DEFAULT FALSE COMMENT '删除标志',
    `update_id`   BIGINT      NOT NULL COMMENT '更新者ID',
    `update_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`   BIGINT      NOT NULL COMMENT '创建者ID',
    `create_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '权限表';
-- 用户_角色表
CREATE TABLE
    IF
    NOT EXISTS `sys_user_role_tb`
(
    `id`          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '用户角色ID',
    `user_id`     BIGINT COMMENT '用户ID',
    `role_id`     BIGINT COMMENT '角色ID',
    `deleted`     BOOLEAN   NOT NULL DEFAULT FALSE COMMENT '删除标志',
    `update_id`   BIGINT    NOT NULL COMMENT '更新者ID',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`   BIGINT    NOT NULL COMMENT '创建者ID',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '用户_角色';
-- 角色_权限表
CREATE TABLE
    IF
    NOT EXISTS `sys_role_permission_tb`
(
    `id`            BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '角色_权限ID',
    `role_id`       BIGINT COMMENT '角色ID',
    `permission_id` BIGINT COMMENT '权限ID',
    `deleted`       BOOLEAN   NOT NULL DEFAULT FALSE COMMENT '删除标志',
    `update_id`     BIGINT    NOT NULL COMMENT '更新者ID',
    `update_time`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`     BIGINT    NOT NULL COMMENT '创建者ID',
    `create_time`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '角色_权限表';
-- 系统字典表
CREATE TABLE
    IF
    NOT EXISTS `sys_dict_tb`
(
    `id`          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '字典ID',
    `name`        VARCHAR(20)           DEFAULT NULL COMMENT '字典名称',
    `code`        VARCHAR(100) NOT NULL COMMENT '字典编码',
    `sort`        INT                   DEFAULT 0 COMMENT '排序依据字段',
    `deleted`     BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '删除标志',
    `update_id`   BIGINT       NOT NULL COMMENT '更新者ID',
    `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`   BIGINT       NOT NULL COMMENT '创建者ID',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '系统字典表';
-- 系统字典数据表
CREATE TABLE
    IF
    NOT EXISTS `sys_dict_data_tb`
(
    `id`          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '字典数据ID',
    `parent_id`   BIGINT                DEFAULT 0 COMMENT '上一级字典数据ID',
    `code`        VARCHAR(100) NOT NULL COMMENT '字典编码',
    `label`       VARCHAR(100)          DEFAULT NULL COMMENT '字典标签',
    `value`       VARCHAR(100)          DEFAULT NULL COMMENT '字典键值',
    `css_style`   VARCHAR(100)          DEFAULT NULL COMMENT '样式属性',
    `list_style`  VARCHAR(100)          DEFAULT NULL COMMENT '表格回显样式',
    `is_default`  BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '是否默认',
    `remark`      TEXT COMMENT '备注',
    `sort`        INT                   DEFAULT 0 COMMENT '排序依据字段',
    `deleted`     BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '删除标志',
    `update_id`   BIGINT       NOT NULL COMMENT '更新者ID',
    `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`   BIGINT       NOT NULL COMMENT '创建者ID',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '系统字典数据表';