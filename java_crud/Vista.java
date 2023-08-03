package java_crud;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CRUD.Eliminar;
import CRUD.Insertar;
import Inserts.Cargo;
import Inserts.ViewTable;
import CRUD.Actualizar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vista extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtLastName;
	private JTable table;
	private JComboBox cbCargo;
	private JTextField txtID;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws  
	 */
	public Vista(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		this.setLocationRelativeTo(null);
//		txtID.setEnabled(true);
		
		ViewTable objT = new ViewTable();
		
		cbCargo = new JComboBox();
		cbCargo.setBounds(82, 171, 134, 22);
		contentPane.add(cbCargo);
		
		JLabel lblNewLabel = new JLabel("Cargos\r\n:");
		lblNewLabel.setBounds(28, 171, 52, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(28, 90, 52, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setBounds(28, 134, 52, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("MI PRIMER CRUD");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(45, 11, 463, 29);
		contentPane.add(lblNewLabel_3);

		txtName = new JTextField();
		txtName.setBounds(90, 87, 94, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		
		txtLastName = new JTextField();
		txtLastName.setBounds(90, 131, 94, 20);
		contentPane.add(txtLastName);
		txtLastName.setColumns(10);
		
		JButton Delete = new JButton("Eliminar");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar objE = new Eliminar();
				
				try {
					objE.Delete(txtName, txtLastName);
					objT.mostrarUser(table);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Hubo un error al eliminar un usuario"+e);
				}
				
			}
		});
		Delete.setBounds(82, 275, 89, 23);
		contentPane.add(Delete);
		
		JButton Modify = new JButton("Modificar");
		Modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar objA = new Actualizar();
				
				try {
					objA.update(txtName, txtLastName, txtID);
					objT.mostrarUser(table);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Hubo un error al actualizar datos: "+e1);
				}
			}
		});
		Modify.setBounds(127, 241, 89, 23);
		contentPane.add(Modify);
		
		JButton Register = new JButton("Registrar");
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insertar objI = new Insertar();
				try {
					objI.InsertarUser(txtName, txtLastName, cbCargo);
					objT.mostrarUser(table);

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Error al insertar un usuario"+e);
				}
			}
		});
		Register.setBounds(28, 241, 89, 23);
		contentPane.add(Register);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ViewTable obTable = new ViewTable();
				obTable.selectUser(table,txtID, txtName, txtLastName);
			}
		});
		table.setBounds(252, 64, 234, 215);
		contentPane.add(table);
		
		
		//Implementando el cargo en el comboBox
		Cargo objC = new Cargo();
		DefaultComboBoxModel modeloCargo = new DefaultComboBoxModel(objC.mostrarCargos());
		cbCargo.setModel(modeloCargo);
		
		//Llenando la tabla con los datos de la base de datos
//		ViewTable objT = new ViewTable();
//		objT.Consulta(txtName, txtLastName, cbCargo);
//		objT.mostrarTbl(table);
		
		
		objT.mostrarUser(table);
		
		JLabel lblNewLabel_4 = new JLabel("ID:");
		lblNewLabel_4.setBounds(28, 51, 27, 14);
		contentPane.add(lblNewLabel_4);
		
		txtID = new JTextField();
		txtID.setBounds(90, 48, 94, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		txtID.setEditable(false);
		
		
		
	}
}
