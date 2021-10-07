package graficos;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;

import baseDatos.GestionBaseDatos;
import clases.Medico;

public class panelMedico extends JPanel implements ActionListener {
	private GestionBaseDatos gbd;
	private Medico medico;
	private JdNuevoMedico nuevoMedico;
	private JdBorrarMedico borrarMedico;
	///
	private JTextField tfNifMedico;
	private JButton btnBuscar;
	private JTextField tfNombre;
	private JTextField tfNumColeg;
	private JTextField tfTelefono;
	private JTextField tfDireccion;
	private JLabel lblNewLabel_5;
	private JButton btnCrearMedico;
	private JButton btnBorrarMedico;

	/**
	 * Create the panel.
	 */
	public panelMedico() {
		setLayout(null);
		setBounds(000, 000, 800, 600);
		
		JLabel lblNewLabel = new JLabel("PANEL MEDICO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(195, 26, 160, 20);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NIF MEDICO");
		lblNewLabel_1.setBounds(16, 109, 65, 15);
		add(lblNewLabel_1);
		
		tfNifMedico = new JTextField();
		tfNifMedico.setBounds(101, 106, 110, 20);
		add(tfNifMedico);
		tfNifMedico.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(16, 133, 90, 25);
		add(btnBuscar);
		
		btnCrearMedico = new JButton("Nuevo Medico");
		btnCrearMedico.addActionListener(this);
		btnCrearMedico.setBounds(266, 76, 120, 25);
		add(btnCrearMedico);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(232, 216, 78, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("N\u00BA de Colegiado");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(226, 260, 90, 20);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Telefono");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(238, 315, 78, 20);
		add(lblNewLabel_4);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(350, 213, 155, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfNumColeg = new JTextField();
		tfNumColeg.setEditable(false);
		tfNumColeg.setBounds(350, 260, 155, 20);
		add(tfNumColeg);
		tfNumColeg.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setEditable(false);
		tfTelefono.setBounds(350, 315, 155, 20);
		add(tfTelefono);
		tfTelefono.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setEditable(false);
		tfDireccion.setBounds(350, 367, 155, 20);
		add(tfDireccion);
		tfDireccion.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Direccion");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(238, 367, 78, 20);
		add(lblNewLabel_5);
		
		btnBorrarMedico = new JButton("Borrar Medico");
		btnBorrarMedico.addActionListener(this);
		btnBorrarMedico.setBounds(404, 76, 120, 25);
		add(btnBorrarMedico);

	}
	protected JTextField getTfNifMedico() {
		return tfNifMedico;
	}
	protected JButton getBtnBuscar() {
		return btnBuscar;
	}
	
	protected JTextField getTfTelefono() {
		return tfTelefono;
	}
	protected JButton getBtnCrearMedico() {
		return btnCrearMedico;
	}
	protected JTextField getTfNumColeg() {
		return tfNumColeg;
	}
	protected JTextField getTextField() {
		return tfDireccion;
	}
	protected JTextField getTfNombre() {
		return tfNombre;
	}
	protected JButton getBtnBorrarMedico() {
		return btnBorrarMedico;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object evento = e.getSource();
		String nif;
		gbd=new GestionBaseDatos();
		medico= new Medico();
		
		if(evento.equals(btnBuscar)) {
			if(tfNifMedico.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "campo en blanco");
			}
			else {
				nif= tfNifMedico.getText();
				try {
					if(gbd.CompruebaMedico(nif)) {
						
						medico = gbd.BuscarMedico(nif);// creamos objeto MEDICO con el objeto devuelto por el metodo
						
						tfNombre.setText(medico.getNombre());
						tfNumColeg.setText(medico.getnColegiado());
						tfTelefono.setText(medico.getTelefono());
						tfDireccion.setText(medico.getDireccion());
					
					}
					else {
						JOptionPane.showMessageDialog(null, "Medico no encontrado");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}// fin BOTON BUSCAR
		
		if(evento.equals(btnCrearMedico)) {
			nuevoMedico = new JdNuevoMedico();
			nuevoMedico.setVisible(true);
			nuevoMedico.setLocationRelativeTo(this);
			
		}// fin BOTON CREAR NEUVO MEDICO
		
		if(evento.equals(btnBorrarMedico)) {
			borrarMedico = new JdBorrarMedico();
			borrarMedico.setVisible(true);
			borrarMedico.setLocationRelativeTo(this);
			
		}// fin boton borrar Medico
		
	}// fin ACTION PERFORMED



}
