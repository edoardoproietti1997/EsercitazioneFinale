package it.dstech.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.dstech.model.Marito;

public class ConnessioneDB {

	public Connection connessionedb() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "root");
		return conn;

	}

	public boolean controlloUsername(String username) {
		String query = "SELECT marito.Username from migliamoglie.marito where username = '" + username + "';";
		Statement state;
		try {
			state = connessionedb().createStatement();
			ResultSet result = state.executeQuery(query);
			if (result.equals(null)) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
		}
		return false;
	}

	public boolean controlloPassword(String username, String password) {
		String query = "SELECT marito.Username from migliamoglie.marito where username = '" + username + "';";
		try {
			Statement state = connessionedb().createStatement();
			ResultSet result = state.executeQuery(query);
			if (result.getString("password").equals(password)) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
		}

		return false;

	}

	public boolean inserisciMarito(Marito marito) {
		String query = "insert into migliamoglie.marito (username , password) values ('" + marito.getUsername() + "','"
				+ marito.getPassword() + "');";
		boolean result = false;
		try {
			PreparedStatement prep = connessionedb().prepareStatement(query);
			result = prep.execute();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
