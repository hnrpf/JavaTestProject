package cglib;

import java.lang.reflect.Method;

import jdkAOP.DogUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyCglibProxy implements MethodInterceptor{

	 // 需要被代理的对象
    private Object target;
    public void setTarget(Object target){
        this.target = target;
    }
	
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		DogUtil du = new DogUtil();
        // 执行DogUtil对象中的method1方法
        du.method1();
        // 以target为主调来执行method方法
        Object result = arg3.invoke(target, arg2);
        // 执行DogUtil对象中的method2方法
        du.method2();
        return result;
	}

}
