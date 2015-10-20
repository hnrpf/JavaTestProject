package jdkAOP;


/**
 * 如果既要多个类调用同一个方法，又无须在程序中以硬编码的方式直接调用，就可以通过动态代理来达到这种效果。
 * JDK 动态代理只能为接口创建动态代理。
 * @author weifu
 *
 */
public class MainTest {

	public static void main(String[] args) throws Exception{
        Dog target = new GunDog();
        Dog dog = (Dog)MyProxyFactory.getProxy(target);
        dog.info();
        System.out.println("---------------------------------------------------");
        dog.run();
    }

}
