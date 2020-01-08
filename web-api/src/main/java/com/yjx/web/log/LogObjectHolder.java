package com.yjx.web.log;

/**
 * <p>
 * 本地线程池存储用户信息
 * </p>
 *
 * @author yejx
 * @date 2019/12/3 15:40
 */
public class LogObjectHolder {

    private static final ThreadLocal<Boolean> OPEN_UP_FLAG = new ThreadLocal<>();
    private static final ThreadLocal<Object> OBJ_HOLDER = new ThreadLocal<>();

    /**
     * 初始化
     */
    public static void init() {
        Thread t = Thread.currentThread();
        System.out.println(t.getName());
        OPEN_UP_FLAG.set(true);
    }

    /**
     * 设置属性 当open_up_flag状态为开启时
     *
     * @param object 属性入参
     * @author yejx
     * @date 2019/12/3 15:43
     */
    public static void set(Object object) {
        Boolean openUpFlag = OPEN_UP_FLAG.get();
        if (openUpFlag != null && openUpFlag.equals(true)) {
            OBJ_HOLDER.set(object);
        }
    }

    /**
     * 获取属性 当open_up_flag状态为开启时
     *
     * @return 属性值
     * @author yejx
     * @date 2019/12/3 15:42
     */
    public static Object get() {
        Boolean openUpFlag = OPEN_UP_FLAG.get();
        if (openUpFlag == null || openUpFlag.equals(false)) {
            return null;
        } else {
            return OBJ_HOLDER.get();
        }
    }

    /**
     * 移除保存的信息
     *
     * @author yejx
     * @date 2019/12/3 15:43
     */
    public static void remove() {
        OPEN_UP_FLAG.remove();
        OBJ_HOLDER.remove();
    }
}
