package MethodInvoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainClass {
	public static void main(String args[]){
		MethodInvoke instance = new MethodInvoke();
		Class cls = MethodInvoke.class;
		try {
			Method method = cls.getDeclaredMethod("testMethod", Integer.class);
			//反射私有方法的重要一步
			method.setAccessible(true);
			method.invoke(instance, new Object[]{null});
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
