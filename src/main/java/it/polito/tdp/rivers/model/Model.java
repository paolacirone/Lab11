package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;

	public Model(){
		
		dao = new RiversDAO();
		
	}
	
	public List<String> loadAllRiver(){
		return dao.getAllRivers();
	}
	
	public double getMedia(String r) {
		return this.dao.loadFlowMedia(r);
	}
	
	public double getTot(String r) {
		return this.dao.loadFlowTot(r);
		
	}
	
	public List<LocalDate> getDate(String r){
		return this.dao.getDate(r);
	}
}
