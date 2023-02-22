package com.test01.test01.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户金额流水实体
 */
@Data
@Builder
@TableName("demo_user_balances_info")
public class UserBalancesInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Long userId;
    @TableField("user_name")
    private String userName;
    @TableField("user_code")
    private String userCode;
    @TableField("play_num")
    private String playNum;
    @TableField("play_data")
    private Date playData;


}
