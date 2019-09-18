package com.yjx.app.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/** 
 * <p> 
 * 订单支付超时相关初始化操作 
 * </p> 
 *
 * @author yejx 
 * @date 2019/9/18 16:57
 */ 
@Slf4j
public class ChainOnOrderPayTaskRunner implements InitializingBean {

    private IOrderAsyncService iOrderAsyncService;

    public ChainOnOrderPayTaskRunner(IOrderAsyncService iOrderAsyncService) {
        this.iOrderAsyncService = iOrderAsyncService;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.isTrue(iOrderAsyncService != null, "订单任务不能为空");
        iOrderAsyncService.scanDisposeOrderTask();
        iOrderAsyncService.handleExpireOrderTask();
    }
}
