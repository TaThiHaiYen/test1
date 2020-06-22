package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
// coppy data
public class CopyData {
	public void copyData() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/copieddatafromdwdb";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";

		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, userName, password);
			
			String sql_createtable = "CREATE TABLE students (STT INT NOT NULL auto_increment, MSSV INT NOT NULL, ho VARCHAR(255) NOT NULL, ten VARCHAR(255) NOT NULL, dOB date NOT NULL, lop VARCHAR(8), tenLop VARCHAR(255), sdt INT NOT NULL, email VARCHAR(255) NOT NULL, quequan VARCHAR(255) NOT NULL, ghichu TEXT, PRIMARY KEY (STT));";
			PreparedStatement ps = con.prepareStatement(sql_createtable);
			ps.execute();
			
			String sql = "INSERT INTO students SELECT * FROM datawarehousedb.students;";
			PreparedStatement pst = con.prepareStatement(sql);


			int rows = pst.executeUpdate();
			if (rows == 0) {
				System.out.println("Don't add any row!");
			} else {
				System.out.println(rows + " row(s)affected.");
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
