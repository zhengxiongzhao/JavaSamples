/**地址分词类*/
import java.util.ArrayList;

public class AddressSplit {
	private String address;	// 待分词的地址
	private String splitedAddress;	// 分词后的地址
	
	public AddressSplit() {
		this.address = "";
		this.splitedAddress = "";
	}
	
	/**地址分词
	 * @param address
	 */
	public AddressSplit(String address) {
		this.address = address.trim();
		this.splitedAddress = "";
	}
	
	public void setAddress(String address) {
		this.address = address.trim();
		this.splitedAddress = "";
	}
	
	public void split() {	// 分词
		try {
			int 	rest_addr_length	= this.address.length();	// 剩余未分词的字符串长度
			int 	curr_split_length	= 0;						// 当前字符串截取长度
			String	split_str			= null;						// 当前截取出的字符串
			int		last_find_pos		= rest_addr_length;			// 记录前一次找到通名或者专名的位置
			int		find_pos			= 0;						// 记录本次找到通名或者专名的位置
			
			while (rest_addr_length > 0) {
				curr_split_length = Parameters.MAXSPLITLEN;	// 当前字符串截取长度初始化为最长字符串截取长度
				
				while (curr_split_length > 0) {
					if (curr_split_length <= rest_addr_length) {
						//System.out.println(String.format("%s %s %s",curr_split_length, rest_addr_length - curr_split_length, rest_addr_length));
						split_str = this.address.substring(rest_addr_length - curr_split_length, rest_addr_length);
					} else {
						split_str = this.address.substring(0, rest_addr_length);
						curr_split_length = rest_addr_length;
					}
					
					String tmp_str = null;
					/*if("海宁".equals(split_str)){
						System.out.println("#######################");
						System.out.println(String.format("%s %s, %s", split_str, Constants.findInCommonName(split_str),Constants.getZoneSpecificName(split_str)));
						System.out.println("#######################");
						
					}*/ 
					
					if (Constants.findInCommonName(split_str)) { // 在通名中查找当前截取出的字符串
						find_pos = rest_addr_length;
						if (find_pos < last_find_pos) { // 前一次找到通名或者专名的位置与本次不同，说明中间有一段字符串既不是通名也不是专名
							if (this.splitedAddress.startsWith("+")) {
								this.splitedAddress = this.address.substring(find_pos, last_find_pos) + this.splitedAddress;
							} else {
								this.splitedAddress = this.address.substring(find_pos, last_find_pos) + "," + this.splitedAddress;
							}
						}
						last_find_pos = rest_addr_length - curr_split_length;
						this.splitedAddress = "+" + split_str + "," + this.splitedAddress;
						break;
					} else if ((tmp_str = Constants.getZoneSpecificName(split_str)) != null) { // 查找省、市、区、县专名
						find_pos = rest_addr_length;
						if (find_pos < last_find_pos) { // 前一次找到通名或者专名的位置与本次不同，说明中间有一段字符串既不是通名也不是专名
							if (this.splitedAddress.startsWith("+")) {
								this.splitedAddress = this.address.substring(find_pos, last_find_pos) + this.splitedAddress;
							} else {
								this.splitedAddress = this.address.substring(find_pos, last_find_pos) + "," + this.splitedAddress;
							}
						}
						last_find_pos = rest_addr_length - curr_split_length;
						
						if (tmp_str.equals(split_str) && !tmp_str.equals("中国")) {
							/* 省、市、区、县专名是全称，例如：北京市
							 * 处理后是：北京+市*/
							tmp_str = Util.getDelEndStrArrString(tmp_str, Constants.getAllStandardCommonName());
							this.splitedAddress = tmp_str + "+" + split_str.substring(tmp_str.length(), split_str.length()) + "," + this.splitedAddress;
						} else { // 省、市、区、县专名不是全称，例如：北京
							if (this.splitedAddress.startsWith("+")) {
								this.splitedAddress = split_str + this.splitedAddress;
							} else {
								this.splitedAddress = split_str + "+," + this.splitedAddress;
							}
						}
						//System.out.println(tmp_str);
						break;
					} else {
						curr_split_length--;	// 在通名和专名中均找不到，当前字符串截取长度-1
					}
				}
				//System.out.println(String.format("#########   %s",this.splitedAddress));
				
				if (curr_split_length == 0) {
					rest_addr_length -= 1;
				} else {
					rest_addr_length -= curr_split_length;
				}
			}
			
			if (last_find_pos > 0) {
				/*前一次找到通名或者专名的位置不为0，说明字符串头有一段既不是通名也不是专名，加入到分词后的地址最前位置处*/
				if (this.splitedAddress.startsWith("+")) {
					this.splitedAddress = this.address.substring(0, last_find_pos) + this.splitedAddress;
				} else {
					this.splitedAddress = this.address.substring(0, last_find_pos) + "," + this.splitedAddress;
				}
			}
			
			/*处理通名前没有专名的情况，比如：云+桥,+路，处理后是：云桥+路*/
			//System.out.println(this.splitedAddress);
			ArrayList<String> str_list = Util.getSplitStringArr(this.splitedAddress, ",");
			//System.out.println(str_list);
			String new_splited_address = "";
			for (int i = str_list.size() - 1; i >= 0; i--) {
				//System.out.println(String.format("%s %s", str_list.get(i), str_list.get(i).startsWith("+")));
				if (str_list.get(i).startsWith("+")) { // 从后往前找，找到第一个通名前没有专名的分词
					int j = i - 1;
					while (j >= 0) { // 往前找，直到找到通名前有专名的分词
						if (str_list.get(j).startsWith("+")) {
							j--;
						} else {
							break;
						}
					}
					String tmp_str = "";
					if (j >= 0 && i - j <= 1) {
						/* 例如：云+桥,+路，处理后是：云桥+路
						 * 对于特殊情况，如：上海+山,+东路，不能处理成：上海山+东路，而应处理成：上海+,山+东路*/
						int idx = str_list.get(j).indexOf("+");
						String str1 = "";
						String str2 = "";
						if (idx >=0 && idx < str_list.get(j).length() - 1) { // 根据"+"分隔成2个词
							str1 = str_list.get(j).substring(0, idx);
							str2 = str_list.get(j).substring(idx + 1, str_list.get(j).length());
						}
						if (Constants.getZoneSpecificName(str1) != null) {
							/* 根据"+"分隔出的第一个词属于省、市、区、县专名*/
							tmp_str += str1 + "+," + str2 + str_list.get(i);
						} else {
							//System.out.println(String.format("%s %s %s", str_list.get(j), Util.filterString(str_list.get(j), "+"), str_list.get(i)  ));
							tmp_str += Util.filterString(str_list.get(j), "+") + str_list.get(i);
						}
					} else if (j >= 0 && i - j > 1) {
						/* 例如：上海+市,+湖,+桥,+路，处理后是：上海+市,湖桥+路*/
						tmp_str = str_list.get(j) + ",";
						for (int k = j + 1; k < i; k++) {
							tmp_str += Util.filterString(str_list.get(k), "+");
						}
						tmp_str += str_list.get(i);
					} else if (j < 0) {
						/* 没有找到前一个通名前有专名的分词，除当前分词外中间的所有分词去掉"+"后连接
						 * 例如：+湖,+桥,+路，处理后是：湖桥+路*/
						for (int k = 0; k < i; k++) {
							tmp_str += Util.filterString(str_list.get(k), "+");
						}
						tmp_str += str_list.get(i);
					}
					new_splited_address = tmp_str + "," + new_splited_address;
					i = j;
				} else { // 通名前有专名的分词，不需要处理
					new_splited_address = str_list.get(i) + "," + new_splited_address;
				}
				//System.out.println(new_splited_address);
			}
			this.splitedAddress = new_splited_address;
			//System.out.println(this.splitedAddress);
		} catch (Exception e) {
			this.splitedAddress = null;
			e.printStackTrace();
		}
	}
	
	public String getSplitedAddress() {
		return this.splitedAddress;
	}
	
	public void print() {
		System.out.println("分词结果：" + this.splitedAddress);
	}
}
