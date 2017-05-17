/**地址比对外部调用接口类*/
public class AddrCompare {
	// 获得地址比对相似度
	public static float compare(String addr1, String addr2) {
		String std_addr1 = null;
		String std_addr2 = null;
		
		// 预处理
		PreTreatment pre = new PreTreatment(addr1); 
		pre.process();
		// 分词
		AddressSplit as = new AddressSplit(pre.getPreTreatedAddress());
		as.split();
		// 标准化
		AddressStandardize asd = new AddressStandardize(as.getSplitedAddress());
		asd.standardize();
		// 获得标准化结果
		std_addr1 = asd.getStandardizedAddress();
		
		// 预处理
		pre.setAddress(addr2);
		pre.process();
		// 分词
		as.setAddress(pre.getPreTreatedAddress());
		as.split();
		// 标准化
		asd.setAddress(as.getSplitedAddress());
		asd.standardize();
		// 获得标准化结果
		std_addr2 = asd.getStandardizedAddress();
		
		// 比对
		AddressCompare ac = new AddressCompare(std_addr1, std_addr2);
		
		return ac.compare();
	}
	
	// 获得地级市
	public static String getCity(String addr) {
		// 预处理
		PreTreatment pre = new PreTreatment(addr); 
		pre.process();
		// 分词
		AddressSplit as = new AddressSplit(pre.getPreTreatedAddress());
		as.split();
		// 标准化
		AddressStandardize asd = new AddressStandardize(as.getSplitedAddress());
		asd.standardize();
		
		return asd.getCityName();
	}
}
