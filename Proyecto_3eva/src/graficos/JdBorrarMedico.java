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
import clases.Medico;

import java.awt.Color;
import java.awt.SystemColor;

public class JdBorrarMedico extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private GestionBaseDatos gbd;
	private JButton btnBorrar;
	private JButton btnCancelar;
	JTextField tfNifMedico;
	private JTextField tfIdMedico;
	private JTextField tfNumColegiado;
	private JTextField tfNombreMedico;
	private JTextField tfTelefonoMedico;
	private JButton btnBuscar;
	private Medico medico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JdBorrarMedico dialog = new JdBorrarMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JdBorrarMedico() {
		getContentPane().setBackground(SystemColor.menu);
		setTitle("BORRAR MEDICO");
		setBounds(100, 100, 550, 550);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		JLabel lblNewLabel = new JLabel("BORRAR MEDICO");
		lblNewLabel.setBounds(0, 0, 0, 0);
		
		getContentPane().add(lblNewLabel);
		
		tfNifMedico = new JTextField();
		tfNifMedico.setBounds(95, 72, 110, 20);
		getContentPane().add(tfNifMedico);
		tfNifMedico.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nif Medico");
		lblNewLabel_1.setBounds(10, 75, 59, 14);
		getContentPane().add(lblNewLabel_1);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(10, 105, 90, 25);
		getContentPane().add(btnBuscar);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(252, 175, 78, 20);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblIdCliente = new JLabel("Id Medico");
		lblIdCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdCliente.setBounds(252, 96, 78, 20);
		getContentPane().add(lblIdCliente);
		
		JLabel lblNewLabel_3 = new JLabel("Nº Colegiado");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(252, 133, 78, 20);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(252, 227, 78, 20);
		getContentPane().add(lblTelefono);
		
		tfIdMedico = new JTextField();
		tfIdMedico.setEditable(false);
		tfIdMedico.setBounds(351, 96, 155, 20);
		getContentPane().add(tfIdMedico);
		tfIdMedico.setColumns(10);
		
		tfNumColegiado = new JTextField();
		tfNumColegiado.setEditable(false);
		tfNumColegiado.setBounds(351, 133, 155, 20);
		getContentPane().add(tfNumColegiado);
		tfNumColegiado.setColumns(10);
		
		tfNombreMedico = new JTextField();
		tfNombreMedico.setEditable(false);
		tfNombreMedico.setBounds(351, 175, 155, 20);
		getContentPane().add(tfNombreMedico);
		tfNombreMedico.setColumns(10);
		
		tfTelefonoMedico = new JTextField();
		tfTelefonoMedico.setEditable(false);
		tfTelefonoMedico.setBounds(351, 227, 155, 20);
		getContentPane().add(tfTelefonoMedico);
		tfTelefonoMedico.setColumns(10);
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
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evento = e.getSource();
		gbd=new GestionBaseDatos();
		medico = new Medico();
			if (evento.equals(btnBuscar)) {// boton buscar
			String nif;
			
			if(tfNifMedico.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "campo en blanco");
			}
			
			else {		
				nif = tfNifMedico.getText();
				try {
					
					if(gbd.CompruebaMedico(nif) == true) {
						medico = gbd.BuscarMedico(tfNifMedico.getText());// creamos objeto MEDICO con el objeto devuelto por el metodo
						JOptionPane.showMessageDialog(null, "Medico encontrado " + medico.getNombre() );
						tfIdMedico.setText(medico.getId());
						tfNumColegiado.setText(medico.getnColegiado());
						tfNombreMedico.setText(medico.getNombre());
						tfTelefonoMedico.setText(medico.getTelefono());
					}//fin if
					else {
						JOptionPane.showMessageDialog(null, "Medico no encontrado");
					}//fin else
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}// fin if
		
		}// fin BOTON BUSCAR
		
		if (evento.equals(btnBorrar)) {
			
			try {
				if(gbd.CompruebaMedico(tfNifMedico.getText()) == true) {
					
					if(gbd.BorrarMedico(tfNifMedico.getText())) {// se pasa nif para borrar Medico
						
						JOptionPane.showMessageDialog(null, "Medico Eliminado");
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
			
		}// fin boton BORRAR
		
		if(evento.equals(btnCancelar)) {
			dispose();
		}// fin boton cancelar
		
	}// Fin ACTION PERFORMED



}// fin clase
