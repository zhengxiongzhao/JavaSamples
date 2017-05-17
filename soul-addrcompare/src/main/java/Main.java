
public class Main {
	public static void main(String[] args) {
		Constants.init();
		//Constants.zoneTree.printTree();
		//System.out.println(Constants.getFullZoneName("津市市"));
		//湖南省常德市津市市人民路
		//湖桥路
		
		/*PreTreatment pre = new PreTreatment("枣庄市市中区");
		pre.process();
		AddressSplit as = new AddressSplit(pre.getPreTreatedAddress());
		as.split();
		as.print();
		AddressStandardize asd = new AddressStandardize(as.getSplitedAddress());
		asd.standardize();
		asd.print();
		System.out.println();
		
		pre.setAddress("上海海运学院三号楼102");
		pre.process();
		as.setAddress(pre.getPreTreatedAddress());
		as.split();
		as.print();
		asd.setAddress(as.getSplitedAddress());
		asd.standardize();
		asd.print();
		System.out.println();
		
		pre.setAddress("上海市浦东陆家嘴东路201号,15幢,1201");
		pre.process();
		as.setAddress(pre.getPreTreatedAddress());
		as.split();
		as.print();
		asd.setAddress(as.getSplitedAddress());
		asd.standardize();
		asd.print();
		System.out.println();
		
		pre.setAddress("黄浦南京西路一千零三号");
		pre.process();
		as.setAddress(pre.getPreTreatedAddress());
		as.split();
		as.print();
		asd.setAddress(as.getSplitedAddress());
		asd.standardize();
		asd.print();
		System.out.println();
		
		pre.setAddress("津市市中路");
		pre.process();
		as.setAddress(pre.getPreTreatedAddress());
		as.split();
		as.print();
		asd.setAddress(as.getSplitedAddress());
		asd.standardize();
		asd.print();
		System.out.println();
		
		pre.setAddress("枣庄市市中区");
		pre.process();
		as.setAddress(pre.getPreTreatedAddress());
		as.split();
		as.print();
		asd.setAddress(as.getSplitedAddress());
		asd.standardize();
		asd.print();
		System.out.println();
		
		pre.setAddress("上海镇宁路");
		pre.process();
		as.setAddress(pre.getPreTreatedAddress());
		as.split();
		as.print();
		asd.setAddress(as.getSplitedAddress());
		asd.standardize();
		asd.print();
		System.out.println();*/
		
		PreTreatment pre = new PreTreatment("黑龙江大兴安岭漠河安远乡宁河村");
		pre.process(); 
		pre.print();
		AddressSplit as = new AddressSplit(pre.getPreTreatedAddress());
		as.split();
		as.print();
		
		AddressStandardize asd = new AddressStandardize(as.getSplitedAddress());
		asd.standardize();
		String str1 = asd.getStandardizedAddress();
		asd.print();
		System.out.println();
		
		pre.setAddress("上海宝山区大场镇南陈路15号");
		pre.process();
		as.setAddress(pre.getPreTreatedAddress());
		as.split();
		as.print();
		asd.setAddress(as.getSplitedAddress());
		asd.standardize();
		String str2 = asd.getStandardizedAddress();
		asd.print();
		System.out.println();
		
		System.out.println(String.format("%s %s", str1, str2));
		AddressCompare ac = new AddressCompare(str1, str2);
		System.out.println(ac.compare());
		
		
		System.out.println(CorpCompare.compare("中国移动上海公司", "上海移动"));
		
		
		System.out.println();
		String addr1 = "曲阳县人民路12号";
		String addr2 = "黄浦区江宁路22号";
		System.out.println("源地址：" + addr1 + ", 目标地址：" + addr2 + ", 相似度：" + AddrCompare.compare(addr1, addr2));
		System.out.println(addr1 + " 所在市：" + AddrCompare.getCity(addr1));
		System.out.println(addr2 + " 所在市：" + AddrCompare.getCity(addr2));
	}
}
