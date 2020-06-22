package main;

import utils.ExportData;
import utils.SelectFieldInConfig;

public class MainTest {
	public static void main(String[] args) {
		try {
//			String filedata = "E:\\Tai Lieu\\CNTT\\1920\\DW\\workspace-datawarehouse\\DW_Group06\\src\\main\\java\\data\\sinhvien_sang_nhom2.xlsx";

			SelectFieldInConfig selectFieldInDB = new SelectFieldInConfig();
			System.out.println(selectFieldInDB.selectField("sourse", "config"));

			ExportData data = new ExportData();
			data.exportData();

			System.out.println("success");
		} catch (Exception e) {
			System.out.println("fail");
			e.printStackTrace();
		}
	}
}
