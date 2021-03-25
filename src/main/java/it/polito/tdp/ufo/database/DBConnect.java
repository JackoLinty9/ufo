package it.polito.tdp.ufo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	public static Connection getConnection() throws SQLException{
		String jdbcURL= "jdbc:mysql://localhost/ufo_sightings?user=root&password=jacko";
		return DriverManager.getConnection(jdbcURL);
	}
}
