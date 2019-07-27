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
		try {
			PreparedStatement state = connessionedb().prepareStatement(query);
			ResultSet result = state.executeQuery(query);

			if (result.next()) {
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

	public void inserisciMarito(Marito marito) {
		String query = "INSERT INTO mogliemiglia.marito (idMarito, username, password, saldo) values (?, ?, ?, ?);";
		try {
			PreparedStatement prep = connessionedb().prepareStatement(query);

			prep.setInt(1, marito.getId());
			prep.setString(2, marito.getUsername());
			prep.setString(3, marito.getPassword());
			prep.setInt(4, 0);

			prep.executeUpdate();
			prep.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int prendiPunti (String username) throws ClassNotFoundException, SQLException 
	{
		int saldo = 0;
		String query = "SELECT saldo from mogliemiglia.marito where username = '"+username+"';";
		PreparedStatement prep = connessionedb().prepareStatement(query);
		saldo = prep.executeQuery().getInt(1);
		return saldo;
	}
}
