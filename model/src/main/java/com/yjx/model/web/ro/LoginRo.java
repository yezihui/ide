package cn.com.webtax.model.web.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 请求登录参数
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/10/31 13:51
 */
@Data
@ApiModel("请求登录参数")
public class LoginRo {

    @NotNull
    @NotBlank
    @ApiModelProperty(value = "用户名", required = true, position = 1)
    private String username;

    @NotNull
    @NotBlank
    @ApiModelProperty(value = "登录密码", required = true, position = 2)
    private String password;
}
