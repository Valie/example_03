package com.ribbon_service.control;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO
 * @Author Takioo
 * @Date 2018/11/5 16:11
 **/

@RestController
public class HelloControl {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errorAction")
    @RequestMapping("/hi")
    public void hiAction() {

        restTemplate.getForEntity("http://aService/hello",
                        String.class).getBody();

    }

    //注意：返回值类型须和原调用函数相同
    public void errorAction() {
        System.out.println("sorry, an error happened");
    }

}
