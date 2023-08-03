package Inserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java_crud.Conection;

public class ViewTable {
	
	public void mostrarTbl(JTable tableUsers, JTextField idUsers) {
		
		
		String consulta = "SELECT u.id_users, u.no_users, u.ap_users, c.de_cargo FROM users u INNER JOIN cargo c ON u.id_cargo = c.id_cargo ";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Conection conn = new Conection();
		Connection con = conn.conect();
		
		DefaultTableModel modeloTbl = new DefaultTableModel();
		
		modeloTbl.addColumn("Nro de usuario");
		modeloTbl.addColumn("Nombre");
		modeloTbl.addColumn("Apellido");
		modeloTbl.addColumn("Cargo");
		
		tableUsers.setModel(modeloTbl);
		
		String[] datos = new String[4]; 
		
		try {
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				datos[0] = rs.getString(1);
				datos[1] = rs.getString(2);
				datos[2] = rs.getString(3);
				datos[3] = rs.getString(4);
				
				modeloTbl.addRow(datos);
			}
			tableUsers.setModel(modeloTbl);
			
			rs.close();
			
			
			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Hubo un error al llenar la tabla"+e);
		}
		
	}
	
	public void mostrarUser(JTable tableUsers) {
		
//		int idC = ((Cargo) idCargo.getItemAt(idCargo.getSelectedIndex())).getId_cargo();
		
		String consulta = "SELECT u.id_users, u.no_users, u.ap_users, c.de_cargo FROM users u INNER JOIN cargo c ON u.id_cargo = c.id_cargo";
		
		
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Conection conn = new Conection();
		Connection con = conn.conect();
		
		DefaultTableModel modeloTbl = new DefaultTableModel();
		
		modeloTbl.addColumn("Nro de usuario");
		modeloTbl.addColumn("Nombre");
		modeloTbl.addColumn("Apellido");
		modeloTbl.addColumn("Cargo");
		
		tableUsers.setModel(modeloTbl);
		
		String[] datos = new String[4]; 
		
		try {
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				datos[0] = rs.getString(1);
				datos[1] = rs.getString(2);
				datos[2] = rs.getString(3);
				datos[3] = rs.getString(4);
				
				modeloTbl.addRow(datos);
			}
			tableUsers.setModel(modeloTbl);
			
			
			ps.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Hubo un error al cargar usuarios por cargo:  "+e);
		}
		
		
		
	}
	
	
	public void selectUser(JTable tableUsers,JTextField idUser, JTextField noUser, JTextField apUser) {
		
		try {
			int fila = tableUsers.getSelectedRow();
			if(fila >= 0) {
				idUser.setText(tableUsers.getValueAt(fila, 0).toString());
				noUser.setText(tableUsers.getValueAt(fila, 1).toString());
				apUser.setText(tableUsers.getValueAt(fila, 2).toString());
			}else {
				JOptionPane.showMessageDialog(null, "No se selecciono correctamente");
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un error en la seleccion:  "+e);
		}
		
		
	}
	
	
	
}
