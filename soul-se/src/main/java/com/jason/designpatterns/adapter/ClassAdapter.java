package com.jason.designpatterns.adapter;

/**
* @ClassName: ClassAdapter
* @Description: TODO(适配器模式(类))
* @author Jason Chiu :  CIHUnKnown@Gmail.com  
* @date 2013-8-26 下午09:19:53
* 将某个类的接口转换成客户端期望的另一个接口表示，目的是消除由于接口不匹配所造成的类的兼容性问题。
*/
public class ClassAdapter
{
	public static void main(String[] args)
	{
		Sender sd=new SmsSender();
		sd.send();
		sd.collect();
	}
}
class MailSender {
	public void send()
	{
		System.out.println("mailSend");
	}
}
interface Sender{
	void send();
	void collect();
}
class SmsSender extends MailSender implements Sender{

	@Override
	public void collect()
	{
		System.out.println("SmsSend");
	}
	
}