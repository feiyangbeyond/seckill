package cn.tsxygfy.seckill.controller;

import cn.tsxygfy.seckill.service.OrderService;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author ruby woo
 * @version v1.0.0
 * @see cn.tsxygfy.seckill.controller
 * @since 2020-04-24 13:52:25
 */
@RestController
@RequestMapping("seckill")
@Slf4j
public class SeckillController {

    @Qualifier("orderServiceImpl")
    @Autowired
    private OrderService orderService;


    @Qualifier("orderServiceImpl1")
    @Autowired
    private OrderService orderService1;

    /**
     * 创建令牌桶实例
     */
    private RateLimiter rateLimiter = RateLimiter.create(50);

    @GetMapping("kill0")
    public String kill0(Integer id) {
        log.info("秒杀商品的id ----->" + id);
        try {
            int orderId = orderService.kill(id);
            return "抢购成功，订单id为 " + orderId;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 悲观锁
     *
     * @param id
     * @return
     */
    @GetMapping("kill1")
    public String kill1(Integer id) {
        log.info("秒杀商品的id ----->" + id);
        try {
            synchronized (this) {
                int orderId = orderService.kill(id);
                return "抢购成功，订单id为 " + orderId;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 乐观锁
     *
     * @param id
     * @return
     */
    @GetMapping("kill2")
    public String kill2(Integer id) {
        log.info("秒杀商品的id ----->" + id);
        try {
            int orderId = orderService1.kill(id);
            return "抢购成功，订单id为 " + orderId;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 乐观锁 + 令牌桶限流
     *
     * @param id
     * @return
     */
    @GetMapping("kill3")
    public String kill3(Integer id) {
        log.info("秒杀商品的id ----->" + id);
        if (!rateLimiter.tryAcquire(2, TimeUnit.SECONDS)){
            return "抢购失败，当前秒杀活动过于火爆，请重试！";
        }

        try {
            int orderId = orderService1.kill(id);
            return "抢购成功，订单id为 " + orderId;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
