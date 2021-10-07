package graficos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import baseDatos.GestionBaseDatos;
import clases.Cita;
import clases.Cliente;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class JdNuevaCita extends JDialog  implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private GestionBaseDatos gbd;
	private ResultSet resultado;
	private ResultSet resultadoSala;
	private ResultSet resultadoHora;
	private ResultSet resultadoCitaFechaSala;
	private Cita cita;
	private Cliente cliente;
	private JButton btnCrear;
	private JButton btnCancelar;
	private JButton btnBuscar;
	private JPanel buttonPane;
	private JTextField tfNif;
	private JTextField tfNombre;
	private JTextField tfTelefono;
	private JTextField tfDireccion;
	private JDateChooser dcFecha;
	private Date fecha;
	private JComboBox<String> cbSala;
	private JComboBox<String> cbMedico;
	private JComboBox<String> cbHora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JdNuevaCita dialog = new JdNuevaCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public JdNuevaCita() throws SQLException {

		gbd = new GestionBaseDatos();
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setType(Type.POPUP);
		setTitle("Crear Cita Nueva");
		setBounds(100, 100, 550, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("NUEVA CITA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(181, 11, 121, 33);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("FECHA");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(39, 209, 50, 20);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NIF CLIENTE");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 79, 90, 20);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("HORA");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(263, 273, 50, 20);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("SALA");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(223, 209, 90, 20);
		contentPanel.add(lblNewLabel_4);
		
		/* combo box para elegid entre los medicos que hay en la base de datos*/
		
		cbMedico = new JComboBox<String>();
		cbMedico.setBounds(99, 273, 161, 20);
		rellenarMedicos();
		contentPanel.add(cbMedico);
		
		/* CALENDARIO   //dateChooser.getDate(); pasa sacar la fecha elegida // */
			fecha = new Date();
			dcFecha = new JDateChooser("dd-MM-yyyy", "##/##/####", '_'); // CALENDARIO PARA ELEGIR FECHA
			dcFecha.getCalendarButton().addActionListener(this);
			dcFecha.getJCalendar();
			dcFecha.setBounds(99, 209, 121, 20);
			dcFecha.setMinSelectableDate(new Date());// Fecha minima seleccionable HOY
			contentPanel.add(dcFecha);
			
		
		
		/* combo box para elegid entre las SALAS que hay en la base de datos*/
			cbSala = new JComboBox<String>();
			cbSala.addActionListener(this);
			cbSala.setBounds(346, 209, 57, 20);
			rellenarSalas();
			contentPanel.add(cbSala);		
			
			
		/* combo box con las horas */
			cbHora = new JComboBox<String>();
			cbHora.setBounds(346, 273, 71, 20);
			//rellenaHoras();
			// se ponen las horas cuando carga la sala
			contentPanel.add(cbHora);
			
		
			
		////////////
		tfNif = new JTextField();
		tfNif.setBounds(111, 79, 103, 20);
		contentPanel.add(tfNif);
		tfNif.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("MEDICO");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(26, 273, 63, 20);
		contentPanel.add(lblNewLabel_5);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(40, 122, 89, 23);
		contentPanel.add(btnBuscar);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(346, 79, 113, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setEditable(false);
		tfTelefono.setBounds(346, 123, 113, 20);
		contentPanel.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setEditable(false);
		tfDireccion.setBounds(346, 163, 161, 20);
		contentPanel.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCrear = new JButton("CREAR");
				btnCrear.addActionListener(this);
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(this);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		
	}// fin nueva cita
	
	protected JButton getOkButton() {
		return btnCrear;
	}
	protected JButton getCancelButton() {
		return btnCancelar;
	}

	protected JPanel getButtonPane() {
		return buttonPane;
	}
	protected JTextField getTextField_2() {
		return tfDireccion;
	}
	protected JTextField getTextField_1() {
		return tfTelefono;
	}
	protected JDateChooser getDcFecha() {
		return dcFecha;
	}
	protected JTextField getTextField() {
		return tfNombre;
	}
	protected JComboBox getCbSalas() {
		return cbSala;
	}
	protected JButton getBtnNewButton() {
		return btnBuscar;
	}
	protected JTextField getTfnif() {
		return tfNif;
	}
	protected JComboBox getComboBox() {
		return cbMedico;
	}
	protected JComboBox getCbHora() {
		return cbHora;
	}
	
	protected  void rellenarSalas() throws SQLException { // mete las salas en el combo box
		
		resultadoSala = gbd.MostrarSalas();
		System.out.println(" 2 - carga salas");
		while (resultadoSala.next()) {
				 
			cbSala.addItem(resultadoSala.getString("idSala"));
		}
		
					
	}// fin metodo RELLENAR SALAS
	
	protected void rellenaHoras(String sala) throws SQLException {// mete las horas que queden libres en una sala
		
		
		if(cbSala.getItemCount()!=0) {
			cbHora.removeAllItems();// se borra la lista de horas
			
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			Date fechaCita = dcFecha.getDate();
			String fecha=df.format(fechaCita); // fecha elegida
			String hora;
			String horacita;
			resultadoHora = gbd.MostrarHoras();
			resultadoCitaFechaSala = gbd.MostrarCitaFechaSala(fecha, sala);
			
			//System.out.println("Fecha elegida: "+fecha +" Sala elegida: "+sala);
			//System.out.println("fecha de cita "+resultadoCitaFechaSala.next());
			
			boolean reserva =   resultadoCitaFechaSala.next();
			System.out.println("tienes citas: "+reserva);
			 	if(reserva) { // si hay citas para ese dia, se comprueban las horas ya reservadas
					cbHora.removeAllItems();// borra las horas que esten cargadas en el combobox

					 while(resultadoHora.next()) { ///carga todas las horas
						hora = resultadoHora.getString("Hora");
						System.out.println("carga: "+hora);
						cbHora.addItem(hora);
					 }
					 ///////////////

						String salaCita = resultadoCitaFechaSala.getString("idSala");
						resultadoCitaFechaSala = gbd.MostrarCitaFechaSala(fecha, sala);
						reserva =   resultadoCitaFechaSala.next();
						System.out.println("reserva "+reserva);
						int cbhoras = cbHora.getItemCount();
						int vuelta=0;
						do {	
							horacita = resultadoCitaFechaSala.getString("Hora");
							//System.out.println("vuelta while "+vuelta);
							//System.out.println("el combo box horas tiene: "+cbhoras);
							
					 		for (int j = 0; j < cbhoras; j++) { // bucle HORAS del COMBO BOX
					 			
					 			hora = cbHora.getItemAt(j);
					 			System.out.println("hora combo: "+hora);
								if(hora.equalsIgnoreCase(horacita)) { // si la hora de la cita es igual a de la tabla, la borra
									System.out.println(hora+" se BORRA, ya reservada");
									cbHora.removeItemAt(j);
									
									cbhoras--;
									j=cbhoras;
								}// fin if
										
							} //fin for combobox horas
					 		//System.out.println("sale del bucle de horas");
					 		vuelta++;
						}// fin DO while citas
						while(resultadoCitaFechaSala.next());
						
			 			JOptionPane.showMessageDialog(null, "Para la Sala "+sala+ " solo quedan "+cbhoras+" citas este dia");
					//System.out.println("prueba DESPUES del WHILE");
			 	}//fin if
			 	
			 	else{ // si no hay citas ese dia carga todas las horas
					cbHora.removeAllItems();
					System.out.println("no hay citas para ese dia");
					while(resultadoHora.next()) { ///
						hora = resultadoHora.getString("Hora");
						System.out.println("carga: "+hora);
						cbHora.addItem(hora);
					}// fin while resultado citas
			    }//fin else
			    
			
		}// fin IF
		
		
	}// fin rellena HORAS
	
	protected  void rellenarMedicos() throws SQLException { // mete los Medicos en el combo box
		 resultado = gbd.MuestraMedicos();
		 System.out.println("Carga Medicos");
		 while (resultado.next()) {
			cbMedico.addItem(resultado.getString("nombreMedico"));
		 }
		 
	}// fin metodo RELLENAR Medicos
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evento = e.getSource();
		String nif;
		
		
		if(evento.equals(cbSala)) { //cuando se pulsa en SALA
		
			try {
				if(cbSala.getItemCount()!=1) {
				cbHora.removeAll();
				String sala =(String) cbSala.getSelectedItem();
				System.out.println("Sala seleccionada: "+ sala);
				
					rellenaHoras(sala);
				}
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
			
				
		}// fin evento SALA
		
		
		if(evento.equals(btnBuscar)) {
			// gbd.MostrarCitas() -- para que devuelva objeto resultado con el array de las citas
			if(tfNif.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "campo en blanco");
			}
			else {	
				nif = tfNif.getText();
				try {
					
					if(gbd.CompruebaCliente(nif) == true) {
						
						cliente = gbd.BuscarCliente(tfNif.getText());// creamos objeto CLIENTE con el objeto devuelto por el metodo
						tfNombre.setText(cliente.getNombre());
						tfTelefono.setText(cliente.getTelefono());
						tfDireccion.setText(cliente.getDireccion());
					}//fin if
					else {
						JOptionPane.showMessageDialog(null, "Cliente no encontrado");
					}//fin else
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}// fin if
			
		}// fin Boton BUSCAR
		
		if(evento.equals(btnCrear)) {
			cita = new Cita();
			String Idmedico,idcliente;
			
			try {
				nif = tfNif.getText();
				
				    cliente = gbd.BuscarCliente(nif);
					idcliente = cliente.getId();
					cita.setIdClienteAux(idcliente);
				 
				resultado = gbd.MuestraMedicos();
				while(resultado.next()){
					if(cbMedico.getSelectedItem().toString().equalsIgnoreCase(resultado.getString("nombreMedico"))) {
						Idmedico = resultado.getString("idMedico");
						cita.setIdMedicoAux(Idmedico);
					
					}
				}
				
			cita.setIdSalaAux(cbSala.getSelectedItem().toString());
			
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			fecha = dcFecha.getDate();
			cita.setFechaCita(df.format(fecha));
				String hora;
				resultado = gbd.MostrarHoras();
				while(resultado.next()) {
					if(cbHora.getSelectedItem().toString().equalsIgnoreCase(resultado.getString("Hora"))) {
						hora = resultado.getString("idHora");
						cita.setIdHoraAux(hora);
					}
				}
			cita.setEstadoCita("1"); // el estado de la cita nueva se pone en 1 - ACTIVA
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				if(gbd.CrearCita(cita)) {// se pasa objeto Cliente al metodo crar nuevo cliente
					
					JOptionPane.showMessageDialog(null, "Cita creada");
					dispose();
				} 
				else {
					JOptionPane.showMessageDialog(null, "No ha sido posible crear la Cita");
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}// fin boton CREAR CITA
		
		if(evento.equals(btnCancelar)) {
			dispose();
		}// fin boton CANCELAR
		
	}// fin ACTION PERFORMED

}
