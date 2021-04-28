package it.polito.tdp.ufo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class sightingDAO { //classe dao per estrarre le informazioni che mi servono da un database e poi usarle nel Model
		
	public List<String> readShapes(){ //restituisce l'elenco di tutte le differenti forme del database
		try {
			Connection conn = DBConnect.getConnection();
		
		    String sql= "SELECT DISTINCT shape FROM sighting WHERE shape<>'' ORDER BY shape ASC";
		
		    PreparedStatement st= conn.prepareStatement(sql);
		
		    ResultSet res= st.executeQuery(sql);
		
		    List<String> formeUfo= new ArrayList<>();
		    while(res.next()){ // ==true
		    	String forma= res.getString("shape"); // .getString(String nomeColonna)
			    formeUfo.add(forma);
		    }
		    
		    st.close();
		    conn.close();
		    return formeUfo;
		    
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int countByShape(String shape) { //restituisce il numero di ufo di una data forma passata come parametro
		
		try {
			Connection conn = DBConnect.getConnection();
			
			String sql2="SELECT COUNT(*) AS cnt FROM sighting WHERE shape= ?" ;
			
			PreparedStatement st2= conn.prepareStatement(sql2);
			
			st2.setString(1, shape); //settaggio del '?' in più rispetto alla precedente query
			
			ResultSet res2= st2.executeQuery();
			res2.first();
			int count= res2.getInt("cnt");
			
			st2.close();
			conn.close();
			return count;
			
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
