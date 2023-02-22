package com.test01.test01.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.istack.internal.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单实体
 */
@Data
@Builder
@TableName("demo_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键  请求参数不填
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单编号  请求参数不填
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 商品id
     */
    @TableField("goods_id")
    private Long goodsId;
    /**
     * 商品购买数量
     */
    @TableField("buy_number_count")
    private Integer buyNumberCount;
    /**
     * 商品总价格
     */
    @TableField("pay_price")
    private Integer payPrice;
    /**
     * 商品'支付状态（0未支付, 1已支付, 2已退款, 3部分退款）',
     */
    @TableField("pay_status")
    private Integer payStatus;
    /**
     *'订单状态（0待确认, 1已确认/待支付, 2已支付/待发货, 3已发货/待收货, 4已完成, 5已取消, 6已关闭）',
     */
    @TableField("yesapi_shopxo_s_order_status")
    private Integer yesapiShopxoSOrderStatus;
}
