package cn.tsxygfy.seckill.service;

import cn.tsxygfy.seckill.entity.Order;
import cn.tsxygfy.seckill.entity.Stock;
import cn.tsxygfy.seckill.mapper.OrderMapper;
import cn.tsxygfy.seckill.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * Description: 乐观锁
 * </p>
 *
 * @author ruby woo
 * @version v1.0.0
 * @see cn.tsxygfy.seckill.service
 * @since 2020-04-24 14:57:37
 */
@Service("orderServiceImpl1")
@Transactional
public class OrderServiceImpl1 implements OrderService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int kill(Integer id) {
        Stock stock = checkStock(id);
        updateSold(stock);
        return createOrder(stock);
    }


    // ================ private methods =================

    /**
     * 校验库存
     *
     * @param id
     * @return
     */
    private Stock checkStock(Integer id) {
        Stock stock = stockMapper.checkStock(id);
        if (stock.getCount() <= stock.getSold()) {
            throw new RuntimeException(stock.getName() + "已售罄！");
        }
        return stock;
    }

    /**
     * 扣除库存
     *
     * @param stock
     */
    private void updateSold(Stock stock) {
        int updateRows = stockMapper.updateSold1(stock);
        if (updateRows == 0) {
            throw new RuntimeException("抢购" + stock.getName() + "失败！请重试！");
        }
    }

    /**
     * 创建订单
     *
     * @param stock
     * @return
     */
    protected Integer createOrder(Stock stock) {
        Order order = new Order();
        order.setName(stock.getName()).setSid(stock.getId()).setCreateTime(new Date());
        orderMapper.createOrder(order);
        return order.getId();
    }

}
