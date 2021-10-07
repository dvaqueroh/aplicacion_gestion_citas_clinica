
package graficos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import baseDatos.Conexion;
import baseDatos.GestionBaseDatos;

public class VentanaLogin extends JFrame implements ActionListener {

		private GestionBaseDatos gbd;
		//Graficos//
		private final JPanel contentPanel = new JPanel();
		private VentanaPrincipal ventanaPrincipal;
		private JTextField tfUsuario;
		private JPasswordField jpPass;
		private JButton btnEntrar;
		private JButton btnSalir;
		private JLabel iconoLogin;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		
		
		
		setResizable(false);
		setTitle("LOG IN Clina CES Fuencarral");
		setBounds(100, 100, 550, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INICIO DE SESION");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(164, 242, 146, 25);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setBounds(138, 304, 58, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CONTRASE\u00D1A");
		lblNewLabel_2.setBounds(116, 340, 80, 14);
		contentPanel.add(lblNewLabel_2);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(240, 301, 103, 20);
		contentPanel.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		jpPass = new JPasswordField();
		jpPass.setToolTipText("");
		jpPass.setBounds(240, 340, 103, 20);
		contentPanel.add(jpPass);
		
		{
			btnEntrar = new JButton("ENTRAR");
			btnEntrar.setBounds(189, 385, 88, 33);
			contentPanel.add(btnEntrar);
			btnEntrar.addActionListener(this);
			btnEntrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnEntrar);
		}
		{
			btnSalir = new JButton("SALIR");
			btnSalir.setBounds(339, 424, 73, 33);
			contentPanel.add(btnSalir);
			btnSalir.addActionListener(this);
			btnSalir.setActionCommand("Cancel");
		}
		
		iconoLogin = new JLabel("");
		iconoLogin.setHorizontalAlignment(SwingConstants.CENTER);
		iconoLogin.setIcon(new ImageIcon(JdLoggin.class.getResource("/imagenes/icon_login_03.png")));
		iconoLogin.setBounds(116, 33, 260, 196);
		contentPanel.add(iconoLogin);
		
		
		
		
	
		
	}// fin JdLoggin
	
	protected JButton getBtnEntrar() {
		return btnEntrar;
	}
	protected JButton getBtnSalir() {
		return btnSalir;
	}
	protected JTextField getTfUsuario() {
		return tfUsuario;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evento = e.getSource(); // captura el evento
		//new Conexion();
		gbd = new GestionBaseDatos();// para usar los metodos de gestion de base de datos
		
		if(evento.equals(btnEntrar)) { // accion boton ENTRAR
			try {
				String pass = new String (jpPass.getPassword());
				//System.out.println(pass);
				if(tfUsuario.getText().isEmpty() || pass.isEmpty()) {
					JOptionPane.showMessageDialog(null, "algun campo en blanco");
				}
				else{
					if(gbd.Login(tfUsuario.getText(),pass)){
						JOptionPane.showMessageDialog(null, "Acceso correcto, Bienvenido");
						dispose();
						/*Se crea nueva ventana principal*/
						ventanaPrincipal = new VentanaPrincipal();
						ventanaPrincipal.cerrarInicio();
						ventanaPrincipal.setVisible(true);
						ventanaPrincipal.setLocationRelativeTo(contentPanel);

					}
					else {JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrectos");
					
					}
				}	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				dispose();
				e1.printStackTrace();
			}
		}// fin accion boton ENTRAR
		
		if(evento.equals(btnSalir)) {// bonton salir
			
			System.exit(WIDTH);
		}// fin accion salir
	
}
	
}// fin clase
