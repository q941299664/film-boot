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
 * 场次表
 * </p>
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("film_showtime_tb")
public class Showtime extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 放映厅ID
     */
    @TableField("hall_id")
    private Integer hallId;

    /**
     * 电影ID
     */
    @TableField("movie_id")
    private Integer movieId;

    /**
     * 放映时间
     */
    @TableField("showtime_date")
    private LocalDateTime showtimeDate;

    /**
     * 售价
     */
    @TableField("ticket_price")
    private BigDecimal ticketPrice;

    /**
     * 剩余座位数
     */
    @TableField("remaining_seats")
    private Integer remainingSeats;

    /**
     * 场次状态
     */
    @TableField("status")
    private String status;

    public static final String HALL_ID = "hall_id";

    public static final String MOVIE_ID = "movie_id";

    public static final String SHOWTIME_DATE = "showtime_date";

    public static final String TICKET_PRICE = "ticket_price";

    public static final String REMAINING_SEATS = "remaining_seats";

    public static final String STATUS = "status";
}
