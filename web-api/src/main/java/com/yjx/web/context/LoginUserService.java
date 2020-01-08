package com.yjx.web.context;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.yjx.common.exception.ServiceException;
import com.yjx.web.bean.WebLoginUser;
import com.yjx.web.util.JwtTokenUtil;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.yjx.cache.constants.RedisDirConstants.WEB_LOGIN_USER_TOKEN_PREFIX;
import static com.yjx.common.exception.enums.CoreExceptionEnum.TOKEN_ILLEGAL;

/**
 * <p>
 * 用户登录服务
 * </p>
 *
 * @author Shawn Deng
 * @date 2019-06-25 17:13
 */
@Service
public class LoginUserService {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RedisTemplate<String, WebLoginUser> redisTemplate;

    /**
     * 生成TOKEN
     *
     * @param id 业务系统登录用户的ID,唯一标识
     * @return token
     * @author yejx
     * @date 2019/12/5 17:59
     */
    public String generateToken(Long id) {
        return jwtTokenUtil.createJwtById(id.toString(), null);
    }

    /**
     * 缓存
     *
     * @param webLoginUser 当前登录用户信息
     * @author Shawn Deng
     * @date 2018/10/24 18:35
     */
    public void put(WebLoginUser webLoginUser) {
        //获取JWT ID唯一标识
        String jwtId = jwtTokenUtil.getJwtIdFromToken(webLoginUser.getAccessToken());
        //获取TOKEN 过期时间
        Date expDate = jwtTokenUtil.getExpirationDateFromToken(webLoginUser.getAccessToken());
        long expireSeconds = DateUtil.between(new Date(), expDate, DateUnit.SECOND);

        BoundValueOperations<String, WebLoginUser> opts = redisTemplate.boundValueOps(WEB_LOGIN_USER_TOKEN_PREFIX + jwtId);
        opts.set(webLoginUser, expireSeconds, TimeUnit.SECONDS);
    }

    /**
     * 移除令牌
     *
     * @param token 令牌
     * @return true 成功 | false 失败
     * @author Shawn Deng
     * @date 2018/10/24 18:33
     */
    public boolean removeToken(String token) {
        //获取JWT ID唯一标识
        String jwtId = jwtTokenUtil.getJwtIdFromToken(token);
        return Optional.ofNullable(redisTemplate.delete(WEB_LOGIN_USER_TOKEN_PREFIX + jwtId)).orElse(false);
    }

    public void removeByLoginUserId(Long id) {
        redisTemplate.delete(WEB_LOGIN_USER_TOKEN_PREFIX + id.toString());
    }


    /**
     * 根据TOKEN获取当前登录用户，找不到则抛出异常
     *
     * @param token 令牌
     * @return AbstractLoginUser
     */
    public WebLoginUser getLoginUserByToken(String token) {
        //先校验jwt是否正确
        if (!jwtTokenUtil.checkToken(token)) {
            throw new ServiceException(TOKEN_ILLEGAL);
        }
        //校验是否过期
        if (jwtTokenUtil.isTokenExpired(token)) {
            throw new ServiceException(TOKEN_ILLEGAL);
        }
        //jwt 唯一标识
        String jwtId = jwtTokenUtil.getJwtIdFromToken(token);
        //校验缓存是否有token,并且校验是否与当前token同步
        BoundValueOperations<String, WebLoginUser> opts = redisTemplate.boundValueOps(WEB_LOGIN_USER_TOKEN_PREFIX + jwtId);
        WebLoginUser webLoginUser = opts.get();
        if (webLoginUser != null) {
            if (!webLoginUser.getAccessToken().equals(token)) {
                throw new ServiceException(TOKEN_ILLEGAL);
            }
            return webLoginUser;
        } else {
            throw new ServiceException(TOKEN_ILLEGAL);
        }
    }

    /**
     * 检查token是否合法
     *
     * @param token 令牌
     * @return true | false
     */
    boolean checkToken(String token) {
        //先校验jwt是否正确
        if (!jwtTokenUtil.checkToken(token)) {
            return false;
        }
        //校验是否过期
        if (jwtTokenUtil.isTokenExpired(token)) {
            throw new ServiceException(TOKEN_ILLEGAL);
        }
        //jwt 唯一标识
        String jwtId = jwtTokenUtil.getJwtIdFromToken(token);
        //校验缓存是否有token
        BoundValueOperations<String, WebLoginUser> opts = redisTemplate.boundValueOps(WEB_LOGIN_USER_TOKEN_PREFIX + jwtId);
        WebLoginUser loginUser = opts.get();
        if (loginUser != null) {
            return loginUser.getAccessToken().equals(token);
        }
        return false;
    }
}
