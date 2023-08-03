package Inserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import java_crud.Conection;

public class Cargo {
	private int id_cargo;
	private String de_cargo;
	
	//Inicio de Getters and Setters
	public int getId_cargo() {
		return id_cargo;
	}
	public void setId_cargo(int id_cargo) {
		this.id_cargo = id_cargo;
	}
	public String getDe_cargo() {
		return de_cargo;
	}
	public void setDe_cargo(String de_cargo) {
		this.de_cargo = de_cargo;
	}	
	//Fin de Getters and Setters
	@Override
	public String toString() {
		return this.de_cargo;
	}
	
	public Vector mostrarCargos(){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Conection conn = new Conection();
		Connection con = conn.conect();
		
		Vector<Cargo> datos = new Vector<Cargo>();
		Cargo dat = null;
		
		String consulta = "SELECT * FROM cargo";
		
		try {
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			dat = new Cargo();
			dat.setId_cargo(0);
			dat.setDe_cargo("Seleccione cargo");
			datos.add(dat);
			
			while(rs.next()) {
				dat = new Cargo();
				dat.setId_cargo(rs.getInt("id_cargo"));
				dat.setDe_cargo(rs.getString("de_cargo"));
				datos.add(dat);
			}
			rs.close();
			
			
			
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un error al llenar el ComboBox de cargo"+e);
		}
		
		
		
		
		return datos;
		
	}
	
	
	
}
