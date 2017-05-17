package org.soul.okhttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {
	private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	// private final static SimpleDateFormat dateFormater = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// private final static SimpleDateFormat dateFormater2 = new
	// SimpleDateFormat("yyyy-MM-dd");

	public static final String NUMBER_FORMAT = "^[0-9]*$";

	public static final String NUMBER = "^[1-9]\\d*$";

	public static final String TWO_DECIMALS_FORMAT = "^[0-9]+(.[0-9]{2})?$"; // 两位小数

	public static final String EMAIL_FORMAT = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	/**
	 * 处理数据库返回的null数据，将其转化为无
	 * 
	 * @param text
	 * @return
	 */
	public static String formatText(String text) {
		text = ((text == null || text == "null") ? "" : text);
		return text.trim();
	}

	/**
	 * 处理数据库返回的null数据，将其转化为传入的值
	 * 
	 * @param text
	 * @return
	 */
	public static Object formatObject(Object object, String value) {
		if (object == null || object.toString().equals("null")) {
			object = value;
		}
		return object;
	}

	public static String trim(String value) {
		return trim(value, null);
	}
	public static String trim(String value, String defaultValue) {
		if (value == null || "".equals(value.trim())) {
			return defaultValue!=null?defaultValue : "";
		}
		return value.trim();
	}
	
	/**
	 * "Wed, 06 Aug 2014 00:00:00 GMT"转换为"yyyy-MM-dd"
	 * 
	 * @param sdate
	 * @return
	 */
	public static String GMTtoSting(String sdate) {
		try {
			Date date = new SimpleDateFormat("E,dd MMM yyyy HH:mm:ss z", Locale.ENGLISH).parse(sdate);
			SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
			String rdate = myformat.format(date);
			return rdate;
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * "Tue Mar 31 10:00:00 CST 2015"转换为"yyyy-MM-dd"
	 * 
	 * @param sdate
	 * @return
	 */
	public static String CSTtoSting(String sdate) {
		try {
			Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(sdate);
			SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
			String rdate = myformat.format(date);
			return rdate;
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String MTYMChrStr(String inMonth){
		StringBuffer sbf = new StringBuffer();
		try {
			if (inMonth.indexOf("个")!=-1) {
				inMonth = inMonth.substring(0, inMonth.indexOf("个"));
			}
			int month = Integer.valueOf(inMonth);
			if (month >11) {
				sbf.append(month/12 + "年");
				sbf.append(month%12 + "月");
			}else {
				sbf.append(month + "月");	
			}
		} catch (Exception e) {
			sbf.append(inMonth);
			e.printStackTrace();
		}
		return sbf.toString();
	}

	public static Calendar toCalendar(String sdate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(sdate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			if (sdate.length() > 12) {
				return dateFormater.get().parse(sdate);
			} else {
				return dateFormater2.get().parse(sdate);
			}
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static String formatDate(String sdate) {
		try {
			if (sdate.length() > 12) {
				return dateFormater.get().format(toDate(sdate));
			}
			return dateFormater2.get().format(toDate(sdate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static String formatDate(Date sdate, String pattern) {
		try {
			return new SimpleDateFormat(pattern).format(sdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormater2.get().format(time);
		}
		return ftime;
	}

	/**
	 * 判断给定字符串时间是否为今日
	 * 
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if (time != null) {
			String nowDate = dateFormater2.get().format(today);
			String timeDate = dateFormater2.get().format(time);
			if (nowDate.equals(timeDate)) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * 返回long类型的今天的日期
	 * 
	 * @return
	 */
	public static long getToday() {
		Calendar cal = Calendar.getInstance();
		String curDate = dateFormater2.get().format(cal.getTime());
		curDate = curDate.replace("-", "");
		return Long.parseLong(curDate);
	}

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * @param idCard
	 * @return
	 */
	public static boolean isValidIdCard(String idCard) {
		Pattern pattern;
		Matcher matcher;
		if (idCard.length() == 15) {
			pattern = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
		} else if (idCard.length() == 18) {
			pattern = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(X|[0-9])$");
		} else {
			return false;
		}
		matcher = pattern.matcher(idCard);
		return matcher.matches();
	}

	/**
	 * 将一个InputStream流转换成字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String toConvertString(InputStream is) {
		StringBuffer res = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader read = new BufferedReader(isr);
		try {
			String line;
			line = read.readLine();
			while (line != null) {
				res.append(line);
				line = read.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr) {
					isr.close();
					isr.close();
				}
				if (null != read) {
					read.close();
					read = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}
		return res.toString();
	}

	/**
	 * 判断字符串是否为空白，常见几种情况如下：
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (null == str || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取随机的UUID字符串：(已将其中的"-"替换掉)
	 * <p>
	 * 另：还可使用RandomGUID <br>
	 * 地址：http://www.javaexchange.com/aboutRandomGUID.html
	 * 
	 * @return
	 */
	public static String getRandomUUIDStr() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String getVeriCode() {
		return "CODE" + getRandomUUIDStr();
	}

	/**
	 * 判断字符串是否不为空，常见几种情况如下：
	 * 
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty("")        = false
	 * StringUtils.isNotEmpty(" ")       = true
	 * StringUtils.isNotEmpty("bob")     = true
	 * StringUtils.isNotEmpty("  bob  ") = true
	 * </pre>
	 */
	public static boolean isNotEmpty(String str) {
		return !StringUtils.isEmpty(str);
	}

	/**
	 * 描述: 判断字符串是否是整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (isNotEmpty(str) && str.matches(NUMBER_FORMAT)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumberGreterThan0(String str) {
		if (isNotEmpty(str) && str.matches(NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * 描述: 判断是否精确到两位小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isTwoDecimals(String str) {
		if (isNotEmpty(str) && str.matches(TWO_DECIMALS_FORMAT)) {
			return true;
		}
		return false;
	}

	/**
	 * 描述: 判断是否符合邮箱规则
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmailFormat(String str) {
		if (isNotEmpty(str) && str.matches(EMAIL_FORMAT)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断某个字符串是否存在于数组中
	 * 
	 * @param stringArray
	 *            原数组
	 * @param source
	 *            查找的字符串
	 * @return 是否找到
	 */
	public static boolean contains(String[] stringArray, String source) {
		List<String> tempList = Arrays.asList(stringArray);
		if (tempList.contains(source)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断某个字符串存在于数组中位置
	 * 
	 * @param stringArray
	 *            原数组
	 * @param source
	 *            查找的字符串
	 * @return int
	 */
	public static int arrayIndex(String[] stringArray, String source) {
		List<String> tempList = Arrays.asList(stringArray);
		return tempList.indexOf(source);
	}

	public static String toHexStringByBytes(byte[] bytesData, int offset, int length) {
		String string = "";
		for (int i = 0; i < length; i++) {
			string += Integer.toHexString((bytesData[i + offset] & 0x000000ff) | 0xffffff00).substring(6);
		}
		return string;
	}

	public static byte[] toByteArrayByHexString(String hexString) {
		hexString = hexString.toLowerCase();
		final byte[] byteArray = new byte[hexString.length() / 2];
		int k = 0;
		for (int i = 0; i < byteArray.length; i++) {// 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
			byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
			byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
			byteArray[i] = (byte) (high << 4 | low);
			k += 2;
		}
		return byteArray;
	}

	public static String getRandomString(int length) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String getRandomInt(int length) {
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
}
