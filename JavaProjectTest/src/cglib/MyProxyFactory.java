package cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

public class MyProxyFactory {

	public  Object getProxy(Object target)
	{
		MyCglibProxy proxy = new MyCglibProxy();
		proxy.setTarget(target);
		Enhancer enhancer = new Enhancer();
	    enhancer.setSuperclass(target.getClass());
	    enhancer.setCallback(proxy);
	    return enhancer.create();
		
	}
}
