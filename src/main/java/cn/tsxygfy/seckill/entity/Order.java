package cn.tsxygfy.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * <p>
 * Description:
 * </p>
 *
 * @author ruby woo
 * @version v1.0.0
 * @since 2020-04-24 13:20:51
 * @see cn.tsxygfy.seckill.entity
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Order {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品 id
     */
    private Integer sid;

    /**
     * 商品名
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

}
