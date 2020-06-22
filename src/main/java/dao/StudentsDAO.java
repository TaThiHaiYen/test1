package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import connection.MyConnection;
import dto.Students;

public class StudentsDAO {
	public void extractData(List<Students> listOfStudents) {
		PreparedStatement state_createtable = null;
		Connection myConnection = null;

		try {
			myConnection = MyConnection.getMyConnection();
			myConnection.setAutoCommit(false);
			// create table
			String sql = "CREATE TABLE students(STT INT NOT NULL auto_increment  PRIMARY KEY "
					+ ", MSSV VARCHAR(200),ho VARCHAR(255),ten VARCHAR(255) , "
					+ "dOB VARCHAR(8),lop VARCHAR(255) ,tenLop VARCHAR(255) ,sdt VARCHAR(255) ,email VARCHAR(255) ,queQuan VARCHAR(255) ,ghiChu VARCHAR(255) )";
			state_createtable = myConnection.prepareStatement(sql);
			state_createtable.execute();

			// insert data
			PreparedStatement state_insertData = null;

			String sql2 = "INSERT INTO students(MSSV, ho, ten, dOB,lop,tenLop,sdt,email,queQuan,ghiChu) VALUES (?,?,?,?)";
			state_insertData = myConnection.prepareStatement(sql2);

			for (Students st : listOfStudents) {
				state_insertData.setString(1, st.getMSSV());
				state_insertData.setString(2, st.getHo());
				state_insertData.setString(3, st.getTen());
				state_insertData.setString(4, st.getdOB());
				state_insertData.setString(5, st.getLop());
				state_insertData.setString(6, st.getTenLop());
				state_insertData.setString(7, st.getSdt());
				state_insertData.setString(8, st.getEmail());
				state_insertData.setString(9, st.getQueQuan());
				state_insertData.setString(10, st.getGhiChu());
				state_insertData.addBatch();
			}

			state_insertData.executeBatch();

			myConnection.commit();
			System.out.println("Success");
		} catch (Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		}

	}
}
