package it.polito.tdp.ufo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestProva {

	public static void main(String[] args) {
		
		//procedimento con STATEMENT
		
//		//passo 1: definire la connessione
//		String jdbcURL= "jdbc:mysql://localhost/ufo_sightings?user=root&password=jacko";
//		
//		//passo 2: aprire la connessione
//		try {
//			Connection conn= DriverManager.getConnection(jdbcURL);//metodo statico della classe
//			
//			//passo 3: preparare invio della query al server (dammi una nuova navetta per farlo)
//			Statement st= conn.createStatement();
//			
//			//passo 4: inserire query nella navetta (query copiata da Heidi)
//			String sql= "SELECT DISTINCT shape "
//					+ "FROM sighting "
//					+ "WHERE shape<>'' "
//					+ "ORDER BY shape ASC";
//			
//			//passo 5: eseguire la query (attraverso cursore)
//			ResultSet res= st.executeQuery(sql);
//			
//			List<String> formeUfo= new ArrayList<>();
//			while(res.next()){ // ==true
//				String forma= res.getString("shape"); // .getString(String nomeColonna)
//				formeUfo.add(forma);
//			}
//			st.close(); // ha senso se ho tanti statement all'interno di una connessione ( se chiudo statement quando ho finito di usarlo libero spazio 
//			
//			System.out.println(formeUfo);
//			
//			//passo 6: chiudere la connessione
//			conn.close(); 
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		
		//procedimento con PREPARED STATEMENT -> da usare sempre 
		
		//passo 1: definire la connessione
		String jdbcURL= "jdbc:mysql://localhost/ufo_sightings?user=root&password=jacko";
		
		//passo 2: aprire la connessione
		try {
			Connection conn= DriverManager.getConnection(jdbcURL);//metodo statico della classe
			
			// passo 3: definire la query (con preparedStatement definisco la stringa e la passo come parametro a st)
			String sql= "SELECT DISTINCT shape "
					+ "FROM sighting "
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC";
			
			//passo 4: invio della query al server (dammi una nuova navetta per farlo)
			PreparedStatement st= conn.prepareStatement(sql);
			
			//passo 5: eseguire la query (attraverso cursore)
			ResultSet res= st.executeQuery(sql);
			
			List<String> formeUfo= new ArrayList<>();
			while(res.next()){ // ==true
				String forma= res.getString("shape"); // .getString(String nomeColonna)
				formeUfo.add(forma);
			}
			st.close(); // ha senso se ho tanti statement all'interno di una connessione ( se chiudo statement quando ho finito di usarlo libero spazio 
			
			System.out.println(formeUfo);
			
			//prova con altra query (parametro passato)
			String sql2="SELECT COUNT(*) AS cnt FROM sighting WHERE shape= ?" ;
			String shapeScelta= "circle";
			PreparedStatement st2= conn.prepareStatement(sql2);
			st2.setString(1, shapeScelta);
			ResultSet res2= st2.executeQuery();
			res2.first();
			int count= res2.getInt("cnt");
			st2.close();
			
			System.out.println("Ufo di forma "+shapeScelta+" sono "+count);
			
			//passo 6: chiudere la connessione
			conn.close(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
