/**省市通名、专名等常量*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Constants {
	public final static int ZONETREE 			= 1;
	public final static int ZONESPECIFICNAME 	= 2;
	public final static int PROVINCE			= 3;
	public final static int CITY				= 4;
	public final static int COUNTRY				= 5;
	public final static int TOWN				= 6;
	public final static int HOUSE				= 7;
	public final static int OTHER				= 8;
	
	// 地区树
	public static ZoneTree zoneTree;
	
	// 省、市、区、县专名
	public static HashMap<String, String> zoneSpecificName;
	
	// 省、市、区、县级别
	public static HashMap<String, String> zoneLevel;
	
	// 省级行政单位通名
	public final static String[] provinceCommonName = {
		"省", "市", "自治区"
	};
	
	// 市（地区）级行政单位通名
	public final static String[] cityCommonName = {
		"市", "地区", "新区", "自治州", "盟"
	};
	
	// 县（区）级行政单位通名
	public final static String[] countyCommonName = {
		"县", "区", "新区", "市", "自治县", "旗"
	};
	
	// 乡镇、村通名
	public final static String[] townCommonName = {
		"镇", "乡", "村"
	};
	
	// 门牌号通名
	public final static String[] houseCommonName = {
		"号楼", "号院", "支", "弄", "门", "号", "层", "座", "栋", "室",
		"幢", "区", "单", "元", "院", "单元", "楼", "房", "间", "组", "支弄", "大楼",
		"、", "-", ",", "*", "^", "#", " ", "	"
	};
	
	// 通用分隔符
	public final static String[] generalSplitName = {
		"、", "-", ",", "*", "^", "#", " ", "	"
	};
	
	// 非标准行政区划通名
	public final static String[] otherCommonName = {
		"工业区", "城区", "工业园区", "园区", "开发区", "高新区", "高科技园区", "保税区",
		"东村" ,"南村" ,"西村" ,"北村", "新村",
		"路", "东路", "南路", "西路", "北路", "中路", "支路",
		"东一路", "东二路", "东三路", "南一路", "南二路", "南三路",
		"西一路", "西二路", "西三路", "北一路", "北二路", "北三路",
		"街", "东街", "南街", "西街", "北街", "中街", "直街", "街道",
		"大街", "东大街", "南大街", "西大街", "北大街", "中大街",
		"道", "东道", "南道", "西道", "北道", "中道", "直道",
		"大道", "东大道", "南大道", "西大道", "北大道", "中大道",
		"东弄", "南弄", "西弄", "北弄", "中弄",
		"条", "区", "小区", "新区", "东区", "南区", "西区", "北区", 
		"队", "大队", "大厦", "饭店", "宾馆", "大饭店", "酒店", "大酒店", "埂子", "公寓", "广场", 
		"家园", "花园", "公园", "东园", "南园", "西园", "北园", 
		"门内", "门外", "巷", "学校", "小学", "中学", "大学", "学院", "校区",
		"银行", "证券", "保险", "医院", "人家", "科", "部", "公司",
		"山", "岭", "梁", "坡", "顶", "崖", "岗", "坨",
		"坎", "峪", "塘", "窝", "穴", "坑",
		"场", "坝", "甸", 
		"涧", "湾", "滩", "洞", "沟", "洼", "港",
		"河", "湖", "溪", "池", "淀", "泉", "潭", "泡子",
		"��", "庄", "屯", "疃", "城", "居", "聚", "坊", "里", "地",
		"巷", "条", "路", "街", "段", "排", "胡同",
		"寺", "观", "堂", "阁", "斋", "庵", "坛",
		"亭", "楼", "宫", "殿", "园",
		"门", "门内", "门外",
		"院", "棚", "桥", "渠", "埠", "井", "房", "圈", "坟", "墓",
		"处", "局", "仓", "库", "司", "署", "所", "厅", "馆", "府", "务",
		"营", "寨", "旗", "堡", "关",
		"省", "县", "区", "镇", "乡", "道",
		"窑", "坊", "场", "厂", "作",
		"市", "店", "铺", "里",
		"道口", "苑", "大楼", "宿舍", "公寓", "大厦", "层", 
		"排", "村", "房间", "组", "#", "-", ",",
		"村委会", "集团", "居委会", "站",
		"泾", "宅", "渡", "浜", "堰", "浪"
	};
	
	// 公司名称通名
	public final static String[] corpCommonName = {
		"公司", "有限", "责任", "股份", "科技", "集团", "创业", "实业"
	};
	
	// 行政区通名
	public final static String[] zoneCommonName = {
		"省", "市", "区", "县"
	};
	
	// 行政区专名
	public final static String[] zoneSpecName = {
		"中国", "上海", "湖南", "黑龙江", "云南", "河南", "宁夏", "新疆", "新疆维吾尔", "甘肃", "江西", "浙江",
		"北京", "内蒙古", "海南", "天津", "西藏", "四川", "河北", "江苏", "山西", "福建", "吉林", "湖北", "青海",
		"广西", "山东", "重庆", "陕西", "安徽", "辽宁", "广东", "贵州"
	};
	
	// 初始化
	public static void init() {
		try {
			BufferedReader 			reader 		= null;
			ArrayList<String> 		read_list 	= new ArrayList<String>();
			HashMap<String, String> read_map 	= new HashMap<String, String>();
			String 					read_str 	= null;
			
			/**初始化地区树开始*/
			reader = new BufferedReader(new FileReader("./zone_tree.txt"));
			read_str = reader.readLine();
			
			while (read_str != null) {// 逐行读取文件，直到读完为止
				ArrayList<String> str_arr = Util.getSplitStringArr(read_str.trim(), " ");
				if (str_arr.size() == 2) {
					read_map.put(str_arr.get(0), str_arr.get(1));
					read_list.add(str_arr.get(0));
				}
				read_str = reader.readLine();// 读取下一行
			}
			reader.close();
			
			ZoneTreeNode zoneRootNode = new ZoneTreeNode("中国"); // 根结点
			zoneLevel = new HashMap<String, String>();
			for (int i = 0; i < read_list.size(); i++) {
				String zone_code = "";	// 地区代码
				String zone_name = "";	// 地区名
				String p_zone_code = "";	// 上级地区代码
				String p_zone_name = "";	// 上级地区名
				String p_zone_code2 = "";	// 上上级地区代码
				String p_zone_name2 = "";	// 上上级地区名
				
				zone_code = read_list.get(i);
				zone_name = read_map.get(zone_code);
				
						
				if (zone_code.endsWith("0000")) { // 省、直辖市
					zoneRootNode.addChild(new ZoneTreeNode(zone_name)); // 添加地区结点
					Util.putIntoUniqueHashMap(zoneLevel, zone_name, "1"); // 地区级别为1
				} else if (zone_code.endsWith("00")) { // 市、区
					p_zone_code = zone_code.substring(0, 2) + "0000";
					p_zone_name = read_map.get(p_zone_code);
					if (p_zone_name != null) {
						String name = Util.getDelPrefixString(zone_name, p_zone_name);
						zoneRootNode.getChild(p_zone_name).addChild(new ZoneTreeNode(name)); // 添加地区结点
						Util.putIntoUniqueHashMap(zoneLevel, name, "2"); // 地区级别为2
					}
					
				} else { // 区、县
					p_zone_code = zone_code.substring(0, 4) + "00";
					p_zone_name = read_map.get(p_zone_code); // 例：河北省石家庄市
					p_zone_code2 = zone_code.substring(0, 2) + "0000";
					p_zone_name2 = read_map.get(p_zone_code2); // 例：河北省
					if (p_zone_name != null && p_zone_name2 != null) {
						if (Util.isPrefixAndNotEqual(zone_name, p_zone_name)) { // 上一级是前缀
							String name = Util.getDelPrefixString(zone_name, p_zone_name);
							zoneRootNode.getChild(p_zone_name2).getChild(Util.getDelPrefixString(p_zone_name, p_zone_name2)).addChild(new ZoneTreeNode(name)); // 添加地区结点
							Util.putIntoUniqueHashMap(zoneLevel, name, "3"); // 地区级别为3
						} else { // 上一级不是前缀，再往上找一级
							String name = Util.getDelPrefixString(zone_name, p_zone_name2);
							zoneRootNode.getChild(p_zone_name2).addChild(new ZoneTreeNode(name)); // 添加地区结点
							Util.putIntoUniqueHashMap(zoneLevel, name, "3"); // 地区级别为3
						}
					} else if (p_zone_name == null && p_zone_name2 != null) { // 找不到上一级，再往上找一级
						String name = Util.getDelPrefixString(zone_name, p_zone_name2);
						zoneRootNode.getChild(p_zone_name2).addChild(new ZoneTreeNode(name)); // 添加地区结点
						Util.putIntoUniqueHashMap(zoneLevel, name, "3"); // 地区级别为3
					}
					
				}
				
			}
			//System.out.println(String.format("%s %s %s", m_i,m_j,m_k));
			
			//System.out.println(zoneLevel.size());
			//System.out.println(zoneLevel.entrySet());
			zoneTree = new ZoneTree(zoneRootNode); // 构造地区树
			/**初始化地区树结束*/
			
			/**初始化省、市、区、县专名开始*/
			zoneSpecificName = new HashMap<String, String>();
			zoneSpecificName.put("中华人民共和国", "中国");
			zoneSpecificName.put("中国", "中国");
			
			reader = new BufferedReader(new FileReader("./zone_specfic.txt"));
			read_str = reader.readLine();
			
			while (read_str != null) {// 逐行读取文件，直到读完为止
				ArrayList<String> str_arr = Util.getSplitStringArr(read_str.trim(), " ");
				for (int i = 1; i < str_arr.size(); i++) {
					// 根据专名找到的全名和本次加入的全名不同，比如：已经存在朝阳-->朝阳区，本次加入的是朝阳-->朝阳市	
					Util.putIntoUniqueHashMap(zoneSpecificName, str_arr.get(i), str_arr.get(0));
				}
				
				zoneSpecificName.put(str_arr.get(0), str_arr.get(0)); // 例：北京市-->北京市
				read_str = reader.readLine();// 读取下一行
			}
			/**初始化省、市、区、县专名结束*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 在所有通名中查找name
	public static boolean findInCommonName(String name) {
		for (int i = 0; i < provinceCommonName.length; i++) {
			if (name.equalsIgnoreCase(provinceCommonName[i])) {
				return true;
			}
		}
		for (int i = 0; i < cityCommonName.length; i++) {
			if (name.equalsIgnoreCase(cityCommonName[i])) {
				return true;
			}
		}
		for (int i = 0; i < countyCommonName.length; i++) {
			if (name.equalsIgnoreCase(countyCommonName[i])) {
				return true;
			}
		}
		for (int i = 0; i < townCommonName.length; i++) {
			if (name.equalsIgnoreCase(townCommonName[i])) {
				return true;
			}
		}
		for (int i = 0; i < houseCommonName.length; i++) {
			if (name.equalsIgnoreCase(houseCommonName[i])) {
				return true;
			}
		}
		for (int i = 0; i < otherCommonName.length; i++) {
			if (name.equalsIgnoreCase(otherCommonName[i])) {
				return true;
			}
		}
		return false;
	}
	
	// 在通名中查找name
	public static boolean findInCommonName(String name, int rank) {
		switch(rank) {
		case Constants.PROVINCE:
			for (int i = 0; i < provinceCommonName.length; i++) {
				if (name.equalsIgnoreCase(provinceCommonName[i])) {
					return true;
				}
			}
			break;
		case Constants.CITY:
			for (int i = 0; i < cityCommonName.length; i++) {
				if (name.equalsIgnoreCase(cityCommonName[i])) {
					return true;
				}
			}
			break;
		case Constants.COUNTRY:
			for (int i = 0; i < countyCommonName.length; i++) {
				if (name.equalsIgnoreCase(countyCommonName[i])) {
					return true;
				}
			}
			break;
		case Constants.TOWN:
			for (int i = 0; i < townCommonName.length; i++) {
				if (name.equalsIgnoreCase(townCommonName[i])) {
					return true;
				}
			}
			break;
		case Constants.HOUSE:
			for (int i = 0; i < houseCommonName.length; i++) {
				if (name.equalsIgnoreCase(houseCommonName[i])) {
					return true;
				}
			}
			break;
		case Constants.OTHER:
			for (int i = 0; i < otherCommonName.length; i++) {
				if (name.equalsIgnoreCase(otherCommonName[i])) {
					return true;
				}
			}
			break;
		}
		return false;
	}
	
	// 在标准行政区划通名中查找name
	public static boolean findInStandardCommonName(String name) {
		for (int i = 0; i < provinceCommonName.length; i++) {
			if (name.equalsIgnoreCase(provinceCommonName[i])) {
				return true;
			}
		}
		for (int i = 0; i < cityCommonName.length; i++) {
			if (name.equalsIgnoreCase(cityCommonName[i])) {
				return true;
			}
		}
		for (int i = 0; i < countyCommonName.length; i++) {
			if (name.equalsIgnoreCase(countyCommonName[i])) {
				return true;
			}
		}
		return false;
	}
	
	// 在非标准行政区划通名中查找name
	public static boolean findInNotStandardCommonName(String name) {
		for (int i = 0; i < townCommonName.length; i++) {
			if (name.equalsIgnoreCase(townCommonName[i])) {
				return true;
			}
		}
		for (int i = 0; i < otherCommonName.length; i++) {
			if (name.equalsIgnoreCase(otherCommonName[i])) {
				return true;
			}
		}
		return false;
	}
	
	// 在门牌号通名中查找name
	public static boolean findInHouseCommonName(String name) {
		for (int i = 0; i < houseCommonName.length; i++) {
			if (name.equalsIgnoreCase(houseCommonName[i])) {
				return true;
			}
		}
		return false;
	}
	
	// 在通用分隔符中查找name
	public static boolean findInGeneralSplitName(String name) {
		for (int i = 0; i < generalSplitName.length; i++) {
			if (name.equalsIgnoreCase(generalSplitName[i])) {
				return true;
			}
		}
		return false;
	}
	
	// 将name映射到省、市、区、县专名，例如：北京映射到北京市
	public static String getZoneSpecificName(String name) {
		return zoneSpecificName.get(name);
	}
	
	// 返回专名字符串组，比如：朝阳，返回朝阳区，朝阳市，朝阳县
	public static ArrayList<String> getZoneSpecificNameList(String name) {
		return Util.getFromUniqueHashMap(zoneSpecificName, name);
	}
	
	// 返回地区的省、市、区级别
	public static ArrayList<String> getZoneLevelList(String name) {
		return Util.getFromUniqueHashMap(zoneLevel, name);
	}
	
	// 获得某一个地区的全名，例如：朝阳区对应到北京市朝阳区
	public static String getFullZoneName(String name) {
		ZoneTreeNode node = zoneTree.getTreeNode(name);
		
		if (node != null) {
			String fullName = "";
			zoneTree.printTree();
			//System.out.println(String.format("%s =========",zoneTree.getZoneRootNode().getZoneName()));
			while (node != null && node != zoneTree.getZoneRootNode()) {
				fullName = node.getZoneName() + "+" + fullName;
				node = node.getParent();
			}
			
			return fullName;
		}
		
		return null;
	}
	
	// 获得某一个地区的全名，例如：输入{"吉林省", null, "朝阳区"}，返回"吉林省+长春市+朝阳区"
	public static String getFullZoneName(String province_name, String city_name, String county_name) {
		ZoneTreeNode province_node 	= null;
		ZoneTreeNode city_node		= null;
		//System.out.println(String.format("%s %s %s", province_name, city_name, county_name));
		if (province_name != null && city_name == null && county_name == null) {
			return getFullZoneName(province_name);
		} else if (province_name == null && city_name != null && county_name == null) {
			//System.out.println(getFullZoneName(city_name));
			return getFullZoneName(city_name);
		} else if (province_name == null && city_name == null && county_name != null) {
			return getFullZoneName(county_name);
		} else if (province_name != null && city_name != null && county_name == null) {
			if ((province_node = zoneTree.getChildNode(province_name)) != null) {
				if ((city_node = zoneTree.getChildNode(province_node, city_name)) != null) {
					return province_name + "+" + city_name;
				}
			}
			return null;
		} else if (province_name != null && city_name == null && county_name != null) {
			if (province_name.equals("北京市") || province_name.equals("天津市") || province_name.equals("上海市") || province_name.equals("重庆市")) {
				if ((province_node = zoneTree.getChildNode(province_name)) != null) {
					if (zoneTree.getChildNode(province_node, county_name) != null) {
						return province_name + "+" + county_name;
					}
				}
			} else {
				if ((province_node = zoneTree.getChildNode(province_name)) != null) {
					for (int i = 0; i < province_node.getChildCount(); i++) {
						city_node = province_node.getChild(i);
						if (zoneTree.getChildNode(city_node, county_name) != null) {
							return province_name + "+" + city_node.getZoneName() + "+" + county_name;
						}
					}
				}
			}
			return null;
		} else if (province_name == null && city_name != null && county_name != null) {
			ArrayList<ZoneTreeNode> node_list = zoneTree.getAllTreeNode(city_name);
			for (int i = 0; i < node_list.size(); i++) {
				if (zoneTree.getChildNode(node_list.get(i), county_name) != null) {
					if (node_list.get(i).getParent() != null) {
						return node_list.get(i).getParent().getZoneName() + "+" + city_name + "+" + county_name;
					}
				}
			}
			return null;
		} else if (province_name != null && city_name != null && county_name != null) {
			if ((province_node = zoneTree.getChildNode(province_name)) != null) {
				if ((city_node = zoneTree.getChildNode(province_node, city_name)) != null) {
					if (zoneTree.getChildNode(city_node, county_name) != null) {
						return province_name + "+" + city_name + "+" + county_name;
					}
				}
			}
			return null;
		}
		return null;
	}
	
	// 返回所有标准行政区划通名字符串数组
	public static String[] getAllStandardCommonName() {
		String[] name_str_arr = new String[provinceCommonName.length + cityCommonName.length + countyCommonName.length];
		
		for (int i = 0; i < provinceCommonName.length; i++) {
			name_str_arr[i] = provinceCommonName[i];
		}
		for (int i = 0; i < cityCommonName.length; i++) {
			name_str_arr[i + provinceCommonName.length] = cityCommonName[i];
		}
		for (int i = 0; i < countyCommonName.length; i++) {
			name_str_arr[i + provinceCommonName.length + cityCommonName.length] = countyCommonName[i];
		}
		
		return name_str_arr;
	}
}
