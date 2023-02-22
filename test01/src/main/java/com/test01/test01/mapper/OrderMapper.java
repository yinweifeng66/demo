package com.test01.test01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test01.test01.dto.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * 订单
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 更新订单--退单
     */
    @Update("update demo_order set pay_status=2,yesapi_shopxo_s_order_status=5 where id=#{id}")
    int updateOrder(Long id);
}
