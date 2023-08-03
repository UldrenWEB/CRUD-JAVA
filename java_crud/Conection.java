package java_crud;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Conection {

	
	public Connection conect() {
		String BD = "jdbc:postgresql://localhost:3753/MyCrud";
		String user = "postgres";
		String password = "13270323";
		Connection conection = null;
		
		try {
			conection = DriverManager.getConnection(BD, user, password);
			
//			JOptionPane.showMessageDialog(null, "Se conecto correctamente a la base de datos");
		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "Hubo un error al conectar con la base de datos:  "+e);
		}
		
		return conection;
	}
}
