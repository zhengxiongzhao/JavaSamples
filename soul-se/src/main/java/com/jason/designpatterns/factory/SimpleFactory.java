package com.jason.designpatterns.factory;

  
/**
* @ClassName: SimpleFactory
* @Description: TODO(普通工厂模式)
* @author Jason Chiu :  CIHUnKnown@Gmail.com  
* @date 2013-8-26 下午08:29:20
* <b>工厂方法就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建</b>
* <p>工厂方法模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进行修改，这违背了闭包原则.<p>
*/
public class SimpleFactory {
	public static void main(String[] args) {
		Factory f = new Factory();
		Sender sd = f.Provider("sms");
		sd.send();
		sd = f.Provider("mail");
		sd.send();
	}
}

interface Sender {
	void send();
}

class MailSender implements Sender {
	@Override
	public void send() {
		System.out.println("Mail Send!");
	}
}

class SmsSender implements Sender {
	@Override
	public void send() {
		System.out.println("Sms Send!");
	}
}
class Factory{
	public Sender Provider(String target){
		if ("mail".equalsIgnoreCase(target)) {
			return new MailSender();
		}else {
			return new SmsSender();
		}
	}
}