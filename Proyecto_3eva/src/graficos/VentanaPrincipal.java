package graficos;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Color;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private JPanel Contenedor;
	private JPanel ContPaneles;
	private PanelLogo panelLogo;
	private panelCita panelCita;
	private panelCliente panelCliente;
	private panelMedico panelMedico;
	private panelSalir panelSalir;
	private JdInicio jdInicio;
	
	private JMenuBar menuBar;
	private JButton btnCita;
	private JButton btnCliente;
	private JButton btnMedico;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JButton btnSalir;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;




	public VentanaPrincipal() {
		setResizable(false);
		setTitle("CLINICA CES FUENCARRAL Version 0.4");
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		Contenedor = new JPanel();
		Contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Contenedor);
		Contenedor.setLayout(new BorderLayout(0, 0));
		
		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 0, 2, 0));
		menuBar.setForeground(new Color(0, 0, 0));
		menuBar.setBackground(SystemColor.textInactiveText);
		Contenedor.add(menuBar, BorderLayout.NORTH);
		
		/* PANELES */
		ContPaneles = new JPanel();
		Contenedor.add(ContPaneles, BorderLayout.CENTER);
		ContPaneles.setLayout(new BorderLayout(0, 0));
		
		///////
		
		panelLogo = new PanelLogo();
		ContPaneles.add(panelLogo, BorderLayout.CENTER);
		panelLogo.setVisible(true);
		
		panelCita = new panelCita();
		ContPaneles.add(panelCita, BorderLayout.CENTER);
		panelCita.setVisible(false);
		
		panelCliente = new panelCliente();
		ContPaneles.add(panelCliente, BorderLayout.CENTER);
		panelCliente.setVisible(false);
		
		panelMedico = new panelMedico();
		ContPaneles.add(panelMedico, BorderLayout.CENTER);
		panelMedico.setVisible(false);
		
		panelSalir = new panelSalir();
		ContPaneles.add(panelSalir, BorderLayout.CENTER);
		panelSalir.setVisible(false);
		
		
		
		/* Jdialog */
		jdInicio = new JdInicio();
		jdInicio.setVisible(true);
		jdInicio.setLocationRelativeTo(Contenedor);
		

		
		btnCita = new JButton("CITAS");
		btnCita.addActionListener(this);
		menuBar.add(btnCita);
		
		btnCliente = new JButton("CLIENTE");
		btnCliente.addActionListener(this);
		menuBar.add(btnCliente);
		
		btnMedico = new JButton("MEDICO");
		btnMedico.addActionListener(this);
		menuBar.add(btnMedico);
		
		label_3 = new JLabel("                          ");
		menuBar.add(label_3);
		
		label_4 = new JLabel("                         ");
		menuBar.add(label_4);
		
		lblNewLabel = new JLabel("                        ");
		menuBar.add(lblNewLabel);
		
		lblNewLabel_3 = new JLabel("       ");
		menuBar.add(lblNewLabel_3);
		
		lblNewLabel_1 = new JLabel("                   ");
		menuBar.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("                      ");
		menuBar.add(lblNewLabel_2);
		
		label_5 = new JLabel("                         ");
		menuBar.add(label_5);
		
		btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(this);
		menuBar.add(btnSalir);
		

		
		
		
		
		
	}// fin VENTANA PRINCIPAL
	protected JButton getBtnMenu() {
		return btnSalir;
	}
	protected JButton getBtnCliente() {
		return btnCliente;
	}
	protected JButton getBtnCita() {
		return btnCita;
	}
	protected JButton getBtnMedico() {
		return btnMedico;
	}
	
	
	protected void cerrarInicio() {
		jdInicio.setVisible(false);
		jdInicio.dispose();
	}// fin metodo Cerra Jdialog INICIO
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evento = e.getSource();
		
		if(evento.equals(btnCita)) {// BOTON CITA
			panelCita.setVisible(true);
			// ocultas //
			panelLogo.setVisible(false);
			panelCliente.setVisible(false);
			panelMedico.setVisible(false);
			panelSalir.setVisible(false);

		}// fin boton cita
		
		if(evento.equals(btnCliente)) {// BOTON CLIENTE
			panelCliente.setVisible(true);
			// ocultas //
			panelLogo.setVisible(false);
			panelCita.setVisible(false);
			panelMedico.setVisible(false);
			panelSalir.setVisible(false);

		}// fin boton cliente
		
		if(evento.equals(btnMedico)) {// BOTON MEDICO
			panelMedico.setVisible(true);
			// ocultas //
			panelLogo.setVisible(false);
			panelCita.setVisible(false);
			panelCliente.setVisible(false);
			panelSalir.setVisible(false);
			
		}// fin boton medico
		
		if(evento.equals(btnSalir)) {// BOTON SALIR
			panelSalir.setVisible(true);
			// ocultas //
			panelLogo.setVisible(false);
			panelCita.setVisible(false);
			panelCliente.setVisible(false);
			panelMedico.setVisible(false);
			

		}// fin boton salir
		
	}// fin ACTION PERFORMED
}// fin CLASE
