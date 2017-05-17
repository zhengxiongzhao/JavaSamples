/**地址比对类*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressCompare {
	private String 				src_addr; // 源地址
	private String 				dst_addr; // 目标地址
	private ArrayList<String> 	src_num_list; // 源地址中门牌号组
	private ArrayList<String> 	dst_num_list; // 目标地址中门牌号组
	
	public AddressCompare() {
		this.src_addr = "";
		this.dst_addr = "";
	}
	
	/**地址比对
	 * @param src_addr
	 * @param dst_addr
	 */
	public AddressCompare(String src_addr, String dst_addr) {
		String addr1 = src_addr.trim();
		String addr2 = dst_addr.trim();
		
		// 保证源地址的长度大于等于目标地址 
		if (addr1.length() < addr2.length()) {
			this.src_addr = addr2;
			this.dst_addr = addr1;
		} else {
			this.src_addr = addr1;
			this.dst_addr = addr2;
		}
	}
	
	public void setAddress(String src_addr, String dst_addr) {
		String addr1 = src_addr.trim();
		String addr2 = dst_addr.trim();
		
		// 保证源地址的长度大于等于目标地址 
		if (addr1.length() < addr2.length()) {
			this.src_addr = addr2;
			this.dst_addr = addr1;
		} else {
			this.src_addr = addr1;
			this.dst_addr = addr2;
		}
	}
	
	// 计算两个地址之间的相似度，相似度在0～1之间
	public float compare() {
		float similarity = 0;
		
		if (src_addr == null || src_addr.trim().equals("") || dst_addr == null || dst_addr.trim().equals("")) {
			// 源地址和目标地址只要有一个为空，即返回0
			return 0;
		}
		
		if (src_addr.equals(dst_addr)) { // 完全匹配，返回1
			return 1;
		}
		
		int[][] compare_matrix = getCompareMatrix(); // 获得两字符串的比对矩阵
		getHouseNumberList(); // 析取出源地址和目标地址中的门牌号串
		ArrayList<int[]> line_list = findLinesInCompareMatrix(compare_matrix); // 获得比对矩阵中所有的线段
		ArrayList<int[]> filter_line_list = filterLinesInFoundLines(line_list); // 过虑线段数组中完全包含在其他线段中的线段
		float line_power = getLinePower(filter_line_list); // 获得所有线段的权值和
		line_power = line_power / src_addr.length();
		if (line_power > 1) { // 确保line_power在0～1之间
			line_power = 1;
		}
		
		// 计算字符相似度
		similarity = line_power * (1 - Parameters.LENGTHEQUALPOWER) * (1 - Parameters.NUMBERPOWER);
		similarity += Parameters.LENGTHEQUALPOWER * (1 - Parameters.NUMBERPOWER) * dst_addr.length() / src_addr.length();
		
		// 计算门牌号相似度
		if (src_num_list.size() == 0 && dst_num_list.size() == 0) { // 源地址和目标地址中都没有门牌号
			similarity = similarity / (1 - Parameters.NUMBERPOWER);
		} else if (src_num_list.size() != 0 && dst_num_list.size() != 0) {
			float number_power = getHouseNumberPower(); // 获得门牌号比对权值
			similarity += number_power * Parameters.NUMBERPOWER;
		}
		
		return similarity;
	}
	
	// 获得两字符串的比对矩阵
	private int[][] getCompareMatrix() {
		int[][] compare_matrix = new int[src_addr.length()][dst_addr.length()];
		src_num_list = new ArrayList<String>();
		dst_num_list = new ArrayList<String>();
		System.out.println(src_addr.length());
		System.out.println(dst_addr.length());
		for (int i = 0; i < src_addr.length(); i++) {
			for (int j = 0; j < dst_addr.length(); j++) {
				if (src_addr.charAt(i) == dst_addr.charAt(j)) {
					compare_matrix[i][j] = 1; // 字符相等
				} else {
					compare_matrix[i][j] = 0;
				}
			}
		}
		
		return compare_matrix;
	}
		
	// 析取出源地址和目标地址中的门牌号串
	private void getHouseNumberList() {
		Pattern pattern = Pattern.compile("([0-9a-zA-Z]+)");
		Matcher matcher = pattern.matcher(src_addr);
		while (matcher.find()) {
			src_num_list.add(matcher.group(1).toLowerCase());
		}
		matcher = pattern.matcher(dst_addr);
		while (matcher.find()) {
			dst_num_list.add(matcher.group(1).toLowerCase());
		}
	}
	
	// 查找比对矩阵中所有的线段
	private ArrayList<int[]> findLinesInCompareMatrix(int[][] compare_matrix) {
		ArrayList<int[]> line_list = new ArrayList<int[]>();
		
		for (int i = 0; i < src_addr.length(); i++) {
			for (int j = 0; j < dst_addr.length(); j++) {
				if (compare_matrix[i][j] == 1) {
					int[] line_info = new int[3]; // 记录线段信息，line_info[0]和line_info[1]分别记录线段起始的横、纵坐标，line_info[2]记录线段长度
					line_info[0] = i;
					line_info[1] = j;
					line_info[2] = 0;
					// 找到一个为1的点，横、纵坐标不断加1查找下一个点是否为1，从而获得线段长度
					for (int m = i, n = j; m < src_addr.length()&& n < dst_addr.length(); m++, n++) {
						if (compare_matrix[m][n] == 1) {
							compare_matrix[m][n] = 0;
							line_info[2]++; // 线段长度加1
						} else {
							break;
						}
					}
					line_list.add(line_info); // 加入线段数组
				}
			}
		}
		
		// 将线段数组中的线段按照先长度后横坐标的顺序排序
		Collections.sort(line_list, new Comparator<int[]>() {
			public int compare(int[] line1, int[] line2) {
				if (line2[2] - line1[2] != 0) { // 比较线段长度
					return line2[2] - line1[2];
				} else { // 比较横坐标
					return line2[0] - line1[0];
				}
			}
		});
		
		return line_list;
	}
	
	// 过滤线段数组中完全包含在其他线段中的线段
	private ArrayList<int[]> filterLinesInFoundLines(ArrayList<int[]> line_list) {
		ArrayList<int[]> 	filter_line_list 	= new ArrayList<int[]>();
		boolean 			is_contained 		= false;
		
		for (int i = line_list.size() - 1; i >= 0; i--) { // 从最短的线段开始遍历
			is_contained = false;
			for (int j = i - 1; j >= 0; j--) {
				if (line_list.get(j)[1] <= line_list.get(i)[1] && (line_list.get(j)[1] + line_list.get(j)[2] >= line_list.get(i)[1] + line_list.get(i)[2])) {
					/* 过滤列，此处的意思是以第i条线段为基准，找到第j条线段，
					 * 第j条线段的起点列坐标<=第i条线段的起点列坐标，
					 * 并且第j条线段的终点列坐 >=第i条线段的终点列坐标，判定为第i条线段被第j条线段完全包含 */
					is_contained = true;
					break;
				}
				/* 注：line_list.get(j)[0] < line_list.get(i)[0] 是咨询公司写的代码，
				 * 不知道这里为什么和列过滤不一致，写成'<'而不是'<='，为了保证比对结果的一致性，先写成'<' */
				if (line_list.get(j)[0] < line_list.get(i)[0] && (line_list.get(j)[0] + line_list.get(j)[2] >= line_list.get(i)[0] + line_list.get(i)[2])) {
					/* 过滤行，此处的意思是以第i条线段为基准，找到第j条线段，
					 * 第j条线段的起点行坐标<=第i条线段的起点行坐标，
					 * 并且第j条线段的终点行坐标>=第i条线段的终点行坐标，判定为第i条线段被第j条线段完全包含 */
					is_contained = true;
					break;
				}
			}
			
			if (!is_contained) { // 没有被包含，加入过滤后的线段数组
				filter_line_list.add(0, line_list.get(i));
			}
		}
		
		return filter_line_list;
	}
	
	// 获得所有线段的权值和
	private float getLinePower(ArrayList<int[]> line_list) {
		float 	line_power 		= 0;
		boolean is_connected	= false; // 标识两线段是否有交集
		int[][] temp_line_info 	= new int[line_list.size()][3];
		
		for (int i = line_list.size() - 1; i >= 0; i--) { // 从最短的线段开始遍历
			is_connected = false;
			
			for (int j = 0; j < temp_line_info.length; j++) {
				if (temp_line_info[j][2] > 0) {
					// 判断列上是否有交集
					if ((line_list.get(i)[1] <= temp_line_info[j][1] + temp_line_info[j][2]) && (line_list.get(i)[1] + line_list.get(i)[2] >= temp_line_info[j][1] + temp_line_info[j][2])) {
						/* 此处的意思是，第i条线段的起点列坐标<=第j条线段的终点列坐标，
						 * 并且第i条线段的终点列坐标>=第j条线段的终点列坐标，判定为有交集*/
						is_connected = true;
						
						// 相交的长度，第j条线段的终点列坐标-第i条线段的起点列坐标
						int connected_length = temp_line_info[j][1] + temp_line_info[j][2] - line_list.get(i)[1];
						
						// 计算权值
						if (line_list.get(i)[2] == 1) { // 线段长度为1
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[0]; // (线段长度-相交长度)*权值
						} else if (line_list.get(i)[2] == 2) { // 线段长度为2
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[1]; // (线段长度-相交长度)*权值
						} else if (line_list.get(i)[2] > 2) { // 线段长度大于2
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[2]; // (线段长度-相交长度)*权值
						}
						
						// 更新temp_line_info中的线段信息，line_list.get(i)[2]-connected_length为第i条线段中不相交的线段长度
						temp_line_info[j][0] = temp_line_info[j][0] + (line_list.get(i)[2] - connected_length);
						temp_line_info[j][1] = temp_line_info[j][1] + (line_list.get(i)[2] - connected_length);
						temp_line_info[j][2] = temp_line_info[j][2] + (line_list.get(i)[2] - connected_length);
						
						break;
					} else if ((line_list.get(i)[1] + line_list.get(i)[2] >= temp_line_info[j][1]) && (line_list.get(i)[1] + line_list.get(i)[2] <= temp_line_info[j][1] + temp_line_info[j][2])) {
						/* 此处的意思是，第i条线段的终点列坐标>=第j条线段的起点列坐标，
						 * 并且第i条线段的终点列坐标<=第j条线段的终点列坐标，判定为有交集*/
						is_connected = true;
						
						// 相交的长度，第i条线段的终点列坐标-第j条线段的起点列坐标
						int connected_length = line_list.get(i)[1] + line_list.get(i)[2] - temp_line_info[j][1];
						
						// 计算权值
						if (line_list.get(i)[2] == 1) { // 线段长度为1
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[0]; // (线段长度-相交长度)*权值
						} else if (line_list.get(i)[2] == 2) { // 线段长度为2
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[1]; // (线段长度-相交长度)*权值
						} else if (line_list.get(i)[2] > 2) { // 线段长度大于2
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[2]; // (线段长度-相交长度)*权值
						}
						
						// 更新temp_line_info中的线段信息，line_list.get(i)[2]-connected_length为第i条线段中不相交的线段长度
						temp_line_info[j][0] = temp_line_info[j][0] - (line_list.get(i)[2] - connected_length);
						temp_line_info[j][1] = temp_line_info[j][1] - (line_list.get(i)[2] - connected_length);
						temp_line_info[j][2] = temp_line_info[j][2] + (line_list.get(i)[2] - connected_length);
						
						break;
					}
					
					// 判断行上是否有交集
					if ((line_list.get(i)[0] <= temp_line_info[j][0] + temp_line_info[j][2]) && (line_list.get(i)[0] + line_list.get(i)[2] >= temp_line_info[j][0] + temp_line_info[j][2])) {
						/* 此处的意思是，第i条线段的起点行坐标<=第j条线段的终点行坐标，
						 * 并且第i条线段的终点行坐标>=第j条线段的终点行坐标，判定为有交集*/
						is_connected = true;
						
						// 相交的长度，第j条线段的终点行坐标-第i条线段的起点行坐标
						int connected_length = temp_line_info[j][0] + temp_line_info[j][2] - line_list.get(i)[0];
						
						// 计算权值
						if (line_list.get(i)[2] == 1) { // 线段长度为1
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[0]; // (线段长度-相交长度)*权值
						} else if (line_list.get(i)[2] == 2) { // 线段长度为2
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[1]; // (线段长度-相交长度)*权值
						} else if (line_list.get(i)[2] > 2) { // 线段长度大于2
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[2]; // (线段长度-相交长度)*权值
						}
						
						// 更新temp_line_info中的线段信息，line_list.get(i)[2]-connected_length为第i条线段中不相交的线段长度
						temp_line_info[j][0] = temp_line_info[j][0] + (line_list.get(i)[2] - connected_length);
						temp_line_info[j][1] = temp_line_info[j][1] + (line_list.get(i)[2] - connected_length);
						temp_line_info[j][2] = temp_line_info[j][2] + (line_list.get(i)[2] - connected_length);
						
						break;
					} else if ((line_list.get(i)[0] + line_list.get(i)[2] >= temp_line_info[j][0]) && (line_list.get(i)[0] + line_list.get(i)[2] <= temp_line_info[j][0] + temp_line_info[j][2])) {
						/* 此处的意思是，第i条线段的终点行坐标>=第j条线段的起点行坐标，
						 * 并且第i条线段的终点行坐标<=第j条线段的终点行坐标，判定为有交集*/
						is_connected = true;
						
						// 相交的长度，第i条线段的终点行坐标-第j条线段的起点行坐标
						int connected_length = line_list.get(i)[0] + line_list.get(i)[2] - temp_line_info[j][0];
						
						// 计算权值
						if (line_list.get(i)[2] == 1) { // 线段长度为1
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[0]; // (线段长度-相交长度)*权值
						} else if (line_list.get(i)[2] == 2) { // 线段长度为2
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[1]; // (线段长度-相交长度)*权值
						} else if (line_list.get(i)[2] > 2) { // 线段长度大于2
							line_power += (line_list.get(i)[2] - connected_length) * Parameters.LINEPOWER[2]; // (线段长度-相交长度)*权值
						}
						
						// 更新temp_line_info中的线段信息，line_list.get(i)[2]-connected_length为第i条线段中不相交的线段长度
						temp_line_info[j][0] = temp_line_info[j][0] - (line_list.get(i)[2] - connected_length);
						temp_line_info[j][1] = temp_line_info[j][1] - (line_list.get(i)[2] - connected_length);
						temp_line_info[j][2] = temp_line_info[j][2] + (line_list.get(i)[2] - connected_length);
						
						break;
					}
				}
			}
			
			if (!is_connected) { // 两线段没有交集
				temp_line_info[i][0] = line_list.get(i)[0];
				temp_line_info[i][1] = line_list.get(i)[1];
				temp_line_info[i][2] = line_list.get(i)[2];
				
				// 计算权值
				if (line_list.get(i)[2] == 1) { // 线段长度为1
					line_power += line_list.get(i)[2] * Parameters.LINEPOWER[0]; // 长度*权值
				} else if (line_list.get(i)[2] == 2) { // 线段长度为2
					line_power += line_list.get(i)[2] * Parameters.LINEPOWER[1]; // 长度*权值
				} else if (line_list.get(i)[2] > 2) { // 线段长度大于2
					line_power += line_list.get(i)[2] * Parameters.LINEPOWER[2]; // 长度*权值
				}
			}
		}
 		
		return line_power;
	}
	
	// 获得门牌号比对权值
	private float getHouseNumberPower() {
		float number_power 			= 0; // 门牌号比对权值
		float length_equal_power 	= 0; // 门牌号个数相同权值
		
		if (src_num_list.size() > dst_num_list.size()) {
			length_equal_power = (float) dst_num_list.size() / src_num_list.size();
		} else {
			length_equal_power = (float) src_num_list.size() / dst_num_list.size();
		}
		
		// 正向比对门牌号是否相同，如{123,15,3}和{123,3}的比对结果是{1,0}
		int[] equal_list = new int[dst_num_list.size()];
		for (int i = 0; i < src_num_list.size() && i < dst_num_list.size(); i++) {
			if (src_num_list.get(i).equals(dst_num_list.get(i))) {
				equal_list[i] = 1;
			}
		}
		// 逆向比对门牌号是否相同，更新equal_list数组，如{123,15,3}和{123,3}的比对结果是{1,1}
		int src_end_idx = src_num_list.size() - 1;
		for (int i = dst_num_list.size() - 1; i >= 0; i--) {
			if (equal_list[i] == 0 && src_end_idx >= 0 && src_num_list.get(src_end_idx).equals(dst_num_list.get(i))) {
				equal_list[i] = 1;
			}
			src_end_idx--;
		}

		// 计算门牌号相等的个数
		int equals_count = 0;
		for (int i = 0; i < equal_list.length; i++) {
			if (equal_list[i] == 1) {
				equals_count++;
			}
		}
		
		// 计算门牌号权值
		number_power = length_equal_power * equals_count / dst_num_list.size();
		
		return number_power;
	}
}
