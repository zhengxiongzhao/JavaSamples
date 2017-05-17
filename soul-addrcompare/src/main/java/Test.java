import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Test {
	private static final int USING_TXT = 1;
	private static final int USING_XLS = 2;
	
	public static void main(String[] args) {
		test_address(Test.USING_XLS);
		test_corp(Test.USING_XLS);
	}
	
	public static void test_address(int test_type) {
		if (test_type == Test.USING_XLS) {
			test_address_xls();
		} else if (test_type == Test.USING_TXT) {
			test_address_txt();
		}
	}
	
	public static void test_corp(int test_type) {
		if (test_type == Test.USING_XLS) {
			test_corp_xls();
		} else if (test_type == Test.USING_TXT) {
			test_corp_txt();
		}
	}
	
	public static void test_address_xls() {
		try {
			Workbook 				workbook 			= Workbook.getWorkbook(new File("./result/地址.xls"));
			Sheet 					sheet 				= workbook.getSheet(0);
			ArrayList<String[]> 	addr_str_list 		= new ArrayList<String[]>();
			ArrayList<String[]> 	splited_str_list 	= new ArrayList<String[]>();
			ArrayList<String[]> 	std_str_list 		= new ArrayList<String[]>();
			ArrayList<Float>		compare_list		= new ArrayList<Float>();
			Calendar 				cal 				= null;
			long					time1				= 0;
			long					time2				= 0;
			
			for (int i = 0; i < sheet.getRows(); i++) {
				String[] str_arr = new String[2];
				str_arr[0] = sheet.getCell(0, i).getContents();
				str_arr[1] = sheet.getCell(1, i).getContents();
				
				if (!str_arr[0].trim().equals("") && !str_arr[1].trim().equals("")) {
					addr_str_list.add(str_arr);
				}
			}
			
			cal = Calendar.getInstance();
			time1 = cal.getTimeInMillis();
			
			if (addr_str_list.size() > 0) {
				Constants.init();
				PreTreatment pre = new PreTreatment();
				AddressSplit as = new AddressSplit();
				AddressStandardize asd = new AddressStandardize();
				AddressCompare ac = new AddressCompare();
				
				for (int i = 0; i < addr_str_list.size(); i++) {
					String[] splited_str_arr = new String[2];
					String[] std_str_arr = new String[2];
					float compare_result = 0;
					
					for (int j = 0; j < 2; j++) {
						pre.setAddress(addr_str_list.get(i)[j]);
						pre.process();
						as.setAddress(pre.getPreTreatedAddress());
						as.split();
						splited_str_arr[j] = as.getSplitedAddress();
						asd.setAddress(splited_str_arr[j]);
						asd.standardize();
						std_str_arr[j] = asd.getStandardizedAddress();
					}
					
					ac.setAddress(std_str_arr[0], std_str_arr[1]);
					compare_result = ac.compare();
					
					splited_str_list.add(splited_str_arr);
					std_str_list.add(std_str_arr);
					compare_list.add(compare_result);
				}
				
				cal = Calendar.getInstance();
				time2 = cal.getTimeInMillis();
				
				WritableWorkbook w_workbook = Workbook.createWorkbook(new File("./result/地址比对结果.xls"));
				WritableSheet w_sheet = w_workbook.createSheet("地址比对结果", 0);
				WritableCellFormat wc = new WritableCellFormat();
				wc.setBackground(jxl.format.Colour.YELLOW);
				Label label = new Label(0, 0, "源地址", wc);
				w_sheet.addCell(label);
				label = new Label(1, 0, "目标地址", wc);
				w_sheet.addCell(label);
				label = new Label(2, 0, "相似度", wc);
				w_sheet.addCell(label);
				label = new Label(3, 0, "分词结果", wc);
				w_sheet.addCell(label);
				label = new Label(4, 0, "分词结果", wc);
				w_sheet.addCell(label);
				label = new Label(5, 0, "标准化结果", wc);
				w_sheet.addCell(label);
				label = new Label(6, 0, "标准化结果", wc);
				w_sheet.addCell(label);
				for (int i = 0; i < addr_str_list.size(); i++) {
					label = new Label(0, i + 1, addr_str_list.get(i)[0]);
					w_sheet.addCell(label);
					label = new Label(1, i + 1, addr_str_list.get(i)[1]);
					w_sheet.addCell(label);
					label = new Label(2, i + 1, compare_list.get(i).toString());
					w_sheet.addCell(label);
					label = new Label(3, i + 1, splited_str_list.get(i)[0]);
					w_sheet.addCell(label);
					label = new Label(4, i + 1, splited_str_list.get(i)[1]);
					w_sheet.addCell(label);
					label = new Label(5, i + 1, std_str_list.get(i)[0]);
					w_sheet.addCell(label);
					label = new Label(6, i + 1, std_str_list.get(i)[1]);
					w_sheet.addCell(label);
				}
				label = new Label(0, addr_str_list.size() + 2, "处理地址对数量：" + addr_str_list.size() + "，运行时间：" + (time2 - time1) + "ms", wc);
				w_sheet.addCell(label);
				w_workbook.write();
				w_workbook.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test_corp_xls() {
		try {
			Workbook 				workbook 			= Workbook.getWorkbook(new File("./result/公司名称.xls"));
			Sheet 					sheet 				= workbook.getSheet(0);
			ArrayList<String[]> 	addr_str_list 		= new ArrayList<String[]>();
			ArrayList<Float>		compare_list		= new ArrayList<Float>();
			Calendar 				cal 				= null;
			long					time1				= 0;
			long					time2				= 0;
			
			for (int i = 0; i < sheet.getRows(); i++) {
				String[] str_arr = new String[2];
				str_arr[0] = sheet.getCell(0, i).getContents();
				str_arr[1] = sheet.getCell(1, i).getContents();
				
				if (!str_arr[0].trim().equals("") && !str_arr[1].trim().equals("")) {
					addr_str_list.add(str_arr);
				}
			}
			
			cal = Calendar.getInstance();
			time1 = cal.getTimeInMillis();
			
			if (addr_str_list.size() > 0) {
				for (int i = 0; i < addr_str_list.size(); i++) {
					float compare_result = 0;
					compare_result = CorpCompare.compare(addr_str_list.get(i)[0], addr_str_list.get(i)[1]);
					compare_list.add(compare_result);
				}
				
				cal = Calendar.getInstance();
				time2 = cal.getTimeInMillis();
				
				WritableWorkbook w_workbook = Workbook.createWorkbook(new File("./result/公司名称比对结果.xls"));
				WritableSheet w_sheet = w_workbook.createSheet("公司名称比对结果", 0);
				WritableCellFormat wc = new WritableCellFormat();
				wc.setBackground(jxl.format.Colour.YELLOW);
				Label label = new Label(0, 0, "公司名称1", wc);
				w_sheet.addCell(label);
				label = new Label(1, 0, "公司名称2", wc);
				w_sheet.addCell(label);
				label = new Label(2, 0, "相似度", wc);
				w_sheet.addCell(label);
				for (int i = 0; i < addr_str_list.size(); i++) {
					label = new Label(0, i + 1, addr_str_list.get(i)[0]);
					w_sheet.addCell(label);
					label = new Label(1, i + 1, addr_str_list.get(i)[1]);
					w_sheet.addCell(label);
					label = new Label(2, i + 1, compare_list.get(i).toString());
					w_sheet.addCell(label);
				}
				label = new Label(0, addr_str_list.size() + 2, "处理公司名称对数量：" + addr_str_list.size() + "，运行时间：" + (time2 - time1) + "ms", wc);
				w_sheet.addCell(label);
				w_workbook.write();
				w_workbook.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test_address_txt() {
		try {
			BufferedReader 			reader 				= null;
			BufferedWriter			writer1				= null;
			BufferedWriter			writer2				= null;
			String 					read_str 			= null;
			ArrayList<String[]> 	addr_str_list 		= new ArrayList<String[]>();
			ArrayList<String[]> 	splited_str_list 	= new ArrayList<String[]>();
			ArrayList<String[]> 	std_str_list 		= new ArrayList<String[]>();
			ArrayList<Float>		compare_list		= new ArrayList<Float>();
			Calendar 				cal 				= null;
			long					time1				= 0;
			long					time2				= 0;
			
			reader = new BufferedReader(new FileReader("./result/地址.txt"));
			read_str = reader.readLine();
			
			writer1 = new BufferedWriter(new FileWriter("./result/地址分词标准化比对结果.txt"));
			writer2 = new BufferedWriter(new FileWriter("./result/地址比对结果.txt"));
			
			while (read_str != null) {
				ArrayList<String> str_list = Util.getSplitStringArr(read_str.trim(), ",");
				if (str_list.size() >= 2) {
					String[] str_arr = new String[2];
					str_arr[0] = str_list.get(0).trim();
					str_arr[1] = str_list.get(1).trim();
					addr_str_list.add(str_arr);
				}
				read_str = reader.readLine();
			}
			reader.close();
			
			cal = Calendar.getInstance();
			time1 = cal.getTimeInMillis();
			
			if (addr_str_list.size() > 0) {
				Constants.init();
				PreTreatment pre = new PreTreatment();
				AddressSplit as = new AddressSplit();
				AddressStandardize asd = new AddressStandardize();
				AddressCompare ac = new AddressCompare();
				
				for (int i = 0; i < addr_str_list.size(); i++) {
					String[] splited_str_arr = new String[2];
					String[] std_str_arr = new String[2];
					float compare_result = 0;
					
					for (int j = 0; j < 2; j++) {
						pre.setAddress(addr_str_list.get(i)[j]);
						pre.process();
						as.setAddress(pre.getPreTreatedAddress());
						as.split();
						splited_str_arr[j] = as.getSplitedAddress();
						asd.setAddress(splited_str_arr[j]);
						asd.standardize();
						std_str_arr[j] = asd.getStandardizedAddress();
					}
					
					ac.setAddress(std_str_arr[0], std_str_arr[1]);
					compare_result = ac.compare();
					
					splited_str_list.add(splited_str_arr);
					std_str_list.add(std_str_arr);
					compare_list.add(compare_result);
				}
				
				cal = Calendar.getInstance();
				time2 = cal.getTimeInMillis();
				
				for (int i = 0; i < addr_str_list.size(); i++) {
					writer1.write("================================================================");
					writer1.newLine();
					writer1.write("源地址：" + '\t' + addr_str_list.get(i)[0]);
					writer1.newLine();
					writer1.write("目标地址：" + '\t' + addr_str_list.get(i)[1]);
					writer1.newLine();
					writer1.newLine();
					writer1.write("分词结果：" + '\t' + splited_str_list.get(i)[0]);
					writer1.newLine();
					writer1.write("分词结果：" + '\t' + splited_str_list.get(i)[1]);
					writer1.newLine();
					writer1.newLine();
					writer1.write("标准化结果：" + '\t' + std_str_list.get(i)[0]);
					writer1.newLine();
					writer1.write("标准化结果：" + '\t' + std_str_list.get(i)[1]);
					writer1.newLine();
					writer1.newLine();
					writer1.write("相似度：" + '\t' + compare_list.get(i));
					writer1.newLine();
					
					writer2.write("================================================================");
					writer2.newLine();
					writer2.write("源地址：" + '\t' + addr_str_list.get(i)[0]);
					writer2.newLine();
					writer2.write("目标地址：" + '\t' + addr_str_list.get(i)[1]);
					writer2.newLine();
					writer2.write("相似度：" + '\t' + compare_list.get(i));
					writer2.newLine();
				}
				
				writer1.write("================================================================");
				writer1.newLine();
				writer1.newLine();
				writer1.newLine();
				writer1.write("处理地址对数量：" + addr_str_list.size() + "，运行时间：" + (time2 - time1) + "ms");
				writer1.newLine();
				
				writer2.write("================================================================");
				writer2.newLine();
				writer2.newLine();
				writer2.newLine();
				writer2.write("处理地址对数量：" + addr_str_list.size() + "，运行时间：" + (time2 - time1) + "ms");
				writer2.newLine();
				
				writer1.flush();
				writer1.close();
				writer2.flush();
				writer2.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test_corp_txt() {
		try {
			BufferedReader 			reader 				= null;
			BufferedWriter			writer				= null;
			String 					read_str 			= null;
			ArrayList<String[]> 	addr_str_list 		= new ArrayList<String[]>();
			ArrayList<Float>		compare_list		= new ArrayList<Float>();
			Calendar 				cal 				= null;
			long					time1				= 0;
			long					time2				= 0;
			
			reader = new BufferedReader(new FileReader("./result/公司名称.txt"));
			read_str = reader.readLine();
			
			writer = new BufferedWriter(new FileWriter("./result/公司名称比对结果.txt"));
			
			while (read_str != null) {
				ArrayList<String> str_list = Util.getSplitStringArr(read_str.trim(), ",");
				if (str_list.size() >= 2) {
					String[] str_arr = new String[2];
					str_arr[0] = str_list.get(0).trim();
					str_arr[1] = str_list.get(1).trim();
					addr_str_list.add(str_arr);
				}
				read_str = reader.readLine();
			}
			reader.close();
			
			cal = Calendar.getInstance();
			time1 = cal.getTimeInMillis();
			
			if (addr_str_list.size() > 0) {
				for (int i = 0; i < addr_str_list.size(); i++) {
					float compare_result = 0;
					compare_result = CorpCompare.compare(addr_str_list.get(i)[0], addr_str_list.get(i)[1]);
					compare_list.add(compare_result);
				}
				
				cal = Calendar.getInstance();
				time2 = cal.getTimeInMillis();
				
				for (int i = 0; i < addr_str_list.size(); i++) {
					writer.write("================================================================");
					writer.newLine();
					writer.write("公司名称1：" + '\t' + addr_str_list.get(i)[0]);
					writer.newLine();
					writer.write("公司名称2：" + '\t' + addr_str_list.get(i)[1]);
					writer.newLine();
					writer.write("相似度：" + '\t' + compare_list.get(i));
					writer.newLine();
				}
				
				writer.write("================================================================");
				writer.newLine();
				writer.newLine();
				writer.newLine();
				writer.write("处理公司名称对数量：" + addr_str_list.size() + "，运行时间：" + (time2 - time1) + "ms");
				writer.newLine();
				
				writer.flush();
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}