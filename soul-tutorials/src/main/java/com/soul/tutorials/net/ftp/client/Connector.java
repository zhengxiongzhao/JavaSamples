package com.soul.tutorials.net.ftp.client;

import java.net.InetSocketAddress;

import sun.net.ftp.FtpClient;

/**
 * 连接FTP服务器
 */
public class Connector extends Thread {

	MainFrame frame = null;
	// 需要IP地址、用户名、密码
	String ip = "";
	String username = "";
	String password = "";

	public Connector(MainFrame frame, String ip, String username,
			String password) {
		// 初始化信息
		this.frame = frame;
		this.ip = ip;
		this.username = username;
		this.password = password;
	}

	public void run() { 
		try {
			frame.consoleTextArea.append("connecting the server " + ip
					+ ",wait for a moment..\n");
			frame.ftpClient = FtpClient.create();
			// 连接服务器
			frame.ftpClient.connect(new InetSocketAddress(ip, 21));
			frame.consoleTextArea.append("connect server " + ip
					+ " succeed,please continue!\n");
			// 用用户名和密码登陆
			frame.ftpClient.login(username, password.toCharArray());
			frame.currentDirTextField.setText("/");
			frame.setTableData();
			frame.serverIPTextField.setText(ip);
			frame.userNameTextField.setText(username);
			frame.passwordTextField.setText(password);
		} catch (Exception e) {
			frame.consoleTextArea.append("can not connect the server " + ip + "!\n");
		}
	}

}