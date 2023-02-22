package com.test01.test01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test01.test01.dto.Goods;
import com.test01.test01.dto.UserBalancesInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 用户账单流水
 */
@Mapper
public interface UserBalancesInfoMapper extends BaseMapper<UserBalancesInfo> {
}
