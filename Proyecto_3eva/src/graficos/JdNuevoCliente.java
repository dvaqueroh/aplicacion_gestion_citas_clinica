package graficos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.GestionBaseDatos;
import clases.Cliente;

import java.awt.Dialog.ModalityType;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class JdNuevoCliente extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private GestionBaseDatos gbd;
	protected Cliente cliente;
	/**/
	private JButton btnCrear;
	private JButton btnCancelar;
	private JPanel buttonPane;
	private JTextField tfNif;
	private JTextField tfNombre;
	private JTextField tfTelefono;
	private JTextField tfDireccion;
	private JLabel lblNewLabel_1;
	private JTextField tfEmail;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JdNuevoCliente dialog = new JdNuevoCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JdNuevoCliente() {
		setTitle("Nuevo Cliente");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setBounds(100, 100, 550, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NIF");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(127, 170, 75, 20);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(127, 207, 75, 20);
		contentPanel.add(lblNombre);
		
		JLabel lblTelefono = new JLabel("TELEFONO");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(127, 286, 75, 20);
		contentPanel.add(lblTelefono);
		
		JLabel lblDireccion = new JLabel("DIRECCION");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(127, 325, 75, 20);
		contentPanel.add(lblDireccion);
		
		tfNif = new JTextField();
		tfNif.setBounds(260, 170, 140, 20);
		contentPanel.add(tfNif);
		tfNif.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(260, 207, 140, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setBounds(260, 286, 140, 20);
		contentPanel.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(260, 325, 140, 20);
		contentPanel.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		lblNewLabel_1 = new JLabel("NUEVO CLIENTE");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(184, 43, 165, 30);
		contentPanel.add(lblNewLabel_1);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(260, 242, 140, 20);
		contentPanel.add(tfEmail);
		tfEmail.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(156, 245, 46, 20);
		contentPanel.add(lblNewLabel_2);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCrear = new JButton("Crear");
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
	}
	
	protected JButton getOkButton() {
		return btnCrear;
	}
	protected JButton getCancelButton() {
		return btnCancelar;
	}
	protected JPanel getButtonPane() {
		return buttonPane;
	}
	protected JTextField getTfDireccion() {
		return tfDireccion;
	}
	protected JPanel getContentPanel() {
		return contentPanel;
	}
	protected JTextField getTfTelefono() {
		return tfTelefono;
	}
	protected JTextField getTfNombre() {
		return tfNombre;
	}
	protected JTextField getTfNif() {
		return tfNif;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evento = e.getSource();
		gbd = new GestionBaseDatos();
		
	if(evento.equals(btnCrear)) {// bonton crear nuevo cliente
			
			try {
				if(gbd.CompruebaMedico(tfNif.getText())) {// comprobacion si ya existe ese nif
					JOptionPane.showMessageDialog(null, "Cliente ya existe");
				}// fin if comprueba cliente
				else {// si no existe cliente crea objeto Cliente con los datos
					cliente = new Cliente();
		
					cliente.setNif(tfNif.getText());
					cliente.setNombre(tfNombre.getText());
					cliente.setEmail(tfEmail.getText());
					cliente.setTelefono(tfTelefono.getText());
					cliente.setDireccion(tfDireccion.getText());
					
					if(gbd.CrearCliente(cliente)) {// se pasa objeto Cliente al metodo crar nuevo cliente
						
						JOptionPane.showMessageDialog(null, "Nuevo Cliente dado de alta");
						dispose();
					} 
					else {
						JOptionPane.showMessageDialog(null, "No ha sido posible dar de alta");
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}// fin crear crear cliente
			
		if(evento.equals(btnCancelar)) {
			dispose();
		}// fin boton Cancelar
		
	}// fin ACTION PERFORMED	

	protected JTextField getTfEmail() {
		return tfEmail;
	}
}//fin clase
