package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//   lấy các trường trong config ra
public class SelectFieldInConfig {
	private ResultSet rs = null;
	PreparedStatement pst = null;

	public String selectField(String field, String tableName) throws Exception {
		String chosenField = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/databasecontrol", "root", "");
		String sql = "SELECT " + field + " FROM " + tableName;
		try {
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				chosenField = rs.getString(field);
			}
			return chosenField;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
//2. lấy data từ file lên table
// 3. kt từng dòng có data đó chưa
