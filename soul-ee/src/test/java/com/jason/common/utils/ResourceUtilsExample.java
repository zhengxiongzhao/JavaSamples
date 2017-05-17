package com.jason.common.utils;

import java.io.File;

import org.springframework.util.ResourceUtils;

/**
* @ClassName: ResourceUtilsExample
* @Description: TODO(地址加载文件资源)
* @author Jason Chiu:CIHUnKnown@Gmail.com
* @date 2013-8-16 下午05:51:06
* 
*/

//对于位于远程服务器（Web 服务器或 FTP 服务器）的文件资源，您则可以方便地通过 UrlResource 进行访问。
//为了方便访问不同类型的资源，您必须使用相应的 Resource 实现类，是否可以在不显式使用 Resource 实现类的情况下，仅根据带特殊前缀的资源地址直接加载文件资源呢？ Spring 提供了一个 ResourceUtils 工具类，它支持“classpath:”和“file:”的地址前缀，它能够从指定的地址加载文件资源，请看下面的例子：
//虽然 JDK 的 java.util.ResourceBundle 类也可以通过相似的方式获取本地化文件资源，但是其返回的是 ResourceBundle 类型的对象。如果您决定统一使用 Spring 的 Resource 接表征文件资源，那么 LocalizedResourceHelper 就是获取文件资源的非常适合的帮助类了。
public class ResourceUtilsExample {
	public static void main(String[] args) throws Throwable {
		File clsFile = ResourceUtils.getFile("classpath:conf/file1.txt");
		System.out.println(clsFile.isFile());

		String httpFilePath = "file:D:/masterSpring/chapter23/src/conf/file1.txt";
		File httpFile = ResourceUtils.getFile(httpFilePath);
		System.out.println(httpFile.isFile());
	}
}