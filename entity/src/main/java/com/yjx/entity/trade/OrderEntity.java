package com.yjx.entity.trade;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  交易订单表
 * </p>
 *
 * @author yejx
 * @date 2019/9/18 17:09
 */
@Data
@Builder
@EqualsAndHashCode
@TableName("t_order")
@ApiModel("交易订单表")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", position = 1)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单号", position = 2)
    private String orderNo;

    @ApiModelProperty(value = "会员ID", position = 3)
    private Long memberId;


    @ApiModelProperty(value = "虚拟商品ID", position = 6)
    private Long goodsId;

    @ApiModelProperty(value = "优惠券ID", position = 6)
    private Long couponId;

    @ApiModelProperty(value = "优惠金额(单位:分)", position = 7)
    private Integer couponAmount;

    @ApiModelProperty(value = "总金额(单位:分)", position = 7)
    private Integer totalAmount;

    @ApiModelProperty(value = "订单金额(单位:分)", position = 7)
    private Integer orderAmount;

    @ApiModelProperty(value = "订单加急金额(单位:分)", position = 7)
    private Integer urgentAmount;

    @ApiModelProperty(value = "订单类型(0:充值,1:文章付费,2:咨询付费)", position = 8)
    private Integer orderType;

    @ApiModelProperty(value = "支付方式", position = 9)
    private String payment;

    @ApiModelProperty(value = "支付时间", position = 10)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;

    @ApiModelProperty(value = "订单状态(0:待支付,1:已取消,2:已支付,3:退款中,4:已退款,5:已完成)", position = 11)
    private Integer orderStatus;

    @ApiModelProperty(value = "订单备注", position = 12)
    private String remark;

    @TableLogic
    @ApiModelProperty(value = "软删除标识(0:否,1:是)", position = 12)
    private Boolean isDelete;

    @ApiModelProperty(value = "创建时间", position = 13)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "退款时间", position = 13)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date refundTime;

    @ApiModelProperty(value = "取消时间", position = 13)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cancelTime;

    @ApiModelProperty(value = "完成时间", position = 13)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishTime;

    @ApiModelProperty(value = "修改时间", position = 21)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Version
    @JsonIgnore
    @ApiModelProperty(value = "版本", position = 22)
    private Integer version;
}
