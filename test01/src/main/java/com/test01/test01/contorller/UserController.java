package com.test01.test01.contorller;

import com.test01.test01.dto.Order;
import com.test01.test01.dto.User;
import com.test01.test01.dto.UserBalancesInfo;
import com.test01.test01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 查询用户钱余额
     */
    @PostMapping("/queryPriceByUser")
    public User queryPriceByUser(@RequestParam("userId") Long  userId){
        if(ObjectUtils.isEmpty(userId)){
            System.out.println("用户不能为空！本地方使用公共的响应体Result，通过配置全局异常监听进行对应Result返回");
        }
        User user = userService.queryPriceByUserId(userId);
        return user;
    }
    /**
     * 查询用户钱包流水
     *
     * @param userId
     */
    @PostMapping("/queryBalancesByUserId")
    public List<UserBalancesInfo> queryBalancesByUserId(@RequestParam("userId") Long userId) {
        if(ObjectUtils.isEmpty(userId)){
            System.out.println("用户不能为空！本地方使用公共的响应体Result，通过配置全局异常监听进行对应Result返回");
        }
        List<UserBalancesInfo> users = userService.queryBalancesByUserId(userId);
        return users;
    }
    /**
     * 购买商品
     */
    @PostMapping("/playOrderController")
    public Boolean playOrderController(@RequestBody Order order) throws Exception {
        if(ObjectUtils.isEmpty(order)){
            System.out.println("参数不能为空！本地方使用公共的响应体Result，通过配置全局异常监听进行对应Result返回");
        }
        System.out.println("第一次上传");
        Boolean aBoolean = userService.playOrder(order);
        System.out.println("合并测试");
        return aBoolean;
    }
    /**
     * 退单
     */
    @PostMapping("/outOrderController")
    public Boolean outOrderController(@RequestParam("orderId") Long orderId) throws Exception {
        if(ObjectUtils.isEmpty(orderId)){
            System.out.println("订单id不能为空！本地方使用公共的响应体Result，通过配置全局异常监听进行对应Result返回");
        }
        Boolean aBoolean = userService.outOrder(orderId);
        return aBoolean;
    }
}
