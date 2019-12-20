package cn.com.webtax.web.bean;

import cn.com.webtax.entity.base.MenuEntity;
import cn.com.webtax.model.web.vo.system.UserOperator;
import cn.com.webtax.web.api.AbstractLoginUser;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 当前用户的登录信息
 * </p>
 *
 * @author Shawn Deng
 * @date 2019-04-09 22:11
 */
public class WebLoginUser implements AbstractLoginUser {

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
    private List<UserOperator> operators;

    /**
     * 资源操作权限URL集合
     */
    private Set<String> resourceUrls;

    /**
     * 菜单集合
     */
    private List<MenuEntity> menus;

    /**
     * 菜单集合
     */
    private List<UserMenuTree> menuTrees;

    public void setUserUniqueId(Long userUniqueId) {
        this.userUniqueId = userUniqueId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRoleIds(Set<String> roleIds) {
        this.roleIds = roleIds;
    }

    public void setOperators(List<UserOperator> operators) {
        this.operators = operators;
    }

    public void setResourceUrls(Set<String> resourceUrls) {
        this.resourceUrls = resourceUrls;
    }

    public void setMenus(List<MenuEntity> menus) {
        this.menus = menus;
    }

    public void setMenuTrees(List<UserMenuTree> menuTrees) {
        this.menuTrees = menuTrees;
    }

    @Override
    public String getAccessToken() {
        return this.accessToken;
    }

    @Override
    public Long getUserUniqueId() {
        return userUniqueId;
    }

    @Override
    public Set<String> getRoleIds() {
        return roleIds;
    }

    @Override
    public Set<String> getResourceUrls() {
        return resourceUrls;
    }

    @Override
    public List<UserOperator> getOperators() {
        return operators;
    }

    @Override
    public List<MenuEntity> getMenus() {
        return menus;
    }

    @Override
    public List<UserMenuTree> getMenuTrees() {
        return menuTrees;
    }
}
