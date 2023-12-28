package com.tao.demo.constant;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import lombok.ToString;

/**
 * @projectName: film
 * @package: cn.fdzc.edu.film.constant
 * @className: MySqlConditionConstant
 * @author: DemoTao
 * @description: TODO
 * @date: 2023/12/19 23:43
 * @version: 1.0
 */
@ToString
public class MySqlConditionConstant extends SqlCondition {
  public static final String LT = "%s < #{%s}";
  public static final String LE = "%s <= #{%s}";
  public static final String GT = "%s > #{%s}";
  public static final String GE = "%s >= #{%s}";
  public static final String NE = "%s <> #{%s}";
}
