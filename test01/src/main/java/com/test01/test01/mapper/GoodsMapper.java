package com.test01.test01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test01.test01.dto.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 商品
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 减库存
     */
    @Update("update demo_goods set goods_stock=goods_stock-#{num} where id=#{id}")
    void updateGoodsNum(@Param("id") Long id,@Param("num") Integer num);
}
