package com.jason.common.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
* @ClassName: FileSourceExample
* @Description: TODO(文件资源操作)
* @author Jason Chiu:CIHUnKnown@Gmail.com
* @date 2013-8-16 下午05:47:34
* @see ResourceUtilsExample
*/
//文件资源的操作是应用程序中常见的功能，如当上传一个文件后将其保存在特定目录下，从指定地址加载一个配置文件等等。我们一般使用 JDK 的 I/O 处理类完成这些操作，但对于一般的应用程序来说，JDK 的这些操作类所提供的方法过于底层，直接使用它们进行文件操作不但程序编写复杂而且容易产生错误。相比于 JDK 的 File，Spring 的 Resource 接口（资源概念的描述接口）抽象层面更高且涵盖面更广，Spring 提供了许多方便易用的资源操作工具类，它们大大降低资源操作的复杂度，同时具有更强的普适性。这些工具类不依赖于 Spring 容器，这意味着您可以在程序中象一般普通类一样使用它们。
//加载文件资源
//Spring 定义了一个 org.springframework.core.io.Resource 接口，Resource 接口是为了统一各种类型不同的资源而定义的，Spring 提供了若干 Resource 接口的实现类，这些实现类可以轻松地加载不同类型的底层资源，并提供了获取文件名、URL 地址以及资源内容的操作方法。
//访问文件资源
//假设有一个文件地位于 Web 应用的类路径下，您可以通过以下方式对这个文件资源进行访问：
//通过 FileSystemResource 以文件系统绝对路径的方式进行访问；
//通过 ClassPathResource 以类路径的方式进行访问；
//通过 ServletContextResource 以相对于 Web 应用根目录的方式进行访问。
//相比于通过 JDK 的 File 类访问文件资源的方式，Spring 的 Resource 实现类无疑提供了更加灵活的操作方式，您可以根据情况选择适合的 Resource 实现类访问资源。下面，我们分别通过 FileSystemResource 和 ClassPathResource 访问同一个文件资源：
//在获取资源后，您就可以通过 Resource 接口定义的多个方法访问文件的数据和其它的信息：如您可以通过 getFileName() 获取文件名，通过 getFile() 获取资源对应的 File 对象，通过 getInputStream() 直接获取文件的输入流。此外，您还可以通过 createRelative(String relativePath) 在资源相对地址上创建新的资源。
//在 Web 应用中，您还可以通过 ServletContextResource 以相对于 Web 应用根目录的方式访问文件资源，如下所示：
// <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
// <jsp:directive.page import="
//    org.springframework.web.context.support.ServletContextResource"/> 
// <jsp:directive.page import="org.springframework.core.io.Resource"/> 
// <% 
//    // ① 注意文件资源地址以相对于 Web 应用根路径的方式表示
//    Resource res3 = new ServletContextResource(application, 
//        "/WEB-INF/classes/conf/file1.txt"); 
//    out.print(res3.getFilename()); 
// %> 
//
@SuppressWarnings("all")
public class FileSourceExample {
	public static void main(String[] args) {
		try {
			String filePath = "D:/masterSpring/chapter23/webapp/WEB-INF/classes/conf/file1.txt";
			// ① 使用系统文件路径方式加载文件
			Resource res1 = new FileSystemResource(filePath);
			// ② 使用类路径方式加载文件
			Resource res2 = new ClassPathResource("conf/file1.txt");
			InputStream ins1 = res1.getInputStream();
			InputStream ins2 = res2.getInputStream();
			System.out.println("res1:" + res1.getFilename());
			System.out.println("res2:" + res2.getFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}