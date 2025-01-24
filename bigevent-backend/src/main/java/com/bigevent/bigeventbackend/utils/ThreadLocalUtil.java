package com.bigevent.bigeventbackend.utils;

/**
 * ThreadLocal 工具类
 * 提供便捷方法存取和移除线程绑定的变量
 */
public class ThreadLocalUtil {

    // 定义一个 ThreadLocal 对象，用于存储线程本地变量
    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取当前线程绑定的变量
     *
     * @param <T> 返回值的泛型类型
     * @return 当前线程绑定的变量值
     */
    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    /**
     * 设置当前线程的变量值
     *
     * @param value 要存储的值
     */
    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    /**
     * 移除当前线程绑定的变量
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}

