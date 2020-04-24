package cn.tsxygfy.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * <p>
 * Description:
 * </p>
 *
 * @author ruby woo
 * @version v1.0.0
 * @since 2020-04-24 13:43:52
 * @see cn.tsxygfy.seckill
 *
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.tsxygfy.seckill.mapper")
public class SeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }

}
