package com.yjx.app.task;

import java.util.Date;

/**
 * <p> 
 *  订单异步处理队列
 * </p> 
 *
 * @author yejx 
 * @date 2019/9/18 16:57
 */ 
public interface IOrderAsyncService {

    /**
     * 订单延迟处理队列
     *
     * @param orderNo    订单号
     * @author yejx
     * @date 2019/9/19 11:52
     */
    void putOrder(String orderNo);

    /**
     * 订单延迟处理队列
     *
     * @param orderNo    订单号
     * @param createTime 订单创建时间
     * @author yejx
     * @date 2018/11/10 15:17
     */
    void putOrderDelay(String orderNo, Date createTime);

    /**
     * 根据订单号移除出延迟队列
     *
     * @param orderNo 订单号
     * @return boolean
     * @author yejx
     * @date 2018/11/10 15:17
     */
    boolean removeByOrderNo(String orderNo);

    /**
     * 扫描待支付延迟订单
     *
     * @author yejx
     * @date 2018/11/10 15:17
     */
    void scanDisposeOrderTask();

    /**
     * 自动处理延迟未支付订单线程
     *
     * @author yejx
     * @date 2018/11/10 15:17
     */
    void handleExpireOrderTask();
}
