package com.jason.designpatterns.adapter;

public class ObjectAdapter
{
	public static void main(String[] args)
	{
	}
}
class SmsSenderObj implements Sender{
	
	private MailSender mailSender;
	
	public SmsSenderObj(MailSender mailSender) {
		this.mailSender=mailSender;
	}

	@Override
	public void send()
	{
		System.out.println(this.getClass()+" send!");
	}

	@Override
	public void collect()
	{
		System.out.println(this.getClass()+"collect!");
	}
	
	
}