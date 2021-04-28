package it.polito.tdp.ufo.model;

import java.util.List;

import it.polito.tdp.ufo.database.sightingDAO;

public class Model {
	
	private List<String> formeUFO = null ;

	public List<String> getFormeUFO() { //il metodo restituisce la lista di tutte le forme degli ufo
		
		if (this.formeUFO==null) {
			sightingDAO dao = new sightingDAO() ;
			this.formeUFO = dao.readShapes() ;
		}
		return this.formeUFO;
	}

	public int getCountByForma(String forma) {
		
		sightingDAO dao = new sightingDAO();
		return dao.countByShape(forma) ;
	}

}
