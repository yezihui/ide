package com.yjx.common.constants;

/** 
 * <p> 
 * 请求头变量
 * </p> 
 *
 * @author yejx 
 * @date 2019/12/5 17:07
 */ 
public interface RequestConstants {

    /**
     * token
     */
    String HEADER_TOKEN = "token";

    String HEADER_TOKEN_DESC = "访问令牌";

    /**
     * 推送设备号
     */
    String DEVICE_NUMBER = "deviceNum";

    String DEVICE_NUMBER_DESC = "推送设备号";

    /**
     * 请求平台
     */
    String HEADER_PLATFORM = "platform";

    /**
     * IOS 设备令牌
     */
    String HEADER_DEVICE_TOKEN = "deviceToken";
}
