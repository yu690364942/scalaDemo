package com.example.scalaDemo.basic.adapter;

import java.lang.reflect.Method;

/**
 * <p>TiTle: ReflectAdapter</p>
 * <p>Description: ReflectAdapter</p>
 * <p>Company: www.nbcb.cn</p>
 *
 * @author yhq
 * @version 1.0
 * @since 2021/12/20
 */
public class ReflectAdapter {
    public static <T> Method getMethod(Class<T> t, String name, Class[] args) throws NoSuchMethodException {
        return t.getMethod(name, args);
    }
}
