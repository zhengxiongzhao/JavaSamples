package com.jason.designpatterns.factory;



/**
* @ClassName: AbstracFactory
* @Description: TODO(抽象工厂模式（Abstract Factory）)
* @author Jason Chiu :  CIHUnKnown@Gmail.com  
* @date 2013-8-26 下午09:36:30
* <pre>加抽象，创建多工厂类
* 解决工厂违背闭包的原则</pre>
*/
public class AbstracFactory
{
	public static void main(String[] args)
	{
		Provider pd=new SmsSendFactory();
		pd.production().send();
		
		pd=new MailSendFactory();
		pd.production().send();
		
	}
}
interface Provider{
	public Sender production();
}
class SmsSendFactory implements Provider{
	public Sender production()
	{
		return new SmsSender();
	}
}
class MailSendFactory implements Provider{
	@Override
	public Sender production()
	{
		return new MailSender();
	}
}