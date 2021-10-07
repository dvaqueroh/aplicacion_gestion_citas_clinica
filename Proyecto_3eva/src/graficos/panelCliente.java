package graficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;

public class panelCliente extends JPanel implements ActionListener {
	/*CONEXION*/
	//BASE DE DATOS//
	//private Connection conn;
	//private Conexion conexion;
	private GestionBaseDatos gbd;
	//private Statement st;// objeto que realiza las sentencias
	private ResultSet resultado;
	private JdNuevoCliente nuevoCliente;
	private JdBorrarCliente borrarCliente;
	private PanelCitaClienteHistorico panelCitaClienteHistorico;
	private PanelCitaClienteActiva panelCitaClienteActiva;
	/*TABLA*/
	private JTable tablaCitas;
	private DefaultTableModel modelo = new DefaultTableModel();
	private String[] datosCita = new String[6]; // string para guarda dlos datos
	JTextField tfNif;
	private JTextField tfNombre;
	private JTextField tfTelefono;
	private JButton btnBuscar;
	private JButton btnCrearCliente;
	private Cliente cliente;
	/**
	 * Create the panel.
	 */
	
	String nif;
	private JButton btnBorrarCliente;
	private JTextField tfDireccion;
	private JLabel lblNewLabel_5;
	private JTextField tfEmail;
	
	public panelCliente() {
		
		setBounds(000, 000, 800, 600);
		setVisible(true);
		JLabel lblNewLabel = new JLabel("CLIENTE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(194, 11, 100, 23);
		setLayout(null);
		
		add(lblNewLabel);
		
		tfNif = new JTextField();
		tfNif.setBounds(109, 83, 110, 20);
		add(tfNif);
		tfNif.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nif Cliente");
		lblNewLabel_1.setBounds(35, 86, 59, 14);
		add(lblNewLabel_1);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(35, 155, 90, 25);
		add(btnBuscar);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(154, 134, 78, 20);
		add(lblNewLabel_2);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(430, 175, 78, 20);
		add(lblTelefono);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(253, 134, 155, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setEditable(false);
		tfTelefono.setBounds(529, 175, 155, 20);
		add(tfTelefono);
		tfTelefono.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Historial de Citas");
		lblNewLabel_3.setBounds(35, 264, 120, 19);
		add(lblNewLabel_3);
		
		btnCrearCliente = new JButton("Nuevo Cliente");
		btnCrearCliente.addActionListener(this);
		btnCrearCliente.setBounds(444, 50, 120, 25);
		add(btnCrearCliente);
		
		btnBorrarCliente = new JButton("Borrar Cliente");
		btnBorrarCliente.addActionListener(this);
		btnBorrarCliente.setBounds(574, 51, 120, 25);
		add(btnBorrarCliente);
		
		JTabbedPane tabbedPaneCitas = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneCitas.setBounds(35, 294, 660, 295);
		add(tabbedPaneCitas);
		
		/*Paneles con las citas del cliente*/
		
		panelCitaClienteActiva = new PanelCitaClienteActiva();// solo las que estan activas
		tabbedPaneCitas.addTab("Activa", null, panelCitaClienteActiva, null);
		
		panelCitaClienteHistorico = new PanelCitaClienteHistorico(); // todas sus citas
		tabbedPaneCitas.addTab("Historico", null, panelCitaClienteHistorico, null);
		
		JLabel lblNewLabel_4 = new JLabel("Direccion");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(430, 221, 78, 14);
		add(lblNewLabel_4);
		
		tfDireccion = new JTextField();
		tfDireccion.setEditable(false);
		tfDireccion.setBounds(529, 218, 155, 20);
		add(tfDireccion);
		tfDireccion.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(462, 140, 46, 20);
		add(lblNewLabel_5);
		
		tfEmail = new JTextField();
		tfEmail.setEditable(false);
		tfEmail.setBounds(529, 137, 155, 20);
		add(tfEmail);
		tfEmail.setColumns(10);
		
		
		
		
		
		

	}// fin panel cita

	public JTextField getTfNif() {
		return tfNif;
	}

	public void setTfNif(JTextField tfNif) {
		this.tfNif = tfNif;
	}

	protected JTextField getTextField() {
		return tfNif;
	}
	protected JTextField getTextField_3() {
		return tfTelefono;
	}
	protected JButton getBtnBuscar() {
		return btnBuscar;
	}
	protected JTextField getTextField_2() {
		return tfNombre;
	}
	
	protected JButton getBtnCrearCliente() {
		return btnCrearCliente;
	}
	
	protected JButton getBtnBorrarCliente() {
		return btnBorrarCliente;
	}
	public String devuelveNif() {
		String nif;
		
		nif = tfNif.getText();
		
		return nif;
	}
	protected JTextField getTfDireccion() {
		return tfDireccion;
	}
	protected JTextField getTfEmail() {
		return tfEmail;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object evento = e.getSource();
		//String nif;
		gbd=new GestionBaseDatos();
		cliente = new Cliente();
		
		if (evento.equals(btnBuscar)) {// boton buscar
			
			// gbd.MostrarCitas() -- para que devuelva objeto resultado con el array de las citas
			if(tfNif.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "campo en blanco");
			}
			
			else {	
				panelCitaClienteHistorico.BorrarTabla();
				panelCitaClienteActiva.BorrarTabla();
				nif = tfNif.getText();
				try {
					
					if(gbd.CompruebaCliente(nif) == true) {
						
						cliente = gbd.BuscarCliente(tfNif.getText());// creamos objeto CLIENTE con el objeto devuelto por el metodo
						JOptionPane.showMessageDialog(null, "Cliente encontrado " + cliente.getNombre() );
						tfNombre.setText(cliente.getNombre());
						tfEmail.setText(cliente.getEmail());
						tfTelefono.setText(cliente.getTelefono());
						tfDireccion.setText(cliente.getDireccion());
						
						//System.out.println("se cargan los datos del cliente");
						
						panelCitaClienteHistorico.MeterDatosTabla(nif);
						panelCitaClienteActiva.MeterDatosTabla(nif);
							
					}//fin if
					else {
						JOptionPane.showMessageDialog(null, "Cliente no encontrado");
					}//fin else
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}// fin if
		
		}// fin BOTON BUSCAR
		
		if(evento.equals(btnCrearCliente)) {
			nuevoCliente = new JdNuevoCliente();
			nuevoCliente.setVisible(true);
			nuevoCliente.setLocationRelativeTo(this);
		}
		
		if(evento.equals(btnBorrarCliente)) {
			borrarCliente = new JdBorrarCliente();
			borrarCliente.setVisible(true);
			borrarCliente.setLocationRelativeTo(this);
		}
		
	}// fin ACTIONPERFORMED

}