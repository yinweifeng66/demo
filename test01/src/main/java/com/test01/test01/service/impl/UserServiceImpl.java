package com.test01.test01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.test01.test01.dto.Goods;
import com.test01.test01.dto.Order;
import com.test01.test01.dto.User;
import com.test01.test01.dto.UserBalancesInfo;
import com.test01.test01.mapper.GoodsMapper;
import com.test01.test01.mapper.OrderMapper;
import com.test01.test01.mapper.UserBalancesInfoMapper;
import com.test01.test01.mapper.UserMapper;
import com.test01.test01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用户业务mapper
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserBalancesInfoMapper userBalancesInfoMapper;
    @Autowired
    private UserService userService;
    /**
     * 查询用户钱包余额
     *
     * @param userId
     */
    @Override
    public User queryPriceByUserId(Long userId) {
        QueryWrapper<User> q=new QueryWrapper();
        q.eq("user_id",userId);
        User user = userMapper.selectOne(q);
        return user;
    }

    /**
     * 购买商品
     *
     * @param order
     */
    @Override
    public Boolean playOrder(Order order) throws Exception {
        IdentifierGenerator identifierGenerator=new DefaultIdentifierGenerator(1,1);
        ReentrantLock reentrantLock=new ReentrantLock();
        Boolean aBoolean = userService.exPlay(reentrantLock, identifierGenerator, order);
        return aBoolean;
    }
    /**
     * 购买商品操作 处理多线程事物
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean exPlay(ReentrantLock reentrantLock,IdentifierGenerator identifierGenerator,Order order) throws Exception {
        try {
            reentrantLock.lock();
            Number number = identifierGenerator.nextId(identifierGenerator);
            Long l = number.longValue();
            order.setOrderNo(l.toString());
            //查询库存
            QueryWrapper<Goods> q=new QueryWrapper<>();
            q.eq("id",order.getGoodsId());
            q.select("goods_stock");
            Goods i = goodsMapper.selectOne(q);
            Integer goods_stock = i.getGoodsStock();
            //判断库存
            if(goods_stock>=order.getBuyNumberCount()){
                //库存够  减库存
                goodsMapper.updateGoodsNum(order.getGoodsId(),order.getBuyNumberCount());
                //下单
                orderMapper.insert(order);
                //支付
                //判断金额是否充足
                QueryWrapper<User> qw=new QueryWrapper<>();
                qw.eq("user_id",order.getUserId());
                User user = userMapper.selectOne(qw);
                Integer balances = user.getBalances();
                if(balances>=order.getPayPrice()*order.getBuyNumberCount()){
                    userMapper.updateUserBalances(order.getUserId(),order.getPayPrice()*order.getBuyNumberCount());
                    //修改订单状态为待发货 支付状态为已支付
                    order.setYesapiShopxoSOrderStatus(2);
                    order.setPayStatus(1);
                    orderMapper.updateById(order);
                    //通过
                    //添加流水记录
                    UserBalancesInfo build = UserBalancesInfo.builder().playNum("-" + order.getPayPrice())
                            .userId(user.getUserId())
                            .userName(user.getUserName())
                            .userCode(user.getUseNrCode())
                            .playData(new Date()).build();
                    userBalancesInfoMapper.insert(build);
                    return true;
                }else{
                    System.out.println("金额不足购买失败！");
                    throw new Exception();
                }
            }else{
                //库存不够 下单失败
                System.out.println("库存不足！");
                return false;
            }
        }catch (Exception e){
            System.out.println("购买失败！");
            throw new Exception();
        }finally {
            reentrantLock.unlock();
        }
    }
    /**
     * 退单
     */
    @Override
    public Boolean outOrder(Long id) throws Exception {
        ReentrantLock reentrantLock=new ReentrantLock();
        Boolean aBoolean = userService.exOutOrder(id, reentrantLock);
        return aBoolean;
    }
    /**
     * 退单 处理多线程事物
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean exOutOrder(Long id,ReentrantLock reentrantLock) throws Exception {
        try {
            reentrantLock.lock();
            //查询订单中的用户id
            QueryWrapper<Order> e = new QueryWrapper<>();
            e.eq("id", id);
            Order order = orderMapper.selectOne(e);
            if(order.getPayStatus()==1&&order.getYesapiShopxoSOrderStatus()!=6){
                Long user_id = order.getUserId();
                Boolean aBoolean = (orderMapper.updateOrder(id)>=1);
                if(aBoolean){
                    Integer pay_price = order.getPayPrice();
                    //退单成功  返还金额
                    userMapper.updateUserBalancesUp(user_id,pay_price);
                    //查询商品信息 返回库存
                    Goods g = goodsMapper.selectOne(new QueryWrapper<Goods>().eq("id", order.getGoodsId()));
                    int i = g.getGoodsStock() + order.getBuyNumberCount();
                    g.setGoodsStock(i);
                    goodsMapper.updateById(g);
                    //通過userId查詢用戶信息
                    QueryWrapper<User> where=new QueryWrapper<>();
                    where.eq("user_id", order.getUserId());
                    User user = userMapper.selectOne(where);
                    //添加流水记录
                    UserBalancesInfo build = UserBalancesInfo.builder().playNum("+" + order.getPayPrice())
                            .userId(order.getUserId())
                            .userName(user.getUserName())
                            .userCode(user.getUseNrCode())
                            .playData(new Date()).build();
                    userBalancesInfoMapper.insert(build);
                }
                System.out.println("该订单退款成功！");
                return true;
            }else{
                System.out.println("该订单已完成退款！");
                return false;
            }
        }catch (Exception e){
            System.out.println("请勿重复退款！");
            throw new Exception();
        }finally {
            reentrantLock.unlock();
        }
    }
    /**
     * 查询用户钱包流水
     *
     * @param userId
     */
    @Override
    public List<UserBalancesInfo> queryBalancesByUserId(Long userId) {
        QueryWrapper<UserBalancesInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<UserBalancesInfo> users = userBalancesInfoMapper.selectList(queryWrapper);
        return users;
    }
}
