package com.yjx.cache.constants;

/**
 * <p>
 * Redis目录常量
 * </p>
 *
 * @author yejx
 * @date 2019-12-13 11:17
 */
public interface RedisDirConstants {

    /**
     * APP用户登录的TOKEN目录
     */
    String APP_LOGIN_USER_TOKEN_PREFIX = "security:app:token:";

    /**
     * 后台用户登录的TOKEN
     */
    String WEB_LOGIN_USER_TOKEN_PREFIX = "security:web:token:";

    /**
     * SMS 注册验证码目录
     */
    String SMS_REGISTER_DIR = "sms:register:";

    /**
     * SMS 登录验证码目录
     */
    String SMS_LOGIN_DIR = "sms:login:";

    /**
     * SMS 修改登录密码目录
     */
    String SMS_UPDATE_PASSWORD_DIR = "sms:updatepass:";

    /**
     * EMAIL 修改登录密码目录（找回密码、修改密码、设置密码）
     */
    String EMAIL_UPDATE_PASSWORD_DIR = "email:updatepass:";

    /**
     * EMAIL 绑定邮箱
     */
    String EMAIL_BOUND_DIR = "email:bound:";

    /**
     * 积分操作键
     */
    String INTEGRAL_RULE_KEY = "integral:rule";

    /**
     * 字典树目录
     */
    String DICTIONARY_TREE_KEY = "dictionary:tree";

    /**
     * 系统菜单
     */
    String SYSTEM_MENU_LIST_KEY = "system:menu";

    /**
     *
     */
    String SYSTEM_OPERATION_MAP_KEY = "system:operation";

    /**
     * 全国区域地区列表
     */
    String REGION_LIST_KEY = "region:tree";

    /**
     * 关键词汇
     */
    String CMS_WORD_LIST = "cms:word:list";

    /**
     * 资讯词典
     */
    String CMS_DICTIONARY_LIST = "cms:dictionary:list";

    /**
     * 关键词近义词
     */
    String CMS_WORD_SYNONYMS = "cms:wordsynonyms";

    /**
     * 税种标签
     */
    String CMS_TAX_TAG = "cms:taxtag:list";

    /**
     * 文章分类
     */
    String CMS_CATEGORY = "cms:category:list";

    /**
     * 推送模板
     */
    String PUSH_TEMPLATE = "push:template:list";

    /**
     * app端登录会员缓存
     * <p>“{}”使用模板的占位符</p>
     */
    String APP_USER_INFO = "app:user:info:{}";

    /**
     * app端登录会员缓存
     * <p>“{}”使用模板的占位符</p>
     */
    String APP_USER_INVITE_INFO = "app:user:invite:info:{}";

    /**
     * 问税订单页面缓存
     */
    String QA_ORDER_PAGE = "app:query_cache:qa:order:page";

    /**
     * job端缓存八爪鱼token配置
     */
    String JOB_BAZHUAYU_TOKEN = "job:bzy:token";

    /**
     * 订单消息总线缓存
     */
    String ORDER_BUS_INFO = "order:bus:info:";

}