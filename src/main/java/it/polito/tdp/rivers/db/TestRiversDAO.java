package it.polito.tdp.rivers.db;

import it.polito.tdp.rivers.model.River;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		System.out.println(dao.getAllRivers());
		
		River r = new River(1, "Jokulsa Eystri River");
		System.out.println(dao.loadFlowTot(r.getName()));
		System.out.println(dao.loadFlowMedia(r.getName()));
		//System.out.println(r.getNumMisure());
	}

}
