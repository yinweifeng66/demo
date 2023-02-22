package com.test01.test01.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.core.SerializableString;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户钱包实体
 */
@Data
@TableName("demo_user_balances")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Long userId;
    @TableField("balances")
    private Integer balances;
    @TableField("pay_password")
    private String payPassword;
    @TableField("user_name")
    private String userName;
    @TableField("user_code")
    private String useNrCode;
    @TableField("user_password")
    private String userPassword;



}
