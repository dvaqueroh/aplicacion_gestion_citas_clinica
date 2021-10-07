package graficos;


import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JPanel;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

import baseDatos.GestionBaseDatos;
import clases.Cliente;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class PanelCitaClienteHistorico extends JPanel {
	/*CONEXION*/
	private GestionBaseDatos gbd;
	private ResultSet resultado;
	private panelCliente panelCliente;
	/*TABLA*/
	private DefaultTableModel modelo = new DefaultTableModel();
	private String[] datosCita = new String[7]; // string para guarda dlos datos
	private Cliente cliente;
	private JTable tablaCitas;
	/**
	 * Create the panel.
	 */
	String nif;
	
	public PanelCitaClienteHistorico() {
		setLayout(new BorderLayout(0, 0));
		setBounds(000, 000, 660	, 200);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tablaCitas = new JTable();
		scrollPane.setViewportView(tablaCitas);
		modelo.addColumn("ID CITA");
		modelo.addColumn("FECHA");
		modelo.addColumn("Hora");
		modelo.addColumn("Sala");
		modelo.addColumn("tamaño");
		modelo.addColumn("Medico");
		modelo.addColumn("Estado");
		tablaCitas.setModel(modelo);

	}// fin Panel citas historico

	protected void MeterDatosTabla(String nif) {
		/* BUSCA CLIENTE Y GUARDA DATOS EN LA TABLA*/
		System.out.println("CARGA TODAS LAS CITAS DEL CLIENTE EN HISTORICO");
		gbd=new GestionBaseDatos();
		cliente = new Cliente();
		 System.out.println(nif);
				try {
						//resultado = gbd.MostrarCitas(); /// cambiar por gbd.MostrarCitasCliente (nif)
					gbd.ActualizarCita();
					resultado = gbd.MostrarCitaCliente(nif);
						while(resultado.next()) {
							
							
							datosCita[0] = resultado.getString("idCita");
							datosCita[1] = resultado.getString("fechaCita");
							datosCita[2] = resultado.getString("Hora");
							datosCita[3] = resultado.getString("idSala");
							datosCita[4] = resultado.getString("tamanoSala");
							datosCita[5] = resultado.getString("nombreMedico");
							if(resultado.getString("estadoCita").equalsIgnoreCase("0")) {
								datosCita[6] = "Cumplida";
							}
							else if(resultado.getString("estadoCita").equalsIgnoreCase("1")){
								datosCita[6] = "ACTIVA";
							}
							
							else if(resultado.getString("estadoCita").equalsIgnoreCase("2")){
								datosCita[6] = "Anulada";
							}
						
							modelo.addRow(datosCita); // agrega la cita a la tabla
							
						}// fin while para mostrar datos en la tabla
				} 
				catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		
	}// fin meter datos en tabla
	
	protected void BorrarTabla() {
		if(datosCita.length >= 0) {
			for (int i = 0; i < datosCita.length; i++) {
				
				modelo.setRowCount(0); // para borrar la lista de citas cuando se busca a un nuevo cliente
	
			}
		}// fin for
	}// fin BORRAR TABLA
	
}// fin clase
