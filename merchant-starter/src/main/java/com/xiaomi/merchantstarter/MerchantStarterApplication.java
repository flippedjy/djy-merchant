package com.xiaomi.merchantstarter;

import com.xiaomi.merchant.infastructure.SerialNumGenerator;
import com.xiaomi.merchant.infastructure.dao.GoodsDao;
import com.xiaomi.merchant.infastructure.dao.SeqDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages={"com.xiaomi"})
@MapperScan(basePackages = {"com.xiaomi.merchant.infastructure.dao"})
public class MerchantStarterApplication {



    public static void main(String[] args) {

        System.out.println(MerchantStarterApplication.class.getResource("/"));
        SpringApplication.run(MerchantStarterApplication.class, args);
    }

}
