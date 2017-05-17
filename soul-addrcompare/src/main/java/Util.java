/**通用函数类*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Util {
	
	// 全角转半角
	public static String Q2B(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 65281 && c < 65373) {
				sb.append((char)(c - 65248));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	// 大写字母转小写
	public static String B2S(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				sb.append((char)(c + 32));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	// 判断prefix_str是否为original_str的前缀
	public static boolean isPrefix(String original_str, String prefix_str) {
		if (original_str == null || prefix_str == null) {
			return false;
		}
		return original_str.indexOf(prefix_str) == 0 ? true : false;
	}
	
	// 判断prefix_str是否为original_str的前缀，如果prefix_str与original_str相同，返回false
	public static boolean isPrefixAndNotEqual(String original_str, String prefix_str) {
		if (original_str == null || prefix_str == null) {
			return false;
		}
		if (original_str.length() == prefix_str.length()) {
			return false;
		}
		return original_str.indexOf(prefix_str) == 0 ? true : false;
	}
	
	// 如果prefix_str是original_str的前缀，则返回original_str去掉prefix_str前缀，否则返回original_str
	public static String getDelPrefixString(String original_str, String prefix_str) {
		if (original_str == null || prefix_str == null) {
			return original_str;
		}
		int index = original_str.indexOf(prefix_str);
		int length = prefix_str.length();
		
		if (index == 0) {
			return original_str.substring(length);
		}
		return original_str;
	}
	
	/* 查找original_str是否以endStrArr数组中的某一个字符串作为结尾，
	 * 如果找到，则返回将original_str去除这个结尾后的字符串。
	 * 注：优先去除endStrArr中较长的字符串*/
	public static String getDelEndStrArrString(String original_str, String[] endStrArr) {
		int 	maxLen = 0;
		String 	maxStr = "";
		
		for (int i = 0; i < endStrArr.length; i++) {
			if (original_str.endsWith(endStrArr[i]) && endStrArr[i].length() > maxLen) {
				maxLen = endStrArr[i].length();
				maxStr = endStrArr[i];
			}
		}
		
		if (maxLen > 0) {
			original_str = original_str.substring(0, original_str.lastIndexOf(maxStr)); // 注意是lastIndexOf，不是indexOf
		}
		return original_str;
	}
	
	/* 去除original_str中的重复字符，重复字符取自dupStrArr，注：dupStrArr是1位长度的字符串数组
	 * 例如：original_str = "AA+#BB++CC"，dupStrArr = {"+", "#"}
	 * 处理后是："AA+BB+CC" */
	public static String getDelDupString(String original_str, String[] dupStrArr) {
		String 	ret_str = "";
		int 	flag 	= 0; // 标志是否为重复的分隔符
		
		for (int i = 0; i < original_str.length(); i++) {
			int j = 0;
			for (j = 0; j < dupStrArr.length; j++) {
				if (dupStrArr[j].equals(original_str.substring(i, i + 1))) {
					break;
				}
			}
			if (j < dupStrArr.length) {
				if (flag == 0) { // 重复的分隔符忽略
					ret_str += original_str.substring(i, i + 1);
				}
				flag = 1;
			} else {
				flag = 0;
				ret_str += original_str.substring(i, i + 1);
			}
		}
		
		return ret_str;
	}
	

	
	// 将original_str中含有数组strArr中所有的字符串都删除，如果只有最后一个数组，则不删除。
	public static String getDelStringArray(String original_str, String[] strArr) {
		String str = original_str;
		
		if (original_str == null || strArr == null) {
			return str;
		}
		
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i] != null) {
				if (str.indexOf(strArr[i]) != -1) {
					if (!str.equals(strArr[i])) {
						str = str.replaceAll(strArr[i], "");
					}
				}
			}
		}
		
		return str;
	}
	
	// 分隔字符串
	public static ArrayList<String> getSplitStringArr(String str, String token) {
		ArrayList<String> return_arr = new ArrayList<String>();
		
		/*
		if (str != null && token != null) {
			String[] t_arr = str.split(token);
			for (int i = 0; i < t_arr.length; i++) {
				if (!(t_arr[i].trim().equals(""))) {
					return_arr.add(t_arr[i].trim());
				}
			}
		}*/
		
		int idx = 0;
		int last_idx = 0;
		
		while ((idx = str.indexOf(token, last_idx)) != -1) {
			if (last_idx < idx) {
				return_arr.add(str.substring(last_idx, idx));
			}
			last_idx = idx + 1;
			
			if (last_idx >= str.length()) {
				break;
			}
		}
		
		if (last_idx < str.length()) {
			return_arr.add(str.substring(last_idx, str.length()));
		}
		
		return return_arr;
	}
	
	// 过滤字符串original_str中的filter_str
	public static String filterString(String original_str, String filter_str) {
		String 	ret_str = original_str;
		String	tmp_str	= null;
		int 	idx		= 0;
		int		len		= filter_str.length();
		
		while ((idx = ret_str.indexOf(filter_str)) != -1) {
			tmp_str = ret_str.substring(0, idx) + ret_str.substring(idx + len, ret_str.length());
			ret_str = tmp_str;
		}
		
		return ret_str;
	}
	
	public static int dic_max = 0;
	
	// 将(key, value)插入到HashMap中，如果key已经存在，则将(key + "1", value)插入到HashMap中，以次类推
	public static void putIntoUniqueHashMap(HashMap<String, String> map, String key, String value) {
		String 					new_key 	= key;
		String					get_value 	= null;
		int 					i 			= 1;
		
		while ((get_value = map.get(new_key)) != null && !get_value.equals(value)) {
			new_key = key + i;
			i++;
			dic_max++;
		}
		
		map.put(new_key, value);
	}
	
	// 将key对应的所有value组成ArrayList返回
	public static ArrayList<String> getFromUniqueHashMap(HashMap<String, String> map, String key) {
		ArrayList<String> 	ret_arr = new ArrayList<String>();
		String 				new_key = key;
		String 				value 	= null;
		int 				i 		= 1;
		
		while ((value = map.get(new_key)) != null) {
			ret_arr.add(value);
			new_key = key + i;
			i++;
		}
		
		return ret_arr;
	}
	
	// 判断一个字符是否为数字
	public static boolean isNumChar(char c) {
		if (c < '0' || c > '9') {
			return false;
		}
		return true;
	}
	
	// 字符转化为数字
	public static int getNumFromNumChar(char c) {
		if (c < '0' || c > '9') {
			return -1;
		}
		return c - 48;
	}
	
	// 判断字符串是否是中文数字，注：只对千级别及以下的数进行判断
	public static boolean isChineseNumber(String str) {
		boolean num_flag = false;
		
		for (int i = 0; i < str.length(); i++) {
			num_flag = false;
			switch (str.charAt(i)) {
			case '0':
			case '０':
			case '○':
			case '零':
			case '1':
			case '１':
			case '一':
			case '壹':
			case '2':
			case '２':
			case '二':
			case '两':
			case '贰':
			case '3':
			case '３':
			case '三':
			case '叁':
			case '4':
			case '４':
			case '四':
			case '肆':
			case '5':
			case '５':
			case '五':
			case '伍':
			case '6':
			case '６':
			case '六':
			case '陆':
			case '7':
			case '７':
			case '七':
			case '柒':
			case '8':
			case '８':
			case '八':
			case '捌':
			case '9':
			case '９':
			case '九':
			case '玖':
			case '千':
			case '仟':
			case '百':
			case '佰':
			case '十':
			case '拾':
				num_flag = true;
				break;
			}
			if (!num_flag) {
				return false;
			}
		}
		
		return true;
	}
	
	// 过滤出字符串中的数字
	public static int filterNumber(String str) {
		String num_str = "";
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				num_str += str.substring(i, i + 1);
			}
		}
		
		return Integer.parseInt(num_str);
	}
	
	// 过滤出字符串中非数字的字符
	public static String filterNotNumber(String str) {
		String not_num_str = "";
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				not_num_str += str.substring(i, i + 1);
			}
		}
		
		return not_num_str;
	}
	
	// 删除括号中的字符串
	public static String getDelBracketStr(String str) {
		if (str == null) {
			return null;
		}
		
		boolean 		have 	= false;
		int 			len 	= str.length();
		char 			ch 		= 0;
		StringBuffer 	sb 		= new StringBuffer();
		
		for (int i = 0; i < len; i++) {
			ch = str.charAt(i);
			if (ch == '(' || ch == '（') {
				have = true;
			} else if (ch == ')' || ch == '）') {
				have = false;
			} else {
				if (!have) {
					sb.append(ch);
				}
			}
		}
		
		return sb.toString();
	}
	
	// 判断str2是否在str1中
	public static boolean isIncluded(String str1, String str2) {
		if (str2 == null) {
			return true;
		}
		
		if (str1 == null) {
			return false;
		}
		
		int str1_len = str1.length() - 1;
		int str2_len = str2.length() - 1;
		
		while (str1_len > -1 && str2_len > -1) {
			if (str1.charAt(str1_len) == str2.charAt(str2_len)) {
				str1_len--;
				str2_len--;
			} else {
				str1_len--;
			}
		}
		
		if (str2_len == -1) {
			return true;
		} else {
			return false;
		}
	}
}
