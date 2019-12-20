package cn.com.webtax.cache.service;

import cn.com.webtax.common.bean.DictTree;
import cn.com.webtax.common.bean.RegionTree;
import cn.com.webtax.entity.base.MenuEntity;
import cn.com.webtax.entity.cms.CmsArticleCategoryEntity;
import cn.com.webtax.entity.cms.CmsTaxTagEntity;
import cn.com.webtax.entity.cms.CmsWordEntity;
import cn.com.webtax.entity.member.MemberIntegralRuleEntity;
import cn.com.webtax.entity.push.PushTemplateEntity;
import cn.com.webtax.model.web.vo.cms.CmsDictionaryResult;
import cn.com.webtax.model.web.vo.system.OperationMenuVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Redis缓存服务接口类
 * </p>
 *
 * @author Shawn Deng
 * @date 2018/10/13 14:52
 */
public interface RedisCacheService {

    /**
     * 删除APP端TOKEN登录
     *
     * @author Shawn Deng
     * @date 2019-04-09 12:02
     */
    void deleteAppTokenDir();

    /**
     * 删除WEB端TOKEN登录
     *
     * @author Shawn Deng
     * @date 2019-04-09 12:02
     */
    void deleteWebTokenDir();

    /**
     * 加载积分规则到缓存
     *
     * @return List
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    List<MemberIntegralRuleEntity> getIntegralRulesFromCache();

    /**
     * 刷新积分规则
     *
     * @param isForced 是否强制刷新
     * @author Shawn Deng
     * @date 2018/10/13 16:36
     */
    void refreshIntegralRules(boolean isForced);

    /**
     * 加载系统菜单到缓存
     *
     * @return List
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    List<MenuEntity> getSystemMenusFromCache();

    /**
     * 刷新系统菜单
     *
     * @param isForced 是否强制刷新
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    void refreshSystemMenus(boolean isForced);

    /**
     * 加载系统字典树到缓存
     *
     * @return List
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    List<DictTree> getDictTreeFromCache();

    /**
     * 根据字典类型查询字典列表
     *
     * @param groupCodes 字典分类CODE
     * @return 字典列表
     * @author Shawn Deng
     * @date 2018/11/6 16:08
     */
    List<DictTree> findByGroupCodes(String... groupCodes);

    /**
     * 刷新系统字典列表
     *
     * @param isForced 是否强制刷新
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    void refreshDictTreeList(boolean isForced);

    /**
     * 加载区域地区列表到缓存
     *
     * @return List
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    List<RegionTree> getRegionsFromCache();

    /**
     * 刷新区域地区列表
     *
     * @param isForced 是否强制刷新
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    void refreshRegions(boolean isForced);

    /**
     * 加载资讯关键词列表到缓存
     *
     * @return List
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    List<CmsWordEntity> getCmsWordFromCache();

    /**
     * 刷新资讯关键词列表
     *
     * @param isForced 是否强制刷新
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    void refreshCmsWords(boolean isForced);

    /**
     * 加载资讯词典列表到缓存
     *
     * @return List
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    List<CmsDictionaryResult> getCmsDictionaryFromCache();

    /**
     * 刷新资讯词典列表列表
     *
     * @param isForced 是否强制刷新
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    void refreshCmsDictionaries(boolean isForced);

    /**
     * 加载资讯关键词近义词到缓存
     *
     * @return List
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    Map<String, String> getCmsWordSynonymFromCache();

    /**
     * 刷新资讯关键词近义词列表
     *
     * @param isForced 是否强制刷新
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    void refreshCmsWordSynonym(boolean isForced);

    /**
     * 加载税种标签列表到缓存
     *
     * @return List
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    List<CmsTaxTagEntity> getCmsTaxTagFromCache();

    /**
     * 刷新税种标签列表
     *
     * @param isForced 是否强制刷新
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    void refreshTaxTag(boolean isForced);

    /**
     * 加载文章分类列表到缓存
     *
     * @return List
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    List<CmsArticleCategoryEntity> getCmsCategoryFromCache();

    /**
     * 刷新文章分类列表
     *
     * @param isForced 是否强制刷新
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    void refreshCategory(boolean isForced);

    /**
     * 加载推送模板表到缓存
     *
     * @return List
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    Map<String, PushTemplateEntity> getPushTemplateFromCache();

    /**
     * 刷新推送模板列表
     *
     * @param isForced 是否强制刷新
     * @author Shawn Deng
     * @date 2018/10/13 15:43
     */
    void refreshPushTemplate(boolean isForced);

    /**
     * 获取操作名称对应菜单
     * 首次启动加载使用
     *
     * @return Map
     * @author Shawn Deng
     * @date 2019/9/11 11:50
     */
    List<OperationMenuVo> getOperationMapFromCache();

    void refreshOperation(boolean isForced);
}
