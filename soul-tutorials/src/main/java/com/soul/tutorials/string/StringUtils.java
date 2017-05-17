package com.soul.tutorials.string;

public class StringUtils {

	/** 是否为空
	 * @param str
	 * @return null == true, "" = true, " " == true,  "1" == false
	 */
	public static boolean isEmpty(String str){
		return str==null || str.trim().isEmpty();
	}
}
