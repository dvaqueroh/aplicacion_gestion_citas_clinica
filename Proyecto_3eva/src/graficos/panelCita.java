package graficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import baseDatos.Conexion;
import baseDatos.GestionBaseDatos;
import clases.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class panelCita extends JPanel implements ActionListener {
	/*CONEXION*/
	//BASE DE DATOS//
	private Connection con;
	private Conexion conexion = new Conexion();
	private GestionBaseDatos gbd;
	private Statement st;// objeto que realiza las sentencias
	private ResultSet resultado;
	private ResultSet resultadoHora;
	/**/
	private JdNuevaCita nuevaCita;

	/*TABLA*/
	private JTable tablaCitas;
	private DefaultTableModel modelo = new DefaultTableModel();
	private String[] datosCita = new String[8]; // string para guarda dlos datos
	private JButton btnCitaHoy;
	private JButton btnTodasCitas;
	private JButton btnCrearCita;
	private JButton btnAnularCita;
	
	/**
	 * Create the panel.
	 */
	public panelCita() {
		
		setBounds(000, 000, 800, 600);
		setVisible(true);
		JLabel lblNewLabel = new JLabel("MENU CITA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(185, 11, 121, 19);
		setLayout(null);
		
		
		/*tabla*/
		/*METER LA TABLA EN UN JTABBEDPANNEL para poder poner una tabla con las todas las citas, y otra solo con las de HOY*/
		
		tablaCitas = new JTable();
		modelo.addColumn("ID CITA");
		modelo.addColumn("FECHA");
		modelo.addColumn("Hora");
		modelo.addColumn("Cliente");
		modelo.addColumn("NifCliente");
		modelo.addColumn("Sala");
		modelo.addColumn("Medico");
		modelo.addColumn("estado");
		tablaCitas.setModel(modelo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tablaCitas);
		scrollPane.setBounds(44, 160, 720, 394);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		/**/
		
		add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Historial de Citas");
		lblNewLabel_3.setBounds(34, 128, 89, 19);
		add(lblNewLabel_3);
		
		btnCitaHoy = new JButton("Citas de Hoy");
		btnCitaHoy.addActionListener(this);
		btnCitaHoy.setBounds(10, 48, 121, 23);
		add(btnCitaHoy);
		
		btnTodasCitas = new JButton("Mostrar Todas");
		btnTodasCitas.addActionListener(this);
		btnTodasCitas.setBounds(10, 80, 121, 23);
		add(btnTodasCitas);
		
		btnCrearCita = new JButton("Crear Cita");
		btnCrearCita.addActionListener(this);
		btnCrearCita.setBounds(421, 48, 120, 40);
		add(btnCrearCita);
		
		btnAnularCita = new JButton("Anular Cita");
		btnAnularCita.addActionListener(this);
		btnAnularCita.setBounds(619, 48, 120, 40);
		add(btnAnularCita);
		

	}// fin panel cita


	protected JButton getBtnCitaHoy() {
		return btnCitaHoy;
	}
	protected JButton getBtnNewButton_1() {
		return btnTodasCitas;
	}
	protected JButton getBtnCrearCita() {
		return btnCrearCita;
	}
	
	protected JButton getBtnBorrarCita() {
		return btnAnularCita;
	}
	
	protected void BorrarTabla() {
		if(datosCita.length >= 0) {
			for (int i = 0; i < datosCita.length; i++) {
				
				modelo.setRowCount(0);// para borrar la lista de citas cuando se busca a un nuevo cliente
				
			}// fin for
		}
	}// fin BORRAR TABLA
	
	@Override
	
	public void actionPerformed(ActionEvent e) {
		// gbd.MostrarCitas() -- para que devuelva objeto resultado con el array de las citas
		// TODO Auto-generated method stub
		Object evento = e.getSource();
		String nif;
		gbd=new GestionBaseDatos();
		
		
		
		if(evento.equals(btnCitaHoy)) {
			gbd.ActualizarCita();
			BorrarTabla();
			try {
				resultado = gbd.MostrarCitasHoy();
			
			
				while(resultado.next()) {
					
					datosCita[0] = resultado.getString("idCita");
					datosCita[1] = resultado.getString("fechaCita");
					datosCita[2] = resultado.getString("Hora");
					datosCita[3] = resultado.getString("nombreCliente");
					datosCita[4] = resultado.getString("nifCliente");
					datosCita[5] = resultado.getString("idSala");
					datosCita[6] = resultado.getString("nombreMedico");
					if(resultado.getString("estadoCita").equalsIgnoreCase("0")) {
						datosCita[7] = "Cumplida";
					}
					else if(resultado.getString("estadoCita").equalsIgnoreCase("1")){
						datosCita[7] = "ACTIVA";
					}
					else if(resultado.getString("estadoCita").equalsIgnoreCase("2")){
						datosCita[7] = "Anulada";
					}
					modelo.addRow(datosCita); // agrega la cita a la tabla
	
				}// fin while
			} 
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}/// fin boton CITAS HOY
		
			
		
		if (evento.equals(btnTodasCitas)) {
			gbd.ActualizarCita();
			BorrarTabla();
			try {
				resultado = gbd.MostrarCitas();
				
				while(resultado.next()) {
					
					
					datosCita[0] = resultado.getString("idCita");
					datosCita[1] = resultado.getString("fechaCita");
					datosCita[2] = resultado.getString("Hora");
					datosCita[3] = resultado.getString("nombreCliente");
					datosCita[4] = resultado.getString("nifCliente");
					datosCita[5] = resultado.getString("idSala");
					datosCita[6] = resultado.getString("nombreMedico");
					if(resultado.getString("estadoCita").equalsIgnoreCase("0")) {
						datosCita[7] = "Cumplida";
					}
					else if(resultado.getString("estadoCita").equalsIgnoreCase("1")){
						datosCita[7] = "ACTIVA";
					}
					else if(resultado.getString("estadoCita").equalsIgnoreCase("2")){
						datosCita[7] = "Anulada";
					}
					modelo.addRow(datosCita); // agrega la cita a la tabla

				}// fin while para mostrar datos en la tabla
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}// fin BOTON TODAS LAS CITAS
		
		if(evento.equals(btnCrearCita)) {
			try {
				nuevaCita = new JdNuevaCita();
				nuevaCita.setVisible(true);
				nuevaCita.setLocationRelativeTo(this);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}// fin boton crear cita
		
		if(evento.equals(btnAnularCita)) {
			int seleccion = tablaCitas.getSelectedRow();
			String idCita = String.valueOf(tablaCitas.getValueAt(seleccion, 0));
			if(gbd.AnularCita(idCita)) {
				JOptionPane.showMessageDialog(nuevaCita, "Cita Anulada");
			}
			else {
				JOptionPane.showMessageDialog(nuevaCita, "No se ha podido anular la cita");
			}
			
		}// fin anular cita
		
	}// fin ACTIONPERFORMED

}
