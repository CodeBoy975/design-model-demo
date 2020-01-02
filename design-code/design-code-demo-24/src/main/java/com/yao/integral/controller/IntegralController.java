package com.yao.integral.controller;

import com.yao.integral.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *  积分系统控制层
 * @author pengjie_yao
 * @date 2020/1/2 16:45
 */
@RestController
public class IntegralController {


    @Autowired
    private IntegralService integralService;

    /**
     * 消费积分，这代码应该是在营销系统，营销系统来调用积分系统进行减积分
     * @param userId
     * @param credit
     */
    @GetMapping("intgral/{userId}/{credit}")
    public String consume(@PathVariable("userId") String userId, @PathVariable("credit")String credit){
        return integralService.consume(userId,Integer.parseInt(credit));
    }
}
