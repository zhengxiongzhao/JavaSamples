package com.jason.se.basic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class HelloWorld {
	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("D:/MyDocument/Desktop/1.txt");
		InputStreamReader reader = new InputStreamReader(in,"UTF-8");
		BufferedReader br = new BufferedReader(reader);
		String len = "";
		while ((len = br.readLine()) != null) {
			System.out.println(len);
		}
	}
}
