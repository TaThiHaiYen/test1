package utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportData {
	SelectFieldInConfig fieldInConfig = new SelectFieldInConfig();
	// load dl lên database
	public void exportData() throws Exception {
		File myFile = new File(fieldInConfig.selectField("pathFile", "config"));
		PreparedStatement ps_createtable = null;
		PreparedStatement ps_drop = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(fieldInConfig.selectField("sourse", "config"), fieldInConfig.selectField("userName", "config"),
				"");

		FileInputStream inputStream = new FileInputStream(myFile);
		
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		
		String table_drop = "DROP TABLE IF EXISTS " +  fieldInConfig.selectField("tableName", "config");
		ps_drop = connection.prepareStatement(table_drop);
		ps_drop.execute();
		
		String sql_createtable = "CREATE TABLE " + fieldInConfig.selectField("tableName", "config") + " (STT INT NOT NULL auto_increment, MSSV INT NOT NULL, ho VARCHAR(255) NOT NULL, ten VARCHAR(255) NOT NULL, dOB date NOT NULL, lop VARCHAR(8), tenLop VARCHAR(255), sdt INT NOT NULL, email VARCHAR(255) NOT NULL, quequan VARCHAR(255) NOT NULL, ghichu TEXT, PRIMARY KEY (STT));";

		ps_createtable = connection.prepareStatement(sql_createtable);
		ps_createtable.execute();

		String sql = "INSERT INTO " + fieldInConfig.selectField("tableName", "config") + " (MSSV, ho, ten, dOB, lop, tenLop, sdt, email, queQuan, ghiChu) VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);

		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();

				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 1:
					int mssv = (int) nextCell.getNumericCellValue();
					ps.setInt(1, mssv);
					break;
				case 2:
					String ho = nextCell.getStringCellValue();
					ps.setString(2, ho);
					break;
				case 3:
					String ten = nextCell.getStringCellValue();
					ps.setString(3, ten);
					break;
				case 4:
					Date dOB = (Date) nextCell.getDateCellValue();
					ps.setString(4, getString(dOB));
					break;
				case 5:
					String lop = nextCell.getStringCellValue();
					ps.setString(5, lop);
					break;
				case 6:
					String tenLop = nextCell.getStringCellValue();
					ps.setString(6, tenLop);
					break;
				case 7:
					int sdt = (int) nextCell.getNumericCellValue();
					ps.setInt(7, sdt);
					break;
				case 8:
					String email = nextCell.getStringCellValue();
					ps.setString(8, email);
					break;
				case 9:
					String queQuan = nextCell.getStringCellValue();
					ps.setString(9, queQuan);
					break;
				case 10:
					String ghiChu = nextCell.getStringCellValue();
					ps.setString(10, ghiChu);
					break;
				}

			}
			ps.execute();
		}
		workbook.close();
		inputStream.close();
		ps.close();
		connection.close();
	}

	public static String getString(Date d) {
		return new SimpleDateFormat("yyyy-MM-dd").format(d);
	}
}
