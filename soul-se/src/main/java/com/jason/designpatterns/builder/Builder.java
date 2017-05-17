package com.jason.designpatterns.builder;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class Builder
{
	@Test
	public void test(){
		Builder builder=new Builder();
		builder.produceMailSender(5);
		builder.produceSmsSender(5);
		List<Sender> li = builder.getList();
		for (Sender sender : li) {
			if (sender instanceof MailSender) {
				System.out.println(MailSender.class);
			}else {
				System.out.println(SmsSender.class);
			}
			
		}
	}
	
	private List<Sender> list=new ArrayList<Sender>();
	public List<Sender> getList()
	{
		return list;
	}
	public void produceMailSender(int count){
		for (int i = 0; i < count; i++) {
			list.add(new MailSender());
		}
	}
	public void produceSmsSender(int count){
		for (int i = 0; i < count; i++) {
			list.add(new SmsSender());
		}
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