package com.yjx.app.bean;

import lombok.Data;

import java.util.Objects;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 延迟队列对象 实现Delayed接口 无界阻塞队列
 *
 * </p>
 *
 * @author yejx
 * @date 2019/9/18 16:28
 */
@Data
public class OrderDelay implements Delayed {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 到期时间，单位为毫秒，实际计算为纳秒
     */
    private long activeTime;

    public OrderDelay(String orderNo) {
        this.orderNo = orderNo;
    }

    public OrderDelay(String orderNo, long activeTime) {
        this.orderNo = orderNo;
        this.activeTime = activeTime;
    }

    /**
     * 计算当前时间到执行时间之间还有多长时间
     *
     * @param unit TimeUnit单位
     * @return long
     * @author yejx
     * @date 2019/9/18 16:28
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.activeTime - System.currentTimeMillis(), unit);
    }

    /**
     * 需要重写用于排序
     *
     * @param delayed 比较对象
     * @return 0相等 -1当前对象小于比较对象 1当前对象大于比较对象
     * @author yejx
     * @date 2019/9/19 11:48
     */
    @Override
    public int compareTo(Delayed delayed) {
        long excessTime = getDelay(TimeUnit.NANOSECONDS) - delayed.getDelay(TimeUnit.NANOSECONDS);
        return excessTime == 0 ? 0 : ((excessTime < 0 ? -1 : 1));
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        OrderDelay delay = (OrderDelay) obj;
        if (null == this.getOrderNo() || null == delay.getOrderNo()) {
            return false;
        }
        return this.orderNo.equals(delay.getOrderNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNo, activeTime);
    }
}
