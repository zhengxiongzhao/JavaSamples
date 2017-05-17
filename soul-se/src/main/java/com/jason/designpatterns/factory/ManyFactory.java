package com.jason.designpatterns.factory;

import org.junit.Test;

/**
* @ClassName: ManyFactory
* @Description: TODO(多个工厂方法模式)
* @author Jason Chiu :  CIHUnKnown@Gmail.com  
* @date 2013-8-26 下午09:22:25
*/
public class ManyFactory
{
	public static void main(String[] args)
	{
		FactoryMany f = new FactoryMany();
		Sender sd = f.mailSend();
		sd.send();
		sd=f.smsSend();
		sd.send();
	}
	@Test
	public void staticFactory(){
		Sender sd=FactoryStatic.smsSend();
		sd.send();
		sd=FactoryStatic.mailSend();
		sd.send();
	}
}
class FactoryMany{
	public Sender mailSend(){
		return new MailSender();
	}
	public Sender smsSend(){
		return new SmsSender();
	}
}
/**
* @ClassName: FactoryStatic
* @Description: TODO(静态工厂模式)
* @author Jason Chiu :  CIHUnKnown@Gmail.com  
* @date 2013-8-26 下午09:32:41
*/
class FactoryStatic{
	public static Sender mailSend(){
		return new MailSender();
	}
	public  static Sender smsSend(){
		return new SmsSender();
	}
}