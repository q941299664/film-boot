-- 电影表
CREATE TABLE
    IF
    NOT EXISTS `movie_tb`
(
    `id`                BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '电影ID',
    `title`             VARCHAR(255) NOT NULL COMMENT '电影名称',
    `original_title`    VARCHAR(255) COMMENT '国外名称',
    `cast_and_crew`     VARCHAR(255) COMMENT '演职人员',
    `director`          VARCHAR(255) COMMENT '导演',
    `description`       TEXT COMMENT '电影详情',
    `duration`          INT COMMENT '电影时长',
    `genre`             VARCHAR(255) COMMENT '电影类型',
    `rating`            DECIMAL(3, 1) COMMENT '电影评分',
    `box_office`        DECIMAL(15, 2) COMMENT '票房',
    `number_of_reviews` INT COMMENT '电影参评人数',
    `release_date`      DATE COMMENT '上映时间',
    `production_region` VARCHAR(255) COMMENT '制片地区',
    `poster_url`        VARCHAR(255) COMMENT '电影海报地址',
    `status`            VARCHAR(255) COMMENT '电影状态',
    `deleted`           BOOLEAN   DEFAULT FALSE COMMENT '删除标志',
    `update_id`         INT COMMENT '更新者ID',
    `update_time`       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`         INT COMMENT '创建者ID',
    `create_time`       TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '电影表';-- 影院表
CREATE TABLE
    IF
    NOT EXISTS `cinema_tb`
(
    `id`          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '影院ID',
    `name`        VARCHAR(255) NOT NULL COMMENT '影院名称',
    `address`     VARCHAR(255) NOT NULL COMMENT '影院地址',
    `deleted`     BOOLEAN   DEFAULT FALSE COMMENT '删除标志',
    `update_id`   INT COMMENT '更新者ID',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`   INT COMMENT '创建者ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '影院表';-- 放映厅表
CREATE TABLE
    IF
    NOT EXISTS `hall_tb`
(
    `id`          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '放映厅ID',
    `name`        VARCHAR(255) NOT NULL COMMENT '放映厅名称',
    `capacity`    INT COMMENT '放映厅容量',
    `cinema_id`   INT COMMENT '所属影院ID',
    `deleted`     BOOLEAN   DEFAULT FALSE COMMENT '删除标志',
    `update_id`   INT COMMENT '更新者ID',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`   INT COMMENT '创建者ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '放映厅表';-- 场次表
CREATE TABLE
    IF
    NOT EXISTS `showtime_tb`
(
    `id`              BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '场次ID',
    `hall_id`         INT COMMENT '放映厅ID',
    `movie_id`        INT COMMENT '电影ID',
    `showtime_date`   DATETIME COMMENT '放映时间',
    `ticket_price`    DECIMAL(8, 2) COMMENT '售价',
    `remaining_seats` INT COMMENT '剩余座位数',
    `status`          VARCHAR(255) COMMENT '场次状态',
    `deleted`         BOOLEAN   DEFAULT FALSE COMMENT '删除标志',
    `update_id`       INT COMMENT '更新者ID',
    `update_time`     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`       INT COMMENT '创建者ID',
    `create_time`     TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '场次表';-- 评论表
CREATE TABLE
    IF
    NOT EXISTS `comment_tb`
(
    `id`           BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '评论ID',
    `user_id`      INT COMMENT '所属用户ID',
    `content`      TEXT COMMENT '评论内容',
    `movie_id`     INT COMMENT '所属电影ID',
    `comment_date` DATETIME COMMENT '评论时间',
    `rating`       DECIMAL(3, 1) COMMENT '评分',
    `deleted`      BOOLEAN   DEFAULT FALSE COMMENT '删除标志',
    `update_id`    INT COMMENT '更新者ID',
    `update_time`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`    INT COMMENT '创建者ID',
    `create_time`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '评论表';-- 订单表
CREATE TABLE
    IF
    NOT EXISTS `order_tb`
(
    `id`           BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    `user_id`      INT COMMENT '所属用户ID',
    `showtime_id`  INT COMMENT '所属场次ID',
    `seat_info`    VARCHAR(255) COMMENT '电影票座位信息',
    `order_status` VARCHAR(255) COMMENT '订单状态',
    `total_price`  DECIMAL(8, 2) COMMENT '订单价格',
    `payment_date` DATETIME COMMENT '订单支付时间',
    `deleted`      BOOLEAN   DEFAULT FALSE COMMENT '删除标志',
    `update_id`    INT COMMENT '更新者ID',
    `update_time`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_id`    INT COMMENT '创建者ID',
    `create_time`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT '订单表';