package cn.com.webtax.mongo.document.modular.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * 登录日志文档
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
public class LoginLog {

    @Id
    @ApiModelProperty(value = "主键", position = 1)
    private String id;

    @ApiModelProperty(value = "日志名称", position = 2)
    private String logName;

    @ApiModelProperty(value = "请求号", position = 3)
    private String requestNo;

    @ApiModelProperty(value = "是否成功", position = 4)
    private String succeed;

    @ApiModelProperty(value = "具体消息", position = 5)
    private String message;

    @ApiModelProperty(value = "登录IP", position = 6)
    private String ipAddress;

    @ApiModelProperty(value = "创建时间", position = 7)
    private Long createTime;
}
