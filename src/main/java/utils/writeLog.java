package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class writeLog {
	public void onWriteLog(String fileName, String fileData, String fileStatus) throws Exception {
		//connection database
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/databasecontrol", "root",
				"");
		
		//insert log
		String sql= "INSERT INTO log(idConfig, fileName, status) VALUES (?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.setString(1, fileName);
			ps.setString(2, fileData);
			ps.setString(3, fileStatus);
			ps.executeUpdate();
			System.out.println("success");
		} catch(Exception e) {
			System.out.println("fail");
			e.printStackTrace();
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
