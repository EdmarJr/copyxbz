package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoUtils {

	private static Connection connection;
	private static Statement st;

	public static Statement obterStatement() {

		if (connection == null) {
			try {

				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/grandbrindstemp", "admin",
						"123321");
				connection.setAutoCommit(false);
				st = connection.createStatement();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return st;

	}

	public static void fecharTudo() {
		try {
			st.close();
			connection.close();
			connection = null;
			st = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void comitar() {
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
