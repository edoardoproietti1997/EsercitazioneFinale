package it.dstech.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import it.dstech.model.Marito;

public class ConnessioneDB {

	public Connection connessionedb() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://192.168.2.96:3306/mogliemiglia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "root");
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
		String query = "INSERT INTO migliamoglie.marito (idMarito, username, password, saldo) values (?, ?, ?, ?);";
		try {
			PreparedStatement prep = connessionedb().prepareStatement(query);
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
	


	public boolean controlloLogin(String username, String password) throws SQLException, ClassNotFoundException {
		String query = "Select marito.username, marito.password from migliamoglie.marito where marito.username = '?' marito.password = '?'";
		PreparedStatement prep = connessionedb().prepareStatement(query);
		prep.setString(1, username);
		prep.setString(2, password);
		ResultSet res = prep.executeQuery();
		
		if(res.next()) {
			return true;
		}
		return false;
	}
	
	
public int calcolaLivello (String username) throws ClassNotFoundException, SQLException
	{
		int livello;
		String query = "SELECT count(username) FROM migliamoglie.marito where username = '"+username+"' ;";
		PreparedStatement prep = connessionedb().prepareStatement(query);
		livello = prep.executeQuery().getInt(1);
		livello = (livello/10)+1;
		return livello;
	}
	
	public String prendiIdMarito (String username) throws ClassNotFoundException, SQLException
	{
		String query = "SELECT idmarito from mogliemiglia.marito where username = '"+username+"';";
		PreparedStatement prep = connessionedb().prepareStatement(query);
		String id = prep.executeQuery().getString(1);
		return id ;
	}
	
	public void inserisciAzione(String id, String azione)
	{
		LocalDateTime calendar = LocalDateTime.now();
		//String dataEOra = calendar.toString();
		String query = "INSERT INTO migliamoglie.storico (distorico, idmarito, azione, data) values (?, ?, ?, ?);";
		try {
			PreparedStatement prep = connessionedb().prepareStatement(query);
			prep.setString(2, id);
			prep.setString(3, azione);
			prep.setString(4, calendar.toString());
			prep.executeUpdate();
			prep.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modificaSaldo (String username, int saldo) throws ClassNotFoundException, SQLException
	{
		String query = "UPDATE migliamoglie.marito SET saldo='"+saldo+"' WHERE username='"+username+"';";
		PreparedStatement prep = connessionedb().prepareStatement(query);
		prep.executeUpdate();
		prep.close();
	}


}
