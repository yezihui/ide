package com.yjx.web.bean;

import lombok.Data;

import java.util.Set;

/**
 * <p>
 * 当前用户的登录信息
 * </p>
 *
 * @author Shawn Deng
 * @date 2019-04-09 22:11
 */
@Data
public class WebLoginUser {

    /**
     * 账号id
     */
    private Long userUniqueId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 创建用户ID
     */
    private Long createUserId;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 角色ID集合
     */
    private Set<String> roleIds;

    /**
     * 权限集合
     */
//    private List<UserOperator> operators;

    /**
     * 资源操作权限URL集合
     */
    private Set<String> resourceUrls;

    /**
     * 菜单集合
     */
//    private List<MenuEntity> menus;

    /**
     * 菜单集合
     */
//    private List<UserMenuTree> menuTrees;
}
