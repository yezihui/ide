package com.yjx.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 基础数据-专家能力维度表
 * </p>
 *
 * @author Pengap
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("wt_base_ability")
@ApiModel("基础数据-专家能力维度表")
public class BaseAbilityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", position = 1)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "能力维度代码", position = 2)
    private String abilityCode;

    @ApiModelProperty(value = "能力维度名称", position = 3)
    private String abilityName;

    @ApiModelProperty(value = "排序", position = 4)
    private Integer sequence;

    @ApiModelProperty(value = "备注", position = 5)
    private String remark;

    @ApiModelProperty(value = "创建时间", position = 6)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "修改时间", position = 7)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
