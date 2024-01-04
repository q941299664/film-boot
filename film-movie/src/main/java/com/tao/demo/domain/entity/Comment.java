package com.tao.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tao.demo.core.domain.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("film_comment_tb")
public class Comment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 所属用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 所属电影ID
     */
    @TableField("movie_id")
    private Integer movieId;

    /**
     * 评论时间
     */
    @TableField("comment_date")
    private LocalDateTime commentDate;

    /**
     * 评分
     */
    @TableField("rating")
    private BigDecimal rating;

    public static final String USER_ID = "user_id";

    public static final String CONTENT = "content";

    public static final String MOVIE_ID = "movie_id";

    public static final String COMMENT_DATE = "comment_date";

    public static final String RATING = "rating";
}
