/**地址标准化类*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class AddressStandardize {
	private String address;	// 待标准化的地址
	private String standardizedAddress;	// 标准化后的地址
	private String province_name; // 省名
	private String city_name; // 市名
	private String country_name; // 区名
	
	public AddressStandardize() {
		this.address = "";
		this.standardizedAddress = "";
		this.province_name = null;
		this.city_name = null;
		this.country_name = null;
	}
	
	/**地址标准化
	 * @param address
	 */
	public AddressStandardize(String address) {
		this.address = address.trim();
		this.standardizedAddress = "";
		this.province_name = null;
		this.city_name = null;
		this.country_name = null;
	}
	
	public void setAddress(String address) {
		this.address = address.trim();
		this.standardizedAddress = "";
		this.province_name = null;
		this.city_name = null;
		this.country_name = null;
	}
	
	public void standardize() {	// 标准化
		try {
			ArrayList<String> 				splited_list 		= Util.getSplitStringArr(this.address, ","); // 分词后得到的字符串组
			String[] 						standard_arr 		= new String[splited_list.size()]; // 标准化后得到的字符串组
			int								max_zone_count		= 3; // 最多处理的行政区划个数，3代表只处理省、市、区三级
			ArrayList<ArrayList<String>>	zone_arr_list		= new ArrayList<ArrayList<String>>(); // 析取出的行政区划字符串组
			int								curr_zone_id		= 0; // 析取出的行政区划字符串个数
			int[]							zone_id_arr			= new int[max_zone_count]; // 记录行政区划字符串在分词后得到的字符串组中的下标
			boolean							zone_begin_flag		= false; // 标识找到第一个行政区名
			boolean							zone_end_flag		= false; // 标识行政区名查找结束
			
			for (int i = 0; i < splited_list.size(); i++) {
				if (splited_list.get(i).indexOf("+") != -1) { // 单词中有"+"
					ArrayList<String> addr = Util.getSplitStringArr(splited_list.get(i), "+"); // 按"+"把单词分割，比如：上海+市，分割成：上海和市
					
					if (addr.size() == 2) { // "+"分割出2个词
						if (Constants.findInStandardCommonName(addr.get(1))) { // "+"后面跟的是标准行政区划通名
							ArrayList<String> zone_spec_name_list = Constants.getZoneSpecificNameList(addr.get(0) + addr.get(1)); // 用全名查找，比如：用上海市
							
							if (zone_spec_name_list.size() == 0) { // 用全名查找没有找到，比如：上海区
								zone_spec_name_list = Constants.getZoneSpecificNameList(addr.get(0)); // 换用专名查找，比如：上海
							}
							
							if (zone_spec_name_list.size() != 0) { // 查找成功
								if (curr_zone_id < max_zone_count && !zone_end_flag) { // 当前待标准化的行政区名小于最大处理个数，并且没有遇到未知的行政区划专名
									zone_arr_list.add(zone_spec_name_list);
									zone_id_arr[curr_zone_id] = i;  // 记录行政区划字符串在分词后得到的字符串组中的下标
									curr_zone_id++; // 析取出的行政区划字符串个数加1
									zone_begin_flag = true;
								} else { // 已经有max_zone_count个行政区划字符串，不再处理
									standard_arr[i] = addr.get(0) + addr.get(1);
									if (zone_begin_flag) {
										zone_end_flag = true;
									}
								}
							} else { // 未知的行政区划专名，不做处理
								standard_arr[i] = addr.get(0) + addr.get(1);
								if (zone_begin_flag) {
									zone_end_flag = true;
								}
							}
						} else if (Constants.findInHouseCommonName(addr.get(1))) { // "+"后面跟的是门牌号通名，比如：200+号
							String number_str = getNumFromChineseNumber(addr.get(0));
							if (number_str != null) { // 中文数字转换成功
								standard_arr[i] = number_str;
							} else { // 无法将中文数字转换为数字
								standard_arr[i] = addr.get(0) + addr.get(1);
							}
							if (zone_begin_flag) {
								zone_end_flag = true;
							}
						} else { // "+"后面跟的不是标准行政区划通名也不是门牌号通名，比如：北京+东路
							standard_arr[i] = addr.get(0) + addr.get(1);
							if (zone_begin_flag) {
								zone_end_flag = true;
							}
						}
					} else if (addr.size() == 1) { // "+"后没有跟通名，遍历地区树得到标准化地址，比如：黄浦+
						ArrayList<String> zone_spec_name_list = Constants.getZoneSpecificNameList(addr.get(0));
						
						if (zone_spec_name_list.size() != 0) { // 用专名查找成功
							if (!zone_spec_name_list.get(0).equals("中国")) { // 如果遇到"中国"，不做处理，并且不保存在标准化后的字符串组中
								if (curr_zone_id < max_zone_count && !zone_end_flag) { // 当前待标准化的行政区名小于最大处理个数，并且没有遇到未知的行政区划专名
									zone_arr_list.add(zone_spec_name_list);
									zone_id_arr[curr_zone_id] = i;  // 记录行政区划字符串在分词后得到的字符串组中的下标
									curr_zone_id++; // 析取出的行政区划字符串个数加1
									zone_begin_flag = true;
								} else { // 已经有max_zone_count个行政区划字符串，不再处理
									standard_arr[i] = addr.get(0);
									if (zone_begin_flag) {
										zone_end_flag = true;
									}
								}
							}
						} else { // 未知的行政区划专名，不做处理
							standard_arr[i] = addr.get(0);
							if (zone_begin_flag) {
								zone_end_flag = true;
							}
						}
					}
				} else { // 单词中没有"+"，作为门牌号处理
					String number_str = getNumFromChineseNumber(splited_list.get(i));
					if (number_str != null) { // 中文数字转换成功
						standard_arr[i] = number_str;
					} else { // 无法将中文数字转换为数字
						standard_arr[i] = splited_list.get(i);
					}
					if (zone_begin_flag) {
						zone_end_flag = true;
					}
				}
			}
			//System.out.println(zone_arr_list);
			
			if (zone_arr_list.size() >= 1) { // 有待标准化的行政区划字符串
				/* 此处的处理方法：
				 * 比如用户输入的是辽宁朝阳，辽宁对应到辽宁省，朝阳对应到朝阳区、朝阳市和朝阳县，
				 * 辽宁省的行政等级是1，朝阳区的行政等级是3，朝阳市的行政等级是2， 朝阳县的行政等级是3，
				 * 分别按照行政等级排序，存入ordered_zone_arr_list，如：{辽宁省1}， {朝阳市2, 朝阳区3, 朝阳县3}，
				 * 最后构成省、市、区名数组遍历地区树得到标准化地址存入standard_arr */
				ArrayList<ArrayList<String>> ordered_zone_arr_list = new ArrayList<ArrayList<String>>(); // 按照行政区划等级由高到低排序后的行政区划字符串组
				
				// 按照行政区划等级由高到低排序
				for (int i = 0; i < zone_arr_list.size(); i++) {
					// 获得所有行政区的行政等级
					ArrayList<String> tmp_arr_list = new ArrayList<String>();
					for (int j = 0; j < zone_arr_list.get(i).size(); j++) {
						ArrayList<String> level_list = Constants.getZoneLevelList(zone_arr_list.get(i).get(j));
						for (int k = 0; k < level_list.size(); k++) {
							tmp_arr_list.add(zone_arr_list.get(i).get(j) + level_list.get(k));
						}
					}
					
					String[] tmp_arr = new String[tmp_arr_list.size()];
					for (int j = 0; j < tmp_arr_list.size(); j++) {
						tmp_arr[j] = tmp_arr_list.get(j);
					}
					
					// 按行政等级排序
					String tmp_str = null;
					for (int j = 1; j < tmp_arr.length; j++) {
						for (int k = 0; k < tmp_arr.length - j; k++) {
							if (Util.filterNumber(tmp_arr[k]) > Util.filterNumber(tmp_arr[k + 1])) {
								tmp_str = tmp_arr[k];
								tmp_arr[k] = tmp_arr[k + 1];
								tmp_arr[k + 1] = tmp_str;
							}
						}
					}
					tmp_arr_list.clear();
					for (int j = 0; j < tmp_arr.length; j++) {
						tmp_arr_list.add(tmp_arr[j]);
					}
					ordered_zone_arr_list.add(tmp_arr_list);
				}
				//System.out.println(ordered_zone_arr_list);
				ArrayList<String[]> all_zone_arr_list = new ArrayList<String[]>(); // 构成省、市、区字符串数组
				
				if (ordered_zone_arr_list.size() == 1) { // 1个行政区划字符串的情况
					for (int i = 0; i < ordered_zone_arr_list.get(0).size(); i++) {
						int level = Util.filterNumber(ordered_zone_arr_list.get(0).get(i)); // 获得行政等级
						if (level >= 1 && level <= 3) {
							String[] all_zone_arr = new String[3];
							all_zone_arr[level - 1] = Util.filterNotNumber(ordered_zone_arr_list.get(0).get(i));
							all_zone_arr_list.add(all_zone_arr);
						}
					}
				} else if (ordered_zone_arr_list.size() == 2) { // 2个行政区划字符串的情况
					for (int i = 0; i < ordered_zone_arr_list.get(0).size(); i++) {
						for (int j = 0; j < ordered_zone_arr_list.get(1).size(); j++) {
							int level1 = Util.filterNumber(ordered_zone_arr_list.get(0).get(i));
							int level2 = Util.filterNumber(ordered_zone_arr_list.get(1).get(j));
							
							if (level1 >= 1 && level1 <= 3 && level2 >= 1 && level2 <= 3 && level1 != level2) {
								String[] all_zone_arr = new String[3];
								all_zone_arr[level1 - 1] = Util.filterNotNumber(ordered_zone_arr_list.get(0).get(i));
								all_zone_arr[level2 - 1] = Util.filterNotNumber(ordered_zone_arr_list.get(1).get(j));
								all_zone_arr_list.add(all_zone_arr);
							}
						}
					}
					for (int i = 0; i < ordered_zone_arr_list.get(0).size(); i++) {
						int level = Util.filterNumber(ordered_zone_arr_list.get(0).get(i)); // 获得行政等级
						if (level >= 1 && level <= 3) {
							String[] all_zone_arr = new String[3];
							all_zone_arr[level - 1] = Util.filterNotNumber(ordered_zone_arr_list.get(0).get(i));
							all_zone_arr_list.add(all_zone_arr);
						}
					}
				} else if (ordered_zone_arr_list.size() == 3) { // 3个行政区划字符串的情况
					for (int i = 0; i < ordered_zone_arr_list.get(0).size(); i++) {
						for (int j = 0; j < ordered_zone_arr_list.get(1).size(); j++) {
							for (int k = 0; k < ordered_zone_arr_list.get(2).size(); k++) {
								int level1 = Util.filterNumber(ordered_zone_arr_list.get(0).get(i));
								int level2 = Util.filterNumber(ordered_zone_arr_list.get(1).get(j));
								int level3 = Util.filterNumber(ordered_zone_arr_list.get(2).get(k));
								
								if (level1 >= 1 && level1 <= 3 && level2 >= 1 && level2 <= 3 && level3 >= 1 && level3 <= 3 && level1 != level2 && level2 != level3 && level1 != level3) {
									String[] all_zone_arr = new String[3];
									all_zone_arr[level1 - 1] = Util.filterNotNumber(ordered_zone_arr_list.get(0).get(i));
									all_zone_arr[level2 - 1] = Util.filterNotNumber(ordered_zone_arr_list.get(1).get(j));
									all_zone_arr[level3 - 1] = Util.filterNotNumber(ordered_zone_arr_list.get(2).get(k));
									all_zone_arr_list.add(all_zone_arr);
								}
							}
						}
					}
					for (int i = 0; i < ordered_zone_arr_list.get(0).size(); i++) {
						for (int j = 0; j < ordered_zone_arr_list.get(1).size(); j++) {
							int level1 = Util.filterNumber(ordered_zone_arr_list.get(0).get(i));
							int level2 = Util.filterNumber(ordered_zone_arr_list.get(1).get(j));
							
							if (level1 >= 1 && level1 <= 3 && level2 >= 1 && level2 <= 3 && level1 != level2) {
								String[] all_zone_arr = new String[3];
								all_zone_arr[level1 - 1] = Util.filterNotNumber(ordered_zone_arr_list.get(0).get(i));
								all_zone_arr[level2 - 1] = Util.filterNotNumber(ordered_zone_arr_list.get(1).get(j));
								all_zone_arr_list.add(all_zone_arr);
							}
						}
					}
					for (int i = 0; i < ordered_zone_arr_list.get(0).size(); i++) {
						int level = Util.filterNumber(ordered_zone_arr_list.get(0).get(i)); // 获得行政等级
						if (level >= 1 && level <= 3) {
							String[] all_zone_arr = new String[3];
							all_zone_arr[level - 1] = Util.filterNotNumber(ordered_zone_arr_list.get(0).get(i));
							all_zone_arr_list.add(all_zone_arr);
						}
					}
				}
				/*System.out.println("################################");
				for (Iterator iterator = all_zone_arr_list.iterator(); iterator
						.hasNext();) {
					String[] strings = (String[]) iterator.next();
					System.out.println(Arrays.asList(strings));
				}
				System.out.println("################################");*/
				boolean std_success = false;
				// 遍历地区树获得标准化行政区名
				for (int i = 0; i < all_zone_arr_list.size(); i++) {
					String std_zone_addr = null;
					//System.out.println(String.format("%s %s %s", all_zone_arr_list.get(i)[0], all_zone_arr_list.get(i)[1], all_zone_arr_list.get(i)[2]));
					if ((std_zone_addr = Constants.getFullZoneName(all_zone_arr_list.get(i)[0], all_zone_arr_list.get(i)[1], all_zone_arr_list.get(i)[2])) != null) {
						ArrayList<String> std_zone_addr_list = Util.getSplitStringArr(std_zone_addr, "+");
						standard_arr[zone_id_arr[0]] = "";
						for (int j = 0; j < std_zone_addr_list.size(); j++) { // 拼接省、市、区
							standard_arr[zone_id_arr[0]] += std_zone_addr_list.get(j);
						}
						int num_zone_arr = 0;
						for (int j = 0; j < 3; j++) {
							if (all_zone_arr_list.get(i)[j] != null) {
								num_zone_arr++;
							}
						}
						for (int j = num_zone_arr; j < curr_zone_id; j++) { // 一部分行政区没有标准化，不做处理，例如：北京黄浦，只对北京进行标准化处理
							standard_arr[zone_id_arr[j]] = Util.filterString(splited_list.get(zone_id_arr[j]), "+");
						}
						
						// 获得标准化后的省、市、区名
						if (std_zone_addr_list.size() == 1) {
							if (std_zone_addr_list.get(0).equals("北京市")
									|| std_zone_addr_list.get(0).equals("天津市")
									|| std_zone_addr_list.get(0).equals("上海市")
									|| std_zone_addr_list.get(0).equals("重庆市")) {
								this.province_name = std_zone_addr_list.get(0);
								this.city_name = std_zone_addr_list.get(0);
							} else {
								this.province_name = std_zone_addr_list.get(0);
							}
						} else if (std_zone_addr_list.size() == 2) {
							if (std_zone_addr_list.get(0).equals("北京市")
									|| std_zone_addr_list.get(0).equals("天津市")
									|| std_zone_addr_list.get(0).equals("上海市")
									|| std_zone_addr_list.get(0).equals("重庆市")) {
								this.province_name = std_zone_addr_list.get(0);
								this.city_name = std_zone_addr_list.get(0);
								this.country_name = std_zone_addr_list.get(1);
							} else {
								this.province_name = std_zone_addr_list.get(0);
								this.city_name = std_zone_addr_list.get(1);
							}
						} else if (std_zone_addr_list.size() == 3) {
							this.province_name = std_zone_addr_list.get(0);
							this.city_name = std_zone_addr_list.get(1);
							this.country_name = std_zone_addr_list.get(2);
						}
						
						std_success = true;
						break;
					}
				}
				
				if (!std_success) { // 标准化失败
					for (int j = 0; j < curr_zone_id; j++) {
						standard_arr[zone_id_arr[j]] = Util.filterString(splited_list.get(zone_id_arr[j]), "+");
					}
				}
			}
			
			// 拼接标准化后的字符串组standard_arr，得到最终的标准化地址串
			this.standardizedAddress = "";
			for (int i = 0; i < standard_arr.length; i++) {
				if (standard_arr[i] != null && !standard_arr[i].equals("")) {
					if (i > 0 && Util.isChineseNumber(standard_arr[i]) && Util.isChineseNumber(standard_arr[i - 1])) {
						this.standardizedAddress += "-";
					}
					this.standardizedAddress += standard_arr[i];
				}
			}
		} catch (Exception e) {
			this.standardizedAddress = null;
			e.printStackTrace();
		}
	}
	
	/**
	 * @return 标准化后的地址
	 */
	public String getStandardizedAddress() {
		return this.standardizedAddress;
	}
	
	public String getProvinceName() {
		return this.province_name;
	}
	
	public String getCityName() {
		return this.city_name;
	}
	
	public String getCountryName() {
		return this.country_name;
	}
	
	public void print() {
		System.out.println("标准化结果：" + this.standardizedAddress);
		System.out.println("省：" + province_name);
		System.out.println("市：" + city_name);
		System.out.println("区：" + country_name);
	}
	
	// 将中文数字转换为阿拉伯数字，注：只对千级别及以下的数进行转换
	private String getNumFromChineseNumber(String str) {
		if (Util.isChineseNumber(str)) {
			String 	norm_str 	= str;
			int		i 			= 0;
			
			// 预处理
			norm_str = norm_str.replace('０', '0');
			norm_str = norm_str.replace('○', '0');
			norm_str = norm_str.replace('零', '0');
			norm_str = norm_str.replace('１', '1');
			norm_str = norm_str.replace('一', '1');
			norm_str = norm_str.replace('壹', '1');
			norm_str = norm_str.replace('２', '2');
			norm_str = norm_str.replace('二', '2');
			norm_str = norm_str.replace('两', '2');
			norm_str = norm_str.replace('贰', '2');
			norm_str = norm_str.replace('３', '3');
			norm_str = norm_str.replace('三', '3');
			norm_str = norm_str.replace('叁', '3');
			norm_str = norm_str.replace('４', '4');
			norm_str = norm_str.replace('四', '4');
			norm_str = norm_str.replace('肆', '4');
			norm_str = norm_str.replace('５', '5');
			norm_str = norm_str.replace('五', '5');
			norm_str = norm_str.replace('伍', '5');
			norm_str = norm_str.replace('６', '6');
			norm_str = norm_str.replace('六', '6');
			norm_str = norm_str.replace('陆', '6');
			norm_str = norm_str.replace('７', '7');
			norm_str = norm_str.replace('七', '7');
			norm_str = norm_str.replace('柒', '7');
			norm_str = norm_str.replace('８', '8');
			norm_str = norm_str.replace('八', '8');
			norm_str = norm_str.replace('捌', '8');
			norm_str = norm_str.replace('９', '9');
			norm_str = norm_str.replace('九', '9');
			norm_str = norm_str.replace('玖', '9');
			norm_str = norm_str.replace('仟', '千');
			norm_str = norm_str.replace('佰', '百');
			norm_str = norm_str.replace('拾', '十');
			
			for (i = 0; i < norm_str.length(); i++) {
				if (!Util.isNumChar(norm_str.charAt(i))) { // 找到非数字的字符
					break;
				}
			}
			
			if (i == norm_str.length()) { // 中文字符串中不含千、百、十
				return norm_str;
			} else { // 中文字符串中含千、百、十，需进一步转换
				int idx = 0;
				int num = 0; // 转换后的数字
				
				idx = norm_str.indexOf("千");
				if (idx == 0) { // "千"在第一位，取1000
					num += 1000;
				} else if (idx > 0) { // "千"不在第一位
					if (Util.isNumChar(norm_str.charAt(idx - 1))) {
						// 前一位是数字，取前一位数字×1000
						num += Util.getNumFromNumChar(norm_str.charAt(idx - 1)) * 1000;
					} else {
						// 前一位不是数字，判断为不可识别，不做任何处理返回null
						return null;
					}
				}
				
				idx = norm_str.indexOf("百");
				if (idx == 0) { // "百"在第一位，取100
					num += 100;
				} else if (idx > 0) { // "百"不在第一位
					if (Util.isNumChar(norm_str.charAt(idx - 1))) {
						// 前一位是数字，取前一位数字×100
						num += Util.getNumFromNumChar(norm_str.charAt(idx - 1)) * 100;
					} else {
						// 前一位不是数字，判断为不可识别，不做任何处理返回null
						return null;
					}
				} else if (idx == -1) { // 找不到"百"
					idx = norm_str.indexOf("千");
					if (idx >= 0 && idx < norm_str.length() - 1) { // 找到"千"，并且"千"不在最后一位，比如：一千三
						if (Util.isNumChar(norm_str.charAt(idx + 1))) {
							// "千"后一位是数字，取该数字×100
							num += Util.getNumFromNumChar(norm_str.charAt(idx + 1)) * 100;
						} else {
							// "千"后一位不是数字，判断为不可识别，不做任何处理返回null
							return null;
						}
					}
				}
				
				idx = norm_str.indexOf("十");
				if (idx == 0) { // "十"在第一位，取10
					num += 10;
					if (idx < norm_str.length() - 1) { // "十"不在最后一位
						if (Util.isNumChar(norm_str.charAt(idx + 1))) {
							// "十"后一位是数字，取该数字
							num += Util.getNumFromNumChar(norm_str.charAt(idx + 1));
						} else {
							// "十"后一位不是数字，判断为不可识别，不做任何处理返回null
							return null;
						}
					}
				} else if (idx > 0) { // "十"不在第一位
					if (Util.isNumChar(norm_str.charAt(idx - 1))) {
						// 前一位是数字，取前一位数字×10
						num += Util.getNumFromNumChar(norm_str.charAt(idx - 1)) * 10;
					} else {
						// 前一位不是数字，判断为不可识别，不做任何处理返回null
						return null;
					}
					if (idx < norm_str.length() - 1) { // "十"不在最后一位
						if (Util.isNumChar(norm_str.charAt(idx + 1))) {
							// "十"后一位是数字，取该数字
							num += Util.getNumFromNumChar(norm_str.charAt(idx + 1));
						} else {
							// "十"后一位不是数字，判断为不可识别，不做任何处理返回null
							return null;
						}
					}
				} else if (idx == -1) { // 找不到"十"
					idx = norm_str.indexOf("百");
					if (idx >= 0 && idx < norm_str.length() - 1) { // 找到"百"，并且"百"不在最后一位，比如：一百三
						if (Util.isNumChar(norm_str.charAt(idx + 1))) {
							// "百"后一位是数字，取该数字×10
							num += Util.getNumFromNumChar(norm_str.charAt(idx + 1)) * 10;
						} else {
							// "百"后一位不是数字，判断为不可识别，不做任何处理返回null
							return null;
						}
					}
				}
				
				// 处理一千零三、一百零二等情况
				idx = norm_str.length() - 2;
				if (idx >= 0 && norm_str.charAt(idx) == '0' && Util.isNumChar(norm_str.charAt(idx + 1))) {
					num += Util.getNumFromNumChar(norm_str.charAt(idx + 1));
				}
				
				return String.valueOf(num);
			}
		} else {
			// 不做任何处理返回null
			return null;
		}
	}
}
