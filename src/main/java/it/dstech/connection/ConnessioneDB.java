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
				"jdbc:mysql://192.168.2.96:3306/mogliemiglia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "dstech");
		return conn;

	}

	public boolean controlloUsername(String username) {
		String query = "SELECT marito.Username from mogliemiglia.marito where username = '\"" + username + "\"';";
		Statement state;
		try {
			state = connessionedb().createStatement();
			ResultSet result = state.executeQuery(query);
			if (result.equals(null)) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
		}
		return false;
	}

	public boolean controlloPassword(String password) {
		String query = "SELECT marito.password from mogliemiglia.marito where password = '\"" + password + "\"';";
		try {
			Statement state = connessionedb().createStatement();
			ResultSet result = state.executeQuery(query);
			if (result.getString("password").equals(password)) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
		}

		return false;

	}

	public boolean inserisciMarito(Marito marito) {
		String query = "INSERT INTO `mogliemiglia`.`marito`(`username` , `password`, `saldo`)values ('" + marito.getUsername() + "','"
				+ marito.getPassword() + "" + 0 + "');";
		boolean result = false;
		try {
			PreparedStatement prep = connessionedb().prepareStatement(query);
			result = prep.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
