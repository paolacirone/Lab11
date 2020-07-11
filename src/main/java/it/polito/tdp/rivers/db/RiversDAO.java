package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RiversDAO {

	public List<String> getAllRivers() {

		final String sql = "SELECT id, name FROM river";

		List<String> rivers = new LinkedList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				River r =new River(res.getInt("id"), res.getString("name"));
				rivers.add(r.getName());
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}

	public double loadFlowMedia(String river) {

		String sql = "SELECT  AVG(f.flow) as m  " + 
				"FROM river AS r, flow AS f " + 
				"WHERE r.id = f.river AND r.name =  ? ";

		double result = 0.0;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, river);
			ResultSet res = st.executeQuery();

			while(res.next()) {
				result = res.getDouble("m");
			}

			conn.close();
		

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

			return result;

	}

	public double loadFlowTot(String river) {

		String sql = " SELECT  COUNT(f.id) as m  " +
		"FROM river AS r, flow AS f  "
				+ "WHERE r.id = f.river AND r.name = ? ";

		double result = 0.0;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, river);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result = res.getDouble("m");
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;

	}

	public List<Flow> loadAllFlows(River river) {

		List<Flow> flussi = new ArrayList<>();

		String sql = "SELECT flow.id, day, flow  " + " FROM flow,river " + " WHERE river.id = flow.river "
				+ " AND river.id = ? ";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Flow f = new Flow(rs.getDate("day").toLocalDate(), rs.getDouble("flow"), river);
				flussi.add(f);

			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

		return flussi;

	}
	
	public List<LocalDate> getDate(String river){
		
		String sql = "SELECT  f.day " + 
				"FROM river AS r, flow AS f  " + 
				"WHERE r.id = f.river AND r.name =  ? " + 
				"ORDER BY f.day ASC "; 
		
		List<LocalDate> result  = new ArrayList<>(); 
		

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, river);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getDate("f.day").toLocalDate());
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
		
		
	}

}
