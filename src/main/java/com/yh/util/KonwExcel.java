package com.yh.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yh.model.Owner;

public class KonwExcel {
	public static List<Owner> getDataFromExcel(String filePath) {
		// String filePath = "E:\\123.xlsx";

		// 判断是否为excel类型文件
		if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
			System.out.println("文件不是excel类型");
		}
		FileInputStream fis = null;
		Workbook wookbook = null;
		try {
			// 获取一个绝对地址的流
			fis = new FileInputStream(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// 2003版本的excel，用.xls结尾
			wookbook = new HSSFWorkbook(fis);// 得到工作簿

		} catch (Exception ex) {
			// ex.printStackTrace();
			try {
				// 这里需要重新获取流对象，因为前面的异常导致了流的关闭——加了这一行
				fis = new FileInputStream(filePath);
				// 2007版本的excel，用.xlsx结尾

				wookbook = new XSSFWorkbook(filePath);// 得到工作簿
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 得到一个工作表
		Sheet sheet = wookbook.getSheetAt(0);

		// 获得表头
		Row rowHead = sheet.getRow(0);

		// 判断表头是否正确
		if (rowHead.getPhysicalNumberOfCells() != 4) {
			System.out.println("表头的数量不对!");
		}

		// 获得数据的总行数
		int totalRowNum = sheet.getLastRowNum();
		List<Owner> list = new ArrayList<Owner>();
		// 要获得属性
		String name = "";
		int id = 0;
		int phone = 0;
		String remark = "";
		// 获得所有数据
		for (int i = 1; i <= totalRowNum; i++) {
			Owner s = new Owner();
			// 获得第i行对象
			Row row = sheet.getRow(i);

			// 获得获得第i行第0列的 String类型对象
			Cell cell = row.getCell((short) 0);
			name = cell.getStringCellValue().toString();
			s.setO_name(name);
			// 获得一个数字类型的数据
			cell = row.getCell((short) 1);
			id = (int) cell.getNumericCellValue();
			s.setO_id(Integer.toString(id));
			cell = row.getCell((short) 2);
			phone = (int) cell.getNumericCellValue();
			s.setO_phone(Integer.toString(phone));
			cell = row.getCell((short) 3);
			remark = cell.getStringCellValue().toString();
			s.setO_remark(remark);
			list.add(s);
		}
		return list;
	}
}
