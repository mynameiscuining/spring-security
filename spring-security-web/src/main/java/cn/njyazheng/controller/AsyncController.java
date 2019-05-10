package cn.njyazheng.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * 示例演示异步处理rest服务
 */
@RestController
public class AsyncController{
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncController.class);
    
    @GetMapping("/order")
    //同步处理
    public String getOrder() throws InterruptedException {
        //随机八位数
        //String orderNumber = RandomStringUtils.randomNumeric(8);
        LOGGER.info("-----------------------------------main thread start");
        Thread.sleep(1000);
        LOGGER.info("-----------------------------------main thread return");
        return "order";
    }
    
    //异步一,异步方式吞吐量会有一个很大的提升
    //开启异步 runnable方式
    @GetMapping("/order1")
    public Callable<String> getOrder1() throws InterruptedException {
        LOGGER.info("-----------------------------------main thread start");
        Callable<String> callable = () -> {
            LOGGER.info("-----------------------------------sub thread start");
            Thread.sleep(1000);
            LOGGER.info("-----------------------------------sub thread return");
            return "order1";
        };
        LOGGER.info("-----------------------------------main thread return");
        return callable;
    }
    
    
    
}
