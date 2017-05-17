/**公司名称比对类*/
public class CorpCompare {
	public static float compare(String corp1, String corp2) {
		String 	corp_name1 		= corp1.trim();
		String 	corp_name2 		= corp2.trim();
		int		corp_length1	= corp_name1.length(); // 公司名称一长度
		int		corp_length2	= corp_name2.length(); // 公司名称二长度
		int[][]	compare_matrix	= null; // 比对矩阵
		
		if (corp_name1 == null || corp_name1.equals("") || corp_name2 == null || corp_name2.equals("")) {
			// 两个公司名称只要有一个为空，即返回0
			return 0;
		}
		
		compare_matrix = new int[corp_length1][corp_length2];
		
		for (int i = 0; i < corp_length1; i++) {
			for (int j = 0; j < corp_length2; j++) {
				if (corp_name1.charAt(i) == corp_name2.charAt(j)) {
					compare_matrix[i][j] = 1;
					break;
				}
			}
		}
		
		if (isMatch(corp_name1, corp_name2)) {
			return (float) (2 / 3.0 + doMartix(compare_matrix) / 3.0);
		} else {
			return (float) (0.75 * doMartix(compare_matrix));
		}
	}
	
	private static boolean isMatch(String corp1, String corp2) {
		String str1 = delNotKeyWord(corp1);
		String str2 = delNotKeyWord(corp2);
		
		if (str1.length() == 0 || str2.length() == 0) {
			return false;
		}
		
		if (Util.isIncluded(str1, str2) || Util.isIncluded(str2, str1)) {
			return true;
		} else {
			return false;
		}
	}
	
	private static String delNotKeyWord(String corp) {
		String str = corp;
		
		str = Util.getDelBracketStr(str); // 删除括号中的字符串
		str = Util.getDelStringArray(str, Constants.corpCommonName); // 删除公司名称通名
		str = Util.getDelStringArray(str, Constants.zoneCommonName); // 删除行政区通名
		str = Util.getDelStringArray(str, Constants.zoneSpecName); // 删除行政区专名
		
		return str;
	}
	
	private static float doMartix(int[][] martix) {
		int num = 0;
		int row = martix.length;
		int col = martix[0].length;
		int min = 0;
		int max = 0;
		
		if (row < 1) {
			return 0;
		}
		
		if (col < 1) {
			return 0;
		}
		
		min = (row > col) ? col : row;
		max = (row > col) ? row : col;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (martix[i][j] == 1) {
					num++;
					break;
				}
			}
		}
		
		return ((float) (num * (min + max)) / ((float) 2 * row * max));
	}
}
