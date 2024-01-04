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
 * 订单表
 * </p>
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("film_order_tb")
public class Order extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 所属用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 所属场次ID
     */
    @TableField("showtime_id")
    private Integer showtimeId;

    /**
     * 电影票座位信息
     */
    @TableField("seat_info")
    private String seatInfo;

    /**
     * 订单状态
     */
    @TableField("order_status")
    private String orderStatus;

    /**
     * 订单价格
     */
    @TableField("total_price")
    private BigDecimal totalPrice;

    /**
     * 订单支付时间
     */
    @TableField("payment_date")
    private LocalDateTime paymentDate;

    public static final String USER_ID = "user_id";

    public static final String SHOWTIME_ID = "showtime_id";

    public static final String SEAT_INFO = "seat_info";

    public static final String ORDER_STATUS = "order_status";

    public static final String TOTAL_PRICE = "total_price";

    public static final String PAYMENT_DATE = "payment_date";
}
