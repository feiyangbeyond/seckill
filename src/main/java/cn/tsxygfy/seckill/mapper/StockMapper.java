package cn.tsxygfy.seckill.mapper;

import cn.tsxygfy.seckill.entity.Stock;

/**
 *
 * <p>
 * Description:
 * </p>
 *
 * @author ruby woo
 * @version v1.0.0
 * @since 2020-04-24 13:43:38
 * @see cn.tsxygfy.seckill.mapper
 *
 */
public interface StockMapper {

    //校验库存
    Stock checkStock(Integer id);

    //更新库存
    void updateSold(Stock stock);


    //更新库存1   乐观锁
    int updateSold1(Stock stock);
}
