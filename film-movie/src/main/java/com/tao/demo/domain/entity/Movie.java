package com.tao.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tao.demo.core.domain.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 电影表
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("film_movie_tb")
public class Movie extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 电影名称
     */
    @TableField(value = "title", condition = SqlCondition.LIKE)
    private String title;

    /**
     * 国外名称
     */
    @TableField(value = "original_title", condition = SqlCondition.LIKE)
    private String originalTitle;

    /**
     * 演职人员
     */
    @TableField(value = "cast_and_crew", condition = SqlCondition.LIKE)
    private String castAndCrew;

    /**
     * 导演
     */
    @TableField(value = "director", condition = SqlCondition.LIKE)
    private String director;

    /**
     * 电影详情
     */
    @TableField("description")
    private String description;

    /**
     * 电影时长
     */
    @TableField("duration")
    private Integer duration;

    /**
     * 电影类型
     */
    @TableField(value = "genre", condition = SqlCondition.LIKE)
    private String genre;

    /**
     * 电影评分
     */
    @TableField("rating")
    private BigDecimal rating;

    /**
     * 票房
     */
    @TableField("box_office")
    private BigDecimal boxOffice;

    /**
     * 电影参评人数
     */
    @TableField("number_of_reviews")
    private Integer numberOfReviews;

    /**
     * 上映时间
     */
    @TableField("release_date")
    private LocalDate releaseDate;

    /**
     * 制片地区
     */
    @TableField("production_region")
    private String productionRegion;

    /**
     * 电影海报地址
     */
    @TableField("poster_url")
    private String posterUrl;

    /**
     * 电影状态
     */
    @TableField("status")
    private String status;

    public static final String TITLE = "title";

    public static final String ORIGINAL_TITLE = "original_title";

    public static final String CAST_AND_CREW = "cast_and_crew";

    public static final String DIRECTOR = "director";

    public static final String DESCRIPTION = "description";

    public static final String DURATION = "duration";

    public static final String GENRE = "genre";

    public static final String RATING = "rating";

    public static final String BOX_OFFICE = "box_office";

    public static final String NUMBER_OF_REVIEWS = "number_of_reviews";

    public static final String RELEASE_DATE = "release_date";

    public static final String PRODUCTION_REGION = "production_region";

    public static final String POSTER_URL = "poster_url";

    public static final String STATUS = "status";
}
