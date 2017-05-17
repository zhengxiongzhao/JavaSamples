package com.jason.designpatterns.singleton;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
* @ClassName: SingletonAm
* @Description: TODO(单例模式)
* @author Jason Chiu : CIHUnKnown@Gmail.com  
* @date 2013-8-27 上午09:19:49
*/
public class SingletonAm
{
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		SingletonStaticInnerClass.getInstance();
		SingletonEnum instance = SingletonEnum.INSTANCE;
		SingletonDualVerifyLock.getInstance();
	}
}
/**
* @ClassName: SingletonEnum
* @Description: TODO(枚举)
*/
enum SingletonEnum {  
    INSTANCE;
    private SingletonEnum() {
    	System.out.println("initial Enumeration");
    }
}  
/**
* @ClassName: SingletonStaticInnerClass
* @Description: TODO(静态内部类)
*/
class SingletonStaticInnerClass{
	private SingletonStaticInnerClass() {System.out.println("initial class!");}
	/**
	* @ClassName: SingletonHolder
	* @Description: TODO(类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例  
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载  )
	*/
	private static class SingletonHolder{
		private static final SingletonStaticInnerClass INSTANCE=new SingletonStaticInnerClass();
	}
	public static final SingletonStaticInnerClass getInstance(){
		return SingletonHolder.INSTANCE;
	}
	private static Class getClass(String classname)  throws ClassNotFoundException {     
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null){
            classLoader = SingletonStaticInnerClass.class.getClassLoader();
        }
        return (classLoader.loadClass(classname));
    }
	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */  
    public Object readResolve() {  
        return getInstance();  
    }  
}
/**
* @ClassName: SingletonDualVerifyLock
* @Description: TODO(双重校验锁)
*/
class SingletonDualVerifyLock{
	private volatile static SingletonDualVerifyLock singleton=null;
	private SingletonDualVerifyLock(){}
	public static SingletonDualVerifyLock getInstance(){
		if (singleton==null) {
			synchronized (SingletonDualVerifyLock.class) {
				if (singleton==null) {
					singleton=new SingletonDualVerifyLock();
				}
			}
		}
		return singleton;
	}
	
}
