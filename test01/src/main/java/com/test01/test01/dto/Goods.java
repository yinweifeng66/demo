package com.test01.test01.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 商品实体
 */
@Data
@TableName("demo_goods")
public class Goods {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableField("id")
    private Long id;
    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 商品描述
     */
    @TableField("goods_desc")
    private String goodsDesc;
    /**
     * 商品价格  单位为分
     */
    @TableField("goods_price")
    private Integer goodsPrice;
    /**
     * 商品市场价 单位为分
     */
    @TableField("goods_maket_price")
    private Integer goodsMaketPrice;
    /**
     * 商品库存
     */
    @TableField("goods_stock")
    private Integer goodsStock;
    /**
     * 商品图片
     */
    @TableField("goods_img")
    private String goodsImg;
    /**
     * 商品上架时间
     */
    @TableField("goods_sell_time")
    private Date goodsSellTime;
    /**
     * 商品状态 商品状态，0待审核，1正常售卖，2已下架，3已售罄
     */
    @TableField("goods_status")
    private Integer goodsStatus;

}
