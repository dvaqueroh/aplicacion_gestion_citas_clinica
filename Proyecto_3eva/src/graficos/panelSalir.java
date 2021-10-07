package graficos;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class panelSalir extends JPanel implements ActionListener {
	private VentanaPrincipal ventanaPrincipal;
	private VentanaLogin VLogin;
	private JButton btnSalir;
	private JButton btnCerrar;

	/**
	 * Create the panel.
	 */
	public panelSalir() {
		setLayout(null);
		setBounds(000, 000, 800, 600);
		btnCerrar = new JButton("Cerrar Sesion");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(57, 132, 162, 38);
		add(btnCerrar);
		
		btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(this);
		btnSalir.setBounds(271, 132, 119, 38);
		add(btnSalir);

	}

	protected JButton getBtnSalir() {
		return btnSalir;
	}
	protected JButton getBtnCerrar() {
		return btnCerrar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evento= e.getSource();
		
		if(evento.equals(btnCerrar)) {
			// abre una nueva ventana login
			VLogin = new VentanaLogin();
			VLogin.setLocationRelativeTo(ventanaPrincipal);
			VLogin.setVisible(true);
			// cierra la ventana principal
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			frame.dispose();
			

		}// fin boton Cerrar sesion
		
		if(evento.equals(btnSalir)) {
			System.exit(WIDTH);
		}// fin boton Salir
		
	}
}
