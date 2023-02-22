package com.test01.test01.service;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.test01.test01.dto.Order;
import com.test01.test01.dto.User;
import com.test01.test01.dto.UserBalancesInfo;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用户业务
 */
public interface UserService {
    /**
     * 查询用户钱包余额
     */
    User queryPriceByUserId(Long userId);
    /**
     *购买商品
     */
    Boolean playOrder(Order order) throws Exception;
    /**
     * 退单
     */
    Boolean outOrder(Long id) throws Exception;
    /**
     * 查询用户钱包流水
     */
    List<UserBalancesInfo> queryBalancesByUserId(Long userId);

    Boolean exPlay(ReentrantLock reentrantLock, IdentifierGenerator identifierGenerator, Order order) throws Exception;

    Boolean exOutOrder(Long id, ReentrantLock reentrantLock) throws Exception;
}
