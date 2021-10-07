package graficos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JdInicio extends JDialog implements ActionListener{

	private VentanaLogin VLogin;
	private final JPanel contentPanel = new JPanel();
	private JButton btnComenzar;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JdInicio dialog = new JdInicio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JdInicio() {
		setTitle("CLINICA CES FUENCARRAL Version 0.4");
		setResizable(false);
		setBounds(100, 100, 550, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnComenzar = new JButton("COMENZAR");
		btnComenzar.addActionListener(this);
		btnComenzar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnComenzar.setBounds(196, 245, 150, 40);
		contentPanel.add(btnComenzar);
		
		JLabel FondoInicio = new JLabel("");
		FondoInicio.setIcon(new ImageIcon(JdInicio.class.getResource("/imagenes/learning-calendar2.png")));
		FondoInicio.setBounds(0, 0, 534, 511);
		contentPanel.add(FondoInicio);
		
		lblNewLabel = new JLabel("VERSION 0.4 02/06/2020");
		lblNewLabel.setBounds(10, 496, 150, 20);
		contentPanel.add(lblNewLabel);
	}

	protected JButton getBtnComenzar() {
		return btnComenzar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evento = e.getSource();
	
		if (evento.equals(btnComenzar)) {
			VLogin = new VentanaLogin(); // abrir la ventana de LOGIN
			VLogin.setLocationRelativeTo(contentPanel);
			VLogin.setVisible(true);
			
			dispose();
			setVisible(false);

		}// fin boton COMENZAR
		
	}
}
