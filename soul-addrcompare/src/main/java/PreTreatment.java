/**预处理类*/
public class PreTreatment {
	private String address;	// 待预处理的地址
	private String preTreatedAddress;	// 预处理后的地址
	
	public PreTreatment() {
		this.address = "";
		this.preTreatedAddress = "";
	}
	
	/**预处理
	 * @param address
	 */
	public PreTreatment(String address) {
		this.address = address;
		this.preTreatedAddress = "";
	}
	
	public void setAddress(String address) {
		this.address = address;
		this.preTreatedAddress = "";
	}
	
	// 执行预处理操作
	public void process() {
		String new_str = this.address;
		String cur_str = null;
		
		new_str = Util.Q2B(new_str); // 全角转化为半角
		
		new_str = Util.B2S(new_str); // 大写字母转小写
		
		new_str = Util.getDelDupString(new_str, Constants.generalSplitName); // 去除连续重复的分隔符，比如：1003号--15号楼--301室，处理后是：1003号-15号楼-301室
		
		/* 以下程序对类似"1003号-15号楼-301室"情况进行处理，处理后是"1003号15号楼301室"*/
		for (int i = 0; i < new_str.length(); i++) {
			cur_str = new_str.substring(i, i + 1);
			if (Constants.findInGeneralSplitName(cur_str)) { // 找到通用分隔符
				if (i > 0 && i < new_str.length() - 1 && Util.isChineseNumber(new_str.substring(i - 1, i)) && Util.isChineseNumber(new_str.substring(i + 1, i + 2))) {
					// 通用分隔符前后跟的都是数字，通用分隔符不能删除
					this.preTreatedAddress += cur_str;
				}
			} else { // 不是通用分隔符
				this.preTreatedAddress += cur_str;
			}
		}
	}
	
	public String getPreTreatedAddress() {
		return this.preTreatedAddress;
	}
	
	public void print() {
		System.out.println(this.preTreatedAddress);
	}
}
