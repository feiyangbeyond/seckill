package cn.tsxygfy.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 *
 * <p>
 * Description:
 * </p>
 *
 * @author ruby woo
 * @version v1.0.0
 * @since 2020-04-24 13:20:45
 * @see cn.tsxygfy.seckill.entity
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Stock {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 库存
     */
    private Integer count;

    /**
     * 已售
     */
    private Integer sold;

    /**
     * 乐观锁版本号
     */
    private Integer version;

}
