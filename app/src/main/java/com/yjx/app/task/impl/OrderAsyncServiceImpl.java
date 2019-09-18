package com.yjx.app.task.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.yjx.app.bean.OrderDelay;
import com.yjx.app.task.IOrderAsyncService;
import com.yjx.entity.trade.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.DelayQueue;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_MS_PATTERN;

/**
 * <p> 
 *  
 * </p> 
 *
 * @author yejx 
 * @date 2019/9/18 16:57
 */
@Service
@Slf4j
public class OrderAsyncServiceImpl implements IOrderAsyncService {

    private static final int PAY_TIMEOUT = 5;

    /**
     * 负责保存限时订单的队列
     */
    private static DelayQueue<OrderDelay> delayOrder = new DelayQueue<>();

    @Override
    public void putOrder(String orderNo) {
//        OrderEntity tradeOrder = iTradeOrderService.selectByOrderNo();
        OrderEntity tradeOrder = OrderEntity.builder().orderNo(orderNo).createTime(DateUtil.date()).build();
        Date expireTime = DateUtil.offsetMinute(tradeOrder.getCreateTime(), PAY_TIMEOUT);
        OrderDelay orderDelay = new OrderDelay(orderNo, expireTime.getTime());
        log.info("订单:{},创建时间：{}", orderNo, DateUtil.format(tradeOrder.getCreateTime(), NORM_DATETIME_MS_PATTERN));
        log.info("订单:{},过期时间：{}", orderNo, DateUtil.format(expireTime, NORM_DATETIME_MS_PATTERN));
        delayOrder.put(orderDelay);
        log.info("订单{}[超时时间点：{}]被推入检查队列", orderNo, DateUtil.format(expireTime, NORM_DATETIME_MS_PATTERN));
    }

    @Override
    public void putOrderDelay(String orderNo, Date expireTime) {
        OrderDelay orderDelay = new OrderDelay(orderNo, expireTime.getTime());
        log.info("订单:{},过期时间：{}", orderNo, DateUtil.format(expireTime, NORM_DATETIME_MS_PATTERN));
        //放入延迟队列
        delayOrder.put(orderDelay);
        log.info("订单{}[超时时间点：{}]被推入检查队列", orderNo, DateUtil.format(expireTime, NORM_DATETIME_MS_PATTERN));
    }

    @Override
    public boolean removeByOrderNo(String orderNo) {
        log.info("延迟队列移除订单号：{}", orderNo);
        OrderDelay orderDelay = new OrderDelay(orderNo);
        if (delayOrder.contains(orderDelay)) {
            return delayOrder.remove(orderDelay);
        }
        return false;
    }

    @Override
    public void scanDisposeOrderTask() {
        log.info("服务启动扫描处理待支付已超时订单...记录时间：{}", DateUtil.now());
        //未支付订单
//        List<OrderEntity> waitPayOrderList = iTradeOrderService.selectUnpaidOrder();
        List<OrderEntity> waitPayOrderList = new ArrayList<>(2);
        OrderEntity tradeOrder = OrderEntity.builder().orderNo("1").createTime(DateUtil.date()).build();
        OrderEntity tradeOrder1 = OrderEntity.builder().orderNo("2").createTime(DateUtil.date()).build();
        waitPayOrderList.add(tradeOrder);
        waitPayOrderList.add(tradeOrder1);
        int waitCount = 0;
        if (CollUtil.isNotEmpty(waitPayOrderList)) {
            waitCount = waitPayOrderList.size();
            for (OrderEntity orderEntity : waitPayOrderList) {
                Date expireTime = DateUtil.offsetMinute(orderEntity.getCreateTime(), PAY_TIMEOUT);
                OrderDelay orderDelay = new OrderDelay(orderEntity.getOrderNo(), expireTime.getTime());
                //放入延迟队列
                delayOrder.put(orderDelay);
            }
        }
        log.info("扫描结束...共发现待支付订单总数为：{}！已推入检查队列准备到期检查...记录时间：{}", waitCount, DateUtil.now());
    }

    @Override
    @Async("businessServiceExecutor")
    public void handleExpireOrderTask() {
        log.info("服务器启动开始，启动订单过期延迟处理线程..记录时间：{}", DateUtil.now());
        // 检查当前线程是否中断
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // take():获取队列，在必要时阻塞等待，直到该队列上有一个具有过期延迟的元素可用。
                OrderDelay delayedOrder = delayOrder.take();
                if (delayedOrder != null) {
                    log.debug("订单超时处理机制轮询，订单号：{}，处理中.....记录时间：{}", delayedOrder.getOrderNo(), DateUtil.now());
//                    OrderEntity tradeOrder = iTradeOrderService.getOne(
//                            new QueryWrapper<OrderEntity>()
//                                    .eq("order_no", delayedOrder.getOrderNo())
//                                    .eq("order_status", OrderStatus.NO_PAY.getCode()));
                    OrderEntity tradeOrder = OrderEntity.builder().orderNo("1").createTime(DateUtil.date()).build();
                    if (ObjectUtil.isNotNull(tradeOrder)) {
                        handleOrder(tradeOrder);
//                        OrderType orderType = OrderType.from(tradeOrder.getOrderType());
//                        if (orderType == OrderType.CONSULT_FEE) {
//                            log.info("轮询-查询咨询订单");
//                            TradeConsultOrderEntity tradeConsultOrder = iTradeConsultOrderService.getOne(
//                                    new QueryWrapper<TradeConsultOrderEntity>()
//                                            .eq("order_no", delayedOrder.getOrderNo())
//                                            .eq("order_status", ConsultOrderStatus.NO_PAY.getCode())
//                            );
//                            iTradeConsultOrderService.cancelConsultOrder(tradeConsultOrder, false);
//                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("处理过期订单异常!,原因:{}", e.getMessage());
            }
        }
        log.error("处理过期订单线程关闭...记录时间：{}", DateUtil.now());
    }

    /**
     * 处理订单
     *
     * @param tradeOrder 订单对象
     * @author Shawn Deng
     * @date 2018/11/9 18:49
     */
    private void handleOrder(OrderEntity tradeOrder) {
        log.debug("查询支付平台订单");
//        PayType payType = PayType.from(tradeOrder.getPayment());
//        if (payType == null) {
//            //未进行支付过，直接关闭订单
//            iTradeOrderService.closeOrder(tradeOrder);
//            this.pushOnPayTimeout(tradeOrder.getOrderNo(), tradeOrder.getMemberPhone());
//        } else if (payType == PayType.Alipay) {
//            // 支付宝订单查询
//        } else if (payType == PayType.Wxpay) {
//            //微信支付订单查询
//        }
    }

    private void pushOnPayTimeout(String orderNo, String memberMobile) {
        // 消息推送
//        eventPublisher.publishEvent(ConsultOrderEvent.pushOrderTimeOut(
//                PushConsultOrderModelFactory.createOrderTimeOutModel(orderNo, memberMobile, orderProperties.getPayTimeout())
//        ));
    }
}
