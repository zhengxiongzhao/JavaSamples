package com.jason.common.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

/**
* @ClassName: EncodedResourceExample
* @Description: TODO(特殊编码的资源)
* @author Jason Chiu:CIHUnKnown@Gmail.com
* @date 2013-8-16 下午06:04:29
* 
*/
//当您使用 Resource 实现类加载文件资源时，它默认采用操作系统的编码格式。如果文件资源采用了特殊的编码格式（如 UTF-8），则在读取资源内容时必须事先通过 EncodedResource 指定编码格式，否则将会产生中文乱码的问题。
//EncodedResource 拥有一个 getResource() 方法获取 Resource，但该方法返回的是通过构造函数传入的原 Resource 对象，所以必须通过 EncodedResource#getReader() 获取应用编码后的 Reader 对象，然后再通过该 Reader 读取文件的内容。
public class EncodedResourceExample {
	public static void main(String[] args) throws Throwable {
		Resource res = new ClassPathResource("conf/file1.txt");
		// ① 指定文件资源对应的编码格式（UTF-8）
		EncodedResource encRes = new EncodedResource(res, "UTF-8");
		// ② 这样才能正确读取文件的内容，而不会出现乱码
		String content = FileCopyUtils.copyToString(encRes.getReader());
		System.out.println(content);
	}
}