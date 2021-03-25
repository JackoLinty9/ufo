package it.polito.tdp.ufo.database;

import java.util.ArrayList;
import java.util.List;

public class TestProva {

	public static void main(String[] args) {
		
		sightingDAO dao=new  sightingDAO();
		
		List<String> formeUFO= dao.readShapes();
		
		for(String forma: formeUFO) {
			int count= dao.countByShape(forma);
			System.out.println("Ufo di forma "+forma+" sono "+count);
		}
	}	
}
