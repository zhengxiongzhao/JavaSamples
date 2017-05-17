package com.jason.common.utils;

import java.util.Locale;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;

/**
 * @ClassName: LocaleResourceTest
 * @Description: TODO(本地化文件资源)
 * @author Jason Chiu:CIHUnKnown@Gmail.com
 * @date 2013-8-16 下午05:52:20
 * 
 */
// 本地化文件资源是一组通过本地化标识名进行特殊命名的文件，Spring 提供的 LocalizedResourceHelper
// 允许通过文件资源基名和本地化实体获取匹配的本地化文件资源并以 Resource 对象返回。假设在类路径的 i18n 目录下，拥有一组基名为 message
// 的本地化文件资源，我们通过以下实例演示获取对应中国大陆和美国的本地化文件资源：
public class LocaleResourceTest {
	public static void main(String[] args) {
		LocalizedResourceHelper lrHalper = new LocalizedResourceHelper();
		// ① 获取对应美国的本地化文件资源
		Resource msg_us = lrHalper.findLocalizedResource("i18n/message",
				".properties", Locale.US);
		// ② 获取对应中国大陆的本地化文件资源
		Resource msg_cn = lrHalper.findLocalizedResource("i18n/message",
				".properties", Locale.CHINA);
		System.out.println("fileName(us):" + msg_us.getFilename());
		System.out.println("fileName(cn):" + msg_cn.getFilename());
	}
}