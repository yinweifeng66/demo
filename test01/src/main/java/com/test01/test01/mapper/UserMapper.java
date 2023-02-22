package com.test01.test01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test01.test01.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 用户业务mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 购买商品扣除用户金额
     */
    @Update("update demo_user_balances set balances=balances-#{price} where id=#{userId}")
    void updateUserBalances(@Param("userId") Long userId, @Param("price") Integer price);
    /**
     * 退款 更新用户金额
     */
    @Update("update demo_user_balances set balances=balances+#{price} where id=#{userId}")
    void updateUserBalancesUp(@Param("userId") Long userId, @Param("price") Integer price);
}
