package jdkAOP;

import java.lang.reflect.Proxy;

public class MyProxyFactory{
    // 为指定的target生成动态代理对象
    public static Object getProxy(Object target) throws Exception{
        MyInvokationHandler handler = new MyInvokationHandler();
        // 设置target对象
        handler.setTarget(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader()
            , target.getClass().getInterfaces(), handler);
    }
}
