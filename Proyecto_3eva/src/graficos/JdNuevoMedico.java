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
import clases.Medico;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JdNuevoMedico extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private GestionBaseDatos gbd;
	private Medico medico;
	private JButton btnCrear;
	private JButton btnCancelar;
	private JTextField tfNombre;
	private JTextField tfNif;
	private JTextField tfNumColegiado;
	private JTextField tfTelefono;
	private JTextField tfDireccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JdNuevoMedico dialog = new JdNuevoMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JdNuevoMedico() {
		setBounds(100, 100, 550, 550);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CREAR NUEVO MEDICO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(157, 36, 220, 25);
		contentPanel.add(lblNewLabel);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(254, 141, 138, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfNif = new JTextField();
		tfNif.setBounds(254, 182, 138, 20);
		contentPanel.add(tfNif);
		tfNif.setColumns(10);
		
		tfNumColegiado = new JTextField();
		tfNumColegiado.setBounds(254, 225, 138, 20);
		contentPanel.add(tfNumColegiado);
		tfNumColegiado.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setBounds(254, 264, 138, 20);
		contentPanel.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(254, 299, 138, 20);
		contentPanel.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(116, 144, 91, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nif");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(116, 185, 91, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("N\u00BA de Colegiado");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(116, 228, 91, 14);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Telefono");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(116, 267, 91, 14);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Direccion");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(116, 302, 91, 14);
		contentPanel.add(lblNewLabel_5);
		{
			JPanel buttonPane = new JPanel();
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
	}// fin

	protected JButton getBtnCrear() {
		return btnCrear;
	}
	protected JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	protected JTextField getTfNumColegiado() {
		return tfNumColegiado;
	}
	protected JTextField getTfNombre() {
		return tfNombre;
	}
	protected JTextField getTfNif() {
		return tfNif;
	}
	protected JTextField getTfDireccion() {
		return tfDireccion;
	}
	protected JTextField getTfTelefono() {
		return tfTelefono;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evento = e.getSource();
		gbd = new GestionBaseDatos();
		
		if(evento.equals(btnCrear)) {
			try {
				if(gbd.CompruebaMedico(tfNif.getText())) {// comprobacion si ya existe ese nif
					JOptionPane.showMessageDialog(null, "Medico ya existe");
				}// fin if comprueba medico
				else {// si no existe Medico crea objeto Cliente con los datos
					medico = new Medico();
		
					medico.setnColegiado(tfNumColegiado.getText());
					medico.setNif(tfNif.getText());
					medico.setNombre(tfNombre.getText());
					medico.setTelefono(tfTelefono.getText());
					medico.setDireccion(tfDireccion.getText());
					
					if(gbd.CrearMedico(medico)) {// se pasa objeto Medico al metodo crar nuevoMedico
						
						JOptionPane.showMessageDialog(null, "Nuevo Medico dado de alta");
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
			
		}// fin boton crear
		
		if (evento.equals(btnCancelar)) {
			dispose();
		}// fin boton cancelar
		
		
	}// fin ACTION PERFORMED

}
