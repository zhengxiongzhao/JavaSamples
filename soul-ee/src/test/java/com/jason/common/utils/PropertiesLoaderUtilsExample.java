package com.jason.common.utils;

import java.util.Properties;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
* @ClassName: PropertiesLoaderUtilsExample
* @Description: TODO(属性文件操作)
* @author Jason Chiu:CIHUnKnown@Gmail.com
* @date 2013-8-16 下午06:02:29
* 
*/
//我们知道可以通过 java.util.Properties 的 load(InputStream inStream) 方法从一个输入流中加载属性资源。Spring 提供的 PropertiesLoaderUtils 允许您直接通过基于类路径的文件地址加载属性资源，请看下面的例子：
//一般情况下，应用程序的属性文件都放置在类路径下，所以 PropertiesLoaderUtils 比之于 Properties#load(InputStream inStream) 方法显然具有更强的实用性。此外，PropertiesLoaderUtils 还可以直接从 Resource 对象中加载属性资源：
//static Properties loadProperties(Resource resource)	从 Resource 中加载属性
//static void fillProperties(Properties props, Resource resource)	将 Resource 中的属性数据添加到一个已经存在的 Properties 对象中
public class PropertiesLoaderUtilsExample {
	public static void main(String[] args) throws Throwable {
		// ① jdbc.properties 是位于类路径下的文件
		Properties props = PropertiesLoaderUtils
				.loadAllProperties("jdbc.properties");
		System.out.println(props.getProperty("jdbc.driverClassName"));
	}
}