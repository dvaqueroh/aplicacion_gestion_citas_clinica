package graficos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import baseDatos.GestionBaseDatos;
import clases.Cliente;
import java.awt.Color;
import java.awt.SystemColor;

public class JdBorrarCliente extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private GestionBaseDatos gbd;
	private JButton btnBorrar;
	private JButton btnCancelar;
	JTextField tfNif;
	private JTextField tfIdcliente;
	private JTextField tfNombre;
	private JTextField tfTelefono;
	private JButton btnBuscar;
	private Cliente cliente;
	private JTextField tfEmail;
	private JTextField tfDireccion;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JdBorrarCliente dialog = new JdBorrarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JdBorrarCliente() {
		getContentPane().setBackground(SystemColor.menu);
		setTitle("BORRAR CLIENTE");
		setBounds(100, 100, 550, 550);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		JLabel lblNewLabel = new JLabel("BORRAR CLIENTE");
		lblNewLabel.setBounds(0, 0, 0, 0);
		
		getContentPane().add(lblNewLabel);
		
		tfNif = new JTextField();
		tfNif.setBounds(95, 72, 110, 20);
		getContentPane().add(tfNif);
		tfNif.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nif Cliente");
		lblNewLabel_1.setBounds(10, 75, 59, 14);
		getContentPane().add(lblNewLabel_1);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(10, 105, 90, 25);
		getContentPane().add(btnBuscar);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(252, 139, 78, 20);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblIdCliente = new JLabel("Id Cliente");
		lblIdCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdCliente.setBounds(252, 96, 78, 20);
		getContentPane().add(lblIdCliente);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(252, 232, 78, 20);
		getContentPane().add(lblTelefono);
		
		tfIdcliente = new JTextField();
		tfIdcliente.setEditable(false);
		tfIdcliente.setBounds(351, 96, 155, 20);
		getContentPane().add(tfIdcliente);
		tfIdcliente.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(351, 139, 155, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setEditable(false);
		tfTelefono.setBounds(351, 232, 155, 20);
		getContentPane().add(tfTelefono);
		tfTelefono.setColumns(10);
		/////
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 478, 534, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				btnBorrar = new JButton("BORRAR");
				btnBorrar.addActionListener(this);
				btnBorrar.setActionCommand("OK");
				buttonPane.add(btnBorrar);
				getRootPane().setDefaultButton(btnBorrar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(this);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		tfEmail = new JTextField();
		tfEmail.setEditable(false);
		tfEmail.setBounds(351, 186, 155, 20);
		getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setEditable(false);
		tfDireccion.setBounds(351, 282, 155, 20);
		getContentPane().add(tfDireccion);
		tfDireccion.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(284, 189, 46, 20);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Direccion");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(220, 285, 110, 20);
		getContentPane().add(lblNewLabel_4);
	}
	
	protected JButton getBtnBorrar() {
		return btnBorrar;
	}
	protected JButton getBtnCancelar() {
		return btnCancelar;
	}
	protected JTextField getTfNombre() {
		return tfNombre;
	}
	protected JButton getBtnBuscar() {
		return btnBuscar;
	}
	protected JTextField getTfTelefono() {
		return tfTelefono;
	}
	protected JTextField getTfNif() {
		return tfNif;
	}
	protected JTextField getTfIdcliente() {
		return tfIdcliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evento = e.getSource();
		gbd=new GestionBaseDatos();
		cliente = new Cliente();
		
if (evento.equals(btnBuscar)) {// boton buscar
	String nif;
			
			if(tfNif.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "campo en blanco");
			}
			
			else {		
				nif = tfNif.getText();
				try {
					
					if(gbd.CompruebaCliente(nif) == true) {
						cliente = gbd.BuscarCliente(tfNif.getText());// creamos objeto CLIENTE con el objeto devuelto por el metodo
						JOptionPane.showMessageDialog(null, "Cliente encontrado " + cliente.getNombre() );
						tfIdcliente.setText(cliente.getId());
						tfNombre.setText(cliente.getNombre());
						tfEmail.setText(cliente.getEmail());
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
		
		}// fin BOTON BUSCAR
		
		if (evento.equals(btnBorrar)) {
			
			try {
				if(gbd.CompruebaCliente(tfNif.getText()) == true) {
					
					if(gbd.BorrarCliente(tfNif.getText())) {// se pasa objeto Cliente al metodo crar nuevo cliente
						
						JOptionPane.showMessageDialog(null, "Cliente Eliminado");
						dispose();
					} 
					else {
						JOptionPane.showMessageDialog(null, "No ha sido posible Eliminar la ficha");
					}
					
				}// fin if
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}// fin boton borrar
		
		if(evento.equals(btnCancelar)) {
			dispose();
		}// fin boton cancelar
		
	}// Fin ACTION PERFORMED



	protected JTextField getTfEmail() {
		return tfEmail;
	}
	protected JTextField getTfDireccion() {
		return tfDireccion;
	}
}// fin clase
