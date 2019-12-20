package cn.com.webtax.mongo.document.modular.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * 操作日志文档
 * </p>
 *
 * @author Shawn Deng
 * @date 2019-05-07 19:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document
public class OperationLog {

    @Id
    @ApiModelProperty(value = "主键", position = 1)
    private String id;

    @ApiModelProperty(value = "日志类型", position = 2)
    private String logType;

    @ApiModelProperty(value = "日志名称", position = 2)
    private String logName;

    @ApiModelProperty(value = "请求号", position = 3)
    private String requestNo;

    @ApiModelProperty(value = "操作名称", position = 3)
    private String operationName;

    @ApiModelProperty(value = "操作类", position = 4)
    private String className;

    @ApiModelProperty(value = "操作方法", position = 5)
    private String method;

    @ApiModelProperty(value = "请求地址", position = 6)
    private String requestUrl;

    @ApiModelProperty(value = "请求参数", position = 7)
    private String requestParams;

    @ApiModelProperty(value = "是否成功", position = 7)
    private String succeed;

    @ApiModelProperty(value = "备注", position = 8)
    private String message;

    @ApiModelProperty(value = "操作用户", position = 9)
    private String username;

    @ApiModelProperty(value = "操作IP", position = 10)
    private String operationIp;

    @ApiModelProperty(value = "创建时间", position = 11)
    private Long createTime;
}
