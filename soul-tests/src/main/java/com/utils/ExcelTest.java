package com.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelTest {

	public static void main(String[] args) throws IOException {

		InputStream ins = new FileInputStream("/Users/zxzhao/Desktop/1.doc");
		// create a new workbook
		HSSFWorkbook wb = new HSSFWorkbook(ins);
		int sheet_count = wb.getNumberOfSheets();
		HSSFSheet sheet;
		for (int i = 0; i < sheet_count; i++) {
			sheet = wb.getSheetAt(i);
			HSSFPrintSetup printSetup = sheet.getPrintSetup();
			// A4纸
			printSetup.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
			FileOutputStream out = new FileOutputStream("images.docx");
			wb.write(out);
			out.close();
		}

	}
}
